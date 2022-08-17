/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.gamebullscows.models;

/**
 *
 * @author elizabeth
 */
public class MiniGame {
    private String GameId;
    private String answer;
    private boolean finished;

    public String getGameId() {
        return GameId;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setGameId(String GameId) {
        this.GameId = GameId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    
}
