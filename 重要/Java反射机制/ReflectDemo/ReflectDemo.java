package ReflectDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {
/* 获取class文件对象的方法
 * 推荐使用Class.forName(String str);参数是一个字符串，可以将其配置到配置文件中去
 * */
	public static void main(String[] args) throws Exception {

		Class c = Class.forName("ReflectDemo.Student");
		//注意这里一定是类的全名，不能只写类名
		Class c1 = Student.class;//利用类的静态属性得到class文件对象
		Student s = new Student();
		Class c2 = s.getClass();  //利用Object类下面的getClass()方法返回对象的class文件对象
		Student s1 = new Student();
		Class c3 = s.getClass();
		System.out.println(c2 == c3);
		System.out.println(c == c1);
		System.out.println(c1 == c2);
		//利用得到的字节码对象得到类的构造器
		System.out.println("得到无参的构造器");
		Constructor constructor1 = c.getConstructor(); //得到无参数的构造器
		//得到一个构造器，它反映此 Class 对象所表示的类的指定公共构造方法，根据参数指定，切实public权限
		//并且该方法是可变参数，并且传递的不是String,int类型，而是String以及int对应的Class类型
		//因此传递的是String.class int.class
		System.out.println(constructor1);
		System.out.println("得到指定参数的构造器对象");
		Constructor constructor2 = c.getConstructor(String.class,int.class,String.class);
		//得到有三个参数的构造器
		System.out.println(constructor2);
		System.out.println("用getConstructors()方法得到所有的public权限的构造器");
		Constructor[] con = c.getConstructors();  //返回构造器数组，注意返回的是public权限的构造器
		for(Constructor constructor : con){
			System.out.println(constructor);
		}
		System.out.println("用getDeclaredConstructors()方法得到所有构造器，不论权限");
		Constructor[] constructor3 = c.getDeclaredConstructors(); //得到所有构造器，无论其权限
		for(Constructor constructor : constructor3){
			System.out.println(constructor);
		}
		System.out.println("利用构造器newInstance()方法，创建类对象");
		//通过构造器创建对象
		Object obj = constructor1.newInstance();//constructor1获取的是对象的无参构造
		System.out.println(obj);
		System.out.println("利用构造器newInstance()方法，创建类对象");
		Object obj1 = constructor2.newInstance("张三",22,"西安");//constructor2获取的是对象的带参构造
		System.out.println(obj1);
		
		System.out.println("setAccessible(true)方法，访问对象的私有构造方法");
		Constructor cons = c.getDeclaredConstructor(String.class);
		//抛出 java.lang.IllegalAccessException异常，不能访问private修饰的构造器，提供一个方法
		cons.setAccessible(true);
		Object obj3 = cons.newInstance("私有构造器");
		System.out.println(obj3);
		System.out.println("getFields()获取类的public权限的属性成员");
		Field[] field = c.getFields();
		for(Field f : field){
			System.out.println(f);
		}
		System.out.println("getFields()获取类的所有的属性成员");
		Field[] field1 = c.getDeclaredFields();
		for(Field f : field1){
			System.out.println(f);
		}
		System.out.println("获取对象的单个属性");
		Class cc = Class.forName("ReflectDemo.Student");//通过类名得到字节码文件对象
		Constructor newconstructor = cc.getConstructor();//通过class文件对象获取构对象的造器
		Object object = newconstructor.newInstance();//通过构造器对象获取类的对象
		System.out.println("设置之前类对象的输出");
		System.out.println(object);
		Field singlefield = cc.getDeclaredField("address");//cc.getMethod()  cc.getConstructor()
		Field singlefield1 = cc.getDeclaredField("name"); 
		//name是类对象的属性成员，通过字节码文件对象得到name属性对应的Field对象
		//(通过类全称名得到该类的class文件对象),通过该Field对象singlefield1的set()方法
		//将得到的object对象上该Field对象代表的字段设置为新的值,下面就是将singlefield代表
		//的字段的值设置为"西安电子科技大学"，将singlefield1代表的字段的值设置为"zhangsan"
		//对于私有属性不但要用getDeclaredField()方法获取，还要用setAccessible()方法获取访问权限
		Field singlefield2 = cc.getField("age");  //age是public权限，因此不用getDeclaredField也可
		singlefield.setAccessible(true);
		System.out.println("设置之后类对象的输出");
		singlefield.set(object, "西安电子科技大学");
		System.out.println(object);
		singlefield1.setAccessible(true);
		singlefield1.set(object, "zhangsan");
		System.out.println(object);
		singlefield2.set(object, 22);
		//将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
		System.out.println(object);
		System.out.println("获取类的成员方法----------------");
		System.out.println("获取类所有的public权限的成员方法,包括父类的方法");
		Class mclass = Class.forName("ReflectDemo.Student");
		Object oo = mclass.newInstance();
		Method[] method = mclass.getMethods();
		for(Method m : method){
			System.out.println(m);
		}
		System.out.println("获取类所有的成员方法，只包含自己的方法");
		Method[] mpethod = mclass.getDeclaredMethods();//获取类所有的成员方法，只包含自己的方法
		for(Method m : mpethod){
			System.out.println(m);
		}
		System.out.println("获取类指定的成员方法");
		Method mm = mclass.getMethod("show",String.class, int.class);
		//有两个参数，第一个是方法名，第二个是方法参数对应的class对象，对于构造方法，不需要传递方法名
		mm.invoke(oo, "张萌",22); 
		Method mm2 = mclass.getDeclaredMethod("show_1",String.class, int.class,String.class);
		mm2.setAccessible(true);
		mm2.invoke(oo, "张萌",22,"西安电子科技大学");
		//一般是先创建对象，对象调用方法，现在是类路径得到class文件对象，通过该字节码对象获取对象的指定的方法
		//也就是Method对象，通过对象调用invoke()方法，该方法传进来的是类对象和实际的参数，这就是反射
		//实际是oo对象调用mm2对应的方法
		//invoke()的返回值是Object类型,第一个参数是类对象,第二个是Method对象代表方法的实际参数
		// 对带有指定参数的指定对象调用由此 Method 对象表示的基础方法。
		//对于私有方法不但要用getDeclaredMethod()方法获取，还要用setAccessible()方法获取访问权限
		//若要调用方法，就用类方法对应的Method对象去调用invoke()方法
		
		Method mm3 = mclass.getMethod("print", String.class);
		Object result = mm3.invoke(oo, "print方法的输出，该方法有返回值");
		System.out.println(result); //输出invoke()方法的返回值
		
	}
}
