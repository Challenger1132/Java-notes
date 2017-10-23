package Two_Thrad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* 将一个文件夹中的所有文件多线程方式复制到指定的文件夹中，不包含文件夹的层级结构
 * */
public class MultipleThread {
	private final String srcpath = Constants.SRC_PATH;  //定义在接口中的常量
	private final String despath = Constants.DES_PATH;
	private final int POOL_SIZE = 4;  //定义在本类中
	private File desFolder;
	private ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
	public List<File> fileList = new ArrayList<>();
	
	//构造器
	/* 这里涉及代码书写风格的问题，可以创建MultipleThread类的类对象，用该类对象调用getFileList()方法
	 * 或者将getFileList()方法的调用直接写在构造器里面，当创建类对象的时候直接调用该方法，因此一些需要调用
	 * 的方法可以写在构造器里面，之后便不用显式的进行调用
	 */
	public MultipleThread(File srcFolder, File desFolder){
		getFileList(srcFolder);
		this.desFolder = desFolder;
		if(! desFolder.exists()){
			desFolder.mkdirs();
		}
	}
	//无参构造器
	public MultipleThread(){
		getFileList(new File(srcpath));
		desFolder = new File(despath);
		if(! desFolder.exists()){
			desFolder.mkdirs();
		}
	}
	
	//线程类,实现文件的复制
	class ThreadDemo implements Runnable{
		private File file;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		byte[] buff = new byte[4*1024];
		public ThreadDemo(File file){
			this.file = file;
		}
		@Override
		public void run() {
			try{
				bis = new BufferedInputStream(new FileInputStream(file));
				bos = new BufferedOutputStream(new FileOutputStream
						(new File(desFolder,file.getName())));
				int len = bis.read(buff);
				while(-1 != len){
					bos.write(buff, 0, len);
					len = bis.read(buff);
				}  //文件流关闭语句写在了这里
				bis.close();
				bos.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private Runnable sendFile(final File file){
		return new Runnable() {
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			byte[] buff = new byte[4*1024];
			@Override
			public void run() {
				try{
					bis = new BufferedInputStream(new FileInputStream(file));
					bos = new BufferedOutputStream(new FileOutputStream
							(new File(desFolder,file.getName())));
					int len = bis.read(buff);
					while(-1 != len){
						bos.write(buff, 0, len);
						len = bis.read(buff);
					}  //文件流关闭语句写在了这里
					bis.close();
					bos.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}				
			}
		};
	}
	//将一个文件夹目录下所有文件存放在容器中，有参数的构造方法
	private void getFileList(File dir){
		File[] files = dir.listFiles();
		if(files != null){
			for(File f : files){
				if(f.isDirectory()){
					getFileList(f);
				}
				else{
					fileList.add(f);
				}
			}
		}
	}
	public void launch(){
		for(File f : fileList){
			System.out.println(f.getAbsolutePath());
			threadpool.execute(new Thread(new ThreadDemo(f))); //传入Runnable对象
		}
		threadpool.shutdown();
	}
	public void launch1(){
		for(File f : fileList){
			System.out.println(f.getAbsolutePath());
			threadpool.execute(sendFile(f));  //调用返回值是 Runnable对象的方法
		}
		threadpool.shutdown();
	}
	public void launch2(){
		for(final File f : fileList){
			System.out.println(f.getAbsolutePath());
			threadpool.execute(new Runnable() {
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				byte[] buff = new byte[4*1024];
				@Override
				public void run() {
					try{
						bis = new BufferedInputStream(new FileInputStream(f));
						bos = new BufferedOutputStream(new FileOutputStream
								(new File(desFolder,f.getName())));
						int len = bis.read(buff);
						while(-1 != len){
							bos.write(buff, 0, len);
							len = bis.read(buff);
						}  //文件流关闭语句写在了这里
						bis.close();
						bos.close();
					}
					catch(Exception e){
						e.printStackTrace();
					}	
				}
			}); 
		}
		threadpool.shutdown();
	}
	public static void main(String[] args) {
		String srcstr = new String("C:/Users/xingyue/Desktop/测试");
		String desstr = new String("C:/Users/xingyue/Desktop/111111");
		File srcFolder = new File(srcstr);
		File desFolder = new File(desstr);
		System.out.println("创建文件夹");
//		new MultipleThread(srcFolder, desFolder).launch();
//		new MultipleThread(srcFolder, desFolder).launch1();
		new MultipleThread(srcFolder, desFolder).launch2();
		new MultipleThread().launch();
	}
}
/**
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
					return new Runnable() {
						....
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
*/

