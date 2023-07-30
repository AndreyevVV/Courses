package com.efimchick.ifmo.collections.countwords;


import java.util.*;
import java.util.regex.Pattern;

public class Words {

    public String countWords(List<String> lines) {

        String answer = "";

        HashMap<String, Integer> myVocabularyount = new HashMap<>();

        String regex = "[^a-zA-Zа-яА-Я]";
        Pattern pattern = Pattern.compile(regex);

        for (String subText : lines) {
            subText = subText.toLowerCase();
            subText = subText.replaceAll(regex, " ");
            String[] words = subText.split(" ");
            for (String word : words){
                if (word.length() < 4) continue;
                if (myVocabularyount.containsKey(word)) {
                    myVocabularyount.put(word, myVocabularyount.get(word) + 1);
                } else {
                    myVocabularyount.put(word, 1);
                }
            }
        }

        ArrayList<Word> words = new ArrayList<>();

        for (String word : myVocabularyount.keySet()){
            if (myVocabularyount.get(word) >= 10){
                words.add(new Word(word, myVocabularyount.get(word)));
            }
        }
        words.sort((o1, o2) -> {
            if (o1.count < o2.count) return 1;
            else if (o1.count > o2.count) return -1;
                else return o1.word.compareTo(o2.word);
        });

        for (int i = 0; i < words.size(); i++){
            if (i < words.size()-1){answer += words.get(i).word + " - " + words.get(i).count + "\n";}
            else {answer += words.get(i).word + " - " + words.get(i).count;}
        }
        return answer;
    }

    public static class Word {
        private String word;
        private Integer count;

        public Word(String word, Integer count) {
            this.word = word;
            this.count = count;
        }
    }
}



