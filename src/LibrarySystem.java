import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {

    private List<Member> memberList;
    private List<Book> bookList;
    private List<Loan> loanList;

    public LibrarySystem() {
        memberList = null;
        this.bookList = new ArrayList<>();
        loanList = null;
    }

    //TODO search a book method
    public void search() {

    }


    public String loanBook(Book book) {
        if (book == null) {
            return "Book does not exist";
        }

        if (!book.isAvailable()) {
            return "The book is already out on a loan.";
        }

        book.setAvailable(false);
        return "The book '" + book.getTitle() + "' has been borrowed.";
    }

    public String returnBook(Book book) {
        if (book == null) {
            return "Book does not exist";
        }

        if (book.isAvailable()) {
            return "The book is already in the library system";
        }

        book.setAvailable(true);
        return "The book '" + book.getTitle() + "' has returned.";
    }
   
   

    public void createAdminAccount() {
        Scanner scan = new Scanner(System.in);
        // Den ska vara admin.getname
        System.out.println("Admin name: ");
        String adminName = scan.nextLine();
        // Den ska vara admin.getpassword
        System.out.println("Admin password");
        int adminPassword = scan.nextInt();

        try (FileWriter fileWriter = new FileWriter("FileNameAdmin.txt", true)) {
            fileWriter.write(adminName + " " + adminPassword + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa filen");
        }


    }

    public void createUserAccount(String username) {
        System.out.println();


    }

    public List<Member> getMemberList() {
        return memberList;

    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void addBook(Book book) {
        bookList.add(book);
    }
}

