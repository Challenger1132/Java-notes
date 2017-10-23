package SrcDemo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
/* ʵ���ļ��в㼶��ϵ�ĸ���
 * ʵ���ļ������ض���׺���ļ��ĸ���(���༶�ʼۼ�������ͼƬ���Ƶ�һ���ļ����е�)
 * �ļ��в㼶Ŀ¼��ɾ��
 * �����ļ��и��Ƶ�������Ҫ���õĵݹ��˼��
 * 		���Դ�ļ��еĳ���File���󣬵õ��ļ����������ļ���File��������
 		forѭ�����ļ����б���   ���ļ�����ֱ�Ӹ��ƣ�ע�����Ŀ���ļ���Ŀ���ļ��м���������ļ����ļ���
 		���ļ��У���Ŀ���ļ������ȴ������ļ���(Ŀ¼)���ݹ����
 * ���Ŀ���ļ��ָ��Ƶ�Դ�ļ����ļ����У���ô���ʱ��Դ�ļ�������Զ�������������
 * 		String srcFolder = new String("C:/Users/xingyue/Desktop/practice");
		String desFolder = new String("C:/Users/xingyue/Desktop/practice/1234");
		��Դ�ļ��е��ļ��ָ��Ƶ�Դ�ļ��У�Դ�ļ�����Զ��������
 * ���������ļ����Լ��ļ���ַ������
 		String srcFolder = new String("C:/Users/xingyue/Desktop/practice"); ����
 		String srcFolder = new String("C:\\Users\\xingyue\\Desktop\\practice"); ���ǲ�����
 		String srcFolder = new String("C:\Users\xingyue\Desktop\practice");
 		������ֱ����ʾ��ָ���ļ��е�·��
 		����
 		System.out.println("����Դ�ļ���");
		File srcFolder = new File((new Scanner(System.in)).next());
		System.out.println("����Ŀ���ļ���");
		File desFolder = new File((new Scanner(System.in)).next());
		ֱ�Ӽ����ļ���·��
		������ C:/Users/xingyue/Desktop/practice
		����    C:\\Users\\xingyue\\Desktop\\practice
		����    C:\Users\xingyue\Desktop\practice
 * */
public class CopyFile {
	public static void copyFile(File srcfile, File dirfile){
		BufferedInputStream bis = null;  // ����������Ҫ�޽��ڽڵ���֮��
		BufferedOutputStream bos = null;
		byte[] buff = new byte[4*1024]; //���û������Ĵ�СΪ4K�ֽ�
		try{
			bis = new BufferedInputStream(new FileInputStream(srcfile));
			bos = new BufferedOutputStream(new FileOutputStream(dirfile));
			int len = bis.read(buff);  //����ʵ�ʶ�ȡ���ֽ���Ŀ
			while(-1 != len){
				bos.write(buff, 0, len); //������д����������������ļ���ȥ
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
	
	/*��һ���ļ��������ض���׺���ĵ��ļ����Ƶ�ָ�����ļ������棬�������ļ��еĲ㼶�ṹ
	 * */
	public static void copySpecificNameFiles(File srcFolder, File desFolder, String postfis){
		File[] filelist = srcFolder.listFiles();
		if(filelist != null){
			for(File f : filelist){
				if(f.isFile() && f.getName().endsWith(postfis)){
					copyFile(f,new File(desFolder,f.getName()));
				}
				else{
					copySpecificNameFiles(f,desFolder,postfis);  //����Ŀ���ļ����ǲ��仯��
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
			folder.delete();  //ɾ����Ŀ¼
		}
		return true;
	}
}






