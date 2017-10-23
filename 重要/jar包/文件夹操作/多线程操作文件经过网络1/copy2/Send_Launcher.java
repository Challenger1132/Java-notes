package Demo.copy2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Send_Launcher {
/* 该类用于遍历目标文件夹，得到文件夹下面所有的文件，向Sender类传递一个File类对象，并启动不同的线程
 * 或者在两个run()方法前夹synchronized 
 * 但是为什么只有一个文件被发送？
 * 是Receiver中文件名定义一样最终文件被覆盖？？？
 * */
	private List<File> fileList = new ArrayList<>();
	
	public Send_Launcher(File Folder){
		getFileList(Folder);
	}
	
	public void getFileList(File Folder){
		File[] list = Folder.listFiles();
		if(list != null){
			for(File f : list){
				if(f.isDirectory()){
					getFileList(f);
				}
				else{
					fileList.add(f);
				}
			}
		}
	}
	private void launch(){
		for(File f : fileList){
			new Thread(new Sender(f)).start();
		}
	}
	public static void main(String[] args) throws IOException {
		String str = new String("C:/Users/xingyue/Desktop/测试");
		File srcFolder = new File(str);
		new Send_Launcher(srcFolder).launch();
	}
}
