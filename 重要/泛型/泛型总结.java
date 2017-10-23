Java泛型
假如我们现在要定义一个类来表示坐标，要求坐标的数据类型可以是整数、小数和字符串，如何实现？
1 将了性设置为Object类型，借助自动装箱和向上转型，基本数据类型可以自动装箱，被转换成对应的包装类
2 函数重载实现，将函数的参数设置为不同的数据类型
3 Java泛型来实现
引入范型后，一个类就可以在细分成更多的类型，例如原先的类型List，现在在细分成List<Object>, List<String>等更多的类型
注意，即使String继承了Object，但是List<Object>, List<String>是两种不同的类型，他们之间没有继承关系
泛型就是使下面的情况是非法的情况
    List<String> ls = new ArrayList<String>();
    List<Object> lo = ls;  
	这样设计的原因在于，根据lo的声明，编译器允许你向lo中添加任意对象（例如Integer），但是此对象是
	List<String>类型，破坏了数据类型的完整性
	或者
	List<Integer> ls = new ArrayList<Integer>();
	ls.add("hello"); 这里只能添加String类型
在引入范型之前，要在类中的方法支持多个数据类型，就需要对方法进行重载，在引入范型后，可以解决此问题
（多态），更进一步可以定义多个参数以及返回值之间的关系
	例如
	public void write(Integer i, Integer[] ia);
	public void write(Double  d, Double[] da);
	的范型版本为
	public <T> void write(T t, T[] ta);

类型参数具有继承关系，但是类型参数构造的泛型类不具有继承关系，他们是不同的类型
Integer是Number的子类，但MyClass<Integer>并不是MyClass<Number>的子类,两者都是泛型类，
前者代表MyClass<T>这个泛型类指定的类型参数是Integer，后者代表MyClass<T>这个泛型类指定的类型参数是Number
无论类A与类B是否存在关联，MyClass<A>与MyClass<B>都没有任何关联，其共同的父类的是Object

泛型指更加宽泛的类型，可也是任何类型
	A:泛型类
		紧跟类命之后的<>内,指定一个或多个类型参数的名字，同时也可以对类型参数的取
		值范围进行限定，多个类型参数之间用,号分隔，类型也可以作为参数，在创建类对象的时候要给出具体的类型
		如果在使用泛型时没有指明数据类型，那么就会擦除泛型类型，也就是和普通类是一样
		定义完类型参数后，可以在定义位置之后的类的几乎任意地方(静态块，静态属性，静态方法除外)使用类型参数，就像使用普通的类型一样
		泛型是 Java 1.5 的新增特性，它以C++模板为参照，本质是参数化类型(Parameterized Type)的应用
		类型参数只能用来表示引用类型，不能用来表示基本类型，如int等，但是传递基本类型不会报错，因为它们会自动装箱成对应的包装类
		class Gen <T1, T2 extends T>{
		// T1、T2是参数，是类型参数
		}
		
		Gen<type1, type2> gen = new Gen<type1, type2>(); //在创建类对象的时候要给出具体的类型
		Gen<type1, type2> gen = new Gen<>();
		用泛型类时指明了数据类型，当赋给其他类型的值会抛出异常，也就是说避免了转型

	B:泛型方法
		泛型方法中，既有普通参数，也有类型参数，类型参数需要放在修饰符后面、返回值类型前面，
		<>中可以有一个或者多个类型参数，多个类型参数,号隔开，同时也可以对类型参数的取值范围进行限定
		方法控制符后面，返回值前面没有加类型参数的情况(这种不是泛型方法，只是泛型类中的方法)
			方法类型参数和类的类型参数要保持一致，集合List<E>的boolean add(E e);就是这种情况
		方法控制符后面，返回值前面有自己的类型参数的情况(泛型方法)
			泛型方法与泛型类没有必然的联系，泛型方法，和泛型类没有关系，不论类是否有类型参数
			泛型方法有自己的类型参数，和泛型类中的类型参数没有关系
			在普通类中可以有泛型方法，一旦定义了类型参数，就可以在参数列表、方法体和返回值类型中使用该类型参数
		泛型方法在一定程度上可以替换函数的重载？？？？
		class A <T>{ //泛型类
			非泛型方法的情况
			public void func(T t){  
			//上面的方法并不是泛型方法
			//方法修饰符后面，返回值前面若没有类型参数 <T>，这时方法和类中的类型应该一致
			A<Integer> aa = new A<Integer>();
			aa.func(5);  //调用func()方法传进去的参数只能是Integer及其子类类型
				......
			}
			联系集合中List<E>
			该接口下面的方法 boolean add(E e);并不是 <E> boolean add(E e);这个形式，也就是返回值前面没有参数类型
			那么添加类型要和类中指定类型一致

			泛型方法的情况
			public <T> void func1(T t){
			//上面的是泛型方法，和泛型类没有关系，不论类是否有类型参数
			//泛型类的类型参数是T，并且在方法返回值前，访问控制符后面还要添加类型参数T
			//泛型方法与泛型类没有必然的联系，泛型方法有自己的类型参数T，和泛型类中的类型参数T没有关系
			//泛型方法中参数可以是任意类型，T只是一种符号
			//该方法等价于public <S> void func1(S t) {....} S T只是符号而已
			.....
			}
		}
		class B{
			//普通类的泛型方法，要在方法的修饰符后面，返回值前面添加类型参数，func()方法可以传递任意参数
			public <T, T1> void func(T t, T1 t1){ //泛型方法
			.....
			} 
		}
		public <T, S extends T> T testGenericMethodDefine(T t, S s){}
		public <T> void fromArrayToCollection(T[] arr, Collection<T> collec){}
			方法有两个参数T类型的数组arr以及Collection接口的实现类类对象，但是该实现类类对象中中存放的是T类对象
			该方法使用了泛型，只需要改变里面的T就可以实现该方法对任何数据类型的操作，提高了代码的利用率
		泛型方法的使用不像泛型类一样需要传入类型参数，只需要传进去普通参数，编译器会根据传进的参数自动匹配相应的类型
	C:泛型接口
		public interface Inter<T>{  //定义了泛型接口
			public void show(T t); 
		}
		类实现泛型接口的情况
			实现接口的时候已经知道数据类型
			class InterImpl_2 implements Inter<String>{  //实现泛型接口的时候直接添加类型参数

				public void show(String t) {
					System.out.println(t);
				}
			}
			实现接口的时候不知道数据类型
			class InterImpl<T> implements Inter<T>{
				public void show(T t) {
					System.out.println(t);
				}
限定泛型类的类型参数，也就是给类型参数添加一个界限
只要是定义过的数据类型(包括用户自定义的类)都可以作为泛型的类型参数
定义形式为<T extends Number> Number处的位置可以是类，也可以是接口，extends不表示继承，表示Number及其子类

类的静态变量和方法也在所有的实例间共享,随着类的加载而加载，优先于类对象存在
这就是为什么在静态方法或静态初始化代码中或者在静态变量的声明和初始化时使用类型参数(类型参数是属于具体实例的)是不合法的原因
这个时候并不知道泛型到底是什么类型
一个泛型类的所有实例在运行时具有相同的运行时类(class),而不论他们的实际类型参数
泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型
		DemoB<Number> dd = new DemoB<Number>();
		DemoB<Float> dd1 = new DemoB<Float>();
		System.out.println(dd.getClass());  //class Gen.DemoB
		System.out.println(dd1.getClass()); //class Gen.DemoB
		System.out.println(dd.getClass() == dd1.getClass()); //true
		泛型类型在逻辑上看可以看成是多个不同的类型，实际上都是相同的基本类型
通配符的作用
	List<? extends Cat> 是 List<? extends Animal>的子类型
	G<X> 是 G<? extends X>的子类型（如List<Animal> 是 List<? extends Animal>的子类型
	G<?> 与 G<? extends Object>等同，如List<?> 与List<? extends Objext>等同
	通配符应用的地方	
		A：类型参数具有继承关系，但是类型参数构造的泛型类不具有继承关系，他们是不同的类型
		Integer是Number的子类，但MyClass<Integer>并不是MyClass<Number>的子类,两者都是泛型类
		这些关系，显然是与Java中的多态理念显然是违背的,我们需要一个在逻辑上可以用来表示同时是
		MyClass<Integer>并不是MyClass<Number>的父类的一个引用类型,类型通配符应运而生
		在一些方法中，希望传进去的参数是MyClass<Integer> 或者 MyClass<Number>对象，这个时候就用通配符MyClass<? extends Number>
		B：泛型类创建对象的时候，要给出具体的类型参数，当类型参数不确定的时候，可以使用统配符
		java泛型如果明确指定，则泛型前后必须一致，即使是有继承关系的两个类型也不行
		集合中有类似的现象
		List<? extends Number> ls = new ArrayList<Number>();
		List<? extends Number> ls1 = new ArrayList<Float>();
		List<? extends Number> ls2 = new ArrayList<Integer>();
		ls.add(null); //除了null不能添加对象

		Collection<Object> c = new ArrayList<Object>();  	//可以
		Collection<Integer> c1 = new ArrayList<Integer>(); 	//可以
		//Collection<Object> c2 = new ArrayList<Integer>();	//不可以，即使Integer是Object的子类
	但是使用Java通配符？就可以实现任意类型的添加
		Collection<?> c3 = new ArrayList<Object>();  	//可以
		Collection<?> c4 = new ArrayList<Integer>(); 	//可以
		Collection<?> c5 = new ArrayList<Dog>(); 	//可以
	? extends E 向下限制，E和E的子类
		//Collection<? extends Animals> c6 = new ArrayList<Object>(); //不可以,Object不是Animals的子类
		Collection<? extends Animals> c7 = new ArrayList<Dog>(); 	//可以
		Collection<? extends Animals> c8 = new ArrayList<Sheep>(); 	//可以
	? super E 代表 E和E的父类
		Collection<? super Animals> c9 = new ArrayList<Object>(); 	//可以
		//Collection<? super Animals> c10 = new ArrayList<Sheep>(); //不可以,Sheep不是Animals的父类