package com.ssafy.pjt1.integration;

import java.time.LocalDate;

import com.ssafy.pjt1.diet.DietDomain;
import com.ssafy.pjt1.diet.DietDomainNotFoundException;
import com.ssafy.pjt1.diet.DietManager;
import com.ssafy.pjt1.diet.DietManagerImpl;
import com.ssafy.pjt1.user.IUserManager;
import com.ssafy.pjt1.user.UserDomain;
import com.ssafy.pjt1.user.UserManagerImpl;

public class UserDietIntegrationTest {

    public static void main(String[] args) throws DietDomainNotFoundException {

        System.out.println("========== User + Diet Integration Test ==========");

        // ========================================
        // 1. User 기능
        // ========================================
        System.out.println("\n[ 1. User 생성 및 로그인 ]");

        IUserManager userManager = UserManagerImpl.getInstance();

        String userId = "diet_test_user";
        String password = "password123";

        try {
            userManager.register(
                    userId,
                    password,
                    "테스트 사용자",
                    25,
                    170.0,
                    65.0,
                    ""
            );

            System.out.println("SUCCESS: 회원 가입");

        } catch (IllegalArgumentException e) {
            /*
             * Singleton UserManager를 사용하기 때문에
             * 테스트를 여러 번 실행하면 이미 등록된 ID일 수 있다.
             */
            System.out.println("회원이 이미 존재할 수 있음: " + e.getMessage());
        }

        // 로그인
        if (!userManager.isLoggedIn()) {
            userManager.login(userId, password);
        }

        if (userManager.isLoggedIn()) {
            System.out.println("SUCCESS: 로그인");
        } else {
            System.out.println("FAIL: 로그인");
            return;
        }


        // ========================================
        // 2. 현재 로그인 사용자 확인
        // ========================================
        System.out.println("\n[ 2. 현재 사용자 확인 ]");

        UserDomain user = userManager.getMyInfo();

        System.out.println("User ID: " + user.getId());
        System.out.println("User Name: " + user.getName());

        if (userId.equals(user.getId())) {
            System.out.println("SUCCESS: User ID 확인");
        } else {
            System.out.println("FAIL: User ID 불일치");
            return;
        }


        // ========================================
        // 3. Diet 생성
        // ========================================
        System.out.println("\n[ 3. User ID를 이용한 Diet 생성 ]");

        DietManager dietManager = new DietManagerImpl();

        DietDomain diet = new DietDomain(
                user.getId(),
                LocalDate.of(2026, 9, 18),
                "아침"
        );

        dietManager.add(diet);

        System.out.println("Diet:");
        System.out.println(diet);


        // ========================================
        // 4. User + Diet 연결 확인
        // ========================================
        System.out.println("\n[ 4. User + Diet 연결 확인 ]");

        DietDomain savedDiet = dietManager.getById(diet.getDietId());

        System.out.println("User ID: " + user.getId());
        System.out.println("Diet User ID: " + savedDiet.getUserId());

        if (user.getId().equals(savedDiet.getUserId())) {
            System.out.println("SUCCESS: User와 Diet 연결");
        } else {
            System.out.println("FAIL: User와 Diet의 ID가 일치하지 않음");
        }


        // ========================================
        // 5. 로그아웃
        // ========================================
        userManager.logout();

        if (!userManager.isLoggedIn()) {
            System.out.println("\nSUCCESS: 로그아웃");
        } else {
            System.out.println("\nFAIL: 로그아웃");
        }

        System.out.println("\n========== Integration Test Finished ==========");
    }
}
