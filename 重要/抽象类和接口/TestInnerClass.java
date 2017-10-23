package innerClass;

/* 
��һ�����ڲ��������з������ⲿ������������ڲ���
�ڲ��������ⲿ���໥���ʵ��ص㣺
	�ڲ������ֱ�ӷ����ⲿ���Ա(����privateȨ�޵ĳ�Ա)���ⲿ�಻��ֱ�ӷ����ڲ����Ա,�ⲿ������ڲ����Ա
	Ҫ�����ڲ������;���ڲ��౻static���Σ�ֻ�ܷ��ʱ�static���ε��ⲿ���Ա
���������һ������ڲ���ĳ�Ա
	�ⲿ����.�ڲ�����  ������ = �ⲿ�����.�ڲ������
	Outer.Inner oi = new Outer().new Inner();  (Outer���ⲿ�࣬Inner�Ǹ��ⲿ����ڲ���)
	Ҫ����� Inner������ͨ��Outer��ͨ��Outer��Inner���з��ʿ��ƣ�����������Ϊ�ڲ������ⲿ���һ����Ա
	���ڲ��౻static���ε����  (OuterDemo���ⲿ�࣬InnerDemo�Ǹ��ⲿ����ڲ���)
	OuterDemo.InnerDemo oiDemo = new OuterDemo.InnerDemo();

 * */
public class TestInnerClass {
	public static void main(String[] args) {
		Outer.Inner oi = new Outer().new Inner();
		oi.show();
		
		OuterDemo.InnerDemo oiDemo = new OuterDemo.InnerDemo();
		//new�����ڲ��൫�����ⲿ�����޶�
		oiDemo.show();
		OuterDemo.InnerDemo.play();//�ڲ��෽���Ǿ�̬�ģ����ڲ��౻static���Σ�������������
	}
}
class Outer{
	class Inner{
		public void show(){
			System.out.println("Inner Class");
		}
	}
}
class OuterDemo{
	public static int num = 10;
	static class InnerDemo{
		public void show(){
			System.out.println("Inner Class");
			System.out.println("�ڲ�������ⲿ���Ա,num = " + num);
			//��static���ε��ڲ���Ҫ�����ⲿ���Ա��ֻ�ܷ��ʱ�static���ε��ⲿ���Ա
		}
		public static void play(){
			System.out.println("Inner Class with static method");
		}
	}
}