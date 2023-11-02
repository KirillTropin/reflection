package org.example;

import org.example.annotations.CustomToString;

@CustomToString
public class ProstoClass {
    public int vChyomSmyslVsegoIVoobcshe;

    public void ask(String question) {
        if(question.isEmpty()) {
            System.out.println("Спроси ещё раз...");
        } else if (question.contains("смысл")) {
            vChyomSmyslVsegoIVoobcshe = 42;
            System.out.println(42);
        } else {
            System.out.println("Подумай сам.");
        }
    }
}
