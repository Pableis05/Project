package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.text.ParseException;

/**
The Project class represents a project that can be registered in the system.
It contains the project's name, client name, initial and final date, budget, and type.
The class provides methods to get and set the values of each attribute and to format the dates.
*/
public class Project{

	//attributes
	private String name;
	private String clientName;
	private Calendar initialDate;
	private Calendar finalDate;
	private double budget;
	private int typeProject;
	private DateFormat formatter;

	/**
	* Constructor for Project class.
	* 
	* @param name         The name of the project.
	* @param clientName   The name of the client who requested the project.
	* @param initialDate  The initial date of the project.
	* @param finalDate    The final date of the project.
	* @param typeproject  The type of project.
	* @param budget       The budget allocated for the project.
	*/

	public Project(String name, String clientName, Calendar initialDate, Calendar finalDate, int typeproject, double budget){
		
		this.formatter = new SimpleDateFormat("dd/M/yy");

		this.name = name;	
		this.clientName = clientName;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.typeProject = typeproject;
		this.budget = budget;
	}
	
	/**
	* Get the name of the project.
	* 
	* @return The name of the project.
	*/
	public String getName(){
		return name;
	}
	
	/**
 	* Get the name of the client who requested the project.
 	* 
 	* @return The name of the client.
 	*/
	public String getClientName(){
		return clientName;
	}

	/**
 	* Get the initial date of the project.
 	* 
 	* @return The initial date of the project.
 	*/
	public Calendar getInitialDate(){
		return initialDate;
	}
	/**
 	* Get the formatted initial date of the project.
 	* 
 	* @return The formatted initial date of the project.
 	* @throws ParseException if the date format is invalid.
 	*/
	
	public String getInitialDateFormated() throws ParseException{
		return formatter.format(this.initialDate.getTime());
	}

	/**
 	* Get the final date of the project.
 	* 
 	* @return The final date of the project.
 	*/
	public Calendar getFinalDate(){
		return finalDate;
	}

	/**
 	* Get the formatted final date of the project.
 	* 
 	* @return The formatted final date of the project.
 	* @throws ParseException if the date format is invalid.
 	*/
	public String getFinalDateFormated() throws ParseException{
		return formatter.format(this.finalDate.getTime());
	}		
	/**
 	* Get the budget allocated for the project.
 	* 
 	* @return The budget allocated for the project.
 	*/

	public double getBudget(){
		return budget;
	}

	/**
 	* Get a formatted string containing the project's information.
 	* 
 	* @return A formatted string containing the project's information.
 	* @throws ParseException if the date format is invalid.
 	*/

	public String getProjectInfo() throws ParseException{
		return "\nName: " + name + "\nClient: " + clientName + "\nInitial Date: " + getInitialDateFormated() + 
		"\nFinal Date: " + getFinalDateFormated() + "\nTotalBudget: " + budget + ".\n";
	}
	

}


