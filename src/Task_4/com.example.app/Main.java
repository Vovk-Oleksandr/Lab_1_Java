package com.example.app;

import com.example.model.Person;
import com.example.service.PersonService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PersonService personService = new PersonService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Додати людину");
            System.out.println("2. Видалити людину за ID");
            System.out.println("3. Знайти людину за ID");
            System.out.println("4. Показати всіх людей");
            System.out.println("5. Вийти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Введіть ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.println("Введіть ім'я:");
                    String name = scanner.nextLine();
                    System.out.println("Введіть вік:");
                    int age = scanner.nextInt();
                    personService.addPerson(new Person(id, name, age));
                    System.out.println("Людину додано!");
                }
                case 2 -> {
                    System.out.println("Введіть ID для видалення:");
                    int id = scanner.nextInt();
                    if (personService.removePersonById(id)) {
                        System.out.println("Людину видалено.");
                    } else {
                        System.out.println("Людину не знайдено.");
                    }
                }
                case 3 -> {
                    System.out.println("Введіть ID для пошуку:");
                    int id = scanner.nextInt();
                    personService.findPersonById(id)
                            .ifPresentOrElse(
                                    person -> System.out.println("Знайдено: " + person),
                                    () -> System.out.println("Людину не знайдено.")
                            );
                }
                case 4 -> {
                    System.out.println("Список людей:");
                    personService.getAllPeople().forEach(System.out::println);
                }
                case 5 -> {
                    System.out.println("Вихід.");
                    return;
                }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }
}
