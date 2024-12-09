import java.util.List;

public class Main {


    public static void main(String[] args) {


        Book book = new Book("The Hobbit", "J.R.R. Tolkien", "1234567890", "?", "Fantasy", 2001, 3);


        LibrarySystem librarySystem = new LibrarySystem();

        librarySystem.createUserAccount();

        LibraryInfo libraryInfo = new LibraryInfo(librarySystem);


    }
}