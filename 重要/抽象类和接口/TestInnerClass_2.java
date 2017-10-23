package innerClass_1;

public class TestInnerClass_2 {
	public static void main(String[] args) {
		Outer outer = new Outer();
		outer.show();
	}
}
class Outer {
	public int num = 10; //外部类属性成员
	
	public void show(){
		
		final int cnt = 10;
		class Inner{ //局部内部类
			
			public void method(){
				System.out.println("Inner Method");
				System.out.println("内部类访问外部类成员num = " + num);
				// 内部类可以直接访问外部类成员
				System.out.println("内部类访问被final修饰的局部变量cnt = " + cnt);
				//局部内部类可以直接访问外部类成员，访问包裹该内部类的方法中被final修饰的局部变量
				//(该局部变量与局部内部类位于一个方法中)，cnt会变成常量值存在于堆内存中
			}
		} 
		Inner inner = new Inner();
		inner.method();
	} 	//方法调用完毕局部变量cnt会消失,但是局部类对象并不会在堆中消失，还在继续使用局部变量cnt
		//因此cnt要用final修饰
}