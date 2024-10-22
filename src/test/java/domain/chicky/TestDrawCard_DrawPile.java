/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

import domain.card.Card;
import domain.card.CardColor;
import domain.game.DrawPile;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestDrawCard_DrawPile {

    // test case 3
    // interface-based

    //Disable due to impossibility to create stack size of -1
//    @Test
//    public void TestDrawCard_DrawPile_interBase_T1(){
//
//        var drawPile = new DrawPile(Arrays.asList());
//        assertThrows(EmptyStackException.class, () -> drawPile.drawCard());
//    }

    @Test
    public void TestDrawCard_DrawPile_inter_T2(){

        var drawPile = new DrawPile(Arrays.asList());
        assertThrows(EmptyStackException.class, () -> drawPile.drawCard());
//        assertTrue(drawPile.drawCard() instanceof Card);
    }

    @Test
    public void TestDrawCard_DrawPile_inter_T3(){

        var card1 = CardTestFactory.createNumberCard(1, CardColor.RED);
        var drawPile = new DrawPile(List.of(card1));

        assertTrue(drawPile.drawCard() instanceof Card);
    }



    // functionality-based
    @Test
    //Draw from empty should result in exception
    public void TestDrawCard_DrawPile_func_T1(){

        var card1 = CardTestFactory.createNumberCard(1, CardColor.RED);
        var drawPile = new DrawPile(List.of(card1));
        assertEquals(drawPile.drawCard(), card1);
    }

//    @Test
//    //Draw from not empty should not result in exception
//    public void TestDrawCard_DrawPile_func_T2(){
//
//        var card1 = CardTestFactory.createNumberCard(1, CardColor.RED);
//        var card2 = CardTestFactory.createSkipCard(CardColor.GREEN);
//
//        var drawPile = new DrawPile(Arrays.asList(card1, card2));
//        assertDoesNotThrow(() -> drawPile.drawCard());
//    }

//    @Test
//    //Draw from not empty should not result in exception
//    public void TestDrawCard_DrawPile_func_T3(){
//
//        var card1 = CardTestFactory.createNumberCard(1, CardColor.RED);
//        var card2 = CardTestFactory.createSkipCard(CardColor.GREEN);
//
//        var drawPile = new DrawPile(Arrays.asList(card1, card2));
//        assertDoesNotThrow(() -> drawPile.drawCard());
//    }

    @Test
    public void TestDrawCard_DrawPile_func_T4(){

        var card1 = CardTestFactory.createWildColorCard(CardColor.RED);
        var drawPile = new DrawPile(List.of(card1));
        assertThrows(IllegalArgumentException.class, () -> drawPile.drawCard());
    }
}
