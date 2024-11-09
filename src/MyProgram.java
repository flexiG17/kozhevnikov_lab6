import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidCalculationException extends Exception {
    public InvalidCalculationException(String message) {
        super(message);
    }
}

class PhysicalCalculation {
    private final double result;

    public PhysicalCalculation(double result) {
        this.result = result;
    }

    public double calculate() {
        return result;
    }

    @Override
    public String toString() {
        return "Результат: " + calculate();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        };
        if (object == null || getClass() != object.getClass()) {
            return false;
        };
        PhysicalCalculation currentType = (PhysicalCalculation) object;
        return Double.compare(currentType.calculate(), calculate()) == 0;
    }
}

class CentripetalAcceleration extends PhysicalCalculation {
    private final double speed;
    private final double radius;

    public CentripetalAcceleration(double speed, double radius) throws InvalidCalculationException {
        super(Math.pow(speed, 2) / radius);
        if (radius <= 0) {
            throw new InvalidCalculationException("Радиус должен быть положительным числом!");
        };
        this.speed = speed;
        this.radius = radius;
    }

    @Override
    public double calculate() {
        return Math.pow(speed, 2) / radius;
    }

    @Override
    public String toString() {
        return String.format("Центростремительное ускорение a = %.3f м/с^2", calculate());
    }
}

class CircularVelocity extends PhysicalCalculation {
    private final double radius;
    private final double period;

    public CircularVelocity(double radius, double period) throws InvalidCalculationException {
        super((2 * Math.PI * radius) / period);
        if (period <= 0) {
            throw new InvalidCalculationException("Период должен быть положительным числом!");
        };
        this.radius = radius;
        this.period = period;
    }

    @Override
    public double calculate() {
        return (2 * Math.PI * radius) / period;
    }

    @Override
    public String toString() {
        return String.format("Круговая скорость V = %.3f м/с", calculate());
    }
}

public class MyProgram {
    private static void printProgramDescription() {
        System.out.println("Данная программа поможет вам посчитать значение центростремительного ускорения или круговой скорости с точностью до тысячных");
    }

    private static void printProgrammerDescription() {
        System.out.println("Кожевников Арсений\nСтудент магистратуры ИРИТ-РТФ\nАкадем группа: РИМ-140970");
    }

    private static int getNumberOfCalculations(Scanner scanner) {
        System.out.print("Сколько расчетов вы хотите выполнить? ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Введите целое число!");
            scanner.nextLine();
            return getNumberOfCalculations(scanner);
        }
    }

    private static PhysicalCalculation getCalculation(Scanner scanner, int index) {
        System.out.println("Выберите тип расчета для " + (index + 1) + " варианта\n1 - Центростремительное ускорение,\n2 - Круговая скорость");
        try {
            int type = scanner.nextInt();
            double radius;
            switch (type) {
                case 1:
                    System.out.print("Введите скорость V(м/с): ");
                    double speed = scanner.nextDouble();
                    System.out.print("Введите радиус R(м): ");
                    radius = scanner.nextDouble();
                    return new CentripetalAcceleration(speed, radius);
                case 2:
                    System.out.print("Введите радиус R(м): ");
                    radius = scanner.nextDouble();
                    System.out.print("Введите период T(с): ");
                    double period = scanner.nextDouble();
                    return new CircularVelocity(radius, period);
                default:
                    System.out.println("Некорректный выбор типа расчета!");
                    return null;
            }
        } catch (InputMismatchException e) {
            System.out.println("Введите число!");
            scanner.nextLine();
        } catch (InvalidCalculationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void performCalculations() {
        Scanner scanner = new Scanner(System.in);
        int count = getNumberOfCalculations(scanner);
        PhysicalCalculation[] calculations = new PhysicalCalculation[count];

        for (int i = 0; i < count; i++) {
            calculations[i] = getCalculation(scanner, i);
            if (calculations[0] == null)
                break;
        }

        if (calculations[0] != null) {
            System.out.println("\nРезультаты расчетов: ");
            for (PhysicalCalculation calculation : calculations) {
                if (calculation != null) {
                    System.out.println(calculation);
                }
            }
        }

        System.out.println("А нужно было вводить правильные данные!!!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Привет юный физик или просто преподаватель, проверяющий дз!");

        String programText = """
                
                Введите 1, чтобы выполнить расчет
                Введите 2, чтобы получить информацию о программе
                Введите 3, чтобы получить информацию о разработчике
                Введите 4, чтобы выйти из программы.
                """;

        System.out.println(programText);
        int programCode;

        while (true) {
            System.out.print("Для продолжения введите цифру от 1 до 4: ");
            try {
            programCode = scanner.nextInt();
            if (programCode < 1 || programCode > 4) {
                System.out.println("Введённое значение не может быть меньше 1 и больше 4!");
                continue;
            }
            switch (programCode) {
                case 1:
                    performCalculations();
                    break;
                case 2:
                    printProgramDescription();
                    break;
                case 3:
                    printProgrammerDescription();
                    break;
                case 4:
                    System.out.println("До свидания!");
                    return;
            }
            System.out.println(programText);
            } catch (InputMismatchException e) {
                System.out.println("Введите целое число!");
                scanner.nextLine();
            }
        }
    }
}
