	代码要注意书写的由结构
	1、这里涉及代码书写风格的问题，可以创建MultipleThread类的类对象，用该类对象调用getFileList()方法
 	或者将getFileList()方法的调用直接写在构造器里面
 		public MultipleThread(File srcFolder, File desFolder){
			getFileList(srcFolder);
			this.desFolder = desFolder;  //该构造器不但调用了getFileList()方法，还不忘初始化了成员desFolder
		}
	当创建类对象的时候直接调用getFileList()方法，因此一些需要调用
          的方法可以写在构造器里面，之后便不用显式的进行调用，此外还要注意有参的构造器，无参数的构造器，其他方法的
          有参形式以及无参形式，也就是方法的重载
    2、定义了一个接口,里面是常量，在另一个类中可以引用,例如
		public interface Constants {
			public final static String SRC_PATH = "C:/Users/xingyue/Desktop/测试";
			public final static String DES_PATH = "C:/Users/xingyue/Desktop/默认";
			public final int POOL_SIZE = 2;
		}
		另一个类中引用
			private final String srcpath = Constants.SRC_PATH;
			private final String despath = Constants.DES_PATH;
          在类中定义 private static final String PATH = "FFF";等成员
    3、构造器有参，无参的形式，在构造器中调用其他方法，在之后便不用显式的进行方法的调用
    4、 如果需要线程对象的时候，可以创建线程类，也可以创建返回值为Runnable对象的方法，例如
          private ExecutorService threadpool = Executors.newFixedThreadPool
          (Runtime.getRuntime().availableProcessors()*POOL_SIZE);
          threadpool.execute(sendFile(f));
          execute()方法的参数需要传进来Runnable接口对象，可以创建线程类
			class ThreadDemo implements Runnable{
				.....
			    @Override
			    public void run() {
					
			    }
			}
			或者创建返回值为Runnable的对象
				public Runnable sendFile(File file){
					return new Runnable() {  //这其实是方法的返回值是一个匿名类对象，是类对象，
						....				//在该对象中可以有属性，方法等，按照类的书写方式进行写就好
						@Override
						public void run() {
						}
					};
				}
			调用方式为：threadpool.execute(new Thread(new ThreadDemo(f)));
			threadpool.execute(new ThreadDemo(f));
			threadpool.execute(sendFile(f));
			或者匿名类来实现
			threadpool.execute(new Runnable(){
				@Override
				public void run() {
				}
			});

关于代码书写注意结构
	/* 代码书写注意结构
	 * 数据传输大致过程如下
	 * Client类：创建Client类对象，会执行该类构造器，在构造器中会执行getFileList()完成文件夹文件
	 * File对象的获取，存储到fileList中，完成fileList初始化，Client类对象调用该类中launch()方法
	 * 该方法中线程池对象调用execute()方法，传进来一个线程对象，每传输一个File对象就会开辟一个新的线程，
	 * 每个文件会开一个新的线程进行传输，而文件传输的代码封装在线程类中，该线程类接收一个File类对象，
	 * 对该对象进行传输
	 * Server类：创建Server类对象的时候就执行构造方法，构造方法中会执行bindToPort()方法，完成ServerSocket
	 * 的初始化，Server类对象调用该类中launch()方法，在该方法中ServerSocket监听客户端的Socket对象
	 * 将接收到的Socket对象传给线程类Task，该线程类作为线程池对象调用execute()方法的参数来执行，对于发送端的
	 * 每一个Socket对象都会开辟一个新的线程来处理
	 * 
	 * 对于文件的发送关键是得到Socket对象，得到该对象的输入，流输出流，从其他地方读取数据，写到Socket对象的
	 * 输出流中(貌似将所有数据都写入，写入文件的文件名 dos.writeUTF(file.getName());写入文件的长度
	 * dos.writeLong(file.length());然后继续写入其他数据，但是这之间怎么进行区分的？？呵呵)
	 * 在接收端获取该Socket对象，获得输入流，输出流对象从该对象的输入流中读取数据
	 * (读取文件名filename = dis.readUTF();读取文件长度length = dis.readLong()，继续读取其他数据)
	 * 将数据写到其他地方
	 * 
	 * 流的其嵌套构造
	 * 	DataInputStream dis = new DataInputStream(new BufferedInputStream
							(new FileInputStream(new File(filename))));
		DataOutputStream = new DataOutputStream(new BufferedOutputStream
							(new FileOutputStream(new File(filename))));
	多线程小结，创建线程类穿件来某个参数，比如发送文件就传进来一个File对象，run()方法中是对File对象的操作，在其他类中
	可以通过传进来不同的参数，或者线程类执行多次达到多线程处理的目的
	开辟线程池，execute()方法中传递线程类对象，也可以多线程处理
	*/

关于String StringBuffer StringBuilder Integer 等
	public int capacity()  返回缓冲区大小 
	public int length() 返回字符串长度
	public StringBuffer append(String str);
	StringBuffer sb = new StringBuffer(); 默认空间是16个字节的字符串缓冲区
	StringBuffer(CharSequence csq);  //是CharSequence的实现类都可以传进来
	StringBuffer(int capacity);  //指定缓冲区大小
	StringBulider和StringBuffer方法都一样，但是不保证同步，在单个线程的建议使用StringBulider
	String、StringBuffer、StringBulider的区别
	String  中内容不可变的，StringBuffer、StringBulider内容可变
	StringBuffer 是线程安全的，效率较低，StringBulider 效率高，但不保证同步
	该类被设计用作 StringBuffer 的一个简易替换，在大多数实现中，它比 StringBuffer 要快
	对于基本数据类型形参的改变不影响实际的参数
	对于引用数据类型，形参的改变会直接影响实参
	字符串是常量值，字符串是一种特殊的引用类型，只能将它视为基本类型来看，形参的改变不影响实际参数
	因为String是一种特殊的引用类型，String作为形参传递，可以视为基本类型来看待
	Arrrays是对数组进行操作的工具类，里面的方法都是静态方法
	Arrays.toString(arr);   Arrays.sort(arr);  Arrays.binarySearch(arr, 9);

			//自动装箱拆箱是给编译器看的真正Java虚拟机执行的时候是下面的代码
			Integer i = 5;
			i += 5;
			System.out.println("i = " + i);
	//		Integer i = Integer.valueOf(5);
	//		Integer i = Integer.valueOf(i.intValue() + 5);
	//		System.out.println(new StringBuilder("i = ").append(i).toString());
	从-128到128之间的数做了一个数据缓冲池，如果是该范围内的数据，就不创建新的对象，直接从缓冲池里面获取，否则创建新的对象
			Integer a1 = new Integer(4);
			Integer a2 = new Integer(4);
			System.out.println(a1 == a2);  //false
			System.out.println(a1.equals(a2));
			
			Integer b1 = 127;
			Integer b2 = 127;
			System.out.println(b1 == b2);  //true
			System.out.println(b1.equals(b2));
			
			Integer c1 = 128;
			Integer c2 = 128;
			System.out.println(c1 == c2);  //false
			System.out.println(c1.equals(c2));
选择排序以及冒泡排序、折半查找以及二分查找
	public class Test {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int cnt = sc.nextInt();
		int[] arr = new int [cnt];
		for(int i = 0;i < cnt;i ++){
			arr[i] = sc.nextInt();
		}
		System.out.println("数组输出 = " + Arrays.toString(arr));
		System.out.println("冒泡排序 = " + Arrays.toString(Test.bubbleSort(arr)));
		System.out.println("选择排序 = " + Arrays.toString(Test.selectSort(arr)));
		System.out.println(Test.search(arr, arr[4]));
		System.out.println(Test.midSearch(Test.selectSort(arr), 4));
	}
	//冒泡排序的思想是相邻元素两两进行比较，将大的往后放，第一次比较完毕，最大的元素在最后的位置
	public static int [] bubbleSort(int[] arr){
		int temp = 0;
		for(int i = 0;i < arr.length - 1;i ++){
			for(int j = 0;j < arr.length - 1 - i;j ++){
				if(arr[j] > arr[j + 1]){
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}
	//选择排序：选定元素与该元素之后的所有元素进行比较，如果该元素之后的元素有小于选定元素的进将该元素和选定元素进行交换
	// public static int[] selectSort(int[] arr){
		int temp;
		for(int i = 0;i < arr.length - 1;i ++){
			for(int j = i + 1;j < arr.length;j ++){
				if(arr[j] < arr[i]){
					temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
		}
		return arr;
	}
	//数组的基本查找
	public static int search(int[] arr, int value){
		int index = -1;
		for(int i = 0;i < arr.length;i ++){
			if(arr[i] == value){
				index = i;
				break;
			}
		}
		return index;
	}
	//二分查找，折半查找，只能对已经排好序的数组进行查找
	public static int midSearch(int[] arr, int value){
		int min = 0;
		int max = arr.length - 1;
		int mid = (min + max)/2;
		while(arr[mid] != value){
			if(value < arr[mid])
				max = mid - 1;
			else
				min = mid + 1;
			if(min > max)
				return -1;
			mid = (min + max)/2;
		}
		return mid;
	}
}

正则表达式初探 判断、分割、替换，
	boolean matches(String regex); 通知此字符串是否匹配给定的正则表达式
	String[] split(String regex);  根据给定的正则表达式的匹配来拆分此字符串
	public static boolean checkMail(String mail){
		//正则表达式 [a-zA-Z_0-9]表示单词字符
		// [a-zA-Z_0-9]+单词字符出现不只一次
		// @代表字符 @   .用 \\.表示
		// ([a-zA-Z_0-9]+){2,6} 表示单词字符出现不只一次，且位数是2-6位
		// ([a-zA-Z_0-9]{2,3})+ 单词字符位数限定2-3位，且出现不止一次
		String regular = "[a-zA-Z_0-9]+@([a-zA-Z_0-9]+){2,6}(\\.[a-zA-Z_0-9]{2,3})+";
		return mail.matches(regular);
	}
		//分割磁盘路径,磁盘路径是双反斜杠"\\",表达式中表示双反斜杠就用4个人反斜杠"\\\\"
		String path = "C:\\Users\\xingyue\\Desktop\\practice"; 
		String[] strpath = path.split("\\\\");
		//分割该字符串不能用"."，这表示任意字符，应该用"\\."
		String str2 = "abc.abc.sds.sd.ere";
		String[] strdemo = str2.split("\\.");
		//分割含有多个空格的字符串 "空格+"
		String str3 = "abc abc      sds       sd     ere";
		String[] str33 = str3.split(" +");

/* 构造器类Integer、 Character
 * Math Random StringBuilder StringBuffer 
 * System、Calendar
 * public static long currentTimeMillis(); 返回以毫秒为单位的当前时间
 * public static void exit(int status); 终止当前正在运行的 Java 虚拟机
 * BigInteger 可以表示不可变的任意精度的整数
 * BigDecimal 由于用float double会丢失数据的精度，BigDecimal可以表示不可变的、任意精度的有符号十进制数
 * Date 在日期和字符串之间的转换 
 * getTime();//long birthInms = borntime.getTime();
 * SimpleDateFormat下面的方法
 * String format(Date date); 将一个 Date格式化为日期/时间字符串----格式化
 * public Date parse(String source); 从给定字符串的开始分析文本，以生成一个日期
 * */
1:正则表达式
	(1)就是符合一定规则的字符串
	(2)常见规则
		A:字符
			x 字符 x。举例：'a'表示字符a
			\\ 反斜线字符。
			\n 新行（换行）符 ('\u000A') 
			\r 回车符 ('\u000D')
		B:字符类
			[abc] a、b 或 c（简单类） 
			[^abc] 任何字符，除了 a、b 或 c（否定） 
			[a-zA-Z] a到 z 或 A到 Z，两头的字母包括在内（范围） 
			[0-9] 0到9的字符都包括
		C:预定义字符类
			. 任何字符。我的就是.字符本身，怎么表示呢? \.
			\d 数字：[0-9]
			\w 单词字符：[a-zA-Z_0-9]
			在正则表达式里面组成单词的东西必须有这些东西组成
		D:边界匹配器
			^ 行的开头 
			$ 行的结尾 
			\b 单词边界
				就是不是单词字符的地方。
				举例：hello world?haha;xixi
		E:Greedy 数量词 
			X? X，一次或一次也没有
			X* X，零次或多次
			X+ X，一次或多次
			X{n} X，恰好 n 次 
			X{n,} X，至少 n 次 
			X{n,m} X，至少 n 次，但是不超过 m 次 
	(3)常见功能：(分别用的是谁呢?)
		A:判断功能
			String类的 public boolean matches(String regex)
		B:分割功能
			String类的 public String[] split(String regex)
		C:替换功能
			String类的 public String replaceAll(String regex,String replacement)
		D:获取功能
			Pattern和Matcher
				Pattern p = Pattern.compile("a*b");
				Matcher m = p.matcher("aaaaab");
				find():查找存不存在
				group():获取刚才查找过的数据
	(4)案例
		A:判断电话号码和邮箱
		B:按照不同的规则分割数据
		C:把论坛中的数字替换为*
		D:获取字符串中由3个字符组成的单词
2:Math
	(1)针对数学运算进行操作的类	
3:Random
	(1)用于产生随机数的类
	(2)构造方法:
		A:Random() 默认种子，每次产生的随机数不同
		B:Random(long seed) 指定种子，每次种子相同，随机数就相同
	(3)成员方法:
		A:int nextInt() 返回int范围内的随机数
		B:int nextInt(int n) 返回[0,n)范围内的随机数
4:System
	(1)系统类,提供了一些有用的字段和方法
	(2)成员方法(自己补齐)
		A:运行垃圾回收器
		B:退出jvm
		C:获取当前时间的毫秒值
		D:数组复制
5:BigInteger
	(1)针对大整数的运算
	(2)构造方法	
		A:BigInteger(String s)
6:BigDecimal
	(1)浮点数据做运算，会丢失精度。所以，针对浮点数据的操作建议采用BigDecimal(金融相关的项目)
	(2)构造方法
		A:BigDecimal(String s)
7:Date/DateFormat
	(1)Date是日期类，可以精确到毫秒
		A:构造方法
			Date()
			Date(long time)
		B:成员方法
			getTime()
			setTime(long time)
		C:日期和毫秒值的相互转换
	(2)DateFormat针对日期进行格式化和针对字符串进行解析的类，但是是抽象类，所以使用其子类SimpleDateFormat
		A:SimpleDateFormat(String pattern) 给定模式
			yyyy-MM-dd HH:mm:ss
		B:日期和字符串的转换
			a:Date -- String	format()
			b:String -- Date 	parse()
8:Calendar
	(1)日历类，封装了所有的日历字段值，通过统一的get()方法根据传入不同的日历字段可以获取值
	(2)如何得到一个日历对象呢?
		Calendar rightNow = Calendar.getInstance();	本质返回的是子类对象
	(3)成员方法
		A:根据日历字段得到对应的值 get()方法
		B:根据日历字段和一个正负数确定是添加还是减去对应日历字段的值 add();
		C:设置日历对象的年月日 set()
	public static int getDays(int year){
		//Calendar类使用的思想 首先创建Calendar类对象
		Calendar calendar = Calendar.getInstance();
		//为日历设置某个日期，那么获取日期就从设置的时间开始，相当于初始化日历
		//calendar.set(1949, 9, 1); //所有的参数都是int类型
		//get()方法中传进来的是静态字段，用类对象来引用，返回的是当前初始化好的日历下的年月日等信息
		//在某个设定好的日历下如何改变获取的日期信息呢，就用add()方法，在原来的基础上进行改变
		//改变之后需要从新获取时间信息
		calendar.set(year, 2, 1);//初始化该对象
		calendar.add(calendar.DATE, -1);//在设置好的日历的基础上改变时间
		return calendar.get(calendar.DATE);  //获取时间
	}


关于字符数组与字节数组
		String str = "hello";
		byte[] buff = str.getBytes(); 
		//字符串转化为字节数组
		System.out.println(buff);  		
		//[B@1db9742  输出是地址
		System.out.println(buff.toString()); 
		//[B@1db9742   输出是地址即使调用了toString()方法
		System.out.println(new String(buff)); 
		// hello 愿与String的构造方法 public String(byte bytes[])
		System.out.println(Arrays.toString(buff));  
		// [104, 101, 108, 108, 111] Arrays工具类加上逗号与括号
		
		char[] chs = str.toCharArray();
		//字符串转化为字符数组
		System.out.println(chs);
		//输出是 hello 这个字符串，直接输出字符数组是字符串，输出字节数组是地址
		System.out.println(chs.toString());
		//输出是地址 [C@106d69c 即使调用了toString()方法
		System.out.println(new String(chs));
		//输出是字符串 hello，源于String的构造器
		System.out.println(Arrays.toString(chs));
		//[h, e, l, l, o] Arrays工具类加上逗号与括号
		//思考如何将字符数组与字节数组进行转化呢,中间通过字符串进行转化
		String string = "hello";
		char[] ch1 = string.toCharArray();
		byte[] buf1 = string.getBytes();
		char[] ch2 = new String(buf1).toCharArray();
		byte[] buf2 = new String(ch1).getBytes();
字节流操作文本相关的东西，当文本中含有汉字的时候容易出现乱码，汉字占两个字节，当每次读取一个字节的时候，
字符就无法表示，因此就会出现乱码，若是读取一个字节数组，若刚好将最后一个汉字分开也会出现乱码
也就是说由于编码的问题(字符占据的字节数不一样)，字节流操作与文本相关的东西的时候不太方便，因此才有字符流以及转换流

/* 用户注册输入文件名和密码可以直接输入，通过User的set方法将用户名以及密码封装成User对象传给regist()方法，
 * 在该方法中通过User类的get方法获取传进来User对象的用户名以及密码，在两者之间添加分隔符","后，写到文件中
 * 登录就是输入的用户名以及密码和文件中写入的信息进行匹配
 * 这是用户操作相关的接口，任何的操作只需要对其进行实现就可以了，若要改变实现方式，只需要改变这个接口的
 * 实现类就行了，其他部分不需要进行改变，也就是说，需求不变，改变的只是实现的方法
 * 该工程有集合实现，IO实现，两种实现方式的类名方法名都一样，在测试类中，根据导入的包的不同
			import UserDaoImplIO.UserDaoImpl;  IO实现的包
			import UserDaoImplCollection.UserDaoImpl;  集合实现的包
			就可以实现不同的版本实现
 * 包分层实现结构，改动实现的时候，只将接口的实现类的实现方式改变即可
		public class UserDaoImpl implements UserDao {
		// 为了保证文件一加载就创建，就用静态代码块来实现
			private static File file = new File("a.txt");
			static {
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.out.println("创建文件失败");
				}
			}
		}
		
/* 多线程参与复制同一个文件总结：
 * 要考虑同步的问题
 * 用线程池来实现多线程复制文件，需要线程对象，返回值为Runnable对象的方法，创建线程类，创建线程类(内部类)
 * 注意数组初始化是传入大小不能为0
 * 考虑是否将一个变量上升为类的属性，以至于在类中其他地方可以使用
 * run方法中保持同步
 * 		public void run() {
			synchronized(srcFile){
				begin = System.currentTimeMillis();
				long blockCapacity = end - start;
				long hasread = 0;
				try{
					srcFile.seek(start);
					desFile.seek(start);
					while(hasread < blockCapacity){
						byte[] buff = new byte[4*1024*1024];
						long len = srcFile.read(buff);
						if(len > blockCapacity - hasread){
							len = blockCapacity - hasread;
						}
						desFile.write(buff, 0, (int)len);
						hasread += len;
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				last = System.currentTimeMillis();
			}
		}
		更新到2016年8月19日18:05:51

