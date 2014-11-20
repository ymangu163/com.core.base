package com.itheima28.neteasedemo.domain;

/**
 * @author andong
 * ������Ϣʵ����
 */
public class NewInfo {

	private String title; // ����
	private String detail; // ��ϸ
	private Integer comment; // ��������
	private String imageUrl; // ͼƬ����
	@Override
	public String toString() {
		return "NewInfo [title=" + title + ", detail=" + detail + ", comment="
				+ comment + ", imageUrl=" + imageUrl + "]";
	}
	public NewInfo(String title, String detail, Integer comment, String imageUrl) {
		super();
		this.title = title;
		this.detail = detail;
		this.comment = comment;
		this.imageUrl = imageUrl;
	}
	public NewInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
