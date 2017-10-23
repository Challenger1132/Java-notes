package exception;

public class TestException_1 {
    @SuppressWarnings("finally")
    public static final String test() {
        String t = "";
        try {
            t = "try";
            String.valueOf(null); //抛出异常,就会执行catch
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
            /* 在try 抛出异常,就会执行catch
             * 此处没有return语句返回的是"catch"
             * 此处有return语句返回的是"finally"
             * */
        }
    }
    public static void main(String[] args) {
    	String str = TestException_1.test();
        System.out.print(str);
    }
}