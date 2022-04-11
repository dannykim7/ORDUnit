package com.webjjang.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webjjang.board.service.BoardService;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

// 해당 클래스가 컨트롤러로 쓰임을 알리는 어노테이션
@Controller
// /board 요청에 공통적으로 처리해야 할 것을 의미
@RequestMapping("/board")
@Log4j
public class BoardController {

	/* 이거말고 위에 로그포제이 어노테이션
	 * private static final Logger log =
	 * LoggerFactory.getLogger(BoardController.class);
	 */

	// 의존성 자동 주입(Dependency Inject) -> 자동으로 하도록 지정하는 어노테이션
	// : @Autowired, @Inject
	@Autowired
	private BoardService service;

	// 1. list
	// 처리 결과를 request에 담아야 하는데 Spring에서는 request가 model에 존재한다.
	// model에 넣어주면 request에 담기도록 프로그램 되어있다.
	// 파라미터로 model 객체를 전달 받아서 사용한다
	@GetMapping("/list.do")
	// @ModelAttribute -> jsp까지 넘어가서 사용되는거
	public String list(@ModelAttribute PageObject pageObject, Model model) throws Exception {
		// 페이지가 1보다 작으면 1페이지로 세팅해준다
		if (pageObject.getPage() < 1) {
			pageObject.setPage(1);
		}
		log.info(pageObject);
		model.addAttribute("list", service.list(pageObject));
		// /WEB-INF/views/ + board/list + .jsp -> servlet-context.xml에 설정
		return "board/list";
	}

	// 2. view
	// model에 넣어주면 request에 담기도록 프로그램 되어있다.
	// 파라미터로 model 객체를 전달 받아서 사용한다
	@GetMapping("/view.do")
	public String view(long no, int inc, Model model) throws Exception {
		log.info(no);
		log.info(inc);

		// 글 내용 중에서 줄바꿈처리해야만 한다. \n -> <br>로 바꾼다.
		BoardVO vo = service.view(no, inc);
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		model.addAttribute("vo", vo);
		return "board/view";
	}

	// 3-1. writeForm
	@GetMapping("/write.do")
	public String writeForm() throws Exception {
		return "board/write";
	}

	// 3-2. write
	@PostMapping("/write.do")
	public String write(BoardVO vo, int perPageNum) throws Exception {
		log.info(vo);
		service.write(vo);
		return "redirect:list.do?page=1&perPageNum=" + perPageNum;
	}

	// 4-1. updateForm
	@GetMapping("/update.do")
	public String updateForm(long no, Model model) throws Exception {
		System.out.println("BoardController.updateForm().no - " + no);
		model.addAttribute("vo", service.view(no, 0));
		return "board/update";
	}

	// 4-2. update
	@PostMapping("/update.do")
	public String update(PageObject pageObject, BoardVO vo) throws Exception {
		System.out.println("BoardController.update().no - " + vo);
		service.update(vo);
		return "redirect:view.do?no=" + vo.getNo() + "&inc=0" 
		+ "&page=" + pageObject.getPage() 
		+ "&perPageNum=" + pageObject.getPerPageNum()
		;
	}

	// 5. delete
	@GetMapping("/delete.do")
	public String delete(long no, int perPageNum) throws Exception {
		System.out.println("BoardController.delete().no - " + no);
		service.delete(no);
		return "redirect:list.do?perPageNum=" + perPageNum;
	}
}
