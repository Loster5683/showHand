package com.company;

import java.util.ArrayList;

public class CardType implements Comparable<CardType>
{
	protected int type;
    protected ArrayList<Card> cards;
    protected String [] types = {"五鬼","五条","同花顺","四条","葫芦","同花","顺子","三条","二对","单对","散牌"};

    public int getType() {
        return type;
    }

    /**
	 * 简单的比较函数
	 * @param  a  参数1
     * @param  b  参数2
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
		return (compare(this.type,o.type));
	}

	@Override
	public String toString()
	{
        return types[type];
	}
}
