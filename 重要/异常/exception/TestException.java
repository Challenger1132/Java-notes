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
            /* ��try catch ��û���쳣�׳���ʱ��
             * û��return��䷵�ص���"try"
             * �˴���return��䷵�ص���"finally"
             * */
        }
    }
    public static void main(String[] args) {
    	String str = TestException.test();
        System.out.print(str);
    }
}