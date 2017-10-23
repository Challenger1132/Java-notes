package MulThread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
 * 在接收端获取该 Socket 对象，获得输入流，输出流对象从该对象的输入流中读取数据
 * (读取文件名filename = dis.readUTF();读取文件长度length = dis.readLong()，继续读取其他数据)
 * 将数据写到其他地方
 * 
 * 流的其嵌套构造
 * 	DataInputStream dis = new DataInputStream(new BufferedInputStream
						(new FileInputStream(new File(filename))));
	DataOutputStream = new DataOutputStream(new BufferedOutputStream
						(new FileOutputStream(new File(filename))));
*/
public class Client {
	private List<File> filelist = new ArrayList<File>();
	private final int POOL_SIZE = 2; 
	private final int PORT = 10010;
	private ExecutorService threadpool = Executors.newFixedThreadPool
			(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
	
	//有参数的构造器，调用getFileList()方法
	public Client(File dir){
		getFileList(dir);
	}
	
	//获取文件夹中所有文件的File对象
	private void getFileList(File dir){
		File[] files = dir.listFiles();
		if(files != null){
			for(File f : files){
				if(f.isDirectory())
					getFileList(f);
				else
					this.filelist.add(f);
			}
		}
	}
	//线程类
	class Task implements Runnable{
		private File file;
		public Task(File file){
			this.file = file;
		}
		private DataInputStream dis;
		private DataOutputStream dos;
		byte[] buff = new byte[4*1024];
		private InetAddress ip;
		private int port;
		private Socket socket;
		@Override
		public void run() {
			if(isConected()){
				try{
					dis = new DataInputStream(new BufferedInputStream
							(new FileInputStream(file)));
					dos = new DataOutputStream(new BufferedOutputStream
							(socket.getOutputStream()));
					dos.writeUTF(file.getName());
					dos.flush();
//					dos.writeLong(file.length());
//					dos.flush();
					int len = 0;
					while((len = dis.read(buff)) != -1){
						dos.write(buff, 0, len);
					}
					dis.close();
					dos.close();
					socket.close();
					System.out.println("文件 " + file.getName() + " 已经传输完毕");
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		private boolean isConected(){
			try{
				ip = InetAddress.getLocalHost();
				port = PORT;
				socket = new Socket(ip, port);
				System.out.println("连接成功");
				return true;
			}
			catch(Exception e){
				System.out.println("连接失败");
				return false;
			}
		}
	}
	private void launch(){
		System.out.println("文件传输开始......");
		for(File f : this.filelist){
			//每传输一个File对象就会开辟一个新的线程，每个文件会开一个新的线程进行传输
			threadpool.execute(new Task(f));
		}
	}
	public static void main(String[] args) {
		File srcFolder = new File("C:/Users/xingyue/Desktop/测试");
		new Client(srcFolder).launch();
	}
}
