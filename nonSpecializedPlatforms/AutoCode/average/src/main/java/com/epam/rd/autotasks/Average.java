package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(averageNum(scanner));
    }

    public static int averageNum(Scanner scanner) {
        List<Integer> inArray = new ArrayList<Integer>();
        boolean run = true;
        do {
            inArray.add(Integer.parseInt(scanner.next()));
            if (inArray.get(inArray.size() - 1) == 0) {
                run = false;
            }
        } while (run);
        Integer sum = 0;
        for (int num : inArray) {
            sum += num;
        }
        return sum / (inArray.size() - 1);
    }
}
