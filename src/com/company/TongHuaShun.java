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


	public int getColor()
	{
		return color;
	}

	public int getNumber()
	{

		return number;
	}

    @Override
    protected int compare(int a, int b) {
        if(a>b)
        {
            return 1;
        }
        else if(a<b)
        {
            return -1;
        }
        return 0;
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
			return compare(o.getType(),type);
		}

		if(this.number != ((TongHuaShun)o).getNumber())
		{
			return this.compare(this.number,((TongHuaShun)o).getNumber());
		}

		return compare(((TongHuaShun)o).getColor(),this.color);
	}

    public String toString()
    {
        return types[type];
    }
}
