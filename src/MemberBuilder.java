public class MemberBuilder extends PersonBuilder<Member> {

    @Override
    public Member build() {
        return new Member(name, yearOfBirth, userName, pincode);
    }
}
