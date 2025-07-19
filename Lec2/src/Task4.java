import java.util.*;
import java.util.stream.Collectors;

public class Task4 {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car("VIN1", "Camry", "Toyota", 2020, 45000, 25000),
                new Car("VIN2", "X5", "BMW", 2021, 20000, 60000),
                new Car("VIN3", "Model S", "Tesla", 2022, 10000, 80000),
                new Car("VIN4", "Civic", "Honda", 2019, 60000, 20000),
                new Car("VIN5", "Accord", "Honda", 2020, 30000, 28000)
        );

        List<Car> lowMileageCars = cars.stream()
                .filter(c -> c.getMileage() < 50000)
                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                .collect(Collectors.toList());

        System.out.println("Машины с пробегом < 50000 км:");
        lowMileageCars.forEach(c -> System.out.println(c.getModel()));

        System.out.println("Топ-3, самые дорогие:");
        cars.stream()
                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                .limit(3)
                .forEach(c -> System.out.println(c.getModel() + ": " + c.getPrice()));

        double avgMileage = cars.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0);
        System.out.printf("Средний пробег: %.2f км%n", avgMileage);

        Map<String, List<Car>> byManufacturer = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));

        System.out.println("Машины по производителям:");
        byManufacturer.forEach((maker, carList) -> {
            System.out.println(maker + ": " + carList.size() + " машин");
        });
    }
}