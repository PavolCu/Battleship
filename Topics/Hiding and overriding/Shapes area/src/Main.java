
class Shape {
    public double area() {
        return 0;
    }
}

class Triangle extends Shape {
    double height;
    double base;

    public  Triangle() {
        super();
    }

    Triangle(double height, double base) {
        super();
        this.height = height;
        this.base = base;
    }
    // override the method here

    @Override

    public double area ( ) {
        return (height * base) / 2;
    }
}

class Circle extends Shape {
    double radius;

    public Circle() {
        super();
    }

    Circle(double radius){
        super();
        this.radius = radius;
    }
    // override the method here

    @Override

    public double area ( ) {
        return Math.PI * Math.pow(radius, 2);
    }
}

class Square extends Shape {
    double side;

    public Square ( ) {
        super();
    }

    Square(double side) {
        super();
        this.side = side;
    }
    // override the method here

    @Override

public double area ( ) {
        return Math.pow(side, 2);
    }
}

class Rectangle extends Shape {
    double width;
    double height;

    public Rectangle() {
        super();
    }

    Rectangle(double width, double height) {
        super();
        this.width = width;
        this.height = height;
    }
    // override the method here

    @Override

    public double area() {
        return width * height;
    }
}