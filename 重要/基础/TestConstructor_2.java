

public class TestConstructor_2 {

	public static void main(String[] args) {
		D dd = new D();
		System.out.println("---------------------------");
		D dd1 = new D("hello  world");
	}
}
class C{
	//���︸���޲ι���㲻������..
	public C(String str){
		System.out.println("����dai�ι���");
	}
}

class D extends C{
	public D(){
		//������û���޲ι��죬��ʱӦ����ʽ���ø�������������
		super("hello world");  //���˾�ע�ͻᱨ������ʾ
		//Implicit super constructor C() is undefined.
		// Must explicitly invoke another constructor
		// ����ղι���C()δ���壬������ʽ���ø�������������
		System.out.println("ZI���޲ι���");
	}
	public D(String str){
		this();  //������û���޲ι��죬�����ﲢû����ʽ���ø����������죬��û�б���
		//��Ϊ������this()������������޲ι��죬������޲ι������и���Ĺ��������ã�
		//Ҳ����������ô���ã����๹����һ����һ�����๹����������
		System.out.println("ZI��dai�ι���");
	}
}