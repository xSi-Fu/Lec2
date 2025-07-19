import java.util.*;

class Car implements Comparable<Car> {
    private String vin;
    private String model;
    private String manufacturer;
    private int year;
    private int mileage;
    private double price;

    public Car(String vin, String model, String manufacturer, int year, int mileage, double price) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(vin, car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.year, this.year);
    }
    public String getVin() { return vin; }
    public String getModel() { return model; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public double getPrice() { return price; }
}
public class Task3 {
    public static void main(String[] args) {
        Set<Car> carSet = new HashSet<>();

        Car car1 = new Car("VIN1", "Camry", "Toyota", 2020, 30000, 25000);
        Car car2 = new Car("VIN2", "X5", "BMW", 2021, 20000, 60000);
        Car car3 = new Car("VIN1", "Model S", "Tesla", 2022, 10000, 80000);

        carSet.add(car1);
        carSet.add(car2);
        carSet.add(car3);
        System.out.println("Количество машин в Set: " + carSet.size());
    }
}