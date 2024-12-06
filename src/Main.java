public class Main {

    public Main() {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.createAdminAccount();

        LibraryInfo libraryInfo = new LibraryInfo(librarySystem);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}