package exception;

public class TestException_3 {
    @SuppressWarnings("finally")
    public static final String test() {
        String t = "";
        try {
            t = "try";
            String.valueOf(null); //�׳��쳣,�ͻ�ִ��catch
            return t;
        } catch (ArrayIndexOutOfBoundsException e) { 
        	//������ArrayIndexOutOfBoundsException���Բ�����в���
            t = "catch";
            return t;
        } finally {
            t = "finally";
            //return t;
            /* 
             * �������catch���飬ֱ�ӽ���finally���飬finally��s��ֵ֮��
             * try�׳� java.lang.NullPointerException
             * ��return����ע�ͣ��Ὣ�쳣�����������"finally"
             * */
        }
    }
    public static void main(String[] args) {
    	String str = TestException_3.test();
        System.out.print(str);
    }
}