import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;

public class Clinic {
    private String name;
    private Person current_user;
    private ArrayList<Role> roles;
    private ArrayList<Person> persons;
    private ArrayList<Appointment> appointments;

    public Clinic(String name) {
        this.setName(name);
        this.roles = new ArrayList<Role>();
        this.persons = new ArrayList<Person>();
        this.appointments = new ArrayList<Appointment>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRole(Role role) {
        if(
            role != null && 
            !this.roles.contains(role)
        ) {
            this.roles.add(role);
            role.setClinic(this);
        }
    }

    public void addPerson(Person person) {
        if (person != null && !this.persons.contains(person)) {
            this.persons.add(person);
            person.setClinic(this);
        }
    }

    public void addAppointment(Appointment appointment) {
        if(
            appointment != null &&
            !this.appointments.contains(appointment)
        ) this.appointments.add(appointment);
    }

    public void removeRole(Role role) {
        if (this.roles.remove(role)) {
            role = null;
        }
    }

    public void removePerson(Person person) {
        if (this.persons.remove(person)) {
            person = null;
        }
    }

    public void removeAppointment(Appointment appointment) {
        if (this.appointments.remove(appointment)) {
            appointment.getDoctor().getAppointments().remove(appointment);
            appointment.getPatient().getAppointments().remove(appointment);
            appointment = null;
        }
    }

    public String getName() {
        return this.name;
    }

    public Person getCurrentUser() {
        return this.current_user;
    }

    public boolean isUsernameValid(String username) {
        boolean valid = true;
        for(Role role : this.roles) {
            if (role.getPerson().getUsername().matches(username)) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    public boolean login(String username, String password) {
        for (Person person : this.persons) {
            if (
                person.getUsername().matches(username) && 
                person.verifyPassword(password)
            ) {
                current_user = person;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        this.current_user = null;
    }

    public Doctor[] getDoctors() {
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        for(int i=0; i<this.roles.size(); i++) {
            if(this.roles.get(i).getRole().matches("Doctor")) {
                doctors.add((Doctor)this.roles.get(i));
            }
        }
        return Arrays.copyOf(doctors.toArray(), doctors.size(), Doctor[].class);
    }

    public Patient[] getPatients() {
     
        ArrayList<Patient> patients = new ArrayList<Patient>();
        for(int i=0; i<this.roles.size(); i++) {
            if(this.roles.get(i).getRole().matches("Patient")) {
                patients.add((Patient)this.roles.get(i));
            }
        }
        return Arrays.copyOf(patients.toArray(), patients.size(), Patient[].class);
    }

    public Person[] getPersons() {
        return Arrays.copyOf(this.persons.toArray(), this.persons.size(), Person[].class);
    }

    public Appointment[] getAppointments() {
        return Arrays.copyOf(this.appointments.toArray(), this.appointments.size(), Appointment[].class);
    }

}
