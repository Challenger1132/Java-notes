package innerClass_1;

public class TestInnerClass_2 {
	public static void main(String[] args) {
		Outer outer = new Outer();
		outer.show();
	}
}
class Outer {
	public int num = 10; //�ⲿ�����Գ�Ա
	
	public void show(){
		
		final int cnt = 10;
		class Inner{ //�ֲ��ڲ���
			
			public void method(){
				System.out.println("Inner Method");
				System.out.println("�ڲ�������ⲿ���Աnum = " + num);
				// �ڲ������ֱ�ӷ����ⲿ���Ա
				System.out.println("�ڲ�����ʱ�final���εľֲ�����cnt = " + cnt);
				//�ֲ��ڲ������ֱ�ӷ����ⲿ���Ա�����ʰ������ڲ���ķ����б�final���εľֲ�����
				//(�þֲ�������ֲ��ڲ���λ��һ��������)��cnt���ɳ���ֵ�����ڶ��ڴ���
			}
		} 
		Inner inner = new Inner();
		inner.method();
	} 	//����������Ͼֲ�����cnt����ʧ,���Ǿֲ�����󲢲����ڶ�����ʧ�����ڼ���ʹ�þֲ�����cnt
		//���cntҪ��final����
}