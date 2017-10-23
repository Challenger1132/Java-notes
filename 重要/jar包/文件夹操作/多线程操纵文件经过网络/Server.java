package MulThread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private int tryTime = 0;
	private ServerSocket serversocket;
	private Socket socket;
	private final static int POOL_SIZE = 2;
	private File desFolder;
	private ExecutorService pool = Executors.newFixedThreadPool
			(Runtime.getRuntime().availableProcessors()*POOL_SIZE);
			
	public Server(int port,File desFolder) throws Exception{
		bindToPort(port);
		this.desFolder = desFolder;
		if(!this.desFolder.exists()){
			desFolder.mkdirs();
		}
	}
	
	private void launch(){
		while(true){
			try{
				socket = serversocket.accept();
				//接收端没接收到一个Socket对象就会开辟一个新的线程去处理该接收到的Socket对象
				pool.execute(new Task(socket));
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	class Task implements Runnable{
		private Socket socket;
		public Task(Socket socket){
			this.socket = socket;
		}
		private DataInputStream dis;
		private DataOutputStream dos;
		byte[] buff = new byte[4*1024];
		private String filename;
		@Override
		public void run() {
			try{
				dis = new DataInputStream(new BufferedInputStream
						(socket.getInputStream()));
				filename = dis.readUTF();
				dos = new DataOutputStream(new BufferedOutputStream
						(new FileOutputStream(new File(desFolder,filename))));
//				long length = dis.readLong();
				int len = 0;
				while((len = dis.read(buff)) != -1){
					dos.write(buff, 0, len);
				}
				System.out.println("文件: " + filename + "接收完成!");
				dis.close();
				dos.close();
				socket.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	private void bindToPort(int port) throws Exception{
		try{
			serversocket = new ServerSocket(port);
			System.out.println("启动服务");
		}
		catch(Exception e){
			System.out.println("绑定端口失败");
			this.tryTime ++;
			port = port + this.tryTime;
			if(this.tryTime <= 10){
				bindToPort(port);
			}
			else{
				throw new Exception("多次尝试失败，请更换端口号");
			}
		}
	}
	public static void main(String[] args) throws Exception {
		File desFolder = new File("C:/Users/xingyue/Desktop/454545454545454");
		int port = 10010;
		new Server(port, desFolder).launch();
	}
}
