## Java基础
1. 面向对象是什么？
    - 面向对象是一种编程思想，世间万物都可以看作对象，通过对类的抽取便可完成模块的设计，优势：
      - 代码开发模块化，更容易维护和修改
      - 复用性比较强
      - 增加代码可读性
    - 面向对象四大基本特征：
      - 抽象，提取现实世界中某事物的关键属性，将这些属性抽象为数据和行为
      - 封装，可以使类具有更好的独立性和隔离性，只暴露给外部一些属性和操作，以保证类的高内聚
      - 继承，对现有类的一种复用机制，在Java语言中包括类的继承和接口的实现
      - 多态，在继承的基础上实现，多态的三个要素：继承、重写和父类引用指向子类对象，表现为调用相同的方法呈现出不同的行为。包括编译时多态和运行时多态。
***
2. 什么是Java虚拟机？为什么Java被称作是“平台无关的编程语言”？
    - Java虚拟机是一个可以执行Java字节码的虚拟机进程。Java源文件被编译成能被Java虚拟机执行的字节码文件。
    - 因为Java虚拟机的存在，Java源程序不需要在不同的平台单独重写或者重新编译。Java虚拟机负责与底层操作系统交互，对Java源程序屏蔽掉了底层的细节，因此Java编程语言本身是平台无关的，这也是Java语言的一大优势。
***
3. JDK，JRE，JVM关系是什么？
    - 简单说来，JDK包含JRE，JRE包含JVM
      - JVM，Java虚拟机，提供了字节码文件的运行环境支持，在运行时，是一个Java虚拟机进程，负责解释执行字节码。
      - JRE，Java运行环境，提供了Java应用程序运行时必要的软件环境，包含JVM和丰富的系统类库。
      - JDK，Java开发工具包，包括编译运行Java程序的开发工具以及JRE。开发工具比如：用于编译的javac，用于启动JVM运行Java程序的java命令，用于生成文档的javadoc命令以及用于打包的jar命令等。
***
4. Java支持的数据类型有哪些？什么是自动拆装箱？
    - 基本数据类型（整数默认int，浮点数默认double，float和double类型后面要加后缀）
      - 整数类型byte，short，int，long
      - 字符类型char
      - 浮点类型float，double
      - 布尔类型boolean
    - 引用数据类型，包括类，接口，数组等
    - 自动装箱和拆箱是基本类型和包装类型之间的转换，自动装箱常用于集合类存放基本数据类型。
***
5. 值传递和引用传递？
    - 值传递是对基本数据类型而言的，传递的是该变量本身的一个副本，在函数内部改变这个副本不会影响到变量本身。
    - 引用传递是对引用类型的变量而言的，传递的是该对象地址的一个副本，而不是该对象本身或该对象本身的副本。
***
6. Java支持多继承吗？
    - Java中类只支持单继承，但是Java中接口支持多继承，接口的作用是用于扩展对象的功能，一个子接口继承多个父接口，说明子接口扩展了多个功能。
    - 为什么Java不支持类的多继承？
      - Java设计者将简单视为Java语言的一大重要性质，相比C++而言，Java去掉了很多复杂并且容易使程序陷入陷阱的一些特性。多继承就是其中之一，如菱形继承问题。Java的设计者认为这种特性带来的问题多于它带来的方便，所以就不支持多继承。而事实上，不使用多继承仍然能很好的解决问题。
***
7. 接口和抽象类的区别
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
> - 什么时候使用抽象类和接口？
    - 如果想拥有默认实现的方法，就是用接口；
    - 如果想实现类似多重继承的机制，就使用接口，因为Java类不支持多重继承；
    - 如果基本功能在不断改变，需要不断的向抽象类中添加方法，就使用抽象类。这种情况下使用接口的话需要大量改动已经实现这个接口的子类。
> - 关于抽象类和抽象方法的限制如下：
    - 用abstract修饰的类为抽象类，修饰的方法为抽象方法
    - 抽象类中可以有0个或多个抽象方法
    - 有一个或以上抽象方法的类必须标记为抽象
    - abstract不能与final并列修饰一个类
    - abstract不能与private，static，final或native并列修饰同一个方法
***
8. 单例模式（饱汉模式和饿汉模式）和工厂模式
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
## Java字符串
1. String和StringBuilder，StringBuffer区别？
    - String是只读字符串，使用String引用的字符串内容不可修改；
    - StringBuilder是Java 5中引入的，属于可变字符串，其用法与StringBuffer完全相同，只不过没有线程安全保证，因为各个函数都没有被synchronized修饰，因此效率更高。
