import java.util.*;
import java.util.stream.Collectors;

enum CarType {
    SEDAN, SUV, ELECTRIC, HATCHBACK, COUPE, TRUCK
}
class DealershipCar extends Car {
    private CarType type;
    public DealershipCar(String vin, String model, String manufacturer,
                         int year, int mileage, double price, CarType type) {
        super(vin, model, manufacturer, year, mileage, price);
        this.type = type;
    }
    public CarType getType() { return type; }
}
class CarDealership {
    private Set<DealershipCar> cars = new HashSet<>();

    public boolean addCar(DealershipCar car) {
        return cars.add(car);
    }

    public List<DealershipCar> findByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    public double getAveragePriceByType(CarType type) {
        return cars.stream()
                .filter(c -> c.getType() == type)
                .mapToDouble(DealershipCar::getPrice)
                .average()
                .orElse(0);
    }

    public List<DealershipCar> getCarsSortedByYear() {
        return cars.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public Map<CarType, Long> getTypeStatistics() {
        return cars.stream()
                .collect(Collectors.groupingBy(DealershipCar::getType, Collectors.counting()));
    }
    public String getOldestNewestInfo() {
        Optional<DealershipCar> oldest = cars.stream().min(Comparator.comparingInt(DealershipCar::getYear));
        Optional<DealershipCar> newest = cars.stream().max(Comparator.comparingInt(DealershipCar::getYear));

        return String.format("Самая старая: %d год, самая новая: %d год",
                oldest.map(DealershipCar::getYear).orElse(0),
                newest.map(DealershipCar::getYear).orElse(0));
    }
}
public class Task5 {
    public static void main(String[] args) {
        CarDealership dealership = new CarDealership();
        Scanner scanner = new Scanner(System.in);
        dealership.addCar(new DealershipCar("VIN1", "Camry", "Toyota", 2020, 30000, 25000, CarType.SEDAN));
        dealership.addCar(new DealershipCar("VIN2", "RAV4", "Toyota", 2021, 20000, 35000, CarType.SUV));
        dealership.addCar(new DealershipCar("VIN3", "Model S", "Tesla", 2022, 10000, 80000, CarType.ELECTRIC));

        while (true) {
            System.out.println("\nМеню автоцентра:");
            System.out.println("1. Добавить машину");
            System.out.println("2. Найти машины по производителю");
            System.out.println("3. Средняя цена по типу");
            System.out.println("4. Список машин по году выпуска");
            System.out.println("5. Статистика");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Введите данные машины:");
                    System.out.print("VIN: ");
                    String vin = scanner.nextLine();
                    System.out.print("Модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Производитель: ");
                    String manufacturer = scanner.nextLine();
                    System.out.print("Год выпуска: ");
                    int year = scanner.nextInt();
                    System.out.print("Пробег: ");
                    int mileage = scanner.nextInt();
                    System.out.print("Цена: ");
                    double price = scanner.nextDouble();
                    System.out.print("Тип (0-SEDAN, 1-SUV, 2-ELECTRIC, 3-HATCHBACK, 4-COUPE, 5-TRUCK): ");
                    int typeOrdinal = scanner.nextInt();
                    CarType type = CarType.values()[typeOrdinal];

                    boolean added = dealership.addCar(
                            new DealershipCar(vin, model, manufacturer, year, mileage, price, type));
                    System.out.println(added ? "Машина добавлена" : "Машина с таким VIN уже существует");
                    break;

                case 2:
                    System.out.print("Введите производителя: ");
                    String maker = scanner.nextLine();
                    List<DealershipCar> makerCars = dealership.findByManufacturer(maker);
                    makerCars.forEach(c -> System.out.println(c.getModel() + " " + c.getYear()));
                    break;

                case 3:
                    System.out.print("Введите тип (0-SEDAN, 1-SUV, 2-ELECTRIC, ...): ");
                    type = CarType.values()[scanner.nextInt()];
                    double avgPrice = dealership.getAveragePriceByType(type);
                    System.out.printf("Средняя цена: %.2f%n", avgPrice);
                    break;

                case 4:
                    List<DealershipCar> sortedByYear = dealership.getCarsSortedByYear();
                    sortedByYear.forEach(c -> System.out.println(c.getYear() + " " + c.getModel()));
                    break;

                case 5:
                    System.out.println("Статистика:");
                    System.out.println("Количество по типам:");
                    dealership.getTypeStatistics().forEach((t, count) ->
                            System.out.println(t + ": " + count));
                    System.out.println(dealership.getOldestNewestInfo());
                    break;

                case 0:
                    System.out.println("Выход из программы");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор");
            }
        }
    }
}