/**
 * 文件名：Test.java
 * 描述：  测试类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/11
*/

package com.company.parseTypes;

import com.company.cardTypes.Card;
import com.company.gameProcess.ShuffleCard;
import com.company.cardTypes.CardType;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @function  测试类
 * @author    yeqiang
 * @version    1.1
 * @time      2019/4/11
*/
public class Test
{
	/**
	 * @function  通过数组生成牌组
	 * @param    a  数组形式牌组
	 * @return    返回Arraylist形式牌组
	*/
	public static ArrayList<Card> generateDesk(int[] a){
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
	 * @function 洗牌算法测试
	 * @param
	 * @return   返回牌组
	*/
	public ShuffleCard testShuffleCard(){

		ShuffleCard shuffleCard = new ShuffleCard();
		shuffleCard.printQueue();
		return shuffleCard;

	}

	/**
	 * @function  发牌功能测试
	 * @param    num  num判断返回玩家A的卡组还是B的卡组
	 * @return    ArrayList形式卡组
	*/
	public ArrayList<Card> testDelivery(int num){
		ShuffleCard shuffleCard = new ShuffleCard();
		ArrayList<Card> c3 = shuffleCard.getCardsA();
		ArrayList<Card> c4 = shuffleCard.getCardsB();
		return (num == 0)?c3:c4;
	}

	/**
	 * @function  牌型判断测试
	 * @param    al ArrayList形式牌组
	 * @return    返回类型对象
	*/
	public CardType testType (ArrayList<Card> al){
        CardType ct = ParseType.parseType(al);
		System.out.println(ct.getType());
		return ct;
	}

	/**
	 * @function  牌型大小比较测试
	 * @param    a1  牌组a1
 	 * @param    a2  牌组a2
	 * @return    返回a1相对a2的大小
	*/
	public void compare (ArrayList<Card> a1,ArrayList<Card> a2){
		CardType t1 = ParseType.parseType(a1);
		CardType t2 = ParseType.parseType(a2);
		System.out.println(t1.compareTo(t2));
	}

	/**
	 * @function    数组格式输入牌组牌型比较测试
	 * @param   a  牌组a
  	 * @param   b  牌组b
	 * @return
	*/
	public void singeltest(int [] a,int [] b){

		ArrayList<Card> a1 = generateDesk(a);
		ArrayList<Card> a2 = generateDesk(b);

		CardType t1 = ParseType.parseType(a1);
		CardType t2 = ParseType.parseType(a2);

		compare(a1,a2);
	}
	/**
	 * @function  自定义数据集的不同牌型测试
	 * @param
	 * @return
	*/
	public void testDiffType()
	{
		/**
		 * 		牌型测试用例
		 */
		int[][] a = {
			//五鬼
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			//五条
			{0, 0, 1, 0, 1, 1, 1, 2, 1, 3},
			{0, 0, 0, 0, 2, 1, 2, 2, 2, 3},
			{0, 0, 0, 0, 0, 0, 3, 0, 3, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 4, 0},
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

		ArrayList<CardType> types = new ArrayList<CardType>();
		for (int[] b : a)
		{
			ArrayList<Card> c1 = generateDesk(b);
			CardType t1 = ParseType.parseType(Test.generateDesk(b));
			types.add(t1);
		}
		/**
		 *	compare 功能测试，不同牌型进行大小比较
		 */

		System.out.println(types);
		Collections.sort(types);
		System.out.println(types);
	}
	/**
	 * @function 同牌型大小比较测试用例
	 * @param
	 * @return
	*/
	public void testSameType(){
		/**
		 *	compare 功能测试，相同牌型进行大小比较
		 */
		int[][] cTestArray_A = {
			//五条
			{0, 0, 0, 0, 1, 0, 1, 1, 1, 2}, //1
			{0, 0, 0, 0, 2, 0, 2, 1, 2, 2}, //-1
			//同花顺
			{1,0,2,0,3,0,4,0,5,0}, //-1
			{9,0,10,0,11,0,12,0,13,0},  //-1
			{1,1,2,1,3,1,4,1,5,1},  // 1
			{2,3,3,3,4,3,5,3,6,3}, // 1
			//四条
			{1,0,1,1,1,2,1,3,2,0}, //1
			{1,0,2,0,2,1,2,2,2,3}, //-1
			{1,0,1,3,0,0,0,0,2,0}, //1
			//葫芦
			{0,0,1,0,1,1,2,0,2,1}, // 1
			{1,0,1,1,0,0,2,0,2,1}, // -1
			{1,0,1,1,0,0,2,0,2,1}, // 1
			//同花
			{2,1,3,1,4,1,5,1,10,1}, //1
			{2,1,3,1,4,1,5,1,7,1},  // 1
			//顺子
			{1,1,2,2,3,3,4,1,5,1}, // -1
			{9,1,10,2,11,1,12,2,13,1}, // -1
			{0,0,2,1,3,2,4,3,5,2},  // 1
			//三条
			{2,0,2,1,2,2,3,1,4,1}, // 1
			{2,0,2,1,2,2,3,1,4,1}, // 1
			//二对
			{2,0,2,1,3,0,3,1,4,0}, // -1
			{2,1,2,2,5,1,5,2,6,1}, // -1
			{2,1,2,2,5,1,5,2,6,1}, // -1
			//单对
			{2,2,2,1,3,1,4,1,5,1}, // -1
			{2,0,2,1,3,1,4,1,5,1}, // 1
			//散牌
			{1,2,2,3,5,1,6,2,7,2}, // 1
			{1,2,2,3,5,1,6,2,7,2}, // -1
		};
		int[][] cTestArray_B = {
			//五条
			{0, 0, 0, 0, 2, 0, 2, 1, 2, 2},
			{0, 0, 0, 0, 3, 0, 3, 1, 3, 2},
			//同花顺
			{2,0,3,0,4,0,5,0,6,0},
			{1,0,10,0,11,0,12,0,13,0},
			{1,2,2,2,3,2,4,2,5,2},
			{1,2,2,2,3,2,4,2,5,2},
			//四条
			{1,0,2,0,2,1,2,2,2,3},
			{1,0,3,0,3,1,3,2,3,3},
			{1,1,1,2,0,0,0,0,2,1},
			//葫芦
			{0,0,3,0,3,1,2,0,2,1},
			{1,0,1,1,0,0,3,0,3,1},
			{1,2,1,3,0,0,2,2,2,3},
			// 同花
			{2,1,3,1,4,1,5,1,7,1},
			{2,2,3,2,4,2,5,2,7,2},
			//顺子
			{2,1,3,3,4,1,5,1,6,2},
			{1,0,10,2,11,1,12,1,13,0},
			{6,1,2,1,3,2,4,3,5,2},
			//三条
			{2,4,2,1,2,2,3,1,4,1},
			{2,2,2,1,0,0,3,1,4,1},
			//二对
			{5,0,5,1,3,0,3,1,4,0},
			{3,1,3,2,5,1,5,2,6,1},
			{2,1,2,2,5,0,5,2,6,1},
			//单对
			{1,2,1,1,3,1,4,1,5,1},
			{2,2,2,3,3,1,4,1,5,1},
			//散牌
			{8,2,2,3,5,1,6,2,7,2},
			{1,0,2,3,5,1,6,2,7,2},
		};
		for (int ttt = 0; ttt < cTestArray_A.length; ttt++)
		{
			ArrayList<Card> c1 = generateDesk(cTestArray_A[ttt]);
			ArrayList<Card> c2 = generateDesk(cTestArray_B[ttt]);
			CardType t1 = ParseType.parseType(c1);
			CardType t2 = ParseType.parseType(c2);
			System.out.println(t1.toString());
			System.out.println(t2.toString());
			System.out.println(t1.compareTo(t2));
		}
	}

}
