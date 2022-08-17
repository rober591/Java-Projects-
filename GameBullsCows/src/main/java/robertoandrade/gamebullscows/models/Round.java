/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.gamebullscows.models;

/**
 *
 * @author elizabeth
 */
public class Round {
    
    private String createdAt;
    private String guess;
    private int em;
    private int pm;

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setEm(int em) {
        this.em = em;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getGuess() {
        return guess;
    }

    public int getEm() {
        return em;
    }

    public int getPm() {
        return pm;
    }
    
    
    
    
}
