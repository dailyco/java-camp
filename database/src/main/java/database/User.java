package database;

public class User {
	String nickname;
	String id;
	String password;
	String gender;
	
	User() {
		
	}
	
	public void set(String nickname, String id, String password, String gender) {
		this.nickname = nickname;
		this.id = id;
		this.password = password;
		this.gender = gender;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
}
