/*
IO用于在设备间进行数据传输的操作
Java语言里的流序列中的数据既可以是未经加工的原始二进制数据，也可以是经过一定编码处理后符合某种特定格式的数据
流的分类：
	A:流向
		输入流	读取数据
		输出流	写出数据
		相对于程序来说，输出流是往存储介质或数据通道写入数据，而输入流是从存储介质或数据通道中读取数据
	B:数据类型
		字节流
		字节流，数据的处理以字节为基本单位，字节流每次读写8位二进制数，也称为二进制字节流或位流
		字符流
		字符流，用于字符数据的处理，字符流一次读写16位二进制数，并将其做一个字符而不是二进制位来处理
	C:流是否直接与特定的地方(如磁盘、内存、设备等)相连，分为
		节点流(基本流):可以从或向一个特定的地方(节点)读写数据
		包裹流(高级流或者处理流):是对一个已存在的流的连接和封装，通过所封装的流的功能调用实现数据读写,
								其构造方法总是要带一个其他的流对象做参数,一个流对象经过其他流的多次包装,称为流的链接,
								处理流包裹或者说嫁接在节点流之上,可以使节点流具有更加强大的功能

一般来说关于流的特性有下面几点：
	先进先出，最先写入输出流的数据最先被输入流读取到
	顺序存取，可以一个接一个地往流中写入一串字节，读出时也将按写入顺序读取一串字节，不能随机访问中间的数据
	只读或只写，每个流只能是输入流或输出流的一种，不能同时具备两个功能，在一个数据传输通道中
	如果既要写入数据，又要读取数据，则要提供两个流

缓冲的概念:
	配备一个缓冲区(Buffer)，一个缓冲区就是专门用于传送数据的一块内存
	当向一个缓冲流写入数据时，系统将数据发送到缓冲区，而不是直接发送到外部设备。缓冲区自动记录数据
	当缓冲区满时，系统将数据全部发送到相应的外部设备
	当从一个缓冲流中读取数据时，系统实际是从缓冲区中读取数据，当缓冲区为空时，系统就会从相关外部设备自动读取数据，
	并读取尽可能多的数据填满缓冲区 
	使用数据流来处理输入输出的目的是使程序的输入输出操作独立于相关设备，避免了频繁的访问设备
	由于程序不需关注具体设备实现的细节，所以对于各种输入输出设备，只要针对流做处理即可，
	不需修改源程序，从而增强了程序的可移植性

File 类对象是，某个文件或者磁盘路径的抽象表示形式
对象属性中包含了文件或目录的相关信息
通过调用 File 类提供的各种方法，能够创建、删除、重名名文件、判断文件的读写权限以及是否存在设置和查询文件的最近修改时间等
不同操作系统具有不同的文件系统组织方式，通过使用 File 类对象，Java 程序可以用与平台无关的、统一的方式来处理文件和目录	

什么流能够直接操作文件呢
	1、FileInputStream	
	FileOutPutStream 
	FileReader 
	FileWriter
	这些流的构造方法一般是传进来File类对象，或者String类型的文件路径，一般参数是这两个的都是可以直接操作文件的流(节点流)
	2、但是像 
	BufferedInputStream 
	BufferedOutputStream 
	BufferedReader 
	BufferedWriter 其构造方法中传进去的不是File对象或者是String类型的文件路径，而是节点流这类流是高级流(包裹流)
	3、PrintWriter 字符打印流
	PrintStream 字节打印流
	以上两种比较奇葩，构造器中传进去的参数既可以是File类对象，或者String类型的文件路径的节点流，还可以是高级流对象
close()  flush()的区别
	注意流一旦被关闭，其占据的资源就会被释放，不能再用关闭之后的某个流对象，对文件进行操作
	如果想再次对文件操作，就要重新建立流的连接，否则会抛出异常
	close 是关闭流对象，但是先刷新缓冲区，关闭之后，流不能继续使用
	flush 仅仅刷新缓冲区，刷新之后，流对象还能继续使用，当数据量比较小的时候，只用close就能搞定
	但是当数据量比较大的时候，就在写数据的过程中进行flush，写完之后关闭
转换流 字符流 = 字节流 + 编码表
	A：为什么会有转换流？
		1、带有汉字的文件用字节流输出操作显示会出现乱码，用字节流操作字符文件不太方便
		2、英文字符占一个字节，用字节流可以方便的输出，显示，但是汉字占两个字节，
		但是我们输出的时候是逐个字节进行输出的，也就是将汉字拆开进行输出，且汉字映射的两个
		数字是负的，因此输出是乱码，没有对应的映射
		3、第二种方式，比如每次读取1024个字节，将1024个字节封装成字符串进行输出
		但是在1023个字节出恰好有汉字出现，这个时候汉字还会被拆开显示，可能会出现乱码
		编码　string ----> byte[]  字符串转化为字节数组
		解码  byte[] ----> String   字符数组转化为字符串
	B：转换流的构造方法
		A:OutputStreamWriter
			OutputStreamWriter(OutputStream os):默认编码，GBK
			OutputStreamWriter(OutputStream os,String charsetName):指定编码
		B:InputStreamReader
			InputStreamReader(InputStream is):默认编码，GBK
			InputStreamReader(InputStream is,String charsetName):指定编码
操作"基本Java数据类型"的流
	A：DataInputStream		
		数据输入流允许应用程序以与机器无关方式从基础输入流中读取"基本Java数据类型"
		应用程序可以使用数据输出流写入稍后由数据输入流读取的数据
	B：DataOutputStream			
		数据输出流允许应用程序以适当方式将"基本Java数据类型"写入输出流中
		然后,应用程序可以使用数据输入流将数据读入
	之前无论是字节流字符流，无论是每次读写一个字符，读写一个字符数组，每次读写一个字节，读写一个字节数组
	还是使用了转换流，或者进行文件的复制读写等，操作的都是"流"，并不是"基本Java数据类型"，直到多线程进行文件
	复制的时候才知道可以写入文件名 writeUTF()，写入文件的长度 writeLong()，这些操作的都是Java的基本数据类型				
IO流
	|--字节流
			|--字节输入流
				|--InputStream
						int read(int by) : 一次读取一个字节
						int read(byte[] buff) : 一次读取一个字节数组
						|--FileInputStream 
						|--BufferedInputStream 带有缓冲区
			|--字节输出流
				|--OutputStream
						void write(int by) : 一次写入一个字节
						void write(byte[] buff,int offset,int len) : 一次写入一个字节数组的一部分
						|--FileOutputStream 
						|--BufferedOutputStream 带有缓冲区
	|--字符流
			|--字符输入流
				|--Reader
						int read() : 一次读取一个字符 
						int read(char[] buff) : 一次读取一个字符数组
						int read(char[] buff, int off, int len) 将字符读入数组的某一部分读取
						|--InputStreamReader
								|--FileReader 上面的简化
						|--BufferedReader  高效读写
							String readLine() : 一次读取一行
			|--字符输出流
				|--Writer
						void write(int ch) : 一次写入一个字符
						void write(char[]) :一次写入一个字符数组
						void write(char[] buff,int offset,int len) : 一次写入一个字符数组的一部分
						void write(String str) : 一次写入一个字符串
						void write(String str, int off, int len) 一次写入一个字符串的一部分
						|--OutputStreamWriter
								|--FileWriter  上面的简化
						|--BufferedWriter  高效读写
							void newlLine() : 写入换行符
							void write(String line) : 一次写入一个字符串
in out：
	in out 是System类下面的两个静态字段，可以通过类名来访问
	public static final InputStream in;  标准输入流
		BufferedReader br = new BufferedReader(InputStreamReader(System.in));
	public static final PrintStream out; 标准输出流
		PrintStream ps = System.out;
		ps.println("hello world !");等价于System.out.println("hello world !"); 
		这里本质是IO流(PrintStream字节打印流)操作数据输出到控制台
打印流
	A：PrintWriter 字符打印流
		PrintWriter可以直接打印一个字符串，pw.println(str);
		PrintWriter可以直接写一个字符串  pw.write(str); 但是这样就没有换行符
		PrintWriter pw = new PrintWriter(new FileWriter("a.txt"),true);  //直接将数据打印到文件中
		第二个参数是true是该输出流就有了自动刷新功能，但是第一个参数不能直接是String类型的文件路径了，而是Writer类型的参数
		这里使用 PrintWriter pw作为输出流，若使用 BufferedWriter bw作为输出流
			pw.println(str);一行代码就相当于
			bw.write(str);
			bw.newLine();
			bw.flush();这三句代码
	B：PrintStream 字节打印流	
		PrintStream 为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式
		PrintStream下面的println()方法可以直接打印一个字符串
		还可以打印基本数据类型
		下面的write()方法可以写入一个指定字节、字节数组、字节数组的一部分
		ps.print(str + "\n");
		ps.println(str);

SequenceInputStream 表示其他输入流的逻辑串联
	SequenceInputStream(Enumeration<? extends InputStream> e)  传进Enumeration类型
	SequenceInputStream(InputStream s1, InputStream s2)  传进两个InputStream对象
		Vector<InputStream> fileLists = new Vector<InputStream>();
		Enumeration<InputStream> ele = fileLists.elements();
		结合Vector调用elements()方法返回值是 Enumeration 类型，可将其传给SequenceInputStream的构造器
	SequenceInputStream 表示其他输入流的逻辑串联，它从输入流的有序集合开始，并从第一个输入流开始读取
	直到到达文件末尾，接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止

支持文件的随机读写 RandomAccessFile
之前读写文件都是对于一个文件顺次的读取，不能从文件的中间进行读取
RandomAccessFile 的实例支持对随机存取文件的读写，允许从文件中间位置开始读取
随机存取文件的行为类似存储在文件系统中的一个大型byte数组
存在指向该隐含数组的光标或索引，称为文件指针，输入操作从文件指针开始读取字节，并随着对字节的读取而前移此文件指针
RandomAccessFile不属于流体系，其父类是Object，但是融合了文件读写的功能，并支持文件的随机读写
其构造器第一个参数是File类对象或者是String类型的文件路径，第二个参数是读写方式

<字节流>
	FileInputStream fis = new FileInputStream(parameter); 参数是File类对象或者String类型的文件路径，可以直接操作文件
	ch = fis.read(); 读取一个字节，并返回
	int len = fis.read(byte[] buff, int offset, int length); 从字节输入流中读取字节，从offset开始，每次最多读取length个，并返回实际读取的字节数
	int len = fis.read(byte[] buff); 从此输入流中将最多 buff.length 个字节的数据读入一个字节数组中
	
	FileOutputStream fos = new FileOutputStream(parameter); 参数是File类对象或者String类型的文件路径，可以直接操作文件
	fos.write(int ch);  无返回值，将指定字节写入
	fos.write(byte[] buff, int offset, int len);  将字节数组buff总的数据从offset开始，每次写入len个字节到字节流中
	fos.write(byte[] buff);  将 buff.length 个字节从指定字节数组写入此文件输出流中
	带有缓冲区的字节流
		BufferedInputStream bis = new BufferedInputStream(InputStream is); 参数是高级流
		ch = bis.read();  返回值为int,读取一个字节，并返回
		int len = bis.read(byte[] buff, int offset, int length); 从字节输入流中读取字节，从offset开始，每次最多读取length个，并返回实际读取的字节数
		BufferedOutputStream bos = new BufferedOutputStream(OutputStream os); 参数是高级流
		bos.write(int ch); 无返回值，将指定字节写入
		bos.write(byte[] buff, int offset, int len); 将字节数组buff总的数据从offset开始，每次写入len个字节到字节流中
<字符流>
	FileReader fr = new FileReader(parameter); 参数是File类对象或者String类型的文件路径，可以直接操作文件
	int len = fr.read(char[] cbuf, int offset, int length); 将读取的内容放到字符数组中，返回实际读取的字符数
	ch = fr.read();  返回实际读取的字符
	FileWriter 和 FileOutputStream 具有相同的方法，字节数组--->字符数组
	带有缓冲区的字符流
		BufferedReader br = new BufferedReader(parameter); 参数是高级流
		ch = br.read();  返回值为int,读取一个字节，并返回
		int len = br.read(char[] cbuf, int offset, int length);  将读取的内容放到字符数组中，返回实际读取的字符数
		String str = br.readLine();  读取一个文本行 
		BufferedWriter bw = new BufferedWriter(parameter); 参数是高级流
		bw.write(int ch); 无返回值，将指定字符写入
		bw.write(char[] buff, int offset, int len); 将字符数组buff中的数据从offset开始，每次写入len个字符到字符流中
		bw.newLine();  写入一个行分隔符