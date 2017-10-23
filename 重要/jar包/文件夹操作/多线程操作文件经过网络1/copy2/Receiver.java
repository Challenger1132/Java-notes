package Demo.copy2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
/* ������һ��Socket���󣬶Ը�Socket������в������������е�����д��ָ�����ļ���ȥ
 * */
public class Receiver implements Runnable{
	private File desFolder;
	private Socket socket;
	public Receiver(Socket s, File desFolder){
		socket = s;  //����ͨ����Ĺ��������һ�����Ͷ˵ģ�socket����
		this.desFolder = desFolder;
		if(!desFolder.exists()){
			desFolder.mkdirs();
		}
	}
	private DataOutputStream dos;
	private DataInputStream dis;
	private byte[] buff = new byte[4*1024];
	private String name;
	@Override
	public void run() {
		try{
			dis = new DataInputStream(new BufferedInputStream
					(socket.getInputStream()));
			name = dis.readUTF();
			dos = new DataOutputStream(new BufferedOutputStream
					(new FileOutputStream(new File(desFolder,name))));
			int len = dis.read(buff);
			while(-1 != len){
				dos.write(buff, 0, len);
				len = dis.read(buff);
			}
			dis.close();
			dos.close();
			socket.close();
			System.out.println("�ļ���" + name + " �������");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
