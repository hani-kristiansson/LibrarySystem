import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book {

    private static String bookName;
    private static String author;
    private static String ISBN;
    private static String publisher;
    private static String genre;
    private static int publishYear;
    private static int quantity;
    private List <String> queue = new ArrayList<>();
    //Singleton example
    private static final List<Book> bookList = Book.getBooks();


    // Singleton method to retrieve the single book list
    public static List<Book> getBookList() {
        return bookList;
    }

    public Book(String bookName, String author, String ISBN, String publisher, String genre, int publishYear, int quantity) {
        Book.bookName = bookName;
        Book.author = author;
        Book.ISBN = ISBN;
        Book.publisher = publisher;
        Book.genre = genre;
        Book.publishYear = publishYear;
        Book.quantity = quantity;
    }

    public static List<Book> getBooks() {
        String readInBookName, readInAuthor, readInISBN, readInPublisher, readInGenre, readInYear, readInQuantity;
        int readInYearInt, readInQuantityInt;
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
                readInQuantity = br.readLine();


                readInYearInt = Integer.parseInt(readInYear);
                readInQuantityInt = Integer.parseInt(readInQuantity);
                Book book = new Book(readInBookName, readInAuthor, readInISBN, readInPublisher, readInGenre, readInYearInt, readInQuantityInt);
                bookList.add(book);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong\n Please Try again");
        }
        return bookList;
    }

    public static String printInfo(Book book) {

        return bookName + "\n" + author + "\n" + ISBN + "\n" + publisher
                + "\n" + genre + "\n" + publishYear + "\n" + quantity + "\n";
    }

    public static void searchBooks(List<Book> bookList, String searchedString) {
        List<Book> searchResults = new ArrayList<>();
        String lowerSearchString = searchedString.toLowerCase();

        for (Book book : bookList) {
            if (bookName.toLowerCase().contains(lowerSearchString) ||
                    author.toLowerCase().contains(lowerSearchString) ||
                    ISBN.toLowerCase().contains(lowerSearchString) ||
                    publisher.toLowerCase().contains(lowerSearchString) ||
                    genre.toLowerCase().contains(lowerSearchString) ||
                    String.valueOf(publishYear).contains(lowerSearchString) ||
                    quantity == Integer.parseInt(searchedString)) {
                searchResults.add(book);
            }
            if (quantity == 0) {
                Scanner scan = new Scanner(System.in);
                System.out.println("The book is not available, Would you like to stand in queue?");
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("No books found matching \"" + searchedString + "\".");
        } else {
            System.out.println("Books matching \"" + searchedString + "\":");
            for (Book result : searchResults) {
                System.out.println(printInfo(result));
            }
        }
    }

    public boolean isAvailable() {
        return quantity > 0;
    }
    //Test

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        Book.quantity = quantity;
    }

    public String getTitle() {

        return bookName;
    }

    public void BookQueue (String userName){
        if (!queue.contains(userName)) {
            queue.add(userName);
            System.out.println(userName + " has been added to the queue for the book: " + bookName);
        } else {
            System.out.println(userName + " is already in the queue for this book.");
        }


    }
    public List<String> getQueue() {
        return queue;
    }
    public String getISBN() {
        return ISBN;
    }


    //VG
    public static void addNewBook(){
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        System.out.println("Please enter Book Title: ");
        bookName = scan.nextLine();
        System.out.println("Please enter Book Author: ");
        author = scan.nextLine();
        System.out.println("Please enter Book ISBN: ");
        ISBN = scan.nextLine();
        System.out.println("Please enter Book Publisher : ");
        publisher = scan.nextLine();
        System.out.println("Please enter Book Genre: ");
        genre = scan.nextLine();
        System.out.println("Please enter Book Year: ");
        publishYear = scan.nextInt();
        System.out.println("Please enter Book Quantity: ");
        quantity = scan.nextInt();

        Book book = new Book(bookName,author,ISBN,publisher,genre,publishYear,quantity);

        //Reads to file
        try(FileWriter fileWriter = new FileWriter("BookLog",true)){
            fileWriter.write(printInfo(book));
        }catch (IOException e){
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        System.out.println(bookName+" Added Successfully");
    }
}

