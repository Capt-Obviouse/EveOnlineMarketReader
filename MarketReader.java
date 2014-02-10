/*****************************************************************************************************
 * MarketReader.java
 * Capt Obviouse
 * Version 2.0
 * Reads EvE Market Exports and returns profitable orders for sale in Jita 
*****************************************************************************************************/

package marketReading;


import java.io.File;
import java.io.IOException;


public class MarketReader 
{
	public static Settings settings = new Settings();
	public static String destFold = settings.getOutFolder();  // Reads Settings for the Destination Folder
	
	//*****************************************************************************************************
	
	public static void main(String[] args) throws IOException
	{		
		Window.displayWindow();
		if(!settings.isSettingsGood())
		{
			do
			{
				settings.setFolders();
			} while (!settings.isSettingsGood());
		}
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e1) 
		{ }
		String destFold = settings.getOutFolder();  // Reads Settings for the Destination Folder
		delFile(destFold, "bin\\Prices.txt");
		delFile(destFold, "BestPrices.txt");
		delFile(destFold, "ExcelPrices.txt");
		Window.addText("Reading market data", 1, 20, 20, 20);
		ReaderFolder.readFolder();		
		Window.addText("Calculating Profitable Trades", 1, 20, 20, 20);
		FindOutput.findOutput();
		Window.addText("Exporting", 1, 30, 30, 30);
		try
		{
			Thread.sleep(3000);
		}
		catch (Exception e)
		{ }
		Window.addText("Completed", 1, 30, 30, 30);
	} // end main
	
	//*****************************************************************************************************
	
	public static void delFile(String destFold, String fileName)
	{
		File theFile = new File(destFold + fileName);
		if(theFile.delete()){
			System.out.println("Cleaning up old files: " + fileName);
		}
		else
		{
			theFile = new File(fileName);
			if(theFile.delete())
			{
				System.out.println("Cleaning up old files: " + fileName);
			} 
			else 
			{
				System.out.println("Delete Failed: " + fileName);
			}
		} // End if theFile.delete
	} // End delFile
} // end MarketReader





