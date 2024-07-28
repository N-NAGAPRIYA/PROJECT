import java.util.Scanner;

public class MissionControl {
    private Rocket rocket = new Rocket();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rocket Launch Simulator");
        handleCommand(scanner);
    }

    private void handleCommand(Scanner scanner) {
        System.out.print("Enter command: ");
        String command = scanner.nextLine();
        
        if (command.equals("start_checks")) {
            rocket.startChecks();
        } else if (command.equals("launch")) {
            if ("Complete".equals(rocket.getChecksStatus())) {
                rocket.launch(1);
            } else {
                System.out.println("Pre-launch checks not complete.");
            }
        } else if (command.startsWith("fast_forward")) {
            String[] parts = command.split(" ");
            if (parts.length == 2) {
                try {
                    int seconds = Integer.parseInt(parts[1]);
                    rocket.fastForward(seconds, 1);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid time format for fast forward.");
                }
            } else {
                System.out.println("Invalid command format for fast forward.");
            }
        } else {
            System.out.println("Unknown command.");
        }

        handleCommand(scanner); // Recursively call handleCommand to continue accepting commands
    }
}
