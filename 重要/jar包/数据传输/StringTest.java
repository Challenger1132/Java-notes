package Demo_3;

import java.io.File;
import java.util.Scanner;

public class StringTest {
	public static String string;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		string = sc.next();
		System.out.println(string);
		File file = new File(string);
		new StringTest().searchFile(file);
	}
	public void searchFile(File file){
		File[] filelist = file.listFiles();
		for(File f : filelist){
			System.out.println(f.getPath().replace(string, ""));
//			if(f.isDirectory()){
//				System.out.println(f.getPath().replace(string, ""));
//				searchFile(f);
//			}
//			else{
//				System.out.println(f.getPath().replace(string, ""));
//			}
		}
	}
}
