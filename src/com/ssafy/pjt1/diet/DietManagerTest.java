package com.ssafy.pjt1.diet;

import java.time.LocalDate;
import java.util.List;

public class DietManagerTest {

public static void main(String[] args) {

    DietManager manager = new DietManagerImpl();

    System.out.println("========== Diet CRUD Test ==========");

    // ========================================
    // F201. 식단 추가
    // ========================================
    System.out.println("\n[ F201. 식단 추가 ]");

    DietDomain breakfast = new DietDomain(
            "user1",
            LocalDate.of(2026, 9, 18),
            "아침"
    );

    DietDomain lunch = new DietDomain(
            "user1",
            LocalDate.of(2026, 9, 18),
            "점심"
    );

    DietDomain dinner = new DietDomain(
            "user2",
            LocalDate.of(2026, 9, 18),
            "저녁"
    );

    manager.add(breakfast);
    manager.add(lunch);
    manager.add(dinner);

    System.out.println("user1 아침 식단 ID: " + breakfast.getDietId());
    System.out.println("user1 점심 식단 ID: " + lunch.getDietId());
    System.out.println("user2 저녁 식단 ID: " + dinner.getDietId());

    if (breakfast.getDietId() == 1
            && lunch.getDietId() == 2
            && dinner.getDietId() == 3) {
        System.out.println("SUCCESS: 식단 추가 및 ID 생성");
    } else {
        System.out.println("FAIL: 식단 ID 생성 오류");
    }


    // ========================================
    // F202. 전체 식단 조회
    // ========================================
    System.out.println("\n[ F202. 전체 식단 조회 ]");

    List<DietDomain> diets = manager.getAll();

    System.out.println("조회된 식단 수: " + diets.size());

    for (DietDomain diet : diets) {
        System.out.println(diet);
    }

    if (diets.size() == 3) {
        System.out.println("SUCCESS: 전체 조회");
    } else {
        System.out.println("FAIL: 전체 조회");
    }


    // ========================================
    // F202. 특정 사용자의 식단 조회
    // ========================================
    System.out.println("\n[ F202. 특정 사용자의 식단 조회 ]");

    List<DietDomain> user1Diets = manager.getByUserId("user1");
    List<DietDomain> user2Diets = manager.getByUserId("user2");

    System.out.println("user1 식단 수: " + user1Diets.size());

    for (DietDomain diet : user1Diets) {
        System.out.println(diet);
    }

    System.out.println("user2 식단 수: " + user2Diets.size());

    for (DietDomain diet : user2Diets) {
        System.out.println(diet);
    }

    boolean user1QuerySuccess =
            user1Diets.size() == 2
            && user1Diets.stream()
                .allMatch(diet -> "user1".equals(diet.getUserId()));

    boolean user2QuerySuccess =
            user2Diets.size() == 1
            && user2Diets.stream()
                .allMatch(diet -> "user2".equals(diet.getUserId()));

    if (user1QuerySuccess && user2QuerySuccess) {
        System.out.println("SUCCESS: 사용자별 식단 조회");
    } else {
        System.out.println("FAIL: 사용자별 식단 조회");
    }


    // ========================================
    // F202. 식단 상세 조회
    // ========================================
    System.out.println("\n[ F202. 식단 상세 조회 ]");

    try {
        DietDomain foundDiet = manager.getById(1);

        System.out.println("조회 결과:");
        System.out.println(foundDiet);

        if (foundDiet.getDietId() == 1) {
            System.out.println("SUCCESS: ID 조회");
        } else {
            System.out.println("FAIL: 잘못된 식단 조회");
        }

    } catch (DietDomainNotFoundException e) {
        System.out.println("FAIL: 존재하는 식단을 찾지 못함");
    }


    // ========================================
    // F202. 특정 사용자의 식단 상세 조회
    // ========================================
    System.out.println("\n[ F202. 특정 사용자의 식단 상세 조회 ]");

    try {
        DietDomain foundDiet = manager.getById("user1", 1);

        System.out.println("조회 결과:");
        System.out.println(foundDiet);

        if (foundDiet.getDietId() == 1
                && "user1".equals(foundDiet.getUserId())) {
            System.out.println("SUCCESS: 사용자별 ID 조회");
        } else {
            System.out.println("FAIL: 잘못된 식단 조회");
        }

    } catch (DietDomainNotFoundException e) {
        System.out.println("FAIL: 존재하는 식단을 찾지 못함");
    }


    // ========================================
    // F202. 다른 사용자의 식단 접근 차단
    // ========================================
    System.out.println("\n[ F202. 다른 사용자의 식단 접근 차단 ]");

    try {
        // dietId=3은 user2의 식단
        manager.getById("user1", 3);

        System.out.println(
                "FAIL: 다른 사용자의 식단이 조회됨"
        );

    } catch (DietDomainNotFoundException e) {
        System.out.println(
                "SUCCESS: 다른 사용자의 식단 접근 차단"
        );
        System.out.println(e.getMessage());
    }


    // ========================================
    // 존재하지 않는 ID 조회 테스트
    // ========================================
    System.out.println("\n[ 예외 테스트 ]");

    try {
        manager.getById(999);

        System.out.println(
                "FAIL: 존재하지 않는 식단인데 예외가 발생하지 않음"
        );

    } catch (DietDomainNotFoundException e) {
        System.out.println(
                "SUCCESS: 존재하지 않는 식단 예외 처리"
        );
        System.out.println(e.getMessage());
    }


    // ========================================
    // F203. 식단 수정
    // ========================================
    System.out.println("\n[ F203. 식단 수정 ]");

    try {
        DietDomain updatedDiet = new DietDomain(
                "user1",
                LocalDate.of(2026, 9, 18),
                "저녁"
        );

        updatedDiet.setDietId(1);

        manager.update(updatedDiet);

        DietDomain result = manager.getById(1);

        System.out.println("수정 결과:");
        System.out.println(result);

        if ("저녁".equals(result.getType())) {
            System.out.println("SUCCESS: 식단 수정");
        } else {
            System.out.println("FAIL: 식단 수정");
        }

    } catch (DietDomainNotFoundException e) {
        System.out.println("FAIL: 수정할 식단을 찾지 못함");
    }


    // ========================================
    // F204. 식단 삭제
    // ========================================
    System.out.println("\n[ F204. 식단 삭제 ]");

    try {
        manager.delete(2);

        List<DietDomain> afterDelete = manager.getAll();

        System.out.println("삭제 후 식단 수: " + afterDelete.size());

        if (afterDelete.size() == 2) {
            System.out.println("SUCCESS: 식단 삭제");
        } else {
            System.out.println("FAIL: 식단 삭제");
        }

    } catch (DietDomainNotFoundException e) {
        System.out.println("FAIL: 삭제할 식단을 찾지 못함");
    }


    // ========================================
    // 존재하지 않는 ID 삭제 테스트
    // ========================================
    System.out.println("\n[ 삭제 예외 테스트 ]");

    try {
        manager.delete(999);

        System.out.println(
                "FAIL: 존재하지 않는 식단인데 예외가 발생하지 않음"
        );

    } catch (DietDomainNotFoundException e) {
        System.out.println(
                "SUCCESS: 존재하지 않는 식단 삭제 예외 처리"
        );
        System.out.println(e.getMessage());
    }


    // ========================================
    // 최종 결과
    // ========================================
    System.out.println("\n========== Test Finished ==========");
}


}