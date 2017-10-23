7、Collections是Collection接口的实现类，里面有很多方法，ArrayList 和linckedList等本身没有
排序、倒置、查找等实现方法，是通过Collections类来实现的，里面有很多public static方法
可直接对Collection接口的实现类进行操作，可以查找API文档
5、toString()方法，执行System.out.println(aa);等价于System.out.println(aa.toString());
由于Java的自动装箱，所有添加到容器中的都是类对象，输出的是该类对象调用过toString()方法后的结果
自己定义的类对象，要添加到容器中，建议重写类的toString()方法,否则输出的是
该对象的引用，类名@.....的形式，因此要要按我们想要输出的结果重写toString()方法
3、java中设计了三种接口来实现三种集合，Set、List、Map，集合、列表、映射
容器的框架图,位于java.util包中
							   Collection <interface>					Map <interface>
									|										|
				Set <interface>   	   	  List <interface>			   HashMap
					  |			                  |
				   HashSet	            LinkedList  ArrayList    //都是上面接口的实现类类对象  
接口中定义属性必须是public static final类型的，定义方法必须是public abstract类型的
这些符号可以部分或者全部省略,便决定了接口是一个抽象类
4、interface it3 extends it1,it2
class B extends A implemrents it1  都是允许的
17、
重写equals方法的先判断调用equals方法的引用和该方法的形参是否是同一类型，是-->堆空间里面的内容肯定相等
不是-->判断两个引用是否是同种类型，是---->判断里面的内容是否相等，不是---->引用不是同种类型，没有比较的必要
基本数据类型 == 比较的是值是否相等
引用数据类型中 == 是比较双等号两端是否指向的是堆内存中的同一块儿存储空间，
是否是堆内存中的同一个对象，引用就相当于地址，地址是用来指向的是其他变量的
指针变量也是一种变量，他存储的是其他变量的地址(该指针所指向变量的地址)
指针变量本身也有地址
*/
。

<important>
C语言中的结构体，是用基本数据类型构造的数据类型，相当于Java中的类   数据类型
C语言中如何实现传进所有类型的数据呢？用空指针，所有类型都有其地址，可以将不同数据类型的地址赋予空指针 (void * pval)
Java中用泛型的概念来实现<T>
2、list()方法的形参需要一个实现了FilenameFilter接口的类的类对象,使该方法具有了文件过滤的功能
listFiles()方法的形参需要一个实现了FileFilter接口的类的类对象
传递给Thread类构造方法的形参是实现了Runnable接口的类的类对象
实现了某个接口的类具有接口的相应地功能，接口的实现类类对象

<论方法的参数>某个方法的形参或者某个类构造方法的形参需要某个类(数据类型)的类对象
或者需要某个抽象类的实现类的类对象，或者某个接口的实现类类对象
那么生成这样一个类继承某个抽象类，或者实现某个接口，重写里面的抽象方法，生成该类的对象
将该对象传递给方法的形参即可





