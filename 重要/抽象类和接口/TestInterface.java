public class TestInterface {
	
	public static void main(String[] args) {
		A aa = new A();
		System.out.println(aa.num);
		System.out.println(Inter.num);
		//num�� public static final�����ε�����num����ͨ���ӿ���������
		// aa.num = 20; //error
		//��Ϊ��A�е�num��Ա��public static final������ֻ�ܽ���һ�θ�ֵ
	}
}
interface Inter{
	int num = 10;
	//�ӿ��е����Գ�Ա�� public static final�����εģ������� public abstract �����ε�
	//��Щ���η�����ȫ�����߲���ʡ�ԣ���˽ӿ������Գ�Ա����ͨ���ӿ���Inter�����ʣ���ֻ�ܽ���һ�θ�ֵ
	//��ʵ���� public static final int num = 10;
}
class A implements Inter{
	//��Aʵ�ֽӿ� Inter
	public A(){
		super(); //���๹����ͨ��super���ʸ��๹�췽��
	}
}