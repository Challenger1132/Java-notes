/*
	门和警报的例子：门都有open()和close()两个动作，
	此时我们可以定义通过抽象类和接口来定义这个抽象概念：
	
	abstract class Door {
	    public abstract void open();
	    public abstract void close();
	}
	或者：
	interface Door {
	    public abstract void open();
	    public abstract void close();
	}
	但是现在如果我们需要门具有报警alarm( )的功能，那么该如何实现？
	
	1）将这三个功能都放在抽象类里面，但是这样一来所有继承于这个抽象类的子类都具备了报警功能，但是有的门并不一定具备报警功能；
	
	2）将这三个功能都放在接口里面，需要用到报警功能的类就需要实现这个接口中的open()和close()，
	也许这个类根本就不具备open()和close()这两个功能，比如火灾报警器。
	从这里可以看出， Door的open() 、close()和alarm()根本就属于两个不同范畴内的行为，
	open()和close()属于门本身固有的行为特性，而alarm()属于延伸的附加行为,因此最好的解决办法是单独将报警设计为一个接口，
	包含alarm()行为,Door设计为单独的一个抽象类，包含open和close两种行为,再设计一个报警门继承Door类和实现Alarm接口
*/

abstract class Door { 
	//Door抽象类，里面有两个抽象方法，属于门的固有属性
	public abstract void open();
	public abstract void close();
}

interface Alarm  { 
	//Alarm接口，接口是一扇门可选择的功能，属于们功能的外延功能，可以根据情况进行选择
	public abstract void alarm();
}

class Adoor extends Door implements Alarm{
	//具体的们可以继承Door抽象类，实现Alarm接口
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
	//这个门选择不具有报警功能，并没有实现Alarm接口
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
