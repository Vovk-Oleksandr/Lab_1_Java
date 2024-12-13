import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // запитуємо текст у користувача
        System.out.println("введіть текст:");
        StringBuilder text = new StringBuilder(scanner.nextLine());

        while (true) {
            // вибір операції
            System.out.println("\nвиберіть операцію:");
            System.out.println("1. пошук слова");
            System.out.println("2. заміна слова");
            System.out.println("3. підрахунок кількості слів");
            System.out.println("4. вихід");
            int choice = scanner.nextInt();
            scanner.nextLine(); // очищаємо буфер

            switch (choice) {
                case 1 -> {
                    // функціональний інтерфейс для пошуку слова
                    Predicate<String> findWord = word -> text.toString().contains(word);
                    System.out.println("введіть слово для пошуку:");
                    String word = scanner.nextLine();
                    System.out.println(findWord.test(word) ? "слово знайдено" : "слово не знайдено");
                }
                case 2 -> {
                    // функціональний інтерфейс для заміни слова
                    Function<String[], String> replaceWord = input -> {
                        String newText = text.toString().replace(input[0], input[1]);
                        return newText;
                    };
                    System.out.println("введіть слово для заміни:");
                    String oldWord = scanner.nextLine();
                    System.out.println("введіть нове слово:");
                    String newWord = scanner.nextLine();
                    text.replace(0, text.length(), replaceWord.apply(new String[]{oldWord, newWord}));
                    System.out.println("оновлений текст: " + text);
                }
                case 3 -> {
                    // функціональний інтерфейс для підрахунку кількості слів
                    Function<String, Integer> wordCount = input -> input.split("\\s+").length;
                    System.out.println("кількість слів у тексті: " + wordCount.apply(text.toString()));
                }
                case 4 -> {
                    System.out.println("вихід...");
                    return;
                }
                default -> System.out.println("некоректний вибір, спробуйте ще раз.");
            }
        }
    }
}
