package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
	int GoldA,GoldB,round=0;
	boolean endGame = false;
	int Gold = 0;
	int Goldtmp = 0;
	public void start(){
		Scanner scanner = new Scanner(System.in);
		GoldA = 1000;
		GoldB = 1000;
		String [] Warn = {"A开始选择","B开始选择"};
		while (!endGame){
			Gold = 10;
			Goldtmp = Gold;
			if(GoldA < 10 || GoldB <= 10 ){
				endGame = true;
				Log.write("金币不足，无法开始游戏");
				break;
			}
			Log.write( "Round "+ ++round +" Start");
			ShuffleCard shuffleCard = new ShuffleCard();
			{
				int addTimes = (round+1)%2;
				Log.write(Warn[addTimes%2]);
				char tmp = scanner.next().charAt(0);
				addTimes++;
				if(tmp == 'Q'){
					Log.write("Game Over");
					break;
				}
				if(tmp == 'N'){
					GoldA -= Gold;
					GoldB += Gold;
					continue;
				}
				else if(tmp == 'Y'){
					Log.write(Warn[addTimes%2]);
					tmp = scanner.next().charAt(0);
					addTimes++;
				}

				while(tmp == 'R'){
					Gold = Goldtmp;
					Goldtmp = Gold + scanner.nextInt();
					Log.write(Warn[addTimes%2]);
					tmp = scanner.next().charAt(0);
					addTimes++;
				}
				if(tmp == 'Y' ){
						Gold = Goldtmp;
					ArrayList<Card> cardsA = shuffleCard.getCardsA();
					ArrayList<Card> cardsB = shuffleCard.getCardsB();
					Type typeA = new Type(cardsA);
					Type typeB = new Type(cardsB);
					Log.write("A的卡牌组:  " + cardsA +  "A的牌型:  " + typeA.getType());
					Log.write("B的卡牌组:  " + cardsB +  "B的牌型:  " + typeB.getType());
					Log.write("A与B牌组大小:  " + typeA.compareTo(typeB));
					if(typeA.compareTo(typeB)==1){
						GoldA += Gold;
						GoldB -= Gold;
					}
					else if(typeA.compareTo(typeB)==-1){
						GoldA -= Gold;
						GoldB += Gold;
					}
					else {
						continue;
					}
				}
				else if(tmp == 'N'){
					if(addTimes %2 == 1){
						GoldA -= Gold;
						GoldB += Gold;
						Log.write("A弃牌");
					}
					else {
						GoldA += Gold;
						GoldB -= Gold;
						Log.write("B弃牌");
					}
				}
				else {
					Log.write("wrong input");
				}
			}
		}
		Log.write("playA 剩余金币 " + GoldA + "损益为" + (GoldA - 1000) );
		Log.write("playB 剩余金币 " + GoldB + "损益为" + (GoldB - 1000) );
		scanner.close();
	}
}
