package com.webjjang.notice.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class NoticeVO {
private long no;
private String title, content;
private Date startDate, endDate, writeDate, updateDate;

public long getNo() {
	return no;
}
public void setNo(long no) {
	this.no = no;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getStartDate() {
	return startDate;
}
// 날짜형을 입력할 때 -> 형태에 맞는 문자열로 만들어주면 날짜형 데이터로 변경해서 받아온다
// 400번 오류가 나면 데이터를 제대로 전달되지 않고 있다.
@DateTimeFormat(pattern = "yyyy-MM-dd")
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
@DateTimeFormat(pattern = "yyyy-MM-dd")
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
public Date getWriteDate() {
	return writeDate;
}
public void setWriteDate(Date writeDate) {
	this.writeDate = writeDate;
}
public Date getUpdateDate() {
	return updateDate;
}
public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}
@Override
public String toString() {
	return "NoticeVO [no=" + no + ", title=" + title + ", content=" + content + ", startDate=" + startDate
			+ ", endDate=" + endDate + ", writeDate=" + writeDate + ", updateDate=" + updateDate + "]";
}

}
