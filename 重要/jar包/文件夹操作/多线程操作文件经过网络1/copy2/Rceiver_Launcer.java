package Demo.copy2;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

public class Rceiver_Launcer {
/* 一直监听来自发送端的Socket对象，得到后accept它，并将该Socket传递给Receiver构造器，启动线程
 * */
	private ServerSocket serversocket;
	private int tryTime = 0;
	private Socket socket;
	
 	public Rceiver_Launcer(int port) throws Exception{
 		bindToPort(port);
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
 	private void launch(File desFolder){
		while(true){
			try{
				socket = serversocket.accept();
				new Thread(new Receiver(socket, desFolder)).start();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
 	}
	public static void main(String[] args) throws Exception{
		int port = 10080;
		File desFolder = new File("C:/Users/xingyue/Desktop/ABC");
		new Rceiver_Launcer(port).launch(desFolder);
	}
}
