package Gen;
/* �������еķ����뷺�ͷ���
 * */
public class TestGen_2 {

	public static void main(String[] args) {
		GenDemo<String> demo = new GenDemo<String>();
		demo.show("hello"); //ֻ����String����
		GenDemo<Integer> demo1 = new GenDemo<Integer>();
		demo1.show(5);
		
		GenDemo<Integer> demo2 = new GenDemo<Integer>();
		demo2.method("hello");  //���������������Ͳ����޹�
		demo2.method(5);
		demo2.method(true);
		
		GenDemo<Number> demo3 = new GenDemo<Number>();
		demo3.show(new Integer(5));
		demo3.show(new Float(5.55f));
		demo3.show(new Double(5.55));
		// demo3.show(new Object()); //����new Object()����Number�������Բ��ܴ���show()�����Ĳ���
		//ע�����ﷺ�����Ͳ�����Number������Number�������඼���Դ�����
	}
}
class GenDemo<T>{
	//�������еķ�����������������Ҫ��������Ͳ���һ��
	public void show(T s){
		System.out.println(s);
	}
	
	//���ͷ��������ͷ����뷺����û�б�Ȼ����ϵ�����ͷ������Լ������Ͳ���T���ͷ������е����Ͳ���Tû�й�ϵ
	public <T> void method(T s){
		System.out.println(s);
	}
}