import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        // Main method is the entry point of the program and contains the implementation
        // of the menu and starts the typing test.
        Scanner sc = new Scanner(System.in);

        String text = Text.text;

        System.out.println("Select mode:");
        System.out.println("1. Type full text");
        System.out.println("2. Type in one minute");
        int mode = sc.nextInt();

        TypingTest test = new TypingTest(text, mode);
        test.startTest();
        sc.close();
    }
}