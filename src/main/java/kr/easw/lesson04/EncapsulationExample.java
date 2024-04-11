package kr.easw.lesson04;
/**
 * �ش� Ŭ������ Car�� ��ӹ޾� ���� ���� Ȯ���ϴ� �ڵ带 ���� �ֽ��ϴ�.
 *
 * �� �ڵ忡���� ���Ϳ� �ƹ��� ���͸��� �������� �ʾ� �� ���� ������ �� �����ϴ�.
 *
 * {@link Car} Ŭ�������� ������ {@link PerformanceManipulation} Ŭ�������� �����ϴ� �� ������ ���ƾ� �մϴ�.
 *
 * �̴� ������ ������ ����� �մϴ� :
 * - ���� ���񺸴� ���� ���� �����Ϸ� �� ���, ���� ��� ���� ������ �߻����Ѿ� �մϴ�.
 *
 * ������ throw new �������� �߻���ų �� �ֽ��ϴ�.
 */
public class EncapsulationExample {
    public static void main(String[] args) {
        try {
            Car car = getCar();
            System.out.printf("�� �̸�: %s\n", car.carName);
            System.out.printf("����: %.2fL/h", car.realFuelEfficiency);
            System.out.println("�����Դϴ�.");
        } catch (Exception e) {
            System.out.println("���� ������ Ȯ�εǾ����ϴ�.asd");
            System.out.println("�����Դϴ�.asd");
        }
    }

    private static abstract class Car {
        private final String carName = "Car Prototype";

        private double realFuelEfficiency = 7.5;

        public String getCarName() {
            return carName;
        }

        public double getRealFuelEfficiency() {
            return realFuelEfficiency;
        }

        public void setRealFuelEfficiency(double realFuelEfficiency) throws IllegalArgumentException{
            if (realFuelEfficiency> this.realFuelEfficiency) {
                throw new IllegalArgumentException();
            }
            this.realFuelEfficiency = realFuelEfficiency;
        }
    }

    private static class PerformanceManipulation extends Car {
        {
            setRealFuelEfficiency(15.0);
        }

        @Override
        public String getCarName() {
            return "New Car";
        }

    }


    public static Car getCar() {
        return new PerformanceManipulation();
    }
}

