package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collecting {

    int sum(IntStream intStream) {
        return intStream.sum();
    }

    int production(IntStream intStream) {
        return intStream.reduce((i1, i2) -> (i1 * i2)).getAsInt();
    }

    int oddSum(IntStream intStream) {
        return intStream.filter(i -> (i % 2 == 1 || i % 2 == -1)).sum();
    }

    HashMap<Integer, Integer> sumByRemainder(int divisor, IntStream intStream) {
        HashMap<Integer, Integer> map = new HashMap<>();
        intStream.forEach(i -> {
            int remainder = i % divisor;
            map.put(remainder, map.getOrDefault(remainder, 0) + i);
        });
        return map;
    }


    public HashMap<Person, Double> totalScores(Stream<CourseResult> stream) {
        HashMap<Person, Double> map = new HashMap<>();
        Set<String> allTasks = new HashSet<>();
        stream.forEach(cr -> {
            double sum = 0.0;
            var results = cr.getTaskResults();
            for (String task :
                    results.keySet()) {
                allTasks.add(task);
                sum += results.get(task);
            }
            map.put(cr.getPerson(), sum);
        });

        Double sum;
        for (Person person :
                map.keySet()) {
            sum = map.get(person);
            sum = sum / allTasks.size();
            map.put(person, sum);
        }
        return map;
    }

    public double averageTotalScore(Stream<CourseResult> stream) {
        Set<String> allTasks = new HashSet<>();
        Set<Person> allStudents = new HashSet<>();

        AtomicReference<Double> sum = new AtomicReference<>((double) 0);

        stream.forEach(cr -> {
            allStudents.add(cr.getPerson());
            for (String task :
                    cr.getTaskResults().keySet()) {
                allTasks.add(task);
                sum.updateAndGet(v -> v + cr.getTaskResults().getOrDefault(task, 0));


            }
        });
        int totalDenominator = allTasks.size() * allStudents.size();
        return sum.get() / totalDenominator;
    }

    public HashMap<String, Double> averageScoresPerTask(Stream<CourseResult> stream) {
        HashMap<String, Double> map = new HashMap<>();

        Set<Person> allStudents = new HashSet<>();
        stream.forEach(cr -> {
            double temp;
            allStudents.add(cr.getPerson());
            for (String task :
                    cr.getTaskResults().keySet()) {
                temp = map.getOrDefault(task, 0.0) + cr.getTaskResults().getOrDefault(task, 0);
                map.put(task, temp);
            }
        });
        int denom = allStudents.size();
        Double sum;
        for (String task :
                map.keySet()) {
            sum = map.get(task);
            sum = sum / denom;
            map.put(task, sum);
        }
        return map;
    }

    public HashMap<Person, String> defineMarks(Stream<CourseResult> stream) {
        HashMap<Person, String> map = new HashMap<>();

        HashMap<Person, Double> mapScores = totalScores(stream);
        String mark;
        for (var entry :
                mapScores.entrySet()) {
            mark = getMarkOfLetter(entry.getValue());
            map.put(entry.getKey(), mark);
        }

        return map;
    }

    public String easiestTask(Stream<CourseResult> stream) {
        var map = averageScoresPerTask(stream);
        var entriesStream = map.entrySet().stream();
        return entriesStream.max(Map.Entry.comparingByValue()).get().getKey();
    }

    public Collector<CourseResult, List<CourseResult>, String> printableStringCollector() {
        return new SPCollector();
    }

    public String getMarkOfLetter(double d){
        String mark;
        if (d < 60) mark = "F";
        else if (d < 68) mark = "E";
        else if (d < 75) mark = "D";
        else if (d < 83) mark = "C";
        else if (d < 90) mark = "B";
        else mark = "A";
        return mark;
    }
}
