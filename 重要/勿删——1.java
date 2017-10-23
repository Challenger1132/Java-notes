/*
1、JRE Java Runtime Environment
java是用的是Unicode字符集，每一个字符都有唯一的编码，因此在Java中一个字符变量可以存储一个汉字
在Java中一个中文字符和一个英文字符占据相同的空间

2、list()方法的形参需要一个实现了FilenameFilter接口的类的类对象,使该方法具有了文件过滤的功能
listFiles()方法的形参需要一个实现了FileFilter接口的类的类对象
传递给Thread类构造方法的形参是实现了Runnable接口的类的类对象
实现了某个接口的类具有接口的相应地功能，接口的实现类类对象

<论方法的参数>某个方法的形参或者某个类构造方法的形参需要某个类(数据类型)的类对象
或者需要某个抽象类的实现类的类对象，或者某个接口的实现类类对象
那么生成这样一个类继承某个抽象类，或者实现某个接口，重写里面的抽象方法，生成该类的对象
将该对象传递给方法的形参即可
3、
class Win implements ActionListener
{
	public void winCreat()
	{
		Button bn = new Button("display");
		bn.addActionListener(this); 
	}
	public void actionPerformed(ActionEvent e)   //Win类实现了ActionListener接口，要重写里面的actionPerformed()方法
	{
	}
}
//内部类的形式
class Win
{
	public void winCreat()
	{
		Button bn = new Button("display");
		Monitor mm = new Monitor();
		bn.addActionListener(mm);   
	}
	class Monitor extends ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		}
	}
}
//外部类的形式
class Win
{
	public void winCreat()
	{
		Button bn = new Button("display");
		Monitor mm = new Monitor();
		bn.addActionListener(mm);   
	}
}
class Monitor extends ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
	}
}

创建一个Win类在该类winCreat()方法中,Button类的类对象bn调用addActionListener()方法
bn.addActionListener();该方法的形参是一个监听器类的类对象，该监听类可以以内部类的方式
创建在Win类的内部，还可以创建在Win类的外部，这个监听类要继承某个抽象类或者实现某个接口
总之addActionListener()方法的形参是一个监听类的类对象
还可以不写方法，直接写public void actionPerformed(ActionEvent e)方法，但是该方法所在的类
要继承某个抽象类或者实现某个接口，这时addActionListener()方法的形参是this
4、String类的构造方法     String(byte[] bytes) 字节数组
String(byte[] bytes, int offset, int length) 
将字节数组转化为字符串
5、迭代器Iterator接口
方法摘要 
 boolean hasNext()   返回值是布尔类型
如果仍有元素可以迭代，则返回true。判断当前游标后面是否有下一个元素，存在返回true，否则返回false 
 E next()   函数返回值是E
先返回当前游标下面的右面的元素，然后将游标后移一个位置
 void remove() 不推荐使用
6、容器
	1、Integer类在对象中包装了一个基本类型int的值，Integer类型的对象包含一个int类型的字段。 
	此外，该类提供了多个方法，能在int类型和String类型之间互相转换，还提供了处理int类型时非常有用的其他一些常量和方法
	2、所有容器里面放的是对象，不能存放一个孤立的值，如果放的是孤立的值
	会自动转化为对该孤立值对应的对象，写的是整型数据1234，但放入进去的是一个Integer对象
	写的是double类型数据88.88，但放入进去的是一个Double类对象，会自动封装成对象
	3、java中设计了三种接口来实现三种集合，Set、List、Map，集合、列表、映射
	容器的框架图,位于java.util包中
								   Collection <interface>					Map <interface>
										|										|
					Set <interface>   	   	  List <interface>			   HashMap
						  |			                  |
					   HashSet	            LinkedList  ArrayList    //都是上面接口的实现类类对象  
	4、Set和List都继承自Collection接口，所以Set和List中许多方法都是一样的
	List中元素可以重复，是有序的，Set中的元素不能重复，里面的元素是无序的
	因此List接口中set()、indexOf()方法，Set接口中没有set()、indexOf()方法，因为
	Set中的元素是无序的，不存在某个元素在某个位置这个概念
	5、toString()方法，执行System.out.println(aa);等价于System.out.println(aa.toString());
	由于Java的自动装箱，所有添加到容器中的都是类对象，输出的是该类对象调用过toString()方法后的结果
	自己定义的类对象，要添加到容器中，建议重写类的toString()方法,否则输出的是
	该对象的引用，类名@.....的形式，因此要要按我们想要输出的结果重写toString()方法
	6、ArrayList和linckedList的区别：两者都实现了List接口中的方法，但是内部实现不同
	ArrayList 存储速度快，插入删除比较慢
	LinckedList 存储速度比较慢，插入删除比较快
	7、Collections是Collection接口的实现类，里面有很多方法，ArrayList 和linckedList等本身没有
	排序、倒置、查找等实现方法，是通过Collections类来实现的，里面有很多public static方法
	可直接对Collection接口的实现类进行操作，可以查找API文档
	8、用户自定义的类对象，放到容器中，如果要进行排序比较，要指定比较的标准，这个类
	要实现Comparable接口，该接口里面有compareTo()方法，要重写里面的compareTo()方法
	class Stu implements Comparable,自定义类实现了Comparable接口，一定要重写里面的
	抽象方法compareTo(Object obj)方法，不进行重写会提示报错
	9、类对象放到容器中，会计算出元素的hashCode值，根据值找要到存放该元素的地址
	不同类对象的hashCode值是不一样的，
	因此要重写hashCode()方法，该地址后面是元素连接，比如存放了new A(1)对象，可能
	new A(1)对象已将存在，为类保证不存放重复的new A(1)，因此要重写equals()方法
	10、对象放到以哈希表为存储内核的容器hashSet、hashTable、hashMap，要保证容器中没有重复的元素，
	必须要同时重写hashCode()方法和equalsO()方法，放到其他类型容器中如，放到TreeSet、TreeMap
	容器中的元素，则不需要重写hashCode()和equalsO()方法
	11、每个类中都默认有hashCode()和compareTo()方法，因为所有类都继承自Object类，
	并不是所有的类都要重写这两个方法，当要用到时，且提供的方法不满足要求时就要重写这两个方法
	12、Object类的hashCode()方法会返回该对象内存真实地址的整数化表示，
	这种不是真正地址的整数值就是哈希码
	向hashSet中添加元素的时候，先通过该对型的hashCode()方法计算出相应的桶，然后根据equals()方法
	找到相应的元素，如果该元素已经存在，则不添加到容器中，反之，添加到容器中
	因此要重写hashCode()方法，保证一个类new出的对象hashCode()方法返回值是一样的，
	即哈希码指向同一块空间，通过重写equals()方法保证这个空间中不存在相同的元素，不重写
	hashCode()方法，new出一个对象会根据返回的哈希码指向一个的存储空间，不同对象指向不同的
	空间，即使equals()方法判断成功也不能保证元素不重复，同样只重写hashCode()方法，虽然同一个类
	new出的对象在同一个空间，但是由于没有equals()方法，也不能保证这一块空间没有重复元素
7、访问权限问题
java中的访问权限一次是 public protected default private
不同包中可以有相同的类名，package com.xidian.user会生成三级文件夹目录，
包名一般是小写，域名倒过来写
public的基本作用 不同的包中的类之间的访问，如果一个包中的类不是公共的，则无法从外部包外对该类进行访问
要想访问该类，类的权限应该是public的，如果外部包中的类要访问类的成员，除类的权限是public外，类的成员的
权限也应该是public的
公开的、受保护的权限、默认的访问权限，私有的
public  可以修饰类以及类的成员，如果类是public的类中的成员也是public的，不同包，同一个包中都可以相互访问，没有访问限制
private 只能在同类中进行访问，同类中访问控制符是透明的各成员之间可以相互访问
default 在同一个包中可以任意访问，同一个包内可以访问，访问权限是包级访问权限
protected 表示受保护权限，体现在继承，即子类可以访问父类protected权限成员，同时相同包内的其他类也可以访问protected成员
protected权限是大于默认权限的，声明为protected的方法和成员变量能被同一个包里的所有类所访问
当你想让一个类中的某个方法或成员变量在包中都可见，而且其子类也能访问（子类有可能和父类不在同一个包中）
但又不想让其他类进行访问该类时，就可以用protected修饰符,只有继承关系的类之间才可以访问protected权限的成员，只能子类访问父类
在一个类中没有导入另一个包，注意下面错误的写法 	com.xidian.person Person p = new com.xidian.person Person();
而应该写成								com.xidian.person.Person p = new com.xidian.person.Person();
其中每一个点前后就是一个文件夹名，类一旦打包，类名就会发生变化，用javac和java进行编译运行的时候如果一个类已经进行了打包，
类名就变成包名夹类名的形式
一个包中需要使用另外一个包中的类，需要进行导入
不同包中的子类：子类和父类不在同一个包中，此时，子类是无法访问父类中default权限的成员函数和成员变量的，或者小于default权限的成员
即default权限不允许跨包的访问,子类和父类不在同一个包中则，子类是无法访问父类中默认权限的成员的
protected权限可以跨包进行继承，如果不同包中的多个子类要继承访问另一个包中的父类成员，可以将父类成员的访问权限设为大于或等于protected权限
这里就体现了访问的权限，如果将父类成员访问权限设置为public则在父类所在的包外部，其他类都可以进行访问，但是设置为protected权限
只有继承了父类的子类才能访问，protected权限提供了一种特殊的跨包访问权限，必须是继承父类的子类才能访问不同包中的父类成员，
如果没有继承关系，不同包中不能进行访问
protected不能修饰类只能修饰成员，default可以修饰类和成员
2、关于继承访问权限思考
子类继承父类，子类继承自父类的成员要比父类成员有更大的访问权限，子类重写继承的方法时,不可以降低方法的访问权限，
子类继承父类的访问修饰符要比父类的更大，也就是要更加开放，父类是protected修饰的，其子类只能是protected或者public，
绝对不能是default(默认的访问范围)或者private还要注意的是，继承当中子类抛出的异常必须是父类抛出的异常的子异常，
或者子类抛出的异常要比父类抛出的异常要少
子类访问权限要比父类的大
假设一个父类A拥有的方法 public void setXXX(){} 可以被其他任意对象调用
这个方法被子类B重写父类方便为void setXXX(){} 即默认的访问权限，只能被本包极其子类所访问
假设其他包中的对象C调用方法为： 
get(A a){
a.setXXX();
}  所有A类的类对象都可以传递给参数a,通过该参数调用setXXX()方法，而此时传入的对象为B类对象，假设为b，此时b将转型为a
但是B中的setXXX()方法的访问权限是默认权限，不能再包外进行访问，就会出错
以上只是一个例子还有其他出于易维护、易代码结构设计的设计思想原因
8、时间复杂度的定义：
	一般情况下，算法中基本操作重复执行的次数是问题规模n的某个函数，用T(n)表示，若有某个辅助函数f(n)，
	使得当n趋近于无穷大时，T(n)/f(n)的极限值为不等于零的常数，则称f(n)是T(n)的同数量级函数。
	记作T(n)=O(f(n))，称O(f(n))为算法的渐进时间复杂度(O是数量级的符号)，简称时间复杂度。
	O运算代表对（）内进行求数量级运算，时间复杂度T(n)是辅助函数f(n)进行数量级运算之后的结果，T(n)=O(f(n))
	例如，T(n) = 2 + 4n + 3n*log2n，则T(n) = O(n*log2n)   --------------------->   "大O表示法"
	决定算法复杂度的是执行次数最多的语句，一般也是最内循环的语句---------------->执行次数最多的语句
	求T(n)的数量级，只要将T(n)进行如下一些操作：忽略常量、低次幂和最高次幂的系数，
并且一个算法花费的时间与算法中语句的执行次数成正比例,一个算法中的语句执行次数称为语句频度或时间频度,记为T(n)
n称为问题的规模，当n不断变化时，时间频度T(n)也会不断变化。
算法中语句执行次数为一个常数，则时间复杂度为O(1)
常见的时间复杂度有：常数阶O(1),对数阶O(log2n),线性阶O(n), 线性对数阶O(nlog2n),平方阶O(n2)，立方阶O(n3),...， 
k次方阶O(nk),指数阶O(2n)

一个算法在计算机存储器上所占用的存储空间，包括存储算法本身所占用的存储空间，
算法的输入输出数据所占用的存储空间和算法在运行过程中临时占用的存储空间这三个方面。
算法的时间复杂度与执行时间是两个完全不同的概念，算法的执行时间不随着问题规模n的增加而增长，
即使算法中有上千条语句，其执行时间也不过是一个较大的常数。此类算法的时间复杂度是O(1)


(1) int num1, num2;
(2) for(int i=0; i<n; i++){ 
(3)     num1 += 1;
(4)     for(int j=1; j<=n; j*=2){ 
(5)         num2 += num1;
(6)     }
(7) }
分析：
语句int num1, num2;的频度为1;
语句i=0;的频度为1;
语句i<n; i++; num1+=1; j=1; 的频度为n；
语句j<=n; j*=2; num2+=num1;的频度为n*log2n;  log2n是以2为底,n的对数
决定算法复杂度的是执行次数最多的语句,这里是num2 += num1,一般也是最内循环的语句

关于时间复杂度的运算规则：
1) 加法规则
T(n,m) = T1(n) + T2(n) = O (max ( f(n), g(m) )

2) 乘法规则
T(n,m) = T1(n) * T2(m) = O (f(n) * g(m))

3) 一个特例（问题规模为常量的时间复杂度）
在大O表示法里面有一个特例，如果T1(n) ＝ O(c),c是一个与n无关的任意常数,T2(n) = O ( f(n) ) 则有
T(n) = T1(n) * T2(n) = O ( c*f(n) ) = O( f(n) )
也就是说，在大O表示法中，任何非0正常数都属于同一数量级，记为O(1)。

4) 一个经验规则
复杂度与时间效率的关系：
c < log2n < n < n*log2n < n2 < n3 < 2n < 3n < n! （c是一个常量）
|--------------------------|--------------------------|-------------|
         较好                     一般              较差
其中c是一个常量，如果一个算法的复杂度为c 、 log2n 、n 、 n*log2n,那么这个算法时间效率比较高 
如果是 2n , 3n ,n!,那么稍微大一些的n就会令这个算法不能动了，居于中间的几个则差强人意。
9、函数的递归：
被调函数运行的栈空间独立于调用函数的栈空间，所以与调用函数之间的数据也是无关的，函数之间靠参数传递和返回值来联系，函数看作为黑盒。
递归之所以能实现，是因为函数的每个执行过程都在栈中有自己的形参和局部变量的拷贝，这些拷贝和函数的其他执行过程毫不相干。

<important>
C语言中的结构体，是用基本数据类型构造的数据类型，相当于Java中的类   数据类型
C语言中如何实现传进所有类型的数据呢？用空指针，所有类型都有其地址，可以将不同数据类型的地址赋予空指针 (void * pval)
Java中用泛型的概念来实现<T>
10、
abstract class animals 
{
	public abstract String cry();  //抽象方法必须用abstract关键字进行修饰
}
一个类继承了抽象类或者实现了某个接口，要实现抽象类中所有的抽象方法，或者给出接口中所有抽象方法的方法体，否则
该类必须声明为抽象类
抽象方法必须为public或者protected（因为如果为private，则不能被子类继承，子类便无法实现该方法），缺省情况下默认为public
抽象类就是为了继承而存在的，如果你定义了一个抽象类，却不去继承它，那么等于白白创建了这个抽象类，
因为你不能用它来做任何事情。对于一个父类，如果它的某个方法在父类中实现出来没有任何意义，必须根据子类的实际需求来进行不同的实现
父类规定，子类实现
接口是一种更为严格的抽象类：接口中定义属性必须是public static final类型的，定义方法必须是public abstract类型的
这些符号可以部分或者全部省略,便决定了接口是一个抽象类，接口中所有的方法不能有具体的实现，也就是说，接口中的方法必须都是抽象方法。
interface it3 extends it1,it2	class B extends A implemrents it1  都是允许的
<important> 
一个类只能继承一个抽象类，而一个类却可以实现多个接口
抽象类和接口的区别，除了语法层面的区别，更多的是设计层面的区别
“一个类实现了某个接口便具有了接口的性质”
抽象类和接口的区别：
1.语法层面上的区别
1）抽象类可以提供成员方法的实现细节，即抽象类中可以有非抽象方法，而接口中只能存在public abstract方法；

2）抽象类中的成员变量可以是各种类型的，而接口中的成员变量只能是public static final类型的；

3）一个类只能继承一个抽象类，而一个类却可以实现多个接口。

2.设计层面上的区别

1）抽象类是对一种事物的抽象，即对类抽象，而接口是对行为的抽象。抽象类是对整个类整体进行抽象，包括属性、行为，
但是接口却是对类局部（行为）进行抽象。举个简单的例子，飞机和鸟是不同类的事物，但是它们都有一个共性，就是都会飞。
那么在设计的时候，可以将飞机设计为一个类Airplane，将鸟设计为一个类Bird，但是不能将 飞行 这个特性也设计为类，
因此它只是一个行为特性，并不是对一类事物的抽象描述。此时可以将 飞行 设计为一个接口Fly，包含方法fly( )，
然后Airplane和Bird分别根据自己的需要实现Fly这个接口。然后至于有不同种类的飞机，比如战斗机、民用飞机等直接继承Airplane即可，
对于鸟也是类似的，不同种类的鸟直接继承Bird类即可。从这里可以看出，继承是一个 “是不是”的关系，而 接口 实现则是
 “有没有”的关系。如果一个类继承了某个抽象类，则子类必定是抽象类的种类，而接口实现则是有没有、具备不具备的关系，
 比如鸟是否能飞（或者是否具备飞行这个特点），能飞行则可以实现这个接口，不能飞行就不实现这个接口。

2）设计层面不同，抽象类作为很多子类的父类，它是一种模板式设计。而接口是一种行为规范，它是一种辐射式设计。
什么是模板式设计？最简单例子，大家都用过ppt里面的模板，如果用模板A设计了ppt B和ppt C，ppt B和ppt C
公共的部分就是模板A了，如果它们的公共部分需要改动，则只需要改动模板A就可以了，不需要重新对ppt B和ppt C进行改动。
而辐射式设计，比如某个电梯都装了某种报警器，一旦要更新报警器，就必须全部更新。也就是说对于抽象类，如果需要添加新的方法，
可以直接在抽象类中添加具体的实现，子类可以不进行变更；而对于接口则不行，如果接口进行了变更，则所有实现这个接口的类都必须进行相应的改动。

下面看一个网上流传最广泛的例子：门和警报的例子：门都有open( )和close( )两个动作，此时我们可以定义通过抽象类和接口来定义这个抽象概念：
abstract class Door {
    public abstract void open();
    public abstract void close();
}
或者：
interface Door {
    public abstract void open();
    public abstract void close();
}
但是现在如果我们需要门具有报警alarm( )的功能，那么该如何实现？下面提供两种思路：
1）将这三个功能都放在抽象类里面，但是这样一来所有继承于这个抽象类的子类都具备了报警功能，但是有的门并不一定具备报警功能；

2）将这三个功能都放在接口里面，需要用到报警功能的类就需要实现这个接口中的open( )和close( )，
也许这个类根本就不具备open( )和close( )这两个功能，比如火灾报警器。

从这里可以看出， Door的open() 、close()和alarm()根本就属于两个不同范畴内的行为，
open()和close()属于门本身固有的行为特性，而alarm()属于延伸的附加行为。因此最好的解决办法是单独将报警设计为一个接口，
包含alarm()行为,Door设计为单独的一个抽象类，包含open和close两种行为。再设计一个报警门继承Door类和实现Alarm接口。
11、泛型
泛型的类与接口:	public class Gen<T>   T是类中要操作的的数据类型，在类中只能操作T类数据类型
				{}
				 Gen<String> gg = new Gen<String>();  泛型类生成类对象
				 Gen<String> gg = new Gen<>();
泛型方法：		public <T> void fromArrayToCollection(T[] arr,Collection<T> collec)
方法有两个参数T类型的数组arr以及Collection接口的实现类类对象，但是该实现类类对象中中存放的是T类对象
该方法使用了泛型，只需要改变里面的T就可以实现该方法对任何数据类型的操作，提高了代码的利用率

List的实现类类对象arr里面指定了Number，因为 Byte, Double, Float, Integer, Long, Short等都是
Number的子类因此可以将这些类对象放到容器中
	这种“is-a”关系，同样也是用泛型。如果你将泛参设置Number，那么在随后的调用里，只需要传入一个数据对象就行了，如下：
		List<Number> arr = new ArrayList<Number>();
		arr.add(new Integer(1));
		arr.add(new Float(1));
		arr.add(new Double(1));
		arr.add(new Long(1));  或者
	List<A> alist = new ArrayList<A>();  //里面放的是A类对象 但也可以将B类对象放到里面，因为B是A的子类
	alist.add(new A(22,"张三"));
	alist.add(new B(20,"李四","西安电子"));  
	
Integer是Number的子类，但Box<Integer>并不是Box<Number>的子类。Box<Integer>与Box<Number>的共同父类是Object
两者都是泛型类，前者代表Box类中操作的数据类型只能是Integer类对象，后者代表操作的数据类型只能是Number换言之，
无论类A与类B是否存在关联，MyClass<A>与MyClass<B>都没有任何关联，其共同的父类的是Object。

有时候，你会希望泛型类型只能是某一部分类型，比如操作数据的时候，你会希望是Number或其子类类型。
这个想法其实就是给泛型参数添加一个界限。其定义形式为：    <T extends BoundingType>
此定义表示T应该是BoundingType的子类型，T和BoundingType可以是类，也可以是接口。另外注意的是，
此处的”extends“表示的子类型，不等同于继承。
泛型的“extends”与继承的“extends”并不一样，泛型的“extends”其后可以是一个类（如T extends Number），
同样也可以是一个接口（如T extends List<T>），泛型的”extends“代表子类型，而不是子类，
或许你可以把等其同于”extends（继承）“和”implement的并集。
*/