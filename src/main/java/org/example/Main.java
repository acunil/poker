package org.example;

import org.example.base.domain.Enums.Card;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    
    public static void listDeck() {
        for (Card card : Card.values()) {
            System.out.println(card);
        }
    }
    
}