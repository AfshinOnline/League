/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.league.game.interfaces;

/**
 * An interface defining methods to be implemented in Game.
 * @author Afshin Ghaziasgar Jan 2015
 */
public interface GameInterface {

    public int returnWinner();

    public int getScoreTeamA();

    public int getScoreTeamB();

    public void setScoreTeamA(int scoreTeamA);

    public void setScoreTeamB(int scoreTeamB);

    public String getClub_A_Name();

    public void setClub_A_Name(String club_A_Name);

    public String getClub_B_Name();

    public void setClub_B_Name(String club_B_Name);

}
