import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryInfo {

    LibraryInfo(LibrarySystem librarySystem){
        boolean loggedIn = false;
        String libraryName = "Bookworm Library";
        String libraryLocation = "Tomtebodavägen 3A, 171 65 Solna";
        String libraryTelephoneNumber = "08-466 60 00";
        String LibraryOpenHours = "Mon-Fri 08-17";
        String userNameLOGGEDIN = "Guest";
        List<Book> bookList = Book.getBooks();
        List<Loan> loanList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (!loggedIn) {
            System.out.println("Login or Sign up");
            System.out.println("1. Login\n2. Sign up");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.println("Enter username: ");
                    userNameLOGGEDIN = scanner.nextLine().trim();
                    loggedIn = true;
                    break;
                case 2:
                    System.out.println("Account created successfully! Please log in.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Welcome to " + libraryName + "!");

        boolean running = true;
        while (running) {
            System.out.println("\n 1. Search/Check quantity \n2. Borrow a book \n3. Return a book \n4. Book Tips \n5. Exit");
            System.out.println(libraryName + " " + libraryLocation + " " + libraryTelephoneNumber + " " + LibraryOpenHours);

            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.println("Enter search term: ");
                    String ISBNInput = scanner.nextLine().trim();
                    if (!ISBNInput.isEmpty()) {
                        Book.searchBooks(bookList, ISBNInput);
                    } else {
                        System.out.println("Invalid input. Please enter a valid search term.");
                    }
                    break;

                case 2:
                    System.out.println("Choose your book by entering the ISBN:");
                    ISBNInput = scanner.nextLine().trim();
                    if (ISBNInput.isEmpty()) {
                        System.out.println("You must choose a valid book.");
                    } else {
                        Loan loan = new Loan(ISBNInput, userNameLOGGEDIN);
                        String result = loan.loanCreator(userNameLOGGEDIN, ISBNInput, bookList);
                        if (result.startsWith("Loan created successfully!")) {
                            loanList.add(loan);
                        }
                        System.out.println(result);
                    }
                    break;

                case 3:
                    System.out.println("Enter the Loan ID to return the book:");
                    String loanID = scanner.nextLine().trim();
                    if (loanID.isEmpty()) {
                        System.out.println("You must enter a valid Loan ID.");
                    } else {
                        Loan loan = new Loan("", "");
                        String result = loan.returnBook(loanID, loanList);
                        System.out.println(result);
                    }
                    break;

                case 4:

                    System.out.println(Book.printInfo(bookList.get(librarySystem.getRandomBook())));


                    break;
                case 5:
                    System.out.println("Thanks for your visit. Please come again.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid input. Please select a valid option.");
            }
        }

        System.out.println("Program terminated.");
    }
}
