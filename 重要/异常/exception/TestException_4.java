package exception;

public class TestException_4 {
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
            String.valueOf(null);
            return t;
            /* 
             * 如果finally块中抛出异常，则整个try、catch、finally块中抛出异常
             * */
        }
    }
    public static void main(String[] args) {
    	String str = TestException_4.test();
        System.out.print(str);
    }
}