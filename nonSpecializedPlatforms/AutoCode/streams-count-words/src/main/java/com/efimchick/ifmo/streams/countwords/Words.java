package com.efimchick.ifmo.streams.countwords;

import java.util.*;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        String res;

        HashMap<String, Integer> ledger = new HashMap<>();

        ArrayList<String> aux = new ArrayList<>();

        lines.stream()
                .map(String::toLowerCase)
                .map(s -> s.replaceAll("[^a-zA-Zа-яА-Я^]", " "))
                .map(s -> s.split(" "))
                .forEach(arr -> aux.addAll(Arrays.asList(arr)));

        aux.stream()
                .filter(s -> (s.length() >= 4))
                .forEach(word -> {
                    var a3 = (!ledger.containsKey(word)) ? (ledger.put(word, 1)) : (ledger.put(word, ledger.get(word) + 1));
                });

        ArrayList<WordNumber> list = new ArrayList<>();

        ledger.keySet().stream()
                .filter(s -> ledger.get(s) >= 10)
                .forEach(s -> list.add(new WordNumber(s, ledger.get(s))));


        Comparator comparator = (o1, o2) -> {
            WordNumber wc1 = (WordNumber) o1;
            WordNumber wc2 = (WordNumber) o2;
            int t = 0;
            int t1 = (wc1.getCount() > wc2.getCount()) ? (t = -1) : (100);
            int t2 = (wc1.getCount() < wc2.getCount()) ? (t = 1) : (100);
            int t3 = (wc1.getCount().equals(wc2.getCount())) ? (t = 0) : (100);
            switch (t) {
                case 1:
                    return 1;
                case -1:
                    return -1;
            }
            return wc1.getWord().compareTo(wc2.getWord());
        };
/*        sorted(Comparator.comparing(
                (Function<Map.Entry<String, Long>, Long>) Map.Entry::getValue).reversed()
                .thenComparing(Map.Entry.comparingByKey()));*/

        list.sort(comparator); 
        System.out.println(list.size());
        res = list.stream()
                .map(w -> (w.getWord() + " - " + w.getCount()))
                .collect(Collectors.joining("\n"));
        return res;
    }
}
