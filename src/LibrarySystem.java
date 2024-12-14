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

    public void createAdminAccount() {
        System.out.println("User name: ");
        String userName = scan.next();

        System.out.println("Create a pincode 4 digits:");
        int pinCode = scan.nextInt();

        try (FileWriter fileWriter = new FileWriter("FileNameAdmin.txt", true)) {
            fileWriter.write(userName + "," + pinCode + "," + null + "," + null + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa filen");
        }
        // borrar?
        System.out.println("Admin Account created successfully." + userName);
    }

    public void createUserAccount() {
        System.out.println("Enter your name: ");
        String name = scan.next();

        System.out.println("Year of birth: ");
        int yearOfBirth = scan.nextInt();
        scan.nextLine();

        System.out.println("User name: ");
        String userName = scan.nextLine();

        System.out.println("Create a pincode 4 digits:");
        int pinCode = scan.nextInt();
        scan.nextLine();

        try (FileWriter fileWriter = new FileWriter("FileNameUser.txt", true)) {
            //fileWriter.write(name + "," + yearOfBirth + "," + userName + "," + pinCode + "\n");
            fileWriter.write(userName + "," + pinCode + "," + userName + "," + yearOfBirth + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa filen" + e.getMessage());
        }

        memberList.add(new Member(name, yearOfBirth, userName, pinCode));
        System.out.println("Account created successfully.");
    }

    public void createManagerAccount() {
        String managerStatus = "Manager";
        System.out.println("Enter Manager Account: ");
        String managerAccount = scan.nextLine();

        System.out.println("Create a pincode 4 digits:");
        String managerPincode = scan.nextLine().trim();

        if (!managerPincode.matches("\\d{4}")) {
            System.out.println("Pincode must be exactly 4 digits.");
            return;
        }

        Map<String, String[]> mapManager = loadUsersFromFile("FileNameManager.txt");

        mapManager.put(managerAccount,new String[]{managerPincode,managerStatus});
        // TODO agregar en mis otros metodos
        if (mapManager.containsKey(managerAccount)) {
            System.out.println("Manager account already exists. Please choose another username.");
            return;
        }

        try (FileWriter fileWriter = new FileWriter("FileNameManager.txt", true)) {
            fileWriter.write( managerAccount + "," + managerPincode + "," + "\n");
        } catch (IOException e) {
            System.out.println("Kunde ej skapa Manager filen");
        }

        System.out.println("Manager Account " + managerAccount.trim() + " has been created successfully");
    }

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
    boolean userNameTrue = false;
    boolean admiNameTrue = false;

    public void loginSystem(){
        while (!userNameTrue && !admiNameTrue){
            System.out.println("1. Login with user\n2. Login with admin\n3. Sign up\n");
            String choiceString = scan.next();

            int choice = Integer.parseInt(choiceString);
            scan.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Enter user name: ");
                    String userLogin = scan.nextLine().trim();

                    System.out.println("Password: ");
                    String userPin = scan.nextLine();

                    if (users.containsKey(userLogin)) {
                        String[] datos = users.get(userLogin);
                        String savedPin = datos[0];
                        if (savedPin.equals(userPin)) {
                            //System.out.println("UserName and Password correct!");
                            userNameTrue = true;
                            break;
                        } else {
                            System.out.println("Something went wrong userName and Password");
                            continue;
                        }
                    }else {
                        System.out.println("User not found in FileNameUser");
                        System.out.println("Try to create new account.");
                        continue;
                    }
                case 2:
                    System.out.println("Enter user name: ");
                    String adminLogin = scan.nextLine().trim();

                    System.out.println("Password: ");
                    String adminPin = scan.nextLine();

                    if (admins.containsKey(adminLogin)) {
                        String[] datos = admins.get(adminLogin);
                        String savedPin = datos[0];
                        if (savedPin.equals(adminPin)) {
                            System.out.println("adminName and Password correct!");
                            admiNameTrue = true;
                            break;
                        } else {
                            System.out.println("Wrong username or password");
                            continue;
                        }
                    }else {
                        System.out.println("esta es la llave de admin " + adminLogin);
                        System.out.println(admins);
                        System.out.println(users);
                        System.out.println("Your are not Admin");
                        System.exit(0);
                    }
                case 3:
                    createUserOrAdmin();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
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
                //System.out.println("UserName and Password correct!");
            } else {
                System.out.println("Something went wrong userName and Password");
            }
        }else {
            System.out.println("User not found. ");
            askToCreateAccount();
        }
    }
    public void askToCreateAccount(){
        System.out.println("Would you like to create an account?\n1. YES\n2. NO");
        String choice = scan.nextLine();
        if (choice.equals("1")) {
            createUserAccount();
        }else {
            System.out.println("Thank you and wellcome back!");
            System.exit(0);
        }

    }
    //Dont touch this metod! It works!
    public void loginSystemAdmin(){
        System.out.println("Enter user name: ");
        String adminLogin = scan.nextLine().trim();

        System.out.println("Password: ");
        String adminPin = scan.nextLine();

        if (admins.containsKey(adminLogin)) {
            String[] datos = admins.get(adminLogin);
            String savedPin = datos[0];
            if (savedPin.equals(adminPin)) {
                System.out.println("adminName and Password correct!\n");
            } else {
                System.out.println("Wrong username or password");
            }
        }else {
            System.out.println("Your are not Admin");
            System.exit(0);
        }
    }
    //case 3 del metodo login en librarysystem.
    public void createUserOrAdmin(){
        System.out.println("1. Create user account\n2. Create Admin account\n3. Exit to login window\n");
        int choice;
        while (true){
            try{
                choice = scan.nextInt();
            }catch (InputMismatchException e ){
                System.out.println("Please enter 1, 2 or 3");
                scan.nextLine();
                break;
            }
            switch (choice){
                case 1:
                    createUserAccount();
                    break;
                case 2:
                    createAdminAccount();
                    break;
                case 3:
                    //loginSystem();
                    break;
                default:
                    System.out.println("OLA K ASE");
            }
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

