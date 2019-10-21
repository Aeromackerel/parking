package parkingLot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingEntrance
{
		
	// Helper functions
	
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
		String sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		return sdf;
	}

	
	
	
}
