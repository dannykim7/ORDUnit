package com.webjjang.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Mapper;
// 서비스 자동 생성
import org.springframework.stereotype.Service;

import com.webjjang.notice.mapper.NoticeMapper;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;

@Service
public class NoticeService {
	@Inject
	private NoticeMapper mapper;

	// // 1. list
	public List<NoticeVO> list(PageObject pageObject) throws Exception {
		// 전체 데이터 갯수 가져오는 처리를 해야 시작 줄과 끝 줄이 계산이 된다.
		// 없으면 데이터를 가져오지 않는다.
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		System.out.println("NoticeService.list().pageObject - " + pageObject);
		return mapper.list(pageObject);
	}

	// 2. view
	public NoticeVO view(long no) throws Exception {
		System.out.println("BoardController.view().no - " + no);
		return mapper.view(no);
	}

	// 3. write
	public int write(NoticeVO vo) throws Exception {
		System.out.println("noticeController.write().vo" + vo);
		return mapper.write(vo);
	}

	// 4. update
	public int update(NoticeVO vo) throws Exception {
		System.out.println("noticeController.update().vo" + vo);
		return mapper.update(vo);
	}

	// 5. delete
	public int delete(long no) throws Exception {
		System.out.println("noticeController.delete().vo" + no);
		return mapper.delete(no);
	}
}
