package com.ssafy.pjt1.diet;

import java.util.ArrayList;
import java.util.List;

public class DietManagerImpl implements DietManager {
	//다이어트, 푸드 리스트 별도 관리
	
	private List<DietDomain> dietList = new ArrayList<DietDomain>();
	private List<FoodDomain> foodList = new ArrayList<FoodDomain>();
	
	private static DietManager instance = new DietManagerImpl();
	
	private DietManagerImpl() {
		
	}
	
	public static DietManager getInstance() {
		return instance;
	}
	
	@Override
	public void add(DietDomain dietdomain) {
		dietList.add(dietdomain);
	}

	@Override
	public DietDomain[] getAll() {
		DietDomain[] result = new DietDomain[dietList.size()];
		
		for (int i = 0; i < dietList.size(); i++) {
			result[i] = dietList.get(i);
		}
		return result;
	}

	@Override
	public DietDomain getDietdomain(int dietId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DietDomain[] searchFoodName(String foodName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(DietDomain dietdomain) throws DietDomainNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(int dietId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		
	}

}
