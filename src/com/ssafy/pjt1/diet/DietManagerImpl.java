package com.ssafy.pjt1.diet;

import java.util.List;

public class DietManagerImpl implements DietManager {
	
	private final DietRepository repository;
	
	public DietManagerImpl() {
		repository = new DietRepository();
	}
	
	// F201. 식단 기록 추가
	@Override
	public void add(DietDomain diet) {
		repository.save(diet);
	}
	
	// F202. 전체 식단 조회
	@Override
	public List<DietDomain> getAll() {
		return repository.findAll();
	}
	
	// F202. 식단 ID를 이용한 상세 조회
	@Override
	public DietDomain getById(int dietId) throws DietDomainNotFoundException {
		DietDomain diet = repository.findById(dietId);
		
		if(diet == null) {
			throw new DietDomainNotFoundException(dietId);
		}
		
		return diet;
	}
	
	// F203. 식단 수정
	@Override
	public void update(DietDomain diet) throws DietDomainNotFoundException {
		DietDomain existing = repository.findById(diet.getDietId());
		
		if(existing == null) {
			throw new DietDomainNotFoundException(diet.getDietId());
		}
		
		repository.update(diet);
	}
	
	// F204. 식단 삭제
	@Override
	public void delete(int dietId) throws DietDomainNotFoundException {
		DietDomain existing = repository.findById(dietId);
		
		if(existing == null) {
			throw new DietDomainNotFoundException(dietId);
		}
		
		repository.delete(dietId);
	}
	
	
	// 식단 검색
	@Override
	public List<DietDomain> searchFoodName(String foodName) {
		return null;
	}

	

}
