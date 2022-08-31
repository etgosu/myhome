package com.kyn.myhome.controller;

import com.kyn.myhome.model.Board;
import com.kyn.myhome.model.Inforoom;
import com.kyn.myhome.repository.BoardRepository;
import com.kyn.myhome.repository.InforoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private InforoomRepository inforoomRepository;
    @GetMapping("list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);
        return "board/list";
    }

    @GetMapping("inforoom")
    public String inforoom(Model model){
        List<Inforoom> inforooms  = inforoomRepository.findAll();
        model.addAttribute("inforooms",inforooms);
        return "board/list2";
    }
}
