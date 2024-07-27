import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Step 1: Create the Product Class
class Pizza {
    private String size;
    private String crustType;
    private List<String> toppings;

    // Private constructor
    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.crustType = builder.crustType;
        this.toppings = builder.toppings;
    }

    @Override
    public String toString() {
        return "Pizza [Size=" + size + ", CrustType=" + crustType + ", Toppings=" + String.join(", ", toppings) + "]";
    }

    // Step 2: Create the Builder Class
    public static class PizzaBuilder {
        private String size;
        private String crustType;
        private List<String> toppings = new ArrayList<>();

        public PizzaBuilder setSize(String size) {
            this.size = size;
            return this;
        }

        public PizzaBuilder setCrustType(String crustType) {
            this.crustType = crustType;
            return this;
        }

        public PizzaBuilder addTopping(String topping) {
            this.toppings.add(topping);
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}

// Example Usage
public class Builder {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();

        // Get pizza size
        System.out.print("Enter pizza size (e.g., Small, Medium, Large): ");
        builder.setSize(scanner.nextLine());

        // Get crust type
        System.out.print("Enter crust type (e.g., Thin, Thick): ");
        builder.setCrustType(scanner.nextLine());

        // Get toppings using recursion
        collectToppings(builder);

        // Build the Pizza object
        Pizza pizza = builder.build();

        // Display the built Pizza
        System.out.println("Your Pizza Configuration:");
        System.out.println(pizza);

        // Close the scanner
        scanner.close();
    }

    private static void collectToppings(Pizza.PizzaBuilder builder) {
        System.out.print("Enter a topping (or type 'done' to finish): ");
        String topping = scanner.nextLine();

        if (!topping.equalsIgnoreCase("done")) {
            builder.addTopping(topping);
            // Recursive call to collect more toppings
            collectToppings(builder);
        }
    }
}
