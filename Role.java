public abstract class Role {
    private Person person;

    public Role(Person person) {
        this.person = person;
    }

    public getPerson() {
        return this.person;
    }

    public abstract String getRole();

}
