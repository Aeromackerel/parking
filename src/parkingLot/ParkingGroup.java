package parkingLot;

import java.util.ArrayList;

public class ParkingGroup 
{
	String groupName;
	ArrayList<ParkingLot> List_ParkingLots = new ArrayList<>();
	ArrayList <Float> List_Discounts = new ArrayList<>();
	
	// Constructors
	public ParkingGroup(String name)
	{
		this.groupName = name;
	}
	
	protected void checkIndex(int indexChosen, int lengthParkingGroupList)
	{
		if (indexChosen-1 >= lengthParkingGroupList)
		{
			System.out.println("Invalid index chosen - exitting program");
			System.exit(1);
		}
	}
	
	protected void ParkingLotNames()
	{
		int index = 1;
		
		for (ParkingLot pL : List_ParkingLots)
		{
			System.out.println(index + " - " + pL.parkingLotName);
			index += 1;
		}
	}
	
	protected void handlePolicy(int index, String policy)
	{
		if (index < 0 || index > List_ParkingLots.size())
		{
			System.out.println("Input a correct index");
			System.exit(1);
		}
		index -= 1;
		ParkingLot selectedLot = List_ParkingLots.get(index);
		
		selectedLot.setPolicy(policy);
	}
	
	protected void handleDiscount(int index, float discount)
	{
		// Check if the discount is valid
		if (discount < 0.0 || discount > 100.0)
		{
			System.out.println(discount + " is an invalid amount to set - exitting program");
			System.exit(1);
		}
		
		index -= 1;
		List_Discounts.set(index, discount);
		System.out.println("Successfully set " + List_ParkingLots.get(index).parkingLotName + " Discount to : " + discount);
	}
	
	protected void rollbackDiscounts()
	{
		for (int i = 0; i < List_Discounts.size(); i++)
			List_Discounts.set(i, (float) 0.0);
		
		System.out.println("Successfully rollbacked all the discounts for ALL lots -> 0.0");
	}
	
	
	protected static void listGroupOperations()
	{
		System.out.println("Please choose from the following operations");
		System.out.println("--------------------------------------------");
		System.out.println("1 - Send Prices to Vehicles");
		System.out.println("2 - Adjust policies for a select parking lot");
		System.out.println("3 - Adjust discounts for a select parking lot");
		System.out.println("4 - Default discounts to 0 for ALL parking lots");
	}
	
	protected void sendPrices()
	{
		for (int i = 0; i < List_ParkingLots.size(); i++)
		{
			float fixedPrices = List_ParkingLots.get(i).getHourlyRate() - (List_Discounts.get(i)/100 * List_ParkingLots.get(i).getHourlyRate());
			System.out.println(List_ParkingLots.get(i).parkingLotName + " price : $" + fixedPrices + " per hour");
		}
	}
	

}
