package Gen;

import java.util.ArrayList;
import java.util.List;

public class TestGen_1 {
/* 这里限定了类型参数是Animals，但是在Animals及其子类都可以进行添加
 * */
	public static void main(String[] args) {
		List<Animals> ls = new ArrayList<Animals>();
		ls.add(new Animals("嬛嬛",22));
		ls.add(new Dog("huahua",23));
		ls.add(new LovelyDog());
		System.out.println("----------------------");
		Demo<Number> d = new Demo<Number>();
		d.show(33);
		d.show(new Integer(6));
		d.show(new Float(6.0000f));
		//虽然限定了类型参数Number但是Number及其子类都可以作为泛型类方法中的参数
	}
}

class Animals{  //创建的类
	private String name;
	private int age;
	public Animals(String name, int age){
		this.age = age;
		this.name = name;
	}
	public Animals(){
		
	}
	public String toString(){
		return "[" + this.name + "," + this.age + "]";
	}
}
class Dog extends Animals{

	public Dog(String name, int age) {
		super(name, age);
		/* 如果父类没有无参构造，这里会自动加上super(name, age)，为什么呢？
		 * 因为子类构造会默认访问父类无参构造，也就是子类构造第一句默认有super()，会访问父类无参构造，完成父类初始化
		 * 但是父类没有无参构造的情况下，就会显式的调用父类带参构造，无论如父类何总会有一个构造方法被调用
		 * */
	}
	public Dog(){
		super();  //可以不写，默认就会有，会访问父类无参构造
	}
}
class LovelyDog extends Dog{
	public LovelyDog(){
		
	}
	public LovelyDog(String name,int age){
		super(name, age);
	}
}

class Demo<T>{
	public void show(T t){
		System.out.println(t);
	}
}