package com.webjjang.image.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// lombok 라이브러리가 저장할 당시(컴파일할때) 
// - 생성자, setter(), getter(), toString()를 자동으로 만들어준다
@SuppressWarnings("unused")
@Data
public class ImageVO {
private long no;
private String title;
private String content;
private String id;
private String name;
// @DateTimeFormat(pattern = "yyyy-MM-dd")
// 데이터 타입의 형태가 필요한 경우는 날짜 데이터를 입력받는 경우에만 필요하다.
private Date writeDate;
// DB 쪽에 저장할 때 쓰는거
private String fileName;
// 파일 올릴때 쓰는거
//write.jsp 에서 인풋태그에 이미지 파일로 맞춰놔서 MultipartFile 타입으로 넣어야 한다 
private MultipartFile image;
private String deleteImage;
}
