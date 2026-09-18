package com.ssafy.pjt1.user;

public final class UserManagerImpl implements IUserManager {
    private static final UserManagerImpl INSTANCE = new UserManagerImpl();
    private final UserRepository repository = new UserRepository();
    private String loggedInUserId;
    private UserManagerImpl() { }
    public static UserManagerImpl getInstance() { return INSTANCE; }

    @Override public void register(String id, String password, String name, int age,
                                   double height, double weight, String disease) {
        if (isLoggedIn()) throw new IllegalStateException("로그아웃 후 가입해 주세요.");
        id = required(id, "아이디");
        if (id.chars().anyMatch(Character::isWhitespace))
            throw new IllegalArgumentException("아이디에는 공백을 사용할 수 없습니다.");
        validatePassword(password);
        UserDomain profile = profile(id, name, age, height, weight, disease);
        if (repository.findById(id) != null)
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        repository.add(new UserRepository.Account(profile, PasswordUtil.hash(password)));
    }
    @Override public void login(String id, String password) {
        if (isLoggedIn()) throw new IllegalStateException("이미 로그인되어 있습니다.");
        UserRepository.Account account = repository.findById(required(id, "아이디"));
        if (account == null || !PasswordUtil.matches(password, account.passwordHash))
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        loggedInUserId = account.profile.getId();
    }
    @Override public void logout() { loggedInUserId = null; }
    @Override public boolean isLoggedIn() { return loggedInUserId != null; }
    @Override public UserDomain getMyInfo() { return current().profile; }
    @Override public void updateMyInfo(String name, int age, double height,
                                       double weight, String disease) {
        UserRepository.Account account = current();
        account.profile = profile(account.profile.getId(), name, age, height, weight, disease);
    }
    @Override public void changePassword(String currentPassword, String newPassword) {
        UserRepository.Account account = verified(currentPassword);
        validatePassword(newPassword);
        account.passwordHash = PasswordUtil.hash(newPassword);
    }
    @Override public void withdraw(String currentPassword) {
        repository.delete(verified(currentPassword));
        logout();
    }
    private UserRepository.Account current() {
        if (!isLoggedIn()) throw new IllegalStateException("로그인이 필요합니다.");
        UserRepository.Account account = repository.findById(loggedInUserId);
        if (account == null) throw new IllegalStateException("회원 정보를 찾을 수 없습니다.");
        return account;
    }
    private UserRepository.Account verified(String password) {
        UserRepository.Account account = current();
        if (!PasswordUtil.matches(password, account.passwordHash))
            throw new IllegalArgumentException("현재 비밀번호가 올바르지 않습니다.");
        return account;
    }
    private static UserDomain profile(String id, String name, int age, double height,
                                      double weight, String disease) {
        name = required(name, "이름");
        if (age < 1 || age > 150) throw new IllegalArgumentException("나이는 1~150으로 입력해 주세요.");
        if (!Double.isFinite(height) || height <= 0 || height > 300)
            throw new IllegalArgumentException("키는 0 초과 300 이하로 입력해 주세요.");
        if (!Double.isFinite(weight) || weight <= 0 || weight > 1000)
            throw new IllegalArgumentException("몸무게는 0 초과 1000 이하로 입력해 주세요.");
        return new UserDomain(id, name, age, height, weight, disease == null ? "" : disease.trim());
    }
    private static String required(String value, String label) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException(label + "을(를) 입력해 주세요.");
        return value.trim();
    }
    private static void validatePassword(String password) {
        if (password == null || password.trim().isEmpty() || password.length() < 8)
            throw new IllegalArgumentException("비밀번호는 공백만으로 구성할 수 없으며 8자 이상이어야 합니다.");
    }
}
