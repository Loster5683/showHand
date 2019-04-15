/**
 * 文件名：TongHuaShun.java
 * 描述：  同花顺类型类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */

package com.company.cardTypes;

import java.util.ArrayList;

public class TongHuaShun implements CardType
{
	//类型
	private int type;
	//大小
	private int number;
	//花色
	private int color;
	//卡组
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
	public int getType()
	{
		return type;
	}

	@Override
	public int compareTo(CardType o)
	{
		if (this.type != o.getType())
		{
			return Integer.compare(o.getType(), type);
		}

		if (this.number != ((TongHuaShun)o).getNumber())
		{
			return Integer.compare(this.number, ((TongHuaShun)o).getNumber());
		}

		return Integer.compare(((TongHuaShun)o).getColor(), this.color);
	}

	public String toString()
	{
		return types[type];
	}
}
