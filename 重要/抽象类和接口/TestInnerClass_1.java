package innerClass;

public class TestInnerClass_1 {
	public static void main(String[] args) {
		OuterClass.InnerClass oi = new OuterClass().new InnerClass();
		//一个类访问另一个类的内部类成员
		oi.show();
	}
}
class OuterClass{
	
	public int num = 10; //外部类属性成员
	class InnerClass{
		public int num = 20;  //内部类属性成员
		public void show(){  //内部类方法成员
			int num = 30;     //方法中的局部变量
			System.out.println(num);
			System.out.println(this.num);
			System.out.println(OuterClass.this.num);
		}
	}
}