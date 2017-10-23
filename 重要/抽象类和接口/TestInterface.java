public class TestInterface {
	
	public static void main(String[] args) {
		A aa = new A();
		System.out.println(aa.num);
		System.out.println(Inter.num);
		//num是 public static final来修饰的所以num可以通过接口名来访问
		// aa.num = 20; //error
		//因为类A中的num成员是public static final的所以只能进行一次赋值
	}
}
interface Inter{
	int num = 10;
	//接口中的属性成员是 public static final来修饰的，方法是 public abstract 来修饰的
	//这些修饰符可以全部或者部分省略，因此接口中属性成员可以通过接口名Inter来访问，且只能进行一次赋值
	//其实就是 public static final int num = 10;
}
class A implements Inter{
	//类A实现接口 Inter
	public A(){
		super(); //子类构造中通过super访问父类构造方法
	}
}