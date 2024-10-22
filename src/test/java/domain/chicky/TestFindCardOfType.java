/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

import domain.card.Card;
import domain.card.CardColor;
import domain.card.CardType;
import domain.player.HandCardList;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;

public class TestFindCardOfType {

    private HandCardList handCardList;
    private CardType Type;  // Assume you have a CardType and Card class

    @BeforeEach
    public void setUp() {
        handCardList = new HandCardList();
    }

    @Test
    public void testFindCardOfType_InterfaceBase_T1() throws Exception {
        Type = CardType.NUMBER;
        handCardList.addCard(CardTestFactory.createNumberCard(1,CardColor.GREEN));  // Add a card with a known type

        // Use reflection to access the private method
        Method findCardOfTypeMethod = HandCardList.class.getDeclaredMethod("findCardOfType", CardType.class);
        findCardOfTypeMethod.setAccessible(true);  // Make the method accessible

        // Invoke the private method and assert the result
        Card result = (Card) findCardOfTypeMethod.invoke(handCardList, Type);
        assertNotNull(result, "Card should be found");
        assertEquals(Type, result.getType(), "Card type should match");
    }
    @Test
    public void testFindCardOfType_InterfaceBase_T2() throws Exception {
        Type = null;
        handCardList.addCard(CardTestFactory.createNumberCard(1,CardColor.GREEN));  // Add a card with a known type

        // Use reflection to access the private method
        Method findCardOfTypeMethod = HandCardList.class.getDeclaredMethod("findCardOfType", CardType.class);
        findCardOfTypeMethod.setAccessible(true);  // Make the method accessible

        // Invoke the private method and assert the result
        Card result = (Card) findCardOfTypeMethod.invoke(handCardList, Type);
        assertNull(result, "Card should not be found");
    }
    // Add more tests as needed
    @Test
    public void testFindCardOfType_Functional_T1() throws Exception {
        Type = CardType.NUMBER;
        var cardToAdd = CardTestFactory.createNumberCard(1,CardColor.GREEN);
        handCardList.addCard(cardToAdd);  // Add a card with a known type
        handCardList.addCard(CardTestFactory.createSkipCard(CardColor.RED));  // Add a card with a known type
        handCardList.addCard(CardTestFactory.createWildColorCard());  // Add a card with a known type

        // Use reflection to access the private method
        Method findCardOfTypeMethod = HandCardList.class.getDeclaredMethod("findCardOfType", CardType.class);
        findCardOfTypeMethod.setAccessible(true);  // Make the method accessible

        // Invoke the private method and assert the result
        Card result = (Card) findCardOfTypeMethod.invoke(handCardList, Type);
        assertEquals(result, cardToAdd);
    }
    @Test
    public void testFindCardOfType_Functional_T2() throws Exception {
        Type = CardType.REVERSE;

        // Use reflection to access the private method
        Method findCardOfTypeMethod = HandCardList.class.getDeclaredMethod("findCardOfType", CardType.class);
        findCardOfTypeMethod.setAccessible(true);  // Make the method accessible

        // Invoke the private method and assert the result
        Card result = (Card) findCardOfTypeMethod.invoke(handCardList, Type);
        assertNull(result);
    }
    @Test
    public void testFindCardOfType_Functional_T3() throws Exception {
        Type = null;
        var cardToAdd = CardTestFactory.createNumberCard(2,CardColor.YELLOW);
        handCardList.addCard(cardToAdd);  // Add a card with a known type
        handCardList.addCard(CardTestFactory.createReverseCard(CardColor.BLUE));  // Add a card with a known type
        handCardList.addCard(CardTestFactory.createWildColorCard());  // Add a card with a known type

        // Use reflection to access the private method
        Method findCardOfTypeMethod = HandCardList.class.getDeclaredMethod("findCardOfType", CardType.class);
        findCardOfTypeMethod.setAccessible(true);  // Make the method accessible

        // Invoke the private method and assert the result
        Card result = (Card) findCardOfTypeMethod.invoke(handCardList, Type);
        assertNull(result);
    }
    @Test
    public void testFindCardOfType_Functional_T4() throws Exception {
        Type = null;

        // Use reflection to access the private method
        Method findCardOfTypeMethod = HandCardList.class.getDeclaredMethod("findCardOfType", CardType.class);
        findCardOfTypeMethod.setAccessible(true);  // Make the method accessible

        // Invoke the private method and assert the result
        Card result = (Card) findCardOfTypeMethod.invoke(handCardList, Type);
        assertNull(result);
    }
}
