/*
��ľ�̬�����ͷ���Ҳ�����е�ʵ���乲��,������ļ��ض����أ���������������
�����Ϊʲô�ھ�̬������̬��ʼ�������л����ھ�̬�����������ͳ�ʼ��ʱʹ�����Ͳ���
(���Ͳ��������ھ���ʵ����)�ǲ��Ϸ���ԭ��,���ʱ�򲢲�֪�����͵�����ʲô����
һ�������������ʵ��������ʱ������ͬ������ʱ��(class),���������ǵ�ʵ�����Ͳ���
�����������߼��Ͽ��Կ����Ƕ����ͬ�����ͣ�ʵ���϶�����ͬ�Ļ�������
		DemoB<Number> dd = new DemoB<Number>();
		DemoB<Float> dd1 = new DemoB<Float>();
		System.out.println(dd.getClass());  //class Gen.DemoB
		System.out.println(dd1.getClass()); //class Gen.DemoB
		System.out.println(dd.getClass() == dd1.getClass()); //true
�����������߼��Ͽ����Կ����Ƕ����ͬ�����ͣ�ʵ���϶�����ͬ�Ļ�������

ͨ���������
���Ͳ������м̳й�ϵ���������Ͳ�������ķ����಻���м̳й�ϵ�������ǲ�ͬ������
���磬Integer �� Number �����࣬��MyClass<Integer>������MyClass<Number>������,���߶��Ƿ�����
������һЩ�����У���Щ��ϵ����Ȼ����Java�еĶ�̬������Ȼ��Υ����
MyClass<Integer>������MyClass<Number>�ĸ����һ���������ͣ��ɴˣ�����ͨ���Ӧ�˶���
List<? extends Cat> �� List<? extends Animal>��������
G<X> �� G<? extends X>�������ͣ���List<Animal> �� List<? extends Animal>��������
G<?> �� G<? extends Object>��ͬ����List<?> ��List<? extends Object>��ͬ
*/
package Gen;
public class TestGen_5 {
	public static void getDatas(DemoB<Number> parameter){
		System.out.println(parameter.getData());
	}
	//����ͨ�����
	public static void getDatasWithSymbol(DemoB<? extends Number> parameter){
		System.out.println(parameter.getData());
	}
	public static void main(String[] args) {
		DemoB<Number> dd = new DemoB<Number>();
		dd.setData(55);
		DemoB<Float> dd1 = new DemoB<Float>();
		dd1.setData(33.33f);
		System.out.println(dd.getClass());  //class Gen.DemoB
		System.out.println(dd1.getClass()); //class Gen.DemoB
		System.out.println(dd.getClass() == dd1.getClass()); //true
		//�����������߼��Ͽ��Կ����Ƕ����ͬ�����ͣ�ʵ���϶�����ͬ�Ļ�������

		getDatas(dd);
		//getDatas(dd1); //dd1��DemoB<Float>���ͣ����ǲ�����DemoB<Number>������
		//Java�еĶ�̬������Ȼ��Υ����,������Ҫһ�����߼��Ͽ���������ʾͬʱ��DemoB<Number>��DemoB<Float>
		//�ĸ����һ���������ͣ��ɴˣ�����ͨ���Ӧ�˶���
		getDatasWithSymbol(dd);
		getDatasWithSymbol(dd1);
		//��ξͲ��ᱨ����ΪDemoB<? extends Number>�߼�����DemoB<Number>�Լ�DemoB<Float>�ĸ���
		//����Ȼ���϶�̬
	}
}
class DemoB <T>{
	private T data;

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}