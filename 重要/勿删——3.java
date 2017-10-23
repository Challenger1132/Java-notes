/*
####将这些总结完毕添加到不定期更新中####
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
<important>
1、Java  IO
public class File extends Object implements Serializable,Comparable<File> 
文件和目录路径名的抽象表示形式File类继承了Object类,实现了Serializable,Comparable<File>接口
<字节流>
FileInputStream fis = new FileInputStream("filepath...");
ch = fis.read();  // 返回值为int,读取一个字节，并返回
int len = fis.read(byte[] buff, int offset, int length); //从字节输入流中读取字节，从offset开始，每次最多读取length个，并返回实际读取的字节数
int len = fis.read(byte[] buff); //从此输入流中将最多 buff.length 个字节的数据读入一个字节数组中
FileOutputStream fos = new FileOutputStream("filepath...");
fos.write(int ch); //无返回值，将指定字节写入
fos.write(byte[] buff, int offset, int len);  //将字节数组buff总的数据从offset开始，每次写入len个字节到字节流中
fos.write(byte[] buff); //将 buff.length 个字节从指定字节数组写入此文件输出流中
带有缓冲区的字节流
BufferedInputStream bis = new BufferedInputStream(new FileInputStream("filepath..."));
ch = bis.read();  // 返回值为int,读取一个字节，并返回
int len = bis.read(byte[] buff, int offset, int length); //从字节输入流中读取字节，从offset开始，每次最多读取length个，并返回实际读取的字节数
BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filepath...));
bos.write(int ch); //无返回值，将指定字节写入
bos.write(byte[] buff, int offset, int len);  //将字节数组buff总的数据从offset开始，每次写入len个字节到字节流中


<字符流>
FileReader fr = new FileReader("filepath...");
int len = fr.read(char[] cbuf, int offset, int length);  // 将读取的内容放到字符数组中，返回实际读取的字符数
ch = fr.read();  //返回实际读取的字符
FileWriter 和 FileOutputStream 具有相同的方法，字节数组--->字符数组
带有缓冲区的字符流
BufferedReader br = new BufferedReader(new FileReader("filepath..."));
ch = br.read();  // 返回值为int,读取一个字节，并返回
int len = br.read(char[] cbuf, int offset, int length);  // 将读取的内容放到字符数组中，返回实际读取的字符数
String str = br.readLine();  //读取一个文本行 
BufferedWriter bw = new BufferedWriter(new FileWriter("filepath..."));
bw.write(int ch); //无返回值，将指定字符写入
bw.write(char[] buff, int offset, int len);  //将字符数组buff中的数据从offset开始，每次写入len个字符到字符流中
bw.newLine();  //写入一个行分隔符

打印流
PrintStream ps = new PrintStream("C:/Users/xingyue/Desktop/算法复杂度.txt");//将文件写到具体的文件路径下的文件中
PrintStream ps = new PrintStream(System.out);   //将文件写到屏幕
		注意这里可以是任何形式的文件
ps.write(byte[] buf, int off, int len);  //将 len 字节从指定的初始偏移量为 off 的字节数组写入此流
ps.write(int b); // 将指定的字节写入此流

PrintWriter pw = new PrintWriter("C:/Users/xingyue/Desktop/算法复杂度.txt");//将文件写到具体的文件路径下的文件中
PrintWriter pw = new PrintWriter(System.out);	//将文件写到屏幕
		注意这里是文本文件
pw.write(char[] buf, int off, int len); // 写入字符数组的某一部分
pw.write(int c);  // 写入单个字符
pw.write(String s, int off, int len); //写入字符串的某一部分
.........

pw.write(byte[] buf, int off, int len);  //将 len 字节从指定的初始偏移量为 off 的字节数组写入此流
pw.write(int b); // 将指定的字节写入此流

转换流
BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); /将 InputStream 流转化为 Reader 流
首先 InputStream ---- Reader    OutputStream ---- Writer是字节流与相对应的字符流  
	OutputStreamWriter是将OutputStream流转化为Writer流OutputStreamWriter类的形参需要OutputStream流
	InputStreamReader是将InputStream流转化为Reader流,InputStreamReader类的形参需要InputStream流
	OutputStreamWriter和InputStreamReader都是包裹流
	System.in 是InputStream流，System.out 是 OutputStream流
将键盘输入的System.in(InputStream流)赋予字符串BufferedReader流(Reader流)
需要将InputStream流转化为Reader流就要用InputStreamReader流

2、static关键字思考
数据类型包括基本数据类型和引用数据类型(类类型、接口类型、数组)
A aa = new A(); new A()是在堆空间中开辟的一块儿存储空间，aa存储在栈中，是指向堆空间的引用
aa(指针变量,所占空间是在栈中分配的)和newA()(所占空间是在栈中分配的)在不同的存储空间
aa本身并没有i和j成员,aa.i代表aa这个引用所指向的堆里面的真正的内存空间中的i成员
对于类，每new出一个对象，就会在堆空间中开辟一块存储空间，若类多个对象具有相同的成所员变量值，可以用关键字
static修饰，设置为静态成员，可以节省存储空间
static：随着类的加载而加载，优先于类对象存在，被类的所有对象共享，可以通过类名类对象名的方式访问
静态成员只能该访问静态成员，非静态成员，可以访问非静态成员，和静态成员

所属不同：静态变量属于类，称为类变量，非静态变量属于类对象，称为实例变量
存储位置不同：静态变量存在方法区的静态区，实例变量存储于堆内存中
加载时间不同：静态变量随着类的加载而加载，消失而消失，实例变量随着类对象的生成而存在，随着对象消失而消失


3、抽象类和接口
抽象类是对事物的抽象，接口是对行为的抽象
为什么类只允许单继承，却可以实现多个接口？类是对事物的抽象，一个类属于这个事物，便不能再属于其他事物
(属于动物的便不能再属于植物)，但是接口就是为了让其他类实现而存在的，是对行为的抽象，一个类具有这个行为(属性),
还可以具有其他的行为(属性)因此类只能单继承，却可以实现多个接口
抽象类由于本质上是一个类，所以可以包含实例变量，也可包含方法的实现，但是接口是为了让其他类实现而存在，
作为一种规范就要”公开透明“，只能存在抽象方法，public权限，接口更像一个“标签”
对于接口当向其中添加一个新的方法，那么实现了这个接口的类都需要做相应地改变，而对于抽象类，
我们向其中添加非抽象方法，不需要它的派生类做任何改动
接口与抽象类的一个本质区别是，抽象类只是一种含有抽象方法的类。也就是说，抽象类是对常规类的进一步”抽象“
常规类是可以直接实例化的，因为它所定义的方法都有了明确的实现，也就是说相应类对象的行为应经能够完全确定下来。
而抽象类包含抽象方法，也就是说对象的一部分行为还没有完全确定下来。可以把抽象方法也理解成一种需求描述，
因为抽象类存在着”待解决的需求“，所以它不能实例化，只能先派生出子类，解决了这个需求后(也就是实现了抽象方法)
从而把实例的整个“蓝图”确定下来，才能够实例化,比如说抽象类People中定义一个write抽象方法，
因为写这个动作不同人可以选择不同的方式完成(比如有的人是左撇子，拿笔姿势各不相同)因为抽象类是一种类，
所以一个类只能继承一个抽象类，而不能像接口那样可以“多继承”。

4、回调设计模式思考
	事件处理相关概念
  	事件：用户对组件的操作称为事件，例如一次点击就是一个事件，一次滑动也是一个事件 
  	事件源：能够产生事件的GUI对象，例如按钮，滑动条，单选框，复选框等
  	事件处理方法：能够接受、处理和解析事件的类对象，实现与用户的交互的方法
  	事件监听器：可以处理事件的一个类
	事件处理的过程：得到代表控件的对象aa;写一个类实现某个监听器接口，重写里面的事件处理方法，
并生成实现了监听器接口的类对象将该对象发送给控件的设置监听器方法的形参，当触发相应地控件的时候
会自动调用相应的事件处理方法

回调是一种设计模式，利用这种模式，我们可以指出在某个事件发生时应该采取什么动作

例如，Java类库中有个Timer类，可以使通过它在过了指定的时间后发出通知，就像一个闹钟一样，
初始化Time类时，需要指定一个时间和到达这个时间后要执行的动作。告诉Timer要执行的动作就是通过传递给它一个对象，
然后到达指定时间后，Timer会调用这个对象的方法。Timer要求我们传递给它的对象需要实现ActionListener接口

5、函数的递归：
被调函数运行的栈空间独立于调用函数的栈空间，所以与调用函数之间的数据也是无关的，
函数之间靠参数传递和返回值来联系，函数看作为黑盒，递归之所以能实现，是因为函数的每个
执行过程都在栈中有自己的形参和局部变量的拷贝，这些拷贝和函数的其他执行过程毫不相干

6、构造器，以某种方式生成某个类的类对象
String str = new String();
String str = String(byte[] bytes);
String str = String(StringBuffer buffer) ; 等等，以不同的方式创建String类对象，体现了函数的重载
String类的类对象str可以调用其他的方法

7、访问同一个类，回调模式
/* 回调设计模式，当用户产生某个事件的时候，与事件源相关的监听类类对象就会自动调用类内部的相关的事件
 * 处理方法，对事件进行处理，某个类实现监听接口，重写里面的时间处理方法，生成该类的类对象，将该类对象
 * 传递给事件源的监听设置方法
 * 等待设置的时间之后，就会打印当前时间，
 * 类A、B、C,B、C 都要访问类A中的成员，在B类中生成类A的类对象从而访问类A中的属性与方法，如果在类C中
 * 要“用”在B类中生成的那个A类类对象，那么在C类中生成B类的引用，创建C类的构造方法，在其他类中用到B C类，
 * 给C类传递B类类对象，通过C类的构造方法，将这个传递的类对象赋予在C类中生成的B类的引用，那么通过这个引用
 * 就可以访问在B类中创建的A类类对象，且B C 访问的是同一个A类类对象
 * class A{
	//....
}
class B{
	public A aa;  // A类对象是类B的属性 
	public void f(){
		aa  = new A();
		aa.start(); // A类下的某个方法
	}
}
class C{
	public B bb;  // 在C类中生成B类的引用
	public C(B bb){  // C类的构造方法，将传进来的形参bb赋予C类的属性bb
		this.bb = bb;  
	}
	public void g(){
		bb.aa.cancel(); // 在C类中也可以访问在B类中生成的A类对象aa,调用A类下的方法aa.start()--aa.cancel();
	}
}
class Test{
	B bb = new B();  // 在其他类中生成B C 类的类对象
	C cc = new C(bb);  // 将B类对象bb传递给C类构造器，此时记忆可以访问在B类中的“成员”
	//.....
}
8、main()方法是静态的，不需要创建对象，直接通过类名就可以访问，方便JVM调用
9、提供按钮设计原则
创建类对象的时候将成员将所有的对象访问权限设置为private，只提供getXXX()、setXXX()方法共外部类调用、
我们创建了一个数组类，将分数组相关的操作方法，都写到这个类中，封装
我们创建一个类，将里面的方法都设为静态方法，外界可以通过类名类对象名的方式进行访问，如果只允许外界通过类名的
方式进行访问，不能new出类对象进行访问，那么将该类的构造方法私有设置为private权限，这个时候通过类对象的方式进行访问
即私有构造方法，静态化所有方法，联系Collections类的实现
就会提示：The constructor PrintArr_1() is not visible  //不可见

10、
			public class Test {
				public static void main(String[] args) {
					Integer[] arr = {1,2,3,4,5};
					PrintArr<Integer> pa = new PrintArr<Integer>();  //生成泛型类对象
					PrintArr.printArray(arr);
				}

			}
			class PrintArr<T>{  //创建泛型类  类名后面要加 <T>
				public static <T> void printArray(T[] arr){  //注意泛型方法之前也要加 <T>
					for(int i = 0;i < arr.length;i ++){
						System.out.println(arr[i]);
					}
				}
			}
11、创建Java帮助文档
javadoc -encoding utf-8 -d C:\Users\xingyue\Desktop -author -version ArrTool.java
编译完成，输出如下：
正在加载源文件ArrTool.java...
正在构造 Javadoc 信息...
标准 Doclet 版本 1.8.0_66
正在构建所有程序包和类的树...
正在生成C:\Users\xingyue\Desktop\practice\ArrTool.html...
正在生成C:\Users\xingyue\Desktop\practice\package-frame.html...
正在生成C:\Users\xingyue\Desktop\practice\package-summary.html...
正在生成C:\Users\xingyue\Desktop\practice\package-tree.html...
正在生成C:\Users\xingyue\Desktop\practice\constant-values.html...
正在构建所有程序包和类的索引...
正在生成C:\Users\xingyue\Desktop\practice\overview-tree.html...
正在生成C:\Users\xingyue\Desktop\practice\index-all.html...
正在生成C:\Users\xingyue\Desktop\practice\deprecated-list.html...
正在构建所有类的索引...
正在生成C:\Users\xingyue\Desktop\practice\allclasses-frame.html...
正在生成C:\Users\xingyue\Desktop\practice\allclasses-noframe.html...
正在生成C:\Users\xingyue\Desktop\practice\index.html...
正在生成C:\Users\xingyue\Desktop\practice\help-doc.html...
12、( ⊙o⊙ )?
Java.lang下面的包使用不需要导入
代码块的作用
继承的作用：提高代码的复用性，类与类之间的产生关系，是多态的前提，易于维护
弊端：类之间的耦合性增强
设计原则：低耦合，高内聚
子类继承父类，子类只能继承父类的非私有权限的成员，父类构造方法不能被继承，但是可以通过super语句访问父类成员
子类中所有构造方法执行前都会默认先执行父类的无参的构造方法，(注意是子类中所有的构造方法)
因为子类继承父类中的成员，可能会使用父类中的成员，子类初始化之前一定要先完成父类的初始化，
实际上子类每一个构造方法第一条语句都默认是super();调用父类的空参构造方法
但是父类如果没有无参的构造方法，就会报错(例如不写父类的无参构造方法，就会默认有一个无参的构造方法，一旦写了有参数
的构造方法，父类无参的构造方法就会不存在，子类继承父类编译会报错)

当父类没有无参的构造方法的时候，如何解决呢？
为父类添加一个无参数的构造方法
可以通过super语句显式的调用父类有参的构造方法，注意子类中所有的构造方法  都  要显示的调用父类有参数的构造方法
通过this()，调用本类中的其他构造方法 无论如何，子类中总得有一个去访问父类中的构造方法，否则父类对象无法进行初始化
只能在子类构造方法中通过super语句调用父类的构造方法  super语句只能是第一条语句

静态代码块，构造代码块，构造方法的执行顺序  静态代码块是随着类的加载而加载，优先获得执行，
子类初始化之前先进行父类的初始化



异常： 当try{}里面的语句出现问题，JVM就会生成一个异常类对象，和catch里面的异常对象
进行匹配，一旦匹配成功，就会执行catch里面的处理代码
throw  在某个方法中，后面跟具体的异常类对象，且只能跟一个
throws 在方法外，用于提示调用该方法的类对象，本方法可能会抛出异常，后面跟的是异常类，可以是多个
finally一定会执行，但是，在finally之前，JVM退出了，就不会得到执行了，finally用于释放资源
在IO流和数据库操作经常使用
final 最后的最终的，用于修饰类(不能被继承)，类的成员变量(是常量)，成员方法(方法不能被重写)
finally finalize(是Object类下面的一个方法，用于垃圾回收)

File类：文件或目录路径的抽象表示形式

方法调用本身叫做递归
递归一定要有出口，否则就是死递归
递归次数不能太多，否则就会内存溢出
构造方法不能递归

流对象的close()方法的作用
让流对象变成垃圾，以便于GC
通知系统去释放与该文件相关的资源

不同的系统对回车识别方式是不同的，
Linux : \n  
window : \r\n
Mac : \r

[97, 98, 99, 100, 101]  英文字符转化为字节数组
[49, 50, 51, 52, 53]  数字转化为字节数组
[-50, -46, -80, -82, -60, -29, -42, -48, -71, -6]  汉字转化为字节数组
计算机中是如何识别中文汉字的:中文存储分两个字节，第一个字节一定是负的，第二个为正或为负

常见DOS命令：
D:回车  盘符切换
dir  XX  列出当前目录下文件或文件夹
md XX  创建XX目录
rd XX  删除XX目录
cd XX  进入XX目录
cd ..  返回上一级目录
cd \   返回根目录
del XX 删除XX文件   删除某后缀名文件 *.txt
cls    清屏
exit   退出DOS

为什么带有汉字的文件用字节流输出操作显示会出现乱码？   用字节流操作字符文件不太方便
英文字符占一个字节，用字节流可以方便的输出，显示，但是汉字占两个字节，
但是我们输出的时候是逐个字节进行输出的，也就是将汉字拆开进行输出，且汉字映射的两个
数字是负的，因此输出是乱码
第二种方式，每次读取len个字节，将len个字节封装成字符串进行输出
但是在1023个字节出恰好有汉字出现，这个时候汉字还会被拆开显示，可能会出现乱码
因此用字节流操作字符文件不太方便，所以出现了转换流
注意流一旦被关闭，其占据的资源就会被释放，不能再用关闭之后的某个流对象，对文件进行操作
如果想再次对文件操作，就要重新建立流的连接，否则会抛出异常

OutputStreamWriter 写数据的五种方式：写一个字符、写字符数组、字符数组的一部分、写字符串、字符串的一部分

close()  flush()的区别
close 是关闭流对象，但是先刷新缓冲区，关闭之后，流不能继续使用
flush 仅仅刷新缓冲区，刷新之后，流对象还能继续使用，当数据量比较小的时候，只用close就能搞定，
但是当数据量比较大的时候，就在写数据的过程中进行flush，写完之后关闭

字节流操作字符不是很方便，因此出现了转换流，字符流相当于字节流 + 编码表
编码　string ----> byte[]  字符串转化为字节数组
解码  byte[] ----> String   字符数组转化为字符串
转换流的构造方法  	OutputStreamWriter(OutputStream os)  使用本机默认的编码表
					OutputStreamWriter(OutputStream os,String charSetName)  使用指定的编码表 相应地有InputStreamReader()
IO流
		|--字节流
				|--字节输入流
					|--InputStream
							int read(int by) : 一次读取一个字节
							int read(byte[] buff) : 一次读取一个字节数组
							|--FileInputStream 
							|--BufferedInputStream
				|--字节输出流
					|--OutputStream
							void write(int by) : 一次写入一个字节
							void write(byte[] buff,int offset,int len) : 一次写入一个字节数组
							|--FileOutputStream
							|--BufferedOutputStream
		|--字符流
				|--字符输入流
					|--Reader
							int read() : 一次读取一个字符 
							int read(char[] buff) : 一次读取一个字符数组
							|--InputStreamReader
									|--FileReader 上面的简化
							|--BufferedReader  高效读写
								String readLine() : 一次读取一行
				|--字符输出流
					|--Writer
							void write(int ch) : 一次写入一个字符
							void write(char buff,int offset,int len) : 一次写入一个字符数组
							|--OutputStreamWriter
									|--FileWriter  上面的简化
							|--BufferedWriter  高效读写
								void newlLine() : 写入换行符
								void write(String line) : 一次写入一个字符串

关于函数的返回值
	public String readLine() throws IOException{
		StringBuilder sb = new StringBuilder();
		int ch = 0;
		while((ch = r.read()) != -1){
			if(ch == '\r')
				continue;
			if(ch == '\n')
				return sb.toString();
			else
				sb.append((char)ch);
		}
		if(sb.length() > 0)
			return sb.toString();
		else
			return null;
	}
	在上面的方法中，while循环体外面要加上返回值语句，以为函数执行到while循环的时候
	可能不满足天剑，无法进入while循环体内部，这个时候函数就没有返回值语句，一次在while循环体外部要加上返回值语句

protected  void finalize() 
         当垃圾回收器确定不存在对该对象的更多引用时，由对象的垃圾回收器调用此方法，用于垃圾回收，但是什么时候垃圾回收不确定 
protected  Object clone() ：创建并返回此对象的一个副本，要实现某个类对象的clone,必须实现Cloneable接口
public interface Cloneable
此类实现了 Cloneable 接口，以指示 Object.clone() 方法可以合法地对该类实例进行按字段复制。 
如果在没有实现 Cloneable 接口的实例上调用 Object 的 clone 方法，则会导致抛出 CloneNotSupportedException异常。
此外还有Serializable等，此类接口中没有抽象方法方法，是标记接口，实现了该接口的类不用重写抽象方法
接口增加了许多类都需要实现的功能，某个类实现了某些接口，就拓展了该类的功能，使这些类具有了接口的功能，具有了接口的性质

2016年4月22日10:17:44



*/