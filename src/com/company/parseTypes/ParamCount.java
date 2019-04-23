/**
 * 文件名：ParamCount.java
 * 描述：  类型解析类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */

package com.company.parseTypes;

import com.company.cardTypes.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ParamCount
{
	private HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();
	private ArrayList<Integer> colors = new ArrayList<Integer>();
	private ArrayList<Integer> values = new ArrayList<Integer>();
	private int ghostCount = 0;

	public ParamCount(ArrayList<Card> cards)
	{
		if (cards.size() != 5)
		{
			System.out.println("Wrong type");
		}
		/**
		 *  局部变量
		 * 	colors用于存储除了鬼牌之外的牌的所有颜色
		 * 	values用于存储除了鬼牌之外的所有牌的数字
		 * 	ghostCount 英语存储卡组鬼牌数量
		 */

		//初始化，约定鬼牌即使在牌组中不存在也应该占据空位
		hashMap.put(0, new ArrayList<Integer>());

		//遍历卡牌组，对牌组进行统计
		for (int j = 0; j < cards.size(); ++j)
		{
			int cardValue = cards.get(j).getValue();
			//map中有该卡牌的值则在对应颜色数组加入该颜色
			if (hashMap.containsKey(cardValue))
			{
				//不为鬼的牌不可能出现花色与值相同的情况，出现提示卡组错误
				if (hashMap.get(cardValue).contains(cards.get(j).getColor()))
				{
					if (cardValue != 0)
					{
						System.out.println("Card repeated");
						continue;
					}
				}
				hashMap.get(cardValue).add(cards.get(j).getColor());
			}
			//不存在对应值的牌则将对应值加入hashMap
			else
			{
				ArrayList<Integer> colorsTmp = new ArrayList<Integer>();
				colorsTmp.add(cards.get(j).getColor());
				hashMap.put(cardValue, colorsTmp);
			}
			// 过滤鬼牌
			if (cards.get(j).isGhost())
			{
				ghostCount++;
				continue;
			}
			// 统计非鬼牌颜色
			if (!colors.contains(cards.get(j).getColor()))
			{
				colors.add(cards.get(j).getColor());
			}
			// 统计非鬼牌数值
			if (!values.contains(cardValue))
			{
				values.add(cardValue);
			}
		}

		//排序方便取值
		Collections.sort(values);
		for (ArrayList<Integer> arrayList : hashMap.values())
		{
			Collections.sort(arrayList);
		}
	}

	public HashMap<Integer, ArrayList<Integer>> getHashMap()
	{
		return hashMap;
	}

	public ArrayList<Integer> getColors()
	{
		return colors;
	}

	public ArrayList<Integer> getValues()
	{
		return values;
	}

	public int getGhostCount()
	{
		return ghostCount;
	}
}