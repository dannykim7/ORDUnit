package com.webjjang.notice.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webjjang.member.service.MemberService;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.notice.service.NoticeService;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;
@SuppressWarnings("unused")
@Controller
@RequestMapping("/notice")
// log객체를 자동으로 만들어준다
@Log4j
public class NoticeController {

	 private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	 
	@Autowired
	private NoticeService service;
	private MemberService service1;

	// 1. list
	// 현재 공지 : pre로 할건지 now로 할건지 암거나(이름만 짓는거)
	// 지난 공지 : old -> 현재 밑
	// 예약 공지 : res(reservation)
	// 전체 공지 : all
	@GetMapping("/list.do")
	public String list(@ModelAttribute PageObject pageObject, Model model, HttpSession session) throws Exception {
		// 페이지가 1보다 작으면 1페이지로 세팅해준다
		if (pageObject.getPage() < 1) {
			pageObject.setPage(1);
		}
		// 콘솔에다 출력하는거
		System.out.println("NoticeController.list().pageObject - " + pageObject);
		// 로그 정보 출력
		log.info("log info 출력 - pageObject : " + pageObject);
		// 로그 경보 출력
		//log.warn("log warn 출력 - pageObject : " + pageObject);
		// 로그 정보 출력
		//log.error("log error 출력 - pageObject : " + pageObject);
		model.addAttribute("list", service.list(pageObject));
		// /WEB-INF/views/ + board/list + .jsp -> servlet-context.xml에 설정
		return "notice/list";
	}

	// 2. view
	@GetMapping("/view.do")
	public String view(long no, Model model) throws Exception {
		System.out.println("NoticeController.view().no - " + no);
		NoticeVO vo = service.view(no);
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		model.addAttribute("vo", vo);
		return "notice/view";
	}

	// 3-1. writeForm
	@GetMapping("/write.do")
	public String writeForm() throws Exception{
		System.out.println("NoticeController.writeForm()");
		return "notice/write";
	}

	// 3-2. write
	@PostMapping("/write.do")
	public String write(NoticeVO vo, int perPageNum) throws Exception {
		System.out.println("NoticeController.write().vo - " + vo);
		service.write(vo);
		return "redirect:list.do?page=1&perPageNum=" + perPageNum;
	}

	// 4-1. updateForm
	@GetMapping("/update.do")
	public String updateForm(long no, Model model) throws Exception {
		System.out.println("NoticeController.updateForm().no - " + no);
		model.addAttribute("vo", service.view(no));
		return "notice/update";
	}

	// 4-2. update
	@PostMapping("/update.do")
	public String update(PageObject pageObject, NoticeVO vo) throws Exception {
		System.out.println("NoticeController.update().vo - " + vo);
		service.update(vo);
		return "redirect:list.do?no=" + vo.getNo() + 
				"&page=" + pageObject.getPage() + "&perPageNum=" + pageObject.getPerPageNum() + 
				// 자바 부분의 한글 코드와 운영 한글 코드가 드르므로 자바에서 꺼내서 넣으면 깨진다
				// 인코딩 필수
				"&key=" + pageObject.getKey() + "&word=" + URLEncoder.encode(pageObject.getWord(), "utf-8");
	}

	// 5. delete
	@GetMapping("/delete.do")
	public String delete(long no, int perPageNum) throws Exception {
		System.out.println("NoticeController.delete()");
		service.delete(no);
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
}