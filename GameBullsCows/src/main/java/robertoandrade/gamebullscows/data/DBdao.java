/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.gamebullscows.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import robertoandrade.gamebullscows.models.Game;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    


/**
 *
 * @author elizabeth
 */

@Repository
@Profile("DB")
public class DBdao implements DAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBdao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String addGame(Game play) {

      final String sql = "INSERT INTO games(GameId, answer) VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, play.getGameId()) ;
            //statement.setString(2, play.getGuess());
            statement.setString(2, play.getAnswer());
            return statement;

        }, keyHolder);

        //play.setRoundId(keyHolder.getKey().intValue()); 

        //return play;
        return "GAMEID = "+play.getGameId();
    }
    
  
    
    @Override
    public Game add(Game play) {

        final String sql = "INSERT INTO games(GameId, answer  , guess,finished ,em ,pm ) VALUES(?,?,?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                sql, 
                Statement.RETURN_GENERATED_KEYS);
            
            String answer = getFinalAnswer(play.getGameId());
            statement.setString(1, play.getGameId()) ;
            statement.setString(2,answer);
            statement.setString(3, play.getGuess());
            int[] BC = checkGuess(play.getGuess(), answer);
            play.setPm(BC[1]);
            play.setEm(BC[0]);
            if(BC[0] == 4){
             play.setFinished(true);
             play.setAnswer("CONGRATS !!!  THE ASNWER IS: " +answer);
            }
            statement.setBoolean(4, play.isFinished());
            statement.setInt(5, play.getEm()) ;
            statement.setInt(6, play.getPm()) ;
            
            return statement;

        }, keyHolder);

        //play.setRoundId(keyHolder.getKey().intValue()); 
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        play.setCreatedAt(dtf.format(now));

        return play;
        
    }

    @Override
    public List<Game> getAll() {
         final String sql = "SELECT id,createdAt, GameId, answer, guess,finished ,em,pm FROM games;";
        return jdbcTemplate.query(sql, new ToDoMapper());
            }

    @Override
    public Game gamebyId(String id) {

        final String sql = "SELECT id, todo, note, finished "
                + "FROM todo WHERE id = ?;";

        return jdbcTemplate.queryForObject(sql, new ToDoMapper(), id);
    }

    @Override
    public boolean update(Game todo) {

        final String sql = "UPDATE todo SET "
                + "todo = ?, "
                + "note = ?, "
                + "finished = ? "
                + "WHERE id = ?;";

        return jdbcTemplate.update(sql,
                todo.getGameId() ,
                todo.getGuess() ,
                todo.isFinished(),
                todo.getGameId() ) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        final String sql = "DELETE FROM todo WHERE id = ?;";
        return jdbcTemplate.update(sql, id) > 0;
    }
    
    
    @Override
        public String getAnswerbyId(String Gameid){
            
            //Game temp = findByCustomerId2(Gameid);
            Game temp = findByCustomerId(Gameid);
            return temp.getAnswer();
           
        }
        
        
        
        public String findAllbyID(String id) {
	
        String sql = "SELECT * FROM games Where GameId = 'id'";

        List<Game> customers = jdbcTemplate.query(
                sql,
                new ToDoMapper());
        if(customers.isEmpty()){
            return "bad";
        }
        
        return customers.get(0).getAnswer();

        //return customers;
		
    }
        
        public String getFinalAnswer(String id){
            List<Game> games =getAll();
            for(int k=0 ; k < games.size() ; k++){
                if(games.get(k).getGameId().equalsIgnoreCase(id)){
                    return games.get(k).getAnswer();
                }
            }
            return "bad";
        }
        
        public Game findByCustomerId(String id) {

        //String sql = "SELECT * FROM games WHERE GameId = ?";
        String sql = "SELECT * FROM games WHERE (GameId = 'id' guess IS NULL)";
        

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ToDoMapper());

    }
        
        public Game findByCustomerId2(String id) {

        String sql = "SELECT * FROM games WHERE GameId = ?";

        return (Game) jdbcTemplate.queryForObject(
			sql, 
			new Object[]{id}, 
			new BeanPropertyRowMapper(Game.class));

    }
        
        public int[] checkGuess(String Guess , String Number){
        int[] bullscows = new int[2];
        int cows = 0;
        int bulls = 0;
        for(int k=0 ; k < Guess.length() ; k++){
            if(Guess.charAt(k) == Number.charAt(0) ){
                if(k == 0){
                    bulls ++;
                }else{
                   cows ++;  
                }
            }
            else if(Guess.charAt(k) == Number.charAt(1) ){
                if(k == 1){
                    bulls ++;
                }else{
                   cows ++;  
                }
            }
            else if(Guess.charAt(k) == Number.charAt(2) ){
                if(k == 2){
                    bulls ++;
                }else{
                   cows ++;  
                }
            }
            else if(Guess.charAt(k) == Number.charAt(3) ){
                if(k == 3){
                    bulls ++;
                }else{
                   cows ++;  
                }
            }
        }
        bullscows[0] = bulls;
        bullscows[1] = cows;
        return bullscows ;
    }
    

    private static final class ToDoMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game td = new Game();
            td.setId(rs.getInt("id"));
            td.setGameId(rs.getString("GameId")); 
            td.setAnswer(rs.getString("answer"));
            td.setCreatedAt(rs.getString("createdAt"));
            td.setGuess(rs.getString("guess"));  
            td.setFinished(rs.getBoolean("finished"));
            td.setEm(rs.getInt("em"));
            td.setPm(rs.getInt("pm"));
            return td;
        }
    }
}
