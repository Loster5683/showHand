package com.company;

/**
 * 单张牌属性
 */
public class Card
{
	private static final String [] colors = {"Spade","Heart","Diamond","Club"};
	private static final char [] values = {'0','A','2','3','4','5','6','7','8','9','T','J','Q','K'};

	private boolean isGhost;
	private int color;
	private int value;

	public Card(boolean isGhost){
		this.isGhost = isGhost;
	}
	public Card(boolean isGhost,int color,int i){
		this.isGhost = isGhost;
		this.color = color;
		this.value = i;
	}
	public Card(int v,int color){
		isGhost = (v==0);
		this.value = v;
		this.color = color;
	}

	public boolean isGhost()
	{
		return isGhost;
	}

	public int getColor()
	{
		return color;
	}

	public int getValue()
	{
		return value;
	}

	@Override
	public String toString()
	{
		if (isGhost)
		{
			return "Ghost";
		}
		return values[value] + "," + colors[color] ;
//		if (isGhost)
//		{
//			return "0,0";
//		}
//		return value + "," + color ;
	}
}
