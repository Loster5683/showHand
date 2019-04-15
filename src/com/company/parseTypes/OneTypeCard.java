/**
 * 文件名：OneTypeCard.java
 * 描述：  卡组不同大小牌种类为1时的解析方法
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */

package com.company.parseTypes;

import com.company.cardTypes.Card;
import com.company.cardTypes.CardType;
import com.company.cardTypes.WuGui;
import java.util.ArrayList;
import java.util.HashMap;

public class OneTypeCard extends ParseCards
{

	public OneTypeCard(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount)
	{
		super(cards, colors, values, hashMap, ghostCount);
	}

	public CardType getType()
	{
		return new WuGui(cards);
	}

}
