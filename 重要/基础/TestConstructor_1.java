
/* 	ע�⣬super(...),this(...)�����๹������һ���ڵ�һ������ϣ�����ͻ���ɸ��๹�챻�ظ�����
 * */

class A{
	public A(){
		System.out.println("�����޲ι���");
	}
	public A(String str){
		System.out.println("����dai�ι���");
	}
}
class B extends A{
	public B(){
		//super();  //���Ĭ�ϴ��ڣ���ʹû����ʽд����
		System.out.println("ZI���޲ι���");
		//super();   //ERROR
		//super()���ڵ�һ������ϣ��ͻ���ʾ Constructor call must be the first statement in a constructor
		//��ʵ�ڸ����๹����Ĭ����һ��  super(),��������ڶ���super()ͨ�����ͻ���������ظ����ø��๹����
	}
	public B(String str){
		System.out.println("ZI��dai�ι���");
	}
}

public class TestConstructor_1 {

	public static void main(String[] args) {
		B bb = new B();
		System.out.println("---------------------------");
		B bb1 = new B("hello world");
	}
}
