import java.time.LocalDate;
import java.util.List;

public class Loan {

    private String ISBN;
    private LocalDate startDate;
    private LocalDate endDate;
    private String userName;
    private String loanID;

    public Loan(String ISBN, String userName) {
        this.ISBN = ISBN;
        this.userName = userName;
    }

    public LocalDate calculateDueDate(LocalDate localDate) {
        return localDate.plusDays(30);
    }

    public void loanCreator (Member member, String ISBN, List<Book> bookList){
        for (Book book : bookList) {
            if (book.getISBN().equals(ISBN)) {
                if (book.isAvailable(book)) {
                    book.setQuantity(book.getQuantity() - 1);
                    startDate = LocalDate.now();
                    endDate = calculateDueDate(startDate);
                    loanID = ISBN + member.getUserName() + member.getNumberOfLoans();
                    System.out.println("The book has been loaned to " + userName + " with the loan ID: "
                            + loanID + " and the due date is: " + endDate);
                }
            }
        }

        //    return loanID + userName;
    }



    public String getLoanID() {
        return loanID;
    }
}
