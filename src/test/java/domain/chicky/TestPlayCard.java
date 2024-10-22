/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

import domain.card.*;
import domain.game.DrawPile;
import domain.game.Game;
import domain.player.Player;
import domain.player.PlayerRoundIterator;
import domain.testhelper.CardTestFactory;
import domain.testhelper.PlayerTestFactory;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayCard {

    private final Player[] players = PlayerTestFactory.createPlayers(2);
    private final PlayerRoundIterator playersIterator = new PlayerRoundIterator(players);

    //Test case 2
    // interface-based
    @Test
    public void TestPlayCard_inter_T1(){

        var cardToPlay = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(5, CardColor.RED);
        var game = createGame(cardToPlay, topCard);

        assertDoesNotThrow(() -> game.playCard(game.getCurrentPlayer().getId(), cardToPlay, true));
    }

    @Test
    public void TestPlayCard_inter_T2(){

        var cardToPlay = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createSkipCard(CardColor.RED);
        var game = createGame(cardToPlay, topCard);

        assertThrows(NullPointerException.class, () -> game.playCard(game.getCurrentPlayer().getId(), null, false));
    }

    @Test
    public void TestPlayCard_inter_T3(){

        var cardToPlay = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(1, CardColor.RED);
        var game = createGame(cardToPlay, topCard);

        assertThrows(IllegalArgumentException.class, () -> game.playCard(null, cardToPlay, true));
    }

    @Test
    public void TestPlayCard_inter_T4(){

        var cardToPlay = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(1, CardColor.RED);

        var game = createGame(cardToPlay, topCard);

        assertThrows(IllegalArgumentException.class, () -> game.playCard(null, null, true));
    }

    //func-base
    @Test
    public void TestPlayCard_func_T1(){

        var cardToPlay = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(1, CardColor.RED);
        var game = createGame(cardToPlay, topCard);

        assertDoesNotThrow(() -> game.playCard(game.getCurrentPlayer().getId(), cardToPlay));
    }

    @Test
    public void TestPlayCard_func_T2(){

        var cardToPlay = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createSkipCard(CardColor.RED);
        var game = createGame(cardToPlay, topCard);

        assertDoesNotThrow(() -> game.playCard(game.getCurrentPlayer().getId(), cardToPlay));
    }

    @Test
    public void TestPlayCard_func_T3(){

        var cardToPlay = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(1, CardColor.GREEN);
        var game = createGame(cardToPlay, topCard);

        assertDoesNotThrow(() -> game.playCard(game.getCurrentPlayer().getId(), cardToPlay));
    }

    @Test
    public void TestPlayCard_func_T4(){

        var cardToPlay = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createSkipCard(CardColor.GREEN);
        var game = createGame(cardToPlay, topCard);

        assertThrows(IllegalArgumentException.class, () -> game.playCard(game.getCurrentPlayer().getId(), cardToPlay));
    }


    //Helper function
    //==================================================================================================

    private Game createGame(Card cardToPlay, Card... drawPileCards) {
        var drawPile = createDrawPile(drawPileCards);

        var game = new Game(drawPile, playersIterator);

        var cardToAdd = CardUtil.isWildCard(cardToPlay)
            ? CardTestFactory.createWildCard(cardToPlay.getType())
            : cardToPlay;

        playersIterator.getCurrentPlayer().addToHandCards(cardToAdd);

        return game;
    }

    private DrawPile createDrawPile(Card... cards) {
        return new DrawPile(Arrays.asList(cards));
    }
}
