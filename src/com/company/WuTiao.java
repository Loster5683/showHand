package com.company;

import java.util.ArrayList;

public class WuTiao extends CardType
{
	private int type;
	private int number;
	private ArrayList<Card> cards;

	public WuTiao(ArrayList<Card> cards, int number)
	{
		this.number = number;
		type = 1;
	}

    @Override
    public int getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    @Override
	public int compareTo(CardType o)
	{
		if(this.type != o.getType())
		{
			return super.compare(o.getType(),this.type);
		}
		return compare(this.number,((WuTiao)o).getNumber());
	}

    public String toString()
    {
        return types[type];
    }
}
