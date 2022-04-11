package com.webjjang.image.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webjjang.image.service.ImageService;
import com.webjjang.image.vo.ImageVO;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/image")
@Log4j
public class ImageController {

	// 자동 DI
	@Autowired
	private ImageService service;

	// 먹이는 매개변수가 다르면 메소드 이름이 같아도 공존 가능하다 - 오버로드
	// 1. list
	// 위의 이미지와 이어붙이기 해야하니까 앞에 슬래쉬 붙어야 한다
	@GetMapping("/list.do")
	// 리스트에 담을려면 페이지오브젝트랑 모델있어야죠
	public String list(PageObject pageObject, Model model) throws Exception {
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
		log.info("list()");
		model.addAttribute("list", service.list(pageObject));
		return "image/list";
	}

	// 2. view
	@GetMapping("/view.do")
	public String view(long no, Model model) throws Exception {
		// DB에서 데이터를 가져와서 
		ImageVO vo = service.view(no);
		// 내용의 줄바꿈 처리하고
		vo.setContent(vo.getContent().replace("\n", "<br>"));
		// model에 담는다. jsp에서 request에서 꺼낼 수 있다
		model.addAttribute("vo", vo);
		// 가져온 데이터를 JSP에 표시하기 위해서 JSP 정보를 리턴해준다.
		return "image/view";
	}

	// 2-1. changeImage
	@PostMapping("/changeImage.do")
	public String changeImage(PageObject pageObject, ImageVO vo, HttpServletRequest request) throws Exception {
	
		String path = "/upload/image";
		
		// 서버에 파일을 업로드한다 -> DB에 저장할 파일정보가 나온다
		String fileName = FileUtil.upload(path, vo.getImage(), request);
		vo.setFileName(fileName);
		
		// DB에 파일정보를 바꾼다 -> 번호, 파일명 필요 -> controller - service - mapper
		service.changeImage(vo);
		
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

	// 3-1. writeForm
	@GetMapping("/write.do")
	public String writeForm() throws Exception {

		return "image/write";
	}

	// 3-2. write
	@PostMapping("/write.do")
	// 아이디가 필요하니 세션도 요구하자
	public String write(PageObject pageObject, ImageVO vo, HttpSession session, HttpServletRequest request) throws Exception {
		log.info(pageObject);

		// 작성자 아이디 세팅(LoginVO 캐스팅 필요)
		vo.setId(((LoginVO) session.getAttribute("login")).getId());
		
		// 파일명 세팅
		String path = "/upload/image";
		// upload(path, multipartFile, 서블릿 리퀘스트)
		// 저장할 파일이름(FileUtil 사용해서 간소화)
		String fileName = FileUtil.upload(path, vo.getImage(), request);
		vo.setFileName(fileName);

		// 아이디 세팅 확인
	    log.info(vo);
		
		// DB 정보 저장
		service.write(vo);

		return "redirect:list.do?page=1&perPageNum=" + pageObject.getPerPageNum();
	}

	// 4-1. updateForm
	@GetMapping("/update.do")
	public String updateForm(PageObject pageObject, long no, Model model)throws Exception {
	log.info(no);
	// DB에서 정보받고 모델에 집어넣고 리퀘스트에 담겨서 jsp까지 전달
	model.addAttribute("vo", service.view(no));
	
		return "image/update";
	}

	// 4-2. update
	@PostMapping("/update.do")
	public String update(PageObject pageObject, ImageVO vo)  throws Exception{
		log.info(vo);
		log.info(pageObject);
		
		// DB에 수정 : controller - service - mapper
		service.update(vo);
		
		return "redirect:view.do?no="+ vo.getNo()
		+"&page="+pageObject.getPage()
		+"&perPageNum="+pageObject.getPerPageNum()
		+"&key="+pageObject.getKey()
		+"&word="+ URLEncoder.encode(pageObject.getWord(), "utf-8");
	}

	// 5. delete
	@GetMapping("/delete.do")
	public String delete(PageObject pageObject, long no, String deleteImage, HttpServletRequest request) throws Exception {
		log.info(no);
		log.info(deleteImage);
		log.info(pageObject);
		
		// DB의 정보를 지운다 - no
		service.delete(no);
		// 서버에서 파일을 지운다 - deleteImage
		FileUtil.remove(FileUtil.getRealPath("", deleteImage, request));
		
		return "redirect:list.do?perPageNum=" + pageObject.getPerPageNum();
	}
}
