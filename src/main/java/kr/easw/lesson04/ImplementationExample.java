package kr.easw.lesson04;

public class ImplementationExample {

    public static void main(String[] args) {
        if (onTest(1000).equals("TestValue - 34300")) {
            System.out.println("�����Դϴ�.");
            return;
        }
        System.out.println("�����Դϴ�.");
    }

    private static String onTest(int value) {
        TestClass testClass = new TestClass();
        return testClass.eval(testClass.getString(), value);
    }


    private static class TestClass {
        /**
         * �ش� �޼���� ������ ���� ������ ������ �մϴ� :
         * ���� ���ڿ��� ��ȯ�ؾ� �մϴ�.
         *
         * "TestValue"
         */
        public String getString() {
            return "TestValue";
        }

        /**
         * �ش� �޼���� ������ ���� ������ ������ �մϴ� :
         *
         * �ι�° ���� ���� ������ ��ģ ��, ù��° ���� " - "�� �ٿ��� �մϴ�.
         *
         * �ι�° ���� 2�� ���� ����, 7�� ���ϰ�, 3�� ������ ��, 5�� ������ �մϴ�.
         *
         * �� ��, �� ���� ��ȯ�ؾ� �մϴ�.
         *
         * ���� ���, first�� "TestValue"�̰�, data�� 1000�̿��ٸ�, ���� ���� ��ȯ�Ǿ�� �մϴ� :
         *
         * "TestValue - 34300"
         *
         * @param first �� �տ� ��ġ�� ���ڿ�
         * @param data ����� ����Ǿ�� �� ��
         */
        public String eval(String first, int data) {
            int result= (int)Math.pow((data/2)*7,3)/5;
            return first+ "-"+ result;
        }
    }
}