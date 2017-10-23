package Gen;
/* 泛型类中的方法与泛型方法
 * */
public class TestGen_2 {

	public static void main(String[] args) {
		GenDemo<String> demo = new GenDemo<String>();
		demo.show("hello"); //只能是String类型
		GenDemo<Integer> demo1 = new GenDemo<Integer>();
		demo1.show(5);
		
		GenDemo<Integer> demo2 = new GenDemo<Integer>();
		demo2.method("hello");  //传入参数和类的类型参数无关
		demo2.method(5);
		demo2.method(true);
		
		GenDemo<Number> demo3 = new GenDemo<Number>();
		demo3.show(new Integer(5));
		demo3.show(new Float(5.55f));
		demo3.show(new Double(5.55));
		// demo3.show(new Object()); //错误，new Object()不是Number子类所以不能传给show()方法的参数
		//注意这里泛型类型参数是Number，但是Number及其子类都可以传进来
	}
}
class GenDemo<T>{
	//泛型类中的方法，方法参数类型要和类的类型参数一致
	public void show(T s){
		System.out.println(s);
	}
	
	//泛型方法，泛型方法与泛型类没有必然的联系，泛型方法有自己的类型参数T，和泛型类中的类型参数T没有关系
	public <T> void method(T s){
		System.out.println(s);
	}
}