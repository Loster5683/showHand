package com.company;

import java.util.ArrayList;

public class Hulu extends CardType
{
	private int type;
	private int number;
	private int number2;
	private int color;
	private ArrayList<Card> cards;

	public Hulu(ArrayList<Card> cards, int number, int number2, int color)
	{
		this.number = number;
		this.number2 = number2;
		this.color = color;
		type = 4;
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

	public int getNumber2(){
		return number2;
	}

	@Override
	public int compareTo(CardType o)
	{
		if(this.type != o.type)
		{
			return super.compareTo(o);
		}

		if(this.number != ((Hulu)o).number)
		{
			return this.compare(this.number,((Hulu)o).number);
		}

		if(this.number2 != ((Hulu)o).number2)
		{
			return this.compare(this.number2,((Hulu)o).number2);
		}

		return compare(((Hulu)o).color,this.color);
	}
}