/**
 * 文件名：ParseCards.java
 * 描述：  卡组解析类的类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */
package com.company.parseTypes;

import com.company.cardTypes.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ParseCards
{

	// 用于记录顺子的大小
	protected int number;
	// 牌组
	protected ArrayList<Card> cards;
	// 统计的非鬼牌的颜色数组
	protected ArrayList<Integer> colors;
	// 统计的非鬼牌的数值数组
	protected ArrayList<Integer> values;
	// 统计的牌组不同值的卡的颜色
	protected HashMap<Integer, ArrayList<Integer>> hashMap;
	// 统计当前卡组的鬼牌数
	protected int ghostCount;

	public ParseCards(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount)
	{
		this.cards = cards;
		this.colors = colors;
		this.values = values;
		this.hashMap = hashMap;
		this.ghostCount = ghostCount;
	}

	protected int getColor(Integer integer)
	{
		Collections.sort(hashMap.get(integer));
		return hashMap.get(integer).get(0);
	}

	/**
	 * @param a 卡牌对应数值
	 *
	 * @return 卡牌转换后数值
	 */
	protected int changeA(Integer a)
	{
		return (a == 1) ? 14 : a;
	}

	public int getNumber()
	{
		return number;
	}

	protected boolean isConnected(ArrayList<Integer> values, int size)
	{
		// 非鬼卡牌与鬼牌数总和应该为5
		if (values.size() + size != 5)
		{
			System.out.println("Wrong param");
		}
		// 三种鬼牌
		if (size == 3)
		{
			int minValue = values.get(0);
			int maxValue = values.get(1);
			//两张卡数值相差小于5则可以构成顺子
			if (maxValue - minValue <= 4)
			{
				number = (minValue + 4 <= 13) ? minValue + 4 : 14;
				return true;
			}
			// 可以构成TJQKA的顺子
			if (minValue == 1 && maxValue >= 10)
			{
				number = 14;
				return true;
			}
			return false;
		}

		// 两张鬼
		if (size == 2)
		{
			int maxValue = values.get(2);
			int midValue = values.get(1);
			int minValue = values.get(0);
			// 最大最小牌值相差小于4，说明三张牌加两张鬼能够构成顺子
			if (maxValue - minValue <= 4)
			{
				number = (minValue + 4 <= 13) ? minValue + 4 : 14;
				return true;
			}
			// 构成TJQKA型顺子的情况
			if (minValue == 1 && midValue >= 10)
			{
				number = 14;
				return true;
			}
			return false;
		}
		// 一张鬼
		if (size == 1)
		{
			// 记录最大值最小值与第二大值，第二大值用于判断是否存在TJQKA型顺子
			int maxValue = values.get(3);
			int midValue = values.get(1);
			int minValue = values.get(0);
			// 最大最小牌值相差小于4，说明三张牌加两张鬼能够构成顺子
			if (maxValue - minValue <= 4)
			{
				number = (minValue + 4 <= 13) ? minValue + 4 : 14;
				return true;
			}
			if (minValue == 1 && midValue >= 10)
			{
				number = 14;
				return true;
			}
			return false;
		}
		//没有鬼牌
		if (size == 0)
		{
			int maxValue = values.get(4);
			int minValue = values.get(0);
			int midValue = values.get(1);
			if (maxValue - minValue == 4)
			{
				number = maxValue;
				return true;
			}
			if (minValue == 1 && midValue == 10)
			{
				number = 14;
				return true;
			}
			return false;
		}
		return false;
	}
}