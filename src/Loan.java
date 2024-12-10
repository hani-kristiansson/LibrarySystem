import java.time.LocalDate;
import java.util.List;

public class Loan {

    private String ISBN;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userName;
    protected String loanID;

    public Loan(String ISBN, String userName) {
        this.ISBN = ISBN;
        this.userName = userName;
        this.loanID = ISBN + userName + LocalDate.now();
    }


    public LocalDate calculateDueDate(LocalDate localDate) {
        return localDate.plusDays(30);
    }

    public String loanCreator(String userName, String ISBN, List<Book> bookList) {
        Loan loan = new Loan(ISBN, userName);

        for (Book book : bookList) {
            if (book.getISBN().trim().equals(ISBN.trim()) && book.isAvailable()) {
                book.setQuantity(book.getQuantity() - 1);
                loanID = ISBN + userName + LocalDate.now();
                startDate = LocalDate.now();
                endDate = calculateDueDate(startDate);
                return "Loan created successfully! Loan ID: " + this.loanID + ", Due Date: " + this.endDate;

            }
        }

        return "The book with ISBN " + ISBN + " is not available or does not exist.";
    }






    public String returnBook(String loanID, List<Loan> loanList) {
        if (loanID == null || loanID.isEmpty()) {
            return "Loan does not exist";
        }
        for (Loan loan : loanList) {
            if (loan.getLoanID().trim().equalsIgnoreCase(loanID.trim())) {
                loanList.remove(loan); // Ta bort lånet från listan
                return "The book with Loan ID '" + loanID + "' has been returned.";
            }
        }


        return "No loan found with ID '" + loanID + "'.";
    }





    public String getLoanID() {
        return loanID;
    }
}
