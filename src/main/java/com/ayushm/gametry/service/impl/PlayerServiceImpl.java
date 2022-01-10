package com.ayushm.gametry.service.impl;


import com.ayushm.gametry.model.game.Player;

import java.util.Optional;

public interface PlayerServiceImpl {
    Optional<Player> findById(Long Id);

}
