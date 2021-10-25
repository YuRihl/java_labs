import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;

public class lab4 {
    public static void main(String[] args) throws Exception{
        ArrayList<ArrayList<String>> wordArrayEng = ReadFileWords("src/userdata_eng.txt");
        ArrayList<ArrayList<String>> initialsArrayEng = new ArrayList<>();
        ArrayList<String> sentenceArrayEng = ReadSentences("src/userdata_eng.txt");

        for(ArrayList<String> sentence : wordArrayEng){
             initialsArrayEng.add(FindInitials(sentence, true));
        }

        System.out.println(initialsArrayEng);

        printNeighboringSentences(sentenceArrayEng, initialsArrayEng);

        ArrayList<String> changedSentenceArrayEng = changeInitialsToLongestWord(initialsArrayEng, wordArrayEng, sentenceArrayEng);

        for(String changedString : changedSentenceArrayEng){
            System.out.println(changedString);
        }

        System.out.println();

        ArrayList<ArrayList<String>> wordArrayUkr = ReadFileWords("src/userdata_ukr.txt");
        ArrayList<ArrayList<String>> initialsArrayUkr = new ArrayList<>();
        ArrayList<String> sentenceArrayUkr = ReadSentences("src/userdata_ukr.txt");

        for(ArrayList<String> sentence : wordArrayUkr){
            initialsArrayUkr.add(FindInitials(sentence, false));
        }

        System.out.println(initialsArrayUkr);

        printNeighboringSentences(sentenceArrayUkr, initialsArrayUkr);

        ArrayList<String> changedSentenceArrayUkr = changeInitialsToLongestWord(initialsArrayUkr, wordArrayUkr, sentenceArrayUkr);

        for(String changedString : changedSentenceArrayUkr){
            System.out.println(changedString);
        }
    }

    public static ArrayList<ArrayList<String>> ReadFileWords(String filepath) throws Exception{
        Scanner fileInput = new Scanner(new File(filepath));

        ArrayList<ArrayList<String>> sentenceArray = new ArrayList<>();

        while (fileInput.hasNext()){
            Scanner sentence = new Scanner(fileInput.nextLine());
            ArrayList<String> wordArray = new ArrayList<>();

            while(sentence.hasNext()){
                wordArray.add(sentence.next());
            }
            sentenceArray.add(wordArray);
        }

        return sentenceArray;
    }

    public static ArrayList<String> ReadSentences(String filepath) throws Exception{
        Scanner fileInput = new Scanner(new File(filepath));
        ArrayList<String> sentenceArray = new ArrayList<>();

        while(fileInput.hasNext()){
            sentenceArray.add(fileInput.nextLine());
        }
        return sentenceArray;
    }

    public static ArrayList<String> FindInitials(ArrayList<String> input, boolean isEnglishInitials){
        ArrayList<String> initialsArray = new ArrayList<>();

        ListIterator<String> wordArrayIterator = input.listIterator();

        while(wordArrayIterator.hasNext()){
            String surname = wordArrayIterator.next();

            if(isWordValid(surname)){
                if(wordArrayIterator.hasNext()) {

                    String name = wordArrayIterator.next();
                    if (isWordValid(name)) {
                        if (!isEnglishInitials) {
                            if (wordArrayIterator.hasNext()) {

                                String patronymic = wordArrayIterator.next();
                                if (isWordValid(patronymic)) {
                                    initialsArray.add(String.join(" ", surname, name, patronymic));
                                    wordArrayIterator.previous();
                                    wordArrayIterator.previous();
                                }
                            }
                        } else {
                            initialsArray.add(String.join(" ", surname, name));
                            wordArrayIterator.previous();
                        }
                    }
                }
            }
        }
        return initialsArray;
    }

    public static void printNeighboringSentences(ArrayList<String> sentences, ArrayList<ArrayList<String>> initialsArray){
        ListIterator<ArrayList<String>> initialsArrayIteratorEng = initialsArray.listIterator();

        // printing neighboring sentences
        while(initialsArrayIteratorEng.hasNext()){
            ArrayList<String> initials = initialsArrayIteratorEng.next();

            if(!initials.isEmpty()){
                initialsArrayIteratorEng.previous();
                if(initialsArrayIteratorEng.hasPrevious()){
                    System.out.println(sentences.get(initialsArrayIteratorEng.previousIndex()));
                }
                if(initialsArrayIteratorEng.hasNext()){
                    initialsArrayIteratorEng.next();
                    System.out.println(sentences.get(initialsArrayIteratorEng.nextIndex()));
                }
            }
        }
        System.out.println();
    }

    public static ArrayList<String> changeInitialsToLongestWord(ArrayList<ArrayList<String>> initials, ArrayList<ArrayList<String>> words, ArrayList<String> sentences){
        ArrayList<String> changedArray = new ArrayList<>();
        Comparator<String> lengthComparator = (String first, String second)->{
            return first.length()-second.length();
        };

        for(String sentence : sentences){
            int sentenceIndex = sentences.indexOf(sentence);

            if(initials.get(sentenceIndex).isEmpty()){
                changedArray.add(sentence);
            }
            else{

                String maxLengthWord = Collections.max(words.get(sentenceIndex), lengthComparator);
                changedArray.add(sentence.replace(initials.get(sentenceIndex).get(0), maxLengthWord));
            }
        }

        return changedArray;
    }

    public static boolean isWordValid(String word){
        boolean isAllLetter=true;

        /*if(word.endsWith(".") || word.endsWith(",") || word.endsWith(";") || word.endsWith("!") || word.endsWith("?")){
            word = word.substring(0, word.length()-1);
        }*/

        for(char character : word.toCharArray()){
            if(!Character.isLetter(character)){
                isAllLetter=false;
                break;
            }
        }

        if(!isAllLetter || Character.isLowerCase(word.charAt(0))){
            return false;
        }
        else return true;
    }
}
