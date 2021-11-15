import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Consumer implements Serializable {
    private String Name;
    private String Surname;
    private String Email;
    private ArrayList<Book> BookList;

    public Consumer(String name, String surname, String email, ArrayList<Book> bookList) {
        Name = name;
        Surname = surname;
        Email = email;
        BookList = bookList;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public ArrayList<Book> getBookList() {
        return BookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        BookList = bookList;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Email='" + Email + '\'' +
                ", BookList=" + BookList +
                '}';
    }
}
