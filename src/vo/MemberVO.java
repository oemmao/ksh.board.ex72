package vo;

public class MemberVO {
	private String userID;
	private String pwd;
	private String name;
	private String phone;
	private String address;
	private int result;
	
	public MemberVO(String userID, String pwd, String name, String phone, String address) {
		this.userID = userID;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public MemberVO(String userID, String pwd) {
		this.userID = userID;
		this.pwd = pwd;
	}
	public MemberVO(String userID) {
		this.userID = userID;
	}
	public MemberVO() {
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
