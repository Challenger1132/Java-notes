package exception;

public class TestException_2 {
    @SuppressWarnings("finally")
    public static final String test() {
        String t = "";
        try {
            t = "try";
            String.valueOf(null); //�׳��쳣,�ͻ�ִ��catch
            return t;
        } catch (Exception e) {
            t = "catch";
            String.valueOf(null); //�׳��쳣
            return t;
        } finally {
            t = "finally";
            //return t;
            /* ��try �׳��쳣,�ͻ�ִ��catch,catchҲ�׳��쳣
             * �˴���return��䷵�ص���"finally"���쳣��������
             * û��return���׳��쳣
             * 
             * ����try����׳��쳣���������catch���飬catch�������׳�һ���쳣��˵��catch���Ҫ�˳���
             * ��ִ��finally���飬��t���и�ֵ��û��return���׳��쳣,catch���������׳��쳣
             * */
        }
    }
    public static void main(String[] args) {
    	String str = TestException_2.test();
        System.out.print(str);
    }
}