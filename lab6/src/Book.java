import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

public class Book implements Serializable {
    private String Name;
    private Long BookReleaseYear;
    private String Author;

    private LocalDate StartUsingDate;
    private LocalDate EndUsingDateExpected;
    private LocalDate EndUsingDataActual;

    public Book(String name, Long bookReleaseYear, String author, LocalDate startUsingDate, LocalDate endUsingDateExpected, LocalDate endUsingDataActual) {
        Name = name;
        BookReleaseYear = bookReleaseYear;
        Author = author;
        StartUsingDate = startUsingDate;
        EndUsingDateExpected = endUsingDateExpected;
        EndUsingDataActual = endUsingDataActual;
    }

    public Book(String name, Long bookReleaseYear, String author) {
        Name = name;
        BookReleaseYear = bookReleaseYear;
        Author = author;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Long getBookReleaseYear() {
        return BookReleaseYear;
    }

    public void setBookReleaseYear(Long bookReleaseYear) {
        this.BookReleaseYear = bookReleaseYear;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public LocalDate getStartUsingDate() {
        return StartUsingDate;
    }

    public void setStartUsingDate(LocalDate startUsingDate) {
        StartUsingDate = startUsingDate;
    }

    public LocalDate getEndUsingDateExpected() {
        return EndUsingDateExpected;
    }

    public void setEndUsingDateExpected(LocalDate endUsingDateExpected) {
        EndUsingDateExpected = endUsingDateExpected;
    }

    public LocalDate getEndUsingDataActual() {
        return EndUsingDataActual;
    }

    public void setEndUsingDataActual(LocalDate endUsingDataActual) {
        EndUsingDataActual = endUsingDataActual;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Name='" + Name + '\'' +
                ", BookReleaseYear=" + BookReleaseYear +
                ", Author='" + Author + '\'' +
                ", StartUsingDate=" + StartUsingDate +
                ", EndUsingDateExpected=" + EndUsingDateExpected +
                ", EndUsingDataActual=" + EndUsingDataActual +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Book book = (Book) obj;
        return this.getAuthor().equals(book.getAuthor());
    }
}
