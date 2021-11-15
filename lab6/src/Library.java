import java.util.*;
import java.util.stream.Collectors;

public class Library {
    ArrayList<Book> BookList;
    ArrayList<Consumer> ConsumerList;

    public Library(ArrayList<Book> bookList, ArrayList<Consumer> consumerList) {
        BookList = bookList;
        ConsumerList = consumerList;
    }

    public ArrayList<Book> getBookList() {
        return BookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        BookList = bookList;
    }

    public ArrayList<Consumer> getConsumerList() {
        return ConsumerList;
    }

    public void setConsumerList(ArrayList<Consumer> consumerList) {
        ConsumerList = consumerList;
    }

    public void sortBooksByYear(){
        Comparator<Book> bookYearComparator = (Book firstBookYear, Book secondBookYear) -> {
            return (int) (firstBookYear.getBookReleaseYear() - secondBookYear.getBookReleaseYear());
        };

        BookList = BookList.stream().sorted(bookYearComparator).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> emailsUserBookGreaterThan(int numberOfBooks){
        ArrayList<String> emailList = ConsumerList.stream().filter(consumer -> consumer.getBookList().size() > numberOfBooks).map(Consumer::getEmail).collect(Collectors.toCollection(ArrayList::new));
        return emailList;
    }

    public int booksOrderedBy(String author){
        ArrayList<Consumer> consumersWithBooks = ConsumerList.stream().filter(consumer -> consumer.getBookList().contains(new Book("Some Book", Integer.toUnsignedLong(1990), author))).collect(Collectors.toCollection(ArrayList::new));

        return consumersWithBooks.size();
    }

    public int biggestNumOfBooks(){
        ArrayList<Integer> numOfBooksPerConsumer = ConsumerList.stream().map(consumer -> consumer.getBookList().size()).collect(Collectors.toCollection(ArrayList::new));
        return numOfBooksPerConsumer.stream().max(Integer::compare).get();
    }

    public void messageCustomers(int booksOwned){
        ConsumerList.stream().filter(consumer -> consumer.getBookList().size() < booksOwned).map(consumer -> consumer.getEmail())
        .forEach(email -> System.out.println("Dear " + email + ", we have sent you a new collection of books so don`t waste your time!"));

        ConsumerList.stream().filter(consumer -> consumer.getBookList().size() >= booksOwned).map(consumer -> consumer.getEmail())
                .forEach(email -> System.out.println("Dear " + email + ", remind you about giving your books back at time! Don`t forget about it!"));
    }

    public Map<Consumer, Map<Book, Integer>> debtorList(){
        Map<Consumer, Map<Book, Integer>> debtors = ConsumerList.stream().filter(consumer ->
                consumer.getBookList().stream().anyMatch(book -> book.getEndUsingDataActual().isAfter(book.getEndUsingDateExpected())))
                .collect(Collectors.toMap(consumer -> consumer, consumer -> consumer.getBookList().stream().filter(book -> book.getEndUsingDataActual().isAfter(book.getEndUsingDateExpected()))
                        .collect(Collectors.toMap(s -> s, p -> p.getEndUsingDataActual().getDayOfYear() - p.getEndUsingDateExpected().getDayOfYear()))));

        return debtors;
    }
}
