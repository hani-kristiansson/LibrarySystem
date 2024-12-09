import java.util.List;

public class Main {


    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();

        librarySystem.createUserAccount();

        LibraryInfo libraryInfo = new LibraryInfo(librarySystem);


    }
}