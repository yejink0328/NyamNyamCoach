package com.ssafy.pjt1;

import java.util.List;

public interface DietManager {
	// CRUD
	// 식단 기록 추가
	public abstract void add(DietDomain dietdomain);
	
	// 식단 ID를 활용하여 식단 기록 내용을 상세 조회
	DietDomain[] getAll();
	
	public abstract DietDomain getDietdomain(int dietId);
	
	// 식단 이름으로 검색
	public abstract DietDomain[] searchFoodName(String foodName);
	
	// 식단 수정
	public abstract void update(DietDomain dietdomain) throws DietDomainNotFoundException;
	
	// 식단 삭제
	public abstract boolean delete(int dietId);
	
	// 저장`
	public abstract void saveData();
	
	// 불러오기
	public abstract void loadData();
	
}
