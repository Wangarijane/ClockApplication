import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Clock {

    private LocalDateTime currentTime = LocalDateTime.now();

    // Update the current date and time
    public void updateTime() {
        currentTime = LocalDateTime.now();
    }

    // Display the current date and time
    public void displayTime() {
        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        System.out.println("Current Date and Time: "
                + currentTime.format(format));
    }
}

public class ClockApplication {

    public static void main(String[] args) {

        Clock clock = new Clock();

        // Thread for updating the clock
        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        clock.updateTime();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Update thread interrupted.");
                }
            }
        });

        // Thread for displaying the clock
        Thread displayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        clock.displayTime();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Display thread interrupted.");
                }
            }
        });

        // Set thread priorities
        updateThread.setPriority(Thread.NORM_PRIORITY);
        displayThread.setPriority(Thread.NORM_PRIORITY + 3);

        System.out.println("Simple Clock Application");
        System.out.println("------------------------");
        System.out.println("Update Thread Priority: "
                + updateThread.getPriority());
        System.out.println("Display Thread Priority: "
                + displayThread.getPriority());
        System.out.println();

        // Start both threads
        updateThread.start();
        displayThread.start();
    }
}
