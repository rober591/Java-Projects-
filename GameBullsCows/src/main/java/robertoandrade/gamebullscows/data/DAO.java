/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package robertoandrade.gamebullscows.data;

import java.util.List;
import robertoandrade.gamebullscows.models.Game;

/**
 *
 * @author elizabeth
 */
public interface DAO {
    Game add(Game play);

    List<Game> getAll();
    
    String addGame(Game play) ;

    Game gamebyId(String id);

    // true if item exists and is updated
    boolean update(Game todo);

    // true if item exists and is deleted
    boolean deleteById(String id);
    
     String getAnswerbyId(String Gameid);
}
