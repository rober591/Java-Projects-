/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.gamebullscows.models;

/**
 *
 * @author elizabeth

 * 
 */

/*
DROP DATABASE IF EXISTS GameBC;
CREATE DATABASE GameBC;

USE GameBC;

CREATE TABLE games(
id INT PRIMARY KEY AUTO_INCREMENT,
GameId VARCHAR(40),
answer VARCHAR(5) NOT NULL,
createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
guess VARCHAR(5),
finished BOOLEAN DEFAULT false,
em INT,
pm INT);


INSERT INTO games(GameId, answer, guess)
VALUES("12", "1234", "5678"),
("14", "2356", "9802");

*/
public class Game {
    private int id;
    private String GameId;
    private String answer;
    private String createdAt;
    private String guess;
    private boolean finished;
    private int em;
    private int pm;
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getGameId() {
        return GameId;
    }

    public String getAnswer() {
        return answer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getGuess() {
        return guess;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getEm() {
        return em;
    }

    public int getPm() {
        return pm;
    }

    public void setGameId(String GameId) {
        this.GameId = GameId;
    }

    public void setAnswer(String number) {
        this.answer = number;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setEm(int em) {
        this.em = em;
    }

    public void setPm(int pm) {
        this.pm = pm;
    }
    
    
    
    
    
    
}
