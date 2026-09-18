package com.ssafy.pjt1.diet;

import java.util.List;

public interface DietManager {
	
	// CRUD
	// F201. 식단 기록 추가
	void add(DietDomain diet);
	
	// F202. 전체 식단 조회
	List<DietDomain> getAll();
	
	// F202. 특정 사용자의 식단 조회
		List<DietDomain> getByUserId(String userId);
	
	// F202. 식단 ID를 이용한 상세 조회
	DietDomain getById(int dietId) throws DietDomainNotFoundException;

	// F202. 특정 사용자의 식단 상세 조회
	DietDomain getById(String userId, int dietId) throws DietDomainNotFoundException;

	// F203. 식단 수정
	void update(DietDomain diet) throws DietDomainNotFoundException;
	
	// F204. 식단 삭제
	void delete(int dietId) throws DietDomainNotFoundException;
	
	// 식단 검색
	List<DietDomain> searchFoodName(String foodName);
	
}
