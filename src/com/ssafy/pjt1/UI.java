package com.ssafy.pjt1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UI {
	public static void main(String[] args) {
		
		DietManager dm = DietManagerImpl.getInstance();
		
		Scanner sc = new Scanner(System.in);
		int userAnswer = 0;
		String[] menu = {"1. 식단을 작성합니다.", "2. 작성한 식단을 조회합니다.", "3. 식단을 분석합니다.", "4. 프로그램을 종료합니다."};
		boolean isOpen = true;
		
		while (isOpen) {
			System.out.println("--------------------------");
			System.out.println("수행할 작업을 선택해 주세요.");
			System.out.println("--------------------------");
			for(int i=0; i < menu.length; i++) {
				System.out.println(menu[i]);
			}
			
			userAnswer = sc.nextInt()-1;
			
			switch (userAnswer) {
			case 0 : 
				//식단 작성
				System.out.println(menu[userAnswer]);
				System.out.println("날짜를 입력하세요. ex) 2026-01-01");
				String date = sc.next();
				System.out.println("아침, 점심, 저녁 중 언제 드셨나요? ex) 점심");
				String type = sc.next();
				
				DietDomain inputDiet = new DietDomain(date, type);
	
				dm.add(inputDiet);
				
				System.out.println("무엇을 드셨나요? ex) 현미밥");
				String food = sc.next();
				
				FoodDomain inputFood = new FoodDomain(inputDiet.getDietId(), food);
				
				System.out.println(inputDiet);
				System.out.println();
				break;
			case 1 : 
				//식단 조회
				System.out.println(menu[userAnswer]);
				
				System.out.println(Arrays.toString(dm.getAll()));
				
				System.out.println();
				break;
			case 2 : 
				//식단 수정
				System.out.println(menu[userAnswer]);
				break;
			case 3 : 
				System.out.println(menu[userAnswer]);
				isOpen = false;
				break;
			default:
				System.out.println("메뉴에 적힌 숫자로 입력해 주세요.");
			
			}
			
		}
	}
}
