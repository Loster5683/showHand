/**
 * 文件名：SixTypeCard.java
 * 描述：  卡组不同大小牌种类为6时的解析方法
 * 创建人：yeqiang
 * 创建时间：2019/4/15
*/

package com.company.parseTypes;

import com.company.cardTypes.Card;
import com.company.cardTypes.CardType;
import com.company.cardTypes.TongHuaShun;
import com.company.cardTypes.SanPai;
import com.company.cardTypes.ShunZi;
import com.company.cardTypes.TongHua;
import java.util.ArrayList;
import java.util.HashMap;

public class SixTypeCard extends ParseCards
{

	public SixTypeCard(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount)
	{
		super(cards, colors, values, hashMap, ghostCount);
	}

	/**
	 * 功能：  判断牌组是否构成顺子
	 * @param    values 除去鬼牌后不同的数字
	 * @param    size 牌组鬼牌数量
	 * @return   是否组成顺子
	*/
	@Override
	protected boolean isConnected(ArrayList<Integer> values, int size)
	{
		return super.isConnected(values, size);
	}

	/**
	 * 功能： 牌型解析函数
	 * @return  解析的牌型
	*/
	public CardType getType()
	{

		int color = 0;
		int number = 0;
		int number2 = 0;

		// 同花
		if (colors.size() == 1)
		{
			// 同花顺
			if (isConnected(values, ghostCount))
			{
				color = colors.get(0);
				return new TongHuaShun(cards, this.getNumber(), color);
			}
			// 普通同花
			else
			{
				number = (values.get(0) == 1) ? 1 : values.get(4);
				color = getColor(number);
				number = (values.get(0) == 1) ? 14 : values.get(4);
				return new TongHua(cards, number, color);
			}
		}
		// 非同花
		else
		{
			// 是顺子
			if (isConnected(values, ghostCount))
			{
				int tmp = 0;
				tmp = (values.get(0) == 1) ? 1 : values.get(4);
				color = getColor(tmp);
				return new ShunZi(cards, this.getNumber(), color);
			}
			// 散牌
			else
			{
				number = (values.get(0) == 1) ? 1 : values.get(4);
				color = getColor(number);
				number = (values.get(0) == 1) ? 14 : values.get(4);
				return new SanPai(cards, number, color);
			}
		}
	}
}
