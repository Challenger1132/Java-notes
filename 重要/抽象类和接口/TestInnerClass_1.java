package innerClass;

public class TestInnerClass_1 {
	public static void main(String[] args) {
		OuterClass.InnerClass oi = new OuterClass().new InnerClass();
		//һ���������һ������ڲ����Ա
		oi.show();
	}
}
class OuterClass{
	
	public int num = 10; //�ⲿ�����Գ�Ա
	class InnerClass{
		public int num = 20;  //�ڲ������Գ�Ա
		public void show(){  //�ڲ��෽����Ա
			int num = 30;     //�����еľֲ�����
			System.out.println(num);
			System.out.println(this.num);
			System.out.println(OuterClass.this.num);
		}
	}
}