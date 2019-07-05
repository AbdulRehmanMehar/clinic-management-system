public class Patient extends Role {
    private String disease;

    public Patient(Person person, String disease) {
        super(person);
        this.setDisease(disease);
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDisease() {
        return this.disease;
    }

    public String getRole() {
        return "Patient";
    }

}
