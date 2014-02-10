package marketReading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Settings 
{
	private String marketFolderPath;
	private String outputFolder;
	private String firstRun = "false"; // True if it is the first run of the program
	private double profitMargin;
	private double totalProfit;
	
	//**********************************************************************
	
	public void setFolders() throws IOException
	{
		String slashd = "\\";
		String itsGood = "no";
		String userEveMarket = "e";
		PrintWriter twriter = new PrintWriter(new FileWriter("Settings.txt", false));
		do 
		{
			while(userEveMarket.length() < 2)
			{
				userEveMarket = JOptionPane.showInputDialog(
						null, "Please enter the EvE Market " +
						"Export Folder Ex: C:\\My Documents\\EvE Market Folder\\");
				if(userEveMarket == null)
				{
					System.exit(0);
				} // end if
			} 
			String lastChar = userEveMarket.substring(userEveMarket.length() - 1);
			while(!lastChar.equals(slashd))
			{
				userEveMarket = userEveMarket + slashd;
				lastChar = userEveMarket.substring(userEveMarket.length() - 1);
				if(userEveMarket == null)
				{
					System.exit(0);
				}
			} // end while
			try
			{
				if(userEveMarket.length() > 1)
				{
					userEveMarket = userEveMarket.replaceAll("\\\\", "\\\\\\\\");
					if(userEveMarket.substring(userEveMarket.length() - 1).equals(slashd))
					{
						twriter.println("EvE Market Folder=" + userEveMarket);
						this.marketFolderPath = userEveMarket;
						itsGood = "yes";
					} 
					else 
					{
						JOptionPane.showMessageDialog(
								null, "Market Folder requires a back slash \" \\ \" at the end");
					} // end else
				} 
				else 
				{
					JOptionPane.showMessageDialog(
							null, "Market Folder requires an input");
				} // end else
					
			} 
			catch (Exception e) 
			{ } 
		} while(!itsGood.equals("yes"));
		itsGood = "no";
		String userDest = "e";
		do 
		{
			while(userDest.length() < 2)
			{
				userDest = JOptionPane.showInputDialog(
						null, "Please enter the Output Folder Ex: " +
						"C:\\My Documents\\Output Folder\\");
				if(userDest == null)
				{
					System.exit(0);
				}
			} // end while
			String lastChar = userDest.substring(userDest.length() - 1);
			while(!lastChar.equals(slashd))
			{
				userDest = userDest + slashd;
				lastChar = userDest.substring(userDest.length() - 1);
				if(userDest == null)
				{
					System.exit(0);
				} 
			} // end while
			try{
				if(userDest.length() > 1)
				{
					userDest = userDest.replaceAll("\\\\", "\\\\\\\\");
					if(userDest.substring(userDest.length() - 1).equals(slashd))
					{
					System.out.println("You entered: " + userDest);
					twriter.println("Program output to Folder=" + userDest);
					twriter.println("First=false");
					itsGood = "yes";
					} 
					else 
					{
					JOptionPane.showMessageDialog(
							null, "Destination Folder requires a back slash \" \\ \" at the end");
					}
				} 
				else 
				{
					JOptionPane.showMessageDialog(null, "Destination Folder requires an input");
				}
			} // End Try 
			catch (Exception e) 
			{ }
		} while(!itsGood.equals("yes"));
		twriter.close();
	} // end setMarketFolder()
	
	//**********************************************************************
	
	public String getMarketFolder()
	{
		return this.marketFolderPath;
	}
	
	//**********************************************************************
	
	public String getOutFolder()
	{
		return this.outputFolder;
	}
	
	//**********************************************************************

	public double getProfitMargin()
	{
		return this.profitMargin;
	}
	
	//**********************************************************************
	
	public double getTotalProfit()
	{
		return this.totalProfit;
	}
	//**********************************************************************
	
	public boolean isSettingsGood()
	{
		try 
		{
			BufferedReader br3 = new BufferedReader(new FileReader("Settings.txt"));
			ArrayList<String> walkLine = new ArrayList<String>();
			String inLine;
			while ((inLine = br3.readLine()) != null)
			{
				walkLine.add(inLine);
			}
			br3.close();
				try
				{
					String[] marketFold = walkLine.get(0).split("=");
					this.marketFolderPath = marketFold[1];
					String[] outFold = walkLine.get(1).split("=");
					this.outputFolder = outFold[1];
					String[] outFirst = walkLine.get(2).split("=");
					this.firstRun = outFirst[1];
					if(this.firstRun.equals("true"))
					{
						System.out.println("first run!");
						return false;
					}
					else
					{
						return true;
					}
				} 
				catch (IndexOutOfBoundsException e)
				{
					System.out.println("index out of bounds");
					return false;
				}	
				catch (Exception e)
				{
					System.out.println("Other");
					return false;
				}
		}
		catch (IOException e)
		{
			return false;
		}
	} // End isSettingsGood()
} // End Class
