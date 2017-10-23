package SrcDemo;
import java.io.File;

public class TestCopyFile {

	public static void main(String[] args) {
//		System.out.println("复制文件夹--------------------");
//		String srcStr = "C:\\Users\\xingyue\\Desktop\\practice";
//		String desStr = "C:/Users/xingyue/Desktop/ADC";
//		File srcfile = new File(srcStr);
//		File desfile = new File(desStr);
//		desfile = new File(desfile,srcfile.getName());
//		desfile.mkdirs();
//		CopyFile.copyDirs(srcfile, desfile);
//		System.out.println("文件夹复制完毕");
		
		
//		System.out.println("将某个文件夹中指定后缀名的文件复制到另一个文件夹中--------------------");
//		String srcStr1 = "E:/";
//		String desStr1 = "C:/Users/xingyue/Desktop/jpg文件";
//		File srcfile1 = new File(srcStr1);
//		File desfile1 = new File(desStr1);
//		desfile1 = new File(desfile1,srcfile1.getName());
//		desfile1.mkdirs();
//		CopyFile.copySpecificNameFiles(srcfile1, desfile1,".jpg");
//		System.out.println("jpg文件复制完毕");
		
		
		System.out.println("删除文件夹--------------------");
		String path = "C:\\Users\\xingyue\\Desktop\\jpg文件";
		File deletefolder = new File(path);
		boolean flag = CopyFile.deieteFolder(deletefolder);
		if(flag == true)
			System.out.println("文件夹删除成功");
		else
			System.out.println("要删除的文件夹不存在");
	}
}
