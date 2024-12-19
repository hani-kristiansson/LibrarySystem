public abstract class PersonBuilder<T extends Person> {

    protected String name;
    protected int yearOfBirth;
    protected String userName;
    protected int pincode;

    public PersonBuilder<T> setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder<T> setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        return this;
    }

    public PersonBuilder<T> setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public PersonBuilder<T> setPincode(int pincode) {
        this.pincode = pincode;
        return this;
    }
    // Use the T / type as a placeholder
    public abstract T build();
}
