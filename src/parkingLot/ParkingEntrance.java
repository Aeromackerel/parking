package parkingLot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingEntrance
{
		
	// Helper functions
	
	/* Gets the params from the parking lot class
	 * and prints to the vehicle to see the contents
	 * of the garage
	 */
	
	
	public void DisplayInfo(int capacity, float rate)
	{
		System.out.println("The current available spots in the lot : " + capacity);
		System.out.println("Our hourly rate is : $" + rate + " per hour");
		
		// Ask user if they want to continue with parking
	}
	
	/*
	 * Passes back the current date and time
	 * to the person that will recieve the
	 * parking ticket
	 * 
	 * @ params - none
	 * @ errors -none
	 * 
	 */
	
	public String RecieveTicket()
	{
		String sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());
		return sdf;
	}

	
	
	
}
