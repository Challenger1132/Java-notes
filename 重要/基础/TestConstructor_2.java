

public class TestConstructor_2 {

	public static void main(String[] args) {
		D dd = new D();
		System.out.println("---------------------------");
		D dd1 = new D("hello  world");
	}
}
class C{
	//这里父类无参构造便不存在了..
	public C(String str){
		System.out.println("父类dai参构造");
	}
}

class D extends C{
	public D(){
		//父类中没有无参构造，这时应该显式调用父类其他构造器
		super("hello world");  //将此句注释会报错，并提示
		//Implicit super constructor C() is undefined.
		// Must explicitly invoke another constructor
		// 父类空参构造C()未定义，必须显式调用父类其他构造器
		System.out.println("ZI类无参构造");
	}
	public D(String str){
		this();  //父类中没有无参构造，但这里并没有显式调用父类其他构造，并没有报错，
		//因为这里有this()，会调用子类无参构造，子类的无参构造中有父类的构造器调用，
		//也就是无论怎么调用，子类构造中一定有一个父类构造器被调用
		System.out.println("ZI类dai参构造");
	}
}