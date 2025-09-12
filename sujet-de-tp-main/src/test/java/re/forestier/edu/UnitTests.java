package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("test - players name")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("test - players avatar name")
    void testPlayerAvatarName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.Avatar_name, is("Grognak le barbare"));
    }

    @Test
    @DisplayName("test - players avatar class")
    void testPlayerAvatarClass() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.getAvatarClass(), is("ADVENTURER"));
    }

    @Test
    @DisplayName("test - players level init")
    void testPlayerLevelInit() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.level, is(1));
    }

    @Test
    @DisplayName("test - players levels")
    void testPlayerLevels(){
        player player1 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player1, 10);
        assertThat(player1.level, is(2));
        player player2 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player2, 27);
        assertThat(player2.level, is(3));
        player player3 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player3, 57);
        assertThat(player3.level, is(4));
        player player4 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player3, 111);
        assertThat(player4.level, is(5));                            
    }


    @Test
    @DisplayName("test - removing more than the current balance")
    void testMoneyRemovalPositive() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("test - removing a negative ammount to than the current balance")
    void testMoneyRemovalNegative() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(-200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("test - adding negative money to the current balance")
    void testMoneyAdd() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.addMoney(-200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

}
