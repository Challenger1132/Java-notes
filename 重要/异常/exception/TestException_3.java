package exception;

public class TestException_3 {
    @SuppressWarnings("finally")
    public static final String test() {
        String t = "";
        try {
            t = "try";
            String.valueOf(null); //抛出异常,就会执行catch
            return t;
        } catch (ArrayIndexOutOfBoundsException e) { 
        	//并不是ArrayIndexOutOfBoundsException所以不会进行捕获
            t = "catch";
            return t;
        } finally {
            t = "finally";
            //return t;
            /* 
             * 不会进入catch语句块，直接进入finally语句块，finally对s赋值之后。
             * try抛出 java.lang.NullPointerException
             * 当return不被注释，会将异常消化掉，输出"finally"
             * */
        }
    }
    public static void main(String[] args) {
    	String str = TestException_3.test();
        System.out.print(str);
    }
}