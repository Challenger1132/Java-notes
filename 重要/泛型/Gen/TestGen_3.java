package Gen;

import java.util.ArrayList;
import java.util.List;
/*


*/
public class TestGen_3 {
/* �����ഴ�������ʱ��Ҫ������������Ͳ����������Ͳ�����ȷ����ʱ�򣬿���ʹ��ͳ���
 * */
	public static void main(String[] args) {
		DemoA<Number> da = new DemoA<Number>();
		da.show(33);
		
		DemoA<? extends Number> db = new DemoA<Number>();
		//db.show(33); ���÷�������
		DemoA<? extends Number> dc = new DemoA<Float>();
		DemoA<? extends Number> dd = new DemoA<Integer>();
		//DemoA<? extends Number> db = new DemoA<String>(); //����
		
		//������Ҳ�����Ƶ�����
		List<? extends Number> ls = new ArrayList<Number>();
		List<? extends Number> ls1 = new ArrayList<Float>();
		List<? extends Number> ls2 = new ArrayList<Integer>();
		ls.add(null); //����null������Ӷ���
	}
}

class DemoA<T>{
	public void show(T t){
		System.out.println(t);
	}
}
