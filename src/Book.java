import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Book> getBooks() {
        String readInBookName, readInAuthor, readInISBN, readInPublisher, readInGenre, readInYear;
        int readInYearInt;
        int i = 0;
        List<Book> bookList = new ArrayList<Book>();

        //Uses BufferedReader to read from file
        try (BufferedReader br = new BufferedReader(new FileReader("BookLog"))) {
            // Checks the line is not empty
            String line;
            while ((line = br.readLine()) != null) {
                    //Stores Personal-number
                    readInBookName = line;
                    readInAuthor = br.readLine();
                    readInISBN = br.readLine();
                    readInPublisher = br.readLine();
                    readInGenre = br.readLine();
                    readInYear = br.readLine();

                    readInYearInt = Integer.parseInt(readInYear);
                    Book book = new Book(readInBookName,readInAuthor,readInISBN,readInPublisher,readInGenre,readInYearInt);
                    bookList.add(book);
            }
        }catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Something went wrong\n Please Try again");
        }
        return bookList;
    }

    public static String printInfo(Book book) {

        return book.bookName +"\n"+ book.author + "\n"+ book.ISBN + "\n"+ book.publisher + "\n"+ book.genre + "\n"+ book.publishYear+ "\n";
    }


    public static void searchBooks(List<Book> bookList, String searchedString) {
        List<Book> results = new ArrayList<>();
        String lowerSearchString = searchedString.toLowerCase();

        for (Book book : bookList) {
            if (book.bookName.toLowerCase().contains(lowerSearchString) ||
                    book.author.toLowerCase().contains(lowerSearchString) ||
                    book.ISBN.toLowerCase().contains(lowerSearchString) ||
                    book.publisher.toLowerCase().contains(lowerSearchString) ||
                    book.genre.toLowerCase().contains(lowerSearchString) ||
                    String.valueOf(book.publishYear).contains(lowerSearchString)) {
                results.add(book);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No books found matching \"" + searchedString + "\".");
        } else {
            System.out.println("Books matching \"" + searchedString + "\":");
            for (Book result : results) {
                System.out.println(printInfo(result));
            }
        }
    }



    public static void main(String[] args) {
        // Get books from file
        List<Book> books = Book.getBooks();

        int i = 0;
        // Print all books to console
        /*
        for (Book book : books) {

            System.out.println("Book "+i+book);
            i++;
        }
*/
        String searchQuery = JOptionPane.showInputDialog("Enter a word to search for books:");
        if (searchQuery != null && !searchQuery.isEmpty()) {
            searchBooks(books, searchQuery);
        }
    }
}
