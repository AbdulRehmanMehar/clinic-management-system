public class Patient extends Role {
    private String disease;

    public Patient(Clinic clinic, Person person, String disease) {
        super(clinic, person);
        this.setClinic(clinic);
        this.setDisease(disease);
        clinic.addPatient(this);
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void addAppointment(Appointment appointment) {
        if (!super.appointments.contains(appointment))
            super.appointments.add(appointment);
    }

    public String getDisease() {
        return this.disease;
    }

    public Appointment[] getAppointments() {
        return (Appointment[]) super.appointments.toArray();
    }

    public String getRole() {
        return "Patient";
    }

}
