package Demo.copy2;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

public class Rceiver_Launcer {
/* һֱ�������Է��Ͷ˵�Socket���󣬵õ���accept����������Socket���ݸ�Receiver�������������߳�
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
			System.out.println("��������");
		}
		catch(Exception e){
			System.out.println("�󶨶˿�ʧ��");
			this.tryTime ++;
			port = port + this.tryTime;
			if(this.tryTime <= 10){
				bindToPort(port);
			}
			else{
				throw new Exception("��γ���ʧ�ܣ�������˿ں�");
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
