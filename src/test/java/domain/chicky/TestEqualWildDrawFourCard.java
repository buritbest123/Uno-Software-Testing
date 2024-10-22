/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

import domain.card.CardColor;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEqualWildDrawFourCard {

    @Test
    public void TestEqualWildDrawFourCard_inter_T1(){
        var wildDrawFourCard1 = CardTestFactory.createWildDrawFourCard();
        var wildDrawFourCard2 = CardTestFactory.createWildDrawFourCard();

        assertTrue(wildDrawFourCard1.equals(wildDrawFourCard2));
    }

    @Test
    public void TestEqualWildDrawFourCard_inter_TT2(){
        var wildDrawFourCard1 = CardTestFactory.createWildDrawFourCard(CardColor.RED);

        assertFalse(wildDrawFourCard1.equals(null));
    }


    @Test
    public void TestEqualWildDrawFourCard_func_T1(){
        var wildDrawFourCard2 = CardTestFactory.createWildDrawFourCard(CardColor.RED);
        var wildDrawFourCard3 = CardTestFactory.createWildDrawFourCard(CardColor.RED);

        assertTrue(wildDrawFourCard3.equals(wildDrawFourCard2));
    }

    @Test
    public void TestEqualWildDrawFourCard_func_T2(){
        var wildDrawFourCard1 = CardTestFactory.createWildDrawFourCard(CardColor.RED);
        var wildDrawFourCard2 = CardTestFactory.createWildDrawFourCard(CardColor.BLUE);

        assertFalse(wildDrawFourCard1.equals(wildDrawFourCard2));
    }

}
