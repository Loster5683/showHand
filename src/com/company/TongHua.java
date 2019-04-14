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
		if(this.type != o.getType())
		{
			return super.compare(o.getType(),type);
		}

		if(this.number != ((TongHua)o).getNumber())
		{
			return this.compare(this.number,((TongHua)o).getNumber());
		}

		return compare(((TongHua)o).getColor(),this.color);
	}

    public String toString()
    {
        return types[type];
    }

}
