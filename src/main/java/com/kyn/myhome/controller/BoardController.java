package com.kyn.myhome.controller;

import com.kyn.myhome.model.Board;
import com.kyn.myhome.model.Inforoom;
import com.kyn.myhome.repository.BoardRepository;
import com.kyn.myhome.repository.InforoomRepository;
import com.kyn.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private InforoomRepository inforoomRepository;
    @Autowired
    private BoardValidator boardValidator;
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

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        if(id == null){
            model.addAttribute("board", new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String formSubmit(@Valid Board board, BindingResult bindingResult){
        boardValidator.validate(board,bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

    @GetMapping("/infoForm")
    public String infoform(Model model, @RequestParam(required = false) Long id){
        if(id == null){
            model.addAttribute("inforoom", new Inforoom());
        }else{
            Inforoom inforoom = inforoomRepository.findById(id).orElse(null);
            model.addAttribute("inforoom", inforoom);
        }
        return "board/form2";
    }

    @PostMapping("/infoForm")
    public String infoFormSubmit(@ModelAttribute Inforoom inforoom){
        inforoomRepository.save(inforoom);
        return "redirect:/board/inforoom";
    }

}
