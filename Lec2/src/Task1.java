import java.util.Arrays;
import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
        int[] productionYears = new int[50];
        Random random = new Random();

        for (int i = 0; i < productionYears.length; i++) {
            productionYears[i] = 2000 + random.nextInt(26); // 2000-2025
        }

        System.out.println("Машины выпущенные после 2015 года:");
        Arrays.stream(productionYears)
                .filter(year -> year > 2015)
                .forEach(System.out::println);

        double averageAge = Arrays.stream(productionYears)
                .average()
                .orElse(0);
        System.out.printf("Средний год выпуска: %.1f%n", averageAge);
    }
}