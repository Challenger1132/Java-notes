/*
 * final���ξֲ�����
 * final���λ������ͣ�ֵ���ܸı�
 * final�����������ͣ���ֵַ���ܸı䣬����������ָ��Ķ��ڴ��е����ݿ��Ըı�
 * ��final���εĳ�Ա����ֻ�ܸ�ֵһ��(һ����ֵ���㲻���ٴα���ֵ)
 * Ҫô�ڳ�Ա������λ�ø�ֵ��Ҫô�ڹ��췽���н��и�ֵ�������ڹ��������и�ֵ(��final���εĳ�Ա������ʼ��ʱ�����ڹ��췽�����ǰ)
 * */
class A{
	public int num = 1;
	public A(){
		num = 0;  //�ȳ�Ա������ʼ�����ٹ��췽����ʼ�������num��ֵ���޸�
	}
}

class B{
	public final int num = 1;
	public B(){
	//	num = 0; //ERROR  num��final���Σ���ֵ���ܱ��޸ģ�ֻ�ܸ�ֵһ��
	}
}

class C{
	public final int num;
	public C(){
		num = 0;  //Ҫô�ڳ�Ա������λ�ø�ֵ��Ҫô�ڹ��췽�����֮ǰ���и�ֵ
	}
}
class D{
	public final int num;
	
	{ //��������
		num = 1;  //��Ϊ�����߹������飬һ����ֵ���㲻���ٴθ�ֵ
	}
	
	public D(){
		//num = 0; //ERROR
	}
}
public class TestFinal {
	public static void main(String[] args) {
		A aa = new A();
		System.out.println(aa.num);

		B bb = new B();
		System.out.println(bb.num);
		
		C cc = new C();
		System.out.println(cc.num);
		
		D dd = new D();
		System.out.println(dd.num);
	}
}
