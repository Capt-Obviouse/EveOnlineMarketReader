package marketReading;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window 
{
	private	static JFrame working = new JFrame();
	private static JPanel panel = new JPanel();
	private static JLabel labelContent2 = new JLabel();
	
	//**************************************************************************************
	
	public static void displayWindow()
	{
		panel.setLayout(new FlowLayout());
		JLabel labelTitle = new JLabel(
			"<html><center><b><u>Market Reader</u></b></center></html>",null,JLabel.CENTER);
		labelTitle.setFont(new Font("Arial",0 , 28));
		labelTitle.setVerticalAlignment(JLabel.TOP);
		panel.add(labelTitle);
		labelTitle.setBorder(BorderFactory.createEmptyBorder( 30 /*top*/, 0, 0, 0 ));
		JLabel labelContent = new JLabel(
			"<html><center><i>Created by Capt Obviouse</i>" +
			"</center></html>",null,JLabel.CENTER);
		panel.add(labelContent);
		labelContent.setBorder(BorderFactory.createEmptyBorder( 5 /*top*/, 20, 20, 20 ));
		working.setSize(250, 500);
		working.add(panel);
		working.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		working.setVisible(true);
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e1) 
		{ }
	} // End displayWindow
	
	//**************************************************************************************
	
	public static void addText(String text, int a, int b, int c, int d)
	{
		labelContent2 = new JLabel("<html><center><br>" + text + "</center></html>");
		panel.add(labelContent2);
		labelContent2.setBorder(BorderFactory.createEmptyBorder( a /*top*/, b, c, d ));
		working.setVisible(true);
	} // End addText()
	
	//**************************************************************************************
	
	public static void showMessage(String text)
	{
		JOptionPane.showMessageDialog(null, text);
	}
} // End Window Class
