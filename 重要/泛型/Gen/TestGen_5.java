/*
类的静态变量和方法也在所有的实例间共享,随着类的加载而加载，优先于类对象存在
这就是为什么在静态方法或静态初始化代码中或者在静态变量的声明和初始化时使用类型参数
(类型参数是属于具体实例的)是不合法的原因,这个时候并不知道泛型到底是什么类型
一个泛型类的所有实例在运行时具有相同的运行时类(class),而不论他们的实际类型参数
泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型
		DemoB<Number> dd = new DemoB<Number>();
		DemoB<Float> dd1 = new DemoB<Float>();
		System.out.println(dd.getClass());  //class Gen.DemoB
		System.out.println(dd1.getClass()); //class Gen.DemoB
		System.out.println(dd.getClass() == dd1.getClass()); //true
泛型类型在逻辑上看可以看成是多个不同的类型，实际上都是相同的基本类型

通配符的作用
类型参数具有继承关系，但是类型参数构造的泛型类不具有继承关系，他们是不同的类型
正如，Integer 是 Number 的子类，但MyClass<Integer>并不是MyClass<Number>的子类,两者都是泛型类
但是在一些方法中，这些关系，显然是与Java中的多态理念显然是违背的
MyClass<Integer>并不是MyClass<Number>的父类的一个引用类型，由此，类型通配符应运而生
List<? extends Cat> 是 List<? extends Animal>的子类型
G<X> 是 G<? extends X>的子类型（如List<Animal> 是 List<? extends Animal>的子类型
G<?> 与 G<? extends Object>等同，如List<?> 与List<? extends Object>等同
*/
package Gen;
public class TestGen_5 {
	public static void getDatas(DemoB<Number> parameter){
		System.out.println(parameter.getData());
	}
	//带有通配符的
	public static void getDatasWithSymbol(DemoB<? extends Number> parameter){
		System.out.println(parameter.getData());
	}
	public static void main(String[] args) {
		DemoB<Number> dd = new DemoB<Number>();
		dd.setData(55);
		DemoB<Float> dd1 = new DemoB<Float>();
		dd1.setData(33.33f);
		System.out.println(dd.getClass());  //class Gen.DemoB
		System.out.println(dd1.getClass()); //class Gen.DemoB
		System.out.println(dd.getClass() == dd1.getClass()); //true
		//泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型

		getDatas(dd);
		//getDatas(dd1); //dd1是DemoB<Float>类型，但是并不是DemoB<Number>的子类
		//Java中的多态理念显然是违背的,我们需要一个在逻辑上可以用来表示同时是DemoB<Number>和DemoB<Float>
		//的父类的一个引用类型，由此，类型通配符应运而生
		getDatasWithSymbol(dd);
		getDatasWithSymbol(dd1);
		//这次就不会报错，因为DemoB<? extends Number>逻辑上是DemoB<Number>以及DemoB<Float>的父类
		//这显然符合多态
	}
}
class DemoB <T>{
	private T data;

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}