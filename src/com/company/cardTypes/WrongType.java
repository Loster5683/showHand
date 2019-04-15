package com.company.cardTypes;

import java.util.ArrayList;

public class WrongType implements CardType
{
	//类型
	private int type;
	//卡组
	private ArrayList<Card> cards;

	@Override
	public int getType()
	{
		return type;
	}

	@Override
	public int compareTo(CardType o)
	{
		return -1;
	}
}
