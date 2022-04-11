package com.example.pogulum.services;

import com.example.pogulum.model.Game;
import java.util.List;

public interface GameService {

    Game saveGame(Game game);

    Game getGame(Long id);

    List<Game> getAllGames();

    Game deleteGame(Long id);
}
