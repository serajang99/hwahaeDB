package pureView.dto;

import java.util.Date;

public class LoginDto {
	
	private int login_num;
	private Date login_date, logout_date;
	private String member_id;
	public LoginDto(int login_num, Date login_date, Date logout_date, String member_id) {
		super();
		this.login_num = login_num;
		this.login_date = login_date;
		this.logout_date = logout_date;
		this.member_id = member_id;
	}
	public int getLogin_num() {
		return login_num;
	}
	public void setLogin_num(int login_num) {
		this.login_num = login_num;
	}
	public Date getLogin_date() {
		return login_date;
	}
	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}
	public Date getLogout_date() {
		return logout_date;
	}
	public void setLogout_date(Date logout_date) {
		this.logout_date = logout_date;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	@Override
	public String toString() {
		return "login_num=" + login_num + ", login_date=" + login_date + ", logout_date=" + logout_date
				+ ", member_id=" + member_id;
	}
	
	

}
