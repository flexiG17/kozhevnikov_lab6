package MyProgram;

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
