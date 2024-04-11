package kr.easw.lesson04;

/**
 * �ش� Ŭ������ {@link TestInterface}�� ��ӹ޾� �� ������ �����ϴ� �ڵ带 ������ �ֽ��ϴ�.
 *
 * �ش� ���������� {@link TestV1}, {@link TestV2}, {@link TestV3} 3���� Ŭ������ ������ �������� ���� �����ϵ��� �ؾ� �մϴ�.
 *
 * �̴� ������ ������ ����� �մϴ� :
 * - TestV1 Ŭ������ �Էµ� ���ڿ��� �״�� ��ȯ�ؾ� �մϴ�.
 * - TestV2 Ŭ������ �Էµ� ���ڿ����� ����ǥ(!)�� ����ǥ(?)�� �ٲ�� �մϴ�.
 * - TestV3 Ŭ������ �Էµ� ���ڿ����� ����ǥ(!)�� ����ǥ(?)�� �ٲٰ�, ����( )�� �����ؾ� �մϴ�.
 */
public class VersioningEncapsulationExample {
    public static String VALUE = "Hello, World!";

    public static String RESULT_SECOND = "Hello, World?";

    public static String RESULT_THIRD = "Hello,World?";

    public static void main(String[] args) {
        TestInterface test = new TestV1();
        if (!test.doAction(VALUE).equals(VALUE)) {
            System.out.println("�����Դϴ�.");
            return;
        }
        test = new TestV2();
        if (!test.doAction(VALUE).equals(RESULT_SECOND)) {
            System.out.println("�����Դϴ�.");
            return;
        }
        test = new TestV3();
        if (!test.doAction(VALUE).equals(RESULT_THIRD)) {
            System.out.println("�����Դϴ�.");
            return;
        }
        System.out.println("�����Դϴ�.asd");
    }

    interface TestInterface {
        String doAction(String type);
    }


    static class TestV1 implements TestInterface {
        @Override
        public String doAction(String type) {
            return type;
        }
    }


    // Split by space
    static class TestV2 implements TestInterface {
        @Override
        public String doAction(String type) {
            return type.replace('!','?');
        }
    }


    // Split by space, and concat
    static class TestV3 implements TestInterface {
        @Override
        public String doAction(String type) {
            return type.replace('!','?').replace(" ","");
        }
    }
}
