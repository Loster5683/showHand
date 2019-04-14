package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedMap;

/**
 * 程序入口
 */
public class Main {

    public static void main(String[] args) {

//		Game game = new Game();
//		game.start();

		int [] a = 						{1,0,2,0,3,0,4,0,5,0};
        ;
		int [] b = 						{2,0,3,0,4,0,5,0,6,0};
        ;
        ;

        CardType pta = ParseType.parseType(Test.generateDesk(a));
        CardType ptb = ParseType.parseType(Test.generateDesk(b));

        System.out.println(pta);
        System.out.println(ptb);

        System.out.println(pta.compareTo(ptb));
        Test t = new Test();
//         t.testDiffType();
//
//         t.singeltest(a,b);

        t.testSameType();


	}
}
