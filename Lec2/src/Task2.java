import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {
        List<String> carModels = Arrays.asList(
                "Toyota Camry", "BMW X5", "Tesla Model S", "Audi A4",
                "Toyota Camry", "Tesla Model 3", "BMW X5", "Honda Civic"
        );

        List<String> uniqueSorted = carModels.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .peek(model -> {
                    if (model.contains("Tesla")) {
                        model = "ELECTRO_CAR";
                    }
                })
                .collect(Collectors.toList());

        System.out.println("Уникальные модели в обратном порядке:");
        uniqueSorted.forEach(System.out::println);
        Set<String> modelSet = new TreeSet<>(Comparator.reverseOrder());
        modelSet.addAll(uniqueSorted);
    }
}