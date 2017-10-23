package exception;

public class TestException_2 {
    @SuppressWarnings("finally")
    public static final String test() {
        String t = "";
        try {
            t = "try";
            String.valueOf(null); //抛出异常,就会执行catch
            return t;
        } catch (Exception e) {
            t = "catch";
            String.valueOf(null); //抛出异常
            return t;
        } finally {
            t = "finally";
            //return t;
            /* 在try 抛出异常,就会执行catch,catch也抛出异常
             * 此处有return语句返回的是"finally"，异常被消化掉
             * 没有return会抛出异常
             * 
             * 由于try语句抛出异常，程序进入catch语句块，catch语句块又抛出一个异常，说明catch语句要退出，
             * 则执行finally语句块，对t进行赋值。没有return会抛出异常,catch语句块里面抛出异常
             * */
        }
    }
    public static void main(String[] args) {
    	String str = TestException_2.test();
        System.out.print(str);
    }
}