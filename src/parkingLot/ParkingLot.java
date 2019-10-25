package parkingLot;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ParkingLot 
{
	// Constructors
	
	public ParkingLot (String nameIn, int maxCap, int numEntrances, int numExits, float rate, int numFloors)
	{
		this.currentCapacity = maxCap;
		this.maxCapacity = maxCap;
		this.hourlyRate = rate;
		this.policy = "";
		this.parkingLotName = nameIn;
		this.numOfFloors = numFloors;
		
		for (int i = 0; i < numEntrances; i++)
			List_ParkingEntrance.add(new ParkingEntrance());
		
		for (int i = 0; i < numExits; i++)
			List_ParkingExit.add(new ParkingExit());
		
		this.initializeLotsPerFloor();
		
		System.out.println("Successfully created a parking lot!");
	}
	
	public ParkingLot() {}

	private int currentCapacity;
	private int maxCapacity;
	private int numOfFloors;
	private float hourlyRate;
	protected String parkingLotName;
	private String policy;
	ArrayList<Integer> capacityPerFloor = new ArrayList<>();
	ArrayList<ParkingEntrance> List_ParkingEntrance = new ArrayList<>();
	ArrayList<ParkingExit> List_ParkingExit = new ArrayList<> ();
	ArrayList<Vehicle> List_Vehicles = new ArrayList<>();
	
	// Correlated functions section
	
	 /* Retrieves the amount of profit that the owner of the lot has
	 * accumulated since opening the parking lot
	 * @ params - none */
	
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
	
	private void initializeLotsPerFloor()
	{
		int numLotsPerFloor = (int) this.maxCapacity/this.numOfFloors;
		int leftOverLots = this.maxCapacity - (numLotsPerFloor * numOfFloors);
		
		for (int i = 0; i < numOfFloors; i++)
			this.capacityPerFloor.add(numLotsPerFloor);
		
		int index = 0;
		
		while (leftOverLots > 0)
		{
			int previousCapacity = this.capacityPerFloor.get(index);
			this.capacityPerFloor.set(index, previousCapacity+1);
			leftOverLots -=1;
			index++;
		}
		
	}
	
	private void updateCapacityPerFloor()
	{
		for (int i = 0; i < this.capacityPerFloor.size(); i++)
		{
			if (this.capacityPerFloor.get(i) > 0)
			{
				this.capacityPerFloor.set(i, this.capacityPerFloor.get(i) - 1);
				break;
			}
			
		}
		
	}
	
	private void getProfit()
	{	
		double amountProfit = 0;
		// Iterate through the parking Exit array
		for (ParkingExit pe : List_ParkingExit)
			amountProfit += pe.totalProfit;
		
		System.out.println("Total profit since parking lots opening date : " + amountProfit);
	}
	
	private void getProfitSpecific(int indexIn)
	{
		indexIn -= 1;
		double profitSpecific = List_ParkingExit.get(indexIn).totalProfit;
		System.out.println("Total profit from exit # : " + (indexIn+1) + " is : " + profitSpecific);
	}
		
	private void enterParkingLot(Scanner sc)
	{	
		// Check if the parking lot has space
		
		if (this.currentCapacity == 0)
		{
			System.out.println("There isn't enough space in the parking lot - consider finding somewhere else to park");
			System.exit(1);
		}
		
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
		
		Vehicle Car = new Vehicle();
		ArrayList<Integer> passList = this.capacityPerFloor;
		
		// Display the current information to the driver
		ParkingEntrance entranceChosen = List_ParkingEntrance.get(indexPass);
		String ticket = entranceChosen.DisplayInfoAndDecide(Car, currentCapacity, hourlyRate, passList);
		
		// Check if the user decided not to enter the parking lot
		if (ticket.equals(""))
			return;
		
		// Verify that there is space
		System.out.println("Recieved a ticket - timestamp given : " + ticket);
		Car.addTicket(ticket);
		List_Vehicles.add(Car);
		
		// Decrements the capacity by 1
		this.ParkVehicle();
	}
	
	/*  Asks the user what vehicle they would like to
	 *  remove from the parking lot. Increments available
	 *  spaces by one and pushes profit into the exit that
	 *  the vehicle exitted from
	 *  @ params - Scanner
	 *  @ errors - if they choose an invalid index, then we tell exit the program
	 */
	
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
		this.UnparkVehicle(indexPassVehicle);
	}
	
	private void ParkVehicle()
	{
		this.currentCapacity -= 1;
		this.updateCapacityPerFloor();
	}
	
	private void UnparkVehicle(int indexCar)
	{
		this.currentCapacity += 1;
		this.List_Vehicles.remove(indexCar);
	}
	
	/* Initializes the parking lot with existing vehicles
	 * and datetime stamped tickets from the txt file passed in
	 * @ params - ArrayList<String> fileLines, integer numbers of car parked */
	
	private void setCarsTickets (ArrayList<String> fileContents, int numCarsParked)
	{
		int index = 7;
		String ticketDate;
		
		while (index < 7 + numCarsParked)
		{
			ticketDate = fileContents.get(index);
			boolean validTicket = List_ParkingExit.get(0).isValidDate(ticketDate);
			if (validTicket == false) {handleInvalidTicket();}
			Vehicle car = new Vehicle(ticketDate);
			List_Vehicles.add(car);
			this.ParkVehicle();
			index++;
		}
	}
	
	private void handleInvalidTicket()
	{
		System.out.println("The ticket is invalid - please contact the parking lot staff");
		System.exit(1);
	}
	
	private void setPolicy(String policyIn)
	{this.policy = policyIn;}
	
	public float getHourlyRate()
	{return this.hourlyRate;}
	
	public int getCurrentCapacity()
		{return this.currentCapacity;}
	
	public boolean isFull()
		{return this.currentCapacity != 0;}
	
	// Static functions section
	
	/* Validates the input of what the user passes in
	 * @ params - number of spaces, number of entrances, number of exits
	 * @ errors - if <= 0 - notify user and exit*/

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
		if (operationRead.equals("V") || operationRead.equals("P") || operationRead.equals("G"))
			return;
		else
		{
			System.out.println("Please enter a valid Simulation operation letter (either P - profit or V - simulate vehicles enterings and exitting the parking lot or G - group related commands");
			System.out.println("Exitting program");
			System.exit(1);
		}
	}
	
	public static ArrayList<String> readFile (String filename)
	{
		ArrayList<String> fileContents = new ArrayList<>();
		
		try 
		{
			BufferedReader br = new BufferedReader (new FileReader(filename));
			String line = br.readLine();
			
			while (line != null)
			{
				fileContents.add(line);
				line = br.readLine();
			}
			
			br.close();
		} 
		catch (IOException e) {e.printStackTrace();}
		
		return fileContents;
	}
	
	// Driver code
	
	public static void main (String [] args)
	{	
		ValidateInputs helperClass = new ValidateInputs();
		ArrayList<String> fileContents = readFile(args[0]);
		helperClass.checkInputs(fileContents);
		
		int capacityLot;
		int numEntrances;
		int numExits;
		int numFloors;
		float rate;
		int numCarsParked;
		String nameGarage;
		String whileOperation;
		String operationPerform;
		boolean continueOperation = true;
		
		// Constructing parking lot based off parameters passed in through text file
		capacityLot = Integer.parseInt(fileContents.get(0));
		numFloors = Integer.parseInt(fileContents.get(1));
		numEntrances = Integer.parseInt(fileContents.get(2));
		numExits = Integer.parseInt(fileContents.get(3));
		rate = Float.parseFloat(fileContents.get(4));
		nameGarage = fileContents.get(5);
		validateInputs (capacityLot, numEntrances, numExits, rate);
		ParkingLot clientParkingLot = new ParkingLot(nameGarage ,capacityLot, numEntrances, numExits, rate, numFloors);
		
		// Populate the parking lot with dates for easier simulation
		numCarsParked = Integer.parseInt(fileContents.get(6));
		clientParkingLot.setCarsTickets(fileContents, numCarsParked);
		
		String tempBoolHolder;
		String optionSelected;
		
		Scanner sc = new Scanner(System.in);
		
		while (continueOperation == true)
		{
			System.out.println("Would you like to continue simulations? Please enter either (Y/N)");
			whileOperation = sc.next();
			continueOperation = VerifyOperation(whileOperation);
			
			// Ask the user what they'd like to do next
			
			System.out.println("What would you like to do next? P - Profit related commands | V - simulate vehicle entering/exitting the parking Lot | G - Group related commands");
			operationPerform = sc.next();
			VerifySimulation(operationPerform);
			
			// Call respective functions depending on what is read in
			if (operationPerform.equals("P"))
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
			else if (operationPerform.equals("V"))
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
			else if (operationPerform.equals("G"))
			{
				ParkingGroup.listGroupOperations();
				optionSelected = sc.next();
				
			}
			
			
			
		}
		
		
		
		sc.close();
		
		
		
	}
	
	
	
	
	
}
