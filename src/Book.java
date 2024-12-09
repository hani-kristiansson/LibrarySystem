public class Book {

    private String bookName;
    private String author;
    private String ISBN;
    private String publisher;
    private String genre;
    private int publishYear;
    private boolean isAvailable;

    public Book(String bookName, String author, String ISBN, String publisher, String genre, int publishYear) {
        this.bookName = bookName;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.genre = genre;
        this.publishYear = publishYear;
        this.isAvailable = true;
    }

    public boolean isAvailable() {

        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;

    }

    public String getTitle() {

        return bookName;
    }
}
