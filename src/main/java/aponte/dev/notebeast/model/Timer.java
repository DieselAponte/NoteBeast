package aponte.dev.notebeast.model;

import aponte.dev.notebeast.util.TimerState;
import java.time.LocalDateTime;

public class Timer {
    private TimerState timerState;
    private int studyDuration;
    private int breakDuration;
    private long remainingTime;
    private long startTime;
    private long endTime;

    public Timer(int studyDuration, int breakDuration) {
        this.studyDuration = studyDuration;
        this.breakDuration = breakDuration;
        this.timerState = TimerState.STUDYING;
        timerStart();
        breakStart();
    }

    public void timerStart(){
        System.out.println("Timer started");
        startTime = System.currentTimeMillis();
        endTime = studyDuration * 60L;
        while (endTime > startTime) {
            remainingTime = endTime - startTime;
            System.out.println("remainng time: " + remainingTime);
        }
        System.out.println("Timer Finished");
    }

    public void breakStart(){
        System.out.println("Break started");
        startTime = System.currentTimeMillis();
        endTime = breakDuration * 60L;
        while (endTime > startTime) {
            remainingTime = endTime - startTime;
            System.out.println("remainng time: " + remainingTime);
        }
        System.out.println();
    }
}
