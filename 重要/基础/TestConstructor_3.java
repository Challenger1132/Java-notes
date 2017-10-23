

public class TestConstructor_3 {
/*
一个类的初始化过程
	先成员变量的初始化
		默认初始化
		显示初始化
	再构造方法初始化
子父类的初始化过程是分层初始化,先进行父类初始化，然后进行子类初始化
虽然子类中所有的构造方法默认都会访问父类中空参构造方法,子类中构造方法默认有一个 super(),
它仅仅表示要先初始化父类数据,再初始化子类数据, super(...)并不是指仅调用父类中的构造方法，当父类中有
成员变量的时候，也会进行初始化,初始化的时候是按照分层初始化进行的
 * */
	public static void main(String[] args) {
		B bb = new B();
	}
}
class A{
	public Demo demo = new Demo();
	public A(){
		System.out.println("A");
	}
}
class Demo{
	public Demo(){
		System.out.println("demo");
	}
}
class B extends A{
	public Demo demo = new Demo();
	public B(){
		super();
		//这里有一个super();但并不是走到这里的时候才开始进行父类成员变量初始化，构造器方法初始化
		//而是按照分层初始化过程进行的
		System.out.println("B");
	}
}
