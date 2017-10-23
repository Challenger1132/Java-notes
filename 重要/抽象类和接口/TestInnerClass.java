package innerClass;

/* 
在一个类内部，类所有方法的外部定义的类属于内部类
内部类与其外部类相互访问的特点：
	内部类可以直接访问外部类成员(包括private权限的成员)，外部类不能直接访问内部类成员,外部类访问内部类成员
	要创建内部类对象;若内部类被static修饰，只能访问被static修饰的外部类成员
其他类访问一个类的内部类的成员
	外部类名.内部类名  对象名 = 外部类对象.内部类对象
	Outer.Inner oi = new Outer().new Inner();  (Outer是外部类，Inner是该外部类的内部类)
	要想访问 Inner必须先通过Outer，通过Outer对Inner进行访问控制，这里可以理解为内部类是外部类的一个成员
	若内部类被static修饰的情况  (OuterDemo是外部类，InnerDemo是该外部类的内部类)
	OuterDemo.InnerDemo oiDemo = new OuterDemo.InnerDemo();

 * */
public class TestInnerClass {
	public static void main(String[] args) {
		Outer.Inner oi = new Outer().new Inner();
		oi.show();
		
		OuterDemo.InnerDemo oiDemo = new OuterDemo.InnerDemo();
		//new的是内部类但是由外部类来限定
		oiDemo.show();
		OuterDemo.InnerDemo.play();//内部类方法是静态的，且内部类被static修饰，可以这样调用
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
			System.out.println("内部类访问外部类成员,num = " + num);
			//被static修饰的内部类要访问外部类成员，只能访问被static修饰的外部类成员
		}
		public static void play(){
			System.out.println("Inner Class with static method");
		}
	}
}