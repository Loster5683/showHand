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

	public int getColor()
	{
		return color;
	}

	public int getNumber()
	{

		return number;
	}

    @Override
    public int getType() {
        return type;
    }

	@Override
	public int compareTo(CardType o)
	{
		if(this.type != o.type)
		{
			return compare(o.getType(),type);
		}

		if(this.number != ((Hulu)o).getNumber())
		{
			return this.compare(this.number,((Hulu)o).getNumber());
		}

		if(this.number2 != ((Hulu)o).getNumber())
		{
			return this.compare(this.number2,((Hulu)o).getNumber2());
		}

		return compare(((Hulu)o).getColor(),this.color);
	}

    public int getNumber2() {
	    return number2;
    }

    public String toString()
    {
        return types[type];
    }
}