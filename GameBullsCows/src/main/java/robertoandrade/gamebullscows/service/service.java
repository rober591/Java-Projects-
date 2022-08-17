/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.gamebullscows.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;
import robertoandrade.gamebullscows.data.DAO;
import robertoandrade.gamebullscows.models.Game;
import robertoandrade.gamebullscows.models.Round;
import robertoandrade.gamebullscows.models.MiniGame;



/**
 *
 * @author elizabeth
 */
@Service
public class service {
    private final DAO dao;

    public service(DAO dao) {
        this.dao = dao;
    }
    
    
    public List<MiniGame> all(){
        List<Game> games = dao.getAll();
        List<MiniGame> miniGames = new ArrayList<MiniGame>();
        List<String> FinishedGames = new ArrayList<String>();

        
         for(Game play : games){
             if(play.isFinished()){
                 FinishedGames.add(play.getGameId());
             }
             
         }

        for(Game play : games){  
         if(play.getGuess() == null){
             MiniGame temp = new MiniGame();
             temp.setGameId(play.getGameId());
             if(FinishedGames.contains(play.getGameId())){
                 temp.setFinished(true);
                 temp.setAnswer(play.getAnswer());
             }else{
                 temp.setAnswer("****");
                 temp.setFinished(false);
             }
             
             miniGames.add(temp);
         }
         }
        return miniGames;
    }
    
    public Round add(Game play){
        Game temp =  dao.add(play);
        Round thisRound = new Round();
        thisRound.setCreatedAt(play.getCreatedAt());
        thisRound.setGuess(play.getGuess());
        thisRound.setEm(play.getEm());
        thisRound.setPm(play.getPm());
        return thisRound;
    }
    
    public String addGame(Game play){
        String number = getAnswer();
       play.setAnswer(number); 
       play.setGameId(Integer.toString(getGameID(Integer.parseInt(number)))); 
      
       
        return dao.addGame(play);
    }
    
    
    class sortItems implements Comparator<Game> {
 
    // Method of this class
    // @Override
    public int compare(Game a, Game b)
    {
 
        // Returning the value after comparing the objects
        // this will sort the data in Ascending order
        return a.getCreatedAt().compareTo(b.getCreatedAt());
    }
}
    
    public List<Round> findRoundsByiD(int id){
        String Gameid = String.valueOf(id);
        List<Game> games = dao.getAll();
        List<Round> list=new ArrayList<Round>();
        for(Game play : games){  
         if(play.getGameId().equalsIgnoreCase(Gameid) && play.getGuess() != null ){
            Round newRound = new Round();
            newRound.setCreatedAt(play.getCreatedAt());
            newRound.setGuess(play.getGuess());
            newRound.setEm(play.getEm());
            newRound.setPm(play.getPm());
            list.add(newRound);
         }
         }
       
        return list;
    }
    
    
    
    
    public MiniGame findGameById(int id){
        MiniGame temp = null;
        String Gameid = String.valueOf(id);
       List<Game> games = dao.getAll();
        for(Game play : games){  
         if(play.getGameId().equalsIgnoreCase(Gameid)){
              temp = new MiniGame();
             temp.setGameId(play.getGameId());
             temp.setFinished(play.isFinished());
             if(!play.isFinished()){
                 temp.setAnswer("****");
             }else{
                 temp.setAnswer(play.getAnswer());
             }
             
            
         }
         }
         return temp;
        //return null;
        
    }
    
    public boolean update(Game play){
        return dao.update(play);
    }
    
    
    public boolean deleteById(int id){
         String ID = String.valueOf(id);
        return dao.deleteById(ID);
    }
    
    public String getAnswer(){
        String number = "";
    int answer[] = {-1 , -1 , -1 , -1};
    int num;
    int index = 1;
    answer[0] = (int) ((Math.random() * (9 - 0)) + 0);
    while( answer[1] == -1 || answer[2] == -1 || answer[3] == -1){
        num = (int) ((Math.random() * (9 - 0)) + 0);
        if(num != answer[0] && num != answer[1] && num != answer[2] && num != answer[3]){
            answer[index] = num ;
            index ++;
        }
    }
    number = Integer.toString(answer[0])+Integer.toString(answer[1])+Integer.toString(answer[2])+Integer.toString(answer[3]);
    return number;
    }
    
    public int getGameID(int num){
        int id = num * 5 / 2 ;
        Random random = new Random();
        int value = random.nextInt(9 + 0) + 0;
        if(value > 5){
           id = id +  value ;
        }else{
           id = id -  value ;
            
        }
        return id;
    }
    
    /*
    class IdComparator implement Comparator<Game> {
  
   @Override
   public int compare(Game u1, Game u2) {
      // compare ages
      return u1.getId() - u2.getId();
   }
  }
*/
    
}
