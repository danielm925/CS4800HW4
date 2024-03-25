package Macronutrients;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main{
    public static void main(String[] args){
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer("Daniel", "No Restriction"));
        customers.add(new Customer("Bella", "Paleo"));
        customers.add(new Customer("Adam", "Nut Allergy"));
        customers.add(new Customer("Angie", "Vegan"));
        customers.add(new Customer("Noah", "Paleo"));
        customers.add(new Customer("Sarah", "No Restriction"));

        for(Customer customer : customers){
            customer.orderMeal();
        }
    }
}

interface Macronutrients{
    String getMacro();
}

class Cheese implements Macronutrients{
    public String getMacro(){
        return "Cheese";
    }
}

class Bread implements Macronutrients{
    public String getMacro(){
        return "Bread";
    }
}

class Lentils implements Macronutrients{
    public String getMacro(){
        return "Lentils";
    }
}

class Pistachio implements Macronutrients{
    public String getMacro(){
        return "Pistachio";
    }
}

class Fish implements Macronutrients{
    public String getMacro(){
        return "Fish";
    }
}

class Chicken implements Macronutrients{
    public String getMacro(){
        return "Chicken";
    }
}

class Beef implements Macronutrients{
    public String getMacro(){
        return "Beef";
    }
}

class Tofu implements Macronutrients{
    public String getMacro(){
        return "Tofu";
    }
}

class Avocado implements Macronutrients{
    public String getMacro(){
        return "Avocado";
    }
}

class SourCream implements Macronutrients{
    public String getMacro(){
        return "Sour Cream";
    }
}

class Tuna implements Macronutrients{
    public String getMacro(){
        return "Tuna";
    }
}

class Peanuts implements Macronutrients{
    public String getMacro(){
        return "Peanuts";
    }
}

interface MacronutrientFactory{
    Macronutrients create();
}

class CarbsFactory implements MacronutrientFactory{
    private static CarbsFactory instance;

    public static CarbsFactory getInstance(){
        if(instance == null){
            instance = new CarbsFactory();
        }
        return instance;
    }

    public Macronutrients create(){
        Random random = new Random();
        List<Macronutrients> options = new ArrayList<>();

        options.add(new Cheese());
        options.add(new Bread());
        options.add(new Lentils());
        options.add(new Pistachio());

        return options.get(random.nextInt(options.size()));
    }
}

class ProteinFactory implements MacronutrientFactory{
    private static ProteinFactory instance;

    public static ProteinFactory getInstance(){
        if(instance == null){
            instance = new ProteinFactory();
        }
        return instance;
    }

    public Macronutrients create(){
        Random random = new Random();
        List<Macronutrients> options = new ArrayList<>();

        options.add(new Fish());
        options.add(new Chicken());
        options.add(new Beef());
        options.add(new Tofu());

        return options.get(random.nextInt(options.size()));
    }
}

class FatsFactory implements MacronutrientFactory{
    private static FatsFactory instance;

    public static FatsFactory getInstance(){
        if(instance == null){
            instance = new FatsFactory();
        }
        return instance;
    }

    public Macronutrients create(){
        Random random = new Random();
        List<Macronutrients> options = new ArrayList<>();

        options.add(new Avocado());
        options.add(new SourCream());
        options.add(new Tuna());
        options.add(new Peanuts());

        return options.get(random.nextInt(options.size()));
    }
}

class MacronutrientAbstractFactory{
    private static MacronutrientAbstractFactory instance;

    public static MacronutrientAbstractFactory getInstance(){
        if(instance == null){
            instance = new MacronutrientAbstractFactory();
        }
        return instance;
    }

    public MacronutrientFactory getFactory(String macronutrientType){
        return switch(macronutrientType){
            case "Carbs" -> CarbsFactory.getInstance();
            case "Protein" -> ProteinFactory.getInstance();
            case "Fats" -> FatsFactory.getInstance();
            default -> throw new IllegalArgumentException("Invalid macronutrient type.");
        };
    }
}

class PaleoFactory implements MacronutrientFactory{
    public Macronutrients create(){
        MacronutrientAbstractFactory factory = MacronutrientAbstractFactory.getInstance();

        MacronutrientFactory carbFactory = factory.getFactory("Carbs");
        MacronutrientFactory proteinFactory = factory.getFactory("Protein");
        MacronutrientFactory fatsFactory = factory.getFactory("Fats");

        Macronutrients carb = carbFactory.create();
        while(!(carb instanceof Pistachio)){
            carb = carbFactory.create();
        }

        Macronutrients protein = proteinFactory.create();
        while(protein instanceof Tofu){
            protein = proteinFactory.create();
        }

        Macronutrients fats = fatsFactory.create();
        while(fats instanceof SourCream){
            fats = fatsFactory.create();
        }

        return carb;
    }
}


class VeganFactory implements MacronutrientFactory{
    public Macronutrients create(){
        MacronutrientAbstractFactory factory = MacronutrientAbstractFactory.getInstance();

        MacronutrientFactory carbFactory = factory.getFactory("Carbs");
        MacronutrientFactory proteinFactory = factory.getFactory("Protein");
        MacronutrientFactory fatsFactory = factory.getFactory("Fats");

        Macronutrients carb = carbFactory.create();
        while((carb instanceof Cheese)){
            carb = carbFactory.create();
        }

        Macronutrients protein = proteinFactory.create();
        while(protein instanceof Fish || protein instanceof Chicken || protein instanceof Beef){
            protein = proteinFactory.create();
        }

        Macronutrients fats = fatsFactory.create();
        while(fats instanceof SourCream || fats instanceof Tuna){
            fats = fatsFactory.create();
        }

        return protein;
    }
}


class NutAllergyFactory implements MacronutrientFactory{
    public Macronutrients create(){
        MacronutrientAbstractFactory factory = MacronutrientAbstractFactory.getInstance();

        MacronutrientFactory carbFactory = factory.getFactory("Carbs");
        MacronutrientFactory fatsFactory = factory.getFactory("Fats");

        Macronutrients carb = carbFactory.create();
        while((carb instanceof Pistachio)){
            carb = carbFactory.create();
        }

        Macronutrients fats = fatsFactory.create();
        while(fats instanceof Peanuts){
            fats = fatsFactory.create();
        }

        return fats;
    }
}


class Customer{
    private final String name;
    private final String diet;

    public Customer(String name, String diet){
        this.name = name;
        this.diet = diet;
    }

    public void orderMeal(){
        MacronutrientAbstractFactory factory = MacronutrientAbstractFactory.getInstance();

        Macronutrients carb;
        Macronutrients protein;
        Macronutrients fats;

        MacronutrientFactory carbFactory = factory.getFactory("Carbs");
        MacronutrientFactory proteinFactory = factory.getFactory("Protein");
        MacronutrientFactory fatsFactory = factory.getFactory("Fats");

        switch(diet){
            case "No Restriction":
                break;
            case "Paleo":
                carbFactory = new PaleoFactory();
                break;
            case "Vegan":
                proteinFactory = new VeganFactory();
                break;
            case "Nut Allergy":
                fatsFactory = new NutAllergyFactory();
                break;
            default:
                throw new IllegalArgumentException("Invalid diet plan.");
        }

        carb = carbFactory.create();
        protein = proteinFactory.create();
        fats = fatsFactory.create();

        System.out.println(name + "'s meal: ");
        System.out.println("Diet plan = " + diet);
        System.out.println("Carbs - " + carb.getMacro());
        System.out.println("Protein - " + protein.getMacro());
        System.out.println("Fats - " + fats.getMacro() + "\n");
    }
}