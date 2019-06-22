## Java基础
#### 1. 面向对象是什么？
- 面向对象是一种编程思想，世间万物都可以看作对象，通过对类的抽取便可完成模块的设计，优势：
  - 代码开发模块化，更容易维护和修改。
  - 复用性比较强。
  - 增加代码可读性。
- 面向对象四大基本特征：
  - 抽象，提取现实世界中某事物的关键属性，将这些属性抽象为数据和行为。
  - 封装，可以使类具有更好的独立性和隔离性，只暴露给外部一些属性和操作，以保证类的高内聚。
  - 继承，对现有类的一种复用机制，在Java语言中包括类的继承和接口的实现。
  - 多态，在继承的基础上实现，多态的三个要素：继承、重写和父类引用指向子类对象，表现为调用相同的方法呈现出不同的行为。包括编译时多态和运行时多态。方法的重载和重写都是实现多态的方式，区别在于前者实现的是编译时的多态性，而后者实现的是运行时的多态性。重载发生在一个类中，同名的方法如果有不同的参数列表（参数类型不同、参数个数不同或者二者都不同）则视为重载；重写发生在子类与父类之间，重写要求子类被重写方法与父类被重写方法有相同的返回类型，比父类被重写方法更好访问，不能比父类被重写方法声明更多的异常（里氏代换原则）。重载对返回类型没有特殊的要求。
#### 2. 什么是Java虚拟机？为什么Java被称作是“平台无关的编程语言”？
- Java虚拟机是一个可以执行Java字节码的虚拟机进程。Java源文件被编译成能被Java虚拟机执行的字节码文件。
- 因为Java虚拟机的存在，Java源程序不需要在不同的平台单独重写或者重新编译。Java虚拟机负责与底层操作系统交互，对Java源程序屏蔽掉了底层的细节，因此Java编程语言本身是平台无关的，这也是Java语言的一大优势。
#### 3. JDK，JRE，JVM关系是什么？
- 简单说来，JDK包含JRE，JRE包含JVM
  - JVM，Java虚拟机，提供了字节码文件的运行环境支持，在运行时，是一个Java虚拟机进程，负责解释执行字节码。
  - JRE，Java运行环境，提供了Java应用程序运行时必要的软件环境，包含JVM和丰富的系统类库。
  - JDK，Java开发工具包，包括编译运行Java程序的开发工具以及JRE。开发工具比如：用于编译的javac，用于启动JVM运行Java程序的java命令，用于生成文档的javadoc命令以及用于打包的jar命令等。
#### 4. Java支持的数据类型有哪些？什么是自动拆装箱？
- 基本数据类型（整数默认int，浮点数默认double，float和double类型后面要加后缀）
  - 整数类型byte，short，int，long
  - 字符类型char
  - 浮点类型float，double
  - 布尔类型boolean
  - 引用数据类型，包括类，接口，数组等
  - 自动装箱和拆箱是基本类型和包装类型之间的转换，自动装箱常用于集合类存放基本数据类型。
#### 5. 值传递和引用传递？
- 值传递是对基本数据类型而言的，传递的是该变量本身的一个副本，在函数内部改变这个副本不会影响到变量本身。
- 引用传递是对引用类型的变量而言的，传递的是该对象地址的一个副本，而不是该对象本身或该对象本身的副本。
#### 6. Java支持多继承吗？
- Java中类只支持单继承，但是Java中接口支持多继承，接口的作用是用于扩展对象的功能，一个子接口继承多个父接口，说明子接口扩展了多个功能。
- 为什么Java不支持类的多继承？
  - Java设计者将简单视为Java语言的一大重要性质，相比C++而言，Java去掉了很多复杂并且容易使程序陷入陷阱的一些特性。多继承就是其中之一，如菱形继承问题。Java的设计者认为这种特性带来的问题多于它带来的方便，所以就不支持多继承。而事实上，不使用多继承仍然能很好的解决问题。
#### 7. 接口和抽象类的区别，abstract关键字。
- 从设计层面上讲，抽象类是对类属性和行为的抽象，可以认为是子类的模板，而接口是行为的抽象，是一种行为的规范。

|   | 抽象类 | 接口 |
|-|-|-|
| 默认的方法实现 | 可以有默认的方法实现 | 完全抽象，不能实现任何方法 |
| 实现 | 子类使用extends继承抽象类，如果子类不是抽象类的话，需要实现父类中所有的抽象方法 | 子类使用implements实现接口，需要提供接口中所有声明方法的实现 |
| 构造器 | 抽象类可以有构造器 | 不能有构造器 |
| 与正常Java类的区别 | 除了不能实例化之外，没有任何区别 | 接口是与Java类完全不同的类型，不能当作正常类来使用 |
| 访问修饰符 | public，private，default等 | 默认public，不能使用其他修饰符 |
| main方法 | 可以有main方法，并且可以运行 | 没有main方法，无法运行 |
| 多继承 | 继承单个类，实现多个接口 | 继承一个或多个其他接口 |
| 速度 | 它比接口速度快 | 接口速度稍微有些慢，因为它需要时间去寻找类中实现的方法 |
| 添加新方法 | 给抽象类中添加新方法之后，可以提供默认的实现，因此，在继承抽象类的子类无需修改 | 如果往接口中添加新方法，必须修改实现该接口的类 |
| 变量 | 各种类型均可 | 默认static final类型的 |

- 什么时候使用抽象类和接口？
  - 如果想拥有默认实现的方法，就是用接口；
  - 如果想实现类似多重继承的机制，就使用接口，因为Java类不支持多重继承；
  - 如果基本功能在不断改变，需要不断的向抽象类中添加方法，就使用抽象类。这种情况下使用接口的话需要大量改动已经实现这个接口的子类。
- 关于抽象类和抽象方法的限制如下：
  - 用abstract修饰的类为抽象类，修饰的方法为抽象方法
  - 抽象类中可以有0个或多个抽象方法
  - 有一个或以上抽象方法的类必须标记为抽象
  - abstract不能与final并列修饰一个类
  - abstract不能与private，static，final或native并列修饰同一个方法
- 抽象的（abstract）方法是否可同时是静态的（static），是否可同时是本地方法（native），是否可同时被synchronized修饰？
  - 都不能。抽象方法需要子类重写，而静态的方法是无法被重写的，因此二者是矛盾的。本地方法是由本地代码（如C代码）实现的方法，而抽象方法是没有实现的，也是矛盾的。synchronized和方法的实现细节有关，抽象方法不涉及实现细节，因此也是相互矛盾的。
#### 8. 单例模式（饱汉模式和饿汉模式）和工厂模式
```java
public class Singleton{
	private Singleton(){}
	private Singleton s = new Singleton();
	public Singleton getInstance(){
		return s;
	}
}//饱汉模式
public class Singleton{
	private Singleton(){}
	private Singleton s = null;
	public Singleton getInstance(){
		if(s == null){
			s = new Singleton();
		}
		return s;
	}
}//饿汉模式
```
```java
interface IFactory{
	public IProduct createProduct();
}
class Factory implements IFactory{
	public IProduct createProduct(){
		return new Product();
	}
}
class Client{
	public static void main(String[] args){
		IFactory factory = new Factory();
		IProduct product =factory.createProduct();
		product.method();
	}
}
```
#### 9. Error和Exception有什么区别？关于异常处理的一些细节。
- Error表示系统级的错误，编译错误或虚拟机错误，程序无法处理的错误，如内存溢出；
- Exception是需要程序捕捉和处理的异常，表示的是程序本身的设计和实现问题，通过写出运行正常的程序，Exception是可以避免的。
  - 运行时异常：
    - 表示程序运行时出现的错误状态，是运行时错误，只要程序逻辑设计的没有问题就不会发生。
    - NullPointerException
    - IndexOutOfBoundsException 
  - 受检异常：
    - 跟程序运行的上下文有关，即使程序逻辑设计没有错误，仍然有可能发生，如IO时访问文件，即使程序逻辑正确，仍有可能因文件不存在导致异常。
  - Java编译器要求必须声明和抛出可能发生的受检异常，并不要求必须声明和抛出运行时异常。
- try{}里有一个return语句，那么紧跟在这个try后的finally{}里的代码会不会被执行，什么时候被执行，在return前还是后？
  - 先执行finally字句，再return。实现方式是记录下返回值等到finally执行完毕再返回这个值。所以在finally中改变返回值是不好的，如果修改的话，就会返回一个修改的值。C#语言直接使用编译错误来阻止在finally中改变返回值，Java中可以通过提升编译器的检查级别来完成。
- Java语言使用throw,throws,try,catch,finally五个关键词来处理异常。
#### 10. 阐述final、finally、finalize的区别。
- final是一个修饰符，有三种用法。修饰类时，则该类不能被继承；修饰方法时也同样只能使用，不能被继承；修饰基本类型变量时，如果编译期确定，则为编译期常量，修饰引用变量时，则引用本身不能改变，引用指向的对象仍可以改变。
- finally，通常放在try…catch…的后面构造总是执行代码块，这就意味着程序无论正常执行还是发生异常，这里的代码只要JVM不关闭都能执行，可以将释放外部资源的代码写在finally块中。
- finalize，Object类中的方法，跟垃圾收集机制有关，在对象被清除之前，如果GC发现有必要执行对象的finalize()方法的话，则会执行这个方法。但是这个方法不推荐使用，如果想要清理资源的话使用finally就能做的很好了。至于为什么要设计这个方法，在《Java编程思想》中，作者提到设计这个方法是一种C++析构函数到Java语言的一种妥协，不推荐重写这个方法。
## Java字符串
#### 1. String和StringBuilder，StringBuffer区别？
- String是只读字符串，使用String引用的字符串内容不可修改。
- StringBuilder是Java 5中引入的，属于可变字符串，其用法与StringBuffer完全相同，只不过没有线程安全保证，因为各个函数都没有被synchronized修饰，因此效率更高。
#### 2. 字符串常量池与String.intern()方法。
- 关于字符串常量池，先看一下两句代码：
    ```java
    String s1 = new String("ab");//“ab”存在字符串常量池中，而堆中也有一个相同的对象，s1指向堆中的那个对象
    String s2 = "cd";//"cd"存在字符串常量池中，堆中无建立对象，s2指向常量池中的"cd"
    ```
- 关于String.intern()方法，Native方法，它的作用是：如果字符串常量池中已经包含了一个等于此String对象的字符串，则返回字符串常量池(运行时常量池)中这个字符串的String对象，如果不存在，则jdk1.6和jdk1.7将会有不同的行为，看如下代码：
    ```java
    String s1 = new StringBuilder("go").append("od").toString();
    System.out.println(s1.intern() == s1);
    String s2 = new StringBuilder("ja").append("va").toString();
    System.out.println(s2.intern() == s2);
    //jdk1.6输出 false false
    //jdk1.7输出 true false
    ```
  - 对以上不同结果的解释是：对于jdk1.6，如果常量池中没有相应字符串，则会复制一份原字符串对象，然后常量池中的引用指向这个新复制的字符串；而对于jdk1.7，当常量池中没有相应的字符串时，不会复制原字符串对象，只会创建一个新的字符串引用指向原来的字符串对象；
  - 对于jdk1.6的两个结果，不难理解，s1和s2都是指向堆中对象，而通过intern()方法获得的是常量池中的字符串引用，均指向s1和s2的拷贝对象；
  - 对于jdk1.7的结果，对于第一个true，比较容易理解，因为此时的intern()不会拷贝字符串，堆上的字符串引用变量和常量池中的字符串引用变量指向的都是同一个地址；而第二个false是因为"java"这个字符串时常驻常量池中的，二者的字符串引用变量指向的不是同一个位置，"java"这个字符串在System类中，而java虚拟机会自动调用System这个类。
    ```java
    //在System类中的注释可以知道，调用了initializeSystemClass方法，在此方法中调用了Version对象的init静态方法
    //sun.misc.Version.init();
    //因此sun.misc.Version类会在JDK类库的初始化过程中被加载并初始化。
    //查看Version类定义的私有静态字符串常量如下：
    private static final String launcher_name = "java";
    private static final String java_version = "1.7.0_51";
    private static final String java_runtime_name = "Java(TM) SE Runtime Environment";
    private static final String java_runtime_version = "1.7.0_51-b13";
    //在初始化Version类时，对其静态常量字段根据指定的常量值做默认初始化，所以"java"被加载到了字符串常量池中，修改上面代码使字符串值为上面常量中的任意一个都会返回false。
    String str2=new StringBuilder("1.7.0").append("_51").toString();
    System.out.println(str2.intern()==str2);
    //输出false
    ```
## Java集合框架
#### 1. Java说出一些集合框架的优点？
- 使用核心集合类降低开发成本，而非实现我们自己的集合类。
- 随着使用经过严格测试的集合框架类，代码质量会得到提高。
- 通过使用JDK附带的集合类，可以降低代码维护成本。
- 复用性和可操作性。
#### 2. 集合框架中的泛型有什么优点？泛型和C++模板的对比。
- 泛型的优点：
  - 提供编译期类型检查，泛型允许程序员为集合提供一个可以容纳的对象类型，有效的避免了运行时期的ClassCastException；
  - 泛型使代码整洁，不需要显式的类型转换和instanceOf操作；
  - 给运行时带来好处，不会产生类型检查的字节码指令；
- Java泛型和C++模板对比：
  - 尽管二者表现形式相似，但底层实现机制却大相径庭。Java泛型只是编译时期的“语法糖”，编译之后指定的泛型类型便被消除，提供了一种编译期检测机制；而C++模板却截然不同，编译器会针对每种模板类型创建一份模板代码的副本，如MyClass\<A\>和MyClass\<B\>不会共享静态变量；
  - C++模板可以使用int等基本数据类型，而Java中的泛型擦除机制使得Java集合类中不能使用基本数据类型；
  - C++中类型模板可以实例化，Java不支持；
  - 在Java中，无论类型参数是什么，泛型类的实例都是同一个类型，而C++中参数类型不同，实例类型也不同。
#### 3. 为何Map接口不继承Collection接口？
- 集合提供的是“一组对象”的概念，而Map提供的是一组对象到另一组对象映射的概念，二者在概念上就不相同。
- Map提供了抽取key集合和value列表集合的方法，这并不适用于“一组对象”的规范。
#### 4. 什么是迭代器(Iterator)？
- 迭代器是一个接口，提供了对集合元素进行迭代的方法，同时也可以在迭代的过程中删除元素。
#### 5. Iterator和ListIterator的区别是什么？
- Iterator可用来遍历Set和List集合，但是ListIterator只能用来遍历List；
- Iterator对集合只能是前向遍历，ListIterator既可以前向也可以后向；
- ListIterator实现了Iterator接口，并包含了其他功能，如增加元素，替换元素	，获取前一个和后一个的元素索引等。
#### 6. fail-fast和fail-safe的区别与联系？
- fail-fast机制，即快速失败机制，是java集合(Collection)中的一种错误检测机制，作用于使用迭代器对集合进行迭代的时期。当在迭代集合的过程中该集合在结构上发生改变的时候，就有可能会发生fail-fast，即抛出ConcurrentModificationException异常。fail-fast机制并不保证在不同步的修改下一定会抛出异常，它只是尽最大努力去抛出，所以这种机制一般仅用于检测bug。该机制会抛出异常，在java.util包中的集合使用该机制。具体实现机制为，在集合类中存在一个检测集合结构变化的变量modCount，每当集合结构变化时，该变量会修改。而在迭代器中也维护了一个exceptedModCount变量，起初与modCount相等，每当调用next()或remove()方法是，都会检测这两个变量是否相等，如果不相等，抛出异常，即发生fail-fast。但为什么使用迭代器调用remove()改变集合结构不会触发呢？是因为迭代器本身调用remove()的时候，会将两个变量重新设置为相等，因此当对集合同时迭代和修改时，阿里巴巴规范中明确规定到要用迭代器，而不是使用一般的for循环；
- fail-safe机制，安全失败，采用安全失败机制的集合容器，在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历。所以在遍历的过程中其他线程对集合的修改不会被检测到，不会触发异常。优点是不会触发异常，缺点是需要拷贝很多无用的对象，且迭代器开始遍历的那一刻拿到的是集合拷贝，在遍历期间发生的改变是无从知晓的。java.util.concurrent包下的容器都采用安全失败，可以用于多线程并发访问，而fail-fast机制则抑制了java.util包中集合的多线程并发访问。
#### 7. ArrayList的问题。
- ArrayList和Vector的异同
  - 相同点
    - 二者都是基于索引的，内部由数组实现；
    - 二者都是有序的，可以维护元素插入的顺序；
    - 二者都实现fail-fast机制；
    - 都允许null值，可以使用索引值进行随机访问。
  - 不同点
    - Vector同步，而ArrayList不是，但是如果在多线程情况下，目前推荐使用CopyOnWriteArrayList。
  - Array和ArrayList区别
    - Array可以容纳基本类型和引用类型，ArrayList只能容纳引用类型；
    - Array大小固定，ArrayList可扩展。
#### 8. HashMap的工作原理是什么？HashMap的一些常见问题。
- HashMap工作原理：
  - HashMap通过结合数组与链表的优点进行设计，是一个链表数组，基于hash的原理，使用put(k, v)将对象存储，使用get(k)获取对象。当调用这两个方法时，都会先根据key的hashCode()计算该key所在的bucket，然后进行搜索；
- 如果两个key的hashCode相同，如何获取值对象？
  - 当调用get()方法时，先根据key的hashCode()获得bucket位置，然后根据key.equals()方法找到正确的节点，最终找到要找的值；
- hashCode()方法和equals()方法的重要性
  - HashMap中使用hashCode()方法找到bucket位置，然后通过equals()方法找到具体的对象。如果这两个方法没有被正确的实现，则不同的对象可能会产生相同的hashCode()和equals()输出，HashMap会认为他们是相同的，并且在把不同的对象覆盖掉。因此，两个方法应该遵循以下规则：
  - 如果o1.equals(o2)，那么o1.hashCode() == o2.hashCode()总是为true的；
  - 如果o1.hashCode() == o2.hashCode()，并不意味着o1.equals(o2)会为true。
- 为什么重写equals就要重写hashCode()
  - 如果不重写hashCode()，hashCode()默认返回结果为根据对象的内存地址换算出来的一个值。重写了equals()而不重写hashCode()可能会导致结果不符合上述的第一个规则，那么在使用基于hash的数据结构时会产生错误。
- HashMap和HashTable的区别
  - HashMap非线程安全，HashTable线程安全；
  - HashMap允许null的key和value出现，而HashTable不行；
  - HashMap效率比HashTable高；
  - HashTable线程安全，适合于多线程环境。然而，这个类已经不建议使用，其内部没有过多的优化，在多线程情况下使用ConcurrentHashMap来替代。
- HashMap和TreeMap的选择
  - 不考虑有序性问题的话，HashMap效率更高；
  - 如果需要有序的key集合，则使用TreeMap。
#### 9. List的三种遍历方式对比。
- 传统的for循环遍历，基于计数器。
  - 对于顺序存储的ArrayList，每次遍历平均时间复杂度为O(1)，遍历整个集合的平均时间复杂度为O(n)。
  - 对于链式存储的LinkedList，使用索引方式遍历的话，每次遍历复杂度为O(n)，遍历整个集合的平均时间复杂度为O(n^2)。
- 使用Iterator迭代器遍历
  - 对于顺序存储来说，使用迭代器遍历并不会提高性能，但是方便在循环中删除多个元素。
  - 对于链式存储来说，使用迭代器可以使遍历整个集合的时间复杂度降为O(n)，因为使用迭代器遍历时会记录当前遍历的状态。
- foreach循环遍历。
  - foreach实质上也是迭代器遍历方式，是编译器提供的一个语法糖，语法上更简洁。但是不能很好的在遍历时删除多个元素。因为使用这种方式遍历时，遍历使用迭代器，而删除并没有使用迭代器，会引发fail-fast机制。
- 总之，针对链式存储来说，使用迭代器遍历会提高性能。而当想要在遍历时删除多个元素的话，就要显式的使用迭代器了。
- 在Java集合框架中，提供了一个RandomAccess接口，但该接口中并没有任何的方法，通常该接口被用作标记，标记该List是否支持随机访问。比如支持随机访问的ArrayList便实现了这个接口，而LinkedList并没有实现。实现这个接口典型的意义是：
    ```java
    if (list instanceof RandomAccess) {
        //使用传统的for循环遍历。
    } else {
        //使用Iterator或者foreach。
    }
    ```
## Java虚拟机
