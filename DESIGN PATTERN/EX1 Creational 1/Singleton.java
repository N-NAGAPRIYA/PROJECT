import java.util.Scanner;

// Step 1: Create the Singleton Class
class Settings {
    private static Settings instance;
    private String theme;
    private int volume;

    // Private constructor to prevent instantiation
    private Settings() {}

    // Method to get the single instance of the class
    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    // Methods to set and get theme
    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getTheme() {
        return theme;
    }

    // Methods to set and get volume
    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}

// Example Usage
public class Singleton {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the single instance of Settings
        Settings settings = Settings.getInstance();

        // Input and set theme
        System.out.print("Enter the theme for the application: ");
        String theme = scanner.nextLine();
        settings.setTheme(theme);

        // Input and set volume
        System.out.print("Enter the volume level (0-100): ");
        int volume = scanner.nextInt();
        settings.setVolume(volume);

        // Display the current settings
        System.out.println("Current Settings:");
        System.out.println("Theme: " + settings.getTheme());
        System.out.println("Volume: " + settings.getVolume());

        // Close the scanner
        scanner.close();
    }
}
