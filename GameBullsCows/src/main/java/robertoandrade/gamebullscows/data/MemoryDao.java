/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.gamebullscows.data;

import java.util.ArrayList;
import java.util.List;
import robertoandrade.gamebullscows.data.DAO;
import robertoandrade.gamebullscows.models.Game;

/**
 *
 * @author elizabeth
 */
public class MemoryDao implements DAO {

    
    private static final List<Game> rounds = new ArrayList<>();

    @Override
    public Game add(Game todo) {

        int nextId = rounds.stream()
                .mapToInt(i -> Integer.parseInt(i.getGameId()))
                .max()
                .orElse(0) + 1;

        todo.setGameId(String.valueOf(nextId)); 
        rounds.add(todo);
        return todo;

    }

    @Override
    public List<Game> getAll() {
        return new ArrayList<>(rounds);
    }

    @Override
    public Game gamebyId(String id) {
        return rounds.stream()
                .filter(i -> i.getGameId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(Game todo) {

        int index = 0;
        while (index < rounds.size()
                && rounds.get(index).getGameId() != todo.getGameId()) {
            index++;
        }

        if (index < rounds.size()) {
            rounds.set(index, todo);
        }
        return index < rounds.size();
    }

    @Override
    public boolean deleteById(String id) {
        return rounds.removeIf(i -> i.getGameId() == id);

    }
    
    @Override
    public String addGame(Game play) {
        return "";
    }
    
    
    @Override
    public String getAnswerbyId(String Gameid){
        return "";
    }

    
}
