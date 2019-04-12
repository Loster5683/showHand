/**
 * 文件名：Card.java
 * 描述：  Card类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/11
*/
package com.company;

/**
 * @function  Card 数据类
 * @author    yeqiang
 * @version   1.1
 * @time      2019/4/11
*/
public class Card
{
	/**
	 * colors 用于toString时数字与花色映射
	 * values 用于toString时数字与牌符号映射
	 * isGhost 是否为鬼牌
	 * color 卡牌花色
	 * value 卡牌值
	 */
	private static final String [] colors = {"Spade", "Heart", "Diamond", "Club"};
	private static final char [] values = {'0','A','2','3','4','5','6','7','8','9','T','J','Q','K'};
	private boolean isGhost;
	private int color;
	private int value;
	/**
	 * @function  构造函数
	*/
	public Card(boolean isGhost)
	{
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
	}
}
