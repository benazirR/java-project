import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PetRegistry {
    private static List<Animal> animals = new ArrayList<>();
    private static int totalAnimals = 0;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                displayMenu();
                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                    continue;
                }

                switch (choice) {
                    case 1:
                        addAnimal(scanner);
                        break;
                    case 2:
                        listAnimalCommands(scanner);
                        break;
                    case 3:
                        trainAnimal(scanner);
                        break;
                    case 4:
                        listAnimalsByBirthDate();
                        break;
                    case 5:
                        System.out.println("Total animals: " + totalAnimals);
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

            System.out.println("Goodbye!");
        }
    }

    private static void displayMenu() {
        System.out.println("\nPet Registry Menu:");
        System.out.println("1. Add new animal");
        System.out.println("2. List animal commands");
        System.out.println("3. Train animal");
        System.out.println("4. List animals by birth date");
        System.out.println("5. Get total animals");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addAnimal(Scanner scanner) {
        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();
        System.out.print("Enter animal birth date (yyyy-MM-dd): ");
        Date birthDate = parseDate(scanner.nextLine());
        System.out.print("Enter animal type (pet/pack_animal): ");
        String type = scanner.nextLine();

        Animal animal;
        if (type.equalsIgnoreCase("pet")) {
            System.out.print("Enter pet type (dog/cat/hamster): ");
            String petType = scanner.nextLine();
            animal = createPet(name, birthDate, petType, scanner); // Передаем существующий scanner
        } else if (type.equalsIgnoreCase("pack_animal")) {
            System.out.print("Enter pack animal type (horse/camel/donkey): ");
            String packType = scanner.nextLine();
            animal = createPackAnimal(name, birthDate, packType, scanner); // Передаем существующий scanner
        } else {
            System.out.println("Invalid animal type. Animal not added.");
            return;
        }

        if (animal != null) {
            animals.add(animal);
            totalAnimals++;
            System.out.println("Animal added successfully.");
        } else {
            System.out.println("Failed to create animal. Please check your input.");
        }
    }

    private static void listAnimalCommands(Scanner scanner) {
        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();
        Animal animal = findAnimalByName(name);
        if (animal != null) {
            System.out.println("Commands for " + animal.name + ":");
            for (String command : animal.getCommands()) {
                System.out.println("- " + command);
            }
        } else {
            System.out.println("Animal not found.");
        }
    }

    private static void trainAnimal(Scanner scanner) {
        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();
        Animal animal = findAnimalByName(name);
        if (animal != null) {
            System.out.print("Enter new command: ");
            String command = scanner.nextLine();
            animal.addCommand(command);
            System.out.println("Command added successfully.");
        } else {
            System.out.println("Animal not found.");
        }
    }

    private static void listAnimalsByBirthDate() {
        animals.sort((a, b) -> a.birthDate.compareTo(b.birthDate));
        System.out.println("Animals sorted by birth date:");
        for (Animal animal : animals) {
            System.out.println(animal.name + " - " + animal.birthDate);
        }
    }

    private static Animal findAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.name.equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }

    private static Pet createPet(String name, Date birthDate, String petType, Scanner scanner) {
        switch (petType.toLowerCase()) {
            case "dog":
                System.out.print("Enter dog's breed: ");
                String breed = scanner.nextLine();
                return new Dog(name, birthDate, breed);
            case "cat":
                System.out.print("Enter cat's color: ");
                String color = scanner.nextLine();
                return new Cat(name, birthDate, color);
            case "hamster":
                System.out.print("Enter hamster's size(big/small/medium): ");
                String size = scanner.nextLine();
                return new Hamster(name, birthDate, size);
            default:
                return null;
        }
    }

    private static PackAnimal createPackAnimal(String name, Date birthDate, String packType, Scanner scanner) {
        switch (packType.toLowerCase()) {
            case "horse":
                System.out.print("Enter horse's breed: ");
                String breed = scanner.nextLine();
                return new Horse(name, birthDate, breed);
            case "camel":
                System.out.print("Enter camel's humps: ");
                int humps = Integer.parseInt(scanner.nextLine());
                return new Camel(name, birthDate, humps);
            case "donkey":
                System.out.print("Enter donkey's color: ");
                String color = scanner.nextLine();
                return new Donkey(name, birthDate, color);
            default:
                return null;
        }
    }

    private static Date parseDate(String dateString) {
        String[] parts = dateString.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        return new Date(year - 1900, month - 1, day);
    }
}