package parkingLot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ParkingEntrance
{
		
	// Helper functions
	
	/* Gets the params from the parking lot class
	 * and prints to the vehicle to see the contents
	 * of the garage*/
	
	
	public String DisplayInfoAndDecide(Vehicle vehicleIn,int capacity, float rate)
	{
		String parkingType;
		
		System.out.println("The current available spots in the lot : " + capacity);
		System.out.println("Our hourly rate is : $" + rate + " per hour");
		
		Scanner tempScanner = new Scanner(System.in);
		System.out.println("Would you like to continue with parking in this parking lot?");
		System.out.println("Please enter (Y/N)");
		String userInput = tempScanner.next();
		
		if (userInput.equals("Y"))
		{
			DetermineParkingType(vehicleIn);
			return RecieveTicket();
		}
		else if (userInput.equals("N"))
			return "";
		else
			return "";
		
	}
	
	private void DetermineParkingType(Vehicle vehicleIn)
	{
		String parkingTypeClassification = "";
		Scanner Scanner = new Scanner(System.in);
		System.out.println("What parking type do you need?");
		System.out.println("R - regular parking, C - compact parking, H - handicapped parking");
		parkingTypeClassification = Scanner.next();
		
		switch (parkingTypeClassification)
		{
		case("R"):
			vehicleIn.requiredSpace = ParkingType.REGULAR;
			break;
		case("C"):
			vehicleIn.requiredSpace = ParkingType.COMPACT;
			break;
		case("H"):
			vehicleIn.requiredSpace = ParkingType.HANDICAPPED;
			break;
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
