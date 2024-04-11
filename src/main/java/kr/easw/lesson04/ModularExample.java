package kr.easw.lesson04;

/**
 * �ش� Ŭ������ Vehicle Ŭ������ �����, ���ϴ� ���� �־� ���࿡ �����ϴ°��� ��ǥ�� �ϰ� �ֽ��ϴ�.
 * <p>
 * �ش� ���������� Vehicle Ŭ������ ����ϴ� �� Ŭ������ �����ϴ°Ͱ� getVehicle Ŭ������ �����ϴ°͸��� ���˴ϴ�.
 * <p>
 * �� ������ ������ ������ ����� �մϴ� :
 * - �ڽŸ��� �� Ŭ������ �����մϴ�. �� Ŭ������ Vehicle�� ��ӹ޾ƾ� �մϴ�.
 * - ������ Vehicle�� ����� Ŭ������ getVehicle �޼��尡 ��ȯ�ϵ��� �����ؾ� �մϴ�.
 * - (����) ������� 100% �̻��̿��� �մϴ�. ������� �ִ� ƽ���� ������ �Ǿ������� ô���̸�, ���ᰡ ������ ��� ƽ�� �ߴܵ˴ϴ�.
 * - (����) �ִ��� ���� ������ �޾ƾ� �մϴ�.
 * <p>
 * ������ �ʹ� ����� ���, �⺻ ���Ǹ� �����ص� �������ϴ�.
 */
public class ModularExample {
    public static int MAX_TICK = 5000;

    public static int INITIAL_FUEL = 500;

    public static void main(String[] args) {
        Vehicle vehicle = getVehicle();
        VehicleType type = vehicle.getType();
        int leftFuel = INITIAL_FUEL;
        int leftTick = 0;
        int totalEnergy = 0;
        int tickUsed = 0;
        for (; tickUsed < MAX_TICK; tickUsed++) {
            if (leftTick-- > 0) {
                continue;
            }
            Energy energy = vehicle.getEnergy();
            leftTick = Math.max(0, type.tickModify() + energy.tickModify());
            vehicle.onTick(tickUsed, leftFuel);
            if (leftFuel < energy.fuelUsage() + type.getCost()) {
                break;
            }
            leftFuel -= energy.fuelUsage() + type.getCost();
            totalEnergy += energy.createEnergy(tickUsed);
        }
        int percentage = (int) (((double) tickUsed) / ((double) (MAX_TICK)) * 100.0);
        System.out.println("������ ����Ǿ����ϴ�!");
        System.out.println("����� : " + percentage + "%");
        System.out.println("�� �̵��Ÿ�: " + totalEnergy);
        System.out.println("���� ����: " + leftFuel);
        System.out.println("���� ����: " + calculateScore(tickUsed, totalEnergy, leftFuel));
    }

    private static int calculateScore(int totalTick, int totalEnergy, int leftFuel) {
        double fuelUsage = 2.0 - ((double) leftFuel / (double) INITIAL_FUEL);
        double tickUsage = 1.5 - ((double) totalTick / (double) MAX_TICK);
        return (int) (fuelUsage * tickUsage * totalEnergy);
    }

    /**
     * �ش� �޼���� ������ ���� ������ ������ �մϴ� :
     * <p>
     * ����Ͽ� ������ Vehicle ��ü�� ��ȯ�ؾ� �մϴ�.
     */
    public static Vehicle getVehicle() {
        throw new RuntimeException("�� �ڵ� ������ �����, �̰����� �ۼ��Ͻʽÿ�.");
    }

    // �ش� Ŭ������ ����Ͽ� �����Ͽ��� �մϴ�.
    static abstract class Vehicle {
        // Energy�� ����� �������� ���մϴ�.
        // CoalEnergy, HumanEnergy, SunlightEnergy 3���� ��� �����մϴ�.
        public abstract Energy getEnergy();

        // VehicleType�� Ż���� Ÿ���Դϴ�.
        // Ÿ�Կ� ���� ƽ�� �Ҹ� ����� �ٸ��ϴ�.
        public abstract VehicleType getType();

        // �� ƽ�� ����Ǳ� ���� ����˴ϴ�.
        // �� �޼��带 ���� ���� �� ���� ����� ������ �� �ֽ��ϴ�.
        public abstract void onTick(int currentTick, int fuel);
    }


    static interface VehicleType {
        int getCost();

        int tickModify();
    }

    static class Bike implements VehicleType {
        @Override
        public int getCost() {
            return 0;
        }

        @Override
        public int tickModify() {
            return -2;
        }
    }

    static class Car implements VehicleType {

        @Override
        public int getCost() {
            return 7;
        }

        @Override
        public int tickModify() {
            return 2;
        }
    }


    interface Energy {
        int createEnergy(int tick);

        int fuelUsage();

        int tickModify();
    }

    static class HumanEnergy implements Energy {

        @Override
        public int createEnergy(int tick) {
            if (tick % 5 == 0)
                return 30;
            return 0;
        }

        @Override
        public int fuelUsage() {
            return 0;
        }

        @Override
        public int tickModify() {
            return 5;
        }
    }

    static class CoalEnergy implements Energy {

        @Override
        public int createEnergy(int tick) {
            return 45;
        }

        @Override
        public int fuelUsage() {
            return 10;
        }

        @Override
        public int tickModify() {
            return 5;
        }
    }

    static class SunlightEnergy implements Energy {

        @Override
        public int createEnergy(int tick) {
            return 5;
        }

        @Override
        public int fuelUsage() {
            return 0;
        }

        @Override
        public int tickModify() {
            return 10;
        }
    }
}