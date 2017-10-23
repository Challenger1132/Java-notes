package exception;

public class TestException {
    @SuppressWarnings("finally")
    public static final String test() {
        String t = "";
        try {
            t = "try";
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            //return t;
            /* 在try catch 中没有异常抛出的时候
             * 没有return语句返回的是"try"
             * 此处有return语句返回的是"finally"
             * */
        }
    }
    public static void main(String[] args) {
    	String str = TestException.test();
        System.out.print(str);
    }
}