在创建一个类的时候要注意成员的访问控制符
类中成员的访问权限，定义为public权限，在其他包中其他类中都可以访问
protected权限，就是给子类用的，不同包中，子类继承父类，在子类中可以通过子类对象，访问继承自父类protected权限的成员
默认权限，包级访问权限，与该类处于同一个包中的其他类都可以访问
private权限，只能在本类中进行访问，其他类中不能访问，或者其他类可以通过本类中提供的方法间接访问本类中的private权限的成员

System.in System.out System.Err 是System类下面的静态字段，代表标准输入，标准输出，标准错误
可以调用System类下面的setXXX()方法进行标准输入输出以及标准错误的重定向
	System.setIn(in);   //参数是InputStream
	System.setOut(out); //参数是PrintStream
	System.setErr(out); //参数是PrintStream
当进行了标准输入，标准输出，标准错误重定向之后，System.out调用println()就不再显示在屏幕上
System.in也不代表是键盘的输入了

/* Java集合类的基本接口是Collection接口，而Collection接口必须实现Iterator接口
 * 任何实现了Iterable接口的类都可以用于增强for
 * 实现这个接口允许对象成为 "foreach" 语句的目标，集合类实现了该接口，因此可以用foreach进行迭代
 * 数组以及实现了Iterable接口的类都可以用增强for来进行元素的遍历，实现Iterable接口，
 * 类中就要重写由于实现接口，接口中的 iterator()方法，在用增强for循环的时候自动调用了类中的 iterator()方法
 * 该方法操作的是类中的某个东西，这个某个东西就是容器类存储元素的地方，所以可以将容器类中的元素输出
 * 
	ArrayList就是动态数组，也是一个对象
	创建一个ArrayList对象，该对象存放在堆内存中，且是一个内存连续的内存区域。
	1、ArrayList是用数组实现的，这个数组的内存是连续的，不存在相邻元素之间还隔着其他内存。
	2、索引ArrayList时，速度比原生数组慢是因为你要用get方法，这是一个函数调用，而数组直接用[]访问，
	相当于直接操作内存地址，速度当然比函数调用快
	3、新建ArrayList的时候，JVM为其分配一个默认或指定大小的连续内存区域（封装为数组）。
	4、每次增加元素会检查容量，不足则创建新的连续内存区域（大小等于初始大小+步长），也用数组形式封装，
	并将原来的内存区域数据复制到新的内存区域，然后再用ArrayList中引用原来封装的数组对象的引用变量引用到新的数组对象：
	elementData = Arrays.copyOf(elementData, newCapacity);

 * 1、类实现了Iterable接口用于增强for循环，操作的是该类的一个对象，但是却可以将类中的某个数组输出，
 * 可以设想，在执行增强for的时候自动调用了里面的 iterator()方法，该方法操作了类中的数组
 * 2、还可以将该类的类对象传递给print0(Iterable<T> it)方法，那么也会将类中的数组输出
 * 在方法体中用了增强for，在执行增强for的时候自动调用了类里面的 iterator()方法，该方法操作了类中的数组
 * 3、继承了AbstractCollection的类，将该类对象传给print(Collection<T> c)方法，也将类中的数组输出
 * 方法体内部用了增强for，在执行增强for的时候自动调用了里面的 iterator()方法，该方法操作了类中的数组
 * 4、继承了AbstractCollection的类还可以将类对象传给print0(Iterable<T> it)方法
 * 5、类中定义一个返回值是Iterable类型的方法，该类类对象显示的调用iterator()方法，从而输出iterator()方法操作的对象
 * 这里类中的数组和具体容器类中存储元素的空间是等价的
 * 同时可以设想，容器类实现了Iterable接口，可以用增强for，自动调用iterator()方法，将具体容器来中的元素输出
 * 元素存储在类内部的某个地方，对于具体容器类，我们用add()方法添加元素，但是这个元素不得存储在容器类中某个地方吗？
 * 可以设想这个类中的某个地方就是具体容器类存储元素的空间
 * 
 * static <T> List<T> asList(T... a) 返回一个受指定数组支持的固定大小的列表
 * Arrays.asList(T... a) 参数数组，返回的是List<T> 返回的List<T>使用传入的底层数组作为其物理实现
 * Integer[] arr = {1,2,3,4,5,6};
 * 当Arrays.asList(arr)改变时，数组也会改变
 * List<Integer> list = new ArrayList<>(Arrays.asList(arr));
 * 但是当list改变时，数组不会改变，因为list开辟了新的空间
 * 
 * */

/* Java反射机制运用实例
 * 新建一个class.txt配置文件，里面是键值对，类的全名，方法名
 * 通过Properties类将其加载到代码中
 * 根据加载的类名以及方法名，创建类的class文件对象，通过class文件对象，获取类的构造器对象
 * 通过构造器对象，创建类的相应地实例，通过class文件对象获取类的Method对象，或者获取属性成员Field
 * 当代码需要进行修改的时候，直接修改配置文件中的值就行了

/* 通过类全名获得class文件对象,通过class文件对象得到构造器，通过构造器创建类的实例，
	* 这里注意获取私有构造器，要用getDeclaredConstructor()，该方法传进几个参数，创建类的实例的时候
	* 方法的参数要和前者保持一致，也就是说，获取无参构造器，不能通过它创建带参数的实例，同样获得的带参构造器
	* 不能创建无参的实例，通过class文件对象得到Method对象，通过method对象调用实例方法
	* 这里通过class文件对象可以获得私有带参数的构造器，私有带参数的method对象

Java模板设计模式：
就是定义一个算法的骨架，将具体算法的经常变动的延迟到子类中来实现，将变动的封装成抽象类，用的时候直接重写这个抽象方法即可
优点：在定义算法骨架的同时，可以根据需要很灵活的实现具体的算法，满足用户多变的需求
缺点：算法骨架需要修改的话，需要修改抽象类

关于内部类，迭代器设计模式的总结
设计模式，工厂设计模式，装饰者模式，单例模式，动态代理
获得对象的类型信息(Linux系统下的文件)，Java反射机制，以及有关反射的例子

/* HashSet：是为快速查找而设计的Set，对速度进行了优化，要保证Set里面的元素唯一，
 * 	存放在Set里面的元素应重写hashCode()和equals()方法，但不能保证
 * TreeSet:底层实现是红黑树(自平衡二叉树)，不但保证元素唯一，在保证元素有序的过程中，要将元素转化为
 * 	Comparable接口类型，因此里面元素要实现Comparable接口，按照compareTo()中制定的方法进行维护元素
 * LinkedHashSet:保证了HashSet的速度，按照插入顺序进行维护元素
 * Map中使用键的要求和Set是一样的，键用于散列Map,要有相应的hashCode()和equals()方法，
 * 	键用于TreeMap要实现comparable接口
 * 一个类若未重写hashCode和equals方法，不同对象的hash值是不一样的
 * LinkedHashMap，遍历键值对的时候，按照元素插入顺序返回键值对
 * 推而广之，当需要的时候，使用散列的数据结构都要重写hashCode和equals方法，才能正确处理Set中的元素唯一
 * 使用tree的数据结构要实现comparable接口
 * 
 * GroundHog类继承了Object类，默认使用Object类的hashCode()方法，该方法使用对象的地址计算散列值
 * 第一个GroundHog(3)和第二个不是同一个对象，因此散列值不一样，通过键get不到值
 * 但是重写GroundHog类中的hashCode()和equals()方法可以保证能够get到GroundHog(3)对应的值，并且
 * 能保证Map中键的唯一性
 * */
可以将子类引用赋给父类，通过父类引用可以访问子类从父类继承或继承改写之后的成员，但不能访问子类特有成员，
这时可以进行向下转型为具体的子类类型，再访问子类中的特有成员，访问属性和方法的时候要注意 示例: ((A)aa).getArr();
 
