package innerClass_1;

public class TestInnerClass_4 {

	public static void main(String[] args) {
		OuterDemo.in.show();
		OuterDemo.method().show();
		OuterDemo.method1().show();
	}
}

interface Inner{
	void show();
}

class OuterDemo {
	//in����OuterDemo���������Գ�Ա���ѣ���Ϊ�Ǿ�̬�ģ����Կ���ͨ������������ OuterDemo.in.show();
	public static Inner in = new Inner(){
		@Override
		public void show() {
			System.out.println("hello world");
		}
	};
	
	//ֻ��OuterDemo���һ����̬�������ѣ�ͨͨ�����������ã������ķ���ֵ��Inner�ӿ�����
	public static Inner method(){
		return new Inner(){
			@Override
			public void show() {
				System.out.println("hello world");
			}
		};
	}
	
	public static Inner method1(){
		return in;
	}
}