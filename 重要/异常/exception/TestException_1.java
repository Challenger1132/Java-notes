package exception;

public class TestException_1 {
    @SuppressWarnings("finally")
    public static final String test() {
        String t = "";
        try {
            t = "try";
            String.valueOf(null); //�׳��쳣,�ͻ�ִ��catch
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
            /* ��try �׳��쳣,�ͻ�ִ��catch
             * �˴�û��return��䷵�ص���"catch"
             * �˴���return��䷵�ص���"finally"
             * */
        }
    }
    public static void main(String[] args) {
    	String str = TestException_1.test();
        System.out.print(str);
    }
}