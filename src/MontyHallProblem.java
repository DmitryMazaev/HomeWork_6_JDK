import java.util.HashMap;
import java.util.Random;

public class MontyHallProblem {
    final static int QUANITY_OF_DOORS = 3;
    final static int COUNT_OPERATION = 1000;
    private double probability = 0.0;
    private int sum = 0;
    private int countVictoryFirstDoor = 0; //количество побед без изменения двери
    private int countVictoryNewDoor = 0; //количество побед с изменением двери
    private int countVictory = 0; //общее количество побед
    private int firstDoorNumber; //дверь, выбранная участником
    private int falseDoor; //дверь, открытая ведущим, за которой ничего нет
    private int trueDoor; //дверь, за которой есть приз
    private int newDoor; //выбор новой двери после открытия ложной (либо выбор первоначальной)
    HashMap<Integer, Double> montyHallHashMap = new HashMap<>();

    public int chooseDoor () {
        Random random = new Random();
        firstDoorNumber = random.nextInt(1, QUANITY_OF_DOORS+1);
        System.out.print("Выбрана дверь " + firstDoorNumber + ", ");
        return firstDoorNumber;
    }

    public int openeFalseDoor() {
        Random random = new Random();
        falseDoor = random.nextInt(1, QUANITY_OF_DOORS+1);
        if (falseDoor != firstDoorNumber) {
            System.out.print("Открыта дверь " + falseDoor + "(за ней пусто), ");
            return falseDoor;
        }
        else {
            while (falseDoor == firstDoorNumber) {
                falseDoor = random.nextInt(1, QUANITY_OF_DOORS+1);
                if (falseDoor != firstDoorNumber) {
                    System.out.print("Открыта дверь " + falseDoor + "(за ней пусто), ");
                }
            }
            return falseDoor;
        }
    }

    public int newChoose () {
        Random random = new Random();
        newDoor = random.nextInt(1, QUANITY_OF_DOORS+1);
        if (newDoor != falseDoor) {
            System.out.print("Выбрана новая дверь " + newDoor + ", ");
            return falseDoor;
        }
        else {
            while (newDoor == falseDoor) {
                newDoor = random.nextInt(1, QUANITY_OF_DOORS+1);
                if (newDoor != falseDoor) {
                    System.out.print("Выбрана новая дверь " + newDoor + ", ");
                }
            }
            return newDoor;
        }
    }

    public void prize() {
        trueDoor = falseDoor;
        Random random = new Random();
        while (trueDoor == falseDoor) {
            trueDoor = random.nextInt(1, QUANITY_OF_DOORS + 1);
        }
        if (trueDoor == newDoor) {
            System.out.print("Приз за дверью " + trueDoor + " ");
            sum = sum + 1;
            System.out.print("Победа!");
            if (newDoor !=firstDoorNumber) {
                countVictoryNewDoor = countVictoryNewDoor + 1;
                countVictory = countVictory + 1;
            }
            else {
                countVictoryFirstDoor = countVictoryFirstDoor + 1;
                countVictory = countVictory + 1;
            }
        }
        else {
            System.out.print("Приз за дверью " + trueDoor + " ");
            sum = sum + 0;
            System.out.print("Поражение!");
        }
    }

    public void start() {
        for (int i = 1; i <= COUNT_OPERATION; i++) {
            chooseDoor ();
            openeFalseDoor();
            newChoose();
            prize();
            probability = Double.valueOf(sum)/Double.valueOf(i);
            montyHallHashMap.put(i, probability);
            System.out.print(" (Вероятность: " + probability + ")");
            System.out.println();
        }
        System.out.println("Побед с изменением двери: " + countVictoryNewDoor + ", побед без изменения двери: " + countVictoryFirstDoor +
                ", общее количество побед: " + countVictory);
        System.out.println("Вероятность без изменения двери: " + Double.valueOf(countVictoryFirstDoor)/Double.valueOf(countVictory));
        System.out.println("Вероятность с изменением двери: " + Double.valueOf(countVictoryNewDoor)/Double.valueOf(countVictory));
        System.out.println("Итоговая вероятность " + COUNT_OPERATION + " измерений равна " + probability);
    }
}
