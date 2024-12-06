public class Person {
    private String name;
    private int yearOfBirth;
    private String userName;
    private int password;

    public Person(String name, int yearOfBirth, String userName, int password){
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.userName = userName;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setYearOfBirth(int yearOfBirth){
        this.yearOfBirth = yearOfBirth;
    }
    public int getYearOfBirth(){
        return yearOfBirth;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }
    public void setPassword(int password){
        this.password = password;
    }
    public int getPassword(){
        return password;
    }
}
