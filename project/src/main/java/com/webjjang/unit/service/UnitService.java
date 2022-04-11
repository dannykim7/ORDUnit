package com.webjjang.unit.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.webjjang.unit.mapper.UnitMapper;
import com.webjjang.unit.vo.UnitVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UnitService {
	// 자동 DI
	@Inject
	private UnitMapper mapper;

	// 컨트롤러에서만 파라미터로 모델을 받아요
	public List<UnitVO> list(PageObject pageObject) throws Exception {
		log.info(pageObject);
		// 페이지 처리 정보를 계산한다 -> 안하면 데이터가 안나옴
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		return mapper.list(pageObject);
	}

	public UnitVO view(long no) throws Exception {
		log.info(log);
		return mapper.view(no);
	}

	public int changeUnit(UnitVO vo) throws Exception {
		log.info(vo);
		return mapper.changeUnit(vo);
	}

	public int write(UnitVO vo) throws Exception {
		log.info(vo);
		return mapper.write(vo);
	}

	public int update(UnitVO vo) throws Exception {
		log.info(vo);
		return mapper.update(vo);
	}

	public int delete(long no) throws Exception {
		log.info(no);
		return mapper.delete(no);
	}

}
