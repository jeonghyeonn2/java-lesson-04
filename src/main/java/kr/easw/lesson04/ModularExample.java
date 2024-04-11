package kr.easw.lesson04;

/**
 * 해당 클래스는 Vehicle 클래스를 상속해, 원하는 값을 넣어 주행에 성공하는것을 목표로 하고 있습니다.
 * <p>
 * 해당 문제에서는 Vehicle 클래스를 상속하는 새 클래스를 선언하는것과 getVehicle 클래스를 수정하는것만이 허용됩니다.
 * <p>
 * 이 문제는 다음의 조건을 따라야 합니다 :
 * - 자신만의 새 클래스를 생성합니다. 새 클래스는 Vehicle을 상속받아야 합니다.
 * - 구현된 Vehicle을 상속한 클래스를 getVehicle 메서드가 반환하도록 수정해야 합니다.
 * - (선택) 수행률이 100% 이상이여야 합니다. 수행률은 최대 틱까지 진행이 되었는지의 척도이며, 연료가 부족할 경우 틱이 중단됩니다.
 * - (선택) 최대한 높은 점수를 받아야 합니다.
 * <p>
 * 문제가 너무 어려운 경우, 기본 조건만 충족해도 괜찮습니다.
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
        System.out.println("주행이 종료되었습니다!");
        System.out.println("수행률 : " + percentage + "%");
        System.out.println("총 이동거리: " + totalEnergy);
        System.out.println("남은 연료: " + leftFuel);
        System.out.println("최종 점수: " + calculateScore(tickUsed, totalEnergy, leftFuel));
    }

    private static int calculateScore(int totalTick, int totalEnergy, int leftFuel) {
        double fuelUsage = 2.0 - ((double) leftFuel / (double) INITIAL_FUEL);
        double tickUsage = 1.5 - ((double) totalTick / (double) MAX_TICK);
        return (int) (fuelUsage * tickUsage * totalEnergy);
    }

    /**
     * 해당 메서드는 다음과 같은 역할을 가져야 합니다 :
     * <p>
     * 상속하여 구현한 Vehicle 객체를 반환해야 합니다.
     */
    public static Vehicle getVehicle() {
        throw new RuntimeException("이 코드 라인을 지우고, 이곳에서 작성하십시오.");
    }

    // 해당 클래스를 상속하여 구현하여야 합니다.
    static abstract class Vehicle {
        // Energy는 사용할 에너지를 뜻합니다.
        // CoalEnergy, HumanEnergy, SunlightEnergy 3개가 사용 가능합니다.
        public abstract Energy getEnergy();

        // VehicleType은 탈것의 타입입니다.
        // 타입에 따라 틱당 소모 비용이 다릅니다.
        public abstract VehicleType getType();

        // 각 틱이 실행되기 전에 실행됩니다.
        // 이 메서드를 통해 조금 더 나은 결과를 도출할 수 있습니다.
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