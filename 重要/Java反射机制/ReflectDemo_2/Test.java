package ReflectDemo_2;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;
/* Java�����������ʵ��
 * �½�һ��class.txt�����ļ��������Ǽ�ֵ�ԣ����ȫ����������
 * ͨ��Properties�ཫ����ص�������
 * ���ݼ��ص������Լ����������������class�ļ�����ͨ��class�ļ����󣬻�ȡ��Ĺ���������
 * ͨ�����������󣬴��������Ӧ��ʵ����ͨ��class�ļ������ȡ���Method���󣬻��߻�ȡ���Գ�ԱField
 * ��������Ҫ�����޸ĵ�ʱ��ֱ���޸������ļ��е�ֵ������
 * */
public class Test {
	public static void main(String[] args) throws Exception{
		Properties property = new Properties();
		FileReader fr = new FileReader("class.txt");//��ȡ���ļ�
		property.load(fr); //�������ļ�
		String classname = property.getProperty("classname"); //���ݼ����ֵ
		String methodname = property.getProperty("methodname");
		
		Class c = Class.forName(classname);
		Constructor cons = c.getConstructor();
		Object obj = cons.newInstance();
		Method mm = c.getMethod("show");
		mm.invoke(obj);		
	}
}
