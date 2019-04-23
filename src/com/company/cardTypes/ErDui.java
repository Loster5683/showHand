/**
 * 文件名：ErDui.java
 * 描述：  二对类型类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */

package com.company.cardTypes;

import com.company.parseTypes.ParamCount;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 二对类
 * 负责记录四条类型的牌与判断是否为四条类型
 *
 * @author 作者  yeqiang
 * @version 1.1 2019/4/15
 */
public class ErDui implements CardType
{
	// 关键卡牌集合，用于对同类型的牌组进行比较
	private ArrayList<Card> keyCards;
	// 牌组统计信息
	private ParamCount paramCount;
	// 类型
	private int type;

	public ErDui(ParamCount paramCount)
	{
		this.paramCount = paramCount;
		this.keyCards = this.getKeyCards();
		type = 8;
	}

	// 判断是否为二对
	public static boolean isErDui(int ghostCount, int values, HashMap<Integer, ArrayList<Integer>> hashMap)
	{
		//值种类不为3这不构成二对
		if (values != 3)
		{
			return false;
		}

		//找到相同数量最多的值
		int MaxCount = 0;
		for (int index : hashMap.keySet())
		{
			if (index == 0)
			{
				continue;
			}
			MaxCount = Math.max(MaxCount, hashMap.get(index).size());
		}

		//相同数量最多的卡牌的数目与鬼牌数和为2，则必为二对
		return (MaxCount + ghostCount == 2);
	}

	@Override
	public int getType()
	{
		return type;
	}

	@Override
	public int compareTo(CardType o)
	{
		//先比类型
		if (this.type != o.getType())
		{
			return Integer.compare(o.getType(), type);
		}
		//比大对子大小
		else if (Integer.compare(this.keyCards.get(0).getValue(), o.getKeyCards().get(0).getValue()) != 0)
		{
			return Integer.compare(this.keyCards.get(0).getValue(), o.getKeyCards().get(0).getValue());
		}
		//比小对子大小
		else if (Integer.compare(this.keyCards.get(1).getValue(), o.getKeyCards().get(1).getValue()) != 0)
		{
			return Integer.compare(this.keyCards.get(0).getValue(), o.getKeyCards().get(0).getValue());
		}
		//比大对子花色
		else
		{
			return this.keyCards.get(0).compareTo(o.getKeyCards().get(0));
		}
	}

	/**
	 * 获取关键性卡牌，二对有两张，分别对应两个对子
	 */
	@Override
	public ArrayList<Card> getKeyCards()
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		int[] valuesTmp = new int[2];
		int index = 0;
		int value;
		int color;

		//找到两个对子的值
		for (int valueTmp : paramCount.getValues())
		{
			if (paramCount.getHashMap().get(valueTmp).size() == 2)
			{
				valuesTmp[index++] = valueTmp;
			}
		}

		// 区分大小对与对应花色
		value = (valuesTmp[0] > valuesTmp[1]) ? valuesTmp[0] : valuesTmp[1];
		color = paramCount.getHashMap().get(value).get(0);
		cards.add(new Card(value, color));

		value = (valuesTmp[0] > valuesTmp[1]) ? valuesTmp[1] : valuesTmp[0];
		color = paramCount.getHashMap().get(value).get(0);
		cards.add(new Card(value, color));

		return cards;
	}

	public String toString()
	{
		return types[type];
	}
}