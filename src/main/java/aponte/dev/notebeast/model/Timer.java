package aponte.dev.notebeast.model;

import aponte.dev.notebeast.util.TimerState;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Timer {
    private TimerState timerState;
    private final long studyDurationMilliseconds;
    private final long breakDurationMilliseconds;

    public Timer(long studyDurationMins, long breakDurationMins) {
        studyDurationMins = studyDurationMins * 60000;
        breakDurationMins = breakDurationMins * 60000;
        this.studyDurationMilliseconds = studyDurationMins;
        this.breakDurationMilliseconds = breakDurationMins;
    }

    public void startTimer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting Study Timer");
        StartStudyTimer();
        timerState = TimerState.PAUSED;
        String input = scanner.nextLine();
        StartBreakTimer();
    }

    private void StartStudyTimer(){
        timerState = TimerState.STUDYING;
        countDownTimer(studyDurationMilliseconds);
    }

    private void StartBreakTimer(){
        timerState = TimerState.BREAK;
        countDownTimer(breakDurationMilliseconds);
    }

    private void countDownTimer(long timeMillis){
        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeMillis;
        while(endTime > System.currentTimeMillis()) {
            long remainingTime = endTime - System.currentTimeMillis();
            if (timeMillis > TimeUnit.MINUTES.toMillis(60)) {
                long hours = TimeUnit.MILLISECONDS.toHours(remainingTime);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime) % 60;
                long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60;
                System.out.printf("Tiempo restante: %02d:%02d:%02d", hours, minutes, seconds);
                System.out.println("");
            } else {
                long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60;
                long milliseconds = remainingTime % 1000;
                System.out.printf("Tiempo restante: %02d:%02d:%02d", minutes, seconds, milliseconds);
                System.out.println("");
            }
        }
    }
}
