import java.util.Scanner;

// Step 1: Define the Component Interface
interface Text {
    String getText();
}

// Step 2: Create Concrete Components
class PlainText implements Text {
    private String content;

    public PlainText(String content) {
        this.content = content;
    }

    @Override
    public String getText() {
        return content;
    }
}

// Step 3: Define the Base Decorator
abstract class TextDecorator implements Text {
    protected Text text;

    public TextDecorator(Text text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text.getText();
    }
}

// Step 4: Create Concrete Decorators
class BoldDecorator extends TextDecorator {
    public BoldDecorator(Text text) {
        super(text);
    }

    @Override
    public String getText() {
        return "**" + text.getText() + "**";
    }
}

class ItalicDecorator extends TextDecorator {
    public ItalicDecorator(Text text) {
        super(text);
    }

    @Override
    public String getText() {
        return "*" + text.getText() + "*";
    }
}

class UnderlineDecorator extends TextDecorator {
    public UnderlineDecorator(Text text) {
        super(text);
    }

    @Override
    public String getText() {
        return "__" + text.getText() + "__";
    }
}

// Example Usage
public class Decorator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for the base text
        System.out.print("Enter the text: ");
        String userInputText = scanner.nextLine();

        // Create the base text object
        Text text = new PlainText(userInputText);

        // Process formatting options
        text = processFormatting(scanner, text, "Do you want to add bold formatting? (yes/no): ", BoldDecorator.class);
        text = processFormatting(scanner, text, "Do you want to add italic formatting? (yes/no): ", ItalicDecorator.class);
        text = processFormatting(scanner, text, "Do you want to add underline formatting? (yes/no): ", UnderlineDecorator.class);

        // Display the final formatted text
        System.out.println("Formatted text: " + text.getText());

        // Close the scanner
        scanner.close();
    }

    // Helper method to process formatting options
    private static Text processFormatting(Scanner scanner, Text text, String prompt, Class<? extends TextDecorator> decoratorClass) {
        System.out.print(prompt);
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            try {
                // Dynamically create an instance of the decorator
                text = decoratorClass.getConstructor(Text.class).newInstance(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return text;
    }
}
