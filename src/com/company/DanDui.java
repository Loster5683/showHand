package com.company;

import java.util.ArrayList;

public class DanDui extends CardType
{
    private int type;
    private int number;
    private int color;
    private ArrayList<Card> cards;

    public DanDui(ArrayList<Card> cards, int number, int color)
    {
        this.number = number;
        this.color = color;
        type = 9;
    }

    public int getColor()
    {
        return color;
    }

    public int getNumber()
    {

        return number;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int compareTo(CardType o)
    {
        if(this.type != o.type)
        {
            return compare(o.getType(),type);
        }

        if(this.number != ((DanDui)(o)).getNumber())
        {
            return this.compare(this.number,((DanDui)o).getNumber());
        }

        return compare(((DanDui)o).getColor(),this.color);
    }

    public String toString()
    {
        return types[type];
    }

}