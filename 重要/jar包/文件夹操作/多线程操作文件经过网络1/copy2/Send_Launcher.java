package Demo.copy2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Send_Launcher {
/* �������ڱ���Ŀ���ļ��У��õ��ļ����������е��ļ�����Sender�ഫ��һ��File����󣬲�������ͬ���߳�
 * ����������run()����ǰ��synchronized 
 * ����Ϊʲôֻ��һ���ļ������ͣ�
 * ��Receiver���ļ�������һ�������ļ������ǣ�����
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
		String str = new String("C:/Users/xingyue/Desktop/����");
		File srcFolder = new File(str);
		new Send_Launcher(srcFolder).launch();
	}
}
