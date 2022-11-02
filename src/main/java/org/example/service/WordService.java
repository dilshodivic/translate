package org.example.service;

import org.example.dto.Word;
import org.example.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    public void addWord(String word, String translate, String description) {
        Word exists = wordRepository.getWordByName(word);

        if (exists != null) {
            System.out.println("Word  exists");
            return;
        }
        Word wordAdded = new Word();
        wordAdded.setWord(word);
        wordAdded.setUzbek(translate);
        wordAdded.setDescription(description);
        wordAdded.setLocalDateTime(LocalDateTime.now());


        int n = wordRepository.save(wordAdded);

        if (n != 0) {
            System.out.println("Word successfully added");
            return;
        } else {
            System.out.println("ERROR");
        }
    }

    public void showWord() {
        List<Word> words = wordRepository.getWordList();
        if (words.isEmpty()) {
            System.out.println("Word List empty");
            return;
        }
        words.forEach(System.out::println);

    }

    public void searchWord(String word) {
        Word wordByName = wordRepository.getWordByName(word);

        if (wordByName != null) {
            System.out.println("Word  not exists");
            return;
        } else {
            List<Word> words = wordRepository.search(word);
            words.forEach(System.out::println);
        }
    }


    public void deleted(int id) {
        int exist = wordRepository.delete(id);
        if (exist != 0) {
            System.out.println("Successfully deleted");
        } else {
            System.out.println("id not found");
        }


    }


    public void translate(String text) {
        Word byName = wordRepository.getWordBy(text);
        System.out.println("English translate -> " + byName.getWord());


    }

    public Word randomUz() {
        List<Word> wordList = wordRepository.getWordList();
        Random random = new Random();

        Word word = wordList.get(random.nextInt(0, wordList.size()));
        return word;


    }

    public void equalsWord(String english) {
        Word word = wordRepository.equalsWord(english);
        if (word != null) {
            System.out.println("you have find right");
        } else {
            System.out.println("you couldn't find");
        }
    }

    public Word randomEng() {
        List<Word> wordList = wordRepository.getWordList();
        Random random = new Random();
        Word word = wordList.get(random.nextInt(0, wordList.size()));
        return word;
    }


    public void answer() {
        List<Word> words = wordRepository.getAnswerList();
        Collections.shuffle(words);
        List<Character> characters = List.of('A','B','C','D');

        for (int i = 0; i < 4; i++) {

            System.out.print( characters.get(i)+ ")");
            String b = "";
            String a = words.get(i).getUzbek();
            if (a.equals(b)){
                continue;
            }
            System.out.println(a);
            b = a;

        }

     /*   for (char i = 65; i < 69; i++) {
            System.out.print(i + ")");
            String b = "";

            String a = words.get(i).getUzbek();
            if (a.equals(b)){
                continue;
            }
//            System.out.println(words.get(random.nextInt(0,words.size())).getUzbek());
            System.out.println(a);
            b = a;
        }*/ //TODO estalik
    }
}
