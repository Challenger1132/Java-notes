
/* 继承中构造方法的关系
	子类中所有的构造方法默认都会访问父类中空参构造方法
	实际上子类每一个构造方法的第一条语句默认都是 super();即使并没有显示的写出来 super()会调用父类空参构造方法,
	因为子类继承父类,可能会使用父类的数据,子类初始化之前，一定要先完成父类数据的初始化,
	(super()会调用父类空参构造方法完成父类数据初始化)
	但是子类显式的进行调用了父类其他构造，空参构造便不会被调用，也就是说子类构造器中不调用父类构造，就会默认调用父类
	空参构造，调用了其他构造器，走的便是这个调用的构造器，但是在子类中一定要有一个父类构造器被调用，以便父类数据初始化
 * */
class Father {
	public Father(){
		System.out.println("父类空参构造");
	}
	public Father(String str){
		System.out.println("父类dai参构造");
	}
}
class Son extends Father {
	public Son(){
		//super(); 这里实际上默认有super();会默认调用父类空参构造器，即使并没有显示写出来
		//若显式调用了父类其他构造，走的便是调用的构造器，空参构造边不会被调用
		//
		System.out.println("zi类空参构造");
	}
	public Son(String str){
		//super(); 这里实际上默认有super();会默认调用父类空参构造器，即使并没有显示写出来
		System.out.println("zi类dai参构造");
	}
}

class TestConstructor {
	public static void main(String[] args) {
		Son s = new Son();
		System.out.println("-----------------------");
		Son s1 = new Son("hello world");
	}
}