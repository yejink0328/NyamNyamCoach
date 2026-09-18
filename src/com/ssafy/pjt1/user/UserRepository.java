package com.ssafy.pjt1.user;

import java.util.HashMap;
import java.util.Map;

/** 아이디를 키로 사용하는 해시 탐색 저장소. 조회/추가/삭제는 평균 O(1). */
final class UserRepository {
    static final class Account {
        UserDomain profile;
        String passwordHash;
        Account(UserDomain profile, String passwordHash) {
            this.profile = profile;
            this.passwordHash = passwordHash;
        }
    }
    private final Map<String, Account> users = new HashMap<>();
    Account findById(String id) {
        return users.get(id);
    }
    void add(Account account) {
        if (users.putIfAbsent(account.profile.getId(), account) != null)
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
    }
    void delete(Account account) {
        // 같은 아이디에 다른 객체가 등록되어 있다면 삭제하지 않는다.
        users.remove(account.profile.getId(), account);
    }
}
