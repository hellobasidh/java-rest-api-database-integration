package com.example.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class HealthData {

	@Id
	@NotNull
	@Valid
	private Long userId;

	@NotNull
	@Valid
	private String personName;

	@NotNull
	@Valid
	private String highFever;

	@NotNull
	@Valid
	private String dryCough;

	@NotNull
	@Valid
	private String breathProblem;

	@NotNull
	@Valid
	private String travelOutsideCountry;

	@NotNull
	@Valid
	private String travelOutsideState;

	@NotNull
	@Valid
	private String travelOutsideDistrict;

	@NotNull
	@Valid
	private String cold;

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

	public String getHighFever() {
		return highFever;
	}

	public void setHighFever(String highFever) {
		this.highFever = highFever;
	}

	public String getDryCough() {
		return dryCough;
	}

	public void setDryCough(String dryCough) {
		this.dryCough = dryCough;
	}

	public String getBreathProblem() {
		return breathProblem;
	}

	public void setBreathProblem(String breathProblem) {
		this.breathProblem = breathProblem;
	}

	public String getCold() {
		return cold;
	}

	public void setCold(String cold) {
		this.cold = cold;
	}

	public String getTravelOutsideCountry() {
		return travelOutsideCountry;
	}

	public void setTravelOutsideCountry(String travelOutsideCountry) {
		this.travelOutsideCountry = travelOutsideCountry;
	}

	public String getTravelOutsideState() {
		return travelOutsideState;
	}

	public void setTravelOutsideState(String travelOutsideState) {
		this.travelOutsideState = travelOutsideState;
	}

	public String getTravelOutsideDistrict() {
		return travelOutsideDistrict;
	}

	public void setTravelOutsideDistrict(String travelOutsideDistrict) {
		this.travelOutsideDistrict = travelOutsideDistrict;
	}

	@Override
	public String toString() {
		return "HealthData [userId=" + userId + ", personName=" + personName + ", highFever=" + highFever
				+ ", dryCough=" + dryCough + ", breathProblem=" + breathProblem + ", travelOutsideCountry="
				+ travelOutsideCountry + ", travelOutsideState=" + travelOutsideState + ", travelOutsideDistrict="
				+ travelOutsideDistrict + ", cold=" + cold + "]";
	}

}
