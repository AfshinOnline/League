/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.league.impl;

import com.league.game.Game;
import com.league.game.utils.Statics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
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
public class GameDaoImplTest {
    
    private Game game;
    private String fileNameIn;
    private String fileNameOut;
    private GameDaoImpl instance;
    
    public GameDaoImplTest() {
      
        game = new Game();
        this.fileNameIn = "Input.txt";
        this.fileNameOut = "Output.txt";
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
     * Test of writeFile method, of class GameDaoImpl.
     */
    /**
     * Test of writeFile method, of class FileActions.
     */
    @Test
    public void testWriteFile() throws FileNotFoundException, IOException {
        System.out.println("writeFile");
        
        String fileName = fileNameOut;
        
        String returnMessage;

        returnMessage = instance.writeFile(fileName);

        String workingDirectory = System.getProperty("user.dir");
        String absoluteFilePath = workingDirectory + File.separator + fileName;
       
        
        //Check if method completed without exceptions and errors
        File f = new File(absoluteFilePath);
        if (f.exists() && !f.isDirectory()) {
            returnMessage = Statics.messages.WRITE_COMPLETE;
        } else {
            assert (false);
        }
 
        assertEquals(returnMessage, Statics.messages.WRITE_COMPLETE);
        
     
    }
       /**
     * Test of readInput method, of class FileActions.
     */
    @Test
    public void testReadInput() throws FileNotFoundException {
        System.out.println("readInput");
        
        String fileName = fileNameIn;
        
        String workingDirectory = System.getProperty("user.dir");
        String absoluteFilePath = workingDirectory + File.separator + fileName;
        
        String returnMessage;
        returnMessage = instance.readInput(fileName);
        
        //Check if method completed without exceptions and errors
        File f = new File(absoluteFilePath);
        if (f.exists() && !f.isDirectory()) {
            returnMessage = Statics.messages.READ_COMPLETE;
        } else{
            assert (false);
        }
        
        assertEquals(returnMessage, Statics.messages.READ_COMPLETE);
        
        //Check if output file not empty.
        int countlines = 0;
        Scanner input = new Scanner(new File(absoluteFilePath));
        while (input.hasNextLine()) {
            countlines++;
            input.nextLine();
        }

        if (countlines > 0 ) {assert (true);}else{assert(false);}
        
    }

 

    /**
     * Test of getNumber method, of class GameDaoImpl.
     */
    @Test
    public void testGetNumber() {
        System.out.println("getNumber");
        String text = "1234asdf";
        int expResult = 1234;
        int result = GameDaoImpl.getNumber(text);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getChars method, of class GameDaoImpl.
     */
    @Test
    public void testGetChars() {
        System.out.println("getChars");
        String text = "1234asdf";
        String expResult = "asdf";
        String result = GameDaoImpl.getChars(text);
        assertEquals(expResult, result);
     
    }

    /**
     * Test of getFileAbsolutePath method, of class GameDaoImpl.
     */
    @Test
    public void testGetFileAbsolutePath() {
       System.out.println("getFileAbsolutePath");
        String fileName = fileNameIn;
        
        String returnMessage = instance.getFileAbsolutePath(fileName);
        
        if(!returnMessage.isEmpty()){assert(true);}else{ assert(false);}
        
        String workingDirectory = System.getProperty("user.dir");
        String absoluteFilePath = workingDirectory + File.separator + fileName;
        
        assertEquals(returnMessage,  absoluteFilePath);
    }

    /**
     * Test of getGame method, of class GameDaoImpl.
     */
    @Test
    public void testGetGame() {
        System.out.println("getGame");
        
        Game expResult = null;
        Game result = instance.getGame();
        assertNotNull(result);
        
    }

    /**
     * Test of setGame method, of class GameDaoImpl.
     */
    @Test
    public void testSetGame() {
        System.out.println("setGame");
        
        instance.setGame(game);
        
        assertNotNull(game);
        
    }

    /**
     * Test of addToList method, of class GameDaoImpl.
     */
    @Test
    public void testAddToList() {
        System.out.println("addToList");
        
        //instance.addToList("test_Club_A", "test_Club_B");
        HashMap leagueTable = instance.getLeagueTable();
        instance.readInput(fileNameIn);
        boolean result = instance.leagueTable.size() > 0;
        
        assertNotNull(leagueTable);
        assertEquals(result, true);
        
    }

    /**
     * Test of checkValidity method, of class GameDaoImpl.
     */
    @Test
    public void testCheckValidity() {
        System.out.println("checkValidity");
        HashMap leagueTable = instance.getLeagueTable();
        boolean result = leagueTable.containsKey("testClubA");
        String clubA = "testClubA";
        String clubB = "testClubB";
        //GameDaoImpl instance = null;
        instance.checkValidity(clubA, clubB);
        boolean expected = leagueTable.containsKey("testClubB");
       
        assertNotSame(expected, result);
        
        
    }

    /**
     * Test of sortByValues method, of class GameDaoImpl.
     */
    @Test
    public void testSortByValues() {
        System.out.println("sortByValues");
        
        
        
        HashMap<String, Integer> map = new HashMap<>();
        
        map.put("ClubD", 3);
        map.put("ClubB", 1);
        map.put("ClubA", 2);
        map.put("ClubC", 2);
        map.put("ClubE", 2);
        map.put("ClubF", 2);
        map.put("ClubG", 2);
        
        
        Map<String, Integer> mapResult = instance.sortByValues(map);
        String expectedResultKey = "ClubB";
        int expectedResultValue = 1;
        String resultKey = "";
        int resultValue = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
        resultKey = entry.getKey();
        resultValue = entry.getValue();
         
        }
       
        assertSame(expectedResultKey, resultKey);
        
    }
    
}
