package com.ayushm.gametry.service;

import com.ayushm.gametry.model.game.Board;
import org.springframework.stereotype.Service;
@Service
public class LevelService {


    Board board = new Board();
    public void easy(){
        board.addSnake(25,4);
        board.addSnake(29,10);
        board.addSnake(33,27);
        board.addSnake(42,22);
        board.addSnake(63,59);
        board.addSnake(64,37);
        board.addSnake(70,49);
        board.addSnake(94,73);
        board.addSnake(96,47);
        board.addSnake(99,78);


        board.addLadder(2,23);
        board.addLadder(8,14);
        board.addLadder(26,35);
        board.addLadder(38,44);
        board.addLadder(31,86);
        board.addLadder(41,60);
        board.addLadder(56,77);
        board.addLadder(69,90);
        board.addLadder(79,81);
        board.addLadder(88,92);
    }

    public void mediumGame(){
        board.addSnake(56,12);
        board.addSnake(65,24);
        board.addSnake(76,30);
        board.addSnake(89,37);

        board.addLadder(59,1);
        board.addLadder(69,2);
        board.addLadder(69,3);
        board.addLadder(69,4);
        board.addLadder(72,5);
        board.addLadder(89,6);
    }

    public void hardGame(){
        board.addSnake(56,12);
        board.addSnake(65,24);
        board.addSnake(76,30);
        board.addSnake(89,37);

        board.addLadder(59,16);
        board.addLadder(69,23);
        board.addLadder(72,29);
        board.addLadder(89,8);
    }
}
