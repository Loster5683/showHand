package com.company;

import java.util.ArrayList;

public class ShunZi extends CardType
{
	private int type;
	private int number;
	private int color;
	private ArrayList<Card> cards;

	public ShunZi(ArrayList<Card> cards, int number, int color)
	{
		this.number = number;
		this.color = color;
		type = 6;
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

		if(this.number != ((ShunZi)o).number)
		{
			return this.compare(this.number,((ShunZi)o).number);
		}

		return compare(((ShunZi)o).color,this.color);
	}

}