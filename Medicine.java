public class Medicine {
    private String name;
    private String dosage;
    private Appointment appointment;

    public Medicine(Appointment appointment, String name, String dosage) {

    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public String getName() {
        return this.name;
    }

    public String getDosage() {
        return this.dosage;
    }

}
