package com.ssafy.pjt1.diet;

import java.util.ArrayList;

public class DietDomain {
	
	private static final long serialVersionUID = 1L;
	
	private static int number = 1;
	private int dietId;
	private String date;
	private String type;
	private ArrayList<FoodDomain> foods;
	
	public DietDomain() {}
	
	public DietDomain(String date, String type) {
		this.dietId = number++;
		this.date = date;
		this.type = type;
	}
	
	public DietDomain(String date, String type, ArrayList<FoodDomain> foods) {
		this.dietId = number++;
		this.date = date;
		this.type = type;
		this.foods = foods;
	}
	
	public DietDomain(int dietId, String date, String type, ArrayList<FoodDomain> foods) {
		number = dietId+1;
		this.dietId = dietId;
		this.date = date;
		this.type = type;
		this.foods = foods;
	}

	public static int getNumber() {
		return number;
	}

	public static void setNumber(int number) {
		DietDomain.number = number;
	}

	public int getDietId() {
		return dietId;
	}

	public void setDietId(int dietId) {
		this.dietId = dietId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<FoodDomain> getFoods() {
		return foods;
	}
	
	public void setFoods(ArrayList<FoodDomain> foods) {
		this.foods = foods;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DietDomain [dietId=" + dietId + ", date=" + date + ", type=" + type +", foods=" + foods
				+ "]";
	}	
	
	
}
