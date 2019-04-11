package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Test
{
	public ArrayList<Card> generateDesk(int[] a){
		ArrayList<Card> cards = new ArrayList<Card>();
		if(a.length!=10){
			System.out.println("wrong length");
		}
		for(int i=0;i<a.length/2;i++){
			cards.add(new Card(a[2*i],a[2*i+1]));
		}
		return cards;
	}
	/**
	 * 洗牌测试
	 */
	public ShuffleCard testShuffleCard(){

		ShuffleCard shuffleCard = new ShuffleCard();
		shuffleCard.printQueue();
		return shuffleCard;

	}
	/**
	 *  发牌测试
	 * @param  num  0选择A卡牌，1选择B卡牌
	 */
	public ArrayList<Card> testDelivery(int num){
		ShuffleCard shuffleCard = new ShuffleCard();
		ArrayList<Card> c3 = shuffleCard.getCardsA();
		ArrayList<Card> c4 = shuffleCard.getCardsB();
		return (num == 0)?c3:c4;
	}

	/**
	 *  类型判断
	 * @param al 卡组
	 */
	public Type testType (ArrayList<Card> al){
		Type t = new Type(al);
		System.out.println(t.getType());
		return t;
	}
	/**
	 * 类型比较
	 * @param a1  卡组A
	 * @param a2  卡组B
	 */
	public void compare (ArrayList<Card> a1,ArrayList<Card> a2){
		Type t1 = new Type(a1);
		Type t2 = new Type(a2);
		System.out.println(t1.compareTo(t2));
	}




	public void testDefined()
	{
		/**
		 * 		牌型测试用例

		 */
		int[][] a = {
			//五鬼
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			//五条
			{0, 0, 1, 0, 1, 1, 1, 2, 1, 3}, {0, 0, 0, 0, 2, 1, 2, 2, 2, 3}, {0, 0, 0, 0, 0, 0, 3, 0, 3, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 4, 0},
			//同花顺
			{0, 0, 0, 0, 0, 0, 1, 0, 2, 0},
			//四条
			{0, 0, 0, 0, 0, 0, 1, 0, 6, 0},
			//四条
			{0, 0, 0, 0, 0, 0, 1, 0, 2, 1},
			//四条
			{0, 0, 0, 0, 2, 0, 2, 1, 3, 0},
			//四条
			{0, 0, 1, 0, 1, 1, 1, 2, 4, 0},
			//葫芦
			{0, 0, 1, 0, 1, 1, 2, 0, 2, 1},
			//四条
			{1, 0, 1, 1, 1, 2, 1, 3, 2, 0},
			//葫芦
			{1, 0, 1, 1, 1, 2, 2, 0, 2, 1},
			//同花顺
			{0, 0, 0, 0, 2, 0, 3, 0, 4, 0},
			//同花
			{0, 0, 0, 0, 2, 0, 3, 0, 8, 0},
			//顺子
			{0, 0, 0, 0, 2, 1, 3, 0, 4, 0},
			//三条
			{0, 0, 0, 0, 2, 1, 3, 0, 8, 0},
			//三条
			{0, 0, 2, 0, 2, 1, 3, 0, 4, 0},
			//三条
			{1, 0, 1, 1, 1, 2, 4, 0, 5, 0},
			//二对
			{1, 0, 1, 1, 4, 2, 4, 0, 5, 0},
			//同花顺
			{0, 0, 1, 0, 2, 0, 3, 0, 4, 0},
			//同花
			{0, 0, 1, 0, 2, 0, 3, 0, 6, 0},
			//顺子
			{0, 0, 1, 1, 2, 0, 3, 0, 4, 0},
			//单对
			{0, 0, 1, 1, 2, 0, 3, 0, 6, 0},
			//单对
			{1, 0, 1, 1, 2, 0, 3, 0, 4, 0},
			//同花顺
			{1, 0, 2, 0, 3, 0, 4, 0, 5, 0},
			//同花
			{1, 0, 2, 0, 3, 0, 4, 0, 6, 0},
			//顺子
			{1, 0, 2, 0, 3, 0, 4, 0, 5, 1},
			//散牌
			{1, 0, 2, 0, 3, 0, 4, 0, 6, 1},};

		ArrayList<Type> types = new ArrayList<Type>();
		for (int[] b : a)
		{
			ArrayList<Card> c1 = generateDesk(b);
			Type t1 = testType(c1);
			types.add(t1);
		}
		/**
		 *	compare 功能测试，不同牌型进行大小比较
		 */

		System.out.println(types);
		Collections.sort(types);
		System.out.println(types);

		/**
		 *	compare 功能测试，相同牌型进行大小比较
		 */
		int[][] cTestArray_A = {
			//五条
			{0, 0, 0, 0, 1, 0, 1, 1, 1, 2}
		};

		int[][] cTestArray_B = {
			{0, 0, 0, 0, 2, 0, 2, 1, 2, 2}
		};
		for (int ttt = 0; ttt < cTestArray_A.length; ttt++)
		{
			ArrayList<Card> c1 = generateDesk(cTestArray_A[ttt]);
			ArrayList<Card> c2 = generateDesk(cTestArray_B[ttt]);
			Type t1 = testType(c1);
			Type t2 = testType(c2);
			compare(c1, c2);
		}
	}

}
