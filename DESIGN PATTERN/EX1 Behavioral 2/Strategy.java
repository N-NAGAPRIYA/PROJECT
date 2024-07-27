import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Step 1: Define the TravelStrategy Interface
interface TravelStrategy {
    void calculateTime(double distance);
}

// Step 2: Create Concrete Strategies
class CarTravelStrategy implements TravelStrategy {
    @Override
    public void calculateTime(double distance) {
        double speed = 60.0; // Average speed in km/h
        double time = distance / speed;
        System.out.printf("Travel time by car: %.2f hours%n", time);
    }
}

class BikeTravelStrategy implements TravelStrategy {
    @Override
    public void calculateTime(double distance) {
        double speed = 20.0; // Average speed in km/h
        double time = distance / speed;
        System.out.printf("Travel time by bike: %.2f hours%n", time);
    }
}

class PublicTransportStrategy implements TravelStrategy {
    @Override
    public void calculateTime(double distance) {
        double speed = 40.0; // Average speed in km/h
        double time = distance / speed;
        System.out.printf("Travel time by public transport: %.2f hours%n", time);
    }
}

// Step 3: Create the TravelContext Class
class TravelPlanner {
    private TravelStrategy travelStrategy;

    public void setTravelStrategy(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public void showTravelTime(double distance) {
        if (travelStrategy != null) {
            travelStrategy.calculateTime(distance);
        } else {
            System.out.println("Travel strategy not set.");
        }
    }
}

// Step 4: Main class to handle user input
public class Strategy {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TravelPlanner travelPlanner = new TravelPlanner();
        Map<String, TravelStrategy> strategyMap = new HashMap<>();
        strategyMap.put("car", new CarTravelStrategy());
        strategyMap.put("bike", new BikeTravelStrategy());
        strategyMap.put("public transport", new PublicTransportStrategy());

        // Display options
        System.out.println("Available travel strategies:");
        for (String key : strategyMap.keySet()) {
            System.out.println("- " + key);
        }

        // Get user choice
        System.out.print("Enter the travel strategy (car, bike, public transport): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        TravelStrategy travelStrategy = strategyMap.get(choice);
        if (travelStrategy == null) {
            System.out.println("Invalid choice. Program will exit.");
            System.exit(1);
        }

        travelPlanner.setTravelStrategy(travelStrategy);

        // Get distance and show travel time
        System.out.print("Enter the distance to travel (in km): ");
        double distance = Double.parseDouble(scanner.nextLine());
        travelPlanner.showTravelTime(distance);

        // Close the scanner
        scanner.close();
    }
}
