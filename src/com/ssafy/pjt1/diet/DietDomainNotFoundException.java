package com.ssafy.pjt1.diet;

public class DietDomainNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private int dietId;
	public int getDietId() {
		return dietId;
	}
	
	public DietDomainNotFoundException(int dietId) {
		super(dietId + "번호의 식단은 존재하지 않습니다.");
		this.dietId = dietId;
	}
}
