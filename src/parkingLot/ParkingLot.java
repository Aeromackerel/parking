package parkingLot;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLot 
{
	// Constructors
	
	public ParkingLot (int maxCap, int numEntrances, int numExits, float rate)
	{
		this.maxCapacity = maxCap;
		this.currentCapacity = maxCap;
		this.hourlyRate = rate;
		
		for (int i = 0; i < numEntrances; i++)
			List_ParkingEntrance.add(new ParkingEntrance());
		
		for (int i = 0; i < numExits; i++)
			List_ParkingExit.add(new ParkingExit());
		
		System.out.println(List_ParkingEntrance.size());
		System.out.println(List_ParkingExit.size());
		System.out.println(this.hourlyRate);
		
		System.out.println("Successfully created a parking lot!");
	}
	
	public ParkingLot() 
	{}

	// Field variables
	private int maxCapacity;
	private int currentCapacity;
	private float hourlyRate;
	ArrayList<ParkingEntrance> List_ParkingEntrance = new ArrayList<>();
	ArrayList<ParkingExit> List_ParkingExit = new ArrayList<> ();
	ArrayList<Vehicle> List_Vehicles = new ArrayList<>();
	
	// Correlated functions
	
	/*
	 * Retrieves the amount of profit that the owner of the lot has
	 * accumulated since opening the parking lot
	 * 
	 * @ params - none
	 * 
	 */
	
	private void listProfitOperations()
	{		
		System.out.println("Please choose from the following operations");
		System.out.println("--------------------------------------------");
		System.out.println("1 - Get total profit made from parking lot");
		System.out.println("2 - Get total profit made from a specific exit");
		// Later implement based on timestamp/datetime
	}
	
	private void listExits()
	{System.out.println("Please select from the following exits : 1 ~  " + String.valueOf(List_ParkingExit.size()));}
	
	
	private void getProfit()
	{	
		int amountProfit = 0;
		
		// Iterate through the parking Exit array
		for (ParkingExit pe : List_ParkingExit)
		{
			
		}
		
		
	}
	
	private void getProfitSpecific(int indexIn)
	{
		int amountProfit = 0;
		
		// Find the specific index of parking exit and get the profit that is kept track in that exit
		indexIn -= 1;
		
	}
		
	private void enterParkingLot(Scanner sc)
	{	
		System.out.println("Please select from the following entrances to enter from : 1 ~ " + String.valueOf(List_ParkingEntrance.size()));
		
		int indexPass = sc.nextInt();
		indexPass -= 1;
		
		// Verify if the index is valid
		if (indexPass < 0 || indexPass > List_ParkingEntrance.size() -1)
		{
			System.out.println(indexPass);
			System.out.println("Invalid index - exitting program");
			System.exit(1);
		}
		
		// Verify that there is space
		if (this.currentCapacity > 0)
		{
			ParkingEntrance entranceChosen = List_ParkingEntrance.get(indexPass);
			String ticket = entranceChosen.RecieveTicket();
			System.out.println("Recieved a ticket - timestamp given : " + ticket);
			Vehicle Car = new Vehicle(ticket);
			List_Vehicles.add(Car);
		}
		
		// Decrements the capacity by 1
		this.ParkVehicle();
	}
	
	private void leaveParkingLot(Scanner sc)
	{
		System.out.println("Please enter the number of your vehicle from : 1 ~ " + String.valueOf(List_Vehicles.size()));
		
		int indexPassVehicle = sc.nextInt();
		indexPassVehicle -= 1;
		
		// Check if it is a valid car
		if (indexPassVehicle < 0 || indexPassVehicle > List_Vehicles.size() -1)
		{
			System.out.println("Invalid index - exitting program");
			System.exit(1);
		}
		
		Vehicle car = List_Vehicles.get(indexPassVehicle);
		String ticket = car.ticketAttached;
		
		
		System.out.println("Please select from the following exits to leave from : 1 ~ " + String.valueOf(List_ParkingExit.size()));
		
		int indexPass = sc.nextInt();
		indexPass -= 1;
		
		// Verify if the index is valid
		if (indexPass < 0 || indexPass > List_ParkingExit.size() -1)
		{
			System.out.println(indexPass);
			System.out.println("Invalid index - exitting program");
			System.exit(1);
		}
		
		ParkingExit exitChosen = List_ParkingExit.get(indexPass);
		exitChosen.PayTicket(ticket, this.hourlyRate);
		
		this.UnparkVehicle();
		
	}
	
	private void ParkVehicle()
	{this.currentCapacity -= 1;}
	
	private void UnparkVehicle()
	{this.currentCapacity += 1;}
	
	public int getCurrentCapacity()
		{return this.currentCapacity;}
	
	public boolean isFull()
		{return this.currentCapacity != 0;}
	
	// Static functions section
	
	/*
	 * Validates the input of what the user passes in
	 * @ params - number of spaces, number of entrances, number of exits
	 * @ errors - if <= 0 - notify user and exit
	 */

	public static void validateInputs (int capacityLot, int numEntrances, int numExits, float rate)
	{
		if (capacityLot <= 0 || numEntrances <= 0 || numExits <= 0 || rate <= 0.0)
		{
			System.out.println("Please input a positive number for the capacity, number of entrances, hourly rate, and number of exits");
			System.out.println("Ending program");
			System.exit(1);
		}
			
	}
	
	public static boolean VerifyOperation (String operationRead)
	{
		if (operationRead.equals ("Y"))
			return true;
		else if (operationRead.equals("N"))
			return false;
		
		else
		{
			System.out.println("Please enter either Y or N  - exitting program");
			System.exit(1);
			return false;
		}
	}
	
	public static void VerifySimulation (String operationRead)
	{
		if (operationRead.equals("V") || operationRead.equals("P"))
			return;
		else
		{
			System.out.println("Please enter a valid Simulation operation letter (either P - profit or V - simulate vehicles enterings and exitting the parking lot");
			System.out.println("Exitting program");
			System.exit(1);
		}
	}
	
	// Driver code
	
	public static void main (String [] args)
	{	
		Scanner sc = new Scanner(System.in);
		
		// Don't have to set these to null because garbage collector does so for us
		int capacityLot;
		int numEntrances;
		int numExits;
		float rate;
		String whileOperation;
		String operationPerform;
		boolean continueOperation = true;
		boolean continueOperationInternal = true;
				
		System.out.println("How many parking spaces would you like in your parking lot?");
		capacityLot = sc.nextInt();
		System.out.println("How many Parking entrances would you like to have?");
		numEntrances = sc.nextInt();
		System.out.println("How many Parking exits would you like to have?");
		numExits = sc.nextInt();
		System.out.println("Please input the hourly rate you'd like to charge");
		rate = sc.nextFloat();
		
		validateInputs (capacityLot, numEntrances, numExits, rate);
		
		// Construct the Parking lot
		
		ParkingLot clientParkingLot = new ParkingLot(capacityLot, numEntrances, numExits, rate);
		
		String tempBoolHolder;
		String optionSelected;
		
		while (continueOperation == true)
		{
			System.out.println("Would you like to continue simulations? Please enter either (Y/N)");
			whileOperation = sc.next();
			continueOperation = VerifyOperation(whileOperation);
			
			// Ask the user what they'd like to do next
			
			System.out.println("What would you like to do next? P - Profit related commands | V - simulate vehicle entering/exitting the parking Lot");
			operationPerform = sc.next();
			VerifySimulation(operationPerform);
			
			// Call respective functions depending on what is read in
			if (operationPerform.equals("P"))
			{
				while (continueOperation == true)
				{
					clientParkingLot.listProfitOperations();
					
					optionSelected = sc.next();
					
					switch(optionSelected)
					{
					case "1":
						clientParkingLot.getProfit();
						break;
					case "2":
						clientParkingLot.listExits();
						int exitIndex = sc.nextInt();
						clientParkingLot.getProfitSpecific(exitIndex);
						break;
					}
					
					System.out.println("Do you want to continue operations? - input (Y/N)");
					tempBoolHolder = sc.next();
					continueOperation = VerifyOperation(tempBoolHolder);
				}
				
			}
			else if (operationPerform.equals("V"))
			{
				while (continueOperation == true)
				{
					Vehicle.listVehicleOperations();
					
					optionSelected = sc.next();
					
					switch(optionSelected)
					{
					case "1":
						clientParkingLot.enterParkingLot(sc);
						break;
					case "2":
						clientParkingLot.leaveParkingLot(sc);
						break;
					}
					
					System.out.println("Do you want to continue operations? - input (Y/N)");
					tempBoolHolder = sc.next();
					continueOperation = VerifyOperation(tempBoolHolder);
				}
			}
			
			
			
		}
		
		
		
		sc.close();
		
		
		
	}
	
	
	
	
	
}
