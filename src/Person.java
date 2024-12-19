public class Person {
    private String name;
    private int yearOfBirth;
    private String userName;
    private int pincode;

    public Person(String name, int yearOfBirth, String userName, int pincode) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.userName = userName;
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public int getPincode() {
        return pincode;
    }
}
