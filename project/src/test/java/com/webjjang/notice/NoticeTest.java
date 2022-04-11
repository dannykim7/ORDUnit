package com.webjjang.notice;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webjjang.notice.mapper.NoticeMapper;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

// test 실행 클래스 지정
@RunWith(SpringJUnit4ClassRunner.class)
// DB 설정 파일 지정 -> JAVA만 가지고 URL과 상관없이 실행되므로 설정파일을 직접 지정해 줘야한다.
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// 로그 출력 객체
@Log4j
public class NoticeTest {

	// mapper 자동 DI
	@Inject
	private NoticeMapper mapper;
	
	// 설정과 자동 DI 테스트
	@Test
	public void testDI() {
		log.info("========================== 자동 DI Test ========================");
		log.info(mapper + "\n");
	}
	
	// 공지사항 리스트 mapper 테스트
	@Test
	public void testList() throws Exception {
		PageObject pageObject = new PageObject();
		// 기간을 세팅해준다.
		pageObject.setPeriod("pre");
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		log.info("========================== 공지사항 리스트 mapper Test ========================");
		log.info(mapper.list(pageObject) + "\n");
		
	}
	
	// 공지사항 글보기 - 글번호
	@Test
	public void testView()  throws Exception {
		long no = 10;
		log.info("========================== 공지사항 글보기 mapper Test ========================");
		log.info(mapper.view(no) + "\n");
	}
	
	// 공지사항 글쓰기 - 제목, 내용, 작성자 -> vo
	@SuppressWarnings("deprecation")
	@Test
	public void testWrite()  throws Exception {
		NoticeVO vo = new NoticeVO();
		vo.setTitle("테스트 진행");
		vo.setContent("테스트 진행 중");
		vo.setStartDate(new Date(2022, 3, 11));
		vo.setEndDate(new Date(2022, 4, 11));
		log.info("========================== 공지사항 글쓰기 mapper Test ========================");
		log.info(mapper.write(vo) + "\n");
	}
	
	// 공지사항 글수정 - 번호, 제목, 내용, 작성자
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdate()  throws Exception {
		NoticeVO vo = new NoticeVO();
		vo.setNo(9);
		vo.setTitle("Test 진행");
		vo.setContent("테스트 진행 중");
		vo.setStartDate(new Date(2022, 3, 11));
		vo.setEndDate(new Date(2022, 4, 11));
		log.info("========================== 공지사항 글수정 mapper Test ========================");
		log.info(mapper.update(vo) + "\n");
	}
	
	// 공지사항 글삭제 - 글번호
	@Test
	public void testDelete()  throws Exception {
		long no = 11;
		log.info("========================== 공지사항 글삭제 mapper Test ========================");
		log.info(mapper.delete(no) + "\n");
	}
}
