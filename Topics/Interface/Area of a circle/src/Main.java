class Circle {
    protected double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
}
class MeasurableCircle extends Circle implements Measurable {

    public MeasurableCircle(double radius) {
        super(radius);
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// do not change the interface
interface Measurable {
    double area();
}