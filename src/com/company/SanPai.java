package com.company;

import java.util.ArrayList;

public class SanPai extends CardType
{
	private int type;
	private int number;
	private int color;
	private ArrayList<Card> cards;

	public SanPai(ArrayList<Card> cards, int number, int color)
	{
		this.number = number;
		this.color = color;
		type = 10;
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

		if(this.number != ((SanPai)o).number)
		{
			return this.compare(this.number,((SanPai)o).number);
		}

		return compare(((SanPai)o).color,this.color);
	}
}
