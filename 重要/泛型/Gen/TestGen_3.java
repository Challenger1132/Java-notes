package Gen;

import java.util.ArrayList;
import java.util.List;
/*


*/
public class TestGen_3 {
/* 泛型类创建对象的时候，要给出具体的类型参数，当类型参数不确定的时候，可以使用统配符
 * */
	public static void main(String[] args) {
		DemoA<Number> da = new DemoA<Number>();
		da.show(33);
		
		DemoA<? extends Number> db = new DemoA<Number>();
		//db.show(33); 调用方法出错
		DemoA<? extends Number> dc = new DemoA<Float>();
		DemoA<? extends Number> dd = new DemoA<Integer>();
		//DemoA<? extends Number> db = new DemoA<String>(); //错误
		
		//集合中也有类似的现象
		List<? extends Number> ls = new ArrayList<Number>();
		List<? extends Number> ls1 = new ArrayList<Float>();
		List<? extends Number> ls2 = new ArrayList<Integer>();
		ls.add(null); //除了null不能添加对象
	}
}

class DemoA<T>{
	public void show(T t){
		System.out.println(t);
	}
}
