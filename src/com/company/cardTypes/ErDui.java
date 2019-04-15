/**
 * 文件名：ErDui.java
 * 描述：  二对类型类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */

package com.company.cardTypes;

import java.util.ArrayList;

public class ErDui implements CardType
{
	// 类型
	private int type;
	// 大对子的大小
	private int number;
	// 小对子大小
	private int number2;
	// 大对子的最大花色
	private int color;
	// 卡牌组
	private ArrayList<Card> cards;

	/**
	 * 功能：   构造函数
	 *
	 * @param cards  卡组
	 * @param number 大对子大小
	 * @param cards  小对子大小
	 * @param color  大对子颜色
	 */
	public ErDui(ArrayList<Card> cards, int number, int number2, int color)
	{
		this.cards = cards;
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
	public int getType()
	{
		return type;
	}

	public int getNumber2()
	{
		return number2;
	}

	@Override
	public int compareTo(CardType o)
	{
		if (this.type != o.getType())
		{
			return Integer.compare(o.getType(), type);
		}

		if (this.number != ((ErDui)o).number)
		{
			return Integer.compare(this.number, ((ErDui)o).getNumber());
		}

		if (this.number2 != ((ErDui)o).number2)
		{
			return Integer.compare(this.number2, ((ErDui)o).getNumber2());
		}

		return Integer.compare(((ErDui)o).getColor(), this.color);
	}

	public String toString()
	{
		return types[type];
	}
}