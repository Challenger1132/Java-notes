
/* 
ִ�н��
��̬��Ա����
��̬�����
��ͨ��Ա����
��������
��A�Ĺ��췽��

1  û�м̳й�ϵ����ĳ�ʼ������
	�ȳ�Ա�����ĳ�ʼ��
		��̬��Ա��ʼ���;�̬������ʼ��(�Ͷ���λ���й�)
		��ͨ��Ա�����ĳ�ʼ��
	�ٹ��췽����ʼ��
		���������ʼ��(�����������ͨ��Ա������ʼ���Ͷ���λ���й�)
		���췽������
 * */
public class TestSingleClassIni {
	public static void main(String[] args) {
		A aa = new A();
	}
}
class MM{
	public MM(String str){
		System.out.println(str);
	}
}
class A{
	public static MM mm = new MM("��̬��Ա����"); 
	static{
		System.out.println("��̬�����");
	}
	public MM mm1 = new MM("��ͨ��Ա����");
	{
		System.out.println("��������");
	}
	public A(){
		System.out.println("��A�Ĺ��췽��");
	}
}
