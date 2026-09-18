package com.ssafy.pjt1.diet;

import java.util.ArrayList;
import java.util.List;

public class DietRepository {
	private final List<DietDomain> diets = new ArrayList<>();
	
	private int nextId = 1;

	// F201. 식단 저장
	public void save(DietDomain diet) {
		diet.setDietId(nextId++);
		diets.add(diet);
	}
	
	// F202. 전체 식단 조회
	public List<DietDomain> findAll() {
		return new ArrayList<>(diets);
	}
	
	// F202. 특정 사용자의 식단 조회
	public List<DietDomain> findByUserId(String userId) {
		List<DietDomain> result = new ArrayList<>();
		
		for (DietDomain diet: diets) {
			if(diet.getUserId().equals(userId)) {
				result.add(diet);
			}
		}
		
		return result;
	}
	
	// F202. ID로 식단 조회
	public DietDomain findById(int dietId) {
		for (DietDomain diet : diets) {
			if (diet.getDietId() == dietId) {
				return diet;
			}
		}
		
		return null;
	} 
	
	// F202. 특정 사용자의 식단 상세 조회
	public DietDomain findByUserIdAndDietId(String userId, int dietId) {
		for (DietDomain diet: diets) {
			if (diet.getDietId() == dietId
					&& diet.getUserId().equals(userId)) {
				return diet;
			}
		}
		
		return null;
	}
	
	// F203. 식단 수정
	public void update(DietDomain diet) {
		for (int i = 0; i<diets.size(); i++) {
			if(diets.get(i).getDietId() == diet.getDietId()) {
				diets.set(i, diet);
				return;
			}
		}
	}
	
	// F204. 식단 삭제
	public void delete(int dietId) {
		for(int i=0; i<diets.size(); i++) {
			if(diets.get(i).getDietId() == dietId) {
				diets.remove(i);
				return;
			}
		}
	}
	
}

