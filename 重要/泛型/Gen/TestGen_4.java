package Gen;
/*
泛型类中的方法，在访问控制符后面，函数返回值前面没有类型参数，方法类型参数
和类的类型参数要保持一致，集合List<E>的boolean add(E e);就是这种情况

泛型方法中，既有普通参数，也有类型参数，类型参数需要放在修饰符后面、返回值类型前面
<>中可以有一个或者多个类型参数，多个类型参数,号隔开，同时也可以对类型参数的取值范围进行限定
泛型方法与泛型类没有必然的联系不论类是否有类型参数，泛型方法有自己的类型参数，和泛型类中的类型参数没有关系

class A <T>{ //泛型类
	public void func(T t){  //泛型类中的方法，并不是泛型方法
	//方法修饰符后面，返回值前面若没有类型参数 <T>，这时方法和类中的类型应该一致
	A<Integer> aa = new A<Integer>();
	aa.func(5);  //调用func()方法传进去的参数只能是Integer及其子类类型
}
联系集合中List<E> 	该接口下面的方法 boolean add(E e);并不是 <E> boolean add(E e);
这个形式，也就是返回值前面没有参数类型，那么添加类型要和类中指定类型一致

public <T> void func1(T t){   //泛型方法
	//上面的是泛型方法，和泛型类没有关系，不论类是否有类型参数
	//泛型方法与泛型类没有必然的联系，泛型方法有自己的类型参数T，和泛型类中的类型参数T没有关系
	//泛型方法中参数可以是任意类型，T只是一种符号
	//该方法等价于public <S> void func1(S t) {....} S T只是符号而已
}
class B{
	//普通类的泛型方法，要在方法的修饰符后面，返回值前面添加类型参数，func()方法可以传递任意参数
	public <T, S> void func(T t, S s){ //泛型方法
	}
}
public <T, S extends T> T testGenericMethodDefine(T t, S s){}
public <T> void fromArrayToCollection(T[] arr, Collection<T> collec){}
方法有两个参数,T类型的数组arr以及Collection接口的实现类类对象，但是该实现类类对象中中存放的是T类对象
该方法使用了泛型，只需要改变里面的T就可以实现该方法对任何数据类型的操作，提高了代码的利用率
泛型方法的使用不像泛型类一样需要传入类型参数，只需要传进去普通参数，编译器会根据传进的参数自动匹配相应的类型
 * */
public class TestGen_4 {
	public static void main(String[] args) {
		Integer[] arr = {2,3,56,32};
		Float[] farr = {34.2f,565.5f,565.00005f,4.0f};
		Number[] narr = {45,67};
		MaxNum demo = new MaxNum();
		//这里new出一个对象就能调用不同参数的方法
		//但是在类后面添上类型参数，要调用不同的方法，就要new出不同的类
		System.out.println(demo.getMax(arr));
		System.out.println(demo.getMax(farr));
		System.out.println(demo.getMax(narr));

		A aa = new A();  	//并没有为类A添加泛型，也就是没有类型参数传递给A
		aa.show("hello"); //类A下面的show()方法却可以传进任意类型，使用的是泛型方法
		aa.show(true);
		aa.show(new Object());

		B bb = new B();	//泛型类没有添加类型参数,有黄色警告线
		bb.show("hello");
		bb.show(11);
		bb.show(true);
		//类BB添加了泛型，如果使用了泛型类就要调用不同方法就必须new不同对象，并且消除了黄色警告线
		//类对象中的传递的类型参数和方法中的参数类型一致
		B<String> bb1 = new B<String>();
		bb1.show("hello");
		B<Boolean> bb3 = new B<Boolean>();
		bb3.show(true);
		C<Integer> cc = new C<Integer>();
		//这说明了类的类型参数和方法的类型参数没有关系，类中类型数是Integer但是方法中参数可以是任意类型
		cc.show("hello");  
		cc.show(true);
		cc.show(new Object());
	}
}
class MaxNum{
	public <T extends Number> T getMax(T[] arr){
		//泛型方法
		//<T extends Number>这里是类型参数的限定，只能是Number及其子类
		//方法的返回值是 T类型
		T max = arr[0];
		for(int i = 0;i < arr.length;i ++){
			max = max.doubleValue() > arr[i].doubleValue() ? max : arr[i];
		}
		return max;
	}
}
class A{  
	//类没有添加泛型参数方法要在修饰符后返回值前添加类型参数
	//普通类中也可以添加泛型方法
	public <T> void show(T t){
		System.out.println(t);
	}
}
class B<T>{
	public void show(T t){  
		//泛型类中的方法，方法中的参数要和类的类型参数保持一致
		System.out.println(t);
	}
}
class C<T>{ 
	//泛型方法与泛型类没有必然的联系，泛型方法有自己的类型参数，和泛型类中的类型参数没有关系
	public <T> void show(T t){
		System.out.println(t);
	}
}
