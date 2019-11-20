package com.itsync.bean;

/**
 * author : wuchengya e-mail : wucy1205@yeah.net date : 2019/11/18 time :17:34
 * desc :ohuo version: 1.0
 */
public class DataBean {

	private String db_image;
	private String name;
	private int type;
	private String person_id;
	private int age;
	private String gender;
	private String title;

	public DataBean(String db_image, String name, int type, String person_id, int age, String gender, String title) {
		super();
		this.db_image = db_image;
		this.name = name;
		this.type = type;
		this.person_id = person_id;
		this.age = age;
		this.gender = gender;
		this.title = title;
	}

	public String getDb_image() {
		return db_image;
	}

	public void setDb_image(String db_image) {
		this.db_image = db_image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
