import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {

    private List<Member> memberList;
    private List<Book> bookList;
    private List<Loan> loanList;

    public LibrarySystem() {
        memberList = null;
        bookList = null;
        loanList = null;
    }

    //TODO search a book method
    public void search() {

    }

    //TODO borrow a book method
    public void borrowBook(){

    }

    //TODO return a book method
    public void returnBook(Loan loanID) {

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
}

