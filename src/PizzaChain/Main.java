package PizzaChain;

import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args){

        Chain pizzaHut = new Chain("Pizza Hut");
        pizzaHut.addPizza("Pizza 1", "Large");
        pizzaHut.getPizza("Pizza 1").addTopping("Sausage");
        pizzaHut.getPizza("Pizza 1").addTopping("Peppers");
        pizzaHut.getPizza("Pizza 1").addTopping("Ham");
        pizzaHut.addPizza("Pizza 2", "Small");
        pizzaHut.getPizza("Pizza 2").addTopping("Beef");
        pizzaHut.getPizza("Pizza 2").addTopping("Bacon");

        Chain littleCaesars = new Chain("Little Caesars");
        littleCaesars.addPizza("Pizza 1", "Medium");
        littleCaesars.getPizza("Pizza 1").addTopping("Olives");
        littleCaesars.getPizza("Pizza 1").addTopping("Spinach");
        littleCaesars.getPizza("Pizza 1").addTopping("Onions");
        littleCaesars.getPizza("Pizza 1").addTopping("Bacon");
        littleCaesars.getPizza("Pizza 1").addTopping("Pepperoni");
        littleCaesars.getPizza("Pizza 1").addTopping("Ham");
        littleCaesars.getPizza("Pizza 1").addTopping("Pesto");
        littleCaesars.getPizza("Pizza 1").addTopping("Peppers");
        littleCaesars.addPizza("Pizza 2", "Small");
        littleCaesars.getPizza("Pizza 2").addTopping("Pepperoni");
        littleCaesars.getPizza("Pizza 2").addTopping("Ham");
        littleCaesars.getPizza("Pizza 2").addTopping("Sausage");
        littleCaesars.getPizza("Pizza 2").addTopping("Bacon");
        littleCaesars.getPizza("Pizza 2").addTopping("Chicken");
        littleCaesars.getPizza("Pizza 2").addTopping("ExtraCheese");

        Chain dominos = new Chain("Dominos");
        dominos.addPizza("Pizza 1", "Small");
        dominos.getPizza("Pizza 1").addTopping("Pepperoni");
        dominos.addPizza("Pizza 2", "Large");
        dominos.getPizza("Pizza 2").addTopping("Pineapple");
        dominos.getPizza("Pizza 2").addTopping("Ham");
        dominos.getPizza("Pizza 2").addTopping("Spinach");





        pizzaHut.eat();
        littleCaesars.eat();
        dominos.eat();
    }
}

class Chain {
    private final String chainName;
    private final ArrayList<Pizza> pizzas;

    public Chain(String name){
        this.chainName = name;
        this.pizzas = new ArrayList<>();
    }

    public void addPizza(String pizzaName, String pizzaSize){
        Pizza pizza = new Pizza(pizzaName, pizzaSize);
        pizzas.add(pizza);
    }

    public Pizza getPizza(String pizzaName){
        for(Pizza pizza : pizzas){
            if(Objects.equals(pizzaName, pizza.getPizzaName())){
                return pizza;
            }
        }
        return new Pizza("", "");
    }

    public void eat(){
        System.out.println();
        System.out.println(chainName + ":");

        for(Pizza pizza : pizzas){
            if (pizza != null){
                System.out.println(pizza.getPizzaName());
                System.out.println("Size - " + pizza.getPizzaSize());
                pizza.eat();
            }
        }
    }
}

class Pizza {
    private final String pizzaName;
    private final String pizzaSize;
    private final ArrayList<Toppings>toppings;

    public Pizza(String pizzaName, String pizzaSize){
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
        this.toppings = new ArrayList<>();
    }

    public String getPizzaSize(){
        return this.pizzaSize;
    }

    public String getPizzaName(){
        return this.pizzaName;
    }

    public void addTopping(String toppingName){
        Toppings topping = new Toppings(toppingName);
        toppings.add(topping);
    }

    public void eat() {

        System.out.print("Toppings -> ");
        for(Toppings topping : toppings){
            if (topping != null){
                System.out.print(topping.getToppingName() + " ");
            }
        }
        System.out.println();
    }
}

class Toppings {
    public String toppingName;
    public Toppings(String toppingName) {
        this.toppingName = toppingName;
    }

    public String getToppingName(){
        return toppingName;
    }
}
