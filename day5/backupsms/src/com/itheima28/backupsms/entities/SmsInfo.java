package com.itheima28.backupsms.entities;

public class SmsInfo {

	private int id;
	private String address;
	private long date;
	private int type;
	private String body;
	public SmsInfo(int id, String address, long date, int type, String body) {
		super();
		this.id = id;
		this.address = address;
		this.date = date;
		this.type = type;
		this.body = body;
	}
	@Override
	public String toString() {
		return "SmsInfo [id=" + id + ", address=" + address + ", date=" + date
				+ ", type=" + type + ", body=" + body + "]";
	}
	public SmsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
