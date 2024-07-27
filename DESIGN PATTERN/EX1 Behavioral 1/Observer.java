import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Step 1: Define the BookObserver Interface
interface BookObserver {
    void update(String bookTitle);
}

// Step 2: Define the Subject Interface
interface Subject {
    void addObserver(BookObserver observer);
    void removeObserver(BookObserver observer);
    void notifyObservers();
}

// Step 3: Create Concrete Subject
class Library implements Subject {
    private List<BookObserver> observers = new ArrayList<>();
    private String newBookTitle;

    @Override
    public void addObserver(BookObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(BookObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (BookObserver observer : observers) {
            observer.update(newBookTitle);
        }
    }

    public void addNewBook(String bookTitle) {
        this.newBookTitle = bookTitle;
        notifyObservers();
    }
}

// Step 4: Create Concrete Observers
class Member implements BookObserver {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    @Override
    public void update(String bookTitle) {
        System.out.println(name + " has been notified about the new book: " + bookTitle);
    }
}

// Step 5: Main class to handle user input
public class Observer {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create subject
        Library library = new Library();

        // Create observers
        Member member1 = new Member("Priya");
        Member member2 = new Member("Naveen");

        // Add observers to the subject
        library.addObserver(member1);
        library.addObserver(member2);

        // Handle user input
        handleUserInput(library);
    }

    private static void handleUserInput(Library library) {
        System.out.println("Enter new book title or 'exit' to quit:");

        // Use a for loop with a condition that checks user input
        for (String input = scanner.nextLine(); !input.equalsIgnoreCase("exit"); input = scanner.nextLine()) {
            library.addNewBook(input);
            System.out.println("Enter new book title or 'exit' to quit:");
        }

        System.out.println("Exiting...");
    }
}
