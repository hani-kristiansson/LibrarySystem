import java.time.LocalDate;

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

    public void loanCreator (String userName, String ISBN){
        //    return loanID + userName;
    }

    public String getLoanID() {
        return loanID;
    }
}
