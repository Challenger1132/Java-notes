package Demo.copy2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
/* 该类用于接收一个File类对象，以该对象作为数据源进行操作，将file中的数据写到发送端Socket输出流中
 * */
public class Sender implements Runnable{
	private final int PORT = 10080;
	private File file;
	public Sender(File file){  //这里传进来一个File类对象
		this.file = file;
	}
	private InetAddress ip;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	byte[] buff = new byte[4*1024];
	@Override
	public void run() {
		if(isConection()){
			System.out.println("文件　" + file.getName() + "开始发送");
			try{
				ip = InetAddress.getLocalHost();
				dis = new DataInputStream(new BufferedInputStream
						(new FileInputStream(file)));
				dos = new DataOutputStream(new BufferedOutputStream
						(socket.getOutputStream()));
				dos.writeUTF(file.getName());
				dos.flush();
				int len = dis.read(buff);
				while(-1 != len){
					dos.write(buff, 0, len);
					len = dis.read(buff);
				}
				dis.close();
				dos.close();
				socket.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public boolean isConection(){
		try{
			ip = InetAddress.getLocalHost();
			socket = new Socket(ip, PORT);
			System.out.println("连接成功");
			return true;
		}
		catch(Exception e){
			System.out.println("连接失败");
			return false;
		}
	}
}
