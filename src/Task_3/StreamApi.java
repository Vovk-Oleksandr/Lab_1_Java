import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class StreamApiS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть текст вручну або вкажіть шлях до файлу:");
        String input = scanner.nextLine();

        String text;

        // якщо користувач ввів шлях до файлу, читаємо текст із файлу
        if (Files.exists(Path.of(input))) {
            try {
                text = Files.readString(Path.of(input));
            } catch (IOException e) {
                System.out.println("Не вдалося прочитати файл: " + e.getMessage());
                return;
            }
        } else {
            // якщо це не шлях, використовуємо введений текст
            text = input;
        }

        System.out.println("Введіть літеру для фільтрації слів:");
        String filterLetter = scanner.nextLine().trim().toLowerCase();

        // обробляємо текст через Stream API
        List<String> words = Arrays.stream(text.split("\\s+")) // розділяємо текст на слова
                .map(word -> word.replaceAll("[^a-zA-Zа-яА-Я0-9]", "")) // прибираємо знаки пунктуації
                .filter(word -> !word.isEmpty()) // фільтруємо порожні слова
                .collect(Collectors.toList());

        // фільтруємо слова, що починаються з певної літери
        List<String> filteredWords = words.stream()
                .filter(word -> word.toLowerCase().startsWith(filterLetter))
                .sorted(String.CASE_INSENSITIVE_ORDER) // сортуємо за алфавітом
                .collect(Collectors.toList());

        // підрахунок унікальних слів
        long uniqueWordCount = words.stream()
                .map(String::toLowerCase)
                .distinct()
                .count();

        // виводимо результати
        System.out.println("Всі слова:");
        words.forEach(System.out::println);

        System.out.println("\nВідфільтровані та відсортовані слова:");
        filteredWords.forEach(System.out::println);

        System.out.println("\nКількість унікальних слів: " + uniqueWordCount);

        // приклад роботи з Optional
        System.out.println("\nВведіть ID користувача для пошуку:");
        int userId = scanner.nextInt();

        List<User> users = List.of(
                new User(1, "Олександр"),
                new User(2, "Марія"),
                new User(3, "Іван")
        );

        Optional<User> user = findUserById(users, userId);

        user.ifPresentOrElse(
                u -> System.out.println("Знайдено користувача: " + u),
                () -> System.out.println("Користувача з таким ID не знайдено.")
        );
    }

    // метод для пошуку користувача за ID
    public static Optional<User> findUserById(List<User> users, int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    // клас User
    static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Ім'я: " + name;
        }
    }
}
