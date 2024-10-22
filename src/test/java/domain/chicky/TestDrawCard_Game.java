/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

import domain.card.*;
import domain.game.DealerService;
import domain.game.DrawPile;
import domain.game.Game;
import domain.player.Player;
import domain.player.PlayerRoundIterator;
import domain.testhelper.CardTestFactory;
import domain.testhelper.PlayerTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestDrawCard_Game {
    private Game game;
    private DrawPile drawPile;
    private PlayerRoundIterator playerRoundIterator;
    private Player[] players;
    private PlayerRoundIterator playersIterator;

    @BeforeEach
    public void setUp() {
        var cards = new CardDeck().getImmutableCards();
        var shuffledCards = DealerService.shuffle(cards);
        drawPile = new DrawPile(shuffledCards);
        players = PlayerTestFactory.createPlayers(2);
        playerRoundIterator =  new PlayerRoundIterator(players);

        // Set up the Game instance with the mock objects
        game = new Game(drawPile, playerRoundIterator);

    }
    @Test
    public void TestDrawCard_Game_Interface_T1() throws Exception {
        var cardToDraw = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(3, CardColor.GREEN);
        var drawPile = createDrawPile(cardToDraw,topCard);
        var game = new Game(drawPile,playersIterator);

        // Access the private method using reflection
        Method drawCardsMethod = Game.class.getDeclaredMethod("drawCards", Player.class, int.class);
        drawCardsMethod.setAccessible(true);
        // Act
        // Call the private method and get the result
        List<Card> drawnCards = (List<Card>) drawCardsMethod.invoke(game, players[0], -1);
        // Verify the result
        assertEquals(0, drawnCards.size(), "Player should not have drawn a card");
    }
    @Test
    public void TestDrawCard_Game_Interface_T2() throws Exception {
        var cardToDraw = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(3, CardColor.GREEN);
        var drawPile = createDrawPile(cardToDraw,topCard);
        var game = new Game(drawPile,playersIterator);

        // Access the private method using reflection
        Method drawCardsMethod = Game.class.getDeclaredMethod("drawCards", Player.class, int.class);
        drawCardsMethod.setAccessible(true);
        // Act
        // Call the private method and get the result
        List<Card> drawnCards = (List<Card>) drawCardsMethod.invoke(game, players[0], 0);
        // Verify the result
        assertEquals(0, drawnCards.size(), "Player should not have drawn a card");
    }
    @Test
    public void TestDrawCard_Game_Interface_T3() throws Exception {
        var cardToDraw = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(3, CardColor.GREEN);
        var drawPile = createDrawPile(cardToDraw,topCard);
        var game = new Game(drawPile,playersIterator);

        // Access the private method using reflection
        Method drawCardsMethod = Game.class.getDeclaredMethod("drawCards", Player.class, int.class);
        drawCardsMethod.setAccessible(true);
        // Act
        // Call the private method and get the result
        List<Card> drawnCards = (List<Card>) drawCardsMethod.invoke(game, players[0], 1);
        // Verify the result
        assertEquals(1, drawnCards.size(), "Player should have drawn 1 card");
        assertEquals(cardToDraw, drawnCards.get(0), "The drawn card should match the Expect Card");
    }    @Test
    public void TestDrawCard_Game_Interface_T4() throws Exception {
        var cardToDraw = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(3, CardColor.GREEN);
        var drawPile = createDrawPile(cardToDraw,topCard);
        var game = new Game(drawPile,playersIterator);

        // Access the private method using reflection
        Method drawCardsMethod = Game.class.getDeclaredMethod("drawCards", Player.class, int.class);
        drawCardsMethod.setAccessible(true);
        // Act
        // Call the private method and get the result
        List<Card> drawnCards = (List<Card>) drawCardsMethod.invoke(game, null, -1);
        // Verify the result
        assertEquals(0, drawnCards.size(), "Player should not have drawn a card");
    }
    @Test
    public void TestDrawCard_Game_Interface_T5() throws Exception {
        var cardToDraw = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(3, CardColor.GREEN);
        var drawPile = createDrawPile(cardToDraw,topCard);
        var game = new Game(drawPile,playersIterator);

        // Access the private method using reflection
        Method drawCardsMethod = Game.class.getDeclaredMethod("drawCards", Player.class, int.class);
        drawCardsMethod.setAccessible(true);
        // Act
        // Call the private method and get the result
        List<Card> drawnCards = (List<Card>) drawCardsMethod.invoke(game, null, 0);
        // Verify the result
        assertEquals(0, drawnCards.size(), "Player should not have drawn a card");
    }
    @Test
    public void TestDrawCard_Game_Interface_T6() throws Exception {
        var cardToDraw = CardTestFactory.createNumberCard(1, CardColor.RED);
        var topCard = CardTestFactory.createNumberCard(3, CardColor.GREEN);
        var drawPile = createDrawPile(cardToDraw,topCard);
        var game = new Game(drawPile,playersIterator);

        // Access the private method using reflection
        Method drawCardsMethod = Game.class.getDeclaredMethod("drawCards", Player.class, int.class);
        drawCardsMethod.setAccessible(true);
        // Act
        // Call the private method and get the result
        assertThrows(NullPointerException.class, () -> game.drawCard(null));

    }
    @Test //ข้อ 7
    public void TestDrawCard_Game_t1(){
        // Arrange
        var testDeck = new ArrayList<Card>();
        var cardToDraw = CardTestFactory.createNumberCard(1,CardColor.RED);
        testDeck.add(cardToDraw);
        testDeck.add(CardTestFactory.createNumberCard(2,CardColor.GREEN));

        var drawPile = new DrawPile(testDeck);
        var players = PlayerTestFactory.createPlayers(2);
        players[0].addToHandCards(CardTestFactory.createNumberCard(2,CardColor.BLUE));
        players[1].addToHandCards(CardTestFactory.createNumberCard(2,CardColor.BLUE));
        var playersIterator = new PlayerRoundIterator(players);

        var game = new Game(drawPile,playersIterator);
        System.out.println(game.getCurrentPlayer().getName());
        // Act
        assertEquals(game.getCurrentPlayer().getName(),players[0].getName());
        game.drawCard(game.getCurrentPlayer().getId());
        // Assert
        System.out.println(Arrays.toString(players[0].getHandCards().toArray()));
        var Result = players[0].getHandCards().count() == 2;
//        assertEquals(2, players[0].getHandCards().count());
        assertTrue(Result);
    }
//    @Test //ข้อ 7
//    public void TestDrawCard_Game_t2(){
//        var testDeck = new ArrayList<Card>();
//        var cardToDraw = CardTestFactory.createNumberCard(1,CardColor.RED);
//        testDeck.add(cardToDraw);
//        testDeck.add(CardTestFactory.createNumberCard(2,CardColor.GREEN));
//
//        var drawPile = new DrawPile(testDeck);
//        var players = PlayerTestFactory.createPlayers(2);
//        players[0].addToHandCards(CardTestFactory.createNumberCard(2,CardColor.BLUE));
//        players[1].addToHandCards(CardTestFactory.createNumberCard(2,CardColor.BLUE));
//        var playersIterator = new PlayerRoundIterator(players);
//
//        var game = new Game(drawPile,playersIterator);
//        System.out.println(game.getCurrentPlayer().getName());
//        // Act
//        assertNotEquals(game.getCurrentPlayer().getName(),players[1].getName());
//        game.drawCard(players[0].getId());
//        // Assert
//        players[0].removePlayedCard(cardToDraw);
//        var Result = players[0].getHandCards().count() == 2;
////        assertEquals(2, players[0].getHandCards().count());
//        assertFalse(Result);
//    }
//    @Test //ข้อ 7
//    public void TestDrawCard_Game_t3(){
//        // Arrange
//        var testDeck = new ArrayList<Card>();
//        var cardToDraw = CardTestFactory.createWildColorCard(CardColor.RED);
//        testDeck.add(cardToDraw);
//        testDeck.add(CardTestFactory.createNumberCard(2,CardColor.GREEN));
//
//        var drawPile = new DrawPile(testDeck);
//        var players = PlayerTestFactory.createPlayers(2);
//        players[0].addToHandCards(CardTestFactory.createNumberCard(2,CardColor.BLUE));
//        players[1].addToHandCards(CardTestFactory.createNumberCard(2,CardColor.BLUE));
//        var playersIterator = new PlayerRoundIterator(players);
//
//        var game = new Game(drawPile,playersIterator);
//        System.out.println(game.getCurrentPlayer().getName());
//        // Act
//        assertEquals(game.getCurrentPlayer().getName(),players[0].getName());
//        game.drawCard(game.getCurrentPlayer().getId());
//        // Assert
//        System.out.println(game.peekTopCard());
//        var Result = players[0].getHandCards().count() == 2;
////        assertEquals(2, players[0].getHandCards().count());
//        assertTrue(Result);
//    }
//    @Test //ข้อ 7
//    public void TestDrawCard_Game_t4(){
//        var testDeck = new ArrayList<Card>();
//        var cardToDraw = CardTestFactory.createWildColorCard(CardColor.RED);
//        testDeck.add(cardToDraw);
//        testDeck.add(CardTestFactory.createNumberCard(2,CardColor.GREEN));
//
//        var drawPile = new DrawPile(testDeck);
//        var players = PlayerTestFactory.createPlayers(2);
//        players[0].addToHandCards(CardTestFactory.createNumberCard(2,CardColor.BLUE));
//        players[1].addToHandCards(CardTestFactory.createNumberCard(2,CardColor.BLUE));
//        var playersIterator = new PlayerRoundIterator(players);
//
//        var game = new Game(drawPile,playersIterator);
//        System.out.println(game.getCurrentPlayer().getName());
//        // Act
//        assertNotEquals(game.getCurrentPlayer().getName(),players[1].getName());
//        game.drawCard(game.getCurrentPlayer().getId());
//        // Assert
//        var Result = players[0].getHandCards().count() == 2;
////        assertEquals(2, players[0].getHandCards().count());
//        assertTrue(Result);
//    }
    private DrawPile createDrawPile(Card... cards) {
        return new DrawPile(Arrays.asList(cards));
    }

    private void playCardFromCurrentPlayer(Game game, Card cardToPlay) {
        game.playCard(playersIterator.getCurrentPlayer().getId(), cardToPlay);
    }

    private void assertGameState(Game game, Card expectedTopCard, String expectedCurrentPlayer) {
        assertEquals(expectedTopCard, game.peekTopCard());
        assertEquals(expectedCurrentPlayer, game.getCurrentPlayer().getName());
    }
}

