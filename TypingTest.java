// Import the Scanner class to allow user input
import java.util.Scanner;

// Class for the typing test
class TypingTest {
    // String for the text to be typed
    private String text;
    // Integer for the mode of the test (full text or one minute)
    private int mode;
    // Double for the user's typing speed (characters per second)
    private double typingSpeed;
    // Integer for the user's accuracy (percent)
    private int accuracy;

    // Constructor for the TypingTest class
    public TypingTest(String text, int mode) {
        // Set the text to be typed to the passed argument
        this.text = text;
        // Set the mode of the test to the passed argument
        this.mode = mode;
    }

    // Method to start the test
    public void startTest() {
        // If the mode is set to full text, run the typeFullText method
        if (mode == 1) {
            typeFullText();
            // If the mode is set to one minute, run the typeInOneMinute method
        } else {
            typeInOneMinute();
        }
    }

    // Method for typing the full text
    private void typeFullText() {
        // Create a Scanner object to allow user input
        Scanner sc = new Scanner(System.in);
        // Prompt the user to start typing
        System.out.println("Start typing the following text:\n");
        // Record the start time
        long start = System.currentTimeMillis();
        // Display the text to be typed
        System.out.println(text + "\n");
        // String to store the user's input
        String input;
        // Check if the user has provided input
        if (sc.hasNextLine()) {
            // Store the user's input in the input variable
            input = sc.nextLine();
        } else {
            // If no input was provided, print a message and return
            System.out.println("No input provided.");
            sc.close();
            return;
        }
        // Record the end time
        long end = System.currentTimeMillis();

        // Calculate the elapsed time in seconds
        long elapsedTime = (end - start) / 1000;

        // Integer to store the number of correctly typed characters
        int correct = 0;
        // Loop through the text to be typed
        for (int i = 0; i < text.length(); i++) {
            // If the current character in the text is the same as the user's input
            // and the user's input is not shorter than the text
            if (i < input.length() && input.charAt(i) == text.charAt(i)) {
                // Increment the correct count
                correct++;
            }
        }
        // Calculate the accuracy as a percentage of correct characters
        accuracy = (int) ((double) correct / input.length() * 100);
        // Calculate the typing speed in characters per second
        typingSpeed = (int) ((double) correct / elapsedTime);

        // Close the Scanner object
        sc.close();
        // Display the results
        displayResult();
    }

    private void typeInOneMinute() {
        // Create a Scanner object to read user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Start typing the following text:\n");
        System.out.println(text + "\n");

        // Create a TimerThread object to handle the timer for one minute
        TimerThread timerThread = new TimerThread();
        // Start the timer thread
        timerThread.start();

        // Use a StringBuilder to collect user input
        StringBuilder inputBuilder = new StringBuilder();
        // Keep looping until the timer thread finishes
        while (timerThread.isAlive()) {
            if (sc.hasNextLine()) {
                // Add the user input to the StringBuilder
                inputBuilder.append(sc.nextLine());
            }
        }
        // Close the Scanner object
        sc.close();

        // Convert the StringBuilder to a string
        String input = inputBuilder.toString();

        // Calculate the number of correct characters typed by the user
        int correct = 0;
        for (int i = 0; i < text.length(); i++) {
            if (i < input.length() && input.charAt(i) == text.charAt(i)) {
                correct++;
            }
        }
        // Calculate the accuracy as a percentage
        accuracy = (int) ((double) correct / input.length() * 100);
        // Calculate the typing speed in characters per second
        typingSpeed = (double) correct / 60;

        // Display the results
        displayResult();
        // Exit the system
        System.exit(0);
    }

    private void displayResult() {
        // This method is used to display the results of the typing test, including
        // typing speed and accuracy.
        System.out.println("Typing Speed: " + typingSpeed + " characters per second");
        System.out.println("Accuracy: " + accuracy + "%");
    }

    class TimerThread extends Thread {
        @Override
        public void run() {
            // This inner class is used to implement a timer thread that runs for 60 seconds and stops the typing test.
            try {
                sleep(60000);
                System.out.println("\n\nTime is up!\n");

            } catch (InterruptedException e) {
                // Thread interrupted, do nothing
            }
        }
    }
}