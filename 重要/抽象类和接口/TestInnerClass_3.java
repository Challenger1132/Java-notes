package innerClass_1;

public class TestInnerClass_3 {
/* 		new Demo(){
			@Override
			public void method() {
				System.out.println("method");
			}
		};
		以上就是一个Demo接口的实现类对象
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
		Demo demo = new Demo(){ //匿名类
			@Override
			public void method() {
				System.out.println("method1");
				System.out.println("访问外部类成员num = " + num);
				System.out.println("访问被final修饰的局部变量 cnt = " + cnt);
			}
		};
		demo.method();

		//或者这样调用匿名类中的方法
		new Demo1(){  //匿名类
			@Override
			public void method() {
				System.out.println("demo1 method");
			}
		}.method();
	}
}