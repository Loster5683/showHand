/**
 * 文件名：gameProcess.java
 * 描述：  游戏类文件
 * 创建人：yeqiang
 * 创建时间：2019/4/11
 */

package com.company.gameProcess;

import com.company.cardTypes.Card;
import com.company.cardTypes.CardType;
import com.company.parseTypes.ParseCards;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author yeqiang
 * @version 1.1
 */
public class Game
{
	public void start()
	{
		//scanner 读取标准输入控制游戏执行
		Scanner scanner = new Scanner(System.in);
		//GoldA 玩家A的金币数
		int GoldA = 1000;
		//GoldB 玩家B的金币数
		int GoldB = 1000;
		//Gold  成功加注的当前金币
		int Gold = 0;
		//Goldtmp 未确认的加注金币数
		int Goldtmp = 0;
		//round 游戏轮次计数器
		int round = 0;
		//endGame 游戏结束标志
		boolean endGame = false;
		//Warn 游戏提示语
		String[] Warn = {"A开始选择", "B开始选择"};

		// 游戏开始
		while (!endGame)
		{
			Gold = 10;
			Goldtmp = Gold;
			// 玩家金币不足则结束游戏
			if (GoldA < 10 || GoldB <= 10)
			{

				Log.write("玩家金币不足，无法开始游戏");
				endGame = true;
				continue;
			}
			// 每轮游戏开始
			Log.write("Round " + ++round + " Start");
			// 洗牌
			ShuffleCard shuffleCard = new ShuffleCard();

			{
				// addTimes 变量用于记录当前操作的执行者是A还是B
				int addTimes = (round + 1) % 2;
				Log.write(Warn[addTimes % 2]);
				char tmp = scanner.next().charAt(0);
				addTimes++;
				// 每轮开始玩家直接结束游戏
				if (tmp == 'Q')
				{
					Log.write("gameProcess Over");
					endGame = true;
					continue;
				}
				// 选择当轮弃牌，直接开始下一轮
				if (tmp == 'N')
				{
					GoldA -= Gold;
					GoldB += Gold;
					// 打印每轮游戏结束后玩家金币剩余
					Log.write("playA 剩余金币 " + GoldA + "损益为" + (GoldA - 1000));
					Log.write("playB 剩余金币 " + GoldB + "损益为" + (GoldB - 1000));
					continue;
				}
				//选择继续发牌，则转交选择权
				else if (tmp == 'Y')
				{
					Log.write(Warn[addTimes % 2]);
					tmp = scanner.next().charAt(0);
					addTimes++;
				}
				// 加注流程，循环判断
				while (tmp == 'R')
				{
					Gold = Goldtmp;
					Goldtmp = Gold + scanner.nextInt();
					Log.write(Warn[addTimes % 2]);
					tmp = scanner.next().charAt(0);
					addTimes++;
				}
				// 选择继续发牌，含义是加注成功且直接开牌不再继续加注
				if (tmp == 'Y')
				{
					Gold = Goldtmp;
					ArrayList<Card> cardsA = shuffleCard.getCardsA();
					ArrayList<Card> cardsB = shuffleCard.getCardsB();
					CardType typeA = new ParseCards(cardsA).parseType();
					CardType typeB = new ParseCards(cardsB).parseType();
					Log.write("A的卡牌组:  " + cardsA + "   A的牌型:  " + typeA.toString());
					Log.write("B的卡牌组:  " + cardsB + "   B的牌型:  " + typeB.toString());
					Log.write("A与B牌组大小:  " + typeA.compareTo(typeB));
					// A的牌型大，A此轮胜利赢得Gold数量金币
					if (typeA.compareTo(typeB) == 1)
					{
						GoldA += Gold;
						GoldB -= Gold;
					}
					// B的牌型大，B此轮胜利赢得Gold数量金币
					else if (typeA.compareTo(typeB) == -1)
					{
						GoldA -= Gold;
						GoldB += Gold;
					}
					else
					{
						continue;
					}
				}
				// 某一方弃牌 通过变量addTimes判断当前做出选择的是A还是B
				else if (tmp == 'N')
				{
					// A弃牌
					if (addTimes % 2 == 1)
					{
						GoldA -= Gold;
						GoldB += Gold;
						Log.write("A弃牌");
					}
					//B弃牌
					else
					{
						GoldA += Gold;
						GoldB -= Gold;
						Log.write("B弃牌");
					}
				}
				else
				{
					// 错误输入
					Log.write("wrong input");
				}
			}
			// 打印每轮游戏结束后玩家金币剩余
			Log.write("playA 剩余金币 " + GoldA + "损益为" + (GoldA - 1000));
			Log.write("playB 剩余金币 " + GoldB + "损益为" + (GoldB - 1000));
		}
		// 游戏结束关闭标准输入
		scanner.close();
	}
}
