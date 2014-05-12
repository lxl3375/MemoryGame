/**
 * $Id: CheatFrame.java,v 1.1 2013/04/21 22:29:23 lxl3375 Exp $
 * $Log: CheatFrame.java,v $
 * Revision 1.1  2013/04/21 22:29:23  lxl3375
 * First revision of lab6
 *
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Class definition for the cheating window in a concentration card game.
 * 
 * @author: Lai-Chung Lau, lxl3375@g.rit.edu
 */
public class CheatFrame extends JFrame
{
	/**
	 * construct a CheatFrame object
	 * @param cardButtons, an ArrayList of CardButtons that are all showing their numbers.
	 * @param size, The size (of one side) of the board (measured in cards).
	 */
	public CheatFrame(ArrayList<CardButton> cardButtons, int size)
	{
		setLayout(new GridLayout(size, size, 2, 2));
		
		//adding button to the frame
		for (int i = 0; i < cardButtons.size(); i++)
		{
			CardButton button = cardButtons.get(i);
			add(button);
		}
		
		setTitle("Cheat Concentration Game");
		setSize(300,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
