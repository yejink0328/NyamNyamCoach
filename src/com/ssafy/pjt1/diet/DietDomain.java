package com.ssafy.pjt1.diet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DietDomain {
	
	private int dietId;
	private String userId;
	private LocalDate date;
	private String type;
	private List<FoodDomain> foods = new ArrayList<>();
	
	public DietDomain() {}
	
	public DietDomain(String userId, LocalDate date, String type) {
		this.userId = userId;
		this.date = date;
		this.type = type;
	}
	
	public DietDomain(String userId, LocalDate date, String type, List<FoodDomain> foods) {
		this.userId = userId;
		this.date = date;
		this.type = type;
		this.foods = foods;
	}
	

	public int getDietId() {
		return dietId;
	}

	public void setDietId(int dietId) {
		this.dietId = dietId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<FoodDomain> getFoods() {
		return foods;
	}
	
	public void setFoods(List<FoodDomain> foods) {
		this.foods = foods;
	}

	@Override
	public String toString() {
		return "DietDomain [dietId=" + dietId
				+  ", userId=" + userId
				+ ", date=" + date
				+ ", type=" + type
				+", foods=" + foods
				+ "]";
	}	
	
	
}
