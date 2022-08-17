/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.gamebullscows.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import robertoandrade.gamebullscows.models.Game;
import robertoandrade.gamebullscows.models.MiniGame;
import robertoandrade.gamebullscows.models.Round;
import robertoandrade.gamebullscows.service.service;

/**
 *
 * @author elizabeth
 * 
 */

@RestController
@RequestMapping("GameBullsCows")
public class Controller {
    //private final ToDoDao dao;
    private final service ser;

    public Controller(service ser) {
        this.ser = ser;
    }

    @GetMapping
    public List<MiniGame> all() {
        return ser.all();
    }
    
    
    @PostMapping
@ResponseStatus(HttpStatus.CREATED)
public String create() {
    Game play = new Game();
    return ser.addGame(play); 
}
    
@PostMapping("/guess")
@ResponseStatus(HttpStatus.CREATED)
public Round create(@RequestBody Game todo) {
    return ser.add(todo);
}


@GetMapping("game/{id}")
public ResponseEntity<MiniGame> findById(@PathVariable int id) {
    
    MiniGame result = ser.findGameById(id);
    if (result == null) {
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(result);
}

@GetMapping("rounds/{id}")
public List<Round> findRoundsById(@PathVariable int id) {
    
    List<Round> rounds = ser.findRoundsByiD(id);
    Game result = null ;
    if (rounds.isEmpty()) {
        return null;
    }
    return rounds;
}

@PutMapping("/{id}")
public ResponseEntity update(@PathVariable int id, @RequestBody Game todo) {
    ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
    if (id != Integer.parseInt(todo.getGameId())) {
        response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
    } else if (ser.update(todo)) {
        response = new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    return response;
}

@DeleteMapping("/{id}")
public ResponseEntity delete(@PathVariable int id) {
    if (ser.deleteById(id)) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity(HttpStatus.NOT_FOUND);
}
}
