package com.webjjang.util.intercepter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.webjjang.member.vo.LoginVO;

import lombok.extern.log4j.Log4j;
@Log4j
public class AuthorityIntercepter extends HandlerInterceptorAdapter {

	// url 권한 정보 저장
		private static Map<String, Integer> authMap = new HashMap<>();
		
		// 이 클래스에서만 사용하는 변수

		private String url = null;
		
		static {
					// 회원관리 권한
					authMap.put("/member/list.do", 9);
					authMap.put("/member/view.do", 1);
					authMap.put("/member/update.do", 1);
					authMap.put("/member/delete.do", 9);
					
					// 공지사항 권한
					authMap.put("/notice/write.do", 9);
					authMap.put("/notice/update.do", 9);
					authMap.put("/notice/delete.do", 9);
					
					// 유닛
					authMap.put("/unit/write.do", 9);
					authMap.put("/unit/update.do", 9);
					authMap.put("/unit/delete.do", 9);
					
					// 공략 게시판
					authMap.put("/unit/delete.do", 9);
		}
			
	 @Override
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	    throws Exception {
		 // 필터 작동하는거 확인해주는 로그
	   log.info("+[AuthorityIntercepter]--------+");
	   // 처리해야할 내용으로 가게한다.
	   url = request.getServletPath();
	   log.info("+ Authotiry.preHandle.url : " + url);


	   // 로그인 객체 꺼내기
	   // 로그인 정보는 session에 있다. session이 안보인다. request에서 꺼낼 수 있다.
	   HttpSession session = request.getSession();
	   LoginVO vo = (LoginVO) session.getAttribute("login");

	   // 새로운 메시지 갯수를 가져오는 처리가 로그아웃 이상이 생긴경우 처리
	   // - 브라우에서 페이지 이동이 없이 가만히 놔둔 상태로 있거가 서버가 재실행이 세션이 사라진다. 
	   if(url.equals("/ajax/getMessageCnt.do") && vo == null)
	   throw new ServletException("DispatcherServlet - 메시지 갯수 가져 오는데 로그인이 안되어 있습니다.");

	   // 권한이 없는 경우의 처리
	   if(!checkAuthority(vo)) {
	   // 오류 페이지로 이동시킵니다.
	   response.sendRedirect(request.getContextPath() + "/error/auth_error.do");
	   // 호출한 쪽으로 되돌아 갑니다. -> 없으면 계속 아래로 실행이 된다.
	   return false;
	   }
	   
	   // 요청한 내용을 계속 진행
	   return super.preHandle(request, response, handler);
	   } // end of preHandle
	   
	   private boolean checkAuthority(LoginVO vo) {
	   // url 정보가 authMap 있는지 확인 한다.
	   // 데이터가 없으면(null이면) 권한 체크가 필요없는 페이지 요청입니다.
	   Integer pageGradeNo = authMap.get(url);
	   if(pageGradeNo == null) {
	   log.info("AuthorityFilter.checkAuthority() - 권한이 필요없는 페이지 입니다.");
	   return true;
	   }
	   // 여기서 부터를 로그인이 필요한 처리입니다. vo가 null이면 안된다.
	   if(vo == null) {
	   log.info("AuthorityFilter.checkAuthority() - 로그인이 필요합니다.");
	   return false;
	   }
	   log.info("AuthorityFilter.checkAuthority().pageGradeNo : " + pageGradeNo);
	   log.info("AuthorityFilter.checkAuthority().userGradeNo : " + vo.getGradeNo());
	   // 권한이 없는 페이지 요청에 대한 처리
	   if(pageGradeNo > vo.getGradeNo()) {
	   log.info("AuthorityFilter.checkAuthority() - 권한이 없습니다.");
	   return false;
	   }
	   log.info("AuthorityFilter.checkAuthority() - 권한이 있습니다.");
	   return true;
	   }
}