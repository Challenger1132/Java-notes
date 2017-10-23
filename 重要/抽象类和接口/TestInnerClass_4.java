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
	//in就是OuterDemo这个类的属性成员而已，因为是静态的，所以可以通过类名来调用 OuterDemo.in.show();
	public static Inner in = new Inner(){
		@Override
		public void show() {
			System.out.println("hello world");
		}
	};
	
	//只是OuterDemo类的一个静态方法而已，通通过类名来调用，方法的返回值是Inner接口类型
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