package com.kjy.controller;

import java.security.Provider.Service;

import javax.print.attribute.standard.Severity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.kjy.domain.MemberVO;
import com.kjy.service.BoardService;
import com.kjy.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	/* 관리자 페이지 */
	@GetMapping("/admin")
	public void getTest(HttpSession session,Model model) {
	
		log.info("test 페이지 입니다 권한 주려고 만듬.");
		
		String id = (String)session.getAttribute("id");
		if(id == null) {
			id =".";
			model.addAttribute("model",service.getModel(id));
		}
		model.addAttribute("model",service.getModel(id));
	}
	
	/* 관리자계정으로 사용자 아이디 삭제 처리 */
	@PostMapping("/remove")
	public String remove(MemberVO member, RedirectAttributes rttr) {
		
		log.info("remove..."+member);
		if(service.remove(member)) {
			rttr.addFlashAttribute("result", "SUCCESS");
		}
		return "redirect:/board/list";
	}
	
	/* 관리자 계정으로 사용자 권한 변경 처리 */
	@PostMapping("/authInsert")
	public String authInsert(MemberVO member, RedirectAttributes rttr) {
		
		log.info("authInsert : " + member);
		
		if(service.authInsert(member)) {
		rttr.addFlashAttribute("result","SUCCESS");
		}
//		log.info(member.getUserid().getClass().getName());
//		log.info(member.getAuth().getClass().getName());
		
		return "redirect:/board/list";
	}
	
	/* 내정보 페이지 */
	@GetMapping("/member")
	public void GetMemer(HttpSession session, Model model) {
	
		log.info("login page");
		
		String id = (String)session.getAttribute("id");
		if(id == null) {
			id =".";
			model.addAttribute("member",service.getMember(id));
		}
		model.addAttribute("member",service.getMember(id));
	}
	
	/* 로그인 페이지 */
	@GetMapping("/login")
	public void login(HttpSession session, Model model) {
	
		log.info("login page");
		
		String id = (String)session.getAttribute("id");
		if(id == null) {
			id =".";
			model.addAttribute("model",service.getModel(id));
		}
		model.addAttribute("model",service.getModel(id));
	}
	
	/* 로그인 처리 페이지 */
	@PostMapping("/login")
	public String login(MemberVO member, HttpServletRequest request) {

		log.info("로그인 처리중..." + member);

		String check = service.LoginCheck(member);
		HttpSession session = request.getSession();
		if (check == "1") {
			log.info("로그인 성공!!");
			session.setAttribute("id", member.getUserid());
			System.out.println("세션 값: "+session.getAttribute("id"));
			return "redirect:/board/list";
		}
			log.info("로그인 실패!!");
		return "redirect:/error";
	}
	
	/* 로그아웃 처리 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/board/list";
	}
	
	/* 회원가입 페이지 */
	@GetMapping("/signup")
	public void signup( ) {
	
		log.info("signup page");
	}
	
	/* 회원가입 처리 */
	@PostMapping("/join")
	public String join(MemberVO member) {
		
		log.info("join : " + member);
		
		service.register(member);
		
		return "redirect:/login";
	}
	
	/* 아이디 체크 아직 구현안됨 */
	@ResponseBody
	@PostMapping("/idChk")
	public int idChk(MemberVO vo) throws Exception {
		int result = service.idChk(vo);
		return result;
	}	
	
	/* 내정보 수정 */
	@GetMapping("/modify")
	public String modify(MemberVO member, RedirectAttributes rttr) {
		log.info("modify:"+member);
		
		if(service.modify(member)) {
			rttr.addFlashAttribute("result","SUCCESS");
		}
		return "redirect:/member";
	}
	
	/* 관리자 아이콘 */
	@GetMapping("/includes/header")
	public void list(HttpSession session,Model model) {
	
		String id = (String)session.getAttribute("id");
		if(id == null) {
			id =".";
			model.addAttribute("model",service.getModel(id));
		}
		model.addAttribute("model",service.getModel(id));
	}
	
	
//	
//	@GetMapping("/remove")
//	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
//		
//		log.info("remove..."+bno);
//		if(service.remove(bno)) {
//			rttr.addFlashAttribute("result", "SUCCESS");
//		}
//		return "redirect:/board/list";
//	}
//	
//	
//	@GetMapping("/register")
//	public void register() {
//		
//	}


	
}
