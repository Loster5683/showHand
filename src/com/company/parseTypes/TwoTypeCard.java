/**
 * 文件名：TwoTypeCard.java
 * 描述：  卡组不同大小牌种类为2时的解析方法
 * 创建人：yeqiang
 * 创建时间：2019/4/15
*/

package com.company.parseTypes;

import com.company.cardTypes.Card;
import com.company.cardTypes.CardType;
import com.company.cardTypes.WuTiao;
import java.util.ArrayList;
import java.util.HashMap;

public class TwoTypeCard extends ParseCards
{

	public TwoTypeCard(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount)
	{
		super(cards, colors, values, hashMap, ghostCount);
	}

	/**
	 * 功能： 牌型解析函数
	 * @return  解析的牌型
	 */
	public CardType getType()
	{
		int number = 0;
		for (Integer i : hashMap.keySet())
		{
			//找到不为鬼牌的牌的值
			if (i != 0)
			{
				number = changeA(i);
				break;
			}
		}
		return new WuTiao(cards, number);
	}
}
