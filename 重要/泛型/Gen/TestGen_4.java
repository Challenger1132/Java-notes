package Gen;
/*
�������еķ������ڷ��ʿ��Ʒ����棬��������ֵǰ��û�����Ͳ������������Ͳ���
��������Ͳ���Ҫ����һ�£�����List<E>��boolean add(E e);�����������

���ͷ����У�������ͨ������Ҳ�����Ͳ��������Ͳ�����Ҫ�������η����桢����ֵ����ǰ��
<>�п�����һ�����߶�����Ͳ�����������Ͳ���,�Ÿ�����ͬʱҲ���Զ����Ͳ�����ȡֵ��Χ�����޶�
���ͷ����뷺����û�б�Ȼ����ϵ�������Ƿ������Ͳ��������ͷ������Լ������Ͳ������ͷ������е����Ͳ���û�й�ϵ

class A <T>{ //������
	public void func(T t){  //�������еķ����������Ƿ��ͷ���
	//�������η����棬����ֵǰ����û�����Ͳ��� <T>����ʱ���������е�����Ӧ��һ��
	A<Integer> aa = new A<Integer>();
	aa.func(5);  //����func()��������ȥ�Ĳ���ֻ����Integer������������
}
��ϵ������List<E> 	�ýӿ�����ķ��� boolean add(E e);������ <E> boolean add(E e);
�����ʽ��Ҳ���Ƿ���ֵǰ��û�в������ͣ���ô�������Ҫ������ָ������һ��

public <T> void func1(T t){   //���ͷ���
	//������Ƿ��ͷ������ͷ�����û�й�ϵ���������Ƿ������Ͳ���
	//���ͷ����뷺����û�б�Ȼ����ϵ�����ͷ������Լ������Ͳ���T���ͷ������е����Ͳ���Tû�й�ϵ
	//���ͷ����в����������������ͣ�Tֻ��һ�ַ���
	//�÷����ȼ���public <S> void func1(S t) {....} S Tֻ�Ƿ��Ŷ���
}
class B{
	//��ͨ��ķ��ͷ�����Ҫ�ڷ��������η����棬����ֵǰ��������Ͳ�����func()�������Դ����������
	public <T, S> void func(T t, S s){ //���ͷ���
	}
}
public <T, S extends T> T testGenericMethodDefine(T t, S s){}
public <T> void fromArrayToCollection(T[] arr, Collection<T> collec){}
��������������,T���͵�����arr�Լ�Collection�ӿڵ�ʵ��������󣬵��Ǹ�ʵ������������д�ŵ���T�����
�÷���ʹ���˷��ͣ�ֻ��Ҫ�ı������T�Ϳ���ʵ�ָ÷������κ��������͵Ĳ���������˴����������
���ͷ�����ʹ�ò�������һ����Ҫ�������Ͳ�����ֻ��Ҫ����ȥ��ͨ����������������ݴ����Ĳ����Զ�ƥ����Ӧ������
 * */
public class TestGen_4 {
	public static void main(String[] args) {
		Integer[] arr = {2,3,56,32};
		Float[] farr = {34.2f,565.5f,565.00005f,4.0f};
		Number[] narr = {45,67};
		MaxNum demo = new MaxNum();
		//����new��һ��������ܵ��ò�ͬ�����ķ���
		//������������������Ͳ�����Ҫ���ò�ͬ�ķ�������Ҫnew����ͬ����
		System.out.println(demo.getMax(arr));
		System.out.println(demo.getMax(farr));
		System.out.println(demo.getMax(narr));

		A aa = new A();  	//��û��Ϊ��A��ӷ��ͣ�Ҳ����û�����Ͳ������ݸ�A
		aa.show("hello"); //��A�����show()����ȴ���Դ����������ͣ�ʹ�õ��Ƿ��ͷ���
		aa.show(true);
		aa.show(new Object());

		B bb = new B();	//������û��������Ͳ���,�л�ɫ������
		bb.show("hello");
		bb.show(11);
		bb.show(true);
		//��BB����˷��ͣ����ʹ���˷������Ҫ���ò�ͬ�����ͱ���new��ͬ���󣬲��������˻�ɫ������
		//������еĴ��ݵ����Ͳ����ͷ����еĲ�������һ��
		B<String> bb1 = new B<String>();
		bb1.show("hello");
		B<Boolean> bb3 = new B<Boolean>();
		bb3.show(true);
		C<Integer> cc = new C<Integer>();
		//��˵����������Ͳ����ͷ��������Ͳ���û�й�ϵ��������������Integer���Ƿ����в�����������������
		cc.show("hello");  
		cc.show(true);
		cc.show(new Object());
	}
}
class MaxNum{
	public <T extends Number> T getMax(T[] arr){
		//���ͷ���
		//<T extends Number>���������Ͳ������޶���ֻ����Number��������
		//�����ķ���ֵ�� T����
		T max = arr[0];
		for(int i = 0;i < arr.length;i ++){
			max = max.doubleValue() > arr[i].doubleValue() ? max : arr[i];
		}
		return max;
	}
}
class A{  
	//��û����ӷ��Ͳ�������Ҫ�����η��󷵻�ֵǰ������Ͳ���
	//��ͨ����Ҳ������ӷ��ͷ���
	public <T> void show(T t){
		System.out.println(t);
	}
}
class B<T>{
	public void show(T t){  
		//�������еķ����������еĲ���Ҫ��������Ͳ�������һ��
		System.out.println(t);
	}
}
class C<T>{ 
	//���ͷ����뷺����û�б�Ȼ����ϵ�����ͷ������Լ������Ͳ������ͷ������е����Ͳ���û�й�ϵ
	public <T> void show(T t){
		System.out.println(t);
	}
}
