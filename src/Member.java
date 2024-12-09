import java.awt.*;

public class Member extends Person {
    private String memberID;
    private int numberOfLoans = 0;

    public Member(String name, int yearOfBirth, String userName, int password) {
        super(name, yearOfBirth, userName, password);
        this.numberOfLoans = 0;
        this.memberID = userName;
    }
    public Member(String memberID, int numberOfLoans){
        this.memberID=memberID;
        this.numberOfLoans=numberOfLoans;
    }

    public String getMemberID() {
        return memberID;
    }

    public int getNumberOfLoans() {
        return numberOfLoans;
    }

    @Override
    public String toString() {
        return memberID;
    }
}
