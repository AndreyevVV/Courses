package com.efimchick.ifmo;

import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


public class SPCollector implements
        Collector<CourseResult, List<CourseResult>, String> {

    @Override
    public Supplier<List<CourseResult>> supplier() {
        return ArrayList::new;
    }


    @Override
    public BiConsumer<List<CourseResult>, CourseResult> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<CourseResult>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<CourseResult>, String> finisher() {
        return this::prettyString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }

    public String prettyString(List<CourseResult> list) {
        HashSet<String> tasks = new HashSet<>();
        HashSet<Person> students = new HashSet<>();

        Collecting collecting = new Collecting();
        StringBuilder sb = new StringBuilder();

        list.forEach(cr -> students.add(cr.getPerson()));

        list.forEach(cr -> {
            var map = cr.getTaskResults();
            tasks.addAll(map.keySet());
        });

        int maxLengthofName = 0;
        for (Person person :
                students) {
            int length = (person.getFirstName() + person.getLastName()).length();
            if (length > maxLengthofName) {
                maxLengthofName = length;
            }
        }

        int maxLengthOfTask = 0;
        for (String task :
                tasks) {
            int length = task.length();
            if (length > maxLengthOfTask) {
                maxLengthOfTask = length;
            }
        }

        var totalScores = collecting.totalScores(list.stream());
        var marksMap = collecting.defineMarks(list.stream());
        var averagePerTask = collecting.averageScoresPerTask(list.stream());

        ArrayList<String> tasksArrangedList = new ArrayList<>(tasks);
        tasksArrangedList.sort(Comparator.naturalOrder());

        ArrayList<Person> studentsArrangedList = new ArrayList<>(students);
        studentsArrangedList.sort(Comparator.comparing(Person::getLastName));

        int row1Length = maxLengthofName + 2;

        sb.append("Student").append(sp(row1Length - "Student".length())).append("|");
        for (String task:
                tasksArrangedList) {
            sb.append(" ").append(task).append(" |");
        }
        sb.append(" " + "Total"+" |");
        sb.append(" " + "Mark"+" |");
        int rowMarkLength = (" " + "Mark"+" |").length();
        sb.append("\n");

        list.sort(Comparator.comparing(c -> c.getPerson().getLastName()));

        list.forEach((cr)->
        {
            Person curStudent = cr.getPerson();
            String fullName = curStudent.getLastName()+" "+curStudent.getFirstName();
            sb.append(fullName).append(sp(row1Length - fullName.length())).append("|");


            var mmap = cr.getTaskResults();
            for (String task:
                    tasksArrangedList){
                int rowLength = task.length()+2;

                int mark = mmap.getOrDefault(task,0);
                String markString = Integer.toString(mark);
                sb.append(sp(rowLength - markString.length() - 1)).append(markString).append(" |");
            }

            double total = totalScores.get(curStudent);
            String mark = marksMap.get(curStudent);
            sb.append(" ").append(String.format(Locale.ROOT, "%.2f", total)).append(" |");
            sb.append(sp(rowMarkLength - 3)).append(mark).append(" |");
            sb.append("\n");
        });

        sb.append("Average").append(sp(row1Length - "Average".length())).append("|");


        for (String task:
                tasksArrangedList){
            int rowLength = task.length()+2;
            double average = averagePerTask.get(task);
            sb.append(sp(rowLength - 6)).append(String.format(Locale.ROOT, "%.2f", average)).append(" |");
        }

        double total = collecting.averageTotalScore(list.stream());
        sb.append(sp(1)).append(String.format(Locale.ROOT, "%.2f", total)).append(" |");

        String totalMark = collecting.getMarkOfLetter(total);
        sb.append(sp(4)).append(totalMark).append(" |");

        return sb.toString();
    }

    private String sp(int n) {
        return " ".repeat(Math.max(0, n));
    }
}
