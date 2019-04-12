package com.company;

import java.util.ArrayList;

public class WuGui extends CardType
{
	private int type;
	private ArrayList<Card> cards;

	public WuGui(ArrayList<Card> cards)
	{
		this.cards = cards;
		type = 0;
	}

	@Override
	public int compareTo(CardType o)
	{
		return super.compareTo(o);
	}
}
