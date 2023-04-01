/**

The Controller class is responsible for managing the list of projects.
It provides methods for registering new projects, searching for projects based on a given date,
and converting a string to a calendar object.
It also has methods for getting the number of projects and accessing a project at a specific index in the list.
*/

package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Controller {

	private Project[] projects;
	private int iteration;
	
	/**
	* Constructor for the Controller class.
	* Initializes the projects array with a size of 10 and sets the iteration counter to 0.
	*/


	public Controller() {

		projects = new Project[10];
		iteration = 0;
		
	}

	/**
 	* Registers a new project with the given name, client name, initial and final dates, type, and budget.
 	* @param name The name of the project.
 	* @param clientName The name of the client.
 	* @param initialDate The initial date of the project.
 	* @param finalDate The final date of the project.
 	* @param typeProject The type of project.
 	* @param budget The budget of the project.
 	* @return false (always).
 	*/
	
	public boolean RegisterProject(String name, String clientName, Calendar initialDate, Calendar finalDate, int typeProject, double budget) {

		projects[iteration] = new Project(name, clientName, initialDate, finalDate,typeProject, budget);
		iteration++;

		return false;
	}

	/**
 	* Returns the index of the first null position in the projects array.
 	* @return The index of the first null position in the projects array.
 	*/

	public int getFirstValidPosition(){
		int position = -1;
		boolean found = false;

		for (int i = 0; i < 10 && !found ; i++){
			if(projects[i] == null){
				position = i;
				found = true;
			}
			
		}
		return position;
	}

	/**
 	* Converts a string in the format "dd-MM-yyyy" to a calendar object.
 	* @param date The string to convert to a calendar object.
	* @return The calendar object representing the given date string.
	* @throws ParseException If the given date string cannot be parsed.
 	*/
	
	public Calendar modifyStringToCalendar(String date) throws ParseException{

		SimpleDateFormat newDate = new SimpleDateFormat("dd-MM-yyyy");
		Calendar newDateCalendar = Calendar.getInstance();
		newDateCalendar.setTime(newDate.parse(date));

		return newDateCalendar;

	}

	/**
 	* Searches for projects that start after the given date.
 	* @param date The date to compare against.
 	* @return A list of projects that start after the given date.
 	* @throws ParseException If the given date string cannot be parsed.
 	*/

	public List<Project> searchProjectsAfterDate(String date) throws ParseException {

		List<Project> projectsAfterDate = new ArrayList<>();
		Calendar realDate = modifyStringToCalendar(date);
		for (int i = 0; i < getFirstValidPosition(); i++) {
			if (projects[i].getInitialDate().compareTo(realDate) > 0) {
				projectsAfterDate.add(projects[i]);
			}
		}
		return projectsAfterDate;

	}
	
	/**
 	* Searches for projects that end before the given date.
 	* @param date The date to compare against.
 	* @return A list of projects that end before the given date.
 	* @throws ParseException If the given date string cannot be parsed
	*/

	public List<Project> searchProjectsBeforeDate(String date) throws ParseException {

		List<Project> projectsBeforeDate = new ArrayList<>();
		Calendar realDate = modifyStringToCalendar(date);
		for (int i = 0; i < getFirstValidPosition(); i++) {
			if (projects[i].getFinalDate().compareTo(realDate) < 0) {
				projectsBeforeDate.add(projects[i]);
			}
		}
		return projectsBeforeDate;

	}

	/**
 	* Get the iterarion about the amount of projects.
 	* 
 	* @return The amount iteration -1.
 	*/
	public int getIteration() {
		return iteration-1;
	}

	/**
	Returns the project at the specified index in the projects array.
	@param i the index of the project to be returned.
	@return the project at the specified index in the projects array.
	*/

	public Project getProjects(int i) {
		return projects[i];
	}
	
	
}
