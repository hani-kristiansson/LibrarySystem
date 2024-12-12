public class Person {
    private String name;
    private int yearOfBirth;
    private String userName;
    private int password;

    public Person(String name, int yearOfBirth, String userName, int password) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.userName = userName;
        this.password = password;
    }

    public Person() {
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
