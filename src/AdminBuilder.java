public class AdminBuilder extends PersonBuilder<Admin> {

    //Overrides Builder in PersonBuilder
    @Override
    public Admin build() {
        return new Admin(name, yearOfBirth, userName, pincode);
    }
}
