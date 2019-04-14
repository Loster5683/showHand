package com.company;

import java.util.ArrayList;

public class SiTiao extends CardType
{
	private int type;
	private int number;
	private int color;
	private ArrayList<Card> cards;

	public SiTiao(ArrayList<Card> cards, int number, int color)
	{
		this.number = number;
		this.color = color;
		type = 3;
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

		if(this.number != ((SiTiao)o).getNumber())
		{
			return this.compare(this.number,((SiTiao)o).getNumber());
		}

		return compare(((SiTiao)o).getColor(),this.color);
	}

    public String toString()
    {
        return types[type];
    }
}
