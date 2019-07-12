import java.util.*;

public class App {
	static Scanner sc = new Scanner(System.in);
	static Clinic clinic = new Clinic("Mehar's....");

	public static void main(String[] args) {
		System.out.println("Welcome to " + clinic.getName() + " Clinic");
		if (clinic.getCurrentUser() != null) {
			mainMenu();
		} else {
			authMenu();
		}
	}

	public static void mainMenu() {
		if (clinic.getCurrentUser() != null) {
			boolean isDoctor = isOfRole(clinic.getCurrentUser(), "Doctor");
			boolean isPatient = isOfRole(clinic.getCurrentUser(), "Patient");;
			
			int n = 2;
			if (isDoctor) n++;
			if (isPatient) n++;
			int choice;
			do {
				if (isDoctor && isPatient) {
					System.out.println("1. Doctor's Portal");
					System.out.println("2. Patient's Portal");
					System.out.println("3. Logout");
					System.out.println("4. Exit");
				} else if (isDoctor) {
					System.out.println("1. Doctor's Portal");
					System.out.println("2. Logout");
					System.out.println("3. Exit");
				} else {
					System.out.println("1. Patient's Portal");
					System.out.println("2. Logout");
					System.out.println("3. Exit");
				}
				choice = sc.nextInt();
				sc.nextLine();
			} while (choice < 1  || choice > n);
			if (isDoctor && isPatient) {
				if (choice == 4)  exit();
				else if (choice == 1) doctorPortal();
				else if (choice == 2) patientPortal();
				else clinic.logout();
			} else {
				if (choice == 3)  exit();
				else if (choice == 1) {
					if (isDoctor) {
						doctorPortal();
					} else if (isPatient) {
						patientPortal();
					}
				}
				else clinic.logout();
			}
		} else {
			authMenu();
		}
	}

	public static void authMenu() {
		if (clinic.getCurrentUser() == null){
			int choice;
			do {
				System.out.println("1. Login");
				System.out.println("2. Register");
				System.out.println("3. Exit");
				choice = sc.nextInt();
				sc.nextLine(); // dump the line break
			} while(choice < 1 || choice > 3);
			if (choice == 3) {
				exit();
			} else if (choice == 1) {
				login();
			} else {
				register();
			}
			authMenu();
		} else {
			mainMenu();
		}
	}

	public static void register() {
		char response;
		do {
			System.out.print("Are you already associated with " + clinic.getName() + " (y/N): ");
			response = sc.nextLine().toLowerCase().charAt(0);
		} while (response != 'y' && response != 'n');
		Role role;
		int role_id;
		Person person;
		if (response == 'y') {
			login();
		} else {
			System.out.println("Please enter your details.");
			String name, cnic, uname, pwd;
			do {
				System.out.print("Enter Name (minimun 3 chars): ");
				name = sc.nextLine();
			} while (name.length() < 3);
			do {
				System.out.print("Enter Cnic (without dashes): ");
				cnic = sc.nextLine();
			} while (cnic.length() != 13);
			do {
				System.out.print("Enter Username (minimum 3): ");
				uname = sc.nextLine();
			} while (uname.length() < 3 || !clinic.isUsernameValid(uname));
			do {
				System.out.print("Enter Password (minimum 8): ");
				pwd = sc.nextLine();
			} while (pwd.length() < 8);
			person = new Person(clinic, cnic, name, uname, pwd);
			clinic.login(uname, pwd);
		}
		if (clinic.getCurrentUser() != null){
			do {
				System.out.println("Choose a Role");
				System.out.println("1. Doctor");
				System.out.println("2. Patient");
				role_id = sc.nextInt();
				sc.nextLine();
			} while (role_id < 1 || role_id > 2);
			if (role_id == 1) {
				String specialization;
				do {
					System.out.print("Enter your specialization: ");
					specialization = sc.nextLine();
				} while (specialization.length() == 0);
				role = new Doctor(clinic, clinic.getCurrentUser(), specialization);
			} else {
				String disease;
				do {
					System.out.print("Enter your disease: ");
					disease = sc.nextLine();
				} while (disease.length() == 0);
				role = new Patient(clinic, clinic.getCurrentUser(), disease);
			}
		} else {
			System.out.println("Sorry Something went wrong.");
		}
	}

	public static void login() {
		System.out.println("Please enter login details");
		String username, password;
		do {
			System.out.print("Enter username: ");
			username = sc.nextLine();
		} while (username.length() == 0);
		do {
			System.out.print("Enter password: ");
			password = sc.nextLine();
		} while (password.length() == 0);
		if (clinic.login(username, password)) {
			System.out.println("You're Logged In as: " + clinic.getCurrentUser().getName());
		} else {
			System.out.println("Invalid Credentials were provided.");
		}
	}

	public static boolean isOfRole(Person person, String r) {
		for (Role role : clinic.getCurrentUser().getRoles()) {
			if (role.getRole().matches(r)) return true;
		}
		return false;
	}

	public static Role getRole(Person person, String r) {
		for (Role role : clinic.getCurrentUser().getRoles()) {
			if (role.getRole().matches(r))
				return role.getRole();
		}
		return null;
	}

	public static String getDayFromUser() {
		int d;
		do {
			System.out.println("1. Monday");
			System.out.println("2. Tuesday");
			System.out.println("3. Wednesday");
			System.out.println("4. Thursday");
			System.out.println("5. Friday");
			System.out.println("6. Saturday");
			System.out.println("7. Sunday");
			d = sc.nextInt();
			sc.nextLine();
		} while (d < 1 || d > 7);
		if (d == 1)
			day = "Monday";
		else if (d == 2)
			day = "Tuesday";
		else if (d == 3)
			day = "Wednesday";
		else if (d == 4)
			day = "Thursday";
		else if (d == 5)
			day = "Friday";
		else if (d == 6)
			day = "Saturday";
		else
			day = "Sunday";
		return day;
	}

	public static void doctorPortal() {
		if (clinic.getCurrentUser() != null && isOfRole(clinic.getCurrentUser(), "Doctor")) {
			int choice;
			do {
				System.out.println("1. View Availabilities");
				System.out.println("2. Add Availability");
				System.out.println("3. Update Availability"); 
				System.out.println("4. Remove Availability"); 
				System.out.println("5. View Appointments");
				System.out.println("6. Postpone Appointment");
				System.out.println("7. Cancel Appointment");
				System.out.println("8. View Prescription");
				System.out.println("9. Add Prescription");
				System.out.println("10. Update Prescription");
				System.out.println("11. Remove Prescription"); 
				System.out.println("12. Back"); 
				choice = sc.nextInt();
				sc.nextLine();
			} while (choice < 1 || choice > 12);
			if (choice == 12) {
				mainMenu();
			} else if (choice == 1) {
				viewAvailabilities();
			} else if (choice == 2) {
				addAvailability();
			} else if (choice == 3) {
				updateAvailability();
			} else if (choice == 4) {
				removeAvailability();
			} else if (choice == 5) {
				viewAppointments();
			} else if (choice == 6) {
				postponeAppointment();
			} else if (choice == 7) {
				cancelAppointment();
			} else if (choice == 8) {
				viewPrescription();
			} else if (choice == 9) {
				addPrescription();
			} else if (choice == 10) {
				updatePrescription();
			} else {
				removePrescription();
			}
			doctorPortal();
		} else {
			authMenu();
		}
	}

	public static void patientPortal() {
		if (clinic.getCurrentUser() != null && isOfRole(clinic.getCurrentUser(), "Patient")) {

		} else {
			authMenu();
		}
	}

	public static void viewAvailabilities() {
		if (clinic.getCurrentUser() != null && getRole(clinic.getCurrentUser(), "Doctor") != null) {
			Doctor doctor = (Doctor) getRole(clinic.getCurrentUser(), "Doctor");
			int i=1;
			for (Availability availability : doctor.getAvailabilities()) {
				System.out.println("Id: " + (i++));
				System.out.println("Day: " + availability.getDay());
				System.out.println("TimeIn: " + availability.getTimeIn());
				System.out.println("TimeOut: " + availability.getTimeOut());
				System.out.println();
			}
			doctorPortal();
		} else {
			authMenu();
		}
	}

	public static void addAvailability() {
		if (clinic.getCurrentUser() != null && getRole(clinic.getCurrentUser(), "Doctor") != null) {
			Doctor doctor = (Doctor) getRole(clinic.getCurrentUser(), "Doctor");
			String day = getDayFromUser(), timeIn, timeOut;
			do {
				System.out.println("Enter time in and out (can never be same)");
				do {
					System.out.print("Enter time in (24 hr format => 01: 00): ");
					timeIn = sc.nextLine();
				} while(timeIn.length() != 5);
				do {
					System.out.print("Enter time out (24 hr format => 01:00): ");
					timeOut = sc.nextLine();
				} while (timeOut.length() != 5);
			} while (timeIn.matches(timeOut));
			Availability availability = new Availability(doctor, day, timeIn, timeOut);
			doctorPortal();
		} else {
			authMenu();
		}
	}

	public static void updateAvailability() {
		if (clinic.getCurrentUser() != null && getRole(clinic.getCurrentUser(), "Doctor") != null) {
			viewAppointments();
			Doctor doctor = (Doctor) getRole(clinic.getCurrentUser(), "Doctor");
			int id;
			do {
				System.out.print("Enter appointment id: ");
				id = sc.nextInt();
				sc.nextLine();
			} while (id < 1 || id > doctor.getAvailabilities().length);
			Availability availability = doctor.getAvailabilities()[id - 1];
			System.out.print("Do you wanna chage Day? (y/N): ");
			char d = sc.nextLine().toLowerCase().charAt(0);
			if (d == 'y') {
				availability.setDay(getDayFromUser());
			} 
			System.out.print("Do you wanna chage Time In? (y/N): ");
			d = sc.nextLine().toLowerCase().charAt(0);
			if (d == 'y') {
				String timeIn;
				do {
					do {
						System.out.println("Enter timeIn (24 hr format => 01:00)");
						timeIn = sc.nextLine();
					} while(timeIn.length() != 5);
					availability.setTimeIn(timeIn);
				} while(availability.getTimeIn() - availability.getTimeOut() <= 0);
			} 
			System.out.print("Do you wanna chage Time Out? (y/N): ");
			d = sc.nextLine().toLowerCase().charAt(0);
			if (d == 'y') {
				String timeOut;
				do {
					do {
						System.out.println("Enter timeOut (24 hr format => 01:00)");
						timeOut = sc.nextLine();
					} while (timeOut.length() != 5);vailability.setTimeOut(timeOut);
				} while (availability.getTimeIn() - availability.getTimeOut() <= 0);
			}
		} else {
			authMenu();
		}
	}

	public static void removeAvailability() {
		
	}

	public static void viewAppointments() {

	}

	public static void postponeAppointment() {

	}
	

	public static void cancelAppointment() {

	}

	public static void viewPrescription() {

	}

	public static void addPrescription() {

	}

	public static void updatePrescription() {

	}

	public static void removePrescription() {

	}

	public static void exit() {
		System.out.print("Bye.");
		System.exit(0);
	}

}