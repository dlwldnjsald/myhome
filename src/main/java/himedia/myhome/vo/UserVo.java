package himedia.myhome.vo;

import java.util.Date; //util.date임포트해줘야함 

public class UserVo {

	//필드 선언
	private Long no; //wrapper type Long
	private String name;
	private String password;
	private String email;
	private String gender;
	private Date createdAt;
	
	
	//기본 생성자 
	public UserVo () {
		
	}

	//전체 생성자
	public UserVo(Long no, String name, String password, String email, String gender, Date createdAt) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.createdAt = createdAt;
	}

	//사용할 생성자 : no는 pk니 뺐고, date도 뺐음
	public UserVo(String name, String password, String email, String gender) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.gender = gender;
	}
	
	
	//getters,setters
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	

	//toString override : 문자열 형태로 출력해주는 toString
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", password=" + password + ", email=" + email + ", gender="
				+ gender + ", createdAt=" + createdAt + "]";
	}
	
	

		
}
