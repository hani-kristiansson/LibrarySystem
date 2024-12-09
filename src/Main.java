public class Main {


    public static void main(String[] args) {

        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.createAdminAccount();
        fetchBookData();
    }

    public static void fetchBookData() {
        LibraryAPI libraryAPI = new LibraryAPI();
        String apiURL = "https://www.googleapis.com/books/v1/volumes?q=isbn:0747532699";
        libraryAPI.connectToLibraryAPI(apiURL);
    }

}