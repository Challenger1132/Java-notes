package Demo_3;
import java.io.*;
import java.net.*;
import java.util.Scanner;
/* path = "%%%%" + (f1.getPath().replace(folderPath,""));         	
 * path = "%%%%" + f1.getName();
 * 这两个地方的区别在哪里呢？ fileStr是键入的路径，对文件中的文件进行遍历，第一条得到的是标志加上去掉键入的路径得到的字符串
 * 第二句的得到的是最后一级名称，如果深入多层文件夹目录，第一句得到的是去掉键入的路径之后的路径，随着文件夹的深入，路径会越来
 * 越长，遍历文件层级路径会保存，第二句随着文件夹的深入只会得到最后一级名称，前面的路径会丢失，也就是说遍历经过的文件夹路径不
 * 会保存
 * 这个传输过程大致是
 * 假设键入路径是     C:\Users\xingyue\Desktop\practice
 * 遍历该目录，得到
					\C
					\Java
					\Test_pic.jpg
					\不是因为寂寞才想你.mp3
					\二叉树.java
					\关于其他(勿删).java
					\勿删——1.java
					\截图
 在接收端判断得到C:\Users\xingyue\Desktop\practice +　以上路径组成的路径
 是文件直接接受，是文件夹就在C:\Users\xingyue\Desktop\practice基础上创建文件夹
 再次深入文件夹，路径会变化
 					\C\双向链表   (路径变长了)
					\Java\..
					\Test_pic.jpg
					\不是因为寂寞才想你.mp3
					\二叉树.java
					\关于其他(勿删).java
					\勿删——1.java
					\截图\....
是在原来的基础上增加新的路径，正是这些增加的路径才是接收端创建文件夹，创建文件层级结构的依据
加入只获得最后一级名称，得到的只是   C:\Users\xingyue\Desktop\practice +　最后一级名称   组成的文件夹或者文件
则文件层级结构就会消失
 * */
public class MyClient 
{
    Socket client;
    boolean flag = true;
    FileInputStream fis;//此输入流负责读取本机上要传输的文件
    DataOutputStream dos;//此输出流负责向另一台电脑(服务器端)传输数据
    DataInputStream dis;//此输入流负责读取另一台电脑的回应信息
	String folderPath;
    public static void main(String[] args) {
        new MyClient().ClientStart();
    }
    
    public void ClientStart(){
        try {
            client = new Socket(InetAddress.getLocalHost(),8888);
			//服务器端的IP,(这个只是在局域网内的)我的是这个,你的根据实际而定
            System.out.println("已连接");
            dos = new DataOutputStream(client.getOutputStream());
            dis = new DataInputStream(client.getInputStream());
			System.out.println("输入源文件夹路径");
			folderPath = (new Scanner(System.in)).next();
			
            transmit(new File(folderPath));
            
            String endFlag = "####";//提示传输完毕的标记		
            byte buff[] = endFlag.getBytes();
            dos.write(buff,0,endFlag.length());
            dos.flush();
        }
        catch(IOException e){
        	e.printStackTrace();
        }
    }
    public void transmit(File f)throws IOException{//这是传输的核心,而且将被递归
        byte buff[];
        String path;
        int len;
        for(File f1 : f.listFiles())
        {   //首先通过if语句判断f1是文件还是文件夹                 
            if(f1.isDirectory()) { //fi是文件夹,则向服务器端传送一条信息
            	//区别于	path = "%%%%"+ f1.getName();
                path = "%%%%"+(f1.getPath().replace(folderPath,""));
                buff = path.getBytes();
                dos.write(buff);
                dos.flush();
                dis.read();   //这里不知道为什么要加上这一句？？？
                transmit(f1); //由于f1是文件夹(即目录),所以它里面很有可能还有文件或者文件夹,所以进行递归
            }
            else {
            	fis = new FileInputStream(f1);
                //区别于	fis=new FileInputStream(f1);
            	path = "$$$$"+(f1.getPath().replace(folderPath,""));//同上,表示这是一个文件的名称
                buff = path.getBytes();
                dos.write(buff);
                dos.flush();
                dis.read();
                dos.writeInt(fis.available());//传输一个整型值,指明将要传输的文件的大小
                dos.flush();
                dis.read();
                buff=new byte[1024*4];
                while(fis.available() > 0)//开始传送文件
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