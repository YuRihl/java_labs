import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userText = "";
        int wordLength = 0;

        System.out.println("Enter your text: ");
        if (input.hasNextLine()){
            userText = input.nextLine();
        }

        System.out.print("Enter word length you want to see: ");
        if(input.hasNextInt()){
            wordLength = input.nextInt();
        }

        ArrayList<String> sentences = makeSentences(userText);
        for (String sentence : sentences) {
            System.out.println(sentence);
        }

        ArrayList<String> correctWords = findWordsOfLength(sentences, wordLength);
        for (String word : correctWords) {
            System.out.print(word + " ");
        }
    }

    public static ArrayList<String> makeSentences(String input){
        ArrayList<String> sentences = new ArrayList<>();
        Pattern patternSentence = Pattern.compile("[A-Z].+?[\\.\\?\\!]");
        Matcher matcherSentence = patternSentence.matcher(input);
        while (matcherSentence.find()){
            sentences.add(matcherSentence.group());
        }

        return sentences;
    }

    public static ArrayList<String> findWordsOfLength(ArrayList<String> sentences, int wordLength){
        ArrayList<String> correctWords = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> wordDictionary = new HashMap<>();

        Pattern patternQuestion = Pattern.compile("[A-Z].+?\\?");
        Pattern patternWord = Pattern.compile("\\w+");

        for (String sentence : sentences) {
            Matcher matcherQuestion = patternQuestion.matcher(sentence);

            if(matcherQuestion.find()){
                Matcher matcherWord = patternWord.matcher(sentence);

                while(matcherWord.find()) {
                    String word = matcherWord.group();

                    if (wordDictionary.containsKey(word.length())) {
                        ArrayList<String> oneLengthWords = wordDictionary.get(word.length());
                        oneLengthWords.add(matcherWord.group());

                        wordDictionary.put(word.length(), oneLengthWords);
                    }
                    else{
                        ArrayList<String> oneLengthWord = new ArrayList<>();
                        oneLengthWord.add(word);
                        wordDictionary.put(word.length(), oneLengthWord);
                    }
                }
            }
        }

        if(wordDictionary.containsKey(wordLength)){
            return wordDictionary.get(wordLength);
        }
        else{
            return new ArrayList<>();
        }
    }
}


/*The Tortoise never stopped for a moment, walking slowly but steadily, right to the end of the course? The Hare ran fast and stopped to lie down for a rest. But he fell fast asleep? Eventually, he woke up and ran as fast as he could. But when he reached the end, he saw the Tortoise there already, sleeping comfortably after her effort.*/