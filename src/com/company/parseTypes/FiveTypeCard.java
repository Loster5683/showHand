/**
 * 文件名：FiveTypeCard.java
 * 描述：  卡组不同大小牌种类为5时的解析方法
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */

package com.company.parseTypes;

import com.company.cardTypes.*;
import com.company.gameProcess.Log;
import java.util.ArrayList;
import java.util.HashMap;

public class FiveTypeCard extends ParseCards
{

	public FiveTypeCard(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount)
	{
		super(cards, colors, values, hashMap, ghostCount);
	}

	/**
	 * 功能：通过构造方法传入的参数解析牌组的牌型
	 *
	 * @return 返回解析后的牌型
	 */

	public CardType getType()
	{

		//color 记录解析类型的颜色
		int color;
		//number 记录解析类型的大小
		int number;

		// 有一张鬼牌
		if (ghostCount == 1)
		{
			// 颜色相同
			if (colors.size() == 1)
			{
				// 构成同花顺
				if (isConnected(values, ghostCount))
				{
					color = colors.get(0);
					return new TongHuaShun(cards, this.getNumber(), color);
				}
				// 不构成顺子，则为一般同花,因为有一张鬼牌，所以最大为A
				else
				{

					number = 14;
					color = 0;
					return new TongHua(cards, number, color);
				}
			}
			// 颜色不同
			else
			{
				// 构成顺子
				if (isConnected(values, ghostCount))
				{
					color = 0;
					return new ShunZi(cards, this.getNumber(), color);
				}
				// 单对
				else
				{
					number = (values.get(0) == 1) ? 1 : values.get(3);
					color = getColor(number);
					number = (values.get(0) == 1) ? 14 : values.get(3);
					return new DanDui(cards, number, color);
				}
			}
		}
		// 没有鬼牌 直接构成单对
		else if (ghostCount == 0)
		{    //找到对子值
			for (Integer a : hashMap.keySet())
			{
				if (hashMap.get(a).size() == 2)
				{
					number = changeA(a);
					color = getColor(a);
					return new DanDui(cards, number, color);
				}
			}
		}
		Log.write("鬼牌数错误");
		return new WrongType();
	}
}
