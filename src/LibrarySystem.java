import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LibrarySystem{


    private List<Book> bookList;
    private List<Loan> loanList;
    Scanner scan = new Scanner(System.in);
    private List<Member> memberList;


    public LibrarySystem() {
        super();
        this.memberList = new ArrayList<>();
        this.bookList = new ArrayList<>();
        loanList = null;
    }

    //TODO search a book method
    public void search() {

    }




    public void createAdminAccount() {
        ArrayList<String[]> adminNamePassword = new ArrayList<>();
        System.out.println("Admin name: ");
        String adminName = scan.nextLine();
        System.out.println("Admin password");
        String adminPassword = scan.next();

        try (FileWriter fileWriter = new FileWriter("FileNameAdmin.txt", true)) {
            fileWriter.write(adminName + " " + adminPassword + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa filen");
        }

        adminNamePassword.add(new String []{adminName,adminPassword});
    }



    public String createUserAccount() {
        System.out.println("enter your name: ");
        String name = scan.nextLine();

        System.out.println("Year of birth: ");
        int yearOfBirth = scan.nextInt();

        System.out.println("User name: ");
        scan.nextLine();
        String userName = scan.nextLine();

        System.out.println("Create a pincode 4 digits:");
        int pincode = scan.nextInt();

        try (FileWriter fileWriter = new FileWriter("FileNameUser.txt", true)) {
            fileWriter.write(name + "," + yearOfBirth + "," + userName + "," + pincode + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa filen");
        }

        memberList.add(new Member(name, yearOfBirth, userName, pincode));
        System.out.println("Account created successfully, Welcome " + name);
        return userName;

    }

    public List<Member> getMemberList() {
        return memberList;

    }

    public Member getMember(String userName) {
        for (Member member : memberList) {
            if (member.getUserName().equals(userName)) {
                return member;
            }
        }
        return null;
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

    public int getRandomBook(){
        Random random = new Random();
        return random.nextInt(50);
    }

}

