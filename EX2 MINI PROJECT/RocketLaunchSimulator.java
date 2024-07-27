import java.util.Scanner;

public class RocketLaunchSimulator {
    public static void main(String[] args) {
        Rocket rocket = new Rocket();
        LaunchSequence launchSequence = new LaunchSequence(rocket);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter command: ");
        String command = scanner.nextLine();
        while (!command.equals("exit")) {
            if (command.equals("start_checks")) {
                launchSequence.startChecks();
            } else if (command.equals("launch")) {
                launchSequence.launch();
            } else if (command.startsWith("fast_forward")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    try {
                        int seconds = Integer.parseInt(parts[1]);
                        launchSequence.fastForward(seconds);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format. Please enter a valid number of seconds.");
                    }
                } else {
                    System.out.println("Invalid command format. Use: fast_forward X");
                }
            } else {
                System.out.println("Unknown command.");
            }
            System.out.print("Enter command: ");
            command = scanner.nextLine();
        }
        System.out.println("Exiting the simulator.");
    }
}
