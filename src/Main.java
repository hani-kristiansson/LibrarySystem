import java.util.List;

public class Main {


    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.createAdminAccount();

        LibraryInfo libraryInfo = new LibraryInfo(librarySystem);


    }
}