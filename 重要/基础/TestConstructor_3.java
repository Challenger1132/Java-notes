

public class TestConstructor_3 {
/*
һ����ĳ�ʼ������
	�ȳ�Ա�����ĳ�ʼ��
		Ĭ�ϳ�ʼ��
		��ʾ��ʼ��
	�ٹ��췽����ʼ��
�Ӹ���ĳ�ʼ�������Ƿֲ��ʼ��,�Ƚ��и����ʼ����Ȼ����������ʼ��
��Ȼ���������еĹ��췽��Ĭ�϶�����ʸ����пղι��췽��,�����й��췽��Ĭ����һ�� super(),
��������ʾҪ�ȳ�ʼ����������,�ٳ�ʼ����������, super(...)������ָ�����ø����еĹ��췽��������������
��Ա������ʱ��Ҳ����г�ʼ��,��ʼ����ʱ���ǰ��շֲ��ʼ�����е�
 * */
	public static void main(String[] args) {
		B bb = new B();
	}
}
class A{
	public Demo demo = new Demo();
	public A(){
		System.out.println("A");
	}
}
class Demo{
	public Demo(){
		System.out.println("demo");
	}
}
class B extends A{
	public Demo demo = new Demo();
	public B(){
		super();
		//������һ��super();���������ߵ������ʱ��ſ�ʼ���и����Ա������ʼ����������������ʼ��
		//���ǰ��շֲ��ʼ�����̽��е�
		System.out.println("B");
	}
}
