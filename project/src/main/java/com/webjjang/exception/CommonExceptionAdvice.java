package com.webjjang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {

	// Exception 예외가 발생하면 가져와서 처리해준다 - 500번 오류
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) throws Exception {

		// 콘솔에 에러 메시지 출력
		log.info("//////////////////예외처리//////////////////");
		log.error("에러 메시지 : " + ex.getMessage());

		// JSP 페이지로 예외를 전달하기 위해 모델에 담기
		model.addAttribute("exception", ex);

		// 모델 출력
		log.error(model);

		// JSP로 이동
		return "error/error_500";
	}

	// 404번 오류
	// 404번 오류 클래스 정의
	@ExceptionHandler(NoHandlerFoundException.class)
	// 404번 오류 코드를 클라이언트에 전송
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		
		log.info("//////////////////no Page(404)//////////////////");
		log.error("요청하신 페이지가 없습니다");
		
		return "error/error_404";
	}
	
}
