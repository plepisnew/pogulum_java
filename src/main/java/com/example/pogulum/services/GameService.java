package com.example.pogulum.services;

import com.example.pogulum.model.Game;

import java.util.List;

public interface GameService {
    Game saveGame(com.example.pogulum.model.Game game);

    Game getGame(Long id);

    List<Game> getAllGames();


}
