import java.util.Objects;

class Car {
    private final String makeBy;
    private final String model;
    private final int yearOfProduction;

    public Car(String make, String model, int year) {
        this.makeBy = make;
        this.model = model;
        this.yearOfProduction = year;
    }

    public void carSound() {
        System.out.println("Машина едет");
    }

    @Override
    public String toString() {
        return "\nПараметры машины \n" +
                "производитель: " + makeBy + "\n" +
                "модель: " + model + "\n" +
                "год выпуска: " + yearOfProduction;
    }

    @Override
    public boolean equals(Object secondCar) {
        if (this == secondCar) {
            return true;
        };
        if (secondCar == null || getClass() != secondCar.getClass()) {
            return false;
        };
        Car car = (Car) secondCar;
        return yearOfProduction == car.yearOfProduction &&
                Objects.equals(makeBy, car.makeBy) &&
                Objects.equals(model, car.model);
    }
}

class ElectricCar extends Car {
    private final int batteryCapacity;

    public ElectricCar(String makeBy, String model, int yearOfProduction, int batteryCapacity) {
        super(makeBy, model, yearOfProduction);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void carSound() {
        System.out.println("Едет бесшумно");
    }

    @Override
    public String toString() {
        return "\nЭлектромобиль" +
                super.toString() + "\n" +
                "объем батареи: " + batteryCapacity + "кв/ч";
    }

    @Override
    public boolean equals(Object secondCar) {
        if (!super.equals(secondCar)) {
            return false;
        };
        ElectricCar currentCar = (ElectricCar) secondCar;
        return batteryCapacity == currentCar.batteryCapacity;
    }
}

class GasolineCar extends Car {
    private final double fuelTankCapacity;

    public GasolineCar(String makeBy, String model, int yearOfProduction, int fuelTankCapacity) {
        super(makeBy, model, yearOfProduction);
        this.fuelTankCapacity = fuelTankCapacity;
    }

    @Override
    public void carSound() {
        System.out.println("Едет со звуком двигателя внутреннего сгорания");
    }

    @Override
    public String toString() {
        return "\nБезниновый автомобиль" +
                super.toString() + "\n" +
                "объем бака: " + fuelTankCapacity + " литров";
    }

    @Override
    public boolean equals(Object secondCar) {
        if (!super.equals(secondCar)) return false;
        GasolineCar currentCar = (GasolineCar) secondCar;
        return Double.compare(currentCar.fuelTankCapacity, fuelTankCapacity) == 0;
    }
}

public class SecondProgramVariant {
    public static void main(String[] args) {
        Car[] cars = new Car[3];

        cars[0] = new ElectricCar("Tesla", "Model S", 2022, 100);
        cars[1] = new GasolineCar("Toyota", "Camry", 2021, 60);

        for (Car car : cars) {
            System.out.println(car.toString());
            car.carSound();
        }

        ElectricCar car1 = new ElectricCar("Tesla", "Model S", 2022, 100);
        System.out.println("Tesla Model S = Tesla Model S это - " + car1.equals(cars[0]));
    }
}
