package ReflectDemo;

public class Student {
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int age;
	private String name;
	private String address;
	String score;
	public Student(){
		
	}
	private Student(String name){
		this.name = name;
	}
	public Student(int age){
		this.age = age;
	}
	Student(String name,int age){  //默认的访问权限
		this.name = name;
		this.age = age;
	}
	public Student(String name,int age,String address){
		this.age = age;
		this.name = name;
		this.address = address;
	}
	
	public void show(String name,int age){
		System.out.println("name = " + name + "," + "age = " + age);
	}
	private void show_1(String name,int age,String address){
		System.out.println("name = " + name + "," + "address = " + address + "age = " + age);
	}
	public String print(String name){
		System.out.println("name = " + name);
		return "hello world";
	}
	public String toString(){
		return "age = " + age + "," + "name = " + name + "," + "address = " + address;
	}

}
