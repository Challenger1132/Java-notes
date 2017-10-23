/*
 * final修饰局部变量
 * final修饰基本类型，值不能改变
 * final修饰引用类型，地址值不能改变，但是引用所指向的堆内存中的内容可以改变
 * 被final修饰的成员变量只能赋值一次(一旦赋值，便不能再次被赋值)
 * 要么在成员变量的位置赋值，要么在构造方法中进行赋值，或者在构造代码块中赋值(被final修饰的成员变量初始化时机是在构造方法完毕前)
 * */
class A{
	public int num = 1;
	public A(){
		num = 0;  //先成员变量初始化，再构造方法初始化，因此num的值被修改
	}
}

class B{
	public final int num = 1;
	public B(){
	//	num = 0; //ERROR  num被final修饰，其值不能被修改，只能赋值一次
	}
}

class C{
	public final int num;
	public C(){
		num = 0;  //要么在成员变量的位置赋值，要么在构造方法完毕之前进行赋值
	}
}
class D{
	public final int num;
	
	{ //构造代码块
		num = 1;  //因为会先走构造代码块，一旦赋值，便不能再次赋值
	}
	
	public D(){
		//num = 0; //ERROR
	}
}
public class TestFinal {
	public static void main(String[] args) {
		A aa = new A();
		System.out.println(aa.num);

		B bb = new B();
		System.out.println(bb.num);
		
		C cc = new C();
		System.out.println(cc.num);
		
		D dd = new D();
		System.out.println(dd.num);
	}
}
