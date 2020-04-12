package com.example.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class PersonData {

	@Id
	@NotNull
	private Long userId;
	@NotNull
	private String personName;
	@NotNull
	private Integer age;
	@NotNull
	private String documentNumber;
	@NotNull
	private String selfImage;
	@NotNull
	private String documentImage;
	@NotNull
	private String emailAddress;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getSelfImage() {
		return selfImage;
	}

	public void setSelfImage(String selfImage) {
		this.selfImage = selfImage;
	}

	public String getDocumentImage() {
		return documentImage;
	}

	public void setDocumentImage(String documentImage) {
		this.documentImage = documentImage;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "PersonData [userId=" + userId + ", personName=" + personName + ", age=" + age + ", documentNumber="
				+ documentNumber + ", selfImage=" + selfImage + ", documentImage=" + documentImage + ", emailAddress="
				+ emailAddress + "]";
	}

}
