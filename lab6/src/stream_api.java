import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class stream_api {
    public static void main(String[] args) throws Exception{
        ArrayList<Consumer> consumers = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        JSONArray consumerArray = (JSONArray) new JSONParser().parse(new FileReader("src/consumers.json"));
        JSONArray bookArray = (JSONArray) new JSONParser().parse(new FileReader("src/books.json"));


        Iterator consumerIterator = consumerArray.iterator();
        while(consumerIterator.hasNext()){
            JSONObject consumer = (JSONObject) consumerIterator.next();

            String consumerName = consumer.get("Name").toString();
            String consumerSurname = consumer.get("Surname").toString();
            String consumerEmail = consumer.get("Email").toString();
            JSONArray consumerBooks = (JSONArray) consumer.get("BookList");
            ArrayList<Book> booksArray = new ArrayList<>();

            Iterator bookIterator = consumerBooks.iterator();
            while(bookIterator.hasNext()){
                JSONObject book = (JSONObject) bookIterator.next();

                String bookName = book.get("Name").toString();
                Long bookReleaseYear = (Long) book.get("BookReleaseYear");
                String bookAuthor = book.get("Author").toString();
                String bookStartUsingDate = book.get("StartUsingDate").toString();
                String bookEndUsingDateExpected = book.get("EndUsingDateExpected").toString();
                String bookEndUsingDateActual = book.get("EndUsingDateActual").toString();

                booksArray.add(new Book(bookName, bookReleaseYear, bookAuthor, LocalDate.parse(bookStartUsingDate), LocalDate.parse(bookEndUsingDateExpected), LocalDate.parse(bookEndUsingDateActual)));
            }

            consumers.add(new Consumer(consumerName, consumerSurname, consumerEmail, booksArray));
        }

        Iterator bookIterator = bookArray.iterator();

        while (bookIterator.hasNext()){
            JSONObject book = (JSONObject) bookIterator.next();

            String bookName = book.get("Name").toString();
            Long bookReleaseYear = (Long) book.get("BookReleaseYear");
            String bookAuthor = book.get("Author").toString();
            String bookStartUsingDate = book.get("StartUsingDate").toString();
            String bookEndUsingDateExpected = book.get("EndUsingDateExpected").toString();
            String bookEndUsingDateActual = book.get("EndUsingDateActual").toString();

            books.add(new Book(bookName, bookReleaseYear, bookAuthor, LocalDate.parse(bookStartUsingDate), LocalDate.parse(bookEndUsingDateExpected), LocalDate.parse(bookEndUsingDateActual)));
        }

        //sorting
        Library library = new Library(books, consumers);
        System.out.println("Books before sorting: " + library.getBookList());
        library.sortBooksByYear();
        System.out.println("Books after sorting: " + library.getBookList());
        System.out.println();

        //email of consumers that have 2 or more books
        System.out.println("Emails of consumers that have 2 or more books: ");
        library.emailsUserBookGreaterThan(2).forEach(email -> System.out.print(email + " "));
        System.out.println();
        System.out.println();

        // number of users that have any book of specified author
        System.out.println("The book of author Exupery is owned by " + library.booksOrderedBy("Exupery") + " number of consumers");
        System.out.println();

        // biggest amount of books owned by consumers
        System.out.println("The most books the consumer have is " + library.biggestNumOfBooks());
        System.out.println();

        // messaging to two groups of consumers
        library.messageCustomers(2);
        System.out.println();

        Map<Consumer, Map<Book, Integer>> debtList = library.debtorList();

        for(Map.Entry<Consumer, Map<Book, Integer>> consumer : debtList.entrySet()){
            System.out.println(consumer.getKey());
            consumer.getValue().forEach((key, value) -> System.out.println(key + " " + value));
        }

        //serialization
        ObjectOutputStream collections = new ObjectOutputStream(new FileOutputStream("src/collections.dat"));
        collections.writeObject(books);
        collections.writeObject(consumers);
    }
}
