package Gen;

import java.util.ArrayList;
import java.util.List;

public class TestGen_1 {
/* �����޶������Ͳ�����Animals��������Animals�������඼���Խ������
 * */
	public static void main(String[] args) {
		List<Animals> ls = new ArrayList<Animals>();
		ls.add(new Animals("�֋�",22));
		ls.add(new Dog("huahua",23));
		ls.add(new LovelyDog());
		System.out.println("----------------------");
		Demo<Number> d = new Demo<Number>();
		d.show(33);
		d.show(new Integer(6));
		d.show(new Float(6.0000f));
		//��Ȼ�޶������Ͳ���Number����Number�������඼������Ϊ�����෽���еĲ���
	}
}

class Animals{  //��������
	private String name;
	private int age;
	public Animals(String name, int age){
		this.age = age;
		this.name = name;
	}
	public Animals(){
		
	}
	public String toString(){
		return "[" + this.name + "," + this.age + "]";
	}
}
class Dog extends Animals{

	public Dog(String name, int age) {
		super(name, age);
		/* �������û���޲ι��죬������Զ�����super(name, age)��Ϊʲô�أ�
		 * ��Ϊ���๹���Ĭ�Ϸ��ʸ����޲ι��죬Ҳ�������๹���һ��Ĭ����super()������ʸ����޲ι��죬��ɸ����ʼ��
		 * ���Ǹ���û���޲ι��������£��ͻ���ʽ�ĵ��ø�����ι��죬�����縸����ܻ���һ�����췽��������
		 * */
	}
	public Dog(){
		super();  //���Բ�д��Ĭ�Ͼͻ��У�����ʸ����޲ι���
	}
}
class LovelyDog extends Dog{
	public LovelyDog(){
		
	}
	public LovelyDog(String name,int age){
		super(name, age);
	}
}

class Demo<T>{
	public void show(T t){
		System.out.println(t);
	}
}