
/*
���м̳й�ϵ��ϵ����ĳ�ʼ������
		A:�̳���ϵ�����о�̬��Ա��ʼ��(������̬��Ա����,��̬�����,�ȸ���,������)
		B:�����ʼ��(��ͨ��Ա�ĳ�ʼ��---->���췽���ĵ���)
		C:�����ʼ��(��ͨ��Ա�ĳ�ʼ��---->���췽���ĵ���)
		Ҳ���ǣ�
		���ྲ̬��Ա(������̬��Ա����,��̬�����)��ʼ��
		���ྲ̬��Ա(������̬��Ա����,��̬�����)��ʼ��
		������ͨ��Ա��ʼ��(���๹������ִ��)
		���๹�췽������
		������ͨ��Ա��ʼ��(�����๹������ִ��)
		���๹�췽������
ִ�н����
үү�ྲ̬�����
���ྲ̬��Ա����
���ྲ̬�����
���ྲ̬��Ա����
���ྲ̬�����
үү����ͨ��Ա����
үү�๹������
үү�๹�췽��
������ͨ��Ա����
���๹������
���๹�췽��
������ͨ��Ա����
���๹������
���๹�췽��

 * */
public class TestExtendsClassIni {
	public static void main(String[] args) {
		C cc = new C();
	}
}
class MM{ //���ڲ���
	public MM(String str){
		System.out.println(str);
	}
}

class A{
	public MM mm = new MM("үү����ͨ��Ա����");
	public static MM mmstatic = new MM("үү�ྲ̬��Ա����");
	static{
		System.out.println("үү�ྲ̬�����");
	}
	{
		System.out.println("үү�๹������");
	}
	public A(){
		System.out.println("үү�๹�췽��");
	}
}

class B extends A{
	public MM mm = new MM("������ͨ��Ա����");
	public static MM mmstatic = new MM("���ྲ̬��Ա����");
	
	static{
		System.out.println("���ྲ̬�����");
	}
	{
		System.out.println("���๹������");
	}
	public B(){
		System.out.println("���๹�췽��");
	}
}

class C extends B{
	public MM mm = new MM("������ͨ��Ա����");
	public static MM mmstatic = new MM("���ྲ̬��Ա����");
	static{
		System.out.println("���ྲ̬�����");
	}
	{
		System.out.println("���๹������");
	}
	public C(){
		System.out.println("���๹�췽��");
	}
}