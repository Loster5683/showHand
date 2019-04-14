package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ParseType
{
	public static CardType parseType(ArrayList<Card> cards){
        HashMap<Integer,ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();
        if(cards.size()!=5)
		{
			System.out.println("Wrong type");
		}
		/**
		 *  局部变量
		 * 	colors用于存储除了鬼牌之外的牌的所有颜色
		 * 	values用于存储除了鬼牌之外的所有牌的数字
		 * 	ghostCount 英语存储卡组鬼牌数量
		 */
		ArrayList<Integer> colors = new ArrayList<Integer>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		int ghostCount = 0;
		//初始化，约定鬼牌即使在牌组中不存在也应该占据空位
		hashMap.put(0,new ArrayList<Integer>());

		//遍历卡牌组，对牌组进行统计
		for(Card i : cards)
		{
			int cardValue = i.getValue();
			//map中有该卡牌的值则在对应颜色数组加入该颜色
			if(hashMap.containsKey(cardValue))
			{
				//不为鬼的牌不可能出现花色与值相同的情况，出现提示卡组错误
				if(hashMap.get(cardValue).contains(i.getColor())){
					if(cardValue!=0)
					{
						System.out.println("Card repeated");
						continue;
					}
				}
				hashMap.get(cardValue).add(i.getColor());
			}
			//不存在对应值的牌则将对应值加入hashMap
			else{
				ArrayList<Integer> colorsTmp = new ArrayList<Integer>();
				colorsTmp.add(i.getColor());
				hashMap.put(cardValue,colorsTmp);
			}
			// 过滤鬼牌
			if(i.isGhost())
			{
				ghostCount++;
				continue;
			}
			// 统计非鬼牌颜色
			if(!colors.contains(i.getColor()))
			{
				colors.add(i.getColor());
			}
			// 统计非鬼牌数值
			values.add(cardValue);
		}

		//排序方便取值
		Collections.sort(values);
		int size = hashMap.size();

		//只有一种值的牌 说明五张全为鬼牌
		if(size == 1)
		{
			return new OneTypeCard(cards,colors,values,hashMap,ghostCount).getType();
		}
		//只有两种值的牌 因为鬼牌一定占用一个位置，即使鬼牌数为0，五张牌构成五条
		else if(size == 2)
		{
			return new TwoTypeCard(cards,colors,values,hashMap,ghostCount).getType();
		}
		//三种值的牌
		else if (size == 3)
		{
			return new ThreeTypeCard(cards,colors,values,hashMap,ghostCount).getType();
		}
		// 四种不同值的牌
		else if(size == 4)
		{
            return new FourTypeCard(cards,colors,values,hashMap,ghostCount).getType();
		}
		// 五种值的牌
		else if(size == 5)
		{
			return new FiveTypeCard(cards,colors,values,hashMap,ghostCount).getType();
		}
		else if(size == 6)
		{
			return new SixTypeCard(cards,colors,values,hashMap,ghostCount).getType();
		}
        Log.write("鬼牌数统计错误");
        return new CardType();
	}
}