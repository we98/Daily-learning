## 第1章 初识Redis

### 1.1 盛赞Redis

- Redis是一个基于键值对的NoSQL数据库，值有以下几种
  - string
  - hash
  - list
  - set
  - zset
  - Bitmaps
  - HyperLogLog
  - GEO
- Redis基于内存，读写性能非常好
- Redis提供了数据持久化，通过快照和日志两种方式进行
- Redis还提供了一些附加功能
  - 键过期
  - 发布订阅
  - 事务
  - 流水线
  - Lua脚本

### 1.2 Redis特性

- 速度快
  - 官方给出的读写性能是10万/秒
  - 速度快的原因
    - 数据存放于内存
    - C语言实现
    - 单线程架构，预防了多线程的竞争问题
    - Redis源代码精打细磨
- 基于键值对
  - 主要提供了5种数据结构
  - 在字符串的基础之上演变出了位图和HyperLogLog
- 丰富的功能
  - 键过期
  - 发布订阅，可以实现消息系统
  - 支持Lua脚本，可以用Lua创造出新的Redis命令
  - 简单的事务
  - 流水线，客户端可以将一组命令一次性传到Redis，减少了网络开销
- 简单稳定
  - 早期版本代码量只有2万行左右，3.0之后由于添加集群特性，增至5万行左右
  - 单线程模型，不仅使服务端处理模型变得简单，也使得客户端开发变得简单
  - Redis不需要依赖操作系统中的类库，例如Memcache需要依赖libevent这样的系统类库，Redis自己实现了事件处理的相关功能
- 客户端语言多
  - 提供了简单的tcp通信协议，多种编程语言可以很方便的接入
- 持久化
  - 两种持久化方式，RDB和AOF用来将内存数据保存到硬盘中
- 主从复制
  - Redis提供了复制功能，实现了具有多个相同数据的Redis副本，复制功能是分布式的基础
- 高可用和分布式
  - Redis2.8提供了实现高可用的Redis哨兵，能保证Redis结点的故障发现和故障自动转移
  - Redis3.0提供了分布式实现Redis Cluster，它是Redis分布式的真正实现，提供了高可用，读写和容量的可扩展

### 1.3 Redis使用场景

#### 1.3.1 Redis可以做什么

- 缓存，并且可以灵活的控制最大内存和淘汰策略
- 排行榜系统，通过列表和有序集合，可以很方便的构建各种排行榜系统
- 计数器应用，播放书，浏览数等，Redis支持天然计数功能
- 社交网络，粉丝，共同好友，推送，下拉刷新等
- 消息队列，消息队列是一个大型网站的必备组件，Redis提供了发布订阅和阻塞队列的功能，虽然和专业的消息队列相比不够强大，但是一般的消息队列功能可以满足

### 1.3.2 Redis不可以做什么

- 从数据规模来看，如果用Redis存储十分大规模的数据，经济成本相当高
- 从数据冷热的角度来看，将冷数据放到Redis中，是对内存的一种浪费

### 1.5 正确安装并启动Redis

#### 1.5.2 配置，启动，操作，关闭Redis

- 启动Redis服务
  - 默认配置，直接通过redis-server启动
  - 运行配置，通过redis-server --port 6380
  - 配置文件启动，如redis-server /opt/redis/redis.conf，**推荐使用这种方式，并且Redis目录下还有一个redis.conf配置文件，可以作为模板**

- 启动Redis客户端
  - redis-cli
- 停止Redis服务
  - redis-cli shutdown
    - 过程是断开连接，并生成持久化文件，是一种优雅的方式
    - 不要通过kill-9直接杀死进程，这样不会做持久化操作
    - 还有一个参数，代表关闭Redis之前，是否生成持久化文件redis-cli shutdown nosave|save

## 第2章 API的理解和使用

### 2.1 预备

#### 2.1.1 全局命令

- 查看所有键 keys *，打印出所有键，复杂度O(N)，当保存了大量键时，线上环境**禁止使用**
- 输出键总数dbsize，不会遍历，直接获取Redis内置的变量，复杂度O(1)
- 查看键类型type

#### 2.1.2 数据结构和内部编码

- string等5中数据结构只是客户端感知的数据结构，实际上，每种数据结构在底层都有内部编码实现，而且是多种实现，这样Redis会在合适的场景选择合适的内部编码，如：![image-20200518181045166](images/Redis内部数据结构.png)

- 通过object encoding key可以查看某个key具体的实现方式

- Redis这样设计有两个好处：
  - 用户对具体实现无感知，这样一旦开发出更优秀的实现方式，无需改动外部的数据结构和命令
  - 多种实现方式可以在不同的场景下发挥不同的优势，同时，在场景出现变化时，Redis可以改变具体实现的方式

#### 2.1.3 单线程架构

- Redis使用了单线程架构和IO多路复用模型来实现高性能的内存数据库服务
  - Redis使用单线程来处理命令，一条命令到达后并不会立刻执行，而是会进入到队列中，逐条执行，所以可以确定的是，两条命令不可能同时执行，所以不会产生并发问题
- 为什么单线程还能那么快
  - 纯内存操作，内存的响应时间大概为100ns，这是Redis达到每秒万级别访问的基础
  - 非阻塞IO，Redis使用epoll，不会在网络IO上浪费过多的时间
  - 单线程避免了线程切换和竞态产生的消耗
- 同时，使用单线程结构还可以简化服务端的开发，并且对于服务端来说，锁和线程切换通常是性能杀手
- 但是单线程会有一个问题，如果某个命令执行时间过长，会造成其他命令的阻塞，对于Redis这种高性能数据库是致命的，因此Redis是面向快速执行场景的数据库

### 2.2 字符串

- Redis中最基础的结构，其他几种结构都是在字符串的基础上构建的，字符串可以是以下几种类型，**但大小不能超过512MB**
  - 字符串
  - 数字（整数，浮点）
  - 二进制（图片，音频）

#### 2.2.1 命令

- 常用命令
  - set key value [ex seconds] [px milliseconds] [nx|xx]
    - ex代表秒，px代表微秒
    - nx，键不存在时成功，用于添加，xx存在时成功，用于更新
  - setex key seconds value
    - 用于给一个键同时设置过期时间和value
    - 相当于set key value， expire key seconds
  - setnx key value
    - 只有当key不存在时，才会创建这个key，并设置value，否则返回失败
    - 可以作为分布式锁的一种实现方案：https://redis.io/topics/distlock
  - get key
    - 获取key，不存在返回nil
  - mset k1 v1 k2 v2
    - 批量设置值
    - 可以有效提高开发效率
    - 但注意每次批量发送的数据不是无节制的，数量过多容易造成Redis阻塞或网络拥塞
  - incr key
    - 增加计数
    - 不是整数，返回错误；key不存在，按0处理，返回1
    - 很多语言为了实现线程安全的计数使用了CAS，但是Redis完全不存在这个问题

#### 2.2.2 内部编码实现

- 字符串的内部编码有3种
  - int：8个字节的长整型
  - embstr：小于等于39字节的字符串
  - raw：大于39个字节的字符串

#### 2.2.3 典型使用场景

- 缓存
  - Redis作为缓存层，MySQL作为存储层
  - Redis键名规范：业务名:对象名: id:属性，如数据库名为vs，表名为user，id为1，那么命名为vs:user:1:id
- 计数
  - 视频播放量等场景
  - 实际上一个真实的计数系统要考虑的问题很多，比如防作弊，按照不同维度计数，数据持久化等
- 共享session
  - 问题：一个分布式的web服务将session保存到各自的服务器中，会存在两个问题：如果负载均衡以请求作为维度，那么相同用户的不同请求会被打到不同服务器中，造成每次刷新都要登录；如果某一个服务器挂掉，那么这个服务器上保存的所有的用户登录信息将会丢失，用户不得已重新登陆。问题如下图：![image-20200519133135257](images/分布式session问题.png)
  - 解决方案是将所有的session保存到一个高可用的Redis集群中，服务器每次收到请求都要先去Redis集群中取session![image-20200519133639358](images/分布式session解决方案.png)
  - 限制频率
    - 比如需求是一个网站一秒内限制IP的访问次数，或验证码一分钟内不能超过几次

### 2.3 哈希

#### 2.3.1 命令

- hset key field value设置值
- hget key field
- hdel key field1 field2
- hlen key返回field数量
- hmget key field1 field2批量获取值
- hkeys key获取所有fields
- hvals key获取所有value
- hgetall key获取素有field-value
  - 注意如果field比较多，会有阻塞Redis的可能，如果只需要取部分field，使用hmget，如果一定要获取所有的field-value，就使用hscan命令

#### 2.3.2 内部编码

- 哈希的内部编码有两种：
  - ziplist（压缩列表）：当哈希元素个数小于hash-max-ziplist-entries（默认512），同时所有值都小于hash-max-ziplist-value（默认64字节），Redis会使用ziplist作为其内部实现，ziplist会更加紧凑，更节省内存
  - hashtable（哈希表）：当无法满足ziplist的要求时，Redis使用hashtable作为哈希的内部实现，因为此时ziplist的读写效率会下降，hashtable读取效率为O(1)

#### 2.3.3 使用场景

- 用来映射关系型数据库的行
- 有三种方式可以用来映射数据库的行
  - 原生字符串类型 set user:1:name tom; set user:1:age 18; set user:1:city beijing
    - 优点：直观，每个属性支持更新
    - 缺点：占用过多的键，用户信息内聚性较差，**一般不会在生产环境中使用**
  - 序列化字符串类型：set user:1 "{name:tom, age:18, city:beijing}"
    - 优点：简化编程，相比第一种可以提高内存的利用效率
    - 缺点：序列化和反序列化有开销，同时，更新一个属性就要全部序列化
  - 哈希类型
    - 优点：简单直观，也可以减少内存空间的使用
    - 缺点：当转换为hashtable时，会占用更多内存

### 2.4 列表

- 一个列表中最多含有2^32-1个元素，支持两端插入（push）和弹出（pop），还可以获取指定范围元素列表，指定索引下标的元素等。比较灵活，可以充当栈和队列，在实际中应用比较广泛
- 列表中常用的命令：![image-20200519154409190](images/redis list命令)- 

- 添加操作
  - lpush key v1 v2，往左边插入元素
  - rpush key v1 v2，往右边插入元素
  - linsert key before|after pivot value，如linsert age before 20 19在20之前插入19
- 查找
  - lrange key start end
    - 从左到右是0~N-1，从右到左是-1~-N
    - 包含end
- 删除
  - lpop key，从左侧删一个元素
  - lrem key count value
    - count>0，从左到右删最多count个等于value的元素
    - count<0，从右到左删除最多-count个元素
    - count=0，删除所有
  - ltrim key start end，删除start~end下标的元素
- 修改
  - lset key index newvalue
- 阻塞操作
  - blpop key1 [key2 ...] timeout
  - brpop key1 [key2 ...] timeout
  - 如果timeout=0，会一直阻塞，从左到右有一个key出现元素，就返回

#### 2.4.2 内部编码

- 仍是两种：
  - 和哈希一样，满足一定条件使用ziplist
  - 无法满足条件时，用linkedlist
- Redis3.2提供了quicklist，简单来说，它是以一个ziplist为结点的linkedlist，结合了二者的优势

#### 2.4.3 使用场景

- 消息队列，使用lpush+brpop的组合
- 文章列表
  - 用于分页展示某列表时，因为list不仅是有序的，也支持按照索引范围获取元素
- 实际上的开发场景比较多，可以参考以下：
  - lpush+lpop=栈
  - lpush+rpop=队列
  - lpush+ltrim=有限集合
  - lpush+brpop=消息队列

### 2.5 集合

- 元素不重复，无序，一个集合最多支持2^32-1个元素，且除了增删改查之外，还提供了交集，并集，差集等等，合理的使用能解决很多实际问题

#### 2.5.1 命令

- sadd key element1 [element2 element3]
- srem key element1 [element2 element3]
- scard key计算个数，并不会遍历，复杂度O(1)
- sismember key element
- srandmember key count，随机返回count个元素
- spop key，随机弹出元素
- smembers key，获取所有元素
  - 注意，smembers，lrange和hgetall都是比较重的命令，元素过多存在阻塞Redis的可能，这时候可以用sscan来完成

#### 2.5.2 内部编码

- intset（整数集合）：当集合中的元素都是整数，并且个数少于set-maxintset-entries（默认512个），Redis会选用intset，从而减少内存使用
- hashtable：不满足intset要求时，使用hashtable

#### 2.5.3 使用场景

- 比较典型的使用场景是标签，比如一个用户对娱乐，体育比较感兴趣，另一个对历史，新闻比较感兴趣，这些兴趣点就是标签
  - 给用户添加标签sadd user:1:tags tag1 tag3; sadd user:2:tags tag2 tag3
  - 给标签添加用户sadd tag:1:users user1; sadd tag:2:users user2; sadd tag:3:users user1 user2
  - 计算用户共同感兴趣的标签sinter...
  - 注意，用户和标签的关系的维护应该在一个事务内执行，防止部分命令失败造成的数据不一致
- 应用场景通常有以下几种：
  - sadd=标签
  - spop/srandmember=生成随机数，如抽奖
  - sadd+sinter=社交需求

### 2.6 有序集合

- 有序结合和集合的不同点是，每一个元素都有一个分数，可以根据分数对元素进行排序，元素不能重复，但分数可以重复

#### 2.6.1 命令

- zadd key score1 member1 [score2 member2 ...]，添加成员，复杂度为logN，而sadd复杂度为1
- zcard key，计算个数
- zscore key member，计算某个成员的分数
- zrange key start end [withscore]，按照start和end返回对应排名，如果加上选项，也会返回分数
- zrangebyscore key min max [withscore] [limit offset count]
  - 返回分数区间为min到max的成员

#### 2.6.2 内部编码

- ziplist：个数小于zset-max-ziplistentries（默认128个），同时每个元素大小小于zset-max-ziplist-value（默认64字节），使用ziplist
- skiplist，不满足条件时，使用跳表

#### 2.6.3 使用场景

- 榜单，以每个视频获赞数为例
  - 添加视频获赞zadd video:zan video1 3，zincrby video:zan video1 1
  - 取消视频赞（作弊等原因移除榜单）zrem video:zan video1
  - 展示获赞最多的十个视频zrevrangebyrank video:zan 0 9
  - 查询用户排名和获赞zscore video:zan video1; zrank video:zan video1

### 2.7 键管理

#### 2.7.2 遍历键

- 全量遍历键 keys pattern，keys *代表全部，pattern属于glob风格
- 渐进式遍历。从2.8之后，提供了新命令scan，采用渐进式遍历的方式解决keys可能带来的阻塞问题，每次scan复杂度为O(1)，但要实现keys的功能，需要多次scan，简化模型如下：![image-20200519165227205](images/scan模型.png)
  - 每次执行scan，可以想象只扫描字典里的一部分键，直至将所有键遍历完毕，用法是scan cursor [match pattern] [count number]
  - cursor是必须参数，实际上cursor是一个游标，第一次遍历从0开始，每次scan遍历完hou会返回当前游标的值，直至游标值为0，表示遍历结束。因此使用scan的方式是在循环里每次使用scan，直至返回值为0，说明遍历完毕
  - match pattern做模式匹配，这一点和keys很像
  - count number表示每次要遍历的键个数，默认是10，可以适当增大
  - 除了scan之外，Redis还提供了hscan sscan zscan用来简化对应结构的遍历
  - 渐进式遍历可以有效解决keys命令可能产生的阻塞问题，但是如果在遍历过程中出现了键的变化，那么可能出现新增的键没有遍历到，遍历出了重复的键等问题

#### 2.7.3 数据库管理

- 切换数据库select index，index作为索引，Redis中默认配置了16个数据库，这16个数据库之间的数据没有任何关联
- 那么能不能向测试数据库和正式数据库一样，把正式的数据放入0号，测试的数据库放入1号呢？事实上，不好。Redis3.0已经弱化了这个功能，比如Redis Cluster只允许使用0号数据库，为什么要废弃掉这个“优秀”的功能呢，有以下原因
  - Redis单线程，多个数据库使用一个CPU，彼此之间还是会受影响
  - 如果一个慢查询存在，依然会影响其他数据库
  - 部分Redis客户端根本不支持这种方式
- 如果要使用多个数据库功能，建议在一台机器上部署多个Redis实例，这样业务之间不会受影响，又合理的使用了CPU资源