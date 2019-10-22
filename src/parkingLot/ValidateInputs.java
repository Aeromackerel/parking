package parkingLot;

import java.util.ArrayList;

public class ValidateInputs 
{
	// No field variables because this is solely a helper function class
	
	// Functions section
	
	private void isNumber (String stringIn)
	{
		try
		{
			Integer.parseInt(stringIn);
		}
		catch (NumberFormatException e)
		{
			System.out.println(stringIn + " is not a number, please take a look at the inputs again.");
			System.out.println("Terminating program");
			System.exit(1);
		}
	}
	
	private void isFloat (String stringIn)
	{
		try
		{
			Float.parseFloat(stringIn);
		}
		catch (NumberFormatException e)
		{
			System.out.println(stringIn + " is not a valid float, please take a look at the inputs again.");
			System.out.println("Terminating program");
			System.exit(1);
		}
		
	}
	
	public void checkInputs(ArrayList<String> fileContents)
	{
		if (fileContents.size() < 4)
		{
			System.out.println("Invalid inputs - terminating the program");
			System.exit(1);
		}
		
		isNumber(fileContents.get(0));
		isNumber(fileContents.get(1));
		isNumber(fileContents.get(2));
		isFloat(fileContents.get(3));
	}
	
}
