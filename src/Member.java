import java.awt.*;

public class Member extends Person {
    private String memberID;
    private List numberOfLoans;

    public Member(String name, int yearOfBirth, String userName, int password) {
        super(name, yearOfBirth, userName, password);
    }
}
