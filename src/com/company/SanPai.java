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

		if(this.number != ((SanPai)o).getNumber())
		{
			return this.compare(this.number,((SanPai)o).getNumber());
		}

		return compare(((SanPai)o).getColor(),this.color);
	}

    public String toString()
    {
        return types[type];
    }
}
