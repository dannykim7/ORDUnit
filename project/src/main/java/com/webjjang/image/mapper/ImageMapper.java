package com.webjjang.image.mapper;

import java.util.List;

import com.webjjang.image.vo.ImageVO;
import com.webjjang.util.PageObject;

// invalid bound statement가 xml 부분에 떴으면
// src/main/resources 들어가서 해당 매퍼.xml이 제대로 된 폴더 분류로 들어갔는지 확인하거나 오타처리
// 난 3월 3일날 폴더 하나 덜 만들었다가 1시간동안 헤맸다ㅋㅋㅋ
// ImageMapper의 아이디에 맞는 추상 메소드와 맞춤
public interface ImageMapper {
	// 1
	public List<ImageVO> list(PageObject pageObject) throws Exception;

	// 1-1
	public long getTotalRow(PageObject pageObject) throws Exception;

	// 2
	public ImageVO view(long no) throws Exception;
	
	// 2-1
	public int changeImage(ImageVO vo) throws Exception;

	// 3
	public int write(ImageVO vo) throws Exception;

	// 4
	public int update(ImageVO vo) throws Exception;
	
	// 5
	public int delete(long no) throws Exception;

}
