public class Book {

    private String bookName;
    private String author;
    private String ISBN;
    private String publisher;
    private String genre;
    private int publishYear;

    public Book(String bookName, String author, String ISBN, String publisher, String genre, int publishYear) {
        this.bookName = bookName;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.genre = genre;
        this.publishYear = publishYear;
    }
}
