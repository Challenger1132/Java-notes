同步产生效率低的问题，如果出现同步嵌套，容易出现死锁问题，死锁问题是两个以及的线程在执行的过程中
因争夺资源产生的一种相互等待的现象
同步代码块的嵌套造成线程的死锁
notify()  唤醒在此监视器上等待的单个线程
notifyAll()  唤醒在此监视器上等待的所有线程线程
wait()  wait(设置时间)  当前线程一直等待，直到设置时间到达，或者notify()、notifyAll()方法唤醒
思考这些方法为什么在Object()类中？
/* 实现了生产、消费两个线程的线程安全
 * 实现安全：通过加锁对象，在同步代码块中只允许一个线程操作数据，解决了线程的安全
 * 实现了生产消费一次执行的问题,必须现生产，然后再消费，使用wait()、notify()方法实现 
 * 一般是将同步唤醒等待机制封装到同步方法中
线程池能够批量设置线程池中线程
启动一个新的线程成本是比较高的，因为他要涉及到与操作系统进行交互，线程池能很好的提高效率，尤其是当程序中
存在大量生存期很短的线程的时候，用该考虑使用线程池
线程池中每一个线程代码结束之后，并不会死亡，而是再次回到线程池中成为空闲状态，等待下一个对象调用

Executors类中有静态方法用于创建线程池等操作，创建线程池，返回值是ExecutorService 类型的
static ExecutorService newFixedThreadPool(int nThreads)//创建一个有 nThreads个线程的线程池
这种线程池中的线程可以执行Runnable或者Callable接口代表的线程
<T> Future<T> submit(Callable<T> task) 提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future。 
Future<?> submit(Runnable task) 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。 
ExecutorService pool = Executors.newFixedThreadPool(3);  //线程池的大小
pool.submit(new MyThread());
Callable<T>可以添加泛型，有返回值，依赖于线程池而使用

ExecutorService pool = Executors.newFixedThreadPool(3);
Executors是一个类，类中都是静态方法，可以直接通过类名的方式进行调用，调用newFixedThreadPool()方法的返回值是
ExecutorService类型，这和其他的没有任何区别
File file = new File("C:/");
String[] FileList = file.listFiles();  文件对象调用listFiles()方法返回的是字符串数组类型
FileList这个字符串数组也可以有其他方法

创建线程方式的思考 && 关于匿名类
	方式一：
		class DemoThread extends Thread{
			@Override
			public void run() {
			}
		}
		DemoThread thread = new DemoThread();
		thread.start();
								new Thread(){  //和上面的等价，相当于继承了Thread类，Thread无参构造形式
									@Override
									public void run() {
									}
								}.start();
	方式二
	class DemoThread0 implements Runnable{
		@Override
		public void run() {
		}
	}
	DemoThread0 thread = new DemoThread0();
	Thread th = new Thread(thread);  // Thread类需要一个Runnable对象，Runnable接口的实现类类对象
	th.start();
								new Thread(new Runnable(){  // 相当于Thread中有一个Runnable接口的实现类类对象
									@Override
									public void run() {
									}
								}).start();
	之前说传递给Thread类形参的，必须实现Runnable接口，这里只是Thread类的两种不同的构造器而已，一个需要参数，一个不需要参数
	Java中有多种这种表现，构造器的重载，要注意

	Thread th = new Thread(thread);  这里需要一个Thread类对象th，那么我直接给出匿名类， new Thread(thread) {....};
	该类中的参数是Runnable接口,那么我直接也给出匿名类实现  new Runnable() {...}  结果就是 new Thread(new Runnable() {...});
	联想之间容器排序的两个接口
	我需要一个Comparable接口的实现类对象，那么就直接  new Comparable() {....重写compareTo()方法}
	我需要Comparator接口的实现类类对象，那么就直接    new Comparator() {....重写compare()方法}
	需要某个接口的实现类对象就直接  new 接口{...},需要抽象类的实现类对象,就直接  new 抽象类 {....} 需要普通类对象就直接  new 普通类 {....}

	Thread thread = new Thread(){
		public void run() {
			System.out.println(Thread.currentThread().getName() + " Thread类");
		}
	};
	thread.start();
									new Thread(){
										public void run() {
											System.out.println(Thread.currentThread().getName() + " Thread类");
										}
									}.start();  //两种方式等价
Timer以及TimerTask设置定时任务
Timer t = new Timer();
t.schedule(new MyTask(), 3000);
MyTask继承了TimerTask类并且重写了里面的的run()方法,用于指定任务是什么
执行任务后取消任务，在run()方法里面调用cancel()方法，t.cancel();注意这个t应该是调用schedule()方法
的那个对象，为保持一致，将t传进MyTask(t)的构造方法

Java程序设计原则：
		|---单一职责原则：每个类只有一个原则，对外只提供一种功能，用户类只与用户相关的操作相关
			开闭原则：	一个对象对扩展开放，对修改关闭，对类的修改是通过增加代码实现的，而不是修改现有的代码
						一旦写出了可以运行的代码，就不应该改动他，而是保证他一直运行下去，这需要借助抽象和多态
						把可能变化的内容抽象出来，从而使抽象的部分是相对稳定的，而具体的实现是可以改变和扩展的
			理氏替换原则：任何父类出现的地方都可以用其子类来替换
			接口分离原则：不应该强迫程序依赖他们不需要使用的方法，也就是一个接口不应提供太多的行为，一个接口只提供一种对外功能，
						  不应该将所有操作都封装到接口中
			迪米特原则： 一个对象应该对其他对象尽可能少的了解，降低各个对象之间的耦合，提高系统的课维护性，模块之间应该只通过接口进行编程
						 而不理会接口内部发的工作原理，这样可以使各个模块耦合降到最低，促进软件的复用
设计模式
|---静态工厂模式，定义一个具体的工厂类，由工厂负责类对象的创建
|---工厂方法模式，抽象工厂负责定义创建对象的接口，具体对象的创建工作由继承抽象工厂的具体类来实现
	具体的动物工厂类实现了工厂接口，用来创建具体的动物对象，而要创建的具体动物类实现了动物接口
|---单例设计模式，确保类在内存中只有一个对象，该实例自动创建，并且对外提供
		优点：在内存中只有一个对象，节约系统资源，对于一些需要频繁创建和销毁的对象，单例模式可以提高性能
		缺点：没有抽象层，很难扩展，职责过重，在一定程度上违背了单一职责原则
		单例模式思想：在内存中只有一个对象
		饿汉式：类一加载就会创建对象，不会出现问题的单例模式
		class Animals{
			private Animal(){
														//将构造器私有化，防止类外部创建对象
			}
			private static Animal animal = new Animal();  //类内部创建的对象，也要private static修饰，防止在类外部进行修改
			public static Animal getAnimal() { 			//向外提供接口,必须是静态方法，因为非静态只能通过类对象调用
				return animal;
			}
		}
		懒汉式：用的时候才会创建对象，会出现问题的单例模式
		public class Student {
			private Student(){  //私有化构造器，防止类外new出对象
			}
			private static Student student = null; 	//如果不用就不创建对象
			public synchronized static Student getStudent(){ //非静态方法，只能通过类对象调用，因为我们的目的是类外不能new出对象
												//因此方法必须是static的，可以通过类名进行访问，且不允许类外对对象进行修改，加private修饰
				if(student == null){ 	//假设thread0抢到执行权走到这里，然后被thread1抢到也走到这里，继续向下走thread0会造出一个对象然后return该对象，
										//被thread1抢到，向下走也造出并return一个对象，造出的不是同一个对象，因此线程不安全，
					student = new Student();
				}
				return student;
			}
		}
		/* 线程安全的本质是多个线程访问的不是同一个数据资源？或者说多个线程访问各自的资源？
		 * 线程安全问题出现的原因：多线程环境、共享数据、多条语句操作共享数据
		 * 懒汉式会有延迟加载，和线程安全问题(设计多条语句操作共享数据t)、因此单例模式的改进是get对象的方法加同步synchronized修饰 * */

			public class Runtime {
		    private static Runtime currentRuntime = new Runtime();
		    public static Runtime getRuntime() {
		        return currentRuntime;
		    }
		    private Runtime() {}
		}

适配器类
一个类实现某个接口，重写(实现)接口中所有的抽象方法，即使有时候我们只使用其中的一个方法，适配器类就解决了这个问题
接口(方法比较多)----- 适配器类(实现接口，仅空实现) ------ 具体类继承适配器类(用到哪个方法就重写哪个方法)
如果接口中抽象方法比较少就没必要设计这个接口的适配器类





0-65535   0-1024
Socket套接字：网络上IP地址与端口号组合在一起构成网络上能偶唯一识别的套接字
Socket通信原理：
	通信两端都有Socket
	网络通信其实就是Sockert通信
	数据在两个Scoket之间通过IO进行传输

UDP协议发送数据：(用户数据报协议，因此他发送的数据就是数据报 包)
	1、创建发送端socket对象
	2、创建数据，并将数据打包
	3、调用socket对象的发送方法发送数据
	4、释放资源

public class DatagramSocket extends Object   此类表示用来发送和接收 数据报包 的套接字
<------>
数据报套接字是 包投递服务 的 发送 或 接收 点。每个在数据报套接字上发送或接收的包都是 单独编址和路由 的。从一台机器发送到另一台机器的
多个包可能 选择不同的路由，也可能按不同的顺序到达，在 DatagramSocket 上总是启用 UDP 广播发送，为了接收广播包，
应该将 DatagramSocket 绑定到通配符地址。在某些实现中，将 DatagramSocket 绑定到一个更加具体的地址时广播包也可以被接收


	public void send(DatagramPacket p) throws IOException 从此套接字发送数据报包
	DatagramPacket 包含的信息指示：将要发送的数据、其长度、远程主机的 IP 地址和远程主机的端口号。
	public final class DatagramPacket extends Object 此类表示数据报包，通过 DatagramPacket 的构造方法来创建数据报包 

数据报包用来实现无连接包投递服务，每条报文仅根据该包中包含的信息从一台机器路由到另一台机器。
从一台机器发送到另一台机器的多个包可能选择不同的路由，也可能按不同的顺序到达。不对包投递做出保证(不可靠的传输协议)

利用UDP传输数据，发送一张图片，然后接受该图片，得到的图片是错乱的，是否可以证明多个包可能选择不同的路由，也可能按不同的顺序到达？？？

UDP 协议：将数据源和目的封装成数据包，用来实现无连接包投递服务，因不需要建立连接速度快，是不可靠的传输协议，每个数据包的大小限制在64K
TCP 协议：面向连接的协议，建立数据传输的通道，在连接中进行大量数据传输，通过三次握手建立连接，是可靠的传输协议，但是有开销
	byte[] buff = new byte[64*1024]; //空间大小是64K

public class Socket extends Object  此类实现客户端套接字,套接字是两台机器之间的通信端点。
public class ServerSocket extends Object此类实现服务器套接字。服务器套接字等待请求通过网络传入。它基于该请求执行某些操作，然后可能向请求者返回结果

关于空字符串与null  ？？？？
Scanner下面 next()方法遇到enter tab 空格 就结束，返回三个标志之前的内容字符串
用nextLine()方法输入，字符串中间可以带空格tab，遇到enter键输入结束
当只输入enter的时候这时返回的是空字符串，但是 str ！= null，字符串只是没有内容而已
BufferedReader下面的readLine()方法读取一行，遇到enter结束，返回用户在按下Enter键之前的所有字符输入,
不包括最后按下的Enter返回字符，当只输入enter时返回的是空字符串，字符串只是没有内容而已
BufferedWriter bw.write(str);将字符串写到指定的地方，newLine()方法写入一个换行符，不用newLine()
就会在原来的基础上接着写，不会另起一行
可以直接往程序外部写字符串，默认有8K的缓存，写完之后一定要记得flush()，还有close()，否则可能出现一个字都没有
这里主要是空格，空字符串和null区分不清楚，空字符串也是字符串，只是没有内容而已，不是null
String str = new String(); str是引用

Java中一切都是对象(引用，地址...) str = br.readLine(),这里str也是对象，对于String类型的对象判定是null
或者不是null，但是和字符串本身是不是有内容，是不是空串没有任何关系，只要对象“正常”，都不是null
对于String str = new String("hello world"); str存在于栈空间中(引用，静态指针变量)，new String("hello world")
是String类的一个对象，存在于堆空间中，str指向这个对象，可以认为str存储的是堆空间中那个对象的地址
A aa = new A(); 同样的aa 是引用(静态指针变量)存在于栈空中，new A()是类A的一个对象，存在于堆空间中
aa指向这个对象，可以认为aa存储的是堆空间中那个对象的地址
aa本身没有i、j成员，aa.i aa.j是aa所指向的堆空间中那个A类对象new A()的i j成员
因此对于引用的判断才是null或者不是null

		int port = 10086;
		ServerSocket ss = new ServerSocket(port); //阻塞式
		while(true){
			Socket s = ss.accept();  //服务器端一直监听客户端的请求，一旦有客户端发送请求，就会被服务器accept
			//然后得到该客户端的Socket对象，并返回，然后服务器端根据将该Socket对象，对不同的用户进行处理
			//服务器端每得到一个客户端的Socket对象，就创建一个新的线程，将该Socket对象作为线程的参数传进去
			new Thread(new UserThread(s)).start();  //创建线程并启动
			//Thread类的形参是实现了Runnable接口的实现类对象，该类对象的参数是客户端的Socket对象
		}

	当程序要使用某个类的时候，要将类加载到内存中，如果类还未加载到内存中，系统会通过加载，连接，初始化三步来实现对这个类的初始化
		加载：就是将class文件读到内存，并为之创建一Class对象(每一个类的class文件都是Class这个类的类对象，注意是有Class这个类的)
		任何类在被使用时系统都会建立一个Class对象
		连接：
			验证是否有正确的内部结构，并且和其他类协调一致
			准备 负责为类的静态成员分配内存，并设置默认的初始化值
			解析 将类的二进制数据中的符号引用替换为直接引用
		初始化：成员变量的初始化，构造器，成员方法的初始化
	类加载的时机
		创建类的实例
		访问类的静态变量，或者为类的静态变量赋值的时候
		调用类的静态方法的时候
		使用反射方式强制创建某个类或者接口对应的java.lang.Class对象
		初始化某个类的子类(子类可能要用到父类的东西，优先加载父类)
		直接使用java.exe命令来运行某个主类
	类的加载器的组成
		根类加载器：也称为引导类加载器，负责Java核心类的加载，如System String类等，在JDK中JRE的lib目录下rt.jar文件中
		扩展类加载器：负责JRE扩展目录中jar包的加载，JDK中JRE的lib目录下ext目录下面
		系统类加载器：负责JVM启动时加载来自java命令的class文件，以及classpath环境变量所指定的jar包和类路径

Java的反射机制：通过class文件对象，去使用该文件中的成员变量，构造器，成员方法
要想使用，首先要得到class文件对象，也就是得到class类的对象，所有.class文件都是Class类的类对象
每个class文件都对应一个class文件对象(将class文件封装成class文件对象)，就像某个具体的文件用一个File类对象来表示，每个IP地址用InetAddress对象来表示
相同的每个class文件都可以用一个Class对象来表示
Class类：Class类包含以下三方面的东西，而每一个都是一个类来表示
	成员变量	Field
	构造方法	Constructor
	成员方法	Method
	获取class文件对象的方式：	Object类中的getClass()方法
								类的静态属性.Class
								Class类下面的forName()方法
	一个类可以new出多个对象，但是他的class文件只有一份
/* Java反射机制运用实例
 * 新建一个class.txt配置文件，里面是键值对，类的全名，方法名
 * 通过Properties类将其加载到代码中
 * 根据加载的类名以及方法名，创建类的class文件对象，通过class文件对象，获取类的构造器对象
 * 通过构造器对象，创建类的相应地实例，通过class文件对象获取类的Method对象，或者获取属性成员Field
 * 当代码需要进行修改的时候，直接修改配置文件中的值就行了

/* 通过类全名获得class文件对象,通过class文件对象得到构造器，通过构造器创建类的实例，
	* 这里注意获取私有构造器，要用getDeclaredConstructor()，该方法传进几个参数，创建类的实例的时候
	* 方法的参数要和前者保持一致，也就是说，获取无参构造器，不能通过它创建带参数的实例，同样获得的带参构造器
	* 不能创建无参的实例，通过class文件对象得到Method对象，通过method对象调用实例方法
	* 这里通过class文件对象可以获得私有带参数的构造器，私有带参数的method对象

Java模板设计模式：
就是定义一个算法的骨架，将具体算法的经常变动的延迟到子类中来实现，将变动的封装成抽象类，用的时候直接重写这个抽象方法即可
优点：在定义算法骨架的同时，可以根据需要很灵活的实现具体的算法，满足用户多变的需求
缺点：算法骨架需要修改的话，需要修改抽象类

线程中断
		QThread mthread = new QThread("我的线程");
		Thread th = new Thread(mthread);
		th.start();  				 //此处启动th线程，该线程执行时间是10S，在当前线程中调用th.interrupt(),当前线程执行2S，就会将th线程终止
		try{
			Thread.sleep(2000);
			th.interrupt();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
线程不安全的原因：多线程环境(即有多个线程操作数据)、共享数据(操作的数据是被多个线程共享的)、多条语句操作共享数据
(即对共享数据的操作不是一部完成的，也就是不是原子性的)，这些会导致线程不安全，那么我们解决的办法就是将操作共享数据的多条语句用
同步代码块包起来，实现线程安全的原理是保证在同步代码块中只有单一的一个线程操作共享数据，也就是不会出现一个线程正在操作的时候，
突然又被另外一个线程操作，执行权被抢走了，导致数据该加的没有加，该该减的没有减等
(即使有多个线程抢占执行权，即使同步代码块中有许多条语句，也就是操作不是原子性的)
其实实现很简单，只需要将操作共享数据的多条语句加锁就可以了，多个线程对加锁的共享数据进行操作，在同步代码块中只允许一个线程进行操作
		class SaleTicketDemo implements Runnable{
			private int ticket = 100;
			private Object obj = new Object();
			@Override
			public void run() {
				//这里th1 th2 th3三个线程都能走到这里，假设其中某个线程抢到了执行权，就会进入同步代码块中
				//Thread.sleep(20);走到这里线程会睡眠，这时执行权会被其他线程抢到，但是其他线程发现被上锁了，无法进入
				//那么之前抢到执行权的线程就会醒过来接着执行，也就是说保证在同步代码块中只有一个线程在操作共享数据，那么这样就保证了
				//线程的安全，该线程走出同步代码块后，th1 th2 th3三个线程会再次抢夺CPU执行权，接着只有一个线程会在同步代码块中执行
				while(true){
					synchronized(obj){
						if(ticket > 0){
							try{
								Thread.sleep(20);
							}
							catch(Exception e){
								e.printStackTrace();
							}
							System.out.println(Thread.currentThread().getName() + "正在售卖第" + ticket-- + "张票");
						}
					}
				}
			}
		}
		为接口增加泛型
		class ThreadSum implements Callable<Integer>{  //为接口增加泛型
			
			@Override
			public Integer call() throws Exception {
				return null;
			}
		}
字符串的分割：
public class Test {
// "." 和 "|" 都是转义字符，必须得加"\\"
// 如果有对多个分割标志进行分割，中间split()参数就是A|B的形式
// str.split("\\||\\.|#") 分割条件是"." 或 "|" 或 "#"
// str.split("\\||\\.");  "." 或 "|"
// strtest.split("\t");   以tab作为分割标志
	public static void main(String[] args) {
		String str = "hello|my|old friends|how.are.you.recently.1212#122#4444";
		System.out.println(str);
		String[] strlist = str.split("\\||\\.|#"); // "." 或 "|"
		for(String s : strlist){
			System.out.println(s);
		}
		System.out.println("--------------------");
		String strtest = "1501120508	2016-06-21 17:15:43	2016-06-21 17:31:44	10.175.129.22" +
				"	8.13M	0时16分1秒";
		String[] strlist0 = strtest.split("\t");  //以tab作为分割标志
		for(String s : strlist0){
			System.out.println(s);
		}
	}
}

关于子类继承父类
在Java继承里，父类的属性还有方法在声明时，如果是public关键字即公共属性，则在子类继承时，这些属性和方法都会被子类继承。
受保护的也可以继承,但是私有的类属性成员和方法则无法继承。
    1.子类继承父类的成员变量
　　当子类继承了某个类之后，便可以使用父类中的成员变量，但是并不是完全继承父类的所有成员变量。具体的原则如下：
　　1）能够继承父类的public和protected成员变量；不能够继承父类的private成员变量；
　　2）对于父类的包访问权限成员变量，如果子类和父类在同一个包下，则子类能够继承；否则，子类不能够继承；
　　3）对于子类可以继承的父类成员变量，如果在子类中出现了同名称的成员变量，则会发生隐藏现象，
	即子类的成员变量会屏蔽掉父类的同名成员变量。如果要在子类中访问父类中同名成员变量，需要使用super关键字来进行引用。
　　2.子类继承父类的方法
    同样地，子类也并不是完全继承父类的所有方法。
　　1）能够继承父类的public和protected成员方法；不能够继承父类的private成员方法；
　　2）对于父类的包访问权限成员方法，如果子类和父类在同一个包下，则子类能够继承；否则，子类不能够继承；
　　3）对于子类可以继承的父类成员方法，如果在子类中出现了同名称的成员方法，则称为覆盖，
	即子类的成员方法会覆盖掉父类的同名成员方法。如果要在子类中访问父类中同名成员方法，需要使用super关键字来进行引用
子类继承父类并不是完全继承父类的所有成员，而是会根据访问权限进行有选择的继承
子类继承父类，子类继承的，父类所有的(子类中是继承自父类的，父类中是原有的)，若子类成员名和父类相同就会将父类隐藏或者覆盖掉
此时访问的就是子类中的成员，若要访问父类中的成员，就用到super关键字，并不是将父类隐藏或者重写了，父类中的成员就不存在了

Java反射机制中两个方法
但是用反射机制怎么没法查看子类继承自父类中的成员呢？？？？？
因为getDeclaredMethods()可以返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，
包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。注意是不包含继承的方法
同样getDeclaredFields()返回 Field 对象的一个数组，但不包括继承的字段

来看一个ArrayList java.util包中下面的一个方法：
protected  void removeRange(int fromIndex, int toIndex) 作用是移除列表中索引在 fromIndex（包括）和 toIndex（不包括）之间的所有元素
但是前面的访问控制符是protected权限的
protected权限要么其他类和ArrayList类定义在同一个包中，这样可以相互访问，要么该类继承ArrayList类，不同包中的子类访问
很显然用第二种方式，不能在java.util这个包中在定义一个其他的类
更新至2016年7月22日12:20:32