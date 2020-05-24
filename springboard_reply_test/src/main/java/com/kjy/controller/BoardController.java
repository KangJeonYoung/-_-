package com.kjy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.pattern.IntegerPatternConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kjy.domain.BoardVO;
import com.kjy.service.BoardService;
import com.kjy.service.MemberService;
import com.kjy.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService service;
	private ReplyService service_re;
	private MemberService servicee;
	
	@GetMapping("/list")
	public void list(HttpSession session,Model model) {
	
		log.info("list");
		model.addAttribute("list",service.getList());
		String id = (String)session.getAttribute("id");
		if(id == null) {
			id =".";
			model.addAttribute("model",servicee.getModel(id));
		}
		model.addAttribute("model",servicee.getModel(id));
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("register : " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model,HttpServletRequest request) {
		
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
		
		log.info("list"); 
		model.addAttribute("list",service_re.getList());
		
		HttpSession session = request.getSession();
		model.addAttribute("userid",session.getAttribute("id"));
		
		String id = (String)session.getAttribute("id");
		if(id == null) {
			id =".";
			model.addAttribute("model",servicee.getModel(id));
		}
		model.addAttribute("model",servicee.getModel(id));
		 
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify:"+board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","SUCCESS");
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		log.info("remove..."+bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "SUCCESS");
		}
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/register")
	public void register(HttpServletRequest request, Model model) {
	
		HttpSession session = request.getSession();
		model.addAttribute("userid",session.getAttribute("id"));
	}

	
}
