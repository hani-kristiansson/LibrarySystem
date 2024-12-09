import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryInfo {

    private String libraryName = "Bookworm library";
    private String libraryLocation = "Tomtebodavägen 3A, 171 65 Solna";
    private String libraryTelephoneNumber = "08-466 60 00";
    private String LibraryOpenHours = "Mon-Fre 08-17";
    private String welcomeMessage = "Welcome to " + libraryName + "!" + "Please select menu.";

    List<Book> bookList = new ArrayList<Book>();

    public LibraryInfo(LibrarySystem librarySystem) {
        System.out.println(welcomeMessage);
        System.out.println("\n 1. Find a book \n 2. Borrow a book \n 3. Return a book \n 4. Exit \n \n"
                + libraryName + " " + libraryLocation + " " + libraryTelephoneNumber + " " + LibraryOpenHours);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter search term: ");
                    String userInput = scanner.nextLine();

                    break;

                case 2:
                    librarySystem.borrowBook();
                    break;

                case 3:
                    Loan loan = new Loan("1234", "firstUser");
                    librarySystem.returnBook(loan);
                    break;

                case 4:
                    System.out.println("Thanks for your visit. Please come again.");
                    break;

                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
    }
}
