import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Member extends Person {
    private String userName;
    private int numberOfLoans = 0;

    public Member(String name, int yearOfBirth, String userName, int password) {
        super(name, yearOfBirth, userName, password);
        this.numberOfLoans = 0;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumberOfLoans() {
        return numberOfLoans;
    }

    @Override
    public String toString() {
        return userName;
    }


    public static List<Member> getMembers() {
        String readInName,readInYearOfBirth,readInUserName,readInPassword;
        int readInYearOfBirthInt,readInPasswordInt;
        int i = 0;
        List<Member> memberList = new ArrayList<Member>();

        //Uses BufferedReader to read from file
        try (BufferedReader br = new BufferedReader(new FileReader("FileNameUser.txt"))) {
            // Checks the line is not empty
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into parts based on the comma separator
                String[] parts = line.split(",");
                if (parts.length == 4) { // Ensure the line has exactly 4 components
                    // Parse values from the line
                    readInName = parts[0].trim(); // Trim to remove any extra spaces
                    readInYearOfBirth = parts[1].trim();
                    readInUserName = parts[2].trim();
                    readInPassword = parts[3].trim();

                    // Convert numerical fields to integers
                    readInYearOfBirthInt = Integer.parseInt(readInYearOfBirth);
                    readInPasswordInt = Integer.parseInt(readInPassword);

                    // Create a Member object and add it to the list
                    Member member = new Member(readInName, readInYearOfBirthInt, readInUserName, readInPasswordInt);
                    memberList.add(member);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        }catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found");
        }
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Something went wrong\n Please Try again");

        }
        return memberList;
    }
}
