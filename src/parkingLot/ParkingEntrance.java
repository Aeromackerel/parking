package parkingLot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParkingEntrance
{
		
	// Helper functions
	
	/* Gets the params from the parking lot class
	 * and prints to the vehicle to see the contents
	 * of the garage*/
		
	public String DisplayInfoAndDecide(Vehicle vehicleIn,int capacity, float rate, ArrayList<Integer> capacityPerFloor, HashSet<String> plateTracker)
	{
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
		
		System.out.println("Please enter your vehicles license plate:");
		String vehiclePlate = tempScanner.next();
		
		boolean plateFlag = checkValidPlate(vehiclePlate);
		
		if (plateFlag == false) return "";
		
		vehicleIn.setPlate(vehiclePlate);
		
		if (!plateTracker.contains(vehiclePlate))
			plateTracker.add(vehiclePlate);
		else
		{
		    System.out.println("You cannot park here because our records indicate another plate in the system identical to what you've inputted");
		    return "";
		}
		
		
		
		if (userInput.equals("Y"))
		{
			return RecieveTicket();
		}
		else if (userInput.equals("N"))
			return "";
		else
			return "";
		
	}
	
	
	private boolean checkValidPlate(String plateIn)
	{
		Matcher m = Pattern.compile("[A-Z][A-Z]([A-Z]|\\d)\\d\\d").matcher(plateIn);
		if (m.find()) 
		{
		    System.out.println(plateIn + " is a valid number plate");
		    return true;
		}
		else 
		{
		    System.out.println(plateIn + " is not a valid number plate");
		    return false;
		}
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
