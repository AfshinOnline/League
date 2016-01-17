package com.league.factory;


import com.league.game.Game;


/**
 * A class allowing production of Games (Factory).
 * @author Afshin Ghaziasgar Jan 2015
 */
public class GameFactory {
    
 
    public Game createGame() {
         return new Game();
    }
}
