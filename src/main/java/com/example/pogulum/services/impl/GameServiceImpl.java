package com.example.pogulum.services.impl;

import com.example.pogulum.exceptions.GameNotFoundException;
import com.example.pogulum.model.Game;
import com.example.pogulum.repository.GameRepository;
import com.example.pogulum.services.GameService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public Game getGame(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game deleteGame(Long id) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
        gameRepository.delete(game);
        return game;
    }
}
