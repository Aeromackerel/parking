package parkingLot;

public class Vehicle
{
	
	// Later implement size and license plate number to keep track of vehicle
	// Field variables
	String ticketAttached;
	String plateNumber;
	ParkingType requiredSpace;	
	
	// Helper functions
	
	public Vehicle() 
	{
		this.requiredSpace = null;
		this.ticketAttached = null;
	}
	
	public Vehicle (String ticketIn)
	{this.ticketAttached = ticketIn;}
	
	protected void setPlate(String plateIn)
	{this.plateNumber = plateIn;}
	
	protected void addTicket (String ticketIn)
	{this.ticketAttached = ticketIn;}
	
	
	// Static functions
	
	public static void listVehicleOperations()
	{
		System.out.println("Please choose from the following operations");
		System.out.println("--------------------------------------------");
		System.out.println("1 - Enter the parking Lot");
		System.out.println("2 - Exit and pay");
	}
	
	
}
