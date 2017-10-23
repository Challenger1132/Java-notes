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
/* ������дע��ṹ
 * ���ݴ�����¹�������
 * Client�ࣺ����Client����󣬻�ִ�и��๹�������ڹ������л�ִ��getFileList()����ļ����ļ�
 * File����Ļ�ȡ���洢��fileList�У����fileList��ʼ����Client�������ø�����launch()����
 * �÷������̳߳ض������execute()������������һ���̶߳���ÿ����һ��File����ͻῪ��һ���µ��̣߳�
 * ÿ���ļ��Ὺһ���µ��߳̽��д��䣬���ļ�����Ĵ����װ���߳����У����߳������һ��File�����
 * �Ըö�����д���
 * Server�ࣺ����Server������ʱ���ִ�й��췽�������췽���л�ִ��bindToPort()���������ServerSocket
 * �ĳ�ʼ����Server�������ø�����launch()�������ڸ÷�����ServerSocket�����ͻ��˵�Socket����
 * �����յ���Socket���󴫸��߳���Task�����߳�����Ϊ�̳߳ض������execute()�����Ĳ�����ִ�У����ڷ��Ͷ˵�
 * ÿһ��Socket���󶼻Ὺ��һ���µ��߳�������
 * 
 * �����ļ��ķ��͹ؼ��ǵõ�Socket���󣬵õ��ö�������룬����������������ط���ȡ���ݣ�д��Socket�����
 * �������(ò�ƽ��������ݶ�д�룬д���ļ����ļ��� dos.writeUTF(file.getName());д���ļ��ĳ���
 * dos.writeLong(file.length());Ȼ�����д���������ݣ�������֮����ô�������ֵģ����Ǻ�)
 * �ڽ��ն˻�ȡ�� Socket ���󣬻�������������������Ӹö�����������ж�ȡ����
 * (��ȡ�ļ���filename = dis.readUTF();��ȡ�ļ�����length = dis.readLong()��������ȡ��������)
 * ������д�������ط�
 * 
 * ������Ƕ�׹���
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
	
	//�в����Ĺ�����������getFileList()����
	public Client(File dir){
		getFileList(dir);
	}
	
	//��ȡ�ļ����������ļ���File����
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
	//�߳���
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
					System.out.println("�ļ� " + file.getName() + " �Ѿ��������");
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
				System.out.println("���ӳɹ�");
				return true;
			}
			catch(Exception e){
				System.out.println("����ʧ��");
				return false;
			}
		}
	}
	private void launch(){
		System.out.println("�ļ����俪ʼ......");
		for(File f : this.filelist){
			//ÿ����һ��File����ͻῪ��һ���µ��̣߳�ÿ���ļ��Ὺһ���µ��߳̽��д���
			threadpool.execute(new Task(f));
		}
	}
	public static void main(String[] args) {
		File srcFolder = new File("C:/Users/xingyue/Desktop/����");
		new Client(srcFolder).launch();
	}
}
