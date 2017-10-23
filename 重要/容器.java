6、容器
	1、Integer类在对象中包装了一个基本类型int的值，Integer类型的对象包含一个int类型的字段。 
	此外，该类提供了多个方法，能在int类型和String类型之间互相转换，还提供了处理int类型时非常有用的其他一些常量和方法
	2、所有容器里面放的是对象，不能存放一个孤立的值，如果放的是孤立的值
	会自动转化为对该孤立值对应的对象，写的是整型数据1234，但放入进去的是一个Integer对象
	写的是double类型数据88.88，但放入进去的是一个Double类对象，会自动封装成对象
	3、java中设计了三种接口来实现三种集合，Set、List、Map，集合、列表、映射
	容器的框架图,位于java.util包中
								   Collection <interface>					Map <interface>
										|										|
					Set <interface>   	   	  List <interface>			   HashMap
						  |			                  |
					   HashSet	            LinkedList  ArrayList    //都是上面接口的实现类类对象  
	4、Set和List都继承自Collection接口，所以Set和List中许多方法都是一样的
	List中元素可以重复，是有序的，Set中的元素不能重复，里面的元素是无序的
	因此List接口中set()、indexOf()方法，Set接口中没有set()、indexOf()方法，因为
	Set中的元素是无序的，不存在某个元素在某个位置这个概念
	5、toString()方法，执行System.out.println(aa);等价于System.out.println(aa.toString());
	由于Java的自动装箱，所有添加到容器中的都是类对象，输出的是该类对象调用过toString()方法后的结果
	自己定义的类对象，要添加到容器中，建议重写类的toString()方法,否则输出的是
	该对象的引用，类名@.....的形式，因此要要按我们想要输出的结果重写toString()方法
	6、ArrayList和linckedList的区别：两者都实现了List接口中的方法，但是内部实现不同
	ArrayList 存储速度快，插入删除比较慢
	LinckedList 存储速度比较慢，插入删除比较快
	7、Collections是Collection接口的实现类，里面有很多方法，ArrayList 和linckedList等本身没有
	排序、倒置、查找等实现方法，是通过Collections类来实现的，里面有很多public static方法
	可直接对Collection接口的实现类进行操作，可以查找API文档
	8、用户自定义的类对象，放到容器中，如果要进行排序比较，要指定比较的标准，这个类
	要实现Comparable接口，该接口里面有compareTo()方法，要重写里面的compareTo()方法
	class Stu implements Comparable,自定义类实现了Comparable接口，一定要重写里面的
	抽象方法compareTo(Object obj)方法，不进行重写会提示报错
	9、类对象放到容器中，会计算出元素的hashCode值，根据值找要到存放该元素的地址
	不同类对象的hashCode值是不一样的，
	因此要重写hashCode()方法，该地址后面是元素连接，比如存放了new A(1)对象，可能
	new A(1)对象已将存在，为类保证不存放重复的new A(1)，因此要重写equals()方法
	10、对象放到以哈希表为存储内核的容器hashSet、hashTable、hashMap，要保证容器中没有重复的元素，
	必须要同时重写hashCode()方法和equalsO()方法，放到其他类型容器中如，放到TreeSet、TreeMap
	容器中的元素，则不需要重写hashCode()和equalsO()方法
	11、每个类中都默认有hashCode()和compareTo()方法，因为所有类都继承自Object类，
	并不是所有的类都要重写这两个方法，当要用到时，且提供的方法不满足要求时就要重写这两个方法
	12、Object类的hashCode()方法会返回该对象内存真实地址的整数化表示，
	这种不是真正地址的整数值就是哈希码
	向hashSet中添加元素的时候，先通过该对型的hashCode()方法计算出相应的桶，然后根据equals()方法
	找到相应的元素，如果该元素已经存在，则不添加到容器中，反之，添加到容器中
	因此要重写hashCode()方法，保证一个类new出的对象hashCode()方法返回值是一样的，
	即哈希码指向同一块空间，通过重写equals()方法保证这个空间中不存在相同的元素，不重写
	hashCode()方法，new出一个对象会根据返回的哈希码指向一个的存储空间，不同对象指向不同的
	空间，即使equals()方法判断成功也不能保证元素不重复，同样只重写hashCode()方法，虽然同一个类
	new出的对象在同一个空间，但是由于没有equals()方法，也不能保证这一块空间没有重复元素

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
				
Set HashSet 元素唯一，提供最快的查找元素的方式，不能保证里面元素的有序
	TreeSet 元素唯一，按照比较结果的升序进行存保存元素
	LinkedHashSet 元素唯一，按照添加元素的顺序保存元素
	HashMap 提供最块的查找方式不保证元素有序
	TreeMap 按照比较结果的升序保存键
	LinkedHashMap 保存了HashMap的查找速度，同时按照插入顺序保存元素
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

迭代器
	迭代器可以不关心他所迭代的容器的具体类型，将遍历序列与序列的底层实现分离，因此迭代器统一了访问容器的方式