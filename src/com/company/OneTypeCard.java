package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class OneTypeCard extends ParseCards{


    public OneTypeCard(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount) {
        super(cards, colors, values, hashMap, ghostCount);
    }

    @Override
    public CardType getType(){
        return new WuGui(cards);
    }

}
