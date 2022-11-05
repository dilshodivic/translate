package org.example.controller;

import org.example.dto.Word;
import org.example.repository.WordRepository;
import org.example.service.WordService;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class WordController {
    @Autowired
    private WordService wordService;
    public void start() {

        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();

            switch (action) {
                case 1 -> addWord();
                case 2 -> wordList();
                case 3 -> multipleChoice();//TODO not done
                case 4 -> translate();
                case 5 -> searchWord();
                case 6 -> deleteById();
                case 7 -> spelling();
                case 8 -> uzbTrans();
                case 0 -> {
                    game = false;
                    System.out.println("error operation, please enter again!");
                }
            }
        }


    }

    private void uzbTrans() {
        System.out.print("Enter english: ");
        String text = ScannerUtil.SCANNER_STR.nextLine();
        wordService.translateUzb(text);
    }

    public void randomUz() {
        Word random = wordService.randomUz();

        System.out.println(random.getUzbek());
    }
    public Word randomEng() {
        Word word = wordService.randomEng();
        return word;
    }
    private void spelling() {
        randomUz();
        System.out.print("Enter english: ");
        String english = ScannerUtil.SCANNER_STR.nextLine().trim();
        wordService.equalsWord(english);


    }
    private void deleteById() {
        wordList();
        System.out.print("Enter id ");
        int id = Integer.parseInt(ScannerUtil.SCANNER_NUM.next());
        wordService.deleted(id);

    }
    private void searchWord() {
        System.out.print("Enter word: ");
        String word = ScannerUtil.SCANNER_STR.nextLine();
        wordService.searchWord(word);
    }
    private void translate() {
        System.out.print("Enter uzbek: ");
        String text = ScannerUtil.SCANNER_STR.nextLine();
        wordService.translate(text);
    }
    private void multipleChoice() {
        Word word = randomEng();
        System.out.println(word.getWord());
//        char c = ScannerUtil.SCANNER_CHAR.next().charAt(0);
        wordService.answer();


    }
    private void wordList() {

        wordService.showWord();
    }
    private void addWord() {
        System.out.print("Enter english word: ");
        String word = ScannerUtil.SCANNER_STR.nextLine().trim().toLowerCase();
        System.out.print("Enter uzbek word: ");
        String translate = ScannerUtil.SCANNER_STR.nextLine().trim();
        System.out.print("Enter description: ");
        String description = ScannerUtil.SCANNER_STR.nextLine();


        wordService.addWord(word, translate, description);


    }
    public void menu() {
        System.out.println("*******Menu*******");
        System.out.println("1.Add word");
        System.out.println("2.Word List");
        System.out.println("3.Multiple choice");
        System.out.println("4.Eng translate");
        System.out.println("5.Searching");
        System.out.println("6.Delete by id");
        System.out.println("7.Spelling");
        System.out.println("8.Uzb translate");
        System.out.println("0.Log out");
    }
}
