package com.webjjang.unit.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webjjang.notice.controller.NoticeController;
import com.webjjang.unit.service.UnitService;
import com.webjjang.unit.vo.UnitVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/unit")
public class UnitController {

	private static final Logger logger = LoggerFactory.getLogger(UnitController.class);
	
	@Autowired
	private UnitService service;
	
	// list
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model) throws Exception{
		
		logger.info("유닛 리스트");
		
		// 페이지가 음수값이면 1로 세팅한다
				if (pageObject.getPage() < 1) {
					pageObject.setPage(1);
				}
				// perPageNum == 10 이면 8로 고치자. perPageNum - 4 8 12 16
				// --> 기본 한 페이지의 데이터 갯수는 8로 고정한다.
				if(pageObject.getPerPageNum() == 10) {
					pageObject.setPerPageNum(8);
				}
				// /WEB-INF/views/ + Image/list + .jsp -> servlet-context.xml에 설정
				logger.info("list()");
				model.addAttribute("list", service.list(pageObject));
		
		return "unit/list";
	}
	
	// view
	@GetMapping("/view.do")
	public String view(long no, Model model) throws Exception {
		
		
		logger.info(null);
		
		// DB에서 데이터를 가져와서 
				UnitVO vo = service.view(no);
				// 내용의 줄바꿈 처리하고
				vo.setSkill(vo.getSkill().replace("\n", "<br>"));
				// model에 담는다. jsp에서 request에서 꺼낼 수 있다
				model.addAttribute("vo", vo);
		
		return "unit/view";
	}
	// unitchange
	@PostMapping("/changeImage.do")
	public String changeImage(PageObject pageObject, UnitVO vo, HttpServletRequest request) throws Exception {
		
		String path = "/upload/unit";
		
		// 서버에 파일을 업로드한다 -> DB에 저장할 파일정보가 나온다
		// String fileName = FileUtil.upload(path, vo.getImage(), request);
		// vo.setFileName(fileName);
		
		// DB에 파일정보를 바꾼다 -> 번호, 파일명 필요 -> controller - service - mapper
		// service.changeImage(vo);
		
		// 이전의 파일은 지운다
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteImage(), request));
		
		// 파일이 로드되는 중간에 보기로 이동해버리면 이미지가 보이지 않는다.
		// 올리는 동안의 시간을 재워서 보기로 이동시키지 않도록 한다.
		Thread.sleep(1000);
		
		return "redirect:view.do?no=" +vo.getNo()
		+"&page=" + pageObject.getPage()
		+"&perPageNum=" + pageObject.getPerPageNum()
		+"&key=" + pageObject.getKey()
		+"&word=" + pageObject.getWord()
		;
	}
	
	// 	writeForm
		@GetMapping("/write.do")
		public String writeForm() throws Exception {

			return "unit/write";
		}
		

}