/**
 * 文件名：ShuffleCard.java
 * 描述：  洗牌与发牌类
 * 创建人：yeqiang
 * 创建时间：2019/4/11
*/

package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * @function  负责每轮游戏的洗牌与发牌过程
 * @author    yeqiang
 * @version   1.1
 * @time      2019/4/11
*/
public class ShuffleCard
{
	/**
	 * initCard  按序自动生成的一副牌
	 * cards     洗牌算法对initCard进行洗牌后的一副牌
	 */
	private ArrayList<Card> initCards = new ArrayList<Card>();
	private ArrayList<Card> cards = new ArrayList<Card>();

	/**
	 * @function  洗牌算法
	 * @param
	 * @return
	*/
	public ShuffleCard()
	{
		init();
		int size = initCards.size();
		if(size <= 1){
			return;
		}

		while (initCards.size()>0){
			Random s = new Random();
			int j = s.nextInt(initCards.size());
			cards.add(initCards.get(j));
			initCards.remove(j);
		}
	}
    /**
     * @function  初始化顺序生成一副
     * @param
     * @return
    */
	public void init()
	{
		int i = 0,j = 0;
		for ( i = 0; i <= 3; i++)
		{
			for(j = 1; j <= 13; j++)
			{
				initCards.add(new Card(false,i,j));
			}
		}
		for( i = 0; i < 5; i++)
		{
			initCards.add(new Card(true));
		}
	}

	/**
	 * @function  牌组打印
	 * @param
	 * @return
	*/
	public void printQueue(){
		for(Card card : cards){
			System.out.print(card.toString()+'\n');
		}
	}

	/**
	 * @function  获取A的五张牌
	 * @param
	 * @return    A玩家每轮获取的五张牌
	*/
	public ArrayList<Card> getCardsA()
	{
		ArrayList<Card> cardhand = new ArrayList<Card>(5);
		cardhand.add(cards.get(0));
		cardhand.add(cards.get(1));
		cardhand.add(cards.get(4));
		cardhand.add(cards.get(6));
		cardhand.add(cards.get(8));
		System.out.println(cardhand);
		return cardhand;
	}
	/**
	 * @function  获取B的五张牌
	 * @param
	 * @return    B玩家每轮获取的五张牌
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
