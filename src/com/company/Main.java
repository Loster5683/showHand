/**
 * 文件名：Main.java
 * 描述：  程序入口
 * 创建人：yeqiang
 * 创建时间：2019/4/15
*/
package com.company;

import com.company.gameProcess.Game;

public class Main {

    public static void main(String[] args) {

		Game game = new Game();
		game.start();

//		int [] a = 	{2,1,3,1,4,1,5,1,7,1};
//		int [] b = {2,1,3,1,4,1,5,1,10,1};

//		CardType pta = ParseType.parseType(Test.generateDesk(a));
//		CardType ptb = ParseType.parseType(Test.generateDesk(b));
//		System.out.println(pta);
//		System.out.println(ptb);
//		System.out.println(pta.compareTo(ptb));
//
//		Test t = new Test();
//		t.testDiffType();
//		t.singeltest(a,b);
//		t.testSameType();
	}
}
