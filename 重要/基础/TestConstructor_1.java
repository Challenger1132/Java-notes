
/* 	注意，super(...),this(...)在子类构造器中一定在第一条语句上，否则就会造成父类构造被重复调用
 * */

class A{
	public A(){
		System.out.println("父类无参构造");
	}
	public A(String str){
		System.out.println("父类dai参构造");
	}
}
class B extends A{
	public B(){
		//super();  //这句默认存在，即使没有显式写出来
		System.out.println("ZI类无参构造");
		//super();   //ERROR
		//super()不在第一条语句上，就会提示 Constructor call must be the first statement in a constructor
		//其实在该子类构造中默认有一句  super(),这样如果第二个super()通过，就会造成两次重复调用父类构造器
	}
	public B(String str){
		System.out.println("ZI类dai参构造");
	}
}

public class TestConstructor_1 {

	public static void main(String[] args) {
		B bb = new B();
		System.out.println("---------------------------");
		B bb1 = new B("hello world");
	}
}
