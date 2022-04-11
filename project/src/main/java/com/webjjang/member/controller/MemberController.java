package com.webjjang.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webjjang.member.service.MemberService;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

	// 자동 DI 시전
	@Autowired
	private MemberService service;

	// 로그인 폼
	@GetMapping("/login.do")
	public String loginForm() throws Exception {
		log.info("login 폼으로 이동");
		return "member/login";
	}

	// 사용자가 아이디와 비밀번호를 입력해서 보낸다 -> 받는다
	@PostMapping("/login.do")
	public String login(LoginVO invo, HttpSession session) throws Exception {
		log.info("로그인 처리 - invo : " + invo);
		session.setAttribute("login", service.login(invo));
		// 임시로 보냄
		return "redirect:/board/list.do";
	}

	@GetMapping("/logout.do")
	public String logut(HttpSession session) throws Exception {
		// 로그아웃 처리 : session의 정보를 지운다.
		session.removeAttribute("login");
		log.info("로그아웃");
		// 원래는 메인으로 보내야하지만 메인 안만듬
		return "redirect:/board/list.do";
	}

	// 회원 리스트(onlyadmin)
	@GetMapping("/list.do")
	// @ ModelAttribute 변수 - 모델에 담긴 변수로 처리해준다 -> jsp까지 전달된다
	public String list(@ModelAttribute PageObject pageObject, Model model) throws Exception {
		model.addAttribute("list", service.list(pageObject));
		return "member/list";
	}

	// 회원정보보기 / 내 정보 보기
	@GetMapping("/view.do")
	public String view(String id, Model model, HttpSession session) throws Exception {

		if (id == null) {
			// 내 정보 보기
			model.addAttribute("title", "내 정보 보기");
			id = ((LoginVO) session.getAttribute("login")).getId();
		} else {
			// 회원관리 - 회원정보보기
			model.addAttribute("title", "회원 정보 보기");
		}
		model.addAttribute("vo", service.view(id));
		return "member/view";
	}

	// 회원가입 폼
	@GetMapping("write.do")
	public String writeForm() throws Exception {
		log.info("회원가입");
		return "member/write";
	}

	// 회원가입 처리
	@PostMapping("/write.do")
	public String write(MemberVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		log.info(vo);
		// 회원 사진 저장 위치
		String path = "/upload/member";
		// 서버에 파일 저장하기 -> 서버에 저장된 파일명을 받아서 photo에 넣는다
		vo.setPhoto(FileUtil.upload(path, vo.getPhotoFile(), request));
		// 회원 가입 처리
		service.write(vo);
		// redirect 하는 페이지에서 한번만 사용되는 속성값을 전달할 수 있다 -> session
		rttr.addFlashAttribute("msg", "성공적으로 회원가입완료, \\n 로그인 후 사용하세요");
		
		return "redirect:/";
	}
	
	// 아이디 중복 체크
	@GetMapping("/idCheck")
	public String idCheck(String id, Model model) throws Exception {
		model.addAttribute("id", service.idCheck(id));
		return "member/idCheck";
	}
	// 상태 변경
	@PostMapping("changeStatus.do")
	public String changeStatus(PageObject pageObject, MemberVO vo) throws Exception{
		log.info(vo);
		System.out.println("상태변경완료");
		
		return "redirect:view.do?id="+vo.getId()+"&page="+pageObject.getPage()+"&perPageNum="
				+pageObject.getPerPageNum();
	}
	// 등급 변경
	@PostMapping("/changeGradeNo.do")
	public String changeGradeNo(PageObject pageObject, MemberVO vo) throws Exception {
		log.info(vo);
		System.out.println("등급변경완료");
		// DB에서 등급변경
		service.changeGradeNo(vo);
		return "redirect:view.do?id="+vo.getId()+"&page="+pageObject.getPage()+"&perPageNum="
				+pageObject.getPerPageNum();
	}
}
