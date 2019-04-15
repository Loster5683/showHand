/**
 * 文件名：DanDui.java
 * 描述：  单对牌型类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */

package com.company.cardTypes;

import java.util.ArrayList;

/**
 * @author 作者  yeqiang
 * 单对类型
 */
public class DanDui implements CardType
{
	// 类型
	private int type;
	// 单对对子的大小
	private int number;
	// 单对对子的最大花色
	private int color;
	// 卡牌组
	private ArrayList<Card> cards;

	/**
	 * 功能： 构造函数
	 *
	 * @param cards  卡组
	 * @param number 对子大小
	 * @param color  对子最大花色
	 */
	public DanDui(ArrayList<Card> cards, int number, int color)

	{
		this.cards = cards;
		this.number = number;
		this.color = color;
		type = 9;
	}

	public int getColor()
	{
		return color;
	}

	public int getNumber()
	{
		return number;
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
		//再比大小
		if (this.number != ((DanDui)(o)).getNumber())
		{
			return Integer.compare(this.number, ((DanDui)o).getNumber());
		}
		//最后比花色
		return Integer.compare(((DanDui)o).getColor(), this.color);
	}

	public String toString()
	{
		return types[type];
	}

}