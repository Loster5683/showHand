package com.company;

import java.util.ArrayList;
import java.util.Random;

public class ShuffleCard
{
	private ArrayList<Card> init_cards = new ArrayList<Card>();
	private ArrayList<Card> cards = new ArrayList<Card>();
	private static final char [] s = {'A','2','3','4','5','6','7','8','9','T','J','Q','K'};
	private int start = 0;


	public ShuffleCard()
	{
		init();
		int size = init_cards.size();
		if(size <= 1){
			return;
		}

		while (init_cards.size()>0){
			Random s = new Random();
			int j = s.nextInt(init_cards.size());
			cards.add(init_cards.get(j));
			init_cards.remove(j);
		}
	}

	public void init()
	{
		int i = 0,j = 0;
		for ( i = 0; i <= 3; i++)
		{
			for(j = 1; j <= 13; j++)
			{
				init_cards.add(new Card(false,i,j));
			}
		}
		for( i = 0; i < 5; i++)
		{
			init_cards.add(new Card(true));
		}
	}
	public void printQueue(){
		for(Card card : cards){
			System.out.print(card.toString()+'\n');
		}
	}

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
