import java.util.*;

public class App {
	static Scanner sc = new Scanner(System.in);
	static Clinic clinic = new Clinic("Mehar's.... Clinic");

	public static void main(String[] args) {
		System.out.println("Welcome to " + clinic.getName());
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
			mainMenu();
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
				return role;
		}
		return null;
	}

	public static String getDayFromUser() {
		int d;
		String day;
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
				System.out.println("3. Remove Availability"); 
				System.out.println("4. View Appointments");
				System.out.println("5. Postpone Appointment");
				System.out.println("6. Cancel Appointment");
				System.out.println("7. View Prescription");
				System.out.println("8. Add Prescription");
				System.out.println("9. Remove Prescription"); 
				System.out.println("10. Back"); 
				choice = sc.nextInt();
				sc.nextLine();
			} while (choice < 1 || choice > 12);
			if (choice == 10) {
				mainMenu();
			} else if (choice == 1) {
				viewAvailabilities();
			} else if (choice == 2) {
				addAvailability();
			} else if (choice == 3) {
				removeAvailability();
			} else if (choice == 4) {
				viewAppointments(getRole(clinic.getCurrentUser(), "Doctor"));
			} else if (choice == 5) {
				postponeAppointment();
			} else if (choice == 6) {
				cancelAppointment(getRole(clinic.getCurrentUser(), "Doctor"));
			} else if (choice == 7) {
				viewPrescription(getRole(clinic.getCurrentUser(), "Doctor"));
			} else if (choice == 8) {
				addPrescription();
			} else if (choice == 9) {
				removePrescription();
			}
			doctorPortal();
		} else {
			authMenu();
		}
	}

	public static void patientPortal() {
		if (clinic.getCurrentUser() != null && isOfRole(clinic.getCurrentUser(), "Patient")) {
			int choice;
			do {
				System.out.println("1. View Doctors");
				System.out.println("2. Add Appointment");
				System.out.println("3. View Appointments");
				System.out.println("4. Cancel Appointment");
				System.out.println("5. View Prescription");
				System.out.println("6. Back");
				choice = sc.nextInt();
				sc.nextLine();
			} while (choice < 1 || choice > 6);
			if (choice == 6) {
				mainMenu();
			} else if (choice == 1) {
				viewDoctors();
			} else if (choice == 2) {
				addAppointment(getRole(clinic.getCurrentUser(), "Patient"));
			} else if (choice == 3) {
				viewAppointments(getRole(clinic.getCurrentUser(), "Patient"));
			} else if (choice == 4) {
				cancelAppointment(getRole(clinic.getCurrentUser(), "Patient"));
			} else if (choice == 5) {
				viewPrescription(getRole(clinic.getCurrentUser(), "Patient"));
			}
			patientPortal();
		} else {
			authMenu();
		}
	}

	public static void viewDoctors() {
		if (clinic.getCurrentUser() != null && isOfRole(clinic.getCurrentUser(), "Patient")) { 
			if (clinic.getDoctors().length > 0) {
				int i=1;
				for (Doctor doctor : clinic.getDoctors()) {
					System.out.println("Id: " + (i++));
					System.out.println("Name: " + doctor.getPerson().getName());
					System.out.println("Specialization: " + doctor.getSpecialization());
					if (doctor.getAvailabilities().length > 0) {
						int j=1;
						System.out.println("Availabilities");
						for (Availability availability : doctor.getAvailabilities()) {
							System.out.println("\t Id: " + (j++));
							System.out.println("\t Day: " + availability.getDay());
							System.out.println("\t Time In: " + availability.getTimeInDate());
							System.out.println("\t Time Out: " + availability.getTimeOutDate());
						}
					}
				}
			} else {
				System.out.println("It seems that no doctor is available.");
			}
		} else {
			authMenu();
		}
	}

	public static void addAppointment(Role role) {
		if (clinic.getCurrentUser() != null && role != null && role.getRole().equals("Patient")){
			viewDoctors();
			if(clinic.getDoctors().length > 0){
				int doc;
				do {
					System.out.print("Enter doctor id: ");
					doc = sc.nextInt();
					sc.nextLine();
				} while(doc < 1 || doc > clinic.getDoctors().length);
				Doctor doctor = clinic.getDoctors()[doc-1];
				if (doctor.getAvailabilities().length > 0){
					String day, timeStart, timeEnd;
					do {
						System.out.println("Choose day");
						day = getDayFromUser();
						System.out.println("Enter time start and end (can never be same)");
						do {
							System.out.print("Enter time start (24 hr format => 01:00): ");
							timeStart = sc.nextLine();
						} while (timeStart.length() != 5);
						do {
							System.out.print("Enter time end (24 hr format => 07:00): ");
							timeEnd = sc.nextLine();
						} while (timeEnd.length() != 5);
					} while (!doctor.hasAvailability(day, timeStart, timeEnd));
					new Appointment(clinic, doctor, (Patient)role, day, timeStart, timeEnd);
				} else {
					System.out.println("Doctor is not free.");
				}
			} else {
				System.out.println("No doctor was available.");
			}
		} else {
			authMenu();
		}
	}

	public static void viewAvailabilities() {
		if (clinic.getCurrentUser() != null && getRole(clinic.getCurrentUser(), "Doctor") != null) {
			Doctor doctor = (Doctor) getRole(clinic.getCurrentUser(), "Doctor");
			if (doctor.getAvailabilities().length > 0){
				int i=1;
				for (Availability availability : doctor.getAvailabilities()) {
					System.out.println("Id: " + (i++));
					System.out.println("Day: " + availability.getDay());
					System.out.println("TimeIn: " + availability.getTimeInDate());
					System.out.println("TimeOut: " + availability.getTimeOutDate());
					System.out.println();
				}
			}else {
				System.out.println("No Availability was found.");
			}
		} else {
			authMenu();
		}
	}

	public static void addAvailability() {
		if (clinic.getCurrentUser() != null && getRole(clinic.getCurrentUser(), "Doctor") != null) {
			Doctor doctor = (Doctor) getRole(clinic.getCurrentUser(), "Doctor");
			System.out.println("Choose day");
			String day = getDayFromUser(), timeIn, timeOut;
			do {
				System.out.println("Enter time in and out (can never be same)");
				do {
					System.out.print("Enter time in (24 hr format => 01:00): ");
					timeIn = sc.nextLine();
				} while(timeIn.length() != 5);
				do {
					System.out.print("Enter time out (24 hr format => 07:00): ");
					timeOut = sc.nextLine();
				} while (timeOut.length() != 5);
			} while (timeIn.equals(timeOut));
			System.out.println(doctor + " " + day + " "+ timeIn+ " "+ timeOut);
			Availability availability = new Availability(doctor, day, timeIn, timeOut);
		} else {
			authMenu();
		}
	}

	public static void removeAvailability() {
		if (clinic.getCurrentUser() != null && getRole(clinic.getCurrentUser(), "Doctor") != null) {
			viewAvailabilities();
			Doctor doctor = (Doctor) getRole(clinic.getCurrentUser(), "Doctor");
			if (doctor.getAvailabilities().length > 0){
				int id;
				System.out.println("Note: It will cancel all appointments on that day.");
				do {
					System.out.print("Enter id: ");
					id = sc.nextInt();
					sc.nextLine();
				} while (id < 1 || id > doctor.getAvailabilities().length);
				Availability availability = doctor.getAvailabilities()[id - 1];
				doctor.removeAvailability(availability);
			}
		} else {
			authMenu();
		}
	}

	public static void viewAppointments(Role role) {
		if (clinic.getCurrentUser() != null && role != null) {
			if(role.getAppointments().size() > 0) {
				int i = 1;
				for (Appointment appointment : role.getAppointments()) {
					System.out.println("Id: " + (i++));
					System.out.println("Appointment Day: " + appointment.getDay());
					System.out.println("Appointment Time Start: " + appointment.getTimeStartDate());
					System.out.println("Appointment Time End: " + appointment.getTimeEndDate());
					if (role.getRole().equals("Doctor")) {
						System.out.println("Patient Name: " + appointment.getPatient().getPerson().getName());
						System.out.println("Patient Disease: " + appointment.getPatient().getDisease());
					} else {
						System.out.println("Doctor Name: " + appointment.getDoctor().getPerson().getName());
						System.out.println("Doctor Specialization: " + appointment.getDoctor().getSpecialization());
					}
				}
			} else {
				System.out.println("No Appointment was found.");
			}
		} else {
			authMenu();
		}
	}

	public static void postponeAppointment() {
		if (clinic.getCurrentUser() != null && getRole(clinic.getCurrentUser(), "Doctor") != null) {
			viewAppointments(getRole(clinic.getCurrentUser(), "Doctor"));
			Doctor doctor = (Doctor) getRole(clinic.getCurrentUser(), "Doctor");
			if (doctor.getAppointments().size() > 0) {
				int id;
				do {
					System.out.print("Enter id of appointment: ");
					id = sc.nextInt();
					sc.nextLine();
				} while (id < 1 || id > doctor.getAppointments().size());

				Appointment appointment = doctor.getAppointments().get(id-1);
				String day;
				do {
					System.out.println("Note: You have to enter day of availability.");
					day = getDayFromUser();
				} while(!doctor.hasAvailability(day));
				appointment.setDay(day);
			} else {
				System.out.println("No Appointment was found");
			}
		} else {
			authMenu();
		}
	}
	
	public static void cancelAppointment(Role role) {
		if (clinic.getCurrentUser() != null && role != null) {
			viewAppointments(role);
			if(role.getAppointments().size() > 0) {
				int id;
				do {
					System.out.print("Enter appointment id: ");
					id = sc.nextInt();
					sc.nextLine();
				} while (id < 1 || id > role.getAppointments().size());
				Appointment appointment = role.getAppointments().get(id - 1);
				appointment.cancel();
			} else {
				System.out.println("No Appointment was found.");
			}
		} else {
			authMenu();
		}
	}

	public static void viewPrescription(Role role) {
		if (clinic.getCurrentUser() != null && role != null) {
			viewAppointments(role);
			if (role.getAppointments().size() > 0) {
				int id; 
				do {
					System.out.print("Enter appointment id: ");
					id = sc.nextInt();
					sc.nextLine();
				} while(id < 1 || id > role.getAppointments().size());
				Appointment appointment = role.getAppointments().get(id-1);
				if (appointment.getMedicines().length > 0) {
					int i=1;
					for (Medicine medicine: appointment.getMedicines()) {
						System.out.println("Id: " + (i++));
						System.out.println("Medicine Name: " + medicine.getName());
						System.out.println("Medicine Dosage: " + medicine.getDosage());
					}
				} else {
					System.out.println("No prescription was found!");
				}
			} else {
				System.out.println("No appointments to view prescription");
			}
		} else {
			authMenu();
		}
	}

	public static void addPrescription() {
		if (clinic.getCurrentUser() != null && isOfRole(clinic.getCurrentUser(), "Doctor")) {
			Role role = getRole(clinic.getCurrentUser(), "Doctor");
			viewAppointments(role);
			int id; 
			do {
				System.out.print("Enter appointment id: ");
				id = sc.nextInt();
				sc.nextLine();
			} while(id < 1 || id > role.getAppointments().size());
			Appointment appointment = role.getAppointments().get(id-1);
			String name, dosage;
			do {
				System.out.print("Enter name of medicine: ");
				name = sc.nextLine();
			} while (name.length() == 0);
			do {
				System.out.print("Enter dosage to take: ");
				dosage = sc.nextLine();
			} while (dosage.length() == 0);
			Medicine medicine = new Medicine(appointment, name, dosage);
		} else {
			authMenu();
		}
	}

	public static void removePrescription() {
		if (clinic.getCurrentUser() != null && isOfRole(clinic.getCurrentUser(), "Doctor")) {
			Role role = getRole(clinic.getCurrentUser(), "Doctor");
			viewAppointments(role);
			if (role.getAppointments().size() > 0) {
				int id;
				do {
					System.out.print("Enter appointment id: ");
					id = sc.nextInt();
					sc.nextLine();
				} while (id < 1 || id > role.getAppointments().size());
				Appointment appointment = role.getAppointments().get(id - 1);
				if (appointment.getMedicines().length > 0) {
					int i = 1;
					for (Medicine medicine : appointment.getMedicines()) {
						System.out.println("Id: " + (i++));
						System.out.println("Medicine Name: " + medicine.getName());
						System.out.println("Medicine Dosage: " + medicine.getDosage());
					}
					int mid;
					do {
						System.out.print("Enter prescription id: ");
						mid = sc.nextInt();
						sc.nextLine();
					} while(mid < 1 || mid > appointment.getMedicines().length);
					appointment.removeMedicine(appointment.getMedicines()[mid-1]);
				} else {
					System.out.println("No Prescription to delete.");
				}
			} else {
				System.out.println("No appointment to remove prescription");
			}
		}
	}

	public static void exit() {
		System.out.println("Bye.");
		System.exit(0);
	}

}