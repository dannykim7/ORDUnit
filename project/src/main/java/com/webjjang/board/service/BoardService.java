package com.webjjang.board.service;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Mapper;
// 서비스 자동 생성
import org.springframework.stereotype.Service;

import com.webjjang.board.mapper.BoardMapper;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

// @Controller, @Service, @Reopsitory, @Component, @RestController 
//-> root-context.xml, servlet-context.xml에 scan 패키지 안쪽에 존재
@Service
@Log4j
public class BoardService {

	
	// 생성해서 넣어준다
		@Inject
	private BoardMapper mapper;

	// 1. list
	public List<BoardVO> list(PageObject pageObject) throws Exception{
		// 전체 데이터 갯수 가져오는 처리를 해야 시작 줄과 끝 줄이 계산이 된다.
		// 없으면 데이터를 가져오지 않는다.
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		// log(pageObject)
		log.info(pageObject);
		return mapper.list(pageObject);
	}
	// 2. view
	public BoardVO view(long no, int inc) throws Exception{
		// 전체 데이터 갯수 가져오는 처리를 해야 시작 줄과 끝 줄이 계산이 된다.
		// 없으면 데이터를 가져오지 않는다.
		// log BoardController.view().no, inc
		log.info(no);
		log.info(inc);
		if (inc == 1)
			mapper.increase(no);
		return mapper.view(no);
	}	
	// 3. write
	public int write(BoardVO vo) throws Exception {
		log.info(vo);
		return mapper.write(vo);
	}
	// 4. update
	public int update(BoardVO vo) throws Exception{
		log.info(vo);
		return mapper.update(vo);
	}
	// 5. delete
	public int delete(long no) throws Exception{
		log.info(no);
		System.out.println("BoardController.delete().vo" + no);
		return mapper.delete(no);
	}
}
