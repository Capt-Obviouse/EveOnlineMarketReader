package marketReading;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReaderFolder 
{
	public static void readFolder() throws IOException
	{
		String cpu = MarketReader.settings.getMarketFolder();
		ArrayList<Double> ar = new ArrayList<Double>();
		ArrayList<Double> ar2 = new ArrayList<Double>();
		ArrayList<File> all = new ArrayList<File>();
		String line;
		String itsprice;
		String says = "price";
		Map<String, String> inMap = new HashMap<String, String>();
		int counter = 0;
		addTree(new File(cpu), all);
		for(Iterator<File> i = all.iterator(); i.hasNext(); )
		{
			File item = i.next();
			try 
			{
				BufferedReader br = new BufferedReader(new FileReader(item));
				while ((line = br.readLine()) != null)
				{
					String[] price = line.split(",");
					itsprice = price[0];
					if(!itsprice.equals(says))
					{
						inMap.put(price[0], price[0] + "," + price[2] + "," + price[11]);
						if(price[7].equals("False"))
						{
							Double x = Double.parseDouble(price[0]);
							Double result = x;
							ar.add(result);
						}
						if(price[7].equals("True"))
						{
							Double c = Double.parseDouble(price[0]);
							Double results = c;
							ar2.add(results);
						}
						counter = counter+1;
					} // End if(!itsprice.equals(says))
				}
				br.close();
			}	 
			catch (Exception e)
			{
				Window.showMessage("No Files in your market folder. Exiting");
				System.exit(0);
			}
			int itsPos = 0;
			Double largest = ar.get(0);
			for(int x = 0; x < ar.size(); x++)
			{
				if(ar.get(x)<largest)
				{
					largest = ar.get(x);
					itsPos = x;
				}
			}
			int itsPos2 = 0;
			Double smallest = ar2.get(0);
			for(int g = 0; g < ar2.size(); g++)
			{
				if(ar2.get(g)>smallest)
				{
					smallest = ar2.get(g);
					itsPos2 = g;
				}
	
			}

			String theAr = ar.get(itsPos).toString();
			Double inBound = Double.parseDouble(theAr);
			DecimalFormat cows = new DecimalFormat("####.0###");
			String results = cows.format(inBound);
			String theFalse = inMap.get(results);
			String[] theFalse2 = theFalse.split(",");
			String theAr2 = ar2.get(itsPos2).toString();
			Double inBound2 = Double.parseDouble(theAr2);
			DecimalFormat cows2 = new DecimalFormat("####.0###");
			String results2 = cows2.format(inBound2);
			String theTrue = inMap.get(results2);
			try
			{
				PrintWriter writer = new PrintWriter(
						new BufferedWriter(new FileWriter("bin\\Prices.txt", true)));
				writer.println(theFalse2[0] + "," + theTrue);
				writer.close();
			} 
			catch (Exception e)
			{
				Window.showMessage("Bin folder not found! Please create a bin folder in your directory!");
			}
			ar.clear();
			ar2.clear();
		} // End for
	} // End readFolder
	
	//*****************************************************************************************************
	
	public static void addTree(File file, ArrayList<File> all) 
	{
		File[] children = file.listFiles();
		if(children != null)
		{
			for (File child : children)
			{
				all.add(child);
				addTree(child, all);
			}
		}
	} // End addTree
}
