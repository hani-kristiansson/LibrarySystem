import java.io.*;
import java.util.*;

public class LibrarySystem {


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

    //private String getValidName(){}
    //private String getValidYearOfBirth(){}
    //private String getValidUserName(){}
    //private String getValidxPin(){}

    public Map<String, String[]> loadUsersFromFile(String fileName) {
        Map<String, String[]> AllManagers = new HashMap<>();
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 2) {
                    String userName = parts[0];
                    String password = parts[1];
                    String userRealName = parts[2];
                    String userYear = parts[3];
                    AllManagers.put(userName, new String[]{password, userRealName, userYear});
                    //AllManagers.put(userName, new String[]{password});
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return AllManagers;
    }

    Map<String, String[]> users = loadUsersFromFile("FileNameUser.txt");
    Map<String, String[]> admins = loadUsersFromFile("FileNameAdmin.txt");


    public void createAdminAccount() {
        System.out.println("Admin user name: ");
        String adminName = scan.nextLine();
        if(admins.containsKey(adminName)){
            System.out.println("A user with this username already exists. Please try a different username.");
            return;
        }

        int pinCode;
        try {
            System.out.println("Create a pincode (4 digits):");
            pinCode = Integer.parseInt(scan.nextLine().trim());
            String pinCodeString = String.valueOf(pinCode);
            if (!pinCodeString.matches("\\d{4}")) {
                System.out.println("Pin must be exactly 4 digits.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric PIN.");
            return;
        }

        try (FileWriter fileWriter = new FileWriter("FileNameAdmin.txt", true)) {
            fileWriter.write(adminName + "," + pinCode + "," + null + "," + null + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa filen");
        }
        System.out.println("Admin Account created successfully." + adminName);
    }
    //Dont touch this metod! It works!
    public void createUserAccount() {
        System.out.println("Enter your name: ");
        String name = scan.next();

        System.out.println("Year of birth: ");
        int yearOfBirth = scan.nextInt();
        scan.nextLine();

        System.out.println("User name: ");
        String userName = scan.nextLine();
        if(users.containsKey(userName)){
            System.out.println("A user with this username already exists. Please try a different username.");
            return;
        }

        int pinCode;
        try {
            System.out.println("Create a pincode (4 digits):");
            pinCode = Integer.parseInt(scan.nextLine().trim());
            String pinCodeString = String.valueOf(pinCode);
            if (!pinCodeString.matches("\\d{4}")) {
                System.out.println("Pin must be exactly 4 digits.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric PIN.");
            return;
        }

        try (FileWriter fileWriter = new FileWriter("FileNameUser.txt", true)) {
            //fileWriter.write(name + "," + yearOfBirth + "," + userName + "," + pinCode + "\n");
            fileWriter.write(userName + "," + pinCode + "," + userName + "," + yearOfBirth + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa filen" + e.getMessage());
        }

        memberList.add(new Member(name, yearOfBirth, userName, pinCode));
        System.out.println("Account created successfully.");
    }
    //Dont touch this metod! It works!
    public void askToCreateAccount(){
        System.out.println("Would you like to create an account?\n1. YES\n2. NO");
        String choice = scan.nextLine();
        if (choice.equals("1")) {
            createUserAccount();
        }else {
            System.out.println("Thank you and welcome back!");
            System.exit(0);
        }
    }
    //Dont touch this metod! It works!
    public void loginSystemUser(){
        System.out.println("Enter user name: ");
        String userLogin = scan.nextLine().trim();

        System.out.println("Password: ");
        String userPin = scan.nextLine();

        if (users.containsKey(userLogin)) {
            String[] datos = users.get(userLogin);
            String savedPin = datos[0];
            if (savedPin.equals(userPin)) {
                System.out.println("UserName and Password correct!");
            } else {
                System.out.println("Something went wrong userName and Password");
            }
        }else {
            System.out.println("User not found. ");
            askToCreateAccount();
        }
    }
    //Dont touch this metod! It works!
    public void loginSystemAdmin(){
        System.out.println("Enter user name: ");
        String adminLogin = scan.nextLine().trim();

        //Es necesario usar int o esta bien con String?
        System.out.println("Password: ");
        String adminPin = scan.nextLine();

        if (admins.containsKey(adminLogin)) {
            String[] datos = admins.get(adminLogin);
            String savedPin = datos[0];
            String savedName = datos[1];
            if (savedPin.equals(adminPin)) {
                System.out.println("Welcome back " + savedName.toUpperCase().charAt(0) + "!");
            } else {
                System.out.println("Wrong password");
            }
        }else {
            System.out.println("Your are not Admin");
            System.exit(0);
        }
    }
    //Dont touch this metod! It works!
    int choice;
    public void createUserOrAdmin(){
        System.out.println("1. Create user account\n2. Create admin account\n3. Exit to login window\n");
        try{
            choice = scan.nextInt();
        }catch (InputMismatchException e ){
            System.out.println("Please enter 1, 2 or 3");
            scan.nextLine();
        }
        switch (choice){
            case 1:
                createUserAccount();
                break;
            case 2:
                createAdminAccount();
                break;
            case 3:
                break;
            default:
                System.out.println("OLA K ASE");
        }
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

