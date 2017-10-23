
/* 	����飺��Java�У�ʹ��{}�������Ĵ��뱻��Ϊ����顣
	������λ�ú������Ĳ�ͬ�����Է�Ϊ
		�ֲ������:�ֲ�λ��,�����޶��������������ڡ�
		��������:�����еĳ�Աλ��,��{}�������Ĵ��롣ÿ�ι��췽��ִ��ǰ��������ִ�й������顣
			���ã����԰Ѷ�����췽���еĹ�ͬ����ŵ�һ�𣬶Զ�����г�ʼ����
		��̬�����:�����еĳ�Աλ��,��{}�������Ĵ���,ֻ��������static�����ˡ�
			���ã�һ���Ƕ�����г�ʼ����
		��̬�����,��������,���췽����ִ��˳���� ��̬����� -- �������� -- ���췽��;
		�Ҿ�̬����飺ִֻ��һ��;�������飺ÿ�ε��ù��췽����ִ��
 * */
public class CodeBlockTest {

	public static void main(String[] args) {
		{ //�ֲ������
			int x = 10;
			System.out.println(x);
		}
		//	System.out.println(x); //x cannot be resolved to a variable
		CodeBlock cblock = new CodeBlock();
		System.out.println(cblock.cnt);
		CodeBlock cblock1 = new CodeBlock();
		CodeBlock cblock2 = new CodeBlock();
	}
}
class CodeBlock{
	public static int cnt = 10;
	
	static {  //��̬�����
		cnt = 20;
		System.out.println("��̬�����1");
	}
	
	{  //��������1
		cnt = 30;
		System.out.println("��������1");
	}
	public CodeBlock(){
		cnt = 40;
		System.out.println("���췽��");
	}
	
	{  //��������2
		cnt = 30;
		System.out.println("��������2");
	}
	
	static {  //��̬�����2
		cnt = 20;
		System.out.println("��̬�����2");
	}
}
