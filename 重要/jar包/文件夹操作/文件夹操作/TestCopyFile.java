package SrcDemo;
import java.io.File;

public class TestCopyFile {

	public static void main(String[] args) {
//		System.out.println("�����ļ���--------------------");
//		String srcStr = "C:\\Users\\xingyue\\Desktop\\practice";
//		String desStr = "C:/Users/xingyue/Desktop/ADC";
//		File srcfile = new File(srcStr);
//		File desfile = new File(desStr);
//		desfile = new File(desfile,srcfile.getName());
//		desfile.mkdirs();
//		CopyFile.copyDirs(srcfile, desfile);
//		System.out.println("�ļ��и������");
		
		
//		System.out.println("��ĳ���ļ�����ָ����׺�����ļ����Ƶ���һ���ļ�����--------------------");
//		String srcStr1 = "E:/";
//		String desStr1 = "C:/Users/xingyue/Desktop/jpg�ļ�";
//		File srcfile1 = new File(srcStr1);
//		File desfile1 = new File(desStr1);
//		desfile1 = new File(desfile1,srcfile1.getName());
//		desfile1.mkdirs();
//		CopyFile.copySpecificNameFiles(srcfile1, desfile1,".jpg");
//		System.out.println("jpg�ļ��������");
		
		
		System.out.println("ɾ���ļ���--------------------");
		String path = "C:\\Users\\xingyue\\Desktop\\jpg�ļ�";
		File deletefolder = new File(path);
		boolean flag = CopyFile.deieteFolder(deletefolder);
		if(flag == true)
			System.out.println("�ļ���ɾ���ɹ�");
		else
			System.out.println("Ҫɾ�����ļ��в�����");
	}
}
