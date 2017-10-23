package ReflectDemo_1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws Exception{
		Class c = Class.forName("ReflectDemo_1.Teacher");
		Constructor con = c.getConstructor();
		Object obj = con.newInstance();
		Method method = c.getMethod("show");
		method.invoke(obj);
		/* ͨ����ȫ�����class�ļ�����,ͨ��class�ļ�����õ���������ͨ���������������ʵ����
		 * ����ע���ȡ˽�й�������Ҫ��getDeclaredConstructor()���÷������������������������ʵ����ʱ��
		 * �����Ĳ���Ҫ��ǰ�߱���һ�£�Ҳ����˵����ȡ�޲ι�����������ͨ����������������ʵ����ͬ����õĴ��ι�����
		 * ���ܴ����޲ε�ʵ����ͨ��class�ļ�����õ�Method����ͨ��method�������ʵ������
		 * ����ͨ��class�ļ�������Ի��˽�д������Ĺ�������˽�д�������method����
		 * */
		Class c1 = Class.forName("ReflectDemo_1.Student");
		Constructor cons0 = c1.getDeclaredConstructor();
		//getDeclaredConstructor()����declared�ᱨ����Ϊ����û��publicȨ�޵��޲ι�����
		//���޲�����˽�й��������Ҫ��getDeclaredConstructor()�����⻹Ҫ��������Ȩ��
		cons0.setAccessible(true);
		Object object = cons0.newInstance();
		Method mm0 = c1.getDeclaredMethod("show", String.class,int.class,String.class);
		mm0.setAccessible(true);
		mm0.invoke(object, "����",33,"��������");
		//��Ϊ����õ������޲����Ĺ�����������ʵ����ʱ��Ҫ�͹�����һ��
		Constructor cons1 = c1.getConstructor(String.class,int.class);
		Object object1 = cons1.newInstance("������������",4444);
		Method mm = c1.getDeclaredMethod("show", String.class,int.class,String.class);
		mm.setAccessible(true);
		mm.invoke(object1, "����",33,"��������");
	}
}
