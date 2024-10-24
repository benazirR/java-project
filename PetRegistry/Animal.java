import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Class Animal
class Animal {
    protected String name;
    protected Date birthDate;
    protected List<String> commands;

    public Animal(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
    }

    public void addCommand(String command) {
        this.commands.add(command);
    }

    public List<String> getCommands() {
        return this.commands;
    }
}

// class Pet
class Pet extends Animal {
    protected String type;

    public Pet(String name, Date birthDate, String type) {
        super(name, birthDate);
        this.type = type;
    }
}

// class PackAnimal
class PackAnimal extends Animal {
    protected String type;

    public PackAnimal(String name, Date birthDate, String type) {
        super(name, birthDate);
        this.type = type;
    }
}

// class Dog
class Dog extends Pet {
    private String breed;

    public Dog(String name, Date birthDate, String breed) {
        super(name, birthDate, "dog");
        this.breed = breed;
    }
}

class Cat extends Pet {
    private String color;

    public Cat(String name, Date birthDate, String color) {
        super(name, birthDate, "cat");
        this.color = color;
    }
}

class Hamster extends Pet {
    private String size;

    public Hamster(String name, Date birthDate, String size) {
        super(name, birthDate, "hamster");
        this.size = size;
    }
}

class Horse extends PackAnimal {
    private String breed;

    public Horse(String name, Date birthDate, String breed) {
        super(name, birthDate, "horse");
        this.breed = breed;
    }
}

class Camel extends PackAnimal {
    private int humps;

    public Camel(String name, Date birthDate, int humps) {
        super(name, birthDate, "camel");
        this.humps = humps;
    }
}

class Donkey extends PackAnimal {
    private String color;

    public Donkey(String name, Date birthDate, String color) {
        super(name, birthDate, "donkey");
        this.color = color;
    }
}