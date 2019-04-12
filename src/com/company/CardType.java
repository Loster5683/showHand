package com.company;

import java.util.ArrayList;

public class CardType implements Comparable<CardType>
{
	protected int type;
	private ArrayList<Card> cards;

	/**
	 * @function  简单的比较函数
	 * @param    a,b
	 * @return    返回三种关系对应的值，1表示a>b,-1表示a<b,0表示a=b
	 */
	protected int compare(int a,int b)
	{
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
	public int compareTo(CardType o)
	{
		return (compare(o.type,this.type));
	}

	@Override
	public String toString()
	{
		return cards.toString();
	}
}
