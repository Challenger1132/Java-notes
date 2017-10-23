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
/* �������ڽ���һ��File������Ըö�����Ϊ����Դ���в�������file�е�����д�����Ͷ�Socket�������
 * */
public class Sender implements Runnable{
	private final int PORT = 10080;
	private File file;
	public Sender(File file){  //���ﴫ����һ��File�����
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
			System.out.println("�ļ���" + file.getName() + "��ʼ����");
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
			System.out.println("���ӳɹ�");
			return true;
		}
		catch(Exception e){
			System.out.println("����ʧ��");
			return false;
		}
	}
}
