/**
 * $Id: CardButton.java,v 1.1 2013/04/21 22:29:23 lxl3375 Exp $
 * $Log: CardButton.java,v $
 * Revision 1.1  2013/04/21 22:29:23  lxl3375
 * First revision of lab6
 *
 */

import javax.swing.*;

/**
 * Class definition for a button that represents a card in the concentration game.
 * 
 * @author: Lai-Chung Lau, lxl3375@g.rit.edu
 */

public class CardButton  extends JButton
{
	private int position;
	
	/**
	 * Construct a CardButton object.
	 * @param pos, The position or index of the represented card in the model.
	 */
	public CardButton(int pos)
	{
		super("");
		position = pos;
		
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
	}
	
	/**
	 * Get the position of the card.
	 * @return An integer that is the position or index of the represented card in the model.
	 */
	public int getPos()
	{
		return position;
	}
}
