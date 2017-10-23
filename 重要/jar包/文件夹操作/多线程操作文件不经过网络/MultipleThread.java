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

/* ��һ���ļ����е������ļ����̷߳�ʽ���Ƶ�ָ�����ļ����У��������ļ��еĲ㼶�ṹ
 * */
public class MultipleThread {
	private final String srcpath = Constants.SRC_PATH;  //�����ڽӿ��еĳ���
	private final String despath = Constants.DES_PATH;
	private final int POOL_SIZE = 4;  //�����ڱ�����
	private File desFolder;
	private ExecutorService threadpool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
	public List<File> fileList = new ArrayList<>();
	
	//������
	/* �����漰������д�������⣬���Դ���MultipleThread���������ø���������getFileList()����
	 * ���߽�getFileList()�����ĵ���ֱ��д�ڹ��������棬������������ʱ��ֱ�ӵ��ø÷��������һЩ��Ҫ����
	 * �ķ�������д�ڹ��������棬֮��㲻����ʽ�Ľ��е���
	 */
	public MultipleThread(File srcFolder, File desFolder){
		getFileList(srcFolder);
		this.desFolder = desFolder;
		if(! desFolder.exists()){
			desFolder.mkdirs();
		}
	}
	//�޲ι�����
	public MultipleThread(){
		getFileList(new File(srcpath));
		desFolder = new File(despath);
		if(! desFolder.exists()){
			desFolder.mkdirs();
		}
	}
	
	//�߳���,ʵ���ļ��ĸ���
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
				}  //�ļ����ر����д��������
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
					}  //�ļ����ر����д��������
					bis.close();
					bos.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}				
			}
		};
	}
	//��һ���ļ���Ŀ¼�������ļ�����������У��в����Ĺ��췽��
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
			threadpool.execute(new Thread(new ThreadDemo(f))); //����Runnable����
		}
		threadpool.shutdown();
	}
	public void launch1(){
		for(File f : fileList){
			System.out.println(f.getAbsolutePath());
			threadpool.execute(sendFile(f));  //���÷���ֵ�� Runnable����ķ���
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
						}  //�ļ����ر����д��������
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
		String srcstr = new String("C:/Users/xingyue/Desktop/����");
		String desstr = new String("C:/Users/xingyue/Desktop/111111");
		File srcFolder = new File(srcstr);
		File desFolder = new File(desstr);
		System.out.println("�����ļ���");
//		new MultipleThread(srcFolder, desFolder).launch();
//		new MultipleThread(srcFolder, desFolder).launch1();
		new MultipleThread(srcFolder, desFolder).launch2();
		new MultipleThread().launch();
	}
}
/**
	����Ҫע����д���ɽṹ
	1�������漰������д�������⣬���Դ���MultipleThread���������ø���������getFileList()����
 	���߽�getFileList()�����ĵ���ֱ��д�ڹ���������
 		public MultipleThread(File srcFolder, File desFolder){
			getFileList(srcFolder);
			this.desFolder = desFolder;  //�ù���������������getFileList()��������������ʼ���˳�ԱdesFolder
		}
	������������ʱ��ֱ�ӵ���getFileList()���������һЩ��Ҫ����
          �ķ�������д�ڹ��������棬֮��㲻����ʽ�Ľ��е��ã����⻹Ҫע���вεĹ��������޲����Ĺ�����������������
          �в���ʽ�Լ��޲���ʽ��Ҳ���Ƿ���������
    2��������һ���ӿ�,�����ǳ���������һ�����п�������,����
		public interface Constants {
			public final static String SRC_PATH = "C:/Users/xingyue/Desktop/����";
			public final static String DES_PATH = "C:/Users/xingyue/Desktop/Ĭ��";
			public final int POOL_SIZE = 2;
		}
		��һ����������
			private final String srcpath = Constants.SRC_PATH;
			private final String despath = Constants.DES_PATH;
          �����ж��� private static final String PATH = "FFF";�ȳ�Ա
    3���������вΣ��޲ε���ʽ���ڹ������е���������������֮��㲻����ʽ�Ľ��з����ĵ���
    4�� �����Ҫ�̶߳����ʱ�򣬿��Դ����߳��࣬Ҳ���Դ�������ֵΪRunnable����ķ���������
          private ExecutorService threadpool = Executors.newFixedThreadPool
          (Runtime.getRuntime().availableProcessors()*POOL_SIZE);
          threadpool.execute(sendFile(f));
          execute()�����Ĳ�����Ҫ������Runnable�ӿڶ��󣬿��Դ����߳���
			class ThreadDemo implements Runnable{
				.....
			    @Override
			    public void run() {
					
			    }
			}
			���ߴ�������ֵΪRunnable�Ķ���
				public Runnable sendFile(File file){
					return new Runnable() {
						....
						@Override
						public void run() {
						}
					};
				}
			���÷�ʽΪ��threadpool.execute(new Thread(new ThreadDemo(f)));
			threadpool.execute(new ThreadDemo(f));
			threadpool.execute(sendFile(f));
			������������ʵ��
			threadpool.execute(new Runnable(){
				@Override
				public void run() {
				}
			});
*/

