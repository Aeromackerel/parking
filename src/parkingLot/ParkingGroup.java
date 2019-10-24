package parkingLot;

import java.util.ArrayList;

public class ParkingGroup 
{
	ArrayList<ParkingLot> List_ParkingLots;
	ArrayList <Float> List_Discounts = new ArrayList<>();
	
	protected static void listGroupOperations()
	{
		System.out.println("Please choose from the following operations");
		System.out.println("--------------------------------------------");
		System.out.println("1 - Send Prices to Vehicles");
		System.out.println("2 - Adjust policies for a select parking lot");
		System.out.println("3 - Adjust discounts for a select parking lot");
		System.out.println("4 - Default discounts to 0 for ALL parking lots");
	}
	
	private void sendPrices()
	{
		for (int i = 0; i < List_ParkingLots.size(); i++)
		{
			float fixedPrices = List_ParkingLots.get(i).getHourlyRate() * List_Discounts.get(i);
		}
	}
	

}
