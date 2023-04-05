package ui;

import java.text.ParseException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Controller;
import model.Project;

/**
The Main class is the entry point for the program, which provides a menu to interact with a
Controller object, allowing the user to create and manage projects. Also the user can consult
the projects that starts after or ends before a certain date enter by him.
*/
public class Main{

	private Scanner input;
	private Controller controller;

	/**
 	* Constructs a Main object.
 	*/

	public Main() {

		input = new Scanner(System.in);
		controller = new Controller();
	}

	/**
 	* The main method, which starts the program.
 	* @param args the command line arguments
 	* @throws ParseException if there is an error parsing the date
 	*/

	public static void main(String[] args) throws ParseException {

		Main exe = new Main();
		System.out.println("Welcome to GreenSQA");
		exe.menu();

	}

	/**
 	* Displays the menu and allows the user to select an option.
 	* @throws ParseException if there is an error parsing the date
 	*/
	public void menu() throws ParseException {

		int option = -1;
		while(option != 0){ 
			System.out.println("Menu: Enter 1.Create Project, 2. consult Projects that starts after a date, 3. consult Projects that ends before a day, 0. Exit");
			option = input.nextInt();
			switch(option){
				case 1: RegisterProject();
				break;
				case 2: searchProjectsAfterDate();
				break;
				case 3: searchProjectsBeforeDate();
				break;
				case 0:
				System.out.println("thank you");
				break;
				default:
				System.out.println("error enter again");

			}
		}
	}

	/**
 	* Prompts the user for information and registers a new project.
 	* @throws ParseException if there is an error parsing the date
 	*/	
	public void RegisterProject() throws ParseException {

		System.out.println("Enter the name of the project:");
		String nameProject = input.next();
		while (nameProject.equals("")) {
    		nameProject = input.nextLine().trim();
    		if (nameProject.equals("")) {
        		System.out.println("Name can't be empty. Enter the name of the project again:");
    		}
		}

		System.out.println("Enter the name of the client:");
		String nameClient = input.next();
		while (nameClient.equals("")) {
    		nameClient = input.nextLine().trim();
    		if (nameClient.equals("")) {
        		System.out.println("Name can't be empty. Enter the name of the client again:");
    		}
		}

		int typeProject = 0;
		while (typeProject < 1 || typeProject > 3) {
    		System.out.println("Enter the type of the project (1. Development, 2. Maintenance, 3. Deployment):");
    		try {
        		typeProject = input.nextInt();
        		input.nextLine();
        		if (typeProject < 1 || typeProject > 3) {
            		System.out.println("Invalid input. Please enter a number from 1 to 3.");
        		}
    		}catch (InputMismatchException e) {
        		System.out.println("Invalid input. Please enter a number from 1 to 3.");
        		input.nextLine();
    		}
		}

		System.out.println("Enter the date to start the project (dd-MM-yyyy):");
		String dateAux = "";
		Calendar startDateProject = null;
		while (startDateProject == null) {
    		dateAux = input.nextLine().trim();
    		try {
        		startDateProject = controller.modifyStringToCalendar(dateAux);
    		} catch (ParseException e) {
        		System.out.println("Invalid date format. Please enter a date in dd-MM-yyyy format.");
    		}
		}

		System.out.println("Enter the date to end the project (dd-MM-yyyy):");
		dateAux = "";
		Calendar endDateProject = null;
		while (endDateProject == null) {
    		dateAux = input.nextLine().trim();
    		try {
        		endDateProject = controller.modifyStringToCalendar(dateAux);
    		} catch (ParseException e) {
        		System.out.println("Invalid date format. Please enter a date in dd-MM-yyyy format.");
    		}
		}

		double budget = 0;
		while (budget <= 0) {
    		System.out.println("Enter the budget of the project ($):");
    		try {
        		budget = input.nextDouble();
        		if (budget <= 0) {
            		System.out.println("Budget must be greater than 0. Please enter the budget of the project again:");
        		}
    		} catch (InputMismatchException e) {
        		System.out.println("Invalid input. Please enter a number greater than 0.");
        		input.nextLine();
    		}
    		input.nextLine();
		}



        controller.RegisterProject(nameProject, nameClient, startDateProject, endDateProject, typeProject, budget);
        String creationProjectConfirmation = "The project "+ controller.getIteration() +"./9 has been created";
        System.out.println(creationProjectConfirmation);
        
    }

	/**
 	* Prompts the user to enter a date in the format dd-MM-yyyy, and then searches for all projects that start after that date.
 	* Prints out the project information for each project found.
 	*
 	* @throws ParseException if the user-entered date cannot be parsed correctly
 	*/
	public void searchProjectsAfterDate() throws ParseException {
		
		System.out.println("Enter the date to check in format dd-MM-yyyy"); 
		String dateSearch = input.next();
		List<Project> projectsAfterDate = controller.searchProjectsAfterDate(dateSearch);
		if (projectsAfterDate.isEmpty()) {
			System.out.println("There is not any project that starts after this date.");
		} else {
			for (Project project : projectsAfterDate) {
				System.out.println(project.getProjectInfo());
			}
		}

	}
	
	/**
 	* Prompts the user to enter a date in the format dd-MM-yyyy, and then searches for all projects that ends before that date.
 	* Prints out the project information for each project found.
 	*
 	* @throws ParseException if the user-entered date cannot be parsed correctly
 	*/
	
	public void searchProjectsBeforeDate() throws ParseException {
		
		System.out.println("Enter the date to check in format dd-MM-yyyy"); 
		String dateSearch = input.next();
		List<Project> projectsBeforeDate = controller.searchProjectsBeforeDate(dateSearch);
		if (projectsBeforeDate.isEmpty()) {
			System.out.println("There is not any project that ends before this date.");
		} else {
			for (Project project : projectsBeforeDate) {
				System.out.println(project.getProjectInfo());
				
			}
		}
	}
	
}
