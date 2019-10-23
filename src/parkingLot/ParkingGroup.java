package parkingLot;

import java.util.ArrayList;

public class ParkingGroup 
{
	ArrayList<ParkingLot> List_ParkingLots;
	ArrayList <Float> List_Discounts = new ArrayList<>();
	
	protected void listGroupCommands()
	{
		
	}
	
	private void sendPrices()
	{
		for (int i = 0; i < List_ParkingLots.size(); i++)
		{
			float fixedPrices = List_ParkingLots.get(i).getHourlyRate() * List_Discounts.get(i);
		}
	}
	

}
