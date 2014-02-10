package marketReading;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FindOutput 
{
	public static void findOutput() throws IOException{
		String destFold = MarketReader.settings.getOutFolder();
		ArrayList<String> sPrice = new ArrayList<String>();
		ArrayList<String> bPrice = new ArrayList<String>();	
		Map<String, String> jitaPrices = new HashMap<String, String>();
		ArrayList<Double> sellersPrice = new ArrayList<Double>();
		ArrayList<Double> buyersPrice = new ArrayList<Double>();
		String line;
		try 
		{
			BufferedReader br2 = new BufferedReader(new FileReader("bin\\Prices.txt"));
			while ((line = br2.readLine()) != null)
			{
				String[] prices = line.split(",");
				if(prices[3].equals("10000002"))
				{
					jitaPrices.put(prices[2], prices[0] + "," + prices[3]);
				}
				else 
				{
					Double conPrice = Double.parseDouble(prices[0]);
					Double bConPrice = Double.parseDouble(prices[1]);
					sPrice.add(prices[0] + "," + prices[2] +  "," + prices[3]); // Sellers Price
					bPrice.add(prices[1] + "," + prices[2] + "," + prices[3]); // Buyers Price	
					sellersPrice.add(conPrice);
					buyersPrice.add(bConPrice);		
				} // end else
			} // end while ((line = br2.readLine()) != null)
			br2.close();
	
	//**********************************************************************
	// Sellers Section
	//**********************************************************************

			int p = 0;
			PrintWriter excel2Writer = new PrintWriter(new BufferedWriter
					(new FileWriter(destFold + "ExcelPrices.txt", true)));
			excel2Writer.println("Type ID,Jita Price,Region Price,Region,Profit Margin,Profit");
			excel2Writer.close();
			String jitaValues = "00.0,10000002";
			while (sellersPrice.size() > p)
			{
				String[] sellPrice = sPrice.get(p).split(",");
				Double jitaValue;
				try 
				{					
					jitaValues = jitaPrices.get(sellPrice[1]);
				} 
				catch (Exception e)
				{
					jitaValues = "0.00,10020342";
				} // end catch
				if(jitaValues != null){	
				} 
				else 
				{
					jitaValues = "0.00,10023232";	
				} // end else	
				String[] jValue = jitaValues.split(",");
				jitaValue = Double.parseDouble(jValue[0]);
				Double regValue = Double.parseDouble(sellPrice[0]);
				if(jitaValue > regValue)
				{
					Double ans1 = (jitaValue - regValue) / jitaValue;
					Double profits = jitaValue - regValue;
					DecimalFormat cows = new DecimalFormat("#,###.00##");
					String results = cows.format(profits);
					if(ans1 > 0.15)
					{
						PrintWriter ans1writer = new PrintWriter(new BufferedWriter
								(new FileWriter(destFold + "BestPrices.txt", true)));
						ans1writer.println("Type ID: " + conID(destFold, sellPrice[1]) + " | Jita Price: " + 
								numForm(jValue[0]) + " | Region Price: " + numForm(sellPrice[0]) +
								" | Region: " + conID(destFold, sellPrice[2]) + 
								" | Profit Margin: " + numDec(ans1) + " | Profit: " + results);
						ans1writer.close();
						PrintWriter excelWriter = new PrintWriter(new BufferedWriter
								(new FileWriter(destFold + "ExcelPrices.txt", true)));
						excelWriter.println(conID(destFold, sellPrice[1]) + ",\"" + 
								numForm(jValue[0]) + "\",\"" + numForm(sellPrice[0]) + "\"," +
								conID(destFold, sellPrice[2]) + 
								",\"" + numDec(ans1) + "\",\"" + results + "\"");
						excelWriter.close();
					}
					else if(profits > 19000000)
					{
						PrintWriter ans1writer = new PrintWriter(new BufferedWriter
									(new FileWriter(destFold + "BestPrices.txt", true)));
						ans1writer.println("Type ID: " + conID(destFold, sellPrice[1]) + " | Jita Price: " + 
									numForm(jValue[0]) + " | Region Price: " + numForm(sellPrice[0]) +
									" | Region: " + conID(destFold, sellPrice[2]) + 
									" | Profit Margin: " + numDec(ans1) + " | Profit: " + results);
						ans1writer.close();
						PrintWriter excelWriter = new PrintWriter(new BufferedWriter
									(new FileWriter(destFold + "ExcelPrices.txt", true)));
						excelWriter.println(conID(destFold, sellPrice[1]) + ",\"" + 
									numForm(jValue[0]) + "\",\"" + numForm(sellPrice[0]) + "\"," +
									conID(destFold, sellPrice[2]) + 
									",\"" + numDec(ans1) + "\",\"" + results + "\"");
						excelWriter.close();
					} // end else if
				} // end if		
				p++;
			} // End while
	
	//**********************************************************************
	// Buyers Section
	//**********************************************************************
	
			p = 0;
			while (buyersPrice.size() > p)
			{
				String[] buyPrice = bPrice.get(p).split(",");			
				Double jitaValue;
				try 
				{					
					jitaValues = jitaPrices.get(buyPrice[1]);
				} 
				catch (Exception e)
				{
					jitaValues = "0.00, 10020342";
				} // end try/catch
				if(jitaValues != null)
				{ } 
				else 
				{
					jitaValues = "0.00,10023232";
				} // end if/else
				String[] jValue = jitaValues.split(",");
				jitaValue = Double.parseDouble(jValue[0]);
				Double regValue = Double.parseDouble(buyPrice[0]);
				if(jitaValue > regValue)
				{
					Double ans1 = (jitaValue - regValue) / jitaValue;
					Double profits = jitaValue - regValue;
					DecimalFormat cows = new DecimalFormat("#,###.00##");
					String results = cows.format(profits);
					if(ans1 > 0.15)
					{							
						PrintWriter ans1writer = new PrintWriter(new BufferedWriter
								(new FileWriter(destFold + "BestPrices.txt", true)));
						ans1writer.println("Type ID: " + conID(destFold, buyPrice[1]) + " | Jita Price: " + 
								numForm(jValue[0]) + " | Region Price: " + numForm(buyPrice[0]) +
								" | Region: " + conID(destFold, buyPrice[2]) + 
								" | Profit Margin: " + numDec(ans1) + " | Profit: " + results);
						ans1writer.close();
						PrintWriter excelWriter = new PrintWriter(new BufferedWriter
								(new FileWriter(destFold + "ExcelPrices.txt", true)));
						excelWriter.println(conID(destFold, buyPrice[1]) + ",\"" + 
								numForm(jValue[0]) + "\",\"" + numForm(buyPrice[0]) + "\"," +
								conID(destFold, buyPrice[2]) + 
								",\"" + numDec(ans1) + "\",\"" + results + "\"");
						excelWriter.close();	
					}
					else if(profits > 19000000)
					{
						PrintWriter ans1writer = new PrintWriter(new BufferedWriter
									(new FileWriter(destFold + "BestPrices.txt", true)));
						ans1writer.println("Type ID: " + conID(destFold, buyPrice[1]) + " | Jita Price: " + 
									numForm(jValue[0]) + " | Region Price: " + numForm(buyPrice[0]) +
									" | Region: " + conID(destFold, buyPrice[2]) + 
									" | Profit Margin: " + numDec(ans1) + " | Profit: " + results);
						ans1writer.close();	
						PrintWriter excelWriter = new PrintWriter(new BufferedWriter
									(new FileWriter(destFold + "ExcelPrices.txt", true)));
						excelWriter.println(conID(destFold, buyPrice[1]) + ",\"" + 
									numForm(jValue[0]) + "\",\"" + numForm(buyPrice[0]) + "\"," +
									conID(destFold, buyPrice[2]) + 
									",\"" + numDec(ans1) + "\",\"" + results + "\"");
						excelWriter.close();
					} // end else if
				} // end if
				p++;
			} // end while
			System.out.println("Complete");
		} 
		catch (FileNotFoundException e)
		{
			Window.showMessage("No Files in your market folder. Exiting");
			System.exit(0);
		} // end try/catch
		catch (IOException e)
		{
			Window.showMessage("Settings Error");
			MarketReader.delFile(null, "Settings.txt");
			Thread t = new Thread()
			{
				public void run()
				{
					String[] args = {};
					try 
					{
						MarketReader.main(args);
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					} // end try/catch
				} //end Run
			}; // end new thread t
			t.start();
		}
	} // end findOutput
	
	//*****************************************************************************************************
	
	public static String conID(String destFold, String theID) throws IOException
	{
		try 
		{
			BufferedReader br3 = new BufferedReader(new FileReader("typeID.txt"));
			String line;
			Map<String, String> mMap = new HashMap<String, String>();
			while ((line = br3.readLine()) != null)
			{
				String[] breakLine = line.split(",");
				mMap.put(breakLine[0], breakLine[1]);
			}
			br3.close();
			try
			{
				String newID = mMap.get(theID).toString();
				return newID;
			} 
			catch (Exception e)
			{
			return theID;
			}
		} 
		catch (IOException e)
		{
			theID = "IOEXception";
			e.printStackTrace();
			return theID;
		}
	} // end conID
	
	//*****************************************************************************************************
	
	public static String numForm(String dBound)
	{
		Double inBound = Double.parseDouble(dBound);
		DecimalFormat cows = new DecimalFormat("#,###.00##");
		String results = cows.format(inBound);
		return results;
	} // End numForm
	
	//*****************************************************************************************************
	
	public static String numDec(Double dBound)
	{
		DecimalFormat df = new DecimalFormat("#%");
		String result = df.format(dBound);
		return result;
	} // End numDec
	
	//*****************************************************************************************************
}
