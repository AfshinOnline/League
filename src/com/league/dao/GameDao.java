
package com.league.dao;


import java.util.HashMap;

/**
 * Interface class for file action (Dao).
 * 
 * @author Afshin Ghaziasgar Jan 2015
 */
public interface GameDao {
    
     public String readInput(String file);
     public String writeFile(String file);
     public HashMap<String, Integer> addToList(String club_A_Name, String club_B_Name);
     public void checkValidity(String clubA, String clubB);
     public String getFileAbsolutePath(String filename);
     
}
