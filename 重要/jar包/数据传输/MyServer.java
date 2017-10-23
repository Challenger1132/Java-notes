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
            System.out.println("端口号:" + server.getLocalPort());
            client = server.accept();
			if (client != null){
				System.out.println("连接完毕");
			}
			System.out.println ("输入目标地址");
			String fileStr = (new Scanner(System.in)).next();
			
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());
            
            String answer = "g";
            byte ans[] = answer.getBytes();
            byte[] buff = new byte[1024 * 4];
            int len;
            new File(fileStr).mkdirs();
            while(flag){
                len = dis.read(buff);   // 将读取的数据存放在buff中，返回实际读取的字节数目
                dos.write(ans);
                String select = new String(buff,0,len);  //转化为字符串
                if(select.contains("%%%%")) {
                	//这里是目的文件夹路径拼接上接受的文件夹路径，并去掉接收路径中的 标志位 "%%%%"
                    File f = new File(fileStr + (select.replace("%%%%","")));
                    System.out.println("接收到的文件夹是" + select);
                    f.mkdirs();   
                }
                else if(select.contains("$$$$")){
                	//这里是目的文件夹路径拼接上接受的文件路径，并去掉接收路径中的 标志位 "$$$$"
                	//有一层路径，深入一层，有多层路径深入多层路径中，这样保存了文件夹层级结构
                	//是文件将接收的文件路径与键入的路径组合成新的路径，传给FileOutputStream构造器，使之关联一个文件
                    fos = new FileOutputStream(fileStr + (select.replace("$$$$","")));
                    String cs;
                    boolean cflag = true;
                    int tip = dis.readInt();  //tip是接收的一个数字
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