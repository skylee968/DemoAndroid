package studio.orange.mobile.reader.entity;

import com.google.gson.annotations.SerializedName;

public class UserEntity {

	@SerializedName("_id")
	private String id;

	private String alias;

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	private String birthday;


	private String status;

	@SerializedName("male")
	private boolean gender;

	@SerializedName("balance")
	private int totalCoins;

	@SerializedName("blocked")
	private boolean isBlocked;

	@SerializedName("date_created")
	private String dateCreated;


	private String deviceId;
	public UserEntity(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotalCoins() {
		return totalCoins;
	}
	public void setTotalCoins(int totalCoins) {
		this.totalCoins = totalCoins;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
}
