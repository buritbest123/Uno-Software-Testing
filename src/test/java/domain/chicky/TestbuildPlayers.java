/*Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter),
 Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
 You may use, distribute, and modify this code under the terms of the MIT license. */
package domain.chicky;

import domain.card.Card;
import domain.card.CardDeck;
import domain.game.DealerService;
import domain.game.DrawPile;
import domain.game.GameBuilder;
import domain.player.HandCardList;
import domain.player.Player;
import domain.player.PlayerRoundIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class TestbuildPlayers {
    Stack<Card> cardList = new Stack<>();
    private DrawPile drawPile;
    private GameBuilder gameBuilder;
    @BeforeEach
    public void SetUp(){
        var cards = new CardDeck().getImmutableCards();
        var shuffledCards = DealerService.shuffle(cards);
        drawPile = new DrawPile(shuffledCards);
        gameBuilder = new GameBuilder();
    }

    @Test
    void TestbuildPlayers_InterFace_T1() throws Exception {
        gameBuilder.withPlayer("Alice")
            .withPlayer("Bob");
        // Access the private method using reflection
        Method buildPlayersMethod = GameBuilder.class.getDeclaredMethod("buildPlayers", DrawPile.class);
        buildPlayersMethod.setAccessible(true);

        // You can mock the DrawPile if necessary; here we just pass null as an example
        PlayerRoundIterator players = (PlayerRoundIterator) buildPlayersMethod.invoke(gameBuilder, drawPile);

        //Assert PlayerList
        assertNotNull(players.stream().toArray());
        assertEquals(2,players.stream().count());
    }
    @Test
    void TestbuildPlayers_InterFace_T2() throws Exception {
        gameBuilder.withPlayer("Alice")
            .withPlayer("Bob");
        // Access the private method using reflection
        Method buildPlayersMethod = GameBuilder.class.getDeclaredMethod("buildPlayers", DrawPile.class);
        buildPlayersMethod.setAccessible(true);

        // You can mock the DrawPile if necessary; here we just pass null as an example
        assertThrows(NullPointerException.class, () -> new DrawPile(null));
        assertThrows(NullPointerException.class, () -> buildPlayersMethod.invoke(gameBuilder, new DrawPile(null)));
    }
    @Test
    void TestbuildPlayers1Player(){
        var gameBuilder = new GameBuilder()
            .withPlayer("Player 1");

        assertThrows(IllegalStateException.class, gameBuilder::build);
        assertThrows(NullPointerException.class, () -> new DrawPile(null));
        //Since the Game cannot be built, the game itself doesn't have a player and their card in the first place.
    }
    @Test
    void TestbuildPlayers2Player(){
        var gameBuilder = new GameBuilder()
            .withPlayer("Player 1")
            .withPlayer("Player 2");
        var temp_handCard = new HandCardList();

        var game = gameBuilder.build();
        // Assert that no exception is thrown
        var Expect = new ArrayList<String>();
        Expect.add("Player 1");
        Expect.add("Player 2");
        var Result = new ArrayList<String>();
        assertDoesNotThrow(gameBuilder::build);
        Result.add(game.getCurrentPlayer().getName());
        assertNotNull(game.getCurrentPlayer().getHandCards());
        assertNotNull(game.getCurrentPlayer().getHandCards());
        game.drawCard(game.getCurrentPlayer().getId());
        Result.add(game.getCurrentPlayer().getName());
        assertEquals(Expect,Result);
    }
    @Test
    void TestbuildPlayersMoreThan10Player(){
        var gameBuilder = new GameBuilder()
            .withPlayer("Player 1")
            .withPlayer("Player 2")
            .withPlayer("Player 3")
            .withPlayer("Player 4")
            .withPlayer("Player 5")
            .withPlayer("Player 6")
            .withPlayer("Player 7")
            .withPlayer("Player 8")
            .withPlayer("Player 9")
            .withPlayer("Player 10")
            .withPlayer("Player 11");

        assertThrows(IllegalStateException.class, gameBuilder::build);
        assertThrows(NullPointerException.class, () -> new DrawPile(null));
        //Since the Game cannot be built, the game itself doesn't have a player and their card in the first place.
    }
    @Test
    void TestbuildPlayers10Player(){
        var gameBuilder = new GameBuilder()
            .withPlayer("Player 1")
            .withPlayer("Player 2")
            .withPlayer("Player 3")
            .withPlayer("Player 4")
            .withPlayer("Player 5")
            .withPlayer("Player 6")
            .withPlayer("Player 7")
            .withPlayer("Player 8")
            .withPlayer("Player 9")
            .withPlayer("Player 10");
        // Assert that no exception is thrown
        assertDoesNotThrow(gameBuilder::build);
    }
}
