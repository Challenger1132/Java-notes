package innerClass_1;

public class TestInnerClass_3 {
/* 		new Demo(){
			@Override
			public void method() {
				System.out.println("method");
			}
		};
		���Ͼ���һ��Demo�ӿڵ�ʵ�������
 * */
	public static void main(String[] args) {
		MyDemo mdemo = new MyDemo();
		mdemo.show();
		
	}
}
interface Demo{
	public abstract void method();
}
interface Demo1{
	public abstract void method();
}
class MyDemo{
	public int num = 10;
	
	public void show(){
		final int cnt = 20;
		Demo demo = new Demo(){ //������
			@Override
			public void method() {
				System.out.println("method1");
				System.out.println("�����ⲿ���Աnum = " + num);
				System.out.println("���ʱ�final���εľֲ����� cnt = " + cnt);
			}
		};
		demo.method();

		//�������������������еķ���
		new Demo1(){  //������
			@Override
			public void method() {
				System.out.println("demo1 method");
			}
		}.method();
	}
}