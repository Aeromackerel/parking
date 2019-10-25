package parkingLot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ParkingEntrance
{
		
	// Helper functions
	
	/* Gets the params from the parking lot class
	 * and prints to the vehicle to see the contents
	 * of the garage*/
	
	
	public String DisplayInfoAndDecide(Vehicle vehicleIn,int capacity, float rate, ArrayList<Integer> capacityPerFloor)
	{
		String parkingType;
		
		System.out.println("The current available spots in the lot : " + capacity);
		System.out.println("Our hourly rate is : $" + rate + " per hour");
		
		System.out.println("The current capacity for the lots are listed : ");
		
		int floorIndex = 1;
		
		for (int i : capacityPerFloor)
		{
			System.out.println("Floor " + floorIndex);
			System.out.println("Number of lots : " + i);
			floorIndex += 1;
		}
		
		Scanner tempScanner = new Scanner(System.in);
		System.out.println("Would you like to continue with parking in this parking lot?");
		System.out.println("Please enter (Y/N)");
		String userInput = tempScanner.next();
		
		if (userInput.equals("Y"))
		{
			return RecieveTicket();
		}
		else if (userInput.equals("N"))
			return "";
		else
			return "";
		
	}
	
	 /* Passes back the current date and time
	 * to the person that will recieve the
	 * parking ticket
	 * @ params - none
	 * @ errors -none */
	
	private String RecieveTicket()
	{
		String sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());
		return sdf;
	}

	
	
	
}
