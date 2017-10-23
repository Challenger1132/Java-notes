package SrcDemo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
/* 实现文件夹层级关系的复制
 * 实现文件夹中特定后缀名文件的复制(将多级问价夹中所有图片复制到一个文件夹中等)
 * 文件夹层级目录的删除
 * 关于文件夹复制的问题主要是用的递归的思想
 * 		获得源文件夹的抽象File对象，得到文件夹中所有文件的File对象数组
 		for循环对文件进行遍历   是文件，就直接复制，注意这个目的文件是目标文件夹加上所获得文件的文件名
 		是文件夹，在目标文件夹中先创建该文件夹(目录)，递归调用
 * 如果目标文件又复制到源文件的文件夹中，那么这个时候源文件夹是永远遍历不完的如下
 * 		String srcFolder = new String("C:/Users/xingyue/Desktop/practice");
		String desFolder = new String("C:/Users/xingyue/Desktop/practice/1234");
		将源文件中的文件又复制到源文件中，源文件夹永远遍历不完
 * 关于输入文件夹以及文件地址的问题
 		String srcFolder = new String("C:/Users/xingyue/Desktop/practice"); 或者
 		String srcFolder = new String("C:\\Users\\xingyue\\Desktop\\practice"); 但是不可以
 		String srcFolder = new String("C:\Users\xingyue\Desktop\practice");
 		以上是直接显示的指定文件夹的路径
 		但是
 		System.out.println("输入源文件夹");
		File srcFolder = new File((new Scanner(System.in)).next());
		System.out.println("输入目的文件夹");
		File desFolder = new File((new Scanner(System.in)).next());
		直接键入文件夹路径
		可以是 C:/Users/xingyue/Desktop/practice
		或者    C:\\Users\\xingyue\\Desktop\\practice
		或者    C:\Users\xingyue\Desktop\practice
 * */
public class CopyFile {
	public static void copyFile(File srcfile, File dirfile){
		BufferedInputStream bis = null;  // 包裹流，需要嫁接在节点流之上
		BufferedOutputStream bos = null;
		byte[] buff = new byte[4*1024]; //设置缓冲区的大小为4K字节
		try{
			bis = new BufferedInputStream(new FileInputStream(srcfile));
			bos = new BufferedOutputStream(new FileOutputStream(dirfile));
			int len = bis.read(buff);  //返回实际读取的字节数目
			while(-1 != len){
				bos.write(buff, 0, len); //将数据写到输出流所关联的文件中去
				len = bis.read(buff);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				bis.close();
				bos.close();
			}
			catch(Exception	e){
				e.printStackTrace();
			}
		}
	}
	
	public static void copyFolder(File srcdir, File desdir){
		File[] filelist = srcdir.listFiles();
		if(filelist != null){
			for(File f : filelist){
				if(f.isDirectory()){
					File newfile = new File(desdir,f.getName());
					newfile.mkdirs();
					copyFolder(f,newfile);
				}
				else{
					copyFile(f,new File(desdir,f.getName()));
				}
			}
		}
	}
	
	/*将一个文件夹下面特定后缀名的的文件复制到指定的文件夹下面，不包含文件夹的层级结构
	 * */
	public static void copySpecificNameFiles(File srcFolder, File desFolder, String postfis){
		File[] filelist = srcFolder.listFiles();
		if(filelist != null){
			for(File f : filelist){
				if(f.isFile() && f.getName().endsWith(postfis)){
					copyFile(f,new File(desFolder,f.getName()));
				}
				else{
					copySpecificNameFiles(f,desFolder,postfis);  //这里目标文件夹是不变化的
				}
			}
		}
	}
	public static void deleteFile(File file){
		if(file.exists()){
			file.delete();
		}
	}
	public static boolean deieteFolder(File folder){
		if(!folder.exists()){
			return false;
		}
		File[] filelist = folder.listFiles();
		if(filelist != null){
			for(File f : filelist){
				if(f.isFile()){
					f.delete();
				}
				else{
					deieteFolder(f);
				}
			}
			folder.delete();  //删除根目录
		}
		return true;
	}
}






