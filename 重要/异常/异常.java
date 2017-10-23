Throwable 类是 Java 语言中所有错误或异常的超类
					Throwable
						|
			Error				Exception
									|
				RuntimeException	  编译期问题   
	编译期问题:不是 RuntimeException 的异常必须进行处理的，不处理，编译就不能通过
	运行期问题:RuntimeException 这种问题我们也不处理问题出现是我们的代码不够严谨，需修正代码			
throws和throw、finally
	throws
		用在方法声明后面，跟的是异常类名，可以跟多个异常类名，用逗号隔开，提示调用该方法的类对象，本方法可能会抛出异常
		throws表示出现异常的一种可能性，并不一定会发生这些异常，
		表示本方法内不对异常进行处理，会抛给被调用者，谁调用该方法，谁进行处理
		方法体内对异常进行了捕获处理，就不用throws了
	throw
		用在方法体内，跟的是异常对象名，只能抛出一个异常对象名
		表示抛出异常，由方法体内的语句处理
		throw 则是抛出了异常，执行throw 则一定抛出了某种异常
	finally:被 finally 控制的语句体一定会执行，用于关闭资源文件等，但执行到 finally 之前jvm退出了，finally语句就不能执行了
	
java不可能对所有的情况都考虑到，需要自己定义异常，就必须继承自Exception或者RuntimeException
子类抛出异常异特点:
	A:子类重写父类方法时，子类的方法抛出异常范围不能比父类大，必须抛出相同的异常或父类异常的子类
	B:如果被重写的方法没有异常抛出,那么子类的方法绝对不可以抛出异常，如果子类方法内有异常发生,
	那么子类只能在方法体内进行try，catch捕获处理

Java try--catch 异常捕获处理一点需要注意的地方
	在try语句块里面即使遇到异常，在try--catch语句块外面还可以继续执行
	当try语句块里面遇到异常之后，try语句块里异常语句之后的便不会再继续得到执行
	try语句块抛出了异常，catch语句块才会得到了执行，若没有抛出异常，catch语句块不会得到执行
	当try{}里面的语句出现问题，JVM就会生成一个异常类对象，和catch里面的异常对象
	进行匹配，一旦匹配成功，就会执行catch里面的处理代码

在 try catch finally 语句块中，try中有return语句，且前面没有异常阻止return语句的执行，
就会返回try中对应的值，后面对该值得修改不会影响try中return语句的返回

try中有异常会(阻止了return语句的执行)会执行catch语句块，若有catch中有return语句，且此处没有抛出异常阻止该return语句的执行，
就会返回catch语句中的值，若try、catch中都有异常，且finally没有return语句，就只会抛出异常，若finally中有return语句，try或catch中
的返回语句忽略，finally语句(若没有异常因为要返回到try或者catch)无法返回，就会抛出异常

尽量在try或者catch中使用return语句，通过finally块中达到对try或者catch返回值修改是不可行的
finally块中避免使用return语句，因为finally块中如果使用return语句，会显示的消化掉try、catch块中的异常信息，屏蔽了错误的发生
finally块中避免再次抛出异常，否则整个包含try语句块的方法回抛出异常，并且会消化掉try、catch块中的异常
try catch 有异常，finally没有异常，且有返回，这时候，finally会将异常消化掉，异常被屏蔽
try catch 有异常，finally有异常，就会抛出异常
try catch 没有异常，finally有异常，就会造成无法返回 try catch ，抛出异常

为什么有这么奇葩的返回形式？
在try语句的return块中返回值是t,在try之外对t的只进行了修改，return 返回的引用变量(t是引用类型)并不是try语句外修改后的引用变量t，
而是系统重新定义了一个局部引用t’，这个引用指向了引用t对应的值，也就是try中的返回值t，即使在finally语句中把引用t指向了值finally，
因为return的返回引用已经不是t (而是系统重新定义了一个局部引用t’)所以引用t的对应的值和try语句中的返回值无关了