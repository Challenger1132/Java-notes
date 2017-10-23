
/*
具有继承关系体系的类的初始化过程
		A:继承体系的所有静态成员初始化(包括静态成员变量,静态代码块,先父类,后子类)
		B:父类初始化(普通成员的初始化---->构造方法的调用)
		C:子类初始化(普通成员的初始化---->构造方法的调用)
		也就是：
		父类静态成员(包括静态成员变量,静态代码块)初始化
		子类静态成员(包括静态成员变量,静态代码块)初始化
		父类普通成员初始化(或父类构造代码块执行)
		父类构造方法调用
		子类普通成员初始化(或子类构造代码块执行)
		子类构造方法调用
执行结果：
爷爷类静态代码块
父类静态成员变量
父类静态代码块
子类静态成员变量
子类静态代码块
爷爷类普通成员变量
爷爷类构造代码块
爷爷类构造方法
父类普通成员变量
父类构造代码块
父类构造方法
子类普通成员变量
子类构造代码块
子类构造方法

 * */
public class TestExtendsClassIni {
	public static void main(String[] args) {
		C cc = new C();
	}
}
class MM{ //用于测试
	public MM(String str){
		System.out.println(str);
	}
}

class A{
	public MM mm = new MM("爷爷类普通成员变量");
	public static MM mmstatic = new MM("爷爷类静态成员变量");
	static{
		System.out.println("爷爷类静态代码块");
	}
	{
		System.out.println("爷爷类构造代码块");
	}
	public A(){
		System.out.println("爷爷类构造方法");
	}
}

class B extends A{
	public MM mm = new MM("父类普通成员变量");
	public static MM mmstatic = new MM("父类静态成员变量");
	
	static{
		System.out.println("父类静态代码块");
	}
	{
		System.out.println("父类构造代码块");
	}
	public B(){
		System.out.println("父类构造方法");
	}
}

class C extends B{
	public MM mm = new MM("子类普通成员变量");
	public static MM mmstatic = new MM("子类静态成员变量");
	static{
		System.out.println("子类静态代码块");
	}
	{
		System.out.println("子类构造代码块");
	}
	public C(){
		System.out.println("子类构造方法");
	}
}