package com.ssafy.pjt1.diet;

import java.util.ArrayList;
import java.util.List;

public class DietRepository {
	private final List<DietDomain> diets = new ArrayList<>();
	
	private int nextId = 1;

	public void save(DietDomain diet) {
		diet.setDietId(nextId++);
		diets.add(diet);
	}
}

