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

        if (!book.isAvailable(book)) {
            return "The book is already out on a loan.";
        }

        return "The book '" + book.getTitle() + "' has been borrowed.";
    }

    public String returnBook(Book book) {
        if (book == null) {
            return "Book does not exist";
        }

        if (book.isAvailable(book)) {
            return "The book is already in the library system";
        }

        return "The book '" + book.getTitle() + "' has returned.";
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

    public void addBook(Book book) {
        bookList.add(book);
    }

    //LibrarySystem library = new LibrarySystem();
    //Book book = new Book("The Hobbit", "J.R.R. Tolkien", "1234567890", "?", "Fantasy", 2001);
        //library.addBook(book);

    //String loanResult = library.loanBook(book);
        //System.out.println(loanResult);

    // Försöker låna den igen
    //loanResult = library.loanBook(book);
        //System.out.println(loanResult);


    //String returnResult = library.returnBook(book);
        //System.out.println(returnResult);

    //returnResult = library.returnBook(book);
        //System.out.println(returnResult);

}

