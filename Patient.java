import java.util.Arrays;

public class Patient extends Role {
    private String disease;

    public Patient(Clinic clinic, Person person, String disease) {
        super(clinic, person);
        this.setDisease(disease);
        clinic.addPatient(this);
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
    
    @Override
    public void addAppointment(Appointment appointment) {
        if (!super.appointments.contains(appointment))
            super.appointments.add(appointment);
    }

    @Override
    public void cancelAppointment(Appointment appointment) {
        if (super.appointments.remove(appointment)) {
            this.getClinic().removeAppointment(appointment);
        }
    }

    public String getDisease() {
        return this.disease;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

}
