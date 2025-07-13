package aponte.dev.notebeast;

import aponte.dev.notebeast.model.Timer;

public class testing {
    public static void main(String[] args) {
        Timer timer = new Timer(1,1);
        // returns the current time in milliseconds
        System.out.print("Current Time in milliseconds = ");
        System.out.println(System.nanoTime());
    }
}
