package org.example;

import org.example.config.Config;
import org.example.controller.WordController;
import org.example.db.Database;
import org.example.repository.WordRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Database.initTable();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        WordController wordController = (WordController) applicationContext.getBean("wordController");
        wordController.start();

    }
//     Map<Character ,String > map = new HashMap<>();
//     map.put('A',"alo");
//        System.out.println(map.get('A'));
//    }
}









