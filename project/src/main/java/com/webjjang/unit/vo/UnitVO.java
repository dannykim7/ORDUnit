package com.webjjang.unit.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UnitVO {
private long no;
private String id;
private String name;
private String character;
private String image;
private String comb;
private String dmg;
private String sound;
private String skill;

private MultipartFile fileName;
private String deleteImage;
}
