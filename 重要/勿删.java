/*系统的指令可以在任何的文件目录下运行，
用户当运行自定义的指令时，会在当前目录下寻找是否有该文件，
当找不到的时候会在系统环境变量下指定的路径下寻找，环境变量找不到，执行就会异常
systeminfo、ping等DOS命令无法运行的情况可能是系统环境变量下没有包含相应的路径
将路径"C:\Windows\System32"添加到环境变量下即可
2、多尝试
3、无论是Thread类中的run()方法还是Runnable接口中的run()方法，都没有抛出异常
因此一个类继承了Thread类，或者是实现了Runnable借口，该类中的方法都不能抛出异常
如果run()方法内要进行异常处理，要在run()方法内部进行异常的捕获处理
class ThreadTesterA implements Runnable
{
	public void run()
	{
		System.out.println(Thread.currentThread().getName() + "Thread start");
		try
		{
			Thread.sleep(5000);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "Thread end");
	}
}
class TestJoin
{
	public static void main(String[] args) throws InterruptedException
	{  									//这里调用join()方法要抛出异常，可以直接抛给被调用者
		ThreadTesterA threadtestera = new ThreadTesterA();
		Thread tha = new Thread(threadtestera);
		tha.start();  //调用start()方法创建一个新的线程，其实就是执行run()方法中的代码
		tha.join(1000); //在main线程中有该行代码，代表主线程等待tha所代表的线程1000毫秒，
						//主线程只等待1000毫秒，而不管tha所代表的线程什么时候结束
		System.out.println(Thread.currentThread().getName() + "hello world");
	}
}

执行tha所代表的线程，在main线程中有tha.jion(1000);代表主线程等待tha所代表的线程1000毫秒，
而不管tha所代表的线程是否已经执行完毕，是否执行多长的时间，就会接着执行main线程
因此运行结果是先输出  tha线程中的Thread start----->然后主线程中的hello world----->tha线程中的Thread end

4、	Scanner sc = new Scanner(System.in);
	n = sc.nextInt();
	Integer[] arr = new Integer[50];
	for(i = 0;i < n;i ++)
		arr[i] = sc.nextInt();
	new Qsort().quick_sort(arr,0,n - 1);
5、在一个类中生成另一个类的类对象，该类对象属于该类的成员
在一个类的方法中生成另一个类的类对象，该类对象属于该类方法中的局部变量
6、三个文本框相加
ActionListener接口下有一个void actionPerformed(ActionEvent e)方法，要给出该接口的方法体

Integer 类在对象中包装了一个基本类型 int 的值。Integer 类型的对象包含一个 int 类型的字段。 
此外，该类提供了多个方法，能在 int 类型和 String 类型之间互相转换，还提供了处理 int 类型时非常有用的其他一些常量和方法。
构造方法：  
Integer(int value)  作用是构造一个新分配的 Integer 对象，它表示指定的 int 值。
Integer(String s)   作用是构造一个新分配的 Integer 对象，它表示 String 参数所指示的 int 值。
static int parseInt(String s, int radix) 使用第二个参数指定的基数，将字符串参数解析为有符号的整数
7、所有的细节都封装在函数中
launch()方法中的这些text1、label、bn等属于局部变量，不是类的属性，
属于在一个类的方法中定义另一个类的类对象,不能在另一个类的方法中访问
因为在另一个类的方法中要访问text1,text2,text3,因此将其定义为类的属性

Listener ll = new Listener(this);
在创建类对象的时候自动调用有参数的构造方法，完成类对象的初始化
this指向当前正在调用(该语句所在的方法)launch()的类的类对象，也就是TF类对象
而在Listener类的构造方法中，this传给构造方法的形参aa，
构造方法将形参aa赋予类的属性aa,这样保证是一个TF类对象

这里用静态成员可由类名点成员名的方式进行访问的特点，
在Monitor类中并没有生成TF类的对象，在Text_4类的main方法中却生成了TF类的对象
由于tf1,tf2,tf3是静态的，属于类本身，所以通过类名进行访问，类对象进行访问的是同一个对象
因此运行结果可以输出

	1、TextField tf1,tf2,tf3;是定义类三个文本框，TextField相当于int、float、struct node *等等
	相当于类A、类Thread、类Exception等等
	2、main方法也是启动类中的一个方法，而且是静态方法，他要访问的成员必须定义为静态成员
	3、int num1 = Integer.parseInt(tt.tf1.getText());
	Integer类类中有parseInt()方法将内容解析为int型数据并赋予变量num1
	4、@Override下面的代码表明是对超类中方法的覆盖或重写
	5、class Monitor extends WindowAdapter implements ActionListener
	先继承后实现
	
8、在类B中访问类A的成员，要在类B中生成类A的类对象，通过该类对象进行访问，
若类A中的成员是静态的，在类B中可以直接通过A类类名进行访问，不用在类B中生成类A的类对象
关于两个类都要访问同一个类A的问题，还可以在一个类中生成另一个类的类对象，通过类对象访问类A中的成员
另一个类访问类A，不生成类A的类对象，通过类A类名的方式进行访问，前提是访问的成员必须是静态的	

9、关于某个类继承父类具有父类的性质的问题
class TF extends Frame
{
	public TextField text1,text2,text3;
	public TF()
	{
		super("hello");
	}
	public void launch()
	{
		text1 = new TextField(15); 
		text2 = new TextField(15);
		text3 = new TextField(15);
		Button bn = new Button("=");
		Label label = new Label("+");
		setLayout(new FlowLayout());
		add(text1);
		add(label);
		add(text2);
		add(bn);
		add(text3);
		setVisible(true);
		Listener ll = new Listener(this);
		addWindowListener(ll);
		bn.addActionListener(ll);
	}
}
//TF类继承了Frame类就具有了Frame类的性质，在该类的方法中可以直接调用相应地方法
但是Button类并没有继承某个类，因此不能直接调用某个方法，必须用类对象名点方法的方式进行访问
public TF(){}是TF类的构造方法，TF类属于子类，在子类构造方法中通过super语句调用父类的构造方法

10、总之，java的transient关键字为我们提供了便利，你只需要实现Serilizable接口，
将不需要序列化的属性前添加关键字transient，序列化对象的时候，这个属性就不会序列化到指定的目的地中。
们都知道一个对象只要实现了Serilizable接口，这个对象就可以被序列化，
java的这种序列化模式为开发者提供了很多便利，我们可以不必关系具体序列化的过程，
只要这个类实现了Serilizable接口，这个类的所有属性和方法都会自动序列化。

11、内部类：内部类可以看做外部类的一个成员
在A类的内部但是A的所有方法外部定义一个类B，类B是类A的内部类
内部类访问原则：内部类可以访问外部类所有的成员，外部类不能直接访问内部类的成员
内部类优点：可以方便访问外部类的所有成员，可以增加程序的安全性，避免不相关类的访问

匿名类：匿名类是一种特殊的内部类，如果在一个类的方法内部定义了一个匿名类，
该匿名类可以访问外部类的所有成员，包裹该匿名类的方法内被final修饰的局部变量，
非final修饰的局部变量不能被匿名类访问
	1、
	如果A是一个匿名类
	new A()
	{
		实现接口中方法的代码
	}//功能是生成了一个实现了A接口的对象
	2、
	假设A是抽象类
	new A()
	{
		实现了A中所有抽象方法的代码
	}//功能是生成一个匿名类，该匿名类要实现类A中所有抽象方法
	3、
	假设类A是一个普通类
	new A()
	{
		代码
	}//功能是生成类A的一个子类对象，该匿名类对象继承了A中所有非private成员
	------------------
class Moinitor implements ActionListener()
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		......
	}
} //创建监听器类Monitor实现了ActionListener接口
创建监听器Moinitor，Moinitor继承了WindowAdapter类
class Monitor extends WindowAdapter()
{
	@Override
	public void windowClosing(WindowEvent e)
	{
		System.exit(-1); /// .....
	}	
}
12、将int类型转化为字符串输出的几种方法
str = i + "";
str = Integer.toString(i);
str = String.valueOf(i);
static Integer valueOf(String s)  String to Integer 
static String valueOf(int i) 	  Integer to String
static String toString(int i) 
13、
/*
String nextLine()方法
next(),nextByte(),nextDouble(),nextFloat,nextInt(),nextLine(),nextLong(),nextShot()
使用nextLine()方法输入行原样输出，可包含空格.
next()方法如果输入中有空格，则不能完整输出
程序中new的意思是创建了一个Scanner类的对象scan.但是在创建Scanner类的对象时,
需要用System.in 作为它的参数,也可以将Scanner看作是System.in对象的支持者,
System.in取得用户输入的内容后,交给Scanner来作一些处理.
Scanner取得输入的依据是空格符,包括空格键,Tab键和Enter键.当按下这其中的任一键时,Scanner就会返回下一个输入

我们可以考虑使用BufferedReader类取得输入
BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
String text = buffer.readLine();
readLine()方法会返回用户在按下Enter键之前的所有字符输入,不包括最后按下的Enter返回字符
BufferedWriter类下面的方法void newLine() 写入一个换行符
System.out.println("please input a char");  
char c = (char)System.in.read();  
System.out.println(c); 
*/
/*
import java.io.*;
class Test_5
{
	public static void main(String[] args) throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String string = br.readLine();
		System.out.println("string = " + string);
	}
}

import java.util.Scanner;
class Test_4
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		System.out.println("str = " + str);
	}
}
14、static void setOut(PrintStream out)   重新分配“标准”输出流
	static void setErr(PrintStream err)   重新分配“标准”错误输出流 
15、对于自定义的类对象，若将其放到容器中，输出的是类对象的地址
对于用户自定义的类对象，要重写里面的toString()方法，输出的才不至于是类对象的地址
16、接口interface  
public abstract double areas();//抽象方法一定要用abstract进行修饰
interface shape  //定义接口
{
}
1、接口是一个更加严格的抽象类
2、接口中定义属性必须是public static final类型的，定义方法必须是public abstract类型的
这些符号可以部分或者全部省略,便决定了接口是一个抽象类，
(抽象类)也不能生成接口的对象，但是可以生成接口的引用，
将其指向实现接口的对象，从而达到多态的目的
4、interface it3 extends it1,it2
class B extends A implemrents it1  都是允许的
接口的存在在于接口可以增加许多类都需要实现的功能，不同类可以实现相同的接口，一个类
也可以实现不同的接口，接口只强调具体的功能，不关心功能的具体实现过程
一个类实现了某个接口要给出接口方法的具体实现，也就是给接口中的所有方法添加方法体
17、
重写equals方法的先判断调用equals方法的引用和该方法的形参是否是同一类型，是-->堆空间里面的内容肯定相等
不是-->判断两个引用是否是同种类型，是---->判断里面的内容是否相等，不是---->引用不是同种类型，没有比较的必要
基本数据类型 == 比较的是值是否相等
引用数据类型中 == 是比较双等号两端是否指向的是堆内存中的同一块儿存储空间，
是否是堆内存中的同一个对象，引用就相当于地址，地址是用来指向的是其他变量的
指针变量也是一种变量，他存储的是其他变量的地址(该指针所指向变量的地址)
指针变量本身也有地址
18、instanceof 左边是类对象右边是类，判断左边的类对象是不是右边的类型
两个不相关的类之间无法用instanceof
出现这种情况经常是需要强制转换的时候，
class Dog extends Animal
{}
譬如dog定义了自己的方法wangwang()
Animal anAnimal = new Dog();
此时不能直接调用anAnimal.wangwang()方法
但是可以
if(anAnimal instanceof Dog){
Dog dog = (Dog)anAnimal;
dog.wangwang  //就可以调用了
}
19、抽象类和实现类的关系
IO流中处理流和节点流的关系
按照流是否直接与特定的地方(如磁盘、内存、设备等)相连，分为节点流和处理流两类。
节点流：可以从或向一个特定的地方(节点)读写数据。如FileReader 
处理流：是对一个已存在的流的连接和封装，通过所封装的流的功能调用实现数据读写。
如BufferedReader。处理流的构造方法总是要带一个其他的流对象做参数。一个流对象经过
其他流的多次包装，称为流的链接。处理流包裹或者说嫁接在节点流之上，可以使节点流具有更加
强大的功能
20、线程：
[1]、Thread(Runnable target, String name)  是Thread类的其中一个构造方法，
target是实现了Runnable接口的类对象name指定线程的名字
static Thread currentThread()   返回对当前正在执行的线程对象的引用。
[2]、注意join()方法，tha.join(),该行代码所在的线程等待调用join()方法的tha线程所设定的时间，
而不管tha线程什么时候结束，就等待tha线程所设定的时间，如果join()方法没有设定时间
则等待tha所代表的线程执行完毕，再执行tha.join()代码所在的线程
主线程调用子线程，子线程调用了join()方法，主线程等待子线程join()方法所设定的时间，不管子线程什么时候执行完毕
时间一到就会执行主线程，如果没有设定时间，则主线程等待子线程执行完毕，再执行子线程
为什么要用join()方法
在很多情况下，主线程生成并起动了子线程，如果子线程里要进行大量的耗时的运算，
主线程往往将于子线程之前结束，但是如果主线程处理完其他的事务后，需要用到子线程的处理结果，
也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法了，让主线程等待子线程执行完毕主线程再结束。
[3]、yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到让步目的，
因为让步的线程还有可能被线程调度程序再次选中。yield()不是导致线程转到等待/睡眠/阻塞状态。
在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果
[4]、Thread.sleep(long millis)方法，使线程转到阻塞状态。millis参数设定睡眠的时间，
以毫秒为单位。当睡眠结束后，就转为就绪（Runnable）状态。
[5]、sleep()和yield()的区别
sleep()和yield()的区别):sleep()使当前线程进入停滞状态，所以执行sleep()的线程在指定的时间内肯定不会被执行；
yield()只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行。
sleep 方法使当前运行中的线程睡眼一段时间，进入不可运行状态，这段时间的长短是由程序设定的，
yield 方法使当前线程让出 CPU 占有权，但让出的时间是不可设定的。实际上，yield()方法对应了如下操作：
先检测当前是否有相同优先级的线程处于同可运行状态，如有，则把CPU的占有权交给此线程，否则，
继续运行原来的线程。所以yield()方法称为“退让”，它把运行机会让给了同等优先级的其他线程
另外，sleep 方法允许较低优先级的线程获得运行机会，但yield()方法执行时，当前线程仍处在可运行状态，
所以，不可能让出较低优先级的线程些时获得 CPU 占有权。在一个运行系统中，如果较高优先级的线程没有调用
sleep方法，又没有受到 I\O 阻塞，那么，较低优先级线程只能等待所有较高优先级的线程运行结束，才有机会运行。 
[4]、main方法其实也是一个线程。在java中所有的线程都是同时启动的，至于什么时候，哪个先执行，
完全看谁先得到CPU的资源。在java中，每次程序运行至少启动2个线程。一个是main线程，
一个是垃圾收集GC线程。因为每当使用java命令执行一个类的时候，
实际上都会启动一个ＪＶＭ，每一个JＶＭ实际就是在操作系统中启动了一个进程。
21、public static long currentTimeMillis()		返回以毫秒为单位的当前时间,当返回值的时间单位是毫秒时，
值的粒度取决于基础操作系统，并且粒度可能更大 
public static long nanoTime()   			返回最准确的可用系统计时器的当前值，以毫微秒为单位
22、接口的出现在与增加了许多类都需要实现的功能，只定义里面的抽象方法，把方法的具体实现交给子类来完成
定义了一个接口，许多类都实现了这个接口，在这些类中要重写接口中的抽象方法，再构造另外一个类A，在类A中调用
实现了接口的类中的共有的方法也可以说子类从父类继承改写后的方法，我们只生成接口的实现类的类对象，只将接口
的实现类的类对象传递给类A中的某个方法，根据传递的参数的不同，执行实现了接口的不同的类中的方法
定义一个打印机接口，佳能打印机类，惠普打印机类都实现了这个接口，可以将佳能打印机类，惠普打印机类的类对象
赋予打印机这个接口的引用，通过该引用可以调用佳能打印机类，惠普打印机类中的方法，
一个接口,许多类都实现了这个接口，可以将这些实现了接口的实现类的对象赋予接口的引用，通过传递的实现类的类对象的不同
通过接口的引用调用不同的实现类中的方法
一个打印机接口,许多类都实现了这个接口，将生成实现类类对象的代码封装在打印机工厂这个类的getprinter()这个方法中，
该方法会根据传递的参数的不同，返回相应地实现类的类对象，调用getprinter()这个方法就会得到不同的打印机
*/