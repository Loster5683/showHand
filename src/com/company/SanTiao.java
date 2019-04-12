package com.company;

import java.util.ArrayList;

public class SanTiao extends CardType
{
	private int type;
	private int number;
	private int color;
	private ArrayList<Card> cards;

	public SanTiao(ArrayList<Card> cards, int number, int color)
	{
		this.number = number;
		this.color = color;
		type = 7;
	}

	public int getType()
	{
		return type;
	}

	public int getColor()
	{
		return color;
	}

	public int getNumber()
	{

		return number;
	}

	@Override
	public int compareTo(CardType o)
	{
		if(this.type != o.type)
		{
			return super.compareTo(o);
		}

		if(this.number != ((SanTiao)o).number)
		{
			return this.compare(this.number,((SanTiao)o).number);
		}

		return compare(((SanTiao)o).color,this.color);
	}
}
