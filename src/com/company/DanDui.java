package com.company;

import java.util.ArrayList;

public class DanDui extends CardType
{
	private int type;
	private int number;
	private int color;
	private ArrayList<Card> cards;

	public DanDui(ArrayList<Card> cards, int number, int color)
	{
		this.number = number;
		this.color = color;
		type = 9;
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

		if(this.number != ((DanDui)o).number)
		{
			return this.compare(this.number,((DanDui)o).number);
		}

		return compare(((DanDui)o).color,this.color);
	}
}
