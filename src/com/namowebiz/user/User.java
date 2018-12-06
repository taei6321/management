package com.namowebiz.user;

public class User {
	private String user_id;			// Employee number 사번
	private String user_pw;			// password 비밀번호
	private String user_name;		// name 이름
	private String user_part;		// Department 부서
	private String user_rank;		// position 직급
	private String user_phone;		// phone number 핸드폰번호
	private String user_email;		// email 이메일
	private Boolean user_manager;	// Manager Status 관리자 유무
	private float user_holidays_use; // Number of vacation days 휴가사용일수
	private float user_holidays_total;	// Number of vacation days 총휴가일수
	private String user_produce_date;	// 입사 날
	private String user_resignation_date;// Update Date 업데이트 일
	private Boolean user_resignation; // 퇴사
	
	
	public Boolean getUser_resignation() {
		return user_resignation;
	}
	public void setUser_resignation(Boolean user_resignation) {
		this.user_resignation = user_resignation;
	}
	public float getUser_holidays_use() {
		return user_holidays_use;
	}
	public void setUser_holidays_use(float user_holidays_use) {
		this.user_holidays_use = user_holidays_use;
	}
	public float getUser_holidays_total() {
		return user_holidays_total;
	}
	public void setUser_holidays_total(float user_holidays_total) {
		this.user_holidays_total = user_holidays_total;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_part() {
		return user_part;
	}
	public void setUser_part(String user_part) {
		this.user_part = user_part;
	}
	public String getUser_rank() {
		return user_rank;
	}
	public void setUser_rank(String user_rank) {
		this.user_rank = user_rank;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public Boolean getUser_manager() {
		return user_manager;
	}
	public void setUser_manager(Boolean user_manager) {
		this.user_manager = user_manager;
	}
	public String getUser_produce_date() {
		return user_produce_date;
	}
	public void setUser_produce_date(String user_produce_date) {
		this.user_produce_date = user_produce_date;
	}
	public String getUser_resignation_date() {
		return user_resignation_date;
	}
	public void setUser_resignation_date(String user_resignation_date) {
		this.user_resignation_date = user_resignation_date;
	}
	
}
