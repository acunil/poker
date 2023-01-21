package org.example;

import Enums.Deck;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    
    public static void listDeck() {
        for (Deck card : Deck.values()) {
            System.out.println(card);
        }
    }
    
}