/*
	�ź;��������ӣ��Ŷ���open()��close()����������
	��ʱ���ǿ��Զ���ͨ��������ͽӿ����������������
	
	abstract class Door {
	    public abstract void open();
	    public abstract void close();
	}
	���ߣ�
	interface Door {
	    public abstract void open();
	    public abstract void close();
	}
	�����������������Ҫ�ž��б���alarm( )�Ĺ��ܣ���ô�����ʵ�֣�
	
	1�������������ܶ����ڳ��������棬��������һ�����м̳����������������඼�߱��˱������ܣ������е��Ų���һ���߱��������ܣ�
	
	2�������������ܶ����ڽӿ����棬��Ҫ�õ��������ܵ������Ҫʵ������ӿ��е�open()��close()��
	Ҳ�����������Ͳ��߱�open()��close()���������ܣ�������ֱ�������
	��������Կ����� Door��open() ��close()��alarm()����������������ͬ�����ڵ���Ϊ��
	open()��close()�����ű�����е���Ϊ���ԣ���alarm()��������ĸ�����Ϊ,�����õĽ���취�ǵ������������Ϊһ���ӿڣ�
	����alarm()��Ϊ,Door���Ϊ������һ�������࣬����open��close������Ϊ,�����һ�������ż̳�Door���ʵ��Alarm�ӿ�
*/

abstract class Door { 
	//Door�����࣬�������������󷽷��������ŵĹ�������
	public abstract void open();
	public abstract void close();
}

interface Alarm  { 
	//Alarm�ӿڣ��ӿ���һ���ſ�ѡ��Ĺ��ܣ������ǹ��ܵ����ӹ��ܣ����Ը����������ѡ��
	public abstract void alarm();
}

class Adoor extends Door implements Alarm{
	//������ǿ��Լ̳�Door�����࣬ʵ��Alarm�ӿ�
	public void alarm() {
		System.out.println("Adoor alarm ...");
	}

	public void open() {
		System.out.println("Adoor open ...");
	}

	public void close() {
		System.out.println("Adoor close ...");
	}
}
class Bdoor extends Door{
	//�����ѡ�񲻾��б������ܣ���û��ʵ��Alarm�ӿ�
	public void open() {
		System.out.println("Bdoor open ...");
	}
	
	public void close() {
		System.out.println("Bdoor close ...");
	}
}


public class TestDoor {
	public static void main(String[] args) {
		Adoor adoor = new Adoor();
		adoor.alarm();
		adoor.open();
		adoor.close();
		Bdoor bdoor = new Bdoor();
		bdoor.open();
		bdoor.close();		
	}
}
