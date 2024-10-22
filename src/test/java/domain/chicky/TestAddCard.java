/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

import domain.card.CardColor;
import domain.player.HandCardList;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAddCard {

    @Test
    public void TestAddToHand_InterfaceAndFunctional_T1(){
        var handCard = new HandCardList();
        var cardInHand = CardTestFactory.createNumberCard(4,CardColor.BLUE);
        var TestCard = CardTestFactory.createNumberCard(4,CardColor.RED);
        handCard.addCard(cardInHand);
        handCard.addCard(TestCard);

        var Hand = handCard.getCardStream().toArray();
        assertEquals(Hand[0],cardInHand);
        assertEquals(Hand[1],TestCard);
    }
    @Test
    public void TestAddToHand_InterfaceAndFunctional_T2(){
        var handCard = new HandCardList();
        var cardInHand = CardTestFactory.createNumberCard(4,CardColor.BLUE);
        var TestCard = CardTestFactory.createWildColorCard(CardColor.RED);
        handCard.addCard(cardInHand);
        handCard.addCard(TestCard);

        var Hand = handCard.getCardStream().toArray();
        assertEquals(Hand[0],cardInHand);
        assertEquals(Hand[1],TestCard);
    }
}
