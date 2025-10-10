package re.forestier.edu;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("test - notnull healthpoints")
    void testPlayerHealth(){
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.healthpoints, is(notNullValue()));
        
    }

    @Test
    @DisplayName("test - players level init")
    void testPlayerLevelInit() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.retrieveLevel(), is(1));
    }
    
    @Test
    @DisplayName("test - correct player xp retrieval")
    void testXpRetrieval(){
        player player1 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player1, 1);
        assertEquals(player1.getXp(),1);
    }

    

    @Test
    @DisplayName("test - players levels")
    void testPlayerLevels(){
        player player1 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player1, 10);
        assertThat(player1.retrieveLevel(), is(2));
        player player2 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player2, 27);
        assertThat(player2.retrieveLevel(), is(3));
        player player3 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player3, 57);
        assertThat(player3.retrieveLevel(), is(4));
        player player4 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(player4, 111);
        assertThat(player4.retrieveLevel(), is(5));                            
    }

    @Test
    @DisplayName("test - level up boolean value - false")
    void testPlayerNoAbnormalLevelUp(){
        player player1 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        Boolean b = UpdatePlayer.addXp(player1, 0);
        assertThat(b, is(false));
    }

    @Test
    @DisplayName("test - level up boolean value - false")
    void testPlayerNormalLevelUp(){
        player player1 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        Boolean b = UpdatePlayer.addXp(player1, 10);
        assertThat(b, is(true));
    }



    @Test
    @DisplayName("test - player money removal is done correctly - partial")
    public void testRemoveMoneyValid() {
        player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
        p.removeMoney(50);
        assertEquals(50, p.money.intValue());
    }

    @Test
    @DisplayName("test - removing the correct amount")
    public void testRemoveMoneyExactAmount() {
        player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
        p.removeMoney(100);
        assertEquals(0, p.money.intValue());
    }

    @Test
    @DisplayName("test - removing more than the balance")
    public void testRemoveMoneyMoreAmount() {
        player p = new player("Test", "Avatar", "ARCHER", 100, new ArrayList<>());
        try {
            p.removeMoney(101);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }




   /*@Test
    @DisplayName("test - adding negative money to the current balance")
    void testMoneyAdd() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        try {
            p.addMoney(-200);
        } catch(IllegalArgumentException e) {
            return;
        }
        fail();
    }*/

    /*@Test
    @DisplayName("test - adding negative xp ")
    void testAddingNegativeXp(){
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        try {
            UpdatePlayer.addXp(p,-1);
        } catch(IllegalArgumentException e) {
            return;
        }
        fail();
    } */

    @Test
    @DisplayName("test - end of round - No Health")
    void testEndOfRoundSequencePlayerisDead(){
        player p1 = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p1.currenthealthpoints = 0;
        p1.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p1);
        assertEquals(p1.currenthealthpoints, 0);
    }

    @Test
    @DisplayName("test - end of round - Dwarf - No Items")
    void testEndOfRoundSequencePlayerisDwarfWithNoItems(){
        player p1 = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p1.currenthealthpoints = 2;
        p1.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p1);
        assertEquals(p1.currenthealthpoints, 3);
    }
    
    @Test
    @DisplayName("test - end of round - Dwarf - Noly Elixir")
    void testEndOfRoundSequencePlayerisDwarfWithHolyElixir(){
        player p1 = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p1.currenthealthpoints = 2;
        p1.healthpoints = 10;
        p1.inventory.add("Holy Elixir");
        UpdatePlayer.majFinDeTour(p1);
        assertEquals(p1.currenthealthpoints, 4);
    }

    @Test
    @DisplayName("test - end of round - Aventurer - No Items")
    void testEndOfRoundSequencePlayerisAdventurerWithNoItems(){
        player p2 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p2.currenthealthpoints = 2;
        p2.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p2);
        assertEquals(p2.currenthealthpoints, 3); 
    }

    @Test
    @DisplayName("test - end of round - Archer - No Item88s")
    void testEndOfRoundSequencePlayerisArcherWithNoItems(){        
        player p3 = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        p3.currenthealthpoints = 2;
        p3.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p3);
        assertEquals(p3.currenthealthpoints,3);
    }

    @Test
    @DisplayName("test - end of round - Archer - Magic Bow")
    void testEndOfRoundSequencePlayerisArcherWithMagicBow(){        
        player p3 = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        p3.currenthealthpoints = 2;
        p3.healthpoints = 16;
        p3.inventory.add("Magic Bow");
        UpdatePlayer.majFinDeTour(p3);
        assertEquals(p3.currenthealthpoints, 2);
    }

    @Test
    @DisplayName("test - end of round - Aventurer - Full Health")
    void testEndOfRoundSequencePlayerisAdventurerWithFullHealth(){
        player p2 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p2.currenthealthpoints = 10;
        p2.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p2);
        assertEquals(p2.currenthealthpoints, 10);
    }

    @Test
    @DisplayName("test - end of round - Aventurer - Half Health")
    void testEndOfRoundSequencePlayerisAdventurerWithHalfHealth(){
        player p2 = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p2.currenthealthpoints = 4; 
        p2.healthpoints = 10;
        UpdatePlayer.majFinDeTour(p2);
        assertEquals(p2.currenthealthpoints, 5); 
    }

}
