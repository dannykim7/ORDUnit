package com.webjjang.image.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.webjjang.image.mapper.ImageMapper;
import com.webjjang.image.vo.ImageVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
// 컨트롤러 -> 서비스 -> 매퍼
public class ImageService {
	
	// 자동 DI
	@Inject
	private ImageMapper mapper;
	// 컨트롤러에서만 파라미터로 모델을 받아요
 public List<ImageVO> list(PageObject pageObject) throws Exception{
	log.info(pageObject);
	 // 페이지 처리 정보를 계산한다 -> 안하면 데이터가 안나옴
	 pageObject.setTotalRow(mapper.getTotalRow(pageObject));
	 return mapper.list(pageObject);
 }
 public ImageVO view(long no) throws Exception{
	 log.info(log);
	return mapper.view(no);
 }
 public int changeImage(ImageVO vo) throws Exception{
	 log.info(vo);
	 return mapper.changeImage(vo);
 }
 public int write(ImageVO vo) throws Exception{
	 log.info(vo);
	 return mapper.write(vo);
 }
 public int update(ImageVO vo) throws Exception{
	 log.info(vo);
	 return mapper.update(vo);
 }
 public int delete(long no) throws Exception{
	 log.info(no);
	 return mapper.delete(no);
 }

}
