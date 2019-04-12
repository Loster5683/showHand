package com.company;

import java.util.ArrayList;

public class TongHua extends CardType
{
	private int type;
	private int number;
	private int color;
	private ArrayList<Card> cards;

	public TongHua(ArrayList<Card> cards, int number, int color)
	{
		this.number = number;
		this.color = color;
		type = 5;
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

		if(this.number != ((TongHua)o).number)
		{
			return this.compare(this.number,((TongHua)o).number);
		}

		return compare(((TongHua)o).color,this.color);
	}

}
