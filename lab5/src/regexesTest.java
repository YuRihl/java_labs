import org.junit.Assert;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class regexesTest {

    @org.junit.jupiter.api.Test
    void makeSentences() {
        String input = "The Tortoise never stopped for a moment, walking slowly but steadily, right to the end of the course. The Hare ran fast and stopped to lie down for a rest.";
        ArrayList<String> sentencesExpected = new ArrayList<>();
        sentencesExpected.add("The Tortoise never stopped for a moment, walking slowly but steadily, right to the end of the course.");
        sentencesExpected.add("The Hare ran fast and stopped to lie down for a rest.");

        ArrayList<String> sentencesActual = regexes.makeSentences(input);

        Assert.assertEquals(sentencesExpected, sentencesActual);
    }

    @org.junit.jupiter.api.Test
    void makeSentencesNegative(){
        String input = "";
        ArrayList<String> sentenceExpected = new ArrayList<>();
        ArrayList<String> sentenceActual = regexes.makeSentences(input);

        Assert.assertEquals(sentenceExpected, sentenceActual);
    }

    @org.junit.jupiter.api.Test
    void findWordsOfLength() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("The Tortoise never stopped for a moment, walking slowly but steadily, right to the end of the course.");
        sentences.add("The Hare ran fast and stopped to lie down for a rest?");

        ArrayList<String> correctWordsExpected = new ArrayList<>();
        correctWordsExpected.add("Hare");
        correctWordsExpected.add("fast");
        correctWordsExpected.add("down");
        correctWordsExpected.add("rest");

        ArrayList<String> correctWordsActual = regexes.findWordsOfLength(sentences, 4);

        Assert.assertEquals(correctWordsExpected, correctWordsActual);
    }

    @org.junit.jupiter.api.Test
    void findWordsOfLengthNegative(){
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("The Tortoise never stopped for a moment, walking slowly but steadily, right to the end of the course.");
        sentences.add("The Hare ran fast and stopped to lie down for a rest?");

        ArrayList<String> correctWordsExpected = new ArrayList<>();

        ArrayList<String> correctWordsActual = regexes.findWordsOfLength(sentences, 5);

        Assert.assertEquals(correctWordsExpected, correctWordsActual);
    }
}