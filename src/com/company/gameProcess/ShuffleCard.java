/**
 * 文件名：ShuffleCard.java
 * 描述：  洗牌与发牌类
 * 创建人：yeqiang
 * 创建时间：2019/4/11
 */

package com.company.gameProcess;

import com.company.cardTypes.Card;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author yeqiang
 * @version 1.1
 */
public class ShuffleCard
{
	//牌组大小
	private static final int SIZE = 5;
	//initCard  按序自动生成的一副牌
	private ArrayList<Card> initCards = new ArrayList<Card>();
	//cards     洗牌算法对initCard进行洗牌后的一副牌
	private ArrayList<Card> cards = new ArrayList<Card>();

	public ShuffleCard()
	{
		init();
		int size = initCards.size();
		if (size <= 1)
		{
			return;
		}
		while (initCards.size() > 0)
		{
			Random s = new Random();
			int j = s.nextInt(initCards.size());
			cards.add(initCards.get(j));
			initCards.remove(j);
		}
	}

	/**
	 * @param
	 *
	 * @return
	 */
	public void init()
	{
		int i;
		int j;
		for (i = 0; i <= 3; i++)
		{
			for (j = 1; j <= 13; j++)
			{
				initCards.add(new Card(false, i, j));
			}
		}
		for (i = 0; i < SIZE; i++)
		{
			initCards.add(new Card(true, 0, 0));
		}
	}

	/**
	 * @param
	 *
	 * @return
	 */
	public void printQueue()
	{
		for (int i = 0; i < cards.size(); i++)
		{
			System.out.println(cards.get(i).toString());
		}
	}

	/**
	 * @param
	 *
	 * @return A玩家每轮获取的五张牌
	 */
	public ArrayList<Card> getCardsA()
	{
		ArrayList<Card> cardHand = new ArrayList<Card>(5);
		cardHand.add(cards.get(0));
		cardHand.add(cards.get(1));
		cardHand.add(cards.get(4));
		cardHand.add(cards.get(6));
		cardHand.add(cards.get(8));
		System.out.println(cardHand);
		return cardHand;
	}

	/**
	 * @param
	 *
	 * @return B玩家每轮获取的五张牌
	 */
	public ArrayList<Card> getCardsB()
	{
		ArrayList<Card> cardhand = new ArrayList<Card>(5);
		cardhand.add(cards.get(2));
		cardhand.add(cards.get(3));
		cardhand.add(cards.get(5));
		cardhand.add(cards.get(7));
		cardhand.add(cards.get(9));
		System.out.println(cardhand);
		return cardhand;
	}
}
