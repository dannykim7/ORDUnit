package com.webjjang.unit.mapper;

import java.util.List;

import com.webjjang.unit.vo.UnitVO;
import com.webjjang.util.PageObject;

public interface UnitMapper  {
	// 1
	public List<UnitVO> list(PageObject pageObject) throws Exception;

	// 1-1
	public long getTotalRow(PageObject pageObject) throws Exception;

	// 2
	public UnitVO view(long no) throws Exception;
	
	// 2-1
	public int changeUnit(UnitVO vo) throws Exception;

	// 3
	public int write(UnitVO vo) throws Exception;

	// 4
	public int update(UnitVO vo) throws Exception;
	
	// 5
	public int delete(long no) throws Exception;

}
