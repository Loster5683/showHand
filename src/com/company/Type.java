package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Type implements Comparable<Type>
{
	private int color;
	private int type;
	private int number;
	private int number2;
	/**
	 * 	colors用于存储除了鬼牌之外的牌的所有颜色
	 * 	values用于存储除了鬼牌之外的所有牌的数字
	 */

	ArrayList<Integer> colors = new ArrayList<Integer>();
	ArrayList<Integer> values = new ArrayList<Integer>();
	ArrayList<Card> arrayList ;

	private String [] types = {"五鬼","五条","同花顺","四条","葫芦","同花","顺子","三条","二对","单对","散牌"};



	//存储每个值对应的卡牌数
	HashMap<Integer,ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();

	/**
	 * 构造函数分析牌型
	 * @param al 传入五张牌的牌组
	 */
	public Type(ArrayList<Card> al)
	{
		//牌组大小不为5报错
		if(al.size()!=5){
			System.out.println("Wrong type");
		}
		//初始化一些中间变量
		arrayList = al;
		//鬼牌即使在牌组中不存在也应该占据空位
		hashMap.put(0,new ArrayList<Integer>());
		//遍历卡牌组，对牌组进行统计
		for(Card i : arrayList)
		{
			//map中有该卡牌的值则在对应颜色数组加入该颜色
			if(hashMap.containsKey(i.getValue()))
			{
				//不为鬼的牌不可能出现花色与值相同的情况
				if(hashMap.get(i.getValue()).contains(i.getColor())){
					if(i.getValue()!=0){
						System.out.println("Card repeated");
						continue;
					}
				}
				hashMap.get(i.getValue()).add(i.getColor());
			}
			//不存在对应值的牌加入
			else{
				ArrayList<Integer> colorsTmp = new ArrayList<Integer>();
				colorsTmp.add(i.getColor());
				hashMap.put(i.getValue(),colorsTmp);
			}
			// 过滤鬼牌
			if(i.isGhost()){
				continue;
			}
			// 统计非鬼牌颜色
			if(!colors.contains(i.getColor())){
				colors.add(i.getColor());
			}
			// 统计非鬼牌数值
			values.add(i.getValue());
		}
		//排序方便取值
		Collections.sort(values);
		int size = hashMap.size();
		//只有一种值的牌 说明五张全为鬼牌
		if(size == 1){
			type = 0;
		}
		//只有两种值的牌 因为鬼牌一定占用一个位置，即使鬼牌数为0，五张牌构成五条
		else if(size == 2)
		{
			type = 1;
			for (Integer i : hashMap.keySet()){
				//找到不为鬼牌的牌的值
				if(i != 0){
					number = i;
					break;
				}
			}
		}
		//三种值的牌
		else if (size == 3){
			//有三张鬼牌
			if(hashMap.get(0).size()==3){
				//除去鬼牌剩余牌颜色只有一种
				if(colors.size()==1){
					//如果能够构成顺子则为同花顺,否则构成四条,同花顺之间比较需要颜色，所以需要记录花色
					if(isConnected(values,hashMap.get(0).size())){
						type = 2;
						color = colors.get(0);
					}
					else{
						//四条需要记录四条的值与单张的值,让三张鬼与大牌组成四条
						type = 3;
						number = (values.get(0)==1)?14:values.get(1);
						number2 = (values.get(0)==1)?values.get(1):values.get(0);
					}
				}
				//剩下牌颜色不知一种,直接构成四条
				else {
					type = 3;
					number = (values.get(0)==1)?14:values.get(1);
					number2 = (values.get(0)==1)?values.get(1):values.get(0);
				}
			}
			// 两张鬼牌，剩余牌组成为2-1，构成四条，取对子与鬼牌组成四条
			else if(hashMap.get(0).size()==2){
				type = 3;
				//找到对子对应的值,四条的值为对应对子值，并记录单牌值
				for(Integer a : values){
					if (hashMap.get(a).size()==2){
						number = a;
					}
					if(hashMap.get(a).size()==1){
						number2 = a;
					}
				}
			}
			// 一张鬼牌 剩余四张牌有1-3和2-2的分法，一三分类型为四条，二二分为葫芦
			else if(hashMap.get(0).size()==1){
				// 遍历数字统计表
				for(Integer a:hashMap.keySet()){
					// 过滤鬼牌
					if (a == 0){
						continue;
					}
					// 找到四条的值
					if(hashMap.get(a).size()==3){
						type = 3;
						number = a;
					}
					//找到单张的值
					if(hashMap.get(a).size()==1){
						type = 3;
						number2 = a;
					}
					// 二二分根据数字大小找到三条与对子的值，构成葫芦
					if(hashMap.get(a).size() == 2){
						type = 4;
						number = values.get(2);
						number2 = values.get(0);
						Collections.sort(hashMap.get(number));
						color = hashMap.get(number).get(0);
						break;
					}
				}
			}
			// 没有鬼牌，五张牌一四分或二三分，一四分为四条，二三分为葫芦
			else if(hashMap.get(0).size()==0){
				// 找到不同分法的对应值
				for(Integer a:hashMap.keySet()){
					if(a == 0){
						continue;
					}
					if(hashMap.get(a).size()==4){
						type = 3;
						number = a;
					}
					if(hashMap.get(a).size()==3){
						type = 4;
						number = a;
						Collections.sort(hashMap.get(number));
						color = hashMap.get(number).get(0);
					}
					if(hashMap.get(a).size()==2){
						type = 4;
						number2 = a;
					}
					if(hashMap.get(a).size()==1){
						type = 3;
						number2 = a;
					}
				}
			}
			// 不符合条件的情况
			else {
				System.out.println("Wrong type");
			}
		}
		// 四种不同值的牌
		else if(size == 4){
			//两张鬼牌
			if(hashMap.get(0).size()==2){
				//除去鬼牌只有一种花色
				if(colors.size()==1){
					// 判断是否构成顺子
					color = colors.get(0);
					if(isConnected(values,hashMap.get(0).size())){
						type = 2;
					}
					// 普通同花,由于有两种鬼牌，所以number,number2取不同的最大值
					else {
						type = 5;
						number = 14;
						number2 = 13;
					}
				}
				// 有多种花色
				else {
					// 判断是否构成顺子
					if(isConnected(values,hashMap.get(0).size())){
						type = 6;
						//找到最大牌的花色
						color = hashMap.get(values.get(2)).get(0);
					}
					// 三条，取最大值作为三条的值
					else {
						type = 7;
						number = (values.get(0)==1)?1:values.get(2);
					}
				}
			}
			// 只有一张鬼牌，则一定构成三条牌型
			if(hashMap.get(0).size()==1){
				type = 7;
				for(Integer a:hashMap.keySet()){
					if(hashMap.get(a).size()==2){
						number = a;
						break;
					}
				}
			}
			// 没有鬼牌，有三一一和二二一两种分法，分别构成三条和二对
			if(hashMap.get(0).size()==0){
				int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
				for(Integer a:hashMap.keySet()){
					// 三条取三张的值
					if(hashMap.get(a).size()==3){
						type = 7;
						number = a;
						break;
					}
					//找到大对子及大对子最大花色
					if(hashMap.get(a).size()==2){
						type = 8;
						number = Math.max(a,max);
						Collections.sort(hashMap.get(number));
						color = hashMap.get(number).get(0);
						number2 = Math.min(a,min);
					}
				}
			}
		}
		// 五种值的牌
		else if(size == 5){
			// 有一张鬼牌
			if(hashMap.get(0).size()==1){
				// 颜色相同
				if(colors.size()==1){
					// 构成同花顺
					if(isConnected(values,hashMap.get(0).size())){
						type = 	2;
						color = colors.get(0);
					}
					// 不构成顺子，则为一般同花,因为有一张鬼牌，所以最大为A
					else {

						type = 5;
						number = 14;
						number2 = (values.get(0)==1)?13:values.get(3);
					}
				}
				// 颜色不同
				else {
					// 构成顺子
					if(isConnected(values,hashMap.get(0).size())){
						type = 6;
						color = hashMap.get(values.get(3)).get(0);
					}
					// 单对
					else{
						type = 9;
						number = values.get(3);
						color = hashMap.get(number).get(0);
					}
				}
			}
			// 没有鬼牌
			if(hashMap.get(0).size()==0){
				for (Integer a: hashMap.keySet()){
					if(hashMap.get(a).size()==2){
						type = 9;
						number = a;
						color = hashMap.get(number).get(0);

					}
				}
			}
		}
		else if(size == 6){
			// 同花
			if(colors.size() == 1){
				// 同花顺
				if (isConnected(values,hashMap.get(0).size())){
					type = 2;
					color = colors.get(0);
				}
				// 普通同花
				else {
					type = 5;
					number = values.get(4);
					number2 = values.get(3);
				}
			}
			// 非同花
			else {
				// 是顺子
				if(isConnected(values,hashMap.get(0).size())){
					type = 6;
					color = hashMap.get(values.get(4)).get(0);
				}
				// 散牌
				else {
					type = 10;
					number = values.get(4);
					color = hashMap.get(number).get(0);
				}
			}
		}

	}

	private boolean isConnected(ArrayList<Integer> values, int size)
	{
		if(values.size() + size != 5){
			System.out.println("Wrong param");
		}
		if (size == 3){
			int minValue = values.get(0);
			int maxValue = values.get(1);
			if(maxValue-minValue<=4){
				number = (minValue + 4 < 13)?minValue+4:14;
				return true;
			}
			if(minValue == 1 && maxValue >=10){
				number = 14;
				return true;
			}
			return false;
		}
		if(size == 2){
			int maxValue = values.get(2);
			int midValue = values.get(1);
			int minValue = values.get(0);
			if(maxValue-minValue<=4){
				number = (minValue + 4 < 13)?minValue+4:14;
				return true;
			}
			if(minValue == 1 && midValue >= 10){
				number = 14;
				return true;
			}
			return false;
		}
		if(size == 1){
			int maxValue = values.get(3);
			int midValue = values.get(1);
			int minValue = values.get(0);
			if(maxValue-minValue<=4){
				number = (minValue + 4 < 13)?minValue+4:14;
				return true;
			}
			if(minValue == 1 && midValue >= 10){
				number = 14;
				return true;
			}
			return false;
		}
		if (size == 0){
			int maxValue = values.get(4);
			int minValue = values.get(0);
			int midValue = values.get(1);
			if(maxValue-minValue==4){
				number = maxValue;
				return true;
			}
			if(minValue == 1 && midValue == 10){
				number = 14;
				return true;
			}
			return false;
		}
		return false;
	}

	public String getType(){
		return types[type];
	}

	@Override
	public String toString()
	{
		return types[type];
	}

	private int compare(int a,int b){
		if(a>b){
			return 1;
		}
		else if(a<b){
			return -1;
		}
		return 0;
	}

	@Override
	public int compareTo(Type o)
	{
		if(this.type<o.type){
			return 1;
		}
		else if(this.type>o.type){
			return -1;
		}
		else {
			if(this.type == 1){
				return compare(this.number,o.number);
			}
			else if(this.type == 2){
				if(compare(this.number,o.number)!=0){
					return compare(this.number,o.number);
				}
				return compare(o.color,this.color);
			}
			else if(this.type == 3){
				if(compare(this.number,o.number)!=0){
					return compare(this.number,o.number);
				}
				return compare(this.number2,o.number2);
			}
			else if(this.type == 4){
				if(compare(this.number,o.number)!=0){
					return compare(this.number,o.number);
				}
				if(compare(this.number2,o.number2)!=0){
					return compare(this.number2,o.number2);
				}
				return compare(o.color,this.color);
			}
			else if (this.type == 5){
				if(compare(this.number,o.number)!=0){
					return compare(this.number,o.number);
				}
				return compare(this.number2,o.number2);
			}
			else if (this.type == 6){
				if(compare(this.number,o.number)!=0){
					return compare(this.number,o.number);
				}
				return compare(o.color,this.color);
			}
			else if(this.type == 7){
				return compare(this.number,o.number);
			}
			else if(this.type == 8){
				if(compare(this.number,o.number)!=0){
					return compare(this.number,o.number);
				}
				if(compare(this.number2,o.number2)!=0){
					return compare(this.number2,o.number2);
				}
				return compare(o.color,this.color);
			}
			else if (this.type == 9){
				if(compare(this.number,o.number)!=0){
					return compare(this.number,o.number);
				}
				return compare(o.color,this.color);
			}
			else if (this.type == 10){
				if(compare(this.number,o.number)!=0){
					return compare(this.number,o.number);
				}
				return compare(o.color,this.color);
			}
			else {
				System.out.println("valid type");
			}
		}
		return 0;
	}
}
