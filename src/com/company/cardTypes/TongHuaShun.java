/**
 * 文件名：TongHuaShun.java
 * 描述：  同花顺类型类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */

package com.company.cardTypes;

import com.company.parseTypes.ParamCount;
import java.util.ArrayList;

public class TongHuaShun implements CardType
{
	// 关键卡牌集合，用于对同类型的牌组进行比较
	private ArrayList<Card> keyCards;
	// 牌组统计信息
	private ParamCount paramCount;
	// 类型
	private int type;

	public TongHuaShun(ParamCount paramCount)
	{
		this.type = 2;
		this.paramCount = paramCount;
		this.keyCards = this.getKeyCards();
	}

	public static boolean isTongHuaShun(int colors, int ghostCount, ArrayList<Integer> values)
	{
		if (colors != 1)
		{
			return false;
		}
		return (ShunZi.isShunZi(values, ghostCount) != 0);
	}

	@Override
	public int getType()
	{
		return type;
	}

	@Override
	public int compareTo(CardType o)
	{
		int result;

		if (this.type != o.getType())
		{
			result = Integer.compare(o.getType(), type);
		}
		else
		{
			result = this.keyCards.get(0).compareTo(o.getKeyCards().get(0));
		}

		return result;
	}

	@Override
	public ArrayList<Card> getKeyCards()
	{
		ArrayList<Card> keyCards = new ArrayList<Card>();
		int color = this.paramCount.getColors().get(0);
		int value = ShunZi.isShunZi(paramCount.getValues(), paramCount.getGhostCount());
		keyCards.add(new Card(value, color));

		return keyCards;
	}

	public String toString()
	{
		return types[type];
	}

}
