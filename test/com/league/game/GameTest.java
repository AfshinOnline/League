/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.league.game;

import com.league.impl.GameDaoImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user1
 */
public class GameTest {
    
    private Game game;
    private GameDaoImpl instance;
    
    
    public GameTest() {
        game = new Game();
        instance = new GameDaoImpl(game);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   

    /**
     * Test of returnWinner method, of class Game.
     */
    @Test
    public void testReturnWinner() {
        System.out.println("returnWinner");
        Game game = new Game();
        game.setClub_A_Name("ClubA");
        game.setClub_B_Name("ClubB");
        game.setScoreTeamA(2);
        game.setScoreTeamB(1);
        
        int expResult = 1;
        int result = game.returnWinner();
        
        assertEquals(expResult, result);
    }
    
}
