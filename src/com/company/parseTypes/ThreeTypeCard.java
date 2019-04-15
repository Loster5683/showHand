/**
 * 文件名：ThreeTypeCard.java
 * 描述：  卡组不同大小牌种类为3时的解析方法
 * 创建人：yeqiang
 * 创建时间：2019/4/15
*/

package com.company.parseTypes;

import com.company.cardTypes.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreeTypeCard extends ParseCards
{

	public ThreeTypeCard(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount)
	{
		super(cards, colors, values, hashMap, ghostCount);
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
		//有三张鬼牌
		if (ghostCount == 3)
		{
			//除去鬼牌剩余牌颜色只有一种
			if (colors.size() == 1)
			{
				//如果能够构成顺子则为同花顺,否则构成四条,同花顺之间比较需要颜色，所以需要记录花色
				if (isConnected(values, ghostCount))
				{
					color = colors.get(0);
					return new TongHuaShun(cards, this.getNumber(), color);
				}
				else
				{
					//四条需要记录四条的值，单张的值与四条花色,让三张鬼与大牌组成四条
					// 如果最小值为
					if (values.get(0) == 1)
					{
						color = this.getColor(1);
						number = changeA(values.get(0));
						//number2 = values.get(1);
					}
					else
					{
						number = values.get(1);
						//number2 = values.get(0);
						color = this.getColor(number);
					}
					return new SiTiao(cards, number, color);
				}
			}

			//剩下牌颜色不只一种,直接构成四条
			else
			{
				// 如果最小值为A
				if (values.get(0) == 1)
				{
					color = this.getColor(1);
					number = changeA(values.get(0));
					number2 = values.get(1);
				}
				else
				{
					number = values.get(1);
					number2 = values.get(0);
					color = this.getColor(number);
				}
				return new SiTiao(cards, number, color);
			}
		}
		// 两张鬼牌，剩余牌组成为2-1，构成四条，取对子与鬼牌组成四条
		else if (ghostCount == 2)
		{
			//找到对子对应的值,四条的值为对应对子值，并记录单牌值
			for (int j = 0;j< values.size();j++)
			{
				if (hashMap.get(values.get(j)).size() == 2)
				{
					number = changeA(values.get(j));
					color = this.getColor(values.get(j));
				}
				if (hashMap.get(values.get(j)).size() == 1)
				{
					number2 = changeA(values.get(j));
				}
			}
			return new SiTiao(cards, number, color);
		}

		// 一张鬼牌 剩余四张牌有1-3和2-2的分法，一三分类型为四条，二二分为葫芦
		else if (ghostCount == 1)
		{
			boolean flag = false;
			// 遍历数字统计表
			for (Integer a : hashMap.keySet())
			{
				// 过滤鬼牌
				if (a == 0)
				{
					continue;
				}
				// 找到四条的值
				if (hashMap.get(a).size() == 3)
				{
					flag = true;
					color = getColor(a);
					number = changeA(a);
				}
				//找到单张的值
				if (hashMap.get(a).size() == 1)
				{
					number2 = changeA(a);
				}
				// 二二分根据数字大小找到三条与对子的值，构成葫芦
				if (hashMap.get(a).size() == 2)
				{
					// 如果最小值为A
					if (values.get(0) == 1)
					{
						color = this.getColor(1);
						number = changeA(values.get(0));
						number2 = values.get(2);
					}
					else
					{
						number = values.get(2);
						number2 = values.get(0);
						color = this.getColor(number);
					}
					break;
				}
			}
			return (flag) ? new SiTiao(cards, number, color) : new HuLu(cards, number, number2, color);
		}
		// 没有鬼牌，五张牌一四分或二三分，一四分为四条，二三分为葫芦
		else if (ghostCount == 0)
		{
			boolean flag = false;
			// 找到不同分法的对应值
			for (Integer a : hashMap.keySet())
			{
				if (a == 0)
				{
					continue;
				}
				// 构成四条
				if (hashMap.get(a).size() == 4)
				{
					flag = true;
					color = getColor(a);
					number = changeA(a);
				}
				// 构成葫芦 number1 与color
				if (hashMap.get(a).size() == 3)
				{
					number = changeA(a);
					color = getColor(a);
				}
				// 记录葫芦number2
				if (hashMap.get(a).size() == 2)
				{
					number2 = changeA(a);
				}
				// 四条无需记录number2
				if (hashMap.get(a).size() == 1)
				{
					continue;
				}
			}
			return (flag) ? new SiTiao(cards, number, color) : new HuLu(cards, number, number2, color);
		}
		// 不符合条件的情况
		else
		{
			System.out.println("Wrong type");
			return new WrongType();
		}
	}
}




