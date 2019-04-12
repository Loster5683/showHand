/**
 * 文件名：Type.java
 * 描述：  类型识别类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/11
*/

package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @function  提供牌型判断与牌型大小比较的功能
 * @author    yeqiang
 * @version    1.1
 * @time      2019/4/11
*/
public class Type implements Comparable<Type>
{
	/**
	 * color 记录用于牌组大小比较的颜色
	 * type  记录牌组的类型
	 * number 记录牌组用于比较大小的数字一大小
	 * number2 记录牌组用于比较大小的数字二的大小
	 * type 记录不同type对应牌型的名称，用于返回字符串
	 * hashMap 记录牌组内卡牌统计数据
	 */
	private int color;
	private int type;
	private int number;
	private int number2;
	private String [] types = {"五鬼","五条","同花顺","四条","葫芦","同花","顺子","三条","二对","单对","散牌"};
	private HashMap<Integer,ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();

	/**
	 * @function  构造函数内对牌型进行判断
	 * @param  card 传入的卡组参数
	*/
	public Type(ArrayList<Card> card)
	{
		//牌组大小不为5报错
		if(card.size()!=5)
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
		for(Card i : card)
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
			//五鬼牌型
			type = 0;
		}
		//只有两种值的牌 因为鬼牌一定占用一个位置，即使鬼牌数为0，五张牌构成五条
		else if(size == 2)
		{
			type = 1;
			for (Integer i : hashMap.keySet()){
				//找到不为鬼牌的牌的值
				if(i != 0)
				{
					number = changeA(i);
					break;
				}
			}
		}
		//三种值的牌
		else if (size == 3)
		{
			//有三张鬼牌
			if(ghostCount == 3)
			{
				//除去鬼牌剩余牌颜色只有一种
				if(colors.size() == 1)
				{
					//如果能够构成顺子则为同花顺,否则构成四条,同花顺之间比较需要颜色，所以需要记录花色
					if( isConnected(values, ghostCount) )
					{
						type = 2;
						color = colors.get(0);
					}
					else{
						//四条需要记录四条的值，单张的值与四条花色,让三张鬼与大牌组成四条
						type = 3;
						if(values.get(0) == 1)
						{
							color = this.getColor(1);
							number = changeA(values.get(0));
							number2 = values.get(1);
						}
						else {
							number = values.get(1);
							number2 = values.get(0);
							color = this.getColor(number);
						}
					}
				}
				//剩下牌颜色不只一种,直接构成四条
				else {
					type = 3;
					if(values.get(0) == 1)
					{
						color = this.getColor(1);
						number = changeA(values.get(0));
						number2 = values.get(1);
					}
					else {
						number = values.get(1);
						number2 = values.get(0);
						color = this.getColor(number);
					}
				}
			}
			// 两张鬼牌，剩余牌组成为2-1，构成四条，取对子与鬼牌组成四条
			else if(ghostCount == 2)
			{
				type = 3;
				//找到对子对应的值,四条的值为对应对子值，并记录单牌值
				for(Integer a : values)
				{
					if (hashMap.get(a).size()==2)
					{
						number = changeA(a);
						color = this.getColor(a);
					}
					if(hashMap.get(a).size()==1)
					{
						number2 = changeA(a);
					}
				}
			}
			// 一张鬼牌 剩余四张牌有1-3和2-2的分法，一三分类型为四条，二二分为葫芦
			else if(ghostCount == 1)
			{
				// 遍历数字统计表
				for(Integer a : hashMap.keySet()){
					// 过滤鬼牌
					if (a == 0)
					{
						continue;
					}
					// 找到四条的值
					if(hashMap.get(a).size()==3)
					{
						type = 3;
						color = getColor(a);
						number = changeA(a);
					}
					//找到单张的值
					if(hashMap.get(a).size()==1)
					{
						type = 3;
						number2 = changeA(a);
					}
					// 二二分根据数字大小找到三条与对子的值，构成葫芦
					if(hashMap.get(a).size() == 2)
					{
						type = 4;
						if(values.get(0) == 1)
						{
							color = this.getColor(1);
							number = changeA(values.get(0));
							number2 = values.get(2);
						}
						else {
							number = values.get(2);
							number2 = values.get(0);
							color = this.getColor(number);
						}
						break;
					}
				}
			}
			// 没有鬼牌，五张牌一四分或二三分，一四分为四条，二三分为葫芦
			else if(ghostCount == 0)
			{
				// 找到不同分法的对应值
				for(Integer a : hashMap.keySet()){
					if(a == 0)
					{
						continue;
					}
					// 构成四条
					if(hashMap.get(a).size() == 4)
					{
						type = 3;
						color = getColor(a);
						number = changeA(a);
					}
					// 构成葫芦 number1 与color
					if(hashMap.get(a).size() == 3)
					{
						type = 4;
						number = changeA(a);
						color = getColor(a);
					}
					// 记录葫芦number2
					if(hashMap.get(a).size() == 2)
					{
						type = 4;
						number2 = changeA(a);
					}
					// 四条无需记录number2
					if(hashMap.get(a).size() == 1)
					{
						type = 3;
					}
				}
			}
			// 不符合条件的情况
			else {
				System.out.println("Wrong type");
			}
		}
		// 四种不同值的牌
		else if(size == 4)
		{
			//两张鬼牌
			if(ghostCount == 2)
			{
				//除去鬼牌只有一种花色
				if(colors.size() == 1)
				{
					//记录花色
					color = colors.get(0);
					// 判断是否构成同花顺
					if(isConnected(values,ghostCount)){
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
					if(isConnected(values,ghostCount)){
						type = 6;
						//找到最大牌的花色
						color = 0;
					}
					// 三条，取最大值作为三条的值
					else {
						type = 7;
						number = (values.get(0)==1)?1:values.get(2);
						color = getColor(number);
						number = (values.get(0)==1)?14:values.get(2);
					}
				}
			}
			// 只有一张鬼牌，则一定构成三条牌型
			if(ghostCount == 1)
			{
				type = 7;
				for(Integer a:hashMap.keySet()){
					if(hashMap.get(a).size()==2)
					{
						number = changeA(a);
						color = getColor(a);
						break;
					}
				}
			}
			// 没有鬼牌，有三一一和二二一两种分法，分别构成三条和二对
			if(ghostCount == 0)
			{
				int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
				boolean hasTreeCard = false;
				for(Integer a:hashMap.keySet()){
					// 三条取三张的值
					if(hashMap.get(a).size()==3)
					{
						type = 7;
						number = changeA(a);
						color = getColor(a);
						hasTreeCard = true;
						break;
					}
					//找到大对子及大对子最大花色
					if(hashMap.get(a).size()==2)
					{
						type = 8;
						max = Math.max(a,max);
						min = Math.min(a,min);
					}
				}
				if(!hasTreeCard){
					if(min == 1 ){
						number = 14;
						number2 = max;
						color = getColor(1);
					}
					else{
						number  = max;
						number2 = min;
						color = getColor(number);
					}
				}
			}
		}
		// 五种值的牌
		else if(size == 5)
		{
			// 有一张鬼牌
			if(ghostCount == 1)
			{
				// 颜色相同
				if(colors.size()==1)
				{
					// 构成同花顺
					if(isConnected(values,ghostCount)){
						type = 	2;
						color = colors.get(0);
					}
					// 不构成顺子，则为一般同花,因为有一张鬼牌，所以最大为A
					else {

						type = 5;
						number = 14;
						color = 0;
					}
				}
				// 颜色不同
				else {
					// 构成顺子
					if(isConnected(values,ghostCount)){
						type = 6;
						color = 0;
					}
					// 单对
					else{
						type = 9;
						number = (values.get(0) == 1)?1:values.get(3);
						color = getColor(number);
						number = (values.get(0) == 1)?14:values.get(3);
					}
				}
			}
			// 没有鬼牌 直接构成单对
			if(ghostCount == 0)
			{
				for (Integer a: hashMap.keySet()){
					if(hashMap.get(a).size()==2)
					{
						type = 9;
						number = changeA(a);
						color = getColor(a);
					}
				}
			}
		}
		else if(size == 6)
		{
			// 同花
			if(colors.size() == 1)
			{
				// 同花顺
				if (isConnected(values,ghostCount)){
					type = 2;
					color = colors.get(0);
				}
				// 普通同花
				else {
					type = 5;
					number = (values.get(0) == 1)?1:values.get(4);
					color = getColor(number);
					number = (values.get(0) == 1)?14:values.get(4);
				}
			}
			// 非同花
			else {
				// 是顺子
				if(isConnected(values,ghostCount)){
					int tmp = 0;
					type = 6;
					tmp = (values.get(0) == 1)?1:values.get(4);
					color = getColor(tmp);
				}
				// 散牌
				else {
					type = 10;
					number = (values.get(0) == 1)?1:values.get(4);
					color = getColor(number);
					number = (values.get(0) == 1)?14:values.get(4);
				}
			}
		}

	}

	/**
	 * @function  通过卡牌值，找到对应卡牌优先级最高的花色
	 * @param  integer  卡牌值
	 * @return   花色
	*/
	private int getColor(Integer integer)
	{
		Collections.sort(hashMap.get(integer));
		return 	hashMap.get(integer).get(0);
	}

	/**
	 * @function    因为统计时将A的值看作1，但是在比较时应该看作比k还大的14
	 * @param    a  卡牌对应数值
	 * @return    卡牌转换后数值
	*/
	private int changeA(Integer a)
	{
		return (a==1)?14:a;
	}

	/**
	 * @function  判断卡组是否构成顺子
	 * @param    values  非鬼牌的值
	 * @param    size  鬼牌的数量
	 * @return    是否为顺子
	*/
	private boolean isConnected(ArrayList<Integer> values, int size)
	{
		// 非鬼卡牌与鬼牌数总和应该为5
		if(values.size() + size != 5)
		{
			System.out.println("Wrong param");
		}
		// 三种鬼牌
		if (size == 3)
		{
			int minValue = values.get(0);
			int maxValue = values.get(1);
			//两张卡数值相差小于5则可以构成顺子
			if(maxValue-minValue<=4)
			{
				number = (minValue + 4 <= 13)?minValue+4:14;
				return true;
			}
			// 可以构成TJQKA的顺子
			if(minValue == 1 && maxValue >=10)
			{
				number = 14;
				return true;
			}
			return false;
		}
		// 两张鬼
		if(size == 2)
		{
			int maxValue = values.get(2);
			int midValue = values.get(1);
			int minValue = values.get(0);
			if(maxValue-minValue<=4)
			{
				number = (minValue + 4 <= 13)?minValue+4:14;
				return true;
			}
			if(minValue == 1 && midValue >= 10)
			{
				number = 14;
				return true;
			}
			return false;
		}
		if(size == 1)
		{
			int maxValue = values.get(3);
			int midValue = values.get(1);
			int minValue = values.get(0);
			if(maxValue-minValue<=4)
			{
				number = (minValue + 4 <= 13)?minValue+4:14;
				return true;
			}
			if(minValue == 1 && midValue >= 10)
			{
				number = 14;
				return true;
			}
			return false;
		}
		if (size == 0)
		{
			int maxValue = values.get(4);
			int minValue = values.get(0);
			int midValue = values.get(1);
			if(maxValue-minValue==4)
			{
				number = maxValue;
				return true;
			}
			if(minValue == 1 && midValue == 10)
			{
				number = 14;
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * @function  便于直观的看到卡组的牌型
	 * @return    表示牌型的字符串
	*/
	public String getType()
	{
		return types[type];
	}

	@Override
	public String toString()
	{
		return types[type];
	}

	/**
	 * @function  简单的比较函数
	 * @param    a,b
	 * @return    返回三种关系对应的值，1表示a>b,-1表示a<b,0表示a=b
	*/
	private int compare(int a,int b)
	{
		if(a>b)
		{
			return 1;
		}
		else if(a<b)
		{
			return -1;
		}
		return 0;
	}

	/**
	 * @function  重写compareTo函数
	 * @param    o  用于比较的Type对象
	 * @return    当前对象与o的大小关系
	*/
	@Override
	public int compareTo(Type o)
	{	// 分别从四个维度取比较
		if(compare(this.type,o.type)!=0){
			return compare(o.type,this.type);
		}
		else if(compare(this.number,o.number)!=0){
			return compare(this.number,o.number);
		}
		else if(compare(this.number2,o.number2)!=0){
			return compare(this.number2,o.number2);
		}
		else if(compare(o.color,this.color)!=0){
			return compare(o.color,this.color);
		}
		else {
			return 0;
		}
	}
}
