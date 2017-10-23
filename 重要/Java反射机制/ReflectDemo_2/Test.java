package ReflectDemo_2;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;
/* Java反射机制运用实例
 * 新建一个class.txt配置文件，里面是键值对，类的全名，方法名
 * 通过Properties类将其加载到代码中
 * 根据加载的类名以及方法名，创建类的class文件对象，通过class文件对象，获取类的构造器对象
 * 通过构造器对象，创建类的相应地实例，通过class文件对象获取类的Method对象，或者获取属性成员Field
 * 当代码需要进行修改的时候，直接修改配置文件中的值就行了
 * */
public class Test {
	public static void main(String[] args) throws Exception{
		Properties property = new Properties();
		FileReader fr = new FileReader("class.txt");//读取流文件
		property.load(fr); //加载流文件
		String classname = property.getProperty("classname"); //根据键获得值
		String methodname = property.getProperty("methodname");
		
		Class c = Class.forName(classname);
		Constructor cons = c.getConstructor();
		Object obj = cons.newInstance();
		Method mm = c.getMethod("show");
		mm.invoke(obj);		
	}
}
