package com.ssafy.pjt1.user;

import java.util.NoSuchElementException;
import java.util.Scanner;

/** 회원 기능 단독 실행 진입점. */
public final class UserTest {
    public static void main(String[] args) {
        new UserMenu(UserManagerImpl.getInstance(), new Scanner(System.in)).run();
    }
}

final class UserMenu {
    private final IUserManager manager;
    private final Scanner scanner;
    UserMenu(IUserManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }
    void run() {
        while (true) {
            System.out.println(manager.isLoggedIn()
                ? "\n1. 내 정보 조회  2. 내 정보 수정  3. 비밀번호 변경  4. 회원 탈퇴  5. 로그아웃  0. 종료"
                : "\n1. 회원가입  2. 로그인  0. 종료");
            try {
                String choice = read("선택: ");
                if ("0".equals(choice)) { manager.logout(); return; }
                if (manager.isLoggedIn()) memberAction(choice);
                else guestAction(choice);
            } catch (NoSuchElementException e) {
                manager.logout();
                System.out.println("입력이 종료되어 프로그램을 종료합니다.");
                return;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("안내: " + e.getMessage());
            }
        }
    }
    private void guestAction(String choice) {
        switch (choice) {
        case "1":
            String id = read("아이디: ");
            String password = read("비밀번호(8자 이상): ");
            if (!password.equals(read("비밀번호 확인: ")))
                throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
            String name = read("이름: ");
            int age = integer("나이: ");
            double height = decimal("키(cm): ");
            double weight = decimal("몸무게(kg): ");
            String disease = read("질환(없으면 Enter): ");
            manager.register(id, password, name, age, height, weight, disease);
            System.out.println("회원가입이 완료되었습니다. 로그인해 주세요.");
            break;
        case "2":
            manager.login(read("아이디: "), read("비밀번호: "));
            System.out.println("로그인 완료");
            break;
        default: System.out.println("메뉴 번호를 확인해 주세요.");
        }
    }
    private void memberAction(String choice) {
        switch (choice) {
        case "1": System.out.println(manager.getMyInfo()); break;
        case "2":
            System.out.println("현재 정보: " + manager.getMyInfo());
            System.out.println("수정 후 정보를 모두 입력해 주세요. 아이디는 변경되지 않습니다.");
            String name = read("이름: ");
            int age = integer("나이: ");
            double height = decimal("키(cm): ");
            double weight = decimal("몸무게(kg): ");
            String disease = read("질환(없으면 Enter): ");
            manager.updateMyInfo(name, age, height, weight, disease);
            System.out.println("회원 정보가 수정되었습니다.");
            break;
        case "3":
            String current = read("현재 비밀번호: ");
            String next = read("새 비밀번호(8자 이상): ");
            if (!next.equals(read("새 비밀번호 확인: ")))
                throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
            manager.changePassword(current, next);
            System.out.println("비밀번호가 변경되었습니다.");
            break;
        case "4":
            if (!"탈퇴".equals(read("회원 정보를 삭제하려면 '탈퇴' 입력: "))) {
                System.out.println("탈퇴를 취소했습니다.");
                break;
            }
            manager.withdraw(read("현재 비밀번호: "));
            System.out.println("회원 탈퇴 및 로그아웃이 완료되었습니다.");
            break;
        case "5": manager.logout(); System.out.println("로그아웃 완료"); break;
        default: System.out.println("메뉴 번호를 확인해 주세요.");
        }
    }
    private String read(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    private int integer(String prompt) {
        while (true) {
            try { return Integer.parseInt(read(prompt).trim()); }
            catch (NumberFormatException e) { System.out.println("정수를 입력해 주세요."); }
        }
    }
    private double decimal(String prompt) {
        while (true) {
            try {
                double value = Double.parseDouble(read(prompt).trim());
                if (!Double.isFinite(value)) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) { System.out.println("유한한 숫자를 입력해 주세요."); }
        }
    }
}
