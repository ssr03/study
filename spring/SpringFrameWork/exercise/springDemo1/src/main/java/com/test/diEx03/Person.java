package com.test.diEx03;

public class Person {

	private String name;
	private String gender;
	private String age;
	private String idNo;
	
	//기본 생성자
	public Person() {}
	
	//인자 생성자
	public Person(String name, String gender, String age, String idNo) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.idNo = idNo;
	}

	//setter, getter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
}
