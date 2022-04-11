package com.webjjang.myapp;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

// 자동생성 어노테이션 - @Controller : url 매핑
@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	// 난 아무것도 돌려주기 싫어(리턴없어)
	public void uploadForms() throws Exception {
		log.info("uploadForm()");
	}
	@PostMapping("/uploadFormAction")
	// uploadForm 인풋태그의 네임과 맞춰야한다 - uploadFile
	// fileName을 넘기기 위해 모델에 담아서 넘겨주자
	public void uploadFormAction(MultipartFile uploadFile, HttpServletRequest request, Model model) throws Exception{
		// 메인메모리(RAM) 혹은 임시 폴더에 저장된 상태 - 아직 저장안됨
		log.info(uploadFile);
		log.info(uploadFile.getOriginalFilename());
		
		
		// 메소드 파라미터에 리스트 넣으면 getoriginal파일네임 바로못써요
		//	log.info(uploadFiles.getOriginalFilename());
	
		// 저장위치 - 서버 기준의 상대 위치 -> 실제적으로 저장할 때는 절대위치가 필요
		String path = "/upload/image";
		
		// request를 이용해서 절대위치를 구한다 -> request는 매개변수로 받는다
		// 저장위치 
		// - D:\KSH\WORKSPACE\spring\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ex00\업로드
		String savePath = request.getServletContext().getRealPath(path);
		log.info(savePath);
		
		// 리스트 쓰면 밑에 전부 못쓰니까 주석처리
		// 파일 저장해주는 메소드 - 원래 파일명으로 저장한다, 파일명이 같으면 덮어쓰기가 된다.
		uploadFile.transferTo(new File(savePath, uploadFile.getOriginalFilename()));

		// DB에 저장될 파일 정보
		String fileName = path + "/" + uploadFile.getOriginalFilename();
		log.info(fileName);
		
		// fileName을 넘기기 위해 모델에 담아서 넘겨주자
		model.addAttribute("fileName", fileName);
	}
	
}
