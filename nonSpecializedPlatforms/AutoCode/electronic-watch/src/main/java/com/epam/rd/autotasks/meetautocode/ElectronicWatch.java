package com.epam.rd.autotasks.meetautocode;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        timeFormat (seconds);

    }
    public static void timeFormat (int seconds){
        int days = (int)TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (days *24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        String formatMinute = new DecimalFormat("00").format(minute);
        String formatSecond = new DecimalFormat("00").format(second);
        System.out.println(hours + ":" + formatMinute + ":" + formatSecond);
    }
}
