import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryInfo {

    private final String libraryName = "Bookworm library";
    private final String libraryLocation = "Tomtebodavägen 3A, 171 65 Solna";
    private final String libraryTelephoneNumber = "08-466 60 00";
    private final String LibraryOpenHours = "Mon-Fre 08-17";
    private final String welcomeMessage = "Welcome to " + libraryName + "!" + " Please select menu.";
    private final List<Loan> loanList = new ArrayList<>();



    List<Book> bookList;

    public LibraryInfo(LibrarySystem librarySystem) {
        bookList = Book.getBooks();
        System.out.println(welcomeMessage);
        System.out.println("\n 1. Find a book \n 2. Borrow a book \n 3. Return a book \n 4. Exit \n \n"
                + libraryName + " " + libraryLocation + " " + libraryTelephoneNumber + " " + LibraryOpenHours);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter search term: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String userInput = scanner2.nextLine().trim();
                    if (userInput != null && !userInput.isEmpty()) {
                        Book.searchBooks(bookList, userInput);
                    }
                    break;

                case 2:
                    System.out.println("Choose your book by entering the ISBN:");
                    Scanner scanner3 = new Scanner(System.in);
                    userInput = scanner3.nextLine().trim();
                    if (userInput.isEmpty()) {
                        System.out.println("You must choose a valid book.");
                    } else {
                        Loan loan = new Loan(userInput, "someUserName");
                        String result = loan.loanBook(userInput, bookList, "someUserName");
                        if(result.startsWith("Loan created successfully!")){
                            loanList.add(loan);

                        }
                        System.out.println(result);

                    }
                    break;

                case 3:
                    System.out.println("Enter the Loan ID to return the book:");
                    Scanner scanner4 = new Scanner(System.in);
                    String loanID = scanner4.nextLine().trim();

                    if (loanID.isEmpty()) {
                        System.out.println("You must enter a valid Loan ID.");
                    } else {
                        Loan loan = new Loan("", "");
                        String result = loan.returnBook(loanID, loanList); // Antag att `loanList` är en lista över lån
                        System.out.println(result);
                    }
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
