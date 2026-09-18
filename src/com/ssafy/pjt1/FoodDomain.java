package com.ssafy.pjt1;

public class FoodDomain {
	private int dietId;
	private String foodName;
	private int calories;
	private int protein;
	private int carbs;
	private int fat;
	
	public FoodDomain() {}
	
	public FoodDomain(int dietId, String foodName) {
		this.dietId = dietId;
		this.foodName = foodName;
	}
	
	public FoodDomain(int dId, String foodName, int calories, int protein, int carbs, int fat) {
		this.dietId = dId;
		this.foodName = foodName;
		this.calories = calories;
		this.protein = protein;
		this.carbs = carbs;
		this.fat = fat;
	}

	public int getdIdName() {
		return dietId;
	}

	public void setdId(int dId) {
		this.dietId = dId;
	}
	
	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	@Override
	public String toString() {
		return "FoodDomain [dietId=" + dietId +" foodName=" + foodName + ", calories=" + calories + ", protein=" + protein + ", carbs="
				+ carbs + ", fat=" + fat + "]";
	}
	
}
