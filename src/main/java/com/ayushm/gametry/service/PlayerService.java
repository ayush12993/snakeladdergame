package com.ayushm.gametry.service;

import com.ayushm.gametry.model.game.Player;
import com.ayushm.gametry.repository.gamerepo.PlayerRepository;
import com.ayushm.gametry.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService implements PlayerServiceImpl {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Optional<Player> findById(Long Id){
        return playerRepository.findById(Id);
    }

    public List<String> getList() {
        List<Player> userList = playerRepository.findAll();
        List<String> userNameList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            String tempUserName = userList.get(i).getUsername();
            //int tempPositions = userList.get(i).getPosition();
            userNameList.add(tempUserName );
        }
        return userNameList;
    }

}
