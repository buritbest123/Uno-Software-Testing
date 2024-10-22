/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

import domain.card.CardColor;
import domain.card.WildCard;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TestEqualWildCard {


    @Test
    public void TestEqualWildCard_inter_T1(){
        var wildCard1 = CardTestFactory.createWildColorCard();
        var wildCard2 = CardTestFactory.createWildColorCard();

        assertTrue(wildCard1.equals(wildCard2));
    }

    @Test
    public void TestEqualWildCard_inter_T2(){
        var wildCard1 = CardTestFactory.createWildColorCard();
        var wildCard2 = CardTestFactory.createWildColorCard();
        WildCard nullCard = null;

        assertFalse(wildCard1.equals(nullCard));
    }

    @Test
    public void TestEqualWildCard_inter_T3(){
        var wildCard1 = CardTestFactory.createWildColorCard();

        var wildCard2 = CardTestFactory.createWildColorCard();
        WildCard nullCard = null;

        assertFalse(wildCard2.equals(nullCard));
    }


    @Test
    public void TestEqualWildCard_func_T1(){
        var wildCard1 = CardTestFactory.createWildColorCard(CardColor.RED);
        var wildCard2 = CardTestFactory.createWildColorCard(CardColor.RED);

        assertTrue(wildCard1.equals(wildCard2));
    }

    @Test
    public void TestEqualWildCard_func_T2(){
        var wildCard1 = CardTestFactory.createWildColorCard(CardColor.RED);
        var wildCard2 = CardTestFactory.createWildColorCard(CardColor.BLUE);

        assertFalse(wildCard1.equals(wildCard2));
    }
}
