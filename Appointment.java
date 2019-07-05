public class Appointment {
    private String day;
    private Doctor doctor;
    private String timeEnd;
    private String timeStart;
    private Patient patient;

    public Appointment(Doctor doctor, Person person, String day, String timeStart, String timeEnd) {
        this.setDoctor(doctor);
        this.setPatient(patient);
        this.setDay(day);
        this.setTimeStart(timeStart);
        this.setTimeEnd(timeEnd);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public Patient getPatient() {
        return this.pateient;
    }

    public String getDay() {
        return this.day;
    }

    public String getTimeStart() {
        return this.timeStart;
    }

    public String getTimeEnd() {
        return this.timeEnd;
    }


}
