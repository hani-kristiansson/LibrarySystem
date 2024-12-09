import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static LibrarySystem librarySystem = new LibrarySystem();
    static Admin admin = new Admin();

    public static void main(String[] args) {
        System.out.println("What would you like to create? \n " +
                "1) Admin account \n " +
                "2) User account");
        int optionA = scan.nextInt();
        if (optionA == 1){
            librarySystem.createAdminAccount();
            System.out.println("Admin account has been created successfully!");
            System.out.println("What would you like to do? \n " +
                    "1) Remove member \n " +
                    "2) Logout.");
            int optionB = scan.nextInt();
            if(optionB == 1) {
                admin.RemoveMember("FileNameUser.txt");
            } else if (optionB == 2) {
                System.out.println("You Logout");
            }
        } else if (optionA == 2) {
            librarySystem.createUserAccount();
            System.out.println("User account has been created successfully!");
        }else {
            System.out.println("Choose 1) for create admin account or 2) for create user account.");
        }





    }
}