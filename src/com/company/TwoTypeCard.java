package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class TwoTypeCard extends ParseCards{

    public TwoTypeCard(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount) {
        super(cards, colors, values, hashMap, ghostCount);
    }

    @Override
    public CardType getType(){
        int number = 0;
        for (Integer i : hashMap.keySet()){
            //找到不为鬼牌的牌的值
            if(i != 0)
            {
                number = changeA(i);
                break;
            }
        }
        return new WuTiao(cards,number);
    }
}
