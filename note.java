public class Test {
    public static void main(String[] args){
        GenDemo<String> g1 = new GenDemo<String>();
        g1.show("hello");
        //g1.show(11); //
        g1.method("hello");
        g1.method(22);
        GenDemo<Number> g2 = new GenDemo<Number>();
        g2.show(new Float(22.22));
        g2.show(new Integer(22));
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

泛型类中的方法，在访问控制符后面，函数返回值前面没有类型参数，方法类型参数
要和类的类型参数要保持一致，集合List<E>的boolean add(E e);就是这种情况

泛型方法中，既有普通参数，也有类型参数，类型参数需要放在访问控制符后面、返回值类型前面
<>中可以有一个或者多个类型参数，多个类型参数,号隔开，同时也可以对类型参数的取值范围进行限定
泛型方法与泛型类没有必然的联系，不论类是否有类型参数，泛型方法有自己的类型参数，和泛型类中的类型参数没有关系

class A <T>{ //泛型类
    public void func(T t){  //泛型类中的方法，并不是泛型方法
    //方法修饰符后面，返回值前面若没有类型参数 <T>，这时方法和类中的类型应该一致
    A<Integer> aa = new A<Integer>();
    aa.func(5);  //调用func()方法传进去的参数只能是Integer及其子类类型
}
联系集合中List<E>    该接口下面的方法 boolean add(E e);并不是 <E> boolean add(E e);
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

泛型类中的方法用类型参数对方法操作的对象的类型进行限定
泛型方法为了提高代码的利用率
/* 
设计模式之单例
    public class MySingleton {
        private MySingleton(){
            //构造器私有，防止在类外new出对象
        }
        private static MySingleton singleton = new MySingleton();
        //随着类的加载而加载，保证在所有线程访问之前有该类的实例存在
        // 但是如果该实例不被使用，会造成浪费内存
        public static MySingleton getSingleton(){  //必须是静态方法，因为不能用类对象进行访问
            return singleton;
        }
    }
    class MySingleInstance{
        private MySingleInstance(){
            //private Constructor
        }
        private static MySingleInstance singleInstance = null;
        public synchronized  static MySingleInstance getSingleInstance(){
            if(singleInstance == null)
                return new MySingleInstance();
            return singleInstance;
        }   //因为方法体不是原子操作，所以要同步方法，单个线程走到这里，
            //不知道是否已经创建了对象，所以要进行if判断，如果为null就创建新的对象
            //否则返回原来创建过的对象
            //延迟加载的方式保证了线程同步，但是造成效率低下
    }
    class MySingle{
        private MySingle(){
        }
        private static MySingle single = null;
        public static MySingle getSingle(){
            if(single == null){
                synchronized (MySingle.class){
                    if(single == null)
                        return new MySingle();
                }
            }
            return single;
        }//双if嵌套，只有实例为null的时候，才进入同步块中，否则直接返回原来创建的对象
        //因此大大提高了运行效率，第二个if若为null就创建实例，否则返回原来创建的实例
    }

设计模式之装饰者模式
    装饰器和被装饰者具有相同的接口类型，装饰器不但实现了被装饰者所实现的接口，还持有一个被装饰者这的接口的引用，
    所有实现了该接口的对象都可以被该装饰者进行装饰，一个对象经过装饰器装饰之后类型不变
    装饰着模式适用于功能相似的类的创建过程中，可以在运行时，通过对对象进行装饰，使对象具有更加强大的功能，过程中会产生大量的类
    但是通过继承来实现类的功能的扩展,会造成 “类爆炸” 出现大量功能相似的类的出现
    装饰者模式在Java IO体系中有着很好的运用

设计模式之模板模式
    模板方法模式在一个方法中定义了一个算法骨架，将该算法的某些步骤延迟到子类来实现
    模板方法模式允许子类在不改变算法骨架的情况下，重新定义算法中的某些步骤
    一般类定义为抽象类，将模板方法一般是一系列方法的集合，该方法定义为final，
	这样就能防止子类修改算法执行顺序
    将需要子类直接继承的定义为final，需要子类进行重写的定义为抽象方法，
    此外还有一个钩子的概念，它能够影响算法执行的，可以根据需要进行选择
    模板方法的抽象类可以定义具体方法，抽象方法，以及钩子
    实现Comparable接口，使类具有可比较属性，自定义的类不像数值那样具有可比较属性，因此类要实现
    Comparable接口，重写compareTo方法，按照方法中规定的进行比较
    该方法需要传入另外一个对象，和这个对象本身进行比较

动态代理的学习：http://www.cnblogs.com/xiaoluo501395377/p/3383130.html

关于内存拷贝的相关问题
public void ensureCapacity(int minCapacity) {
    modCount++;  
    int oldCapacity = elementData.length;  
    if (minCapacity > oldCapacity) {
        Object oldData[] = elementData;  
        int newCapacity = (oldCapacity * 3)/2 + 1;  //增加50%+1
            if (newCapacity < minCapacity)  
                newCapacity = minCapacity;  
      // minCapacity is usually close to size, so this is a win:  
      elementData = Arrays.copyOf(elementData, newCapacity);  
    }  
 }
用一个局部变量引用该内存块，代表该内存块还有用，涉及到引用计数相关问题
因为下面进行元素拷贝的时候要用到该旧的内存块，因此增加一个局部变量引用该内存块
防止其他线程分配内存或者当前拷贝分配内存时，侵占该内存块，导致拷贝出现异常，从而保证拷贝过程安全进行
Object oldData[] = elementData;//为什么要用到 oldData[]
乍一看来后面并没有用到关于oldData，这句话显得多此一举！但是这是一个牵涉到内存管理的类，
所以要了解内部的问题，而且为什么这一句还在if的内部，这跟elementData = Arrays.copyOf(elementData, newCapacity); 
这句是有关系的，下面这句 Arrays.copyOf的实现时新创建了newCapacity大小的内存，然后把老的elementData放入
好像也没有用到oldData，有什么问题呢，问题就在于旧的内存的引用是elementData
elementData指向了新的内存块，如果有一个局部变量oldData变量引用旧的内存块的话，
在copy的过程中就会比较安全，因为这样证明这块老的内存依然有引用，分配内存的时候就不会被侵占掉，
然后copy完成后这个局部变量的生命期也过去了，然后释放才是安全的。不然在copy的的时候万一新的内存
或其他线程的分配内存侵占了这块老的内存，而copy还没有结束，这将是个严重的事情

return (T[]) Arrays.copyOf(elementData, size, a.getClass());
将elementData中size个元素拷贝到新分配的空间，拷贝元素类型是a.getClass()，返回的是新创建的空间

indexOf方法以及lastIndexOf方法两种实现是一样的，只是遍历顺序相反，分传入对象是不是为null
为null == ，不为null equals
remove传入为index以及元素，为index底层实现是System.arrayCopy(),为Object分为null不为null，用fastRmove实现

关于hashmap
HashMap实际上是一个“链表的数组”的数据结构，是每个元素存放链表头结点的一个数组，即数组和链表的结合体
哈希表，也称为哈希数组，数组的每个元素都是一个单链表的头节点，链表是用来解决冲突的，
如果不同的key映射到了数组的同一位置处，就将其放入单链表中，形成Entry链表

只有相同的hash值的两个对象才会被放到数组中的同一个位置上形成链表
如果两个 Entry 的 key 的 hashCode() 返回值相同，那它们的存储位置相同
当向一个HashMap中put一个键值对的时候，通过键的hashCode方法，通过hash函数计算哈希码
从而决定该键值对(Entry应该散列到哈希表的哪个位置)存放的位置，若该位置没有Entry
就将该键值对直接添加到散列的位置，若原来的地方有Entry，该键值对和链表中的元素逐一比较
如果这两个 Entry 的 key 通过 equals 比较返回 true，新添加 Entry 的 value 将覆盖集合中
原有Entry的value但key不会覆盖，如果这两个Entry的key通过equals比较返回false
新添加的 Entry 将与集合中原有Entry 形成 Entry 链，而且新添加的 Entry 位于 Entry 链的头部
调用equals方法有一个true说明有重复的键，返回旧值，并将旧值修改，全为false说明该位置处的
单向链表中没有重复的键，并将该键值对插入链表头部

取出元素的过程通过调用键的hashCode，hash，indexFor计算出该键值对在哈希表中存放的位置
如该位置没有元素返回false，如有Entry链(单向链表)就遍历该链表中的每个元素，通过调用equals
方法找到该键对应的值，并返回值，通过遍历找不到就返回false

通过hashCode方法确定键值对在哈希表中的位置，通过equals方法遍历Entry链在对应位置的链表中找到需要的元素
HashMap 在底层将 key-value 当成一个整体进行处理，这个整体就是一个 Entry 对象
HashMap 底层采用一个Entry[] 数组来保存所有的 key-value 对，当需要存储一个Entry对象时
会根据hash算法来决定其在数组中的存储位置，在根据equals方法决定其在该数组位置上的链表中的存储位置
当需要取出一个Entry时，也会根据hash算法找到其在数组中的存储位置，再根据equals方法从该位置上的链表中取出该Entry

HashMap数组扩容之后，最消耗性能的点：原数组中的数据必须重新计算其在新数组中的位置并放进去，这就是resize

我们知道java.util.HashMap不是线程安全的，因此如果在使用迭代器的过程中有其他线程修改了map
那么将抛出ConcurrentModificationException，这就是所谓fail-fast策略
在迭代器创建之后，如果从结构上对映射进行修改，除非通过迭代器本身的 remove 方法
其他任何时间任何方式的修改，迭代器都将抛出ConcurrentModificationException
因此，面对并发的修改，迭代器很快就会完全失败，而不保证在将来不确定的时间发生任意不确定行为的风险

static int hash(int hashCode);
该函数的作用是防止质量较差的哈希函数带来过多的冲突(碰撞)问题
static int indexFor(int h, int length);
作用是计算键值对被散列的位置，返回hashCode结果和table数组长度减1的与运算结果

void addEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<K,V>(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
}   函数的作用是在一个链表头部插入一个节点的过程，取出哈希表bucketIndex位置处的元素
并将该位置处的元素修改为新增的对象(造一个节点存储到该位置)，并将该元素的 next指向e
判断是否大于阈值，若是进行resize扩容

HashMap之所以不能保持元素的顺序有以下几点原因
	第一，插入元素的时候对元素进行哈希处理不同元素分配到table的不同位置
	第二，容量拓展的时候又进行了hash处理
	第三，复制原表内容的时候链表被倒置

关于ConcurrentHashMap
观察entry定义，发现除了value，其他所有属性都是用final来修饰的，这意味着在第一次设置了
next域之后便不能再改变它，因此对于一个已经存在的HashEntry链，不能从中间直接删除某个节点，
因为节点的顺次关系(next域)由final修饰，一旦确定便不能进行修改
因此将被删除节点之前的节点依次取出，通过用取出节点的数据构造新的节点，重新指定next域，从而实现元素的删除
因为被final修饰，变量变成常量，必须进行初始化，只能进行一次初始化，创建对象是可以在构造完结之前完成初始化

String 字符串直接赋值的方式 
Integer -128---127
Java String 不可变对象
public final class String implements java.io.Serializable, Comparable<String>, CharSequence{}
String实例中保存有一个char[]字符数组，char[]字符数组是以unicode码来存储的
String(byte[] bytes, Charset charset)是指通过charset来解码指定的byte数组，
将其解码成unicode的char[]数组，够造成新的String
private final char value[]; value是String类内部的一个私有成员，用来存储构造字符串的字符
String对“+”的支持其实就是使用了StringBuilder以及他的append、toString两个方法
        String s = "hello";
        String t = s + " world";
        String t1 = new StringBuilder(String.valueOf(s)).append(" world").toString();  //等价

1、Arrays.asList() 返回一个受固定尺寸数组支持List,仅支持不会改变数组尺寸的操作，任何试图修改底层数组
尺寸的操作都会抛出 UnsurpportedOperationException,但是可以修改list中的元素，只要不改变尺寸就行
2、将一个List经过Collections.unmodifiableList()包装产生一个不可修改的List，任何的修改
	都会抛出UnsurpportedOperationException
3、若一个类没有重写hashCode方法，将其用作map的键，就会调用Object类的hanshCode方法，
	默认使用对象的地址计算散列码
4、若自己创建一个map类要继承AbstractMap，在map类内部要自定义存放键值对的类某种Entry,这个Entry要实现
	Map.Entry接口，在Java集合类框架中，Entry实际上是一个单项链表，是用来解决hash冲突的
5、散列的价值在于速度，通过键的哈希码，将其散列到数组中的某个位置上
	桶位 哈希冲突 单向链表 17 37 result*37 + c  ensuCpacity resize rehash
6、基于对象的内容生成散列码
	生成速度首要，唯一性其次、散列均匀
	对同一个对象调用hashCode方法应产生相同的值，不能put时是一个值，get时散列到另外一个值
	hashCode方法必须速度快，且有意义，hash码必须基于对象的内容生成，更应该关注其生成的速度，而不是唯一性，
	好的hashCode方法应该使哈希码分布均匀，若分布比较集中，就会使这个地方的负载很重，hashCode和equals方法
	应该唯一确定一个对象的位置，相同对象产生相同的hashCode值，不同对象，产生不同的hashCode值
7、大量随机访问 or 插入删除操作 对于ArrayList插入删除，会导致部分元素整体的拷贝
8、HashMap是用来代替Hashtable的,已知数据项数目，指定初始容量，避免再散列的开销
9、LinkedHashMap底层不但维护散列结构，还维护链表结构用于维护元素插入顺序，正是由于链表结构，使其迭代速度很快
	Map负载因子很小的时候，会影响其用迭代器进行迭代的速度
10、并发修改异常，并发就是多个线程同时操作同一个容器，如当一个线程遍历容器的时候，另一个线程介入
对容器的内容进行修改，就应该抛出并发修改异常,使用fail-fast机制，会检测除当前线程之外的其他线程对容器的修改
一旦其他线程进行了修改，就会抛出ConcurrentModificationException
11、容器的只读版本以及同步版本Collections.X

输入输出重定向以及管道
因为是在屏幕(标准输出流的地方)进行交互的，所以必须以System.out为起点
数据输出地点是屏幕，可以 > a.txt 重新定向到文件中，将数据写到文件中
数据输入来源是标准输入流 < a.txt 重新定向到文件中，从文件中读取数据

数据库Connection是非常稀有的资源，一定要及时释放，晚创建，早释放
先创建的对象一定后释放
IO流、Connection一定要在finally中进行释放，如果在try中进行释放，如果某个语句抛出异常，
异常后面的代码就会得不到执行，资源释放的语句就会得不到执行，因此资源无法释放
Connection 对象要不要设置为static的，当访问量不是很大的时候，可以设置为static的，
但是当访问量很大，访问很频繁，就不要设为static的，如果资源设为静态的，
当一个进行访问时，另一个就不能访问，并发会受到影响

配置文件 xx.properties
资源文件放到工程目录下面，如果放到src目录下面，就是放到了类路径下面

关于try catch finally 
1、try中抛出异常，异常之后的语句便不会被执行，若异常之后有return，return不会被返回
2、try中没有异常，则catch便不会被执行
3、finally块一定会执行，无论是否try…catch
4、finally前有return，会先执行return语句，并保存下来，再执行finally块，最后return
5、finally前有return、finally块中也有return，先执行前面的return，保存下来，
	再执行finally的return，覆盖之前的结果，并返回

堆空间中分配对象，栈中分配
如果某个类没有重写(Object类的)equals 方法，那么会调用Object类的equals方法此时比较的是对象的地址值是否相等
	public boolean equals(Object obj){
		return (this == obj);
	}

自定义的类型若要放到TreeSet中，如果没有实现Comparable接口，会报错，抛出java.lang.ClassCastException
因为在TreeSet中操作的元素都是Comparable类型，因此放到TreeSet中的元素要实现Comparable接口
实现某个接口，可以转化为接口类型
HashSet通过重写对象的hashCode以及equals方法保证元素的唯一，那么TreeSet如何保证元素的唯一呢(有些元素放不进去)
通过重写Comparable接口中的compareTo方法
如果compareTo方法返回值一直是0 那么只会放进去一个元素，放进去的元素都和根节点进行比较，
等于0表示元素相同，不进行插入，返回正数右插，返回负数左插，因此元素插入完毕，也完成了对元素的排序
返回值一直是1是一种排序方式，是-1又是一种排序方式，但是不能保证元素的唯一
compareTo方法就是让使用者定义一种排序规则，告诉TreeSet按照哪种规则进行排序，要考虑全面对象的成员

计算机中的原码反码补码：
所谓原码就是二进制定点表示法，即最高位为符号位，“0”表示正，“1”表示负，其余位表示数值的大小
正数的原码反码补码一样，负数符号位不变，其余各位取反为负数的反码，负数反码加一为负数的补码
计算机对带符号数的表示有三种方法：原码、反码和补码
8位原码和反码能够表示数的范围是 -127~127
8位补码能够表示数的范围是 -128~127
一个字节表示的范围是-128~127，那肯定是用补码表示的
10000000-11111111表示-128到-1
00000000-01111111表示0到127 正数和负数采用补码进行表示，除了最高位相反，
数值位都是相同的，因而进行了统一
引入了补码概念：负数的补码就是对反码加一，而正数不变,正数的原码反码补码是一样的
在补码中用(-128)代替了(-0)，所以补码的表示范围为：(-128~0~127)共256个
注意:-128没有相对应的原码和反码，(-128) = (10000000)
对于一个字节的存储范围：
01111111 + 1 = 10000000		10000000 - 1 = 01111111
	 127 + 1 = -128				-128 - 1 = 127
补码减一，符号位不变，各位取反，变为原码

Java中的多线程是一种抢占式的机制，而不是分时机制。抢占式的机制是有多个线程处于可运行状态，但是只有一个线程在运行。 
共同点 ： 
1. 他们都是在多线程的环境下，都可以在程序的调用处阻塞指定的毫秒数，并返回。 
2. wait()和sleep()都可以通过interrupt()方法 打断线程的暂停状态 ，从而使线程立刻抛出InterruptedException。 
如果线程A希望立即结束线程B，则可以对线程B对应的Thread实例调用interrupt方法。如果此刻线程B正在wait/sleep/join，则线程B会立刻抛出InterruptedException，在catch() {} 中直接return即可安全地结束线程。 
需要注意的是，InterruptedException是线程自己从内部抛出的，并不是interrupt()方法抛出的。对某一线程调用 interrupt()时，如果该线程正在执行普通的代码，那么该线程根本就不会抛出InterruptedException。但是，一旦该线程进入到 wait()/sleep()/join()后，就会立刻抛出InterruptedException 。 
不同点 ：  
1.每个对象都有一个锁来控制同步访问。Synchronized关键字可以和对象的锁交互，来实现线程的同步。 
sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法。 
2.wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用 
3.sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常 
4.sleep是线程类(Thread)的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。
5.wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法(或notifyAll)后本线程才进入对象锁定池准备获得对象锁进入运行状态。

Scanner 下面的读取一个int值在用nextLine()读取字符串，会显示少一行
在缓冲中因为int num = sc.nextInt()仅仅是把第一行的整数值读取了，在管道缓存中
还有换行符enter,下面的nextLine()首先是读取换行符前面的数据，就是空字符串，所以读取的数据就会少一行
但是使用next方法就会解决这个问题

树的定义
树的4种遍历方式前序遍历、中序遍历、后序遍历，层次遍历
利用前序、后序和中序构建树，给出树求某种遍历之后的结果序列
前中后序三种遍历方式走过的路径都是一样的，访问某个节点的时机不同，
如果第一次遇到就访问是前序遍历，第二次遇到访问时中序遍历，最后一次遇到才访问是后序遍历
找叶节点(是在三种遍历方式上做稍微的改动)、求树的高度(递归)
二叉搜索树定义：
	1、树为空
	2、树不为空，要那么足下面的条件
	左子树的值都小于其根节点的值，右子树的值都大于其根节点的值，左右子树，如果非空又都是二叉搜索树
二叉搜索树的查找
Position Find(ElementType x, BinTree BST){
	if(!BST) return NULL; // 如果树为空直接返回NULL
	if(x > BST->Data)  // x大于根节点的值，向右子树中查找
		return Find(x, BST->Right);  //递归调用
	else if(x < BST->Data)
		return Find(x, BST->Left)
	else
		return BST;  //查找成功，返回查找节点的地址
}  // 以上是尾递归的实现，可以用循环改变

Position Find(ElementType x, BinTree BST){
	while(BST){   //树不为空，才能进入循环体
		if(x > BST->Data)
			BST = BST->Right;
		else if(x < BST->Data)
			BST = BST->Left;
		else 
			return BST;
	}
	return NULL;
}
二叉搜索树找最大值、最小值
最大值一定在树的最右端节点上，最小值一定在最左端节点上
递归实现：
Position FindMin(BinTree BST){
	if(!BST) return NULL;  //树为空，直接返回NULL
	else if(!BST->Left) //BST的左节点为空，那么BST一定是最小节点
		return BST;
	else 
		return FindMin(BST->left); //当前节点的左节点不为空，继续递归进行
}
循环实现：
Position FindMin(BinTree BST){
	if(BST){
		while(BST->Left)
			BST = BST->Left;
	}
	return BST;
}
二叉搜索树的产找、插入、删除，值和根节点的值进行比较，对整个树的操作转化为左子树或者右子树的操作
二叉搜索树插入节点，与根节点进行比较，大于根节点的值，在整个树中插入某个节点，
变为在右子树中插入某个节点，小于根节点的值，在真个树中插入某个节点，变为在左子树中插入某个节点
注意递归要有返回值，因为进行插入操作，根节点会发生变化
BinTree Insert(ElementType x, BinTree BST){
	if(!BST){  //树是否为空，如果为空，就直接造一个节点，将x的值赋予该节点，并返回该节点
		BST = malloc(sizeof(struct TreeNode));
		BST->Data = x;
		BST->Left = NULL;
		BST->Right = NULL;
	}
	else{  //某个根节点不为空，判断向左插入，还是向右插入
		if(x > BST->Data)
			BST->Right = Insert(x, BST->Right);
		else if(x < BST->Data)
			BST->Left = Insert(x, BST->Left);
		// else // x == BST->Data的情况 doing nothing 
	}
	return BST;
}  //注意函数的最后的返回值是整个树的根节点

二叉搜索树删除某个节点
被删除的节点是叶节点，直接将该节点的Left或者Right置为NULL
被删除节点只有一个儿子，将该节点的儿子替换掉该节点
被删除节点有左右儿子，进行转化，找该节点左子树中的最大值，将找到的最大值替换掉被删除节点，
并删除左子树中的最大值，将该节点的删除，转化为该节点左子树中最大值的删除，
或者找右子树中的最小值，并且，左右子树的最大值或者最小值一定是没有儿子或者只有一个儿子的情况
左子树的最大值节点，一定在该子树的最右端，因此不可能有右儿子，同理
右子树的最小值节点，一定在该子树的最左端，因此不可能有左儿子
//##
在某个子树进行删除操作，该子树根节点的值可能会发生变化，
例如，删除该子树根节点下面的节点，根节点不变，若该子树只有一个节点，删除之后，
该子树根节点变为NULL，
因此，要返回删除节点之后的子树的根节点
BinTree Delete(ElementType x, BinTree BST){
	BinTree temp;
	if (!BST) return NULL;
	if(x > BST->Data)  
		BST->Right = Delete(x, BST->Right);  // ##
	else if(x < BST->Data)
		BST->Left = Delete(x, BST->Left);
	else{  //找到了被删除的节点
		if(BST->Left && BST->Right){ //有两个儿子的情况
			temp = FindMin(BST->Right);
			BST->Data = temp->Data;  // 该节点的值替换为右子树中节点的最大值
			BST->Right = Delete(BST->Data, BST->Right); //转化为从该节点的右子树中删除最大值
		} 
		else{ //被删除节点有一个儿子或者没有儿子
			temp = BST;
			if(!BST->Left)
				BST = BST->Right; // BST是被删除节点，如果当前节点有右儿子BST = BST->Right
			else if(!BST->Right)  // BST没有右儿子直接BST = NULL(BST->Right)
				BST = BST->Left
			free(temp); 
		}
		return BST;
	}
}
二叉搜索树的插入删除的效率和树的高度有关系log2(N)

平衡二叉树，左右子树的高度只差称为平衡因子，平衡二叉树的平衡因子 <= 1
优先队列
一种特殊的队列，取出元素的顺序是按照优先权的(关键字)大小进行，而非元素进入队列的先后顺序
对优先队列的操作归纳为两种：将任意元素插入队列、每次取出队列中的最大值或者最小值
比如，每次向队列中插入一个新的任务，每次拿出一个优先级最高的任务去执行，实现这样功能的数据结构是优先队列
这里的“队列”并不是与栈对应的队列，而是一种叫法，实现的结构可以是数组、链表、树等，不限于队列
二叉搜索树实现优先队列，插入删除某个节点的效率和树的高度有关，因为每次删除的是一个最大值或者最小值节点
都是树的最左端或者最右端的节点，会导致树失衡，那么效率会降低
二叉搜索树应重点考虑删除最大值或者最小值，自然联想最大值和最小值在树根的位置(这样不会引起树的失衡)

堆，首先满足是完全二叉树(**)，任意节点的值都是其所在子树所有节点的最大值(最小值)称为最大堆(最小堆)
从根节点到任意节点(随便一条路径)节点都是有序的
堆的主要操作：创建堆、堆是否为空、堆是否已满，返回堆中元素最大值，插入一个元素
对的数据结构
typedef struct HeapStruct *MaxHeap;
struct HeapStruct{
	ElementType *Elements; 
	//存储堆元素的数组,其中对于i号节点，父节点是i/2, 左子节点是2*i(2*i<=N),右子节点是2*i+1(2*i+1<=N)
	int size;   //堆的当前元素的个数
	int capacity;   //堆的最大容量
};
创建堆
MaxHeap Creat(int MaxSize){
	MaxHeap H = malloc(sizeof(struct HeapStruct));
	H->Elements = malloc(sizeof((MaxSize + 1) * sizeof(ElementType)));
	H->size = 0;
	H->capacity = MaxSize;
	H->Elements[0] = MaxData;  //哨兵
	return H;
}
最大堆的插入，满足从根节点到任意节点都是有序的
将元素插入到完全二叉树中最后的位置，然后和其父节点进行比较，调整满足最大堆
void Insert(MaxHeap H, ElementType item){
	int i;
	if(isFull(H))
		return;
	i = ++H->size; // i表示被插入元素的位置
	for( ; H->Elements[i / 2] < item; i /= 2){
		H->Elements[i] = H->Elements[i / 2];  //如果父节点的值小于被插入节点的值，将父节点下移到被插入位置
	}
	H->Elements[i] = item;  //将item插入到合适的位置
	//先将待插入节点放置到i的位置，若其父节点小于该节点值，父节点下移替换该节点的值，然后依次进行
	如果被插入的位置是堆顶的位置，那么直接将item插入到堆顶就行，这样循环条件中每次都得判断是否满足i > 0
	也就是H->Elements[i / 2] < item && i > 0
	为了每次不进行判断，设置哨兵为H->Elements[0] = MaxData，堆中元素从下标为1的位置开始存放
	且不用每次都在循环中进行判断i > 1，因为所有元素的值都小于哨兵，如果堆顶元素比被插入元素item小，
	堆顶元素下移，下次循环H->Elements[i / 2] < item 	==> 	H->Elements[0] < item 必不满足，进而不执行
}

最大堆的删除：解决的问题是已知左边是一个堆，右边是一个堆，来了一个元素，如何将整体调成一个堆
也就是删除 Elements[1]，然后重整去掉树根之后的二叉树
将完全二叉树最后一个节点移到树根的位置，然后找树根左右子节点的最大值，替换树根的位置，
下次循环树根从找到的左右子节点开始，依次进行调整，直至重新构建出最大堆
ElementType DeleteMax(MaxHeap H){
	int parent, child;
	ElementType tmp, result;
	if(isEmpty(H))
		return;
	result = Elements[1]; // 若堆非空，取出最大元素，保存用于最后返回
	tmp = H->Elements[H->size--];  //取出堆最后一个元素，并将size值减一
	for(parent = 1; parent*2 <= H->size; parent = child){
		child = parent*2;  //child先置为左子节点
		if(child != H->size && H->Elements[child] < H->Elements[child + 1])
			child++;
		//以上就是找父节点左右儿子中的较大值，并且下标用child表示
		//for循环parent*2 <= H->size进入，如果child != H->size那么说明有右儿子，才进行左右儿子值大小的比较
		if (tmp >= H->Elements[child])
			break;  //如果堆中最后一个节点值大于被删节点的左右儿子中的较大值，直接跳出
		else
			H->Elements[parent] = H->Elements[child];  
			//被删节点用其左右儿子中的最大值替换，然后下一次从该child的位置开始，parent = child
	}
	H->Elements[parent] = tmp;
	return result;
}
建立最大堆，将N各元素按照最大堆的要求存放到一个数组中
1、将N个元素依次插入初始为空的堆中，整体时间复杂度为时间复杂度为 N*log2(N)
2、先将元素构建为完全二叉树，然后再调整成一个堆，和删除思想一样，
	只不过不是从堆顶开始，而是从下至上一次调成堆

图：“多对多”的对应关系，线性表和树都可以看做图的特殊情况
图用来反映若干个体之间关系的数据结构

顶点，通常用V(vertex)用来表示顶点的集合
边，通常用E(edge)用来表示边的集合
用顶点对表示边(v, w)∈ E， v, w ∈ V 无向边
用顶点对表示边<v, w>∈ E， v, w ∈ V 有向边
一般不考虑平行边和子回路
连通：从v到w存在一条路径，则w和v是连通的
路径：v到w的路径是一系列顶点的集合,任意一对儿相邻顶点间都有图中的边，
路径的长度是路径中边的数目，如果带权，就是所有边权重之和
w到w的所有顶点都不相同，称为简单路径
起点等于终点的图称为环
连通图：图中任意两点间是连通的
连通分量：无向图的极大连通子图
	极大顶点数，增加一个顶点，图就不连通了
	极大边数：包含子图中所有顶点相连的所有边

图的存储：邻接表、邻接矩阵
	邻接矩阵
		用一个二维数组来表示图，图中有N个顶点，占据N*N的空间，对角阵的元素为0
		顶点的度，和顶点相邻接的点中，由该顶点发出的称为出度，回到该顶点的称为入度
		无向图，没有入度和出度的概念，只有度，邻接矩阵对应行(列)非零元素的个数
		有向图，出度，对应行非零元素的个数，入度，对应列非零元素的个数
		方便找任意顶点的邻接点
		无向图，对应行或列非零元素的个数(因为无向图邻接矩阵是对称的)
		有向图，对应行非零元素的个数加上对应列非零元素的个数(无向图邻接矩阵是非对称的)
		缺点：对于稀疏图(点很多，边很少)的存储，造成空间的浪费
	邻接表，类似哈希表
		邻接表的某一项，是某个顶点的所有邻接点组成的链表
		邻接表的存储空间：对于V个顶点，E条边，需要V个头指针，2*E个边节点(每个节点至少有两个域)
		度的计算：对于无向图，度很好计算，邻接表某一项有多少个边节点
		对于有向图，出度很好计算邻接表某一项有多少个边节点，入度，不好计算

		
		图的遍历
	DFS: 树的先序遍历的推广
	void DFS(Vertex v){
		visited[v] = true;
		for(w = v.adj()){
			if(!visited[w]){
				DFS(w);
			}
		}
	}
	
	BFS: 树的层序遍历
	先被访问的顶点的相邻顶点访问先于后被访问顶点的相邻顶点访问
	树的层次遍历，树根入队，进入循环:节点出队，访问该节点的左右儿子入队
	void BFS(Vertex v){
		Vertex tmp;
		Q = creatQueue();
		visited[v] = true;
		Enqueue(v, Q);
		while(!isEmpty(Q)){
			tmp = Dequeue(Q);
			for(w = tem.adj())  //对于出队顶点的每一个邻接顶点，如果没被访问过，标记被访问，并且入队
				if(!visited[w]){
					visited[w] = true;
					Enqueue(w, Q);
				}
		}
	}  // 该过程和树的层序遍历类似

对于不连通的图如何访问图中所有的顶点？
对于不连通的图，可以考虑其连通分量
采用DFS、BFS访问过的节点是连通的(访问的是连通区域，节点之间是直接或者间接有边的)
如果用一次DFS、BFS遍历，孤立的点就会被丢掉，孤立的节点不会被遍历到
每调用一次DFS(v)、BFS(V)就把v所在的连通分量遍历一遍(和v相连通的所有顶点都会被访问)
void listComponents(Graph G){
	for(each v in G){
		if(!visited[v]){
			DFS(v);  //BFS(v);//这里会把v所在的连通分量遍历一遍(和v相连的所有顶点都会被访问)
		}
	} //接着对于下一个顶点w，若没被访问过，就进行DFS(w),这样就会把图中所有的顶点访问一遍
}

定义图的数据结构
typedef struct GNode *PtrtoGnode;
struct GNode{
	int Ne;  //边数
	int Nv;  //顶点数
	WeightType G[MaxVertexnum][MaxVertexnum];
	DataType D[MaxVertexnum];  //存储顶点数据
};
typedef PtrtoGnode Mgraph;

初始化有Vertex个顶点但是没有边的图
typedef int Vertex;
Mgraph vreatGraph(Vertex vertexNum){
	Mgraph graph;
	graph = (Mgraph)malloc(sizeof(struct GNode));
	graph->Nv = vertexNum;
	graph->Ne = 0;
	for(Vertex v = 0; v != vertexNum; ++v){
		for(Vertex w = 0; w != vertexNum; ++w){
			graph->G[v][w] = 0;  // or INFINITE
		}
	}
	return graph;
}
向图中插入一条边
typedef struct ENode *PtrtoENode;
struct ENode{
	Vertex v, w;  // 边的顶点
	WeightType weight;   // 权重
};
typedef PtrtoENode Edge;

void insertEdge(Mgraph Graph, Edge E){
	Graph->G[E->v][E->w] = E->weight; 
	Graph->G[E->w][E->v] = E->weight; 
}  //无向图
void insertEdge(Mgraph Graph, Edge E){
	Graph->G[E->v][E->w] = E->weight; 
}  //有向图

深度优先栈来实现，递归实现(隐式的采用了栈)，广度优先采用队列来实现
"小老鼠走迷宫"问题
深优，广优在图中所走路径的特点
访问下一个节点的规则(有其数据结构决定)
深度优先进行图的遍历，找连通路径
广度优先找最短路径


逆序对：如果i < j,A[i] > A[j],则称(i, j)是一对逆序对
交换两个相邻元素，能消除一个逆序对
任意N个元素组成的序列平均逆序对的个数是N*(N-1)/4
任何仅以交换相邻两元素的的排序算法的平均时间复杂度是Ω(N^2) Ω是下界
也就是说最好最好是(N^2)，每次交换相邻两个元素仅能去掉一个逆序对，
要想提高排序速度，设想每次交换消除多个逆序对，相邻多个元素进行交换(希尔排序 shell sort)

选择排序，当前元素和之后所有元素进行比较，如果当前元素不是最小元素，进行交换，每次得到一个未排序的最小元素的下标
如果使用遍历时间复杂度是N，若能很快得到未排序的最小元素的下标,(最小堆实现时间复杂度是log(N))
插入排序、冒泡排序每次交换的是相邻元素，仅能消除一对逆序对，对于逆序对很少的序列，插入排序效率很高
希尔排序，是一种基于插入排序的快速排序算法，通过交换不相邻的元素使数组部分有序，
最后通过插入排序将部分有序的数组进行排序，利用了插入排序的简单，同时克服了插入排序交换相邻元素仅能消除一对逆序对的缺点
目的是为了每次交换尽可能多的消除逆序对,定义增量间隔，(先进行5间隔排序，再进行4间隔排序...)
Dk-1间隔的排序会保持原来Dk间隔排序结果仍然是有序的(更小间隔的排序会保持大间隔排序的结果)

快速排序适用于大规模随机数据的排序，每次选定基准在换分完成，都能最终正确排定一个元素
(不像插入排序，每次插入元素位置是临时的，不是最终的)
分治思想：先"分",使左边元素逗比基准小，使右边的元素都比基准大，再"治"，分别对左右边进行“分治”
基准选择与时间复杂度分析：
	好的情况，每次选择的基准刚好等分数组 T(N) = O(NlogN)
	最坏的情况对一个有序的的序列进行快排，每次选最左边(最小)的元素作为基准，这样就会将序列
	划分成左边空序列，右边N-1长的序列，然后递归对N-1个元素进行处理，然后再次划分，
	选定第二小元素进行划分序列，左边空序列，右边N-2长序列，每次划分只会排定一个元素，
	这样导致序列需要更多次切分这样的是N^2时间复杂度的
	复杂度的递推公式是
		T(N) = O(N) + T(N-1)
			 = O(N) + O(N-1) + T(N-2)
			 = O(N) + O(N-1) + O(N-2) + T(N-3)
			 = O(N) + O(N-1) + O(N-2) + O(N-3) + ... + O(1)
			 = O(N^2)
	切分不平衡造成快排性能很低效，需要较多次的切分，极端情况每次只能排定一个元素
	引入随机化解决切分不平衡的问题
对于和基准相等元素的处理(有大量重复元素的排序问题)
	1、从右向左扫描遇到 >= 基准的停下来，从左向右遇到 <= 基准的停下来，这样可能会引入许多次不必要的元素交换
	但是在一些特定的情况下(有大量重复元素的排序)避免快排性能沦为平方级别，这样能保证切分比较平衡
	2、另一种处理方法是放过与基准相等的元素，这样虽然能够避免不必要的元素交换，但是在某些情况下会导致
	指针一直向左边或向右边移动，导致切分不平衡，快排沦为平方级别的性能
	(极端对于所有元素都相等的序列的排序)
提高快速排序的性能
	1、随机化序列
	2、三分取样(用序列一小部分的中位数作为基准，3取中位数，5取中位数，7取中位数...) 
	3、小规模序列换成插入排序 
		if(lo >= hi)
			return;
		if(lo + M >= hi){
			insertSort(arr, lo, hi);
			return;
		}
归并排序，将一个序列排序，将序列分成两部分，分别进行排序，然后将两个排序完毕的序列进行归并
归并排序所需时间和Nlog(N)成正比(最好、最坏、平均都是NlogN)，但是需要额外的空间和N成正比
归并排序需要Nlog(N)/2 -- Nlg(N)次比较
归并排序先进行递归分割，递归完毕再进行归并操作
快速排序先对序列进行操作，当第一次操作完毕再进行递归

对于分治算法时间复杂度的分析，归并操作的时间复杂度是O(N)
对于N长的序列时间复杂度是T(N)，对于左半部分时间复杂度是T(N/2)，对于右半部分时间复杂度是T(N/2)
然后进行一次merge时间复杂度是O(N)，故T(N) = T(N/2) + T(N/2) + O(N)

各种排序算法的性能比较
冒泡，插入是交换相邻元素，不需要交换的时候就不交换，因此是稳定的
选择排序，跳着进行交换，因此是不稳定的 5 5 3
希尔排序打破N^2性能，取决于增量序列的选择(最坏情况下是N^2性能)，由于增量序列的存在
希尔排序交换不相邻的元素(跳着进行交换)因此是不稳定的
堆排序、归并排序平均和最坏的性能都是O(NlogN),是稳定的，但是需要额外空间O(N)，堆排序不需要额外空间
快排平均时间复杂度是O(NlogN)，最坏是O(N^2),由于递归实现，需要额外空间O(logN)
堆排序和快排都是不稳定的

排序算法的稳定性
冒泡、插入、归并、基数排序树稳定的
选择、希尔、快排、堆排序是不稳定的
快排不稳定的原因 4 3 (3) 3 5 如果选择中间的3作为基准，那么 > 3的必然被划分到3的右边，小于3的必然被划分到左边 3 3 3 4 5
希尔排序不稳定的原因 5 1 1 3 当增量间隔是2的时候，第3个元素会和5进行交换，那么值相同的1位置会交换

冒泡、插入、选择、希尔、堆排序空间复杂度是O(1)
快排空间复杂度是O(logN) -- O(N) 调用栈占据的空间，取决于序列划分的方式
归并排序空间复杂度是O(N) 存储辅助数组
基数排序的空间复杂度是O(M) M是选择的桶的数量
快排之所以称为快排，是因为他相对于归并排序和堆排序常量系数比较小

查找
线性查找 O(N)
二分查找 O(log2(N)) (静态查找)
二叉搜索树 O(h) h为树的高度
平衡二叉树 O(log2(N)) 

图中最短路径的问题
单源最短路径问题：源点是固定的，求其到其他所有顶点的最短路径
核心：按照非递减的顺序找出到各个顶点的的最短路径
	无权图的单源最短路径问题
		dist[w]数组，源点S到顶点W的路径
		pathTo[w] = v;到达当前点w的顶点是v,是由那个顶点到达当前顶点的
		广度优先搜索算法解决
		void shortestPath(Vertex s){
			Vertex v;
			Q = creatQueue();
			Enqueue(s, Q);
			while(!isEmpty(Q)){
				v = Dequeue(Q);  //源点到出队的点v的路径是已经算好了的
				for(Vertex w : tmp.adj(v)){
					if(dist[w] != -1){  
						dist[w] = dist[v] + 1;
						pathTo[w] = v;
						Enqueue(w, Q);  //计算好源点到当前点的距离，将其入队
					}
				}
			}
		}
		//dist[w] != -1表示w点没有没被访问过，就访问该顶点并更新源点到该点w的距离
		//是该顶点前一个顶点的距离值+1
		//pathTo数组是用来存储路径上面的节点的，最后反向输出就是路径
		//时间复杂度分析：图中每个顶点入队一次，出队一次，对于每个点的邻接点，实际上是访问了每条边
		// T(N) = O(|V| + |E|)
	有权图的单源最短路径问题
		Dijkstra算法
		维持一个集合S = {源点S + 已经确定了最短路径的顶点Vi}
		每次从未收录的顶点中选取一个dist值最小的顶点v进行收录(贪心)
		将一个顶点v放到集合中，v只会影响源点到v的邻接点w之间的dist值，就是只会影响和顶点v有直接路径的点w
		源点到v的邻接点之间的距离会经过顶点v进行松弛并更新，
		如果dist[v] + E(v, w) < dist[w]; 那么dist[w] = dist[v] + E(v, w),因此源点到未收录的点的距离只会变短或者不变
		void Dijkstra(Vertex s){
			while(1){
				v = 未收录顶点中dist值最小的顶点;
				if(这样的v不存在)
					break;
				collected[v] = true; //收录顶点v
				for(v 的邻接点 w){
					if(collected[w] = false){  //保证松弛的是未收录的点
						if(dist[v] + E(v, w) < dist[w]){
							dist[w] = dist[v] + E(v, w);
							pathTo[w] = v;
						}
					}
				}
			}
		}
		算法的时间复杂度取决于采用什么方式来找未收录的且dist值最小的点
		1、采用遍历寻找，更新dist[w]的值操作很方便 T(N) = O(|V|^2 + E) 对于稠密图适用
		2、采用最小堆寻找未收录的且dist值最小的点，那么更新dist[w]的值操作要维持最小堆的结构
			T(N) = O(|V|*log|V| + |E|*log|V|)= O(|E|*log|V|) 对于稀疏图适用
		
多源最短路径问题：求任意两点之间的最短路径
	如果要让任意两点之间的路程变短，只能引入第三个点(顶点k)，并通过这个顶点k中转即a->k->b
	才可能缩短原来从顶点a点到顶点b的路程,那么这个中转的顶点k是1~n中的哪个点呢？甚至有时候不只通过一个点
	而是经过两个点或者更多点中转会更短
	在只通过1号顶点中转的情况下,邻接矩阵中的元素被更新，如果再经过2号顶点进行中转，邻接矩阵中的元素会在之前的基础上被再次更新
	基本思想就是：最开始只允许经过1号顶点进行中转，接下来只允许经过1和2号顶点进行中转……允许经过1~n号所有顶点进行中转
	求任意两点之间的最短路程，用一句话概括就是：从i号顶点到j号顶点只经过前k号点的最短路程，其实这是一种“动态规划”的思想
	for(k=1;k<=n;k++)
		for(i=1;i<=n;i++)
			for(j=1;j<=n;j++)
				if(e[i][j]>e[i][k]+e[k][j])
					 e[i][j]=e[i][k]+e[k][j];
	Floyd算法

C++值传递、引用传递、指针传递
值传递:形参是实参的一份拷贝，对形参的修改不会影响实际参数
引用传递：形参是实参的引用，对形参的修改就相当于对实参的修改(如果不希望改变参数的内容，就用const修饰，const引用能够避免拷贝，提高效率)
指针传递：就是值传递，传递的是地址的拷贝，对形参的修改不会影响实参的值，但是可以通过指针去修该指针所指向对象的内容
Java只有值传递，形参是实参的一份拷贝，对于基本数据类型以及String，不能通过形参修改实参的值
对于引用类型(对象、数组)，形参是实参的一份拷贝，两者指向同一个对象，可以通过形参修改对象的内容

泛型只支持引用类型，不支持基本数据类型
泛型实现任意数组中元素的交换
	public <T> void swap(T[] arr, int left, int right){
		T t = arr[left];
		arr[left] = arr[right];
		arr[right] = t;
	}  
该方法的参数必须是引用类型，也就是传进去的第一个参数可以是Integer[]，不能是int[]

二叉树的前序、中序、后序遍历的递归与非递归的实现
非递归实现：
	前序：将根节点压入栈，如果栈不为空，就弹出栈顶元素记为curr,右儿子不为空，将右儿子压入栈，左儿子不为空，将左儿子压入栈
	后序：建立两个栈s1，s2，将根节点压入栈s1，如果栈不为空，从栈中弹出元素将其放入s2，如果该节点左右儿子不为空，
		依次将左右儿子入栈s1，直到栈s1为空，依次从s2中弹出元素，弹出顺序就是后序遍历的顺序
	中序：对于以curr为头结点的整棵树，依次将整棵树左边界压入栈中，即不断令curr = curr.left;当curr为空时，弹出节点(访问)
		令curr = curr.right，重复上面的过程
	层序遍历：输出 分层，last以及nlast指针
二叉树的序列化，也叫二叉树的持久化，就是将二叉树的信息存储到文件中
假设序列化的结果字符串为str，初始时str等于空字符串。先序遍历二叉树，如果遇到空节点，就在str的末尾加上“#!”
“#”表示这个节点为空，节点值不存在，当然你也可以用其他的特殊字符，“!”表示一个值的结束，如果遇到不为空的节点
假设节点值为4，就在str的末尾加上“4!”

二叉搜索树：查找、最大值 、最小值、插入、删除
二叉搜索树按照中序遍历(左、根、右)的方式遍历整棵树，得到的序列一定是从小到大排列的，反之也成立
红黑树、自平衡二叉树(AVL树)等，其实都是二叉搜索树的不同实现，都力争使搜索效率更高而已

满二叉树：除了最后一层没有子节点外，其他任意节点都有两个子节点
节点个数N和输的高度L关系
第K层节点最多是 2^(k-1)
N = 2^L - 1
完全二叉树是在满二叉树的概念上面定义的，允许缺少若干节点，堆结构就是一种完全二叉树
一个节点的后继节点是指，该节点在中序遍历序列中的下一个节点
前驱节点是该节点在中序遍历中的前一个节点，注意前驱后继而是中序遍历中的前一个节点或者后一个节点

平衡二叉树，树为空，若树不为空，任意节点的左右子树高度差 <= 1
求树的高度，先求根节点左右子树的高度，左右子树高度的最大值加一
(树的高度等于树所有子树的高度的最大值--含有递归的意味)
1、输出整棵树的叶子结点，采用某种遍历方式，如果当前节点左右儿子都为空，输出该节点
2、判断一棵树是不是二叉搜索树，按照中序遍历进行，如果当前节点的值一直大于上次遍历的值，则是二叉搜索树
	因为二叉搜索树的中序遍历是从大到小排序的
3、求树的高度，判断树是否是平衡二叉树，采用后序遍历的方式，先求左子树的高度HL，
	再求右子树的高度HR，求左右子树最大值max(HL, HR)，最大值加一就是整棵树的高度
	树的遍历 ---- 后序遍历 ---- 求树的高度 ---- 树是否为平衡二叉树
4、判断一棵树是不是完全二叉树，采用层序遍历的方式，总体分为当前节点是叶节点的情况，当前节点不是叶节点的情况
	不是叶节点的情况：当前节点有右节点，没有左节点，直接返回false
	左节点不为空添加左节点到栈，右节点不为空，添加右节点到栈，如果右节点为空，那么isLeafNode置为true
	此后的节点必须全部是叶节点才能保证二叉树是完全的
	是叶节点的情况：但此时如果节点有左节点或者有右节点，或者左右两个节点都有，直接返回false	

java进制转换
	Integer.toBinaryString() 将其他进制(8 10 16)转换为2进制，数字以0开头表示8进制，以0x开头表示16进制
	相应的有其他进制转化为8进制 Integer.toOctalString()、16进制Integer.toHexString()
	Integer.valueOf(String s, int radix),将其他进制转化为10进制，
	但是传入的字符串s必须是radix形式表示的，也就是说s要和radix相匹配
	String.parseInt(String s, int radix)功能一样，如果没有radix参数，默认转化为10进制
	当然输入的字符串要符合十进制
	public static Integer valueOf(String s, int radix) throws NumberFormatException {
		return Integer.valueOf(parseInt(s,radix));
	}

移位操作与正负数无关，它只是忠实的将所有位进行移动，补0，舍弃操作
与运算(&)
1、与0相与可清零
2、与1相与可保留原值
或运算(|)
1、与0相或可保留原值
2、与1相与可齐设1
异或运算(^)
1、与0异或保留原值
2、与1异或比特值反转
3、可通过某种算法，使用异或实现交换两个值
异或运算是有结合律的
取反(~)

一个数与本身异或结果是0，与0异或结果是本身，而异或满足结合律和交换律
也就是说一个数X异或另一个数N偶数次结果是其本身 X^N^N == X  X^N^N^N^N == X
通过异或交换两个数 a b
a = a^b;
b = a^b;
a = a^b; 原理还是云用异或的次数
>> 是高位补符号位，>>>高位补0
Java中取出一个32位数的符号位，将该数 >> 31位，因为>>是高位补符号位因此如果是复数返回的是-1，因此整体再和1 &运算
或者直接>>>高位补0，因此可以忽略输入的数字的正负
	public static int getSign(int val){
    	return (val >> 31)&1;  //val右移31位，保留的是最高位，是符号位,与 1& 运算符
		//return (val >>> 31);
    }

散列：计算位置、解决冲突
散列(哈希)的价值在于速度，而不在于唯一性
基于对象的内容哈希，散列要尽可能的均匀，避免某些地方负载过重
散列函数的构建，除留余数法
1、典型的哈希函数拥有无限的输入值域、但是输出域是有限的
2、输入值相同的时候，返回值一样
3、输入值不同的时候，返回值可能相同
4、不同输入对应的哈希值，整体分布应该均匀
1--3是哈希函数的基础，4是评价一个哈希函数优劣的重要标准
很相似的输入如果能产生较大差别的哈希值，那么哈希函数是优秀的

在原码反码补码基础上面，数据表示符号位+数据位
对以一个字节能表示的数据范围是-2^7 ---- 2^7 - 1 (-128 ---- 127)
对于4个字节32位的数据，范围是-2^31 ---- 2^31 - 1 (-2147483648 ---- 2147483647)
刚好是int、Integer的最大值和最小值，大约共40亿个数字

1G大约是10亿字节 2^30字节 80亿比特
1、Map - reduce
map阶段 用哈希函数将大任务分成若干个小任务
reduce阶段

2、10亿IP地址实现排序，每个IP地址不相同
利用bitmap，每个IP地址可以转化为32位无符号整型来表示，申请一个2^32-1 bit(512M)大小的空间
没出现一个数将对应的bit位描黑，然后对bitmap进行遍历输出即可
3、在20亿个32位整型数据的大文件，找出其中出现次数最多的数
	键值对解决办法：类似词频统计，如果20亿个数都相同，频次最大值是20亿不会超过Integer.MAX_VALUE
	如果20亿个数都不一样，那么会产生20亿条记录，每条记录是一个键值对，占用8字节，共占用160亿字节的空间
	也就是16G的大小
	利用哈希函数将20亿个数字的大文件分成16个小部分，每部分在不同的机器上面处理
	由于哈希函数的性质，同一种数，会出现同一个机器上面，不会出现在不同的机器中
	对于每一小部分，分别统计频次最高的数，最后16个频次进行比较即可
	
4、top K问题
	哈希函数分流、哈希表做词频统计、小根堆、外排序
5、一致性哈希算法
	一致性哈希算法解决的是服务器集群中，当服务器节点数目发生变换的时候，导致原来散列的地址失效的问题
	传统hash算法是对服务器数目取模运算，而服务器数目N变化时，所有对象必须重新计算哈希值，然后对新的机器数目M取模
	一致性哈希算法是对一个固定的值(2^32)进行取模运算，不受机器数目变动的影响
	它不会因为服务器节点数目变化而受影响
	无论是服务器、还是被存储的对象，都对2^32进行取模运算，计算其将会被散列到哈希环中的那个位置上面
	hash(服务器的IP地址) % 2^32 服务器被散列到哈希环中不同的位置
	hash(对象的唯一标识) % 2^32 对象被散列到哈希环中某个位置上面，沿着这个环顺时针找遇到的第一台服务器，然后存储
哈希环的偏斜：服务器被散列的位置很集中，有大量的空间没有服务器存在，这个时候会造成很多对象存储在同一个服务器中
	造成服务器压力过大，或者有些服务器没有存储的现象
	解决办法：引入虚拟节点，虚拟节点是现实物理节点在哈希环中的复制，虚拟节点越多，越能减轻哈希环的偏斜
	哈希环上面的节点越多，对象被均匀存储的概率就越大


Bloom Filter  哈希函数和位运算联合使用
用途： 判断一个元素是否在一个集合中、检查一个英语单词是否正确拼写
布隆过滤器：网页黑名单系统、垃圾邮件过滤系统、爬虫的网址判断重复系统
且容忍一定的失误率、对空间要求严格，应该考虑布隆过滤器
一个布隆过滤器是一个精确的集合，使用很少的空间，可以做到很高的精确率
对象经过K个独立且很优秀的哈希函数，产生k个哈希值，每个哈希值对m取模，结果映射到一个大小为m的bitArray中k个位置,
bitArray中只有两种状态(0 1)，相应的位置被置为1，查询时判断这k个位，有0则该元素肯定不在集合中
都为1则该元素有可能在集合中，是集合中的元素一定会被检测到，不是集合中的元素可能会被检测到(宁可错杀三千，不放过一个)
优点： 有良好的空间效率和时间效率，插入、查询O(n)，安全性高(不保存元素本身)
缺点： 正确率低，有可能不在集合中的元素在位数组查询的位得到都为1
单个样本大小(URL的长度)等不影响bitArray的大小(只与哈希函数的输出以及失误率有关)，它只影响k个哈希函数的参数
bitArray大小的确定m = -n*lnp / (ln2)^2
哈希函数数目k的确定 k = ln2*m/m


	
	
	




