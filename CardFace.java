/**
 * CardFace.java
 *
 * File:
 *	$Id: CardFace.java,v 1.1 2013/04/21 22:29:24 lxl3375 Exp $
 *
 * Revisions:
 *	$Log: CardFace.java,v $
 *	Revision 1.1  2013/04/21 22:29:24  lxl3375
 *	First revision of lab6
 *
 */

/**
 * The interface that unites Cards and CardBacks.
 *
 * @author: Arthur Nunes-Harwitt
 * @editor: Lai-Chung Lau, lxl3375@g.rit.edu
 */

public interface CardFace {
    
    /**
     * Get the value indicating whether or not the card is face-up.
     *
     * @return A boolean indicating whether or not the card is face-up.
     */
    public boolean isFaceUp();
    
    /**
     * Get the number on the card.
     *
     * @return An integer that is the number on the card.
     */
    public int getNumber();

}