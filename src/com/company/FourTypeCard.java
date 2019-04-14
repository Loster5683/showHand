package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class FourTypeCard  extends ParseCards{
    public FourTypeCard(ArrayList<Card> cards, ArrayList<Integer> colors, ArrayList<Integer> values, HashMap<Integer, ArrayList<Integer>> hashMap, int ghostCount) {
        super(cards, colors, values, hashMap, ghostCount);
    }

    @Override
    public CardType getType() {
        int color = 0;
        int number = 0;
        int number2 = 0;
        //两张鬼牌
        if(ghostCount == 2)
        {
            //除去鬼牌只有一种花色
            if(colors.size() == 1)
            {
                boolean flag = false;
                //记录花色
                color = colors.get(0);
                // 判断是否构成同花顺
                if(isConnected(values,ghostCount,number)){
                    flag = true;
                }
                // 普通同花,由于有两种鬼牌，所以number,number2取不同的最大值
                else {
                    number = 14;
                }
                return (flag)?new TongHuaShun(cards,number,color):new TongHua(cards,number,color);
            }
            // 有多种花色
            else {
                // 判断是否构成顺子
                if(isConnected(values,ghostCount,number)){
                    //找到最大牌的花色
                    color = 0;
                    return new ShunZi(cards,number,color);
                }
                // 三条，取最大值作为三条的值
                else {
                    number = (values.get(0)==1)?1:values.get(2);
                    color = getColor(number);
                    number = (values.get(0)==1)?14:values.get(2);
                    return new SanTiao(cards,number,color);
                }
            }
        }
        // 只有一张鬼牌，则一定构成三条牌型
        else if(ghostCount == 1)
        {
            for(Integer a:hashMap.keySet()){
                if(hashMap.get(a).size()==2)
                {
                    number = changeA(a);
                    color = getColor(a);
                    break;
                }
            }
            return new SanTiao(cards,number,color);
        }
        // 没有鬼牌，有三一一和二二一两种分法，分别构成三条和二对
        else if(ghostCount == 0)
        {
            boolean flag = false;
            int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
            for(Integer a:hashMap.keySet()){
                // 三条取三张的值
                if(hashMap.get(a).size()==3)
                {
                    flag = true;
                    number = changeA(a);
                    color = getColor(a);
                    break;
                }
                //找到大对子及大对子最大花色
                if(hashMap.get(a).size()==2)
                {   flag = false;
                    max = Math.max(a,max);
                    min = Math.min(a,min);
                }
            }
            // 类型为二对的话需要讨论对子大小
            if(!flag){
                //存在A
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
            return (flag)?new SanTiao(cards,number,color):new ErDui(cards,number,number2,color);
        }
        Log.write("鬼牌数错误");
        return new CardType();
    }
}
