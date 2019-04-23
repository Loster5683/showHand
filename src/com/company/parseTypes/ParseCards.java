/**
 * 文件名：ParseCards.java
 * 描述：  卡组解析类的类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */
package com.company.parseTypes;

import com.company.cardTypes.*;
import java.util.ArrayList;

public class ParseCards
{

	// 用于记录顺子的大小
	protected int number;
	ParamCount paramCount;

	public ParseCards(ArrayList<Card> cards)
	{
		this.paramCount = new ParamCount(cards);
	}

	public static int getColor(ParamCount pa, int index)
	{
		return pa.getHashMap().get(index).get(0);
	}

	/**
	 * @param a 卡牌对应数值
	 *
	 * @return 卡牌转换后数值
	 */
	public static int changeA(Integer a)
	{
		return (a == 1) ? 14 : a;
	}

	public CardType parseType()
	{
		if (WuGui.isWuGui(paramCount.getGhostCount()))
		{
			return new WuGui();
		}
		else if (WuTiao.isWuTiao(paramCount.getGhostCount(), paramCount.getValues().size()))
		{
			return new WuTiao(paramCount);
		}
		else if (TongHuaShun.isTongHuaShun(paramCount.getColors().size(), paramCount.getGhostCount(), paramCount.getValues()))
		{
			return new TongHuaShun(paramCount);
		}
		else if (SiTiao.isSiTiao(paramCount.getGhostCount(), paramCount.getValues().size(), paramCount.getHashMap()))
		{
			return new SiTiao(paramCount);
		}
		else if (HuLu.isHuLu(paramCount.getGhostCount(), paramCount.getValues().size(), paramCount.getHashMap()))
		{
			return new HuLu(paramCount);
		}
		else if (TongHua.isTongHua(paramCount.getColors().size()))
		{
			return new TongHua(paramCount);
		}
		else if (ShunZi.isShunZi(paramCount.getValues(), paramCount.getGhostCount()) != 0)
		{
			return new ShunZi(paramCount);
		}
		else if (SanTiao.isSanTiao(paramCount.getGhostCount(), paramCount.getValues().size(), paramCount.getHashMap()))
		{
			return new SanTiao(paramCount);
		}
		else if (ErDui.isErDui(paramCount.getGhostCount(), paramCount.getValues().size(), paramCount.getHashMap()))
		{
			return new ErDui(paramCount);
		}
		else if (DanDui.isDanDui(paramCount.getGhostCount(), paramCount.getValues().size(), paramCount.getHashMap()))
		{
			return new DanDui(paramCount);
		}
		else if (SanPai.isSanPai(paramCount.getValues().size()))
		{
			return new SanPai(paramCount);
		}
		else
		{
			return new WrongType();
		}
	}

	public int getNumber()
	{
		return number;
	}

}
