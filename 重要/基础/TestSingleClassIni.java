
/* 
执行结果
静态成员变量
静态代码块
普通成员变量
构造代码块
类A的构造方法

1  没有继承关系的类的初始化过程
	先成员变量的初始化
		静态成员初始化和静态代码块初始化(和定义位置有关)
		普通成员变量的初始化
	再构造方法初始化
		构造代码块初始化(构造代码块和普通成员变量初始化和定义位置有关)
		构造方法调用
 * */
public class TestSingleClassIni {
	public static void main(String[] args) {
		A aa = new A();
	}
}
class MM{
	public MM(String str){
		System.out.println(str);
	}
}
class A{
	public static MM mm = new MM("静态成员变量"); 
	static{
		System.out.println("静态代码块");
	}
	public MM mm1 = new MM("普通成员变量");
	{
		System.out.println("构造代码块");
	}
	public A(){
		System.out.println("类A的构造方法");
	}
}
