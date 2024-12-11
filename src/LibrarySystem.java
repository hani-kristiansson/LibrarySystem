import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LibrarySystem {


    private List<Book> bookList;
    private List<Loan> loanList;
    Scanner scan = new Scanner(System.in);
    private List<Member> memberList;
    private List<Admin> adminList;


    public LibrarySystem() {
        super();
        this.memberList = Member.getMembers();
        this.adminList = Admin.getAdmins();
        this.bookList = new ArrayList<>();
        loanList = null;
    }


    public boolean login(String userName, boolean isAdmin) {
        String username = userName;
        int pincode = 0;
        List <? extends Person> personlist = isAdmin ? adminList : memberList;

        for (Member member : memberList) {
            username = username.trim();
            if (member.getUserName().equalsIgnoreCase(username)) {
                System.out.println("Enter your pincode: ");
                pincode = scan.nextInt();
                if (member.getPassword() == pincode) {
                    System.out.println("Hello " + member.getName());
                    return true;
                } else {
                    System.out.println("Invalid pin code try again");
                    return false;
                }

            }
            return false;
        }
        System.out.println("Login failed, try again");
        return false;
    }


    public String createUserAccount() {

        System.out.println("Enter your name: ");
        String name = scan.nextLine();

        System.out.println("Year of birth: ");
        int yearOfBirth = scan.nextInt();
        scan.nextLine();

        System.out.println("User name: ");
        String userName = scan.nextLine();

        System.out.println("Create a pincode 4 digits:");
        int pincode = scan.nextInt();

        try (FileWriter fileWriter = new FileWriter("FileNameUser.txt", true)) {
            fileWriter.write(name + "," + yearOfBirth + "," + userName + "," + pincode + "\n");
        } catch (IOException e) {
            System.out.println("Could not create the file");
        }

        memberList.add(new Member(name, yearOfBirth, userName, pincode));
        System.out.println("`\nGreetings " + name);
        return userName;
    }



    public String createAdminAccount() {
        System.out.println("Enter your name: ");
        String name = scan.nextLine();

        System.out.println("Year of birth: ");
        int yearOfBirth = scan.nextInt();
        scan.nextLine();

        System.out.println("User name: ");
        String userName = scan.nextLine();

        System.out.println("Create a pincode 4 digits:");
        int pincode = scan.nextInt();

        try (FileWriter fileWriter = new FileWriter("FileNameAdmin.txt", true)) {
            fileWriter.write( name + ","+ yearOfBirth + "," + userName + "," + pincode + "\n");
        } catch (IOException e) {
            System.out.println("Could not create the file");
        }

        adminList.add(new Admin(name, yearOfBirth, userName, pincode));

        System.out.println("Admin " + name + " Salutations!");
        return userName;
    }

    public List<Member> getMemberList() {
        return memberList;

    }

    public Member getMember(String userName) {
        for (Member member : memberList) {
            if (member.getUserName().equalsIgnoreCase(userName)) {
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

    public int getRandomBook() {
        Random random = new Random();
        return random.nextInt(50);
    }

    public void updateUserFile(List<Member> memberListIn) {

        System.out.println("members");
        for (Member member : memberListIn) {
            System.out.println(member);
        }

        try (FileWriter fileWriter = new FileWriter("FileNameUser.txt", false)) { // 'false' overwrites the file

            for (Member member : memberListIn) {
                fileWriter.write(member.getName() + "," + member.getYearOfBirth() + "," + member.getUserName() + "," + member.getPassword() + "\n");
            }

            System.out.println("File updated successfully");
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file: " + e.getMessage());
        }
    }


    public void deleteUser(String userName, List<Member> memberListIn) {
        Iterator<Member> iterator = memberListIn.iterator(); // Create an iterator for the list
        while (iterator.hasNext()) {
            Member member = iterator.next(); // Get the next member
            System.out.println("Member: " + member.getUserName());
            if (member.getUserName().equalsIgnoreCase(userName)) {
                iterator.remove(); // Safely remove the current element
                System.out.println("User " + userName + " deleted successfully");
            }
        }
    }
}

