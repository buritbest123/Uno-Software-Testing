/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='

import domain.card.Card;
import domain.card.CardColor;
import domain.player.HandCardList;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TestRemoveCard {

    // Interface Base
    @Test
    public void TestRemoveCard_inter_t1() {
        var testHand = new HandCardList();
        var ExpectedHand = new HandCardList();

        var red1 = CardTestFactory.createNumberCard(1, CardColor.RED);
        var green2 = CardTestFactory.createNumberCard(2, CardColor.GREEN);
        var blue3 = CardTestFactory.createNumberCard(3, CardColor.BLUE);
        var yellow4 = CardTestFactory.createNumberCard(4, CardColor.YELLOW);

        testHand.addCard(red1);
        testHand.addCard(green2);
        testHand.addCard(blue3);
        testHand.addCard(yellow4);

        ExpectedHand.addCard(green2);
        ExpectedHand.addCard(blue3);
        ExpectedHand.addCard(yellow4);

        testHand.removeCard(red1);

        List<Card> testHandlist = testHand.getCardStream().collect(Collectors.toList());
        List<Card> ExpectedHandlist = ExpectedHand.getCardStream().collect(Collectors.toList());

        assertEquals(testHandlist, ExpectedHandlist);
    }

    @Test
    public void TestRemoveCard_inter_t2() {
        var testHand = new HandCardList();
        var ExpectedHand = new HandCardList();

        try {
            testHand.removeCard(null);
        }catch (NullPointerException ignored){

        }

        List<Card> testHandlist = testHand.getCardStream().collect(Collectors.toList());
        List<Card> ExpectedHandlist = ExpectedHand.getCardStream().collect(Collectors.toList());

        assertEquals(testHandlist, ExpectedHandlist);
    }


    //Functionality Base
    @Test
    public void TestRemoveCard_func_t1() {
        var testHand = new HandCardList();
        var ExpectedHand = new HandCardList();

        var red1 = CardTestFactory.createNumberCard(1, CardColor.RED);
        var green2 = CardTestFactory.createNumberCard(2, CardColor.GREEN);
        var blue3 = CardTestFactory.createNumberCard(3, CardColor.BLUE);
        var yellow4 = CardTestFactory.createNumberCard(4, CardColor.YELLOW);

        testHand.addCard(red1);
        testHand.addCard(green2);
        testHand.addCard(blue3);
        testHand.addCard(yellow4);

        ExpectedHand.addCard(green2);
        ExpectedHand.addCard(blue3);
        ExpectedHand.addCard(yellow4);

        testHand.removeCard(red1);

        List<Card> testHandlist = testHand.getCardStream().collect(Collectors.toList());
        List<Card> ExpectedHandlist = ExpectedHand.getCardStream().collect(Collectors.toList());

        assertEquals(testHandlist, ExpectedHandlist);
    }

    @Test
    public void TestRemoveCard_func_t2() {
        var testHand = new HandCardList();
        var ExpectedHand = new HandCardList();

        var wildCard = CardTestFactory.createWildColorCard(CardColor.RED);
        var green2 = CardTestFactory.createNumberCard(2, CardColor.GREEN);
        var blue3 = CardTestFactory.createNumberCard(3, CardColor.BLUE);
        var yellow4 = CardTestFactory.createNumberCard(4, CardColor.YELLOW);

        testHand.addCard(green2);
        testHand.addCard(blue3);
        testHand.addCard(yellow4);

        ExpectedHand.addCard(green2);
        ExpectedHand.addCard(blue3);
        ExpectedHand.addCard(yellow4);

        testHand.removeCard(wildCard);

        List<Card> testHandlist = testHand.getCardStream().collect(Collectors.toList());
        List<Card> ExpectedHandlist = ExpectedHand.getCardStream().collect(Collectors.toList());

        assertEquals(testHandlist, ExpectedHandlist);
    }
}
