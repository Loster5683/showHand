package com.company;

import java.util.ArrayList;

public class TongHuaShun extends CardType
{
	private int type;
	private int number;
	private int color;
	private ArrayList<Card> cards;

	public TongHuaShun(ArrayList<Card> cards, int number, int color)
	{
		this.number = number;
		this.color = color;
		type = 2;
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

		if(this.number != ((TongHuaShun)o).getNumber())
		{
			return this.compare(this.number,((TongHuaShun)o).number);
		}

		return compare(((TongHuaShun)o).color,this.color);
	}

}
