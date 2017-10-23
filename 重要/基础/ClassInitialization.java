package xxxxxx;
/* 生成一个类的类对象，首次访问类的静态数据成员的时候就会进行类的初始化
 * 静态初始化只会在类的Class对象首次加载是进行一次
当用到某个类的时候(创建该类的类对象，首次访问该类中的静态数据成员时，即使没有创建该类的类对象)
就会对该类进行初始化，静态初始化(静态成员变量以及静态代码块)，普通成员变量初始化，构造器的执行
若再次创建该类的类对象，静态初始化(静态成员变量以及静态代码块)就不会执行，即静态初始化只在类首次加载时只初始化一次
普通成员变量以及构造器还会再次进行初始化，通过首次访问类的静态数据成员的方式只会初始化类的静态成员，
类的普通成员以及构造器并不会进行初始化
静态初始化动作只在必要时进行，静态初始化只在类首次加载时只初始化一次
 * */
public class ClassInitialization {

	public static void main(String[] args) {
		System.out.println("inside main()");
//		Cups.cup1.f(99);  
		//访问Cups类的静态数据成员cup1，然后再调用f()方法
		//但是这是指初始化了类的静态数据成员，类的普通成员变量以及构造器都没有初始化
	}
	static Cups cups1 = new Cups();
	static Cups cups2 = new Cups();
	// new Cups()依次走了Cups类的静态成员(静态成员变量，静态代码块)，普通成员变量，构造器
	// 以上是ClassInitialization类的两个静态成员变量，要先进行初始化，进而会进行Cups类的初始化
	// 即使有两句new Cups()，Cups类中的静态成员只会进行一次初始化，第二次 new Cups()时，Cups类的静态
	// 成员不会再次进行初始化，但是普通成员变量以及构造器还会进行初始化
}
class Cup{
	public Cup(int maker){
		System.out.println("Cup" + "(" + maker + ")");
	}
	public void f(int maker){
		System.out.println("f" + "(" + maker + ")");
	}
}
class Cups{
	public static Cup cup1;
	public static Cup cup2;
	public Cup cup = new Cup(11);
	static {
		cup1 = new Cup(1);
		cup2 = new Cup(2);
	}
	public Cups(){
		System.out.println("Cups()构造");
	}
}