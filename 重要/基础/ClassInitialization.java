package xxxxxx;
/* ����һ�����������״η�����ľ�̬���ݳ�Ա��ʱ��ͻ������ĳ�ʼ��
 * ��̬��ʼ��ֻ�������Class�����״μ����ǽ���һ��
���õ�ĳ�����ʱ��(���������������״η��ʸ����еľ�̬���ݳ�Աʱ����ʹû�д�������������)
�ͻ�Ը�����г�ʼ������̬��ʼ��(��̬��Ա�����Լ���̬�����)����ͨ��Ա������ʼ������������ִ��
���ٴδ������������󣬾�̬��ʼ��(��̬��Ա�����Լ���̬�����)�Ͳ���ִ�У�����̬��ʼ��ֻ�����״μ���ʱֻ��ʼ��һ��
��ͨ��Ա�����Լ������������ٴν��г�ʼ����ͨ���״η�����ľ�̬���ݳ�Ա�ķ�ʽֻ���ʼ����ľ�̬��Ա��
�����ͨ��Ա�Լ���������������г�ʼ��
��̬��ʼ������ֻ�ڱ�Ҫʱ���У���̬��ʼ��ֻ�����״μ���ʱֻ��ʼ��һ��
 * */
public class ClassInitialization {

	public static void main(String[] args) {
		System.out.println("inside main()");
//		Cups.cup1.f(99);  
		//����Cups��ľ�̬���ݳ�Աcup1��Ȼ���ٵ���f()����
		//��������ָ��ʼ������ľ�̬���ݳ�Ա�������ͨ��Ա�����Լ���������û�г�ʼ��
	}
	static Cups cups1 = new Cups();
	static Cups cups2 = new Cups();
	// new Cups()��������Cups��ľ�̬��Ա(��̬��Ա��������̬�����)����ͨ��Ա������������
	// ������ClassInitialization���������̬��Ա������Ҫ�Ƚ��г�ʼ�������������Cups��ĳ�ʼ��
	// ��ʹ������new Cups()��Cups���еľ�̬��Աֻ�����һ�γ�ʼ�����ڶ��� new Cups()ʱ��Cups��ľ�̬
	// ��Ա�����ٴν��г�ʼ����������ͨ��Ա�����Լ�������������г�ʼ��
}
class Cup{
	public Cup(int maker){
		System.out.println("Cup" + "(" + maker + ")");
	}
	public void f(int maker){
		System.out.println("f" + "(" + maker + ")");
	}
}
class Cups{
	public static Cup cup1;
	public static Cup cup2;
	public Cup cup = new Cup(11);
	static {
		cup1 = new Cup(1);
		cup2 = new Cup(2);
	}
	public Cups(){
		System.out.println("Cups()����");
	}
}