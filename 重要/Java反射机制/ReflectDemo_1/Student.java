package ReflectDemo_1;

public class Student {
	private int age;
	private String name;
	private Student(){
		
	}
	public Student(String name,int age){
		this.name = name;
		this.age = age;
	}
	public void show(){
		System.out.println("student");
	}
	private void show(String name,int age,String address){
		System.out.println(this.name + ":" + this.age + ":" + name + "---" + age + "---" + address);
	}
}
