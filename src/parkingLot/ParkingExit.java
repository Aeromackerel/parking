package parkingLot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class ParkingExit
{
	double totalProfit;
	ArrayList <Integer> totalProfitPerDay;
	ArrayList <String> timeStampDay;
	
	// Helper functions
	
	/*
	 * checks if the datetime is valid
	 * 
	 * @ params - datetime of ticket
	 * @ errors - Exception if we have trouble parsing the ticket
	 * 
	 */
	
	protected boolean isValidDate (String inDate)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		dateFormat.setLenient(false);
		System.out.println(inDate);
		
		try
		{dateFormat.parse(inDate.trim());}
		catch (ParseException pe)
		{return false;}
		
		
		return true;
	}
	
	 /* checks if the current date passed is is ==
	 * to the day of the last entry - if not then
	 * we make a new entry to the totalProfit Array
	 * 
	 * @ params - datetime stamp of current day
	 * @ error - datetime later than current datetime -> throw exception*/
	
	private boolean newEntryHelper (String currentDate)
	{
		// Get the last date
		String lastDate = timeStampDay.get(timeStampDay.size() - 1);
		
		
		
		return true;
	}
	
	
	 /* Given the ticket and the previous datetime
	 * we take that and subtract from the current datetime,
	 * then multiply by the rate per hour
	 * 
	 * @ params - Datetime string
	 * @ errors - If ticket is later than the current datetime
	 * then they don't pay.*/
	
	public void PayTicket (String ticket, float hourlyRate)
	{
		if (!isValidDate(ticket))
			System.out.println("Invalid ticket - please exit");
		
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm");  
		
		// Otherwise, we evaluate the ticket
		String currentDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());
		
		System.out.println(ticket);
		System.out.println(currentDateTime);
		
		Date d1 = null;
		Date d2 = null;
		try
		{
			d1 = format.parse(ticket);
			d2 = format.parse(currentDateTime);
		}
		catch (ParseException e)
		{e.printStackTrace();}
		
		long diff = d2.getTime() - d1.getTime();
		
		double diffDouble = diff/(1000 * 60);
		diffDouble /= 60;
		System.out.println(diffDouble);
		
		totalProfit += diffDouble * hourlyRate;
		
		
		
		System.out.println("To exit, you'll have to pay the following amount : $ " + hourlyRate * diffDouble );
		
	}
	
	
}
