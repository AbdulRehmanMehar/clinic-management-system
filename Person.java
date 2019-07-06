public class Person {
    private String cnic;
    private String name;

    public Person(String cnic, String name) {
        this.setCnic(cnic);
        this.setName(name);
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return this.cnic;
    }

    public String getName() {
        return this.name;
    }
}
