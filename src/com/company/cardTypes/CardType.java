/**
 * 文件名：CardType.java
 * 描述：  不同卡牌的父类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/15
 */
package com.company.cardTypes;

import java.util.ArrayList;

/**
 * @author 作者  yeqiang
 * @version 创建时间 2019/4/15
 */
public interface CardType extends Comparable<CardType>
{
	// toString使用String数组

	String[] types = {"五鬼", "五条", "同花顺", "四条", "葫芦", "同花", "顺子", "三条", "二对", "单对", "散牌"};

	int getType();

	int compareTo(CardType o);

	ArrayList<Card> getKeyCards();
}
