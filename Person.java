public class Person {
    private String cnic;
    private String name;
    private String username;
    private String password;

    public Person(String cnic, String name, String username, String password) {
        this.setCnic(cnic);
        this.setName(name);
        this.setUsername(username);
        this.setPassword(password);
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnic() {
        return this.cnic;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
