import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryInfo {

    LibraryInfo(LibrarySystem librarySystem){
        boolean loggedIn = false;
        boolean adminLoggedIn = false;
        boolean running = true;

        String libraryName = "Bookworm Library";
        String libraryLocation = "Tomtebodavägen 3A, 171 65 Solna";
        String libraryTelephoneNumber = "08-466 60 00";
        String LibraryOpenHours = "Mon-Fri 08-17";
        String userNameLOGGEDIN = "Guest";
        List<Book> bookList = Book.getBooks();
        List<Loan> loanList = new ArrayList<>();
        Loan.readLoanFromFile(loanList);
        List<Member> memberList = Member.getMembers();

        Scanner scanner = new Scanner(System.in);

        while (!loggedIn && !adminLoggedIn) {
            System.out.println("1. Login\n2. Sign up\n3.Admin Login\n");
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
                case 3:
                    System.out.println("Admin logged in");
                    adminLoggedIn = true;
                    running = false;

                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Welcome to " + libraryName + "!");

        while (running) {
            System.out.println("\n 1. Search/Check quantity \n2. Borrow a book \n3. Return a book \n4. Book Tips \n5. Mark a favorite book\n6 Queue for a book \n7. Exit");
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
                            Loan.saveLoansToFile(loanList);
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
                        String result = Loan.returnBook(loanID, loanList);
                        Loan.saveLoansToFile(loanList);
                        System.out.println(result);
                    }
                    break;

                case 4:
                    System.out.println(Book.printInfo(bookList.get(librarySystem.getRandomBook())));
                    break;
                case 5:
                    System.out.println("Enter the ISBN of the book you want to make your favorite:");
                    String favoriteISBN = scanner.nextLine().trim();
                    if (favoriteISBN.isEmpty()) {
                        System.out.println("You must enter a valid ISBN.");
                    } else {
                        boolean memberFound = false;
                        for (Member member : memberList) {
                            System.out.println("Checking member: " + member.getMemberID());
                            if (member.getMemberID().equals(userNameLOGGEDIN)) {
                                member.addFavoriteBookFromLog(favoriteISBN);//Puts books as favorites
                                memberFound = true;
                                break;
                            }

                        }
                        if (!memberFound) {
                            System.out.println("No logged in member found.");
                        }
                    }
                    break;
                case 6:
                    System.out.println("Enter the ISBN of the book you want to queue for:");
                    String queueISBN = scanner.nextLine().trim();

                    if (queueISBN.isEmpty()) {
                        System.out.println("You must enter a valid ISBN.");
                    } else {
                        boolean bookFound = false;
                        for (Book book : bookList) {
                            if (book.getISBN().trim().equals(queueISBN.trim())) {
                                if (!book.isAvailable()) {
                                    book.BookQueue(userNameLOGGEDIN);
                                } else {
                                    System.out.println("The book is available and does not require a queue.");
                                }
                                bookFound = true;
                                break;
                            }
                        }
                        if (!bookFound) {
                            System.out.println("No book found with ISBN: " + queueISBN);
                        }
                    }
                    break;

                case 7:
                    System.out.println("Thanks for your visit. Please come again.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid input. Please select a valid option.");
            }
        }
        while (adminLoggedIn) {

            System.out.println("1.Check Loans\n2.Check Members\n3.Delete Member\n4.Create new Admin Account\n5.Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            switch (choice){
                case 1:
                    //Check Loans
                break;
                case 2:
                    //Check members
                    System.out.println("Members List:\n" + memberList.toString());
                break;
                case 3:
                    //Delete Member
                    System.out.println("Whats the username: ");
                    String temp = scanner.nextLine();
                    librarySystem.deleteUser(temp,memberList);
                    librarySystem.updateUserFile(memberList);
                break;
                case 4:
                    librarySystem.createAdminAccount();
                    break;
                case 5:
                    //Exit
                    adminLoggedIn = false;
                break;
            }

        }


        System.out.println("Program terminated.");
    }
}
