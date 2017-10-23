字符串直接赋值的方式是在先到字符串常量池里面去找有，有返回，没有，先创建，再返回(方法区的中的字符串常量池)
	public static void main(String[] args) {
		/* 直接赋值的方式先在方法区的字符串常量池里面找是否有字符串"hello"，有就直接返回，
		 * 没有先创建，再返回，开辟空间创建" world"，再创建空间将"hello"和" world"拼接
		 * 最后栈中的str指向的创建的空间，指向"hello"和" world"拼接
		 * 因此字符串一旦被创建就不能被改变，是指创建的"hello",""
		 */
		String str = "hello";  
		str = str + " world";  
		System.out.println(str); // hello world
		String string = new String("hello");
		String string0 = "hello";
		/* 两者内存中的变化，String string在栈中，new String()在堆中开辟空间，首先在方法区的
		 * 字符串常量池中寻找是否有"hello"，没有就在常量池创建该字符串常量"hello"，并将其地址(假设0x001)赋予
		 * 堆中开辟的空间，但堆中开辟的空间本身也有地址(假设0x0001),将其赋予栈中string
		 * String string0同样在栈中，直接赋值同样在字符串常量池中找是否有字符串常量，有，就使 string0
		 * 指向该字符串常量，也就是将地址0x001赋予string0，因此，string与string0内容分别是0x0001与0x001
		 * 不一样，但是两者指向的内容都是"hello"，前者创建两个对象，后者创建一个对象   */
		//引用类型比较的是==两端是否指向堆内存中同一块存储空间，因为两者指向不同的空间，因此返回false
		System.out.println(string == string0); 
		//String类型重写了里面的equals()方法，比较的是内容，不论引用指向是否相同，因此返回true
		System.out.println(string.equals(string0));
		String string1 = "hello";
		String string2 = "hello";
		 //都是在方法区的静态常量池中直接拿过来的因此string1与string2相同，指向的内容也相同
		System.out.println(string1 == string2);
	}

字符串一旦被复制就不能被改变(指的是字符串的内容("hello")不改变，但是引用指向(str)可以改变)

	public static void main(String[] args) {
		String str1 = "hello";
		String str2 = " world";
		String str3 = "hello world";
		//字符串变量相加，先开辟空间，再进行拼接
		System.out.println(str1 == str2 + str3);
		//字符串常量相加，先拼接成hello world，在字符串常量池中寻找是否有，发现和str3所指向的一样，就返回str3
		System.out.println(str3 == "hello" + " world");
		System.out.println(str3.equals(str1 + str2));
		System.out.println(str3.equals("hello" + " world"));
		System.out.println(str1 == "hello"); //引用可以和字符串常量进行逻辑判断
	}
1." " 引号创建的字符串在字符串常量池中，字符串直接复制的方式会在字符串常量池中寻找，有，直接返回该字符串的引用，没有，先创建
字符串，再返回该字符串的引用
2.new，new创建字符串时首先查看池中是否有相同值的字符串，如果有，则拷贝一份到堆中，然后返回堆中的地址
如果池中没有，则在堆中创建一份，然后返回堆中的地址（注意，此时不需要从堆中复制到池中，否则导致浪费池的空间）
3.另外，对字符串进行赋值时，如果操作含有一个或一个以上的字符串引用时，则在堆中再建立一个字符串对象，返回引用；如String str2=str1+ "abc"; 
只有使用引号包含文本的方式创建的String对象之间使用“+”连接产生的新对象才会被加入字符串池中
对于所有包含new方式新建对象（包括null）的“+”连接表达式，它所产生的新对象都不会被加入字符串池中

/* String str1 = "hello";字符串直接赋值的方式
 * 对于字符串直接赋值的方式，会在字符常量池中寻找是否有"hello"这个字符串,有，就将该字符串的引用赋予str1，
 * 没有，先创建该字符串，再将字符串的引用赋予str1，String str2 = "hello";也会在字符串常量池中寻找
 * 发现有已经有创建的字符串"hello"，就直接将该字符串的引用赋予str2
 * String str1 = "hello";
 * String str2 = "hello";都是字符串直接赋值的方式，str1和str2都指向字符串常量池中同同一个字符串"hello"
 * 因此引用和内容都是相同的
 * String str3 = new String("hello");
 * String str4 = new String("hello");
 * 对于以上两句new String("hello")会在字符串常量池中找是否有字符串"hello"
 * 如果有，则拷贝一份到堆(new出的东西在堆中存储)中，然后返回new出对象的地址；
 * 如果字符串常量池中没有，则在堆中创建该字符串，然后返回new出对象的地址，由于以上new出了两个字符串对象，所以
 * str3和str4是不同的，但是内容相同
 * /

Java try--catch 异常捕获处理一点需要注意的地方
在try语句块里面即使遇到异常，在try--catch语句块外面还可以继续执行，但是当try语句块里面遇到异常之后，
异常语句之后的便不会再继续得到执行，try语句块抛出了异常，catch语句块才会得到了执行，若没有抛出异常，catch语句块不会得到执行

在try catch finally语句块中，try中有return语句，且前面没有异常阻止return语句的执行，就会返回try中对应的值，后面对该值得修改
不会影响try中return语句的返回
try中有异常会(阻止了return语句的执行)会执行catch语句块，若有catch中有return语句，且此处没有抛出异常阻止该return语句的执行，
就会返回catch语句中的值，若try、catch中都有异常，且finally没有return语句，就只会抛出异常，若finally中有return语句，try或catch中
的返回语句忽略，finally语句 (若没有异常因为要返回到try或者catch)无法返回，就会抛出异常

尽量在try或者catch中使用return语句。通过finally块中达到对try或者catch返回值修改是不可行的。
finally块中避免使用return语句，因为finally块中如果使用return语句，会显示的消化掉try、catch块中的异常信息，屏蔽了错误的发生
finally块中避免再次抛出异常，否则整个包含try语句块的方法回抛出异常，并且会消化掉try、catch块中的异常
try catch 有异常，finally没有异常，且有返回，这时候，finally会将异常消化掉，异常被屏蔽
try catch 有异常，finally有异常，就会抛出异常
try catch 没有异常，finally有异常，就会造成无法返回try catch ，抛出异常

为什么有这么奇葩的返回形式？
在try语句的return块中返回值是t,在try之外对t的只进行了修改，return 返回的引用变量(t是引用类型)并不是try语句外修改后的引用变量t，
而是系统重新定义了一个局部引用t’，这个引用指向了引用t对应的值，也就是try中的返回值t，即使在finally语句中把引用t指向了值finally，
因为return的返回引用已经不是t (而是系统重新定义了一个局部引用t’)所以引用t的对应的值和try语句中的返回值无关了

集合相关
boolean remove(Object o)  移除一个元素，将集合中所有是o的元素全部移除
boolean removeAll(Collection<?> c) 将集合中的所有属于集合c的元素全部移除，而不论集合中的这些属于c的元素有多少个
boolean retainAll(Collection<?> c) 两个集合的操作 c1.retainAll(c2);  结果保存在集合之中，而集合c2的不变  返回的是集合c1是否发生变化
c1发生变化返回true，否则返回false

集合元素遍历的两种方式 lterator方式，将集合转化为数组
lterator方式遍历元素的过程，通过集合调用iterator()方法获得Iterator对象，通过hasNext()方法判断是否存在元素，
通过next()方法返回元素并将游标指向下一个元素

public interface Iterator<E>  Iterator是一个接口
java中设计了三种接口来实现三种集合，Set、List、Map，集合、列表、映射
容器的框架图,位于java.util包中
							   Collection <interface>					Map <interface>
									|										|
				Set <interface>   	   	  List <interface>			   HashMap
					  |			                  |
				   HashSet	            LinkedList  ArrayList    //都是上面接口的实现类类对象
接口中定义属性必须是 public static final 类型的，定义方法必须是 public abstract 类型的
这些符号可以部分或者全部省略,便决定了接口是一个抽象类
interface it3 extends it1,it2
class B extends A implemrents it1  都是允许的

集合Collection继承了Iterable接口

public interface Iterator<E>{   //Iterator是一个接口，里面有两个抽象的方法
	public abstract boolean hasNext();
	public abstarct <E> next();
}

public interface Iterable<T>{
	public abstarct Iterator<T> iterator();  //接口下面有一个抽象方法iterator()，没有实现的抽象方法
}
Iterable是一个接口，他仅仅是一个接口而已，里面有一个抽象方法iterator()，该方法的返回类型是Iterator<T>，返回的是这个接口类型
我们创建一个接口，接口里面是定义的抽象方法(标准，规则，是用来让其他类来实现的)，抽象方法有返回值，返回值是某个类，那么该类中也是有其他成员的
例如接口中抽象方法返回值是String类型，那么String本身也是一个类，String类下面有许多的成员，包括各种方法等
更新 Java中一切都是对象
public interface Collection<E>extends Iterable<E>{
	public abstarct Iterator<T> iterator();  //接口下面有一个抽象方法iterator()，没有实现的抽象方法
}
public interface List<E>extends Collection<E>{
	public abstarct Iterator<T> iterator();  //接口下面有一个抽象方法iterator()，没有实现的抽象方法
}
public class ArrayList implemrents List{
	.....
	public Iterator<T> iterator(){  //这里已经是接口的实现类了，因此要具体的实现iterator()方法，不同的实现类中实现的方法不同
		return new Itr();   //返回值是一个类对象
	}
	private class Itr implemrents Iterator{  // ArrayList的内部类
		public boolean hasNext();
		public <E> next();
	}
} 
注意这里 ArrayList 已经是以上接口的实现类，里面实现了iterator()方法，返回值是一个 new Itr(),一个 Itr 类对象
Itr 是ArrayList这个实现类的内部类，在内部类中具体实现了 hasNext()以及next()方法
容器类都继承了Iterable接口，继承了里面的iterator()方法，但是对于容器具体的实现类，都要重写继承自父类或者上层接口中的iterator()方法
不同的容器类对于iterator()方法的实现都是返回一个Itr的类对象，该类是具体容器类的内部类，该类实现了Iterator接口，
在该内部类中重写Iterator接口中的三个抽象方法，因此对于不同的容器具体的实现类，Itr的中三个抽象方法的实现是不同的，这就是为什么，
不同的容器类迭代都可以进行，是因为容器具体的实现类中对于Itr这个内部类中的三个抽象方法的实现是不一样的
		Collection c = new ArrayList();
		Iterator it = c.iterator();  // c 就代表容器的实现类类对象ArrayList，调用iterator()方法，返回是new Itr() [new Itr()是内部类对象]
										// 因为Itr类实现了Iterator接口，因此该类对象就属于Iterator接口这种类型的对象
		while(it.hasNext()){         // 此时it就相当于Itr的一个类对象，it.hasNext() it.next() it调用这两个方法就是调用Itr类中已经具体实现的两个方法
			System.out.println(it.next());
		}

当迭代器迭代迭代元素的时候，集合不能进行修改，否则会抛出并发修改异常 ConcurrentModificationException
因为迭代器依赖于集合而存在，当集合发生修改时迭代器并不知道，因此会抛出异常，要进行修改，迭代器迭代，迭代器修改
或者集合遍历，集合修改,ListIterator遍历ArrayList，提供了迭代器迭代元素的时候进行元素的修改，只是两者在集合的
某个位置添加元素后，元素出现的位置不同，用集合的方式元素出现在最后，迭代器的方式添加元素，元素出现在某位置

数据结构
栈：先进后出,“子弹夹”，push压栈，pop取出最上面的一个元素，并返回该元素，判断栈是否为空
队列：先进先出，“检票”
数组：查找比较方便，插入删除不方便
	arr = [2,4,3,6,898,54,32,66,33];
	如何获取 4 这个元素？  --->  arr[1]
	如何在 4 这个元素之后插入一个新的元素 88？
		定义一个新的数组，大小为原来数组大小 + 1,元素4之前的元素，按照之前的索引直接复制过来
		元素4继续复制，元素4之后，先在新的数组中插入元素 88 ,然后将之前 4 之后的元素的索引 +１后复制到新的数组中
	如何删除某个元素　如元素　4 ? 
		定义一个新的数组，大小为原来数组大小 - 1,元素4之前的元素，按照之前的索引直接复制过来
		元素4不添加，然后将之前 4 之后的元素的索引 -１后复制到新的数组中
链表：每个节点包含数据域和指针域，查询慢，插入删除比较快
	查询一个元素？ --> 从头开始遍历
	在某个元素之后插入一个元素，将该元素的？？？？？

ArrayList : 底层实现是数组，查询快，增删慢，是线程不安全的，效率高
LinkedList : 底层实现是链表，查询满，增删快，是线程不安全的，效率高
Vector : 底层实现是数组，查询快，增删慢，是线程安全的，效率低
Java中数组和集合的区别，数组能存储基本数据类型和引用类型，且只能是同一种类型，而集合只能存储引用类型，能存储多种类型
当集合中存储的是基本数据类型的时候，会通过自动装箱直接转化为基本数据类型对应的构造器类型
对于 Collection类中的方法，remove()方法的参数是元素，对于 ArrayList可以remove元素，还可以是index
oldlist.remove(oldlist.get(j));      oldlist.remove(j);
重写equals()方法
					public boolean equals(Object obj){ 
						if(this == obj)  //如果引用相等，那么堆空间中的内容一定相等
							return true;
						else{
							boolean con = obj instanceof Student; 
							if(con){  // 属于同一类，才有比较的必要
								//父类引用实际指向的是子类引用，可以将父类引用强制转化为子类引用而不出错
								//父类引用不能访问子类所特有的成员，因此要将父类引用转化为子类引用
								Student s = (Student)obj;
								if(this.age == s.age && this.name.equals(s.name)) //String类型已经重写了equals方法
									return true;
								else
									return false;
							}
							else
								return false;
						}
					}// 有返回值的方法要保证每个逻辑单元有返回值

泛型接口，将泛型定义在接口上面
		public interface Inter<T>{  //定义了泛型接口
			public void show(T t); 
		}
		类实现泛型接口的情况
				实现接口的时候已经知道数据类型
				class InterImpl_2 implements Inter<String>{  //实现泛型接口的时候直接添加泛型标志

					public void show(String t) {
						System.out.println(t);
					}
				}
				实现接口的时候不知道数据类型
				class InterImpl<T> implements Inter<T>{
					public void show(T t) {
						System.out.println(t);
					}
// java 泛型如果明确指定，则泛型前后必须一致，即使是有继承关系的两个类型也不行
		Collection<Object> c = new ArrayList<Object>();  	//可以
		Collection<Integer> c1 = new ArrayList<Integer>(); 	//可以
		//Collection<Object> c2 = new ArrayList<Integer>();	//不可以
		
		// 但是使用Java通配符？就可以实现任意类型的添加
		Collection<?> c3 = new ArrayList<Object>();  	//可以
		Collection<?> c4 = new ArrayList<Integer>(); 	//可以
		Collection<?> c5 = new ArrayList<Dog>(); 	//可以
		// ? extends E 向下限制，E和E的子类
		//Collection<? extends Animals> c6 = new ArrayList<Object>(); //不可以,Object不是Animals的子类
		Collection<? extends Animals> c7 = new ArrayList<Dog>(); 	//可以
		Collection<? extends Animals> c8 = new ArrayList<Sheep>(); 	//可以
		// ? super E 代表 E和E	的父类
		Collection<? super Animals> c9 = new ArrayList<Object>(); 	//可以
		//Collection<? super Animals> c10 = new ArrayList<Sheep>(); //不可以,Sheep不是Animals的父类

增强for循环遍历的集合不能为空，增强for是用来替代迭代器的
Java可变参数 public int sum(int... a) {  }

a 其实是一个数组，如果有多个参数，可变参数一定要放在最后一个参数位置上面
List < ArrayList<Student> > stuList = new ArrayList < ArrayList<Student> > ();

在进行设计的时候，如果传递的参数少于等于3，就直接传递具体的参数，如果传递的参数过多，就直接传递类对象
对于一个项目的分析：
	1、需要哪些类来实现？
	2、每个类中有哪些的东西？
	3、类与类之间的关系是什么？
	对于一个用户注册访问的demo可以进行如下的划分
		1、用户基本描述类  
		2、用户操作类
	如何对项目进行分包？
		1、用户基本描述类包 cn.example.user.pojo
		2、用户操作接口,将接口打包， cn.example.dao
		3、用户操作类包  cn.example.dao.userImpl
	思考为什么将操作进行接口单独进行打包？ 
		接口增加了许多类都需要实现的功能，接口是对行为的抽象，是一种“标准”，用户的操作类是根据操作接口，用某种方式具体实现的
		将用户操作要实现的接口单独打包，如果以后更改用户操作实现的具体方式，就不用再次规定要实现的操作，也就是说，这种接口对需求的
		规定(在一个单独的包中)是不用发生变化的

为什么保证 HashSet里面的元素不相同就要同时重写hashCode()方法和equals()方法？
	保证元素的唯一性，当前对象和之前所有对象进行比较，调用hashCode()方法，先得到当前对象的哈希值
		相同：再比较地址值或者调用equals()方法看内容是否一样
			equals()方法返回 true 说明元素重复，不添加到set里面、
						返回 false 说明元素不重复，添加到set里面
		不同：直接添加到set里面
		注意当hashCode()方法返回值相同的时候才进行equals()调用，当哈希值不同的时候是不进行equals比较的
		这里可以理解成，不同的哈希值对应不同的存储空间，每块空间类似一个桶，当哈希值相同的时候，所有元素相当于放到同一个
		桶中，每添加一个元素，就会和桶中之前的所有元素进行比较，当元素较多的时候，这种哈希值相同，通过逐个和之前元素equals()方法
		来保证元素不重复的方法效率会比较低，
		解决办法是尽量保证对象的哈希值尽可能不同，即造出多个桶，不同的对象放到不同的桶中，即使哈希值有相同的，就会通过equals()来保证元素不重复
		也就是每个桶中存放较少数目的元素，放入桶中的元素会进行很少数目的比较，这样效率会比较高
		对象的哈希值和类的成员变量相关，因此利用成员变量可以来实现hashCode()方法，基本类型直接相加，引用类型就调用hashCode()方法再相加	


List Set 是对现实事物的抽象，功能不同，要根据实际的需求，选择适当的集合类  ———————————
对于自定义的类对象，添加到集合中的元素要保证不重复，必须同时重写hashCode()方法和equals()方法
在Java集合框架下，提供了 Set 这个集合，可以保证元素的不重复(实际应用的时候，需要元素不重复就选集合 Set)

LinkedHashSet直接继承自HashSet，可以保证存取的元素是有序的  Set<Stu> s = new LinkedHashSet<Stu>();
底层实现由哈希表和链表，哈希表保证元素的唯一，链表保证有序(存取一致)

List  集合里面的元素是有序的(存取一致)，可以有重复元素
	|-- ArrayList : 底层实现是数组，查询快，增删慢，是线程不安全的，效率高
		LinkedList : 底层实现是链表，查询满，增删快，是线程不安全的，效率高
		Vector : 底层实现是数组，查询快，增删慢，是线程安全的，效率低

		List是有序的,是指里面的元素存取一致，并不能保证按照某种方式有序的输出，如果要实现按照某种方式排序，就得实现Comparable接口
		这一点和Set集合中TreeSet是一样的，只是list要用Collections类的sort()方法进行排序
				方式一：这里面的Student类要实现Comparable接口
				List <Student> list = new ArrayList <Student> ();
				Collections.sort(list);
				方式二：匿名类来实现
				List <Student> list = new ArrayList <Student> ();
				Collections.sort(list, new Comparator <Student> (){
					@Override
					public  int compare(Student s1, Student s2){
						.......
					}
				});
Set   集合中的元素是无序的(存取不一致)，不包含重复元素
		这里关键的问题是如何保证集合里面的元素是唯一的 ？？
		add()方法实际上调用的是Map的put()方法
	|-- HashSet 通过哈希表(hashCode()方法)和equals()方法保证集合中的元素是唯一的
		|--- LinkedHashSet 直接继承自HashSet，可以保证存取的元素是有序的  Set<Stu> s = new LinkedHashSet<Stu>();
			底层实现由哈希表和链表，哈希表保证元素的唯一，链表保证有序(存取一致)
			因为Stirng类已经重写了hashCode()方法和equals()方法，因此添加到集合中的元素可以保证都不同，但是对于自定义的类对象，
			没有重写hashCode()方法和equals()方法，不能保证添加到集合中的元素是唯一的，保证元素唯一，类必须重写hashCode()方法和equals()方法
			但是HashSet对自定义类是不能进行排序的，即使要进行排序的类实现了Comparable接口重写了里面的compareTo()方法
			这一点值得注意
		TreeSet 实现元素有序且唯一的原理(保证元素唯一以及进行排序条件的设定非常重要)
				根据比较返回值是否为0来保证元素的唯一，当返回0时候元素不用管
				有序：自然排序 让元素所属的类实现Comparable接口，重写里面的compareTo()方法
					  构造器排序 让集合的构造方法接收一个比较器接口 Comparator 的实现类类对象
				通过二叉树(红黑树是一种自平衡二叉树)来实现元素的唯一，依赖于树的结构
				当元素存放完毕，集合中的元素已经是不重复的元素了，又由于树的特殊结构，当取出来的时候可以实现排序
				对于自定义的类，比较是依赖于compareTo()方法的，该方法在接口Comparable接口里面，因此要实现排序应该先实现Comparable接口
				Integer与String类实现了 Comparable接口，因此可将Integer与String类对象转化为Comparable引用来接收，这里是多态的实现，
				但是对于自定义的类，因为没有实现Comparable接口，因此不能将类对象用Comparable引用来接收
				TreeSet的add方法的实现依赖于TreeMap的put方法的，里面涉及将类对象转化为Comparable引用的过程，对于Integer和String类型
				因为已经实现了Comparable接口所以不会出现异常，但是对于自定义的类，必须要实现Comparable接口，否则会抛出异常 
				java.lang.ClassCastException: Students cannot be cast to java.lang.Comparable 
				这就解释了自定义的类添加到TreeSet里面为什么要实现Comparable接口？？要指定比较的标准
				对于自定义的类，比较是依赖于compareTo()方法的，要根据子集的需要来重写该方法

				对于String类和Integer类里面实现了 Comparable 接口可保证TreeSet中的元素唯一并且有序，但是对于自定义的类，是无法查看唯一的，
				运行会出现异常，因为在保证唯一的过程中涉及将类对象转化为 Comparable 引用，因此必须先实现该接口，之后再进行查看，也就说定义
				什么情况下元素算是唯一  “即使保证唯一  也要实现Comparable接口，或者构造器的方式”，之前认为TreeMap存储完毕里面的值已经
				实现了唯一，实现Comparable接口或者构造器只是进行排序的需要，但是保证唯一的过程中涉及将类对象转化为Comparable引用，因此保证唯一
				也要实现Comparable接口或者用构造器方式
				方式一：
						public int compareTo(Students o) {
							/* 如果这里指定按照年龄作为排序的条件，但还要考虑，当有年龄相同名字不同的时候，这也是不同元素的情况，
							 * 这时只会添加到集合中其中 一个元素，这时就要考虑姓名也作为排序条件*/
							int condition = this.age - o.age;
							return condition == 0 ? this.name.compareTo(o.name) : condition;
							/* 如果condition等于0表示年龄相同，这时候就返回this.name.compareTo(o.name)
							 * 否则，表示年龄不相同，就按年龄为第一条件进行排序，返回condition，name是String已经重写了
							 * compareTo()方法，因此可以直接调用  */
							注意这里当年龄相同的时候，就比较姓名是否相同，直接调用String的compareTo()方法，将内容作为比较条件，而非长短
						}
				方式二：匿名类来实现
						Set<SSS> s = new TreeSet<SSS>(new Comparator<SSS>(){  
						public int compare(SSS para1, SSS para2) {  
							int con = para1.getName().length() - para2.getName().length();
							return con == 0 ? para1.getAge() - para2.getAge() : con;
						}
						});     /* 这里是用构造器的方式实现添加到TreeSet集合中的元素的排序,用匿名类来实现
								 * 要重写里面的compare()方法，这里参数parameter1相当于Comparable接口中，compareTo()方法
								 * 中的this，parameter2参数相当于o,这里还要注意SSS类中属性是私有的不能直接用，用get以及set方法来获取 * */
集合如何选择？
元素唯一？
	是：Set
		有序？
			是：TreeSet
			否：HashSet
	否: List
		安全？
			是：Vector
			否：LinkedList或者ArrayList
某个类实现了某个接口，可以将它转为接口类型
public Integer implements Comparable<Integer>  可以将 Integer 类型转化为 Comparable类型
Thread thread = new Thread(Runnable target); Thread类的参数需要一个Runnable接口，可以将实现了Runnable接口的实现类对象传给Thread构造方法的形参
<论方法的参数>某个方法的形参或者某个类构造方法的形参需要某个类(数据类型)的类对象，或者需要某个抽象类的实现类的类对象，
或者某个接口的实现类类对象，那么生成这样一个类继承某个抽象类，或者实现某个接口，重写里面的抽象方法，生成该类的对象
将该对象传递给方法的形参即可，所有这些可以用匿名类来实现，如果一个方法的参数是一个接口，那么，传进去的就是该接口的实现类类对象，
两种实现方式：创建类实现接口重写方法，将该类的类对象传进去，直接匿名类的方式进行实现

Collection<V> values() 返回映射值得集合，注意这里返回的是Collection，用Collection来接收，而不是Set，否则抛出异常
Set<K> keySet() 返回映射所有键的集合，是Set<K>
V get(Object key) 返回键key对应的值，当键不存在的时候，返回null
V put(K key, V value) 将键值对添加到映射，注意该方法有返回值，如果第一次存储就返回null，该键第二次存在，会将该键对应的值更新，并返回之前的值
Set<Map.Entry<K,V>> entrySet() 该方法返回映射的集合 
映射值得遍历：	1、通过values()方法获得值得集合，然后遍历输出
				2、通过keySet()方法获得键的集合，通过键获得每个值
				3、通过entrySet()方法得到映射的集合，通过该集合获得值，或者获得映射的键
						Set<Map.Entry<String,String>> s = map.entrySet();
						for(Map.Entry<String,String> m : s){
							System.out.println(m.getValue());
						}
对于用户自定义的类，如果不重写hashCode()方法和equals()方法，则添加到映射中的元素是重复的，如果重写hashCode()方法和equals()方法
如果键相同之前的值就会被最近的值覆盖，没有重复的键存在，这个时候元素就是唯一的，因为String以及Integer类已经重写了这两个方法，
因此，能保证集合中的键是唯一的
LinkedHashMap可保证里面元素存取一致，有序
TreeMap的键是基于红黑树实现的
HashMap以及TreeMap可以完全按照HashSet以及TreeSet来分析

如何实现映射键是Teacher类对象，值是String类型，保证里面的元素唯一且有序？
用TreeMap来存放，由于树的结构，里面的元素已经是唯一的了，键对应的类要么实现Comparable接口(自然排序)，
要么用构造器的方式进行排序(用匿名类的方式进行实现)来保证元素是有序的，或者用HashMap来进行存放，保证里面的元素是唯一的，
那么键对应的元素应该重写hashCode()方法和equals()方法，用LinkedHashMap保证有序

List是有序的,是指里面的元素存取一致，并不能保证按照某种方式有序的输出，如果要实现按照某种方式排序，就得实现Comparable接口
这一点和Set集合中TreeSet是一样的(注意HashSet里面的类对象即使实现了 Comparable 接口，也不能实现元素按照规定进行排序)
只是list要用Collections类的sort()方法进行排序
方式一：这里面的Student类要实现Comparable接口
List <Student> list = new ArrayList <Student> ();
Collections.sort(list);
方式二：匿名类来实现
List <Student> list = new ArrayList <Student> ();
Collections.sort(list, new Comparator <Student> (){  //为借口增加泛型
	@Override
	public  int compare(Student s1, Student s2){
		.......
	}
});

但是 HashSet 里面存放自定义的类对象，类对象实现了Comparable接口，并且重写了ComparaTo()方法，不能做到按照规定的方式进行排序
HashSet主要来保证里面的元素唯一，TreeSet主要实现里面的元素有序
为啥是这样的结果呢？从帮助文档上看就知道
TreeSet(Collection<? extends E> c) 
TreeSet(Comparator<? super E> c) 构造方法需要某个接口的实现类类对象
两个TreeSet的构造方法的参数需要某个接口的实现类对象，然后重写里面的相关方法，就能实现元素的排序，<构造方法形参问题...>
但是HashSet里面的构造器并没有这样的形式
Collections类下面提供的相关方法
static <T extends Comparable<? super T>> void sort(List<T> list) 
          根据元素的自然顺序 对指定列表按升序进行排序 
static <T> void  sort(List<T> list, Comparator<? super T> c) 
        根据指定比较器产生的顺序对指定列表进行排序
根据Comparator再次理解匿名类实现以及常规实现的区别

HashMap和Hashtable的区别
HashMap线程不安全，但是效率高，允许null键和null值
Hashtable线程安全，但是效率低，不允许null键和null值
Collection 是单列集合的顶层接口，其子接口有List和Set
Collections 是对集合操作的工具类，里面都是静态方法


多线程：
进程是系统进行资源分配和调用的独立单位，每个进程有自己的独立空间和系统资源，多进程是提高CPU使用效率
单线程：程序有一条执行路径，多线程：程序有多条执行路径，
线程是程序的不同执行路径，每个进程有多个任务，每个任务可以看成一个线程，线程是使用CPU基本单位
程序的执行就是抢CPU的资源，抢CPU的执行权，如果某个进程有多条执行路径，就会有更高的几率抢占到CPU
线程的执行具有随机性

JVM虚拟机运行时单线程还是多线程？ 多线程的，至少有main线程，还有GC线程
为什么要重写run()方法，类中并不是所有的代码都需要被线程执行，为了进行区分，Java提供了Thread类中的该方法包含被线程执行的代码
设置线程优先级，线程优先级较高的执行几率比优先级底的执行几率要高，但是要在尝试次数多的情况下才能看到(线程的执行具有随机性)
线程中run()方法和start()方法的区别？
run()方法里封装的是线程执行的代码，调用start()方法，先启动线程，再由JVM调用run()方法
线程问题
	|--	线程的生命周期
			新建：创建线程对象
			就绪：该线程有执行资格，但是没有执行权
				阻塞：由于一些操作线程阻塞，没有执行资格，没有执行权(必须先获得执行资格之后才能获得执行权)
			执行：有执行资格，有执行权
			死亡：线程对象变成垃圾，准备被回收
			线程执行流程图

	|--	创建线程的第二种方式适合多个相同程序的代码去处理同一资源的情况，将线程同程序代码，数据有效的分离，较好的体现了面向对象的设计思想
		避免由于Java单继承带来的局限性
		线程安全问题的分析
			多线程安全问题出现的原因：
				有多线程环境
				延迟
				共享数据
				多条语句共同操作共享数据
				解决思想，将多条语句设置为同步代码
	|--	同步代码块
			假设有tha thb thc 三个线程，三个线程都能走到这里，在这里抢占CPU的执行权，假设tha抢到了执行权，就进到同步代码块里面
			执行到sleep()tha进行休眠，假设这是thb抢到了执行权，这是发现已经上锁，进不到同步代码块里面,tha睡眠时间到
			就会被唤醒，继续执行，知道走出同步代码块，接着三个线程继续抢占CPU的执行权
			同步代码块：synchronized(对象){
							.....  //同步代码块解决线程安全问题的关键在于创建的对象上面，这个对象相当于一把锁，注意多个线程必须是同一把锁
						}  这里可以设想多个人(多个线程)上厕所，只允许一人使用，也就是只允许一个线程操作同步代码块里面的资源
	|--	线程同步的特点
			1、前提是有多个线程  
			2、多个线使用的同一把锁(使用同一个锁对象)，不同情况下的锁对象是不一样的
			3、有多个线程，多个线程都会判断同步上的锁，这是很耗费资源的，因此保持同步，效率会比较低
			|--	同步代码块格式以及锁对象？
					是任意的类对象
				同步方法的格式以及锁对象？
					将关键字synchronized加到方法之前 public synchronized void f() {.....}，其锁对象是关键字 this
				静态对象的锁对象？
					类对象.class 
	StringBuffer sb = new StringBuffer();
	Hashtable<Integer,Integer> ht = new Hashtable<Integer,Integer>();
	Vector<String> v = new Vector<String>();
	上面是线程安全的，方法前面都加了关键字 synchronized
Collections类里面有线程安全的集合的创建方法---将线程不安全的集合转化为线程安全的集合
	List<String> list = Collections.synchronizedList(new ArrayList<String>());
	//这里创建的集合 list 是线程安全的
	Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
	//这里创建的集合 set 是线程安全的
	Collection<String> cc = Collections.synchronizedCollection(new TreeSet<String>());
	//这里创建的集合 cc 是线程安全的

	//更新至 2016年6月2日17:41:29