/**
 * 文件名：DanDui.java
 * 描述：  单对牌型类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */
package com.company.cardTypes;

import com.company.parseTypes.ParamCount;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 作者  yeqiang
 * 单对类型
 */
public class DanDui implements CardType
{
	// 关键卡牌集合，用于对同类型的牌组进行比较
	private ArrayList<Card> keyCards;
	// 牌组统计信息
	private ParamCount paramCount;
	// 类型
	private int type;

	public DanDui(ParamCount paramCount)
	{
		type = 9;
		this.paramCount = paramCount;
		this.keyCards = this.getKeyCards();
	}

	// 判断类型是否为单对
	public static boolean isDanDui(int ghostCount, int values, HashMap<Integer, ArrayList<Integer>> hashMap)
	{
		if (values != 4)
		{
			return false;
		}

		int MaxCount = 0;

		for (int index : hashMap.keySet())
		{
			if (index == 0)
			{
				continue;
			}
			MaxCount = Math.max(MaxCount, hashMap.get(index).size());
		}

		return (MaxCount + ghostCount == 2);
	}

	@Override
	public int getType()
	{
		return type;
	}

	/**
	 * 功能：  重写compareTo方法
	 *
	 * @param o 类型为CardType的比较对象
	 *
	 * @return 当前对象与比较对象的牌型大小关系
	 */
	@Override
	public int compareTo(CardType o)
	{
		//先比类型
		if (this.type != o.getType())
		{
			return Integer.compare(o.getType(), type);
		}
		//如果类型相同则判断对子大小与最大花色
		else
		{
			return this.keyCards.get(0).compareTo(o.getKeyCards().get(0));
		}
	}

	/**
	 * 获取用于同类型卡牌组大小比较的关键卡牌
	 *
	 * @return
	 */
	@Override
	public ArrayList<Card> getKeyCards()
	{
		ArrayList<Card> cards = new ArrayList<Card>();
		int value = 0;
		int color = 0;

		//没有鬼牌的情况
		if (paramCount.getGhostCount() == 0)
		{
			//找到组成对子的卡牌
			for (int valueTmp : paramCount.getValues())
			{
				if (paramCount.getHashMap().get(valueTmp).size() == 2)
				{
					value = valueTmp;
					color = paramCount.getHashMap().get(value).get(0);
					break;
				}
			}
		}
		//有鬼牌
		else
		{
			//找最大卡牌
			for (int valueTmp : paramCount.getValues())
			{
				value = Math.max(value, valueTmp);
			}
			color = paramCount.getHashMap().get(value).get(0);
		}

		cards.add(new Card(value, color));
		return cards;
	}

	public String toString()
	{
		return types[type];
	}
}