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
			return super.compare(o.getType(),type);
		}

		if(this.number != ((ShunZi)o).getNumber())
		{
			return this.compare(this.number,((ShunZi)o).getNumber());
		}

		return compare(((ShunZi)o).getColor(),this.color);
	}

    public String toString()
    {
        return types[type];
    }
}