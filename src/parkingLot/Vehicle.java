package parkingLot;

public class Vehicle
{
	
	// Later implement size and license plate number to keep track of vehicle
	// Field variables
	String ticketAttached;
	
	// Helper functions
	
	public Vehicle (String ticketIn)
	{
		this.ticketAttached = ticketIn;
	}
	
	
	// static functions
	
	public static void listVehicleOperations()
	{
		System.out.println("Please choose from the following operations");
		System.out.println("--------------------------------------------");
		System.out.println("1 - Enter the parking Lot");
		System.out.println("2 - Exit and pay");
	}
	
	
}
