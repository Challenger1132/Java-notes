package ReflectDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {
/* ��ȡclass�ļ�����ķ���
 * �Ƽ�ʹ��Class.forName(String str);������һ���ַ��������Խ������õ������ļ���ȥ
 * */
	public static void main(String[] args) throws Exception {

		Class c = Class.forName("ReflectDemo.Student");
		//ע������һ�������ȫ��������ֻд����
		Class c1 = Student.class;//������ľ�̬���Եõ�class�ļ�����
		Student s = new Student();
		Class c2 = s.getClass();  //����Object�������getClass()�������ض����class�ļ�����
		Student s1 = new Student();
		Class c3 = s.getClass();
		System.out.println(c2 == c3);
		System.out.println(c == c1);
		System.out.println(c1 == c2);
		//���õõ����ֽ������õ���Ĺ�����
		System.out.println("�õ��޲εĹ�����");
		Constructor constructor1 = c.getConstructor(); //�õ��޲����Ĺ�����
		//�õ�һ��������������ӳ�� Class ��������ʾ�����ָ���������췽�������ݲ���ָ������ʵpublicȨ��
		//���Ҹ÷����ǿɱ���������Ҵ��ݵĲ���String,int���ͣ�����String�Լ�int��Ӧ��Class����
		//��˴��ݵ���String.class int.class
		System.out.println(constructor1);
		System.out.println("�õ�ָ�������Ĺ���������");
		Constructor constructor2 = c.getConstructor(String.class,int.class,String.class);
		//�õ������������Ĺ�����
		System.out.println(constructor2);
		System.out.println("��getConstructors()�����õ����е�publicȨ�޵Ĺ�����");
		Constructor[] con = c.getConstructors();  //���ع��������飬ע�ⷵ�ص���publicȨ�޵Ĺ�����
		for(Constructor constructor : con){
			System.out.println(constructor);
		}
		System.out.println("��getDeclaredConstructors()�����õ����й�����������Ȩ��");
		Constructor[] constructor3 = c.getDeclaredConstructors(); //�õ����й�������������Ȩ��
		for(Constructor constructor : constructor3){
			System.out.println(constructor);
		}
		System.out.println("���ù�����newInstance()���������������");
		//ͨ����������������
		Object obj = constructor1.newInstance();//constructor1��ȡ���Ƕ�����޲ι���
		System.out.println(obj);
		System.out.println("���ù�����newInstance()���������������");
		Object obj1 = constructor2.newInstance("����",22,"����");//constructor2��ȡ���Ƕ���Ĵ��ι���
		System.out.println(obj1);
		
		System.out.println("setAccessible(true)���������ʶ����˽�й��췽��");
		Constructor cons = c.getDeclaredConstructor(String.class);
		//�׳� java.lang.IllegalAccessException�쳣�����ܷ���private���εĹ��������ṩһ������
		cons.setAccessible(true);
		Object obj3 = cons.newInstance("˽�й�����");
		System.out.println(obj3);
		System.out.println("getFields()��ȡ���publicȨ�޵����Գ�Ա");
		Field[] field = c.getFields();
		for(Field f : field){
			System.out.println(f);
		}
		System.out.println("getFields()��ȡ������е����Գ�Ա");
		Field[] field1 = c.getDeclaredFields();
		for(Field f : field1){
			System.out.println(f);
		}
		System.out.println("��ȡ����ĵ�������");
		Class cc = Class.forName("ReflectDemo.Student");//ͨ�������õ��ֽ����ļ�����
		Constructor newconstructor = cc.getConstructor();//ͨ��class�ļ������ȡ�����������
		Object object = newconstructor.newInstance();//ͨ�������������ȡ��Ķ���
		System.out.println("����֮ǰ���������");
		System.out.println(object);
		Field singlefield = cc.getDeclaredField("address");//cc.getMethod()  cc.getConstructor()
		Field singlefield1 = cc.getDeclaredField("name"); 
		//name�����������Գ�Ա��ͨ���ֽ����ļ�����õ�name���Զ�Ӧ��Field����
		//(ͨ����ȫ�����õ������class�ļ�����),ͨ����Field����singlefield1��set()����
		//���õ���object�����ϸ�Field���������ֶ�����Ϊ�µ�ֵ,������ǽ�singlefield����
		//���ֶε�ֵ����Ϊ"�������ӿƼ���ѧ"����singlefield1������ֶε�ֵ����Ϊ"zhangsan"
		//����˽�����Բ���Ҫ��getDeclaredField()������ȡ����Ҫ��setAccessible()������ȡ����Ȩ��
		Field singlefield2 = cc.getField("age");  //age��publicȨ�ޣ���˲���getDeclaredFieldҲ��
		singlefield.setAccessible(true);
		System.out.println("����֮�����������");
		singlefield.set(object, "�������ӿƼ���ѧ");
		System.out.println(object);
		singlefield1.setAccessible(true);
		singlefield1.set(object, "zhangsan");
		System.out.println(object);
		singlefield2.set(object, 22);
		//��ָ����������ϴ� Field �����ʾ���ֶ�����Ϊָ������ֵ��
		System.out.println(object);
		System.out.println("��ȡ��ĳ�Ա����----------------");
		System.out.println("��ȡ�����е�publicȨ�޵ĳ�Ա����,��������ķ���");
		Class mclass = Class.forName("ReflectDemo.Student");
		Object oo = mclass.newInstance();
		Method[] method = mclass.getMethods();
		for(Method m : method){
			System.out.println(m);
		}
		System.out.println("��ȡ�����еĳ�Ա������ֻ�����Լ��ķ���");
		Method[] mpethod = mclass.getDeclaredMethods();//��ȡ�����еĳ�Ա������ֻ�����Լ��ķ���
		for(Method m : mpethod){
			System.out.println(m);
		}
		System.out.println("��ȡ��ָ���ĳ�Ա����");
		Method mm = mclass.getMethod("show",String.class, int.class);
		//��������������һ���Ƿ��������ڶ����Ƿ���������Ӧ��class���󣬶��ڹ��췽��������Ҫ���ݷ�����
		mm.invoke(oo, "����",22); 
		Method mm2 = mclass.getDeclaredMethod("show_1",String.class, int.class,String.class);
		mm2.setAccessible(true);
		mm2.invoke(oo, "����",22,"�������ӿƼ���ѧ");
		//һ�����ȴ������󣬶�����÷�������������·���õ�class�ļ�����ͨ�����ֽ�������ȡ�����ָ���ķ���
		//Ҳ����Method����ͨ���������invoke()�������÷�������������������ʵ�ʵĲ���������Ƿ���
		//ʵ����oo�������mm2��Ӧ�ķ���
		//invoke()�ķ���ֵ��Object����,��һ�������������,�ڶ�����Method�����������ʵ�ʲ���
		// �Դ���ָ��������ָ����������ɴ� Method �����ʾ�Ļ���������
		//����˽�з�������Ҫ��getDeclaredMethod()������ȡ����Ҫ��setAccessible()������ȡ����Ȩ��
		//��Ҫ���÷����������෽����Ӧ��Method����ȥ����invoke()����
		
		Method mm3 = mclass.getMethod("print", String.class);
		Object result = mm3.invoke(oo, "print������������÷����з���ֵ");
		System.out.println(result); //���invoke()�����ķ���ֵ
		
	}
}
