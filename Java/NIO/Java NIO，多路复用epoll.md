## BIO

jps 查看java进程号

netstat -natp 查看各个进程的socket状态信息

strace -ff -o out java BIOSocket 检测各个java线程使用的系统调用，并打印到out.pid文件中

nc localhost 8090 以TCP连接进程

- 不使用多线程的BIO因为阻塞问题不能同时响应多个连接
- 使用了多线程的BIO可以解决阻塞问题，但线程本身耗费内存并且线程资源利用率不高（大多数清情况下属于阻塞状态）
- NIO的出现，可以使得socket以非阻塞的方式运行，具体使用是：serversocket的使用与BIO类似，但注意将其设置为非阻塞。当建立好一个连接之后，将该连接的socket同样设置为非阻塞，并放置到一个列表中去，同时，循环遍历该列表（也可以新建一个线程去遍历列表）
  - 存在的问题：每次遍历列表的时候，都要在每个socket中尝试读取，根据读取到的长度是否为0来判断当前socket是否来了数据，然而，每次read都是一个系统调用，如果大多数socket没有来数据，则read系统调用被浪费，这实际上是轮询方式的通病（浪费CPU时间）
  - 改进措施：对于每个socket来说，操作系统肯定知道它有没有来数据，因此，我们需要一个系统调用函数，用户进程给它传递一个socket列表，这个函数返回有数据的socket列表
- 这种通过一次调用可以返回有数据的socket列表的方式被称为多路复用，最开始的时候，操作系统提供了两种多路复用方式：select poll
  - select，向该函数传入3个fd集合，分别代表读集，写集和异常条件集，当这三个集合中至少有一个fd准备好（对于读集，当调用read不阻塞时，代表准备好；对于写集，调用write不阻塞时代表准备好；对于这三种集合，普通文件总是准备好的，socket则不一定）时，select返回，否则阻塞（当然也可以设置不阻塞，或者设置具体超时时间），并将三个参数指针修改为已准备好的fd集合
  - poll，跟select类似，只不过参数是一个集合，而该集合中的每个元素包含fd，关心的事件集合，发生的事件集合，仍然是通过修改参数的方式返回。并且，select最多只能传递1024个fd，而poll没有这个限制
  - 起到的效果：减少了系统调用次数
  - 存在的问题：每次都把所有的集合传递给内核（是想一下有非常多的长连接，每次都需要传这些长连接没意义），能不能在内核开辟一块缓冲区，通过动态的增加或删除fd来管理fd集合；select和poll在内核中的实现本质还是轮询，复杂度O(N)
  - 改进措施：修改上两个存在的问题
- epoll，仍然属于多路复用（只要说是多路复用，就只负责告诉程序准备好的fd，而不负责读取数据），但是增加的一些特征解决了上述的一些问题
  - 不同于select poll，epoll一共有3个函数，epoll_create, epoll_ctl, epoll_wait，其中epoll_wait和poll函数类似，都是等到发生关心的事件时，才会返回，并且epoll对fd的数量也没有限制
  - 通过epoll_create返回一个fd，该fd指向内核缓冲区的一块区域，该区域就是fd list，并且可以通过epoll_ctl可以增加，删除或修改fd的属性，这就解决了上述的第一个问题
  - select和poll都通过遍历的方式，在运行时检测感兴趣的事件是否发生，复杂度O(N)；而epoll_ctl事先就将每一个fd感兴趣的事件注册到内核，设备就绪时，通过软件中断的方式，将就绪的fd放入就绪列表，所以epoll只需要在运行时判断一下就绪列表是否为空即可，无需多余轮询，复杂度O(1)，这就解决了上述的第二个问题
  - 同时，epoll还增加了边缘工作模式（select和poll都只支持水平工作模式），边缘工作模式用好之后更加高效
    - 在边缘工作模式下，假如epoll_wait返回了一个可读的文件描述符，但是程序并没有一次性读完，那么下次就不会返回该文件描述符，直至当该文件中内容发生变化（比如可读的内容增多），epoll_wait才会重新返回该fd，因此，边缘工作模式要求进程尽可能读或写（fd必须为非阻塞），如果没有尽可能地读或者写，那么下次将不会返回该fd。因此，ET在很大程度上减少了epoll事件被重复触发的次数
    - 在水平工作模式（LT）下，如果进程没有尽可能地读写，那么下次仍然会返回该fd，这导致一些无用的工作增加
- select和epoll的实现细节
  - select：每次进入select时，将当前进程放到监听的所有socket的阻塞队列中，当DMA将数据从网卡复制到任意一个被监听的socket缓冲区时，会触发中断，相应中断处理程序会将当前进程从所有被监听的socket队列中取出，将该进程设置为可运行状态。但是，当运行该进程时，仍然无法知道哪些socket就绪，因此仍然需要遍历一次监听的socket列表
  - 以上叙述了select的一般过程，进入select之后应该先遍历一遍，如果存在就绪socket，直接返回，无需阻塞
  - epoll：epoll维护了一个eventpoll内核对象，epoll_create用来创建该对象，epoll_ctl用来维护该对象中监视的socket列表，该socket列表使用红黑树组织。同时，eventpoll还维护了一个就绪队列，正是因为这个就绪队列，使得epoll被唤醒后无需遍历整个socket列表，和select类似，DMA完成数据复制之后，也会触发中断，该中断唤醒阻塞在eventpoll上的进程，并且将对应socket放到就绪队列双向链表中，epoll进程被唤醒后，直接拿出就绪队列处理一下就能返回对应的结果，无需遍历socket
    - 与select不同的是，epoll进程并不阻塞到socket上，而是阻塞到eventpoll对象上。同时，进入epoll之后，如果发现就绪队列不为空，直接返回即可，无需阻塞
- 参考
  - https://blog.csdn.net/lihao21/article/details/67631516
  - https://blog.csdn.net/fengxinlinux/article/details/75331567
  - https://blog.csdn.net/tennysonsky/article/details/45745887
  - 关于select和epoll具体的实现：https://www.cnblogs.com/wish123/p/11393383.html







- 零拷贝，参考：
  - https://www.ibm.com/developerworks/cn/java/j-zerocopy/
  - https://www.jianshu.com/p/fad3339e3448