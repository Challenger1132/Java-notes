package Demo_3;
import java.io.*;
import java.net.*;
import java.util.Scanner;
/* path = "%%%%" + (f1.getPath().replace(folderPath,""));         	
 * path = "%%%%" + f1.getName();
 * �������ط��������������أ� fileStr�Ǽ����·�������ļ��е��ļ����б�������һ���õ����Ǳ�־����ȥ�������·���õ����ַ���
 * �ڶ���ĵõ��������һ�����ƣ�����������ļ���Ŀ¼����һ��õ�����ȥ�������·��֮���·���������ļ��е����룬·����Խ��
 * Խ���������ļ��㼶·���ᱣ�棬�ڶ��������ļ��е�����ֻ��õ����һ�����ƣ�ǰ���·���ᶪʧ��Ҳ����˵�����������ļ���·����
 * �ᱣ��
 * ���������̴�����
 * �������·����     C:\Users\xingyue\Desktop\practice
 * ������Ŀ¼���õ�
					\C
					\Java
					\Test_pic.jpg
					\������Ϊ��į������.mp3
					\������.java
					\��������(��ɾ).java
					\��ɾ����1.java
					\��ͼ
 �ڽ��ն��жϵõ�C:\Users\xingyue\Desktop\practice +������·����ɵ�·��
 ���ļ�ֱ�ӽ��ܣ����ļ��о���C:\Users\xingyue\Desktop\practice�����ϴ����ļ���
 �ٴ������ļ��У�·����仯
 					\C\˫������   (·���䳤��)
					\Java\..
					\Test_pic.jpg
					\������Ϊ��į������.mp3
					\������.java
					\��������(��ɾ).java
					\��ɾ����1.java
					\��ͼ\....
����ԭ���Ļ����������µ�·����������Щ���ӵ�·�����ǽ��ն˴����ļ��У������ļ��㼶�ṹ������
����ֻ������һ�����ƣ��õ���ֻ��   C:\Users\xingyue\Desktop\practice +�����һ������   ��ɵ��ļ��л����ļ�
���ļ��㼶�ṹ�ͻ���ʧ
 * */
public class MyClient 
{
    Socket client;
    boolean flag = true;
    FileInputStream fis;//�������������ȡ������Ҫ������ļ�
    DataOutputStream dos;//���������������һ̨����(��������)��������
    DataInputStream dis;//�������������ȡ��һ̨���ԵĻ�Ӧ��Ϣ
	String folderPath;
    public static void main(String[] args) {
        new MyClient().ClientStart();
    }
    
    public void ClientStart(){
        try {
            client = new Socket(InetAddress.getLocalHost(),8888);
			//�������˵�IP,(���ֻ���ھ������ڵ�)�ҵ������,��ĸ���ʵ�ʶ���
            System.out.println("������");
            dos = new DataOutputStream(client.getOutputStream());
            dis = new DataInputStream(client.getInputStream());
			System.out.println("����Դ�ļ���·��");
			folderPath = (new Scanner(System.in)).next();
			
            transmit(new File(folderPath));
            
            String endFlag = "####";//��ʾ������ϵı��		
            byte buff[] = endFlag.getBytes();
            dos.write(buff,0,endFlag.length());
            dos.flush();
        }
        catch(IOException e){
        	e.printStackTrace();
        }
    }
    public void transmit(File f)throws IOException{//���Ǵ���ĺ���,���ҽ����ݹ�
        byte buff[];
        String path;
        int len;
        for(File f1 : f.listFiles())
        {   //����ͨ��if����ж�f1���ļ������ļ���                 
            if(f1.isDirectory()) { //fi���ļ���,����������˴���һ����Ϣ
            	//������	path = "%%%%"+ f1.getName();
                path = "%%%%"+(f1.getPath().replace(folderPath,""));
                buff = path.getBytes();
                dos.write(buff);
                dos.flush();
                dis.read();   //���ﲻ֪��ΪʲôҪ������һ�䣿����
                transmit(f1); //����f1���ļ���(��Ŀ¼),������������п��ܻ����ļ������ļ���,���Խ��еݹ�
            }
            else {
            	fis = new FileInputStream(f1);
                //������	fis=new FileInputStream(f1);
            	path = "$$$$"+(f1.getPath().replace(folderPath,""));//ͬ��,��ʾ����һ���ļ�������
                buff = path.getBytes();
                dos.write(buff);
                dos.flush();
                dis.read();
                dos.writeInt(fis.available());//����һ������ֵ,ָ����Ҫ������ļ��Ĵ�С
                dos.flush();
                dis.read();
                buff=new byte[1024*4];
                while(fis.available() > 0)//��ʼ�����ļ�
                {
                     len = fis.read(buff);
                     dos.write(buff,0,len);
                     dos.flush();
                }
                dos.flush();
                fis.close();
                dis.read();
            }
        }
    }
}