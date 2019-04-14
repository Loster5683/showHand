package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class SixTypeCard extends ParseCards{

    public SixTypeCard(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount) {
        super(cards, colors, values, hashMap, ghostCount);
    }

    @Override
    public CardType getType() {

        int color = 0;
        int number = 0;
        int number2 = 0;

        // 同花
        if(colors.size() == 1)
        {
            // 同花顺
            if (isConnected(values,ghostCount,number)){
                color = colors.get(0);
                return new TongHuaShun(cards,number,color);
            }
            // 普通同花
            else {
                number = (values.get(0) == 1)?1:values.get(4);
                color = getColor(number);
                number = (values.get(0) == 1)?14:values.get(4);
                return new TongHua(cards,number,color);
            }
        }
        // 非同花
        else {
            // 是顺子
            if(isConnected(values,ghostCount,number)){
                int tmp = 0;
                tmp = (values.get(0) == 1)?1:values.get(4);
                color = getColor(tmp);
                return new ShunZi(cards,number,color);
            }
            // 散牌
            else {
                number = (values.get(0) == 1)?1:values.get(4);
                color = getColor(number);
                number = (values.get(0) == 1)?14:values.get(4);
                return new SanPai(cards,number,color);
            }
        }
    }
}
