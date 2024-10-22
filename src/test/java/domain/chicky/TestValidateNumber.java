/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

import domain.card.CardColor;
import domain.card.CardUtil;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValidateNumber {
    @Test
    void TestValidateNumber_Interface_T1(){
        assertDoesNotThrow(() -> CardTestFactory.createNumberCard(1, CardColor.RED));
    }
    @Test
    void TestValidateNumber_Interface_T2(){
        assertThrows(IllegalArgumentException.class, () -> CardUtil.validateNumber(CardTestFactory.createNumberCard(-1,CardColor.RED).getValue()));
    }
    @Test
    void TestValidateNumber_Functional_T1(){
        assertThrows(IllegalArgumentException.class, () -> CardTestFactory.createNumberCard(-1, CardColor.BLUE));
    }
    @Test
    void TestValidateNumber_Functional_T2(){
        assertDoesNotThrow(() -> CardTestFactory.createNumberCard(0, CardColor.RED));
    }
    @Test
    void TestValidateNumber_Functional_T3(){
        assertThrows(IllegalArgumentException.class, () -> CardTestFactory.createNumberCard(11, CardColor.BLUE));
    }
}
