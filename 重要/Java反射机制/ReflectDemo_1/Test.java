package ReflectDemo_1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) throws Exception{
		Class c = Class.forName("ReflectDemo_1.Teacher");
		Constructor con = c.getConstructor();
		Object obj = con.newInstance();
		Method method = c.getMethod("show");
		method.invoke(obj);
		/* 通过类全名获得class文件对象,通过class文件对象得到构造器，通过构造器创建类的实例，
		 * 这里注意获取私有构造器，要用getDeclaredConstructor()，该方法传进几个参数，创建类的实例的时候
		 * 方法的参数要和前者保持一致，也就是说，获取无参构造器，不能通过它创建带参数的实例，同样获得的带参构造器
		 * 不能创建无参的实例，通过class文件对象得到Method对象，通过method对象调用实例方法
		 * 这里通过class文件对象可以获得私有带参数的构造器，私有带参数的method对象
		 * */
		Class c1 = Class.forName("ReflectDemo_1.Student");
		Constructor cons0 = c1.getDeclaredConstructor();
		//getDeclaredConstructor()不用declared会报错，因为类中没有public权限的无参构造器
		//有无参数的私有构造器因此要用getDeclaredConstructor()，此外还要声明访问权限
		cons0.setAccessible(true);
		Object object = cons0.newInstance();
		Method mm0 = c1.getDeclaredMethod("show", String.class,int.class,String.class);
		mm0.setAccessible(true);
		mm0.invoke(object, "张三",33,"西安电子");
		//因为这里得到的是无参数的构造器，创建实例的时候，要和构造器一致
		Constructor cons1 = c1.getConstructor(String.class,int.class);
		Object object1 = cons1.newInstance("仄仄仄仄仄仄",4444);
		Method mm = c1.getDeclaredMethod("show", String.class,int.class,String.class);
		mm.setAccessible(true);
		mm.invoke(object1, "张三",33,"西安电子");
	}
}
