package com.company;

import java.util.ArrayList;

public class ErDui extends CardType
{
	private int type;
	private int number;
	private int number2;
	private int color;
	private ArrayList<Card> cards;

	public ErDui(ArrayList<Card> cards, int number, int number2, int color)
	{
		this.number = number;
		this.number2 = number2;
		this.color = color;
		type = 8;
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

	public int getNumber2(){
		return number2;
	}

	@Override
	public int compareTo(CardType o)
	{
		if(this.type != o.type)
		{
			return compare(o.getType(),type);
		}

		if(this.number != ((ErDui)o).number)
		{
			return this.compare(this.number,((ErDui)o).getNumber());
		}

		if(this.number2 != ((ErDui)o).number2)
		{
			return this.compare(this.number2,((ErDui)o).getNumber2());
		}

		return compare(((ErDui)o).getColor(),this.color);
	}

    public String toString()
    {
        return types[type];
    }
}