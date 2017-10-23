package Demo_3;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyServer {
    ServerSocket server=null;
    Socket client=null;
    boolean flag = true;
    DataInputStream dis;
    DataOutputStream dos;
    FileOutputStream fos;
    public static void main(String[] args) {
        new MyServer().ServerStart();
    }
    
    public void ServerStart(){
        try{
            server = new ServerSocket(8888);
            System.out.println("�˿ں�:" + server.getLocalPort());
            client = server.accept();
			if (client != null){
				System.out.println("�������");
			}
			System.out.println ("����Ŀ���ַ");
			String fileStr = (new Scanner(System.in)).next();
			
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());
            
            String answer = "g";
            byte ans[] = answer.getBytes();
            byte[] buff = new byte[1024 * 4];
            int len;
            new File(fileStr).mkdirs();
            while(flag){
                len = dis.read(buff);   // ����ȡ�����ݴ����buff�У�����ʵ�ʶ�ȡ���ֽ���Ŀ
                dos.write(ans);
                String select = new String(buff,0,len);  //ת��Ϊ�ַ���
                if(select.contains("%%%%")) {
                	//������Ŀ���ļ���·��ƴ���Ͻ��ܵ��ļ���·������ȥ������·���е� ��־λ "%%%%"
                    File f = new File(fileStr + (select.replace("%%%%","")));
                    System.out.println("���յ����ļ�����" + select);
                    f.mkdirs();   
                }
                else if(select.contains("$$$$")){
                	//������Ŀ���ļ���·��ƴ���Ͻ��ܵ��ļ�·������ȥ������·���е� ��־λ "$$$$"
                	//��һ��·��������һ�㣬�ж��·��������·���У������������ļ��в㼶�ṹ
                	//���ļ������յ��ļ�·��������·����ϳ��µ�·��������FileOutputStream��������ʹ֮����һ���ļ�
                    fos = new FileOutputStream(fileStr + (select.replace("$$$$","")));
                    String cs;
                    boolean cflag = true;
                    int tip = dis.readInt();  //tip�ǽ��յ�һ������
                    dos.write(ans);
                    while(tip > 0){
                        len = dis.read(buff, 0, (tip > 1024*4 ? 1024*4 : tip));
                        tip = tip - len; 
                        cs = new String(buff,0,4);
                        fos.write(buff,0,len);
                    }
                    fos.flush();
                    fos.close();
                    dos.write(ans);
                }
                else if(select.contains("####"))
                {
                    flag = false;
                }
            }
            dis.close();
            client.close();
            server.close();
         }
         catch(IOException e){
        	 e.printStackTrace();
         }
     }
}