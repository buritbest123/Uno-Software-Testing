# 🤩❤️✨ ChickyShiningSparklingStar_CuteCute 💫🌟🎀: 🃏 UNO - Card Game Testing

**ITCS473 - Software Quality Assurance and Testing**  
🏫 Mahidol University, ICT  

## 🗂 Table of Contents
1. [Introduction](#-introduction)
2. [Project Details](#-project-details)
3. [Test Case Documentation](#-test-case-documentation)
4. [Test Results](#-test-results)
---

## 🚀 Introduction
This repository showcases our team's **contributions** to **enhancing** the UNO card game project as part of the **"Project Assignment 1: Unit Test for Open-Source Software Projects"**. We focused on **improving the test coverage** and **reliability** of the game by implementing **10 additional unit test cases** to thoroughly **validate various game functionalities and scenarios**.

---

## 📌 Project Details
- **GitHub Repository**: UNO by tk-codes ([Link to Repository](https://github.com/tk-codes/uno))
- **Star Count**: 🌟 106 ⭐️
- **Programming Language**: Java ☕️
- **Existing Testing Framework**: Gradle 🐘

---

## 🧩 Added Unit Test Cases

We have developed 10 new unit test cases to cover various functionalities of the UNO card game. These test cases ensure that all game rules and scenarios are handled correctly.


---

## 📑 Test Case Documentation
Here's the full README version of your document:

---

# Uno Game Test Cases

This README details various test cases for the Uno game. Each test case is focused on validating different parts of the game's functionality, such as card removal, turn-based actions, and the interaction between game elements. These test cases aim to ensure robust and correct gameplay under various scenarios.

---

## Test Cases

### 1. Test Case Name: `TestRemoveCard`

- **Goal**: Ensure that when a player has multiple cards of different colors, only the specified card with the given color is removed from the player's hand (HandCardList).
- **Testable Functions**: `removeCard(Card card)` (from `HandCardList` class)
  - **Input Domain Modeling**:
    - The hand consists of multiple cards of different colors and types (e.g., red, blue, wild).
    - The removeCard function removes a specific card based on its color or type.
      - **Parameters**:
        - `card`: It is the specific card the player is trying to remove from their hand, passed to the removeCard() method.
        - `handCardList`: It is the player’s hand (a list of Card objects), which can be managed and accessed using methods from the HandCardList class, such as getCardStream().
    - **Return Types**: 
      - `handCardList`: The updated list of Card objects remaining in the player's hand.
    - **Return Values**: 
      - `Expected`: The hand should contain only the cards that were not removed (e.g., blue and wild), confirming the red card was removed correctly. The method should return true indicating successful removal.
      - `Unexpected`: The hand still contains the red card, or a wrong card (e.g., blue or wild) is removed. The method should return false if the incorrect card is removed.
    - **Exceptional Behavior**: 
      - In this test scenario, we assume that when the **card type is both number types,** the **number is always the same**.
      - **Card not found in hand**:
        - If the card to be removed does not exist in the hand, the system should return false without modifying the hand indictaing the removal is unsuccessful. 
        
  - **Characteristics**:
    - Interface-Based: Verify the target card and current card list.
      - Develop characteristics
        - C1  = target card to be removed
        - C2  = handCardList
      - Partition characteristics:

| Characteristics                | b1                      | b2                  |
|---------------------------------|------------------------|--------------------|
| C1 = target card to be removed  | Not Null               | Null                 |
| C2 = handCardList               | Non-empty Card List    | Empty Card List   |

  - Identify possible value:

| Characteristics                | b1          | b2               |
|---------------------------------|-------------|------------------|
| C1 = target card to be removed  | (1, red)    | Null     |
| C2 = handCardList               | (1, red), (2, green), (3, blue), (4, yellow) | Empty      |

  - Approaches Used: ECC
    - T1 (Not Null, Non-empty Card List)
    - T2 (Null, Empty Card List)

  - Derive test values and expected values.

| Test                             | Target Card   | Card List in Hand    | Expected Result |
|----------------------------------|---------------|----------------------|-----------------|
| T1 (Not Null, Non-empty Card List) | (1, red)      | Current Card List     | True            |
| T2 (Null, Empty Card List) Remark: Impossible case, throw Null Pointer Exception | Null          | Empty | Empty |

  - **Functionality-Based**: Confirming the target card is correctly removed from the list.
    - Develop characteristics
      - C1 = Card to be removed (i.e. The card is an invalid type which violate game rule)
      - C2 = Cards in handCardList
    - Partition characteristics

| Characteristics                     | b1     | b2    |
|--------------------------------------|--------|-------|
| C1 = Card to be removed              | Valid Card | Invalid Card  |
| C2 = Cards in handCardList             | List of cards + Target Card | List of cards but no target card  |

  - Identify possible value

| Characteristics                | b1     | b2    |
|---------------------------------|--------|-------|
| C1 = Target card to be removed   | (1, red)      | (wild_color, red)  |
| C2 = Cards in handCardList   | (1, red), (2, green), (3, blue), (4, yellow)      | (2, green), (3, blue), (4, yellow)  |

  - Approaches Used: ECC
    - Test requirements:
      - T1 (Valid Card, List of cards + Target Card)
      - T2 (Invalid Card, List of cards)

  - Derive test values and expected values.

| Test                              | Card to be removed  | Cards in handCardList | Expected Result |
|-----------------------------------|---------------------|---------------------|------------------|
| T1 (Valid Card, List of cards + Target Card) | (1, red)  | (1, red), (2, green), (3, blue), (4, yellow) | (2, green), (3, blue), (4, yellow)  |
| T2 (Invalid Card, List of cards) | (wild_color, red)            | (2, green), (3, blue), (4, yellow)    | (2, green), (3, blue), (4, yellow) |

📝Test Scenarios
- Ensuring that the correct card with the specified color is removed from the hand.
    ```java
    assertEquals(testHandlist, ExpectedHandlist);
    ```
---
## 2. Test Case Name: `TestPlayCard`

- **Goal**: Ensure that a player is not allowed to play a card if it does not match the top card on the discard pile.

- **Testable Functions**: `playCard(UUID playerId, Card playedCard)` (from `Game` class)

  - **Input Domain Modeling**:
    - The player is on their turn and attempts to play a card from their hand.
    - The played card must match the top card of the discard pile either by color or type.
    - The game's discard pile has a specific card on top, which must be matched by the card played.

  - **Parameters**:
    - `cardToPlay`: The card that the player attempts to play.
    - `topCard`: The card currently on top of the discard pile, used to verify if the play is valid.
    - `playerId`: Identifies the player attempting the move.
    - `game`: The Game object that manages the draw pile, players, and the state of the game.

  - **Return Types**: 
    - **void**: This method updates the game state internally and does not return a value but throws an exception (IllegalArgumentException) if the player ID or card is null or the play is invalid.

  - **Return Values**: 
    - Expected: The game state updates correctly if the play is valid, with no exceptions thrown.
    - Unexpected: The card is played successfully even though it does not match the top card.

  - **Exceptional Behavior**:
    - Invalid card on hand: If the player's card is invalid (e.g., null or doesn’t exist in the player's hand), the system should throw an appropriate exception, such as IllegalArgumentException. This prevents the player from attempting an invalid move.
    - No cards in hand: If the player attempts to play a card when they have no cards in their hand, the game should throw an exception, such as IllegalStateException, to indicate that the player cannot play a card.

  - **Characteristics**:
    - **Interface-Based**: Verifying if the discard pile and played card are valid in the first round of the game.
      - **Develop characteristics**:
        - C1 = Player ID
        - C2 = Played card
        - C3 = Said Uno
      - **Partition characteristics**:

| Characteristics         | b1   | b2   |
|-------------------------|------|------|
| C1 = Player ID   | Not Null | Null |
| C2 = Played card  | Not Null | Null |
| C3 = Said Uno | True | False |

  - Identify possible value:

| Characteristics         | b1         | b2          |
|-------------------------|------------|-------------|
| C1 = Player ID   | game.getCurrentPlayer().getId() | Null |
| C2 = Played card  | (1, red) | Null |
| C3 = Said Uno | True | False |

  - Approaches Used: MBCC
    - Base Choice:
      - T1 (Not Null, Not Null, True)
      - T2 (Not Null, Null, False)
    - Test requirements:
      - T3 (Null, Not Null, True)
      - T4 (Null, Null, False)
  - Derive test values and expected values.

| Test                   | Player ID | Played card  | Said Uno | Expected Result                        |
|------------------------|-------------|---------------|-----------------------------|----------------------------------------|
| T1 (Not Null, Not Null, True)   | game.getCurrentPlayer().getId()    | (1, red)      | True                        | Does not throw exception               |
| T2 (Not Null, Null, False)  | game.getCurrentPlayer().getId()    | Null      | False                       | Thrown error: NullPointerException     |
| T3 (Null, Not Null, True)  | Null | (1, red)      | True                        | Thrown error: IllegalArgumentException |
| T4 (Null, Null, False)| Null| Null  | False                       | Thrown error: IllegalArgumentException |

  - **Functionality-Based**: Confirming the first played card is valid with the discard pile
    - Develop characteristics
      - C1 = Check if the played card’s color is the same as the discard pile.
      - C2 = Check if the played card’s type is the same as the discard pile.

    - Partition characteristics

| Characteristics                                      | b1   | b2   |
|------------------------------------------------------|------|------|
| C1 = Check if the played card’s color is the same as the discard pile. | True | False |
| C2 = Check if the played card’s type is the same as the discard pile.  | True | False |

  - Identify possible value

| Characteristics                                           | b1 (played, discard pile) | b2 (played, discard pile) |
|-----------------------------------------------------------|---------------------------|---------------------------|
| C1 = Check if the played card’s color is the same as the discard pile. | (red, red)                 | (red, green)               |
| C2 = Check if the played card’s type is the same as the discard pile.  | (1, 1)                     | (1, skip)                 |

  - Approaches Used: MBCC
    - Base Choice:
      - T1 (True, True)
      - T2 (True, False)
  - Test requirements:
    - T3 (False, True)
    - T4 (False, False)
  - Derive test values and expected values.

| Test            | Match Card Color (played, discard pile) | Match Card Type (played, discard pile) | Expected Result |
|-----------------|------------------|-----------------|-----------------|
| T1 (True, True)  | (red, red)       | (1, 1)          | Does not throw exception            |
| T2 (True, False) | (red, red)       | (1, skip)       | Does not throw exception            |
| T3 (False, True) | (red, green)     | (1, 1)          | Does not throw exception            |
| T4 (False, False)| (red, green)     | (1, skip)       | Thrown error: IllegalArgumentException           |

📝Test Scenarios
  - Ensure that the player can play card when card's color or card's type match with top card on discard pile without throwing exception.
      ```java
      assertDoesNotThrow(() -> game.playCard(game.getCurrentPlayer().getId(), cardToPlay));
      ```
  - Validate when played card's color or card's type do not match with top card on discard pile.
    ```java
    assertThrows(IllegalArgumentException.class, () -> game.playCard(game.getCurrentPlayer().getId(), cardToPlay));
    ```
---
## 3. Test Case Name: `TestDrawCard_DrawPile`

- **Goal**: Ensure that attempting to draw a card from an empty DrawPile throws the appropriate exception.

- **Testable Functions**: `drawCard()` (from the `DrawPile` class)

  - **Input Domain Modeling**:
    - The DrawPile is empty when a player attempts to draw a card.
    - The DrawPile is not empty when a player attempts to draw a card.

  - **Parameters**:
    - `drawPile`: `DrawPile object, which manages the stack of cards in the game. The drawPile is empty at the time of the test, meaning no cards are left to draw from. The method used to draw a card from the DrawPile is drawCard() in the DrawPile class.

  - **Return Types**: 
    - **Card**: Typically, the drawCard() method would return a Card object, but when the draw pile is empty (i.e. no card to be drawn left), it should throw an exception.
      
  - **Return Values**: 
    - **Expected**: An exception is thrown when the draw pile is empty, preventing the game from continuing.
    - **Unexpected**: No exception is thrown, or a card is incorrectly drawn from an empty pile.

  - **Exceptional Behavior**:
    - **Forced Draw Due to +2/+4 Card**: If the player receives a +2 or +4 card and is forced to draw, but the draw pile is empty, this is considered exceptional behavior. The player did not voluntarily press the draw button but was forced to draw by game mechanics. In this case, the system should throw an appropriate exception (e.g., EmptyStackException) to indicate that there are no cards left in the draw pile, even though the player was forced to draw.

  - **Characteristics**:
    - **Interface-Based**: Verify the draw pile.
      - Develop characteristics
        - C1 = draw pile size
      - Partition characteristics

| Characteristics             | b1   | b2   | b3 |
|-----------------------------|------|------|----|
| C1 = draw pile size      | <0 | 0 | >0 |

  - Identify possible value:

| Characteristics             | b1   | b2   | b3 |
|-----------------------------|------|------|----|
| C1 = draw pile size      | -1 | 0 | 1 |

  - Approaches Used: ACoC
    - Test requirements:
      - T1 (<0)
      - T2 (0)
      - T3 (>0)

  - Derive test values and expected values.

| Test      | Draw pile size | Expected Result       |
|-----------|--------------------|-----------------------|
| T1 (<0) Remarks: Impossible case, stack size cannot be less than 0. | -1               | Throw Exception: EmptyStackException |
| T2 (0)| 0           | Throw Exception: EmptyStackException           |
| T3 (>0)| 1           | Card Object           |

- **Functionality-Based**: Verify if the card is drawn from the draw pile correctly, the top card from the draw pile is drawn.
  - Develop characteristics
    - C1 = top card in draw pile
    - C2 = drawn card

  - Partition characteristics

| Characteristics                         | b1   | b2   |
|-----------------------------------------|------|------|
| C1 = top card in draw pile      | Valid Card | Invalid Card |
| C2 = drawn card                        | Valid Card | Invalid Card |

  - Identify possible value

| Characteristics                         | b1                           | b2               |
|-----------------------------------------|------------------------------|------------------|
| C1 = top card in draw pile           | (1, red)               | (wild_color, red)            |
| C2 = drawn card                        | (1, red) | (wild_color, red) |

  - Approaches Used: ACoC
  - Test requirements:
    -T1 (Valid Card, Valid Card)
    T2 (Valid Card, Invalid Card)
    T3 (Invalid Card, Valid Card)
    T4 (Invalid Card, Invalid Card)

  - Derive test values and expected values.

| Test            | Top card in draw pile | Drawn card                        | Expected Result                                                   |
|-----------------|--------------------------|------------------------------------|-------------------------------------------------------------------|
| T1 (Valid Card, Valid Card)  | (1, red)           | (1, red) | True                                                              |
| T2 (Valid Card, Invalid Card) Remarks: Non-deterministic, unable to create test. The Method drawCards add drawn card to player hand in itself method | (1, red) | (wild_color, red) | Does not throw exception |
| T3 (Invalid Card, Valid Card) Remarks: Non-deterministic, unable to create test. The Method drawCards add drawn card to player hand in itself method | (wild_color, red)    | (1, red) | Throw Error: EmptyStackException |
| T4 (Invalid Card, Invalid Card| (wild_color, red)                     | (wild_color, red)        | Thrown Error: IllegalStateException                                                        |

📝Test Scenarios
  - Ensure that the method draw correct card
      ```java
      assertEquals(drawPile.drawCard(), card1);
      ```
  - Validate that the method should not draw card and throw exception when the draw card is illegal.
    ```java
    assertThrows(IllegalArgumentException.class, () -> drawPile.drawCard());
    ```   
       
---
## 4. Test Case Name: `TestEqualWildCard`

- **Goal**: Ensure that two `WildCard` objects with different colors are not considered equal.

- **Testable Functions**: `equals(Object obj)` (overridden in the `WildCard` class)

  - **Input Domain Modeling**:
    - Two `WildCard` objects with different colors (e.g., red and blue).

  - **Parameters**:
    - `wildCard1`: The first `WildCard` object with a specific color. The method used to compare `wildCard1` with another card is `equals(Object obj)` in the `WildCard` class.
    - `wildCard2`: The second `WildCard` object with a different color. The method used to compare `wildCard2` with another card is `equals(Object obj)` in the `WildCard` class.

  - **Return Types**: 
    - **boolean**: The result of comparing the two `WildCard` objects using the `equals()` method.

  - **Return Values**:
    - **Expected**: The two `WildCard` objects should not be considered equal if they have different colors, because the `equals()` method in `WildCard` compares both type and color.
    - **Unexpected**: The two wild cards are considered equal despite having different colors.

  - **Exceptional Behavior**: 
    - **N/A**: No exceptions are expected for normal object comparisons.

  - **Characteristics**:
    - **Interface-Based**: Verify the wild cards.
      - Prefix
        - Cards are wild card
      - Develop characteristics**:
        - C1 = First wild card
        - C2 = Second wild card
      - Partition characteristics**:
     
| Characteristics               | b1   | b2   |
|-------------------------------|------|------|
| C1 = First wild card   | Not Null | Null |
| C2 = Second wild card  | Not Null | Null |

  - Identify possible value:

| Characteristics               | b1        | b2        |
|-------------------------------|-----------|-----------|
| C1 = First wild card   | (wild_color, yellow) | Null |
| C2 = Second wild card  | (wild_color, yellow) | Null |

  - Approaches Used: BCC
    - Base Choice:
      - T1 (Not Null, Not Null)
  - Test requirements:
    - T2 (Not Null, Null)
    - T3 (Null, Not Null)
  - Derive test values and expected values.

| Test            | First wild card | Second wild card | Expected Result |
|-----------------|-----------------|------------------|-----------------|
| T1 (Not Null, Not Null)  | (wild_color, yellow)       | (wild_color, yellow)        | True            |
| T2 (Not Null, Null) | (wild_color, yellow)       | Null        | False           |
| T3 (Null, Not Null) | Null       | (wild_color, yellow)        | False           |

  - **Functionality-Based**: Confirming that the first and second wild card should not be equal if they are the same type but different color.
  - Prefix
    - First card is wild card
    - Second card is wild card
  - Develop characteristics
    - C1 = First and second cards have same color
  - Partition characteristics

| Characteristics               | b1        | b2            |
|-------------------------------|-----------|---------------|
| C1 = First and second cards have same color   | True | False |

  - Identify possible values:

| Characteristics               | b1        | b2        |
|-------------------------------|-----------|-----------|
| C1 = First and second cards have same color   | (red, red) | (red, blue) |

  - Approaches Used: BCC
    - Base Choice:
      - T1 (True)
    - Test requirements:
      - T2 (False)
  - Derive test values and expected values.

| Test         | Card color  | Expected Result |
|-------------|-------------|-----------------|
| T1 (True)  | (red, red)  | True            |
| T2 (False)  | (red, blue)  | False           |

📝Test Scenarios
  - Ensure that wildcards of the same color are equivalent.
      ```java
      assertTrue(wildCard1.equals(wildCard2));
      ```
  - Validate that wild cards of different colors are not equivalent, and wild cards are not equal to cards with different types.
      ```java     
      assertFalse(wildCard1.equals(wildCard2));
      ```
      
---
## 5. Test Case Name: `TestEqualWildDrawFourCard`

- **Goal**: Ensure that two `WildDrawFourCard` objects with different colors are not considered equal.

- **Testable Functions**: `equals(Object obj)` (overridden in the `WildCard` or `WildDrawFourCard` class)

  - **Input Domain Modeling**:
    - Two `WildDrawFourCard` objects, each with a different color (e.g., red and blue).

  - **Parameters**:
    - `wildDrawFourCard1`: The first `WildDrawFourCard` object with a specific color. The method used to compare `wildDrawFourCard1` with another card is `equals(Object obj)` in the `WildDrawFourCard` class or inherited from the `WildCard` class.
    - `wildDrawFourCard2`: The second `WildDrawFourCard` object with a different color. The method used to compare `wildDrawFourCard2` with another card is `equals(Object obj)` in the `WildDrawFourCard` class or inherited from the `WildCard` class.

  - **Return Types**: 
    - **boolean**: The result of comparing the two `WildDrawFourCard` objects using the `equals()` method.

  - **Return Values**:
    - **Expected**: The two `WildDrawFourCard` objects should not be considered equal if they have different colors. The `equals()` method should compare both card type (`WILD_DRAW_FOUR`) and color.
    - **Unexpected**: The two `WildDrawFourCard` objects are considered equal even if they have different colors.

  - **Exceptional Behavior**:
    - Null card: In case one of them is a null card, the method should return False.

  - **Characteristics**:
    - **Interface-Based**: Verifying if the wild draw four cards are valid.
      -Prefix
        - First card is wild-four card
        - Second card is wild-four card
      - Develop characteristics**:
        - C1 = First card
        - C2 = Second card
      - Partition characteristics**:

| Characteristics               | b1   | b2   |
|-------------------------------|------|------|
| C1 = First card   | Not Null  | Null |
| C2 = Second card  | Not Null  | Null |

  - Identify possible values:

| Characteristics               | b1            | b2            |
|-------------------------------|---------------|---------------|
| C1 = First card | wild_draw_four | Null |
| C1 = Second card  | wild_draw_four | Null |

  - Approaches Used: BCC
    - Base Choice:
      - T1 (Not Null, Not Null)
    - Test requirements:
      - T2 (Not Null, Null)
      - T3 (Null, Not Null)
    - Derive test values and expected values.

| Test            | First Card Type  | Second Card Type  | Expected Result |
|-----------------|------------------|------------------|-----------------|
| T1 (Not Null, Not Null)  | wild_draw_four    | wild_draw_four     | True            |
| T2 (Not Null, Null) | wild_draw_four    | Null             | False           |
| T3 (Null, Not Null) | Null             | wild_draw_four     | False           |

  - **Functionality-Based**: Confirming that the first and second wild-four card should not be equal if they are the same type but different color.
    - Prefix
      - First card is wild-four card
      - Second card is wild-four card
    - Develop characteristics**:
      - C1 = First and second card have same color
    - **Partition characteristics**

| Characteristics               | b1            | b2            |
|-------------------------------|---------------|---------------|
| C1 = First and second card have same color   | True  | False |

  - Identify possible values:

| Characteristics               | b1            | b2            |
|-------------------------------|---------------|---------------|
| C1 = First and second card have same color   | (wild-four, red), (wild-four, red)  | (wild-four, red), (wild-four, blue) |

  - Approaches Used: BCC
    - Base Choice:
      - T1 (True)
    - Test requirements:
      - T2 (False)
    - Derive test values and expected values.

| Test            | Card color      | Expected Result |
|-----------------|-----------------|-----------------|
| T1 (True) | wild_draw_four | (wild-four, red), (wild-four, red) | True            |
| T2 (False)           | (wild-four, red), (wild-four, blue) | False           |

📝Test Scenarios
  - Ensure that wild draw four cards of the same color are equivalent.
    ```java
    assertTrue(wildDrawFourCard1.equals(wildDrawFourCard2));
    ```
  - Validate that wild draw four cards of different colors are not equivalent
    ```java
    assertFalse(wildDrawFourCard1.equals(wildDrawFourCard2));
    ```

---
## 6. Test Case Name: `TestbuildPlayers`

- **Goal**: Ensure that when building a game, the game should contain a number of players between 2-10 players, throwing IllegalStateException when there is invalid number of players, and all players should receive cards in their hand.

- **Testable Functions**: `buildPlayer()` (from `Game` class)

  - **Input Domain Modeling**:
    - The  numbers of players are in the range 2-10 players.
    - The numbers of players are not in the range 2-10 players.
    - A non-empty draw pile is prepared.
    - An empty draw pile is prepared.


  - **Parameters**:
    - `playerNames`: An ArrayList of player’s name to be added into the game.

  - **Return Types**:
    - `PlayerRoundIterator(players)`: The list of players in the game, which should have the number of players between 2-10 players.

  - **Return Values**:
    - **Expected**: The method should return a list of players with their cards in hand, and the PlayerRoundIterator contains the number of players between 2-10 players correctly.
    - **Unexpected**: The method can return a list of players with their cards in hand, but the game contains the number of players less than 2 or more than 10 players, or the game is created without a draw pile.
  - **Exceptional Behavior**:
    - Players have the same name as others: The method should still be able to return a  list of players PlayerRoundIterator(players) without any issue even though some players have the same name.

  - **Characteristics**:
    - **Interface-Based**: Verifying the draw pile, to ensure that the game should be created with a draw pile.
      - Develop characteristics:
        - C1 = draw pile
      - Partition characteristics:

| Characteristics               | b1   | b2   |
|-------------------------------|------|------|
| C1 = draw pile   | Not Empty  | Empty |

  - Identify possible value:

| Characteristics               | b1            | b2            |
|-------------------------------|---------------|---------------|
| C1 = draw pile   | DrawPile(cards)  | DrawPile(null)          |

  - Approaches Used: MBCC
    - Base Choice:
      - T1 (Not empty)
      - T2 (Empty)
    - Test requirements:
      - -
    - Derive test values and expected values.

| Test            | Card Type      | Expected Result |
|-----------------|----------------|-----------------|
| T1 (Not empty)       | DrawPile(cards) | Player List  |
| T2 (Empty)      | DrawPile(null)           | Thrown error: NullPointerException           |

  - **Functionality-Based**: Confirming that the players are successfully built with the valid number of players.
    - Develop characteristics:
      - C1 = Number of player
      - C2 = Draw pile
    - Partition characteristics

| Characteristics               | b1     | b2    | b3      |
|-------------------------------|--------|-------|---------|
| C1 = Number of player          | (-inf, 2) | \[2,10]  | (10, inf) |
| C2 = Draw pile   | Not Empty  | Empty |  |

  - Identify possible values:

| Characteristics               | b1     | b2    | b3      |
|-------------------------------|--------|-------|---------|
| C1 = Number of player          | 1 | 2  | 11 |
| C2 = Draw pile   | DrawPile(cards)  | DrawPile(null)          |

  - Approaches Used: MBCC
    - Base Choice:
      - T1 (<2, Empty)
      - T2 (2-10, Not Empty)
  - Test requirements:
    - T3 (>10, Empty)
    - T4 (>10, Not Empty)
  - Derive test values and expected values.

| Test                         | Number of players      | Draw pile | Expected Result |
|------------------------------|-----------------|-------------|-----------------|
| T1 (<2, Not Empty)      | 1  | DrawPile(null)         | Thrown error: IllegalStateException |
| T2 (2-10, Empty)    | 2  | DrawPile(cards)       | Player list            |
| T3 (>10, Not Empty)     | 11  | DrawPile(null)        | Thrown error: IllegalStateException            |
| T4 (>10, Empty)    | 11  | DrawPile(cards)      | Thrown error: IllegalStateException            |

📝Test Scenarios
  - Ensure That the number of players is between 2 - 10 players, and Each player has their Card.
      ```java
        assertDoesNotThrow(gameBuilder::build);
        assertNotNull(game.getCurrentPlayer().getHandCards());
        game.drawCard(game.getCurrentPlayer().getId());
        assertNotNull(game.getCurrentPlayer().getHandCards());
        assertEquals(Expect,Result);
      ```
  - Validate that the number of players should be between 2 - 10 players.
      ```java
      assertThrows(IllegalStateException.class, gameBuilder::build);
      ```
---
## 7. Test Case Name: `TestDrawCard_Game`

- **Goal**: Ensure that when a player has a playable card but chooses to draw instead, the game continues without issues. Specifically, verify that the player’s hand increases and the turn passes to the next player.

- **Testable Functions**: `drawCard(UUID playerId)` (from the `Game` class)

  - **Input Domain Modeling**:
    - The player has a playable card but chooses to draw a card instead of playing the card.
    - The game should handle this correctly and continue, with the player's hand increasing by one card and passing the turn to the next player.

  - **Parameters**:
    - `playerId`: The ID of the current player. This ID is used to reference the player in the game and is typically retrieved from the `Player` object.
      - Method: `Player.getId()`
    - `gameState`: The overall state of the game, which includes multiple components:
      - **Hands**: Refers to the `HandCardList`, which manages the cards currently held by each player.
        - Method: `Player.getHandCards()` returns the `HandCardList` for that player.
      - **Current turn player**: This refers to the current order of play and which player's turn it is. It’s managed by a `PlayerRoundIterator`, which cycles through the players to determine whose turn it is.
        - Method: `PlayerRoundIterator.getCurrentPlayer()` gives the player whose turn it currently is.

  - **Return Types**:
    - **boolean**: The method should return `true` if the player's hand increases by one card after drawing, ensuring the game state is updated correctly.

  - **Return Values**:
    - **Expected**: After the player draws a card, the number of cards in their hand increases by one, and the turn passes to the next player.
    - **Unexpected**: The hand size doesn't change, or the turn doesn't pass to the next player (i.e. the same player can still play the card).

  - **Exceptional Behavior**:
    - **N/A**: No exceptions are expected in this scenario under normal gameplay conditions.

  - **Characteristics**:
    - **Interface-Based**: Verifying the inputs of the game state are valid, a player can draw correct number of cards.
      - Develop characteristics
        - C1 = Current Player (playerId)
        - C2 = Number of Cards to be Drawn

      - Partition characteristics

| Characteristics               | b1   | b2   |  |
|-------------------------------|------|------|---|
| C1 = Current Player   | Not Null | Null | |
| C2 = Number of Cards to be Drawn       | <0 | 0 | >0 |

  - Identify possible values:

| Characteristics               | b1   | b2   |  |
|-------------------------------|------|------|---|
| C1 = Current Player   | 1 | Null | |
| C2 = Number of Cards to be Drawn       | -1 | 0 | 1 |

  - Approaches Used: PWC
    - Test requirements:
      - T1 (Not Null, <0)
      - T2 (Not Null, 0)
      - T3 (Not Null >0)
      - T4 (Null, <0)
      - T5 (Null, 0)
      - T6 (Null, >0)

  - Derive test values and expected values.

| Test                         | Current Player (PlayerId) | Number of Cards to be Drawn               | Expected Result |
|------------------------------|-------------------------|-----------------------------------|----------------|
| T1 (Not Null, <0)         | 0                       | -1           | 0       |
| T2 (Not Null, 0)      | 0                      | 0                              | 0           |
| T3 (Not Null >0)      | 0                      | 1                              | 1           |
| T4 (Null, <0)      | Null                      | -1                              | 0           |
| T5 (Null, 0)      | Null                      | 0                              | 0           |
| T6 (Null, >0)      | Null                      | 1                              | 1           |

  - **Functionality-Based**: Confirming that the current player can draw a card even though they have a playable card (i.e., the player's hand is updated correctly after drawing).
    - Develop characteristics
      - C1 = A valid card is drawn from the draw pile
      - C2 = handCardList
  - Partition characteristics

| Characteristics               | b1   | b2   |
|-------------------------------|------|------|
| C1 = A valid card is drawn from the draw pile | True  | False |
| C2 = handCardList | Updated card list  | Not updated card list |

  - Identify possible values:

| Characteristics               | b1   | b2   |
|-------------------------------|------|------|
| C1 = A valid card is drawn from the draw pile | (1, red)  | (wild_color, red) |
| C2 = handCardList | Card List == Card List + (1, red)  | Card List == Card List |

  - Approaches Used: PWC
    - Test requirements:
      - T1 (True, Updated card list)
      - T2 (True, Not updated card list)
      - T3 (False, Updated card list)
      - T4 (False, Not updated card list)

    - Derive test values and expected values.

| Test                                                                                                                                                                                                         | A card is drawn from draw pile | Updated card list in hand | Expected Result |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------|-------------------------------|---------------------------|
| T1 (True, Updated card list)                                                                                                                                                                                 | (1, red)                             | Card List == Card List + (1, red)   | True |
| T2 (True, Not updated card list) Remarks: Non-deterministic, unable to create test. The Method drawCards add drawn card to player hand in itself method                                                      | (1, red)      | Card List == Card List   | False     |
| T3 (False, Updated card list) Remarks: Non-deterministic, unable to create test. The Method tryToPlayDrawnCard will play the wild card with matched color with the top card on discard pile by itself method | (wild_color, red)                             | Card List == Card List +  (wild_color, red) | True |
| T4 (False, Not updated card list)                                                                                                                                                                            | (wild_color, red) | Card List == Card List   | False |


📝Test Scenarios
  - Ensure that game state is the next player after played and total card is 3, player hand + drawn card
    ```java
    assertEquals(game.getCurrentPlayer().getName(),players[0].getName());
    game.drawCard(players[0].getId());
    
    assertGameState(game,topCard,"1");
    assertEquals(1,players[1].getHandCards().count());
    ```
    
  - Validate that game state is not the next player after and total card is not equal to 1 (player hand)
    ```java
    assertNotEquals(game.getCurrentPlayer().getName(),players[1].getName());
    game.drawCard(players[1].getId());
    
    assertGameState(game,topCard,"1");
    assertEquals(1,players[1].getHandCards().count());
    ```

---
## 8. Test Case Name: `TestAddCard`

- **Goal**: Ensure that when adding a new card into hand, the method should add the correct card.

- **Testable Functions**: 
  - `addCard(Card newCard)` (from `HandCardList` class)

  - **Input Domain Modeling**:
    - Target card to be added into HandCardList is not null.
    - Target card to be added into HandCardList is null.
    - HandCardList is an empty list.
    - HandCardList is not an empty list.

  - **Parameters**:
    - `Card`: Referring to the target card to be added into `HandCardList`.

  - **Return Types**:
    - **void**: The method does not return the value.

  - **Return Values**:
    - Expected: A correct card should be added to HandCardList.
    - Unexpected: An incorrect card is added into HandCardList, or  HandCardList changes although the target card is null.

  - **Exceptional Behavior**:
    - No exceptional behavior in AddCard method.

  - **Characteristics**:
    - **Interface-Based**: Verify the target card and current handCardList list.
      - Develop characteristics:
        - C1  = Target card to add
        - C2  = handCardList
      - Partition characteristics:

| Characteristics               | b1   | b2   |
|-------------------------------|------|------|
| C1 = Target card to add        | Not Null  | Null |
| C2 = handCardList  | Non-empty Card List  | Empty Card List |

  - Identify possible values:

| Characteristics               | b1         | b2          |
|-------------------------------|------------|-------------|
| C1  = Target card to add        | (2, red)   | Null          |
| C2 = handCardList  | (1, red), (2, green), (3, blue), (4, yellow)       | Empty |

- Approaches Used: ECC
  - Test requirements:
    - T1 (Not Null, Non-empty Card List)
    - T2 (Null, Empty Card List)

  - Derive test values and expected values.

| Test            | Target Card | Card List in hand | Expected Result               |
|-----------------|----------------------|--------------------|-------------------------------|
| T1 (Not Null, Non-empty Card List) | (2, red)             | (1, red), (2, green), (3, blue), (4, yellow)  | (1, red), (2, green), (3, blue), (4, yellow), (2, red)  |
| T2 (Null, Empty Card List) Remark: Impossible case, throw Null Pointer Exception | Null                  | Empty       | Empty     |

  - **Functionality-Based**: Confirming the target card is correctly added to the list.
    - Develop characteristics
      - C1 = Card to be added, checking whether the card is an invalid type which violate game rule (e.g. wild card with color) can still be added without any issue
    - Partition characteristics
  
| Characteristics               | b1        | b2            |
|-------------------------------|-----------|---------------|
| C1 = Card to add | Valid Card | Invalid Card |

  - Identify possible values:

| Characteristics               | b1        | b2            |
|-------------------------------|-----------|---------------|
| C1 = Target card to add | (2, red) | (wild_color, red) |

  - Approaches Used: ECC
    - Test requirements:
      - T1 (Valid Card)
      - T2 (Invalid Card)

    - Derive test values and expected values.

| Test              | Target card to add       | Expected Result    |
|-------------------|-----------------|--------------------|
| T1 (Valid Card)| (2, red)        | handCardList + (2, red)         |
| T2 (Invalid Card) | (wild_color, red) | HandCardList + (wild_color, red)  |

📝Test Scenarios
  - Ensuring that target card is added into handCardList correctly.
    ```java
    // card to be added is valid type
    var cardInHand = CardTestFactory.createNumberCard(4,CardColor.BLUE);
    var TestCard = CardTestFactory.createNumberCard(4,CardColor.RED);
    
    assertEquals(Hand[0],cardInHand);
    assertEquals(Hand[1],TestCard);
    ```
     
---
## 9. Test Case Name: `TestFindCardOfType`

- **Goal**: Ensure that the findCardOfType() method correctly identifies and returns a card of the specified CardType from the hand of cards (handCards). If no card of the specified type exists, it should return null.

- **Testable Functions**: 
  - `findCardOfType()` (`HandCardList` Class)

  - **Input Domain Modeling**:
    - handCardList contains different CardType values.
    - A target type is valid card type
    - A target type is null

  - **Parameters**:
    - Type: The CardType that is searching for in the hand of cards.
    - handCardsList: The list of cards

  - **Return Types**:
    - Card: Returns the first card that matches the specified type from the handCards, or null if no match is found.

  - **Return Values**:
    - Expected:  When a card of the specified type exists in handCards, it returns the card. If it is not, it returns null.
    - Unexpected: The wrong card is returned (a card does not match the requested CardType).

  - **Exceptional Behavior**:
    - Empty card : If the list of cards (handCards) is empty, the method should return null. Invalid card type (eg. null) should return null.

  - **Characteristics**:
    - **Interface-Based**: Verify card return in the hand of cards.
      - Develop characteristics
        - C1 = Card type that want to find
        - 
      - **Partition characteristics**:
     
| Characteristics               | b1            | b2              |
|-------------------------------|---------------|-----------------|
| C1 = Card type that want to find   | Not Null    | Null     |

  - Identify possible values:

| Characteristics               | b1            | b2           |
|-------------------------------|---------------|--------------|
| C1 = Card type that want to find   | (1, Green)    | Null |

  - Approaches Used: PWC
    - Test requirements:
      - T1 (Not Null)
      - T2 (Null)

    - Derive test values and expected values.

| Test                            | Card Type    | Expected Result |
|---------------------------------|--------------|--------------|
| T1 (Not Null) | (1, Green)   | Matched card (Not null)    |
| T2 (Null) | Null | Null |

- **Functionality-Based**: Verify the correct card return based on its type.
  - Develop characteristics:
    - C1 = Type of target card
    - C2 = Cards in handCardsList

  - Partition characteristics:

| Characteristics               | b1                        | b2                        |
|-------------------------------|---------------------------|---------------------------|
| C1 = Card type         | Not Null                      | Null                      |
| C2 = Cards in handCardsList     | Non empty list                      | Empty                      |

  - Identify possible values:

| Characteristics               | b1                        | b2                        |
|-------------------------------|---------------------------|---------------------------|
| C1 = Card type        | (1, Green)            | Null        |
| C2 = Cards in handCardsList     | {(1, Green), (skip, red), (wild_color, no color)} | Empty |

  - Approaches Used: PWC
    - Test requirements:
      - T1 (Not Null, Not empty card)
      - T2 (Not Null, Empty card)
      - T3 (Null, Not empty card)
      - T4  (Null, Empty card)

    - Derive test values and expected values.

| Test                             | Card Type    | handCardsList       |  Expected Result |
|----------------------------------|-----------------------|-----------------------------|-------------------------|
| T1 (Not Null, Not empty card) | (1, Green)       | {(1, Green), (skip, red), (wild_color, no color)}         |  (1, Green)    |
| T2 (Not Null, Empty card) | (reverse ,blue)   | Empty | Null |
| T3 (Null, Not empty card)   | Null        | {(2, Yellow), (reverse ,blue), (wild_color, no color)}         |Null             |
| T4 (Null,  Empty card)   | Null        | Empty         |Null             |

📝Test Scenarios
  - Ensure that the method return thr correct type.
    ```java
    assertEquals(Type, result.getType(), "Card type should match");
    assertNotNull(result, "Card should be found");
    ```
  - Validate that the method return null when no type macthed.
    ```java
    assertNull(result, "Card should not be found");
    ```
     
---
## 10. Test Case Name: `TestValidateNumber`

- **Goal**: Ensure that the `validateNumber()` method correctly throws an exception if a card's number is outside the valid range (0-9), and accepts numbers within the valid range.

- **Testable Functions**: 
  - `validateNumber()` (Class: `CardUtil`)

  - **Input Domain Modeling**:
    - Card numbers are in the range 0-9.
    - Card numbers are outside the range 0-9.

  - **Parameters**:
    - **number**: The card number to validate. This is an integer that is checked to ensure it is within the valid range (0-9).

  - **Return Types**:
    - **void**: The method does not return a value but throws an exception if the number is invalid.

  - **Return Values**:
    - Expected: The test should pass when the number is within the valid range (0-9), and no exception is thrown. An exception is thrown  an IllegalArgumentException for the number that is outside this range.
    - Unexpected: There is a card that contains an invalid number (i.e. card with number outside the range 0-9), without throwing any exception.

  - **Exceptional Behavior**:
    - Invalid card type: An invalid card type is created, such as number is specified in other card types except from NUMBER type (e.g. number is specified in action card). In this case, the game should throw an exception for an invalid card type.

  - **Characteristics**:
    - **Interface-Based**: Verify the created number of card.
      - Develop characteristics
        - C1 = Card number
      - Partition characteristics
     
| Characteristics               | b1    | b2    |
|-------------------------------|-------|-------|
| C1 = Card number                  | Not Null  | Null |    

  - Identify possible values:

| Characteristics               | b1            | b2               |
|-------------------------------|---------------|------------------|
| C1 = Card number  | (1, red) | Null |

  - Approaches Used: ACoC
    - Test requirements:
      - T1 (Not Null)
      - T2 (Null)
    - Derive test values and expected values.

| Test            | Card | Expected Result |
|-----------------|--------------------|-----------------|
| T1 (Not Null)       | (1, red)      | Does not throw exception (Card is successfully created)            |
| T2 (Null)      | Null  | Thrown error: IllegalArgumentException           |

  - **Functionality-Based**: Check the created card number.
    - Develop characteristics:
      - C1 = Card number
    - Partition characteristics

| Characteristics               | b1    | b2    |  b3    |
|-------------------------------|-------|-------|-----|
| C1 = Card number  | (-inf, 0)  | \[0,9] |  (9, inf)  |

  - Identify possible values:

| Characteristics               | b1    | b2    |  b3    |
|-------------------------------|-------|-------|-----|
| C1 = Card number  | -1  | 0 |  10  |

  - Approaches Used: ACoC
    - Test requirements:
      - T1 ((-inf, 0))
      - T2 (\[0,9])
      - T3 ((9, inf))
    - Derive test values and expected values.

| Test            | Card number  | Expected Result         |
|-----------------|-----------|---------------------------|
| T1 (-inf, 0)    | -1  | Thrown error: IllegalArgumentException       |
| T2 \[0,9]   | 0 | Does not throw exception |
|  T3 ((9, inf))    | 10  | Thrown error: IllegalArgumentException       |

📝Test Scenarios
  - Ensure that the method do not throw exception when the card number is valid number (0-9).
    ```java
    assertDoesNotThrow(() -> CardTestFactory.createNumberCard(1, CardColor.RED));
    ```
  - Validate that the methos throw exception when the card number is invalid number
    ```java
    assertThrows(IllegalArgumentException.class, () -> CardTestFactory.createNumberCard(-1, CardColor.BLUE));
    ```
---

## Conclusion

This set of test cases ensures comprehensive coverage of various gameplay mechanics in the Uno game, from basic card interactions to more complex scenarios like handling empty draw piles and special cards. Proper validation of these test cases will help to maintain a smooth and bug-free experience for players.

---
## 📊 Test Results


---

## 📄 License
```plaintext
Copyright (C) 2024 (Ramita Deeprom (Tingting), Sushawapak Kancharoendee (Bee), Poramet Kaewpradub (Copter), 
Burit Sihabut (Best), Thanat Phichitphanphong (Pooh), Piangfa Boonkaew (Fah)) - All Rights Reserved
You may use, distribute, and modify this code under the terms of the MIT license. 
```
---
