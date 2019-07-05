public class Availability {
    private String day;
    private String timeIn;
    private String timeOut;
    private Doctor doctor;

    public Availability(Doctor doctor, String day, String timeIn, String timeOut) {
        this.setDay(day);
        this.setDoctor(doctor);
        this.setTimeIn(timeIn);
        this.setTimeOut(timeOut);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public String getDay() {
        return this.day;
    }

    public String getTimeIn() {
        return this.timeIn;
    }

    public String getTimeOut() {
        return this.timeOut;
    }

}
