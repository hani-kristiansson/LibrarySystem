import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem{

    private List<Member> memberList;
    private List<Book> bookList;
    private List<Loan> loanList;
    Scanner scan = new Scanner(System.in);
    Person person = new Person();

    public LibrarySystem() {
        super();
        memberList = null;
        bookList = null;
        loanList = null;
    }


    public void search() {

    }

    public void returnBook(Loan loanID) {

    }

    public void createAdminAccount() {
        ArrayList<String[]> adminNamePassword = new ArrayList<>();
        // Den ska vara admin.getname
        System.out.println("Admin name: ");
        String adminName = scan.nextLine();
        // Den ska vara admin.getpassword
        System.out.println("Admin password");
        String adminPassword = scan.next();

        try (FileWriter fileWriter = new FileWriter("FileNameAdmin.txt", true)) {
            fileWriter.write(adminName + " " + adminPassword + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa filen");
        }

        adminNamePassword.add(new String []{adminName,adminPassword});
    }

    public void createUserAccount() {
        ArrayList<String[]> userNamePassword = new ArrayList<>();
        // Den ska vara admin.getname
        System.out.println("User name: ");
        String adminName = scan.nextLine();
        // Den ska vara admin.getpassword
        System.out.println("User password");
        String adminPassword = scan.next();

        try (FileWriter fileWriter = new FileWriter("FileNameUser.txt", true)) {
            fileWriter.write(adminName + " " + adminPassword + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa filen");
        }

        userNamePassword.add(new String []{adminName,adminPassword});


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

