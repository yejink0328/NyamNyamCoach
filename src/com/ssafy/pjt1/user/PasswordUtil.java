package com.ssafy.pjt1.user;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/** JDK 기본 비밀번호 보호 기능. 과제용 검색 알고리즘과는 별개이다. */
final class PasswordUtil {
    private static final int ITERATIONS = 600_000;
    private static final SecureRandom RANDOM = new SecureRandom();
    static String hash(String password) {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt) + ":"
            + Base64.getEncoder().encodeToString(derive(password, salt));
    }
    static boolean matches(String password, String stored) {
        if (password == null) return false;
        String[] parts = stored.split(":");
        return MessageDigest.isEqual(Base64.getDecoder().decode(parts[1]),
            derive(password, Base64.getDecoder().decode(parts[0])));
    }
    private static byte[] derive(String password, byte[] salt) {
        char[] chars = password.toCharArray();
        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, 256);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(spec).getEncoded();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("비밀번호 처리 기능을 사용할 수 없습니다.", e);
        } finally {
            spec.clearPassword();
            Arrays.fill(chars, '\0');
        }
    }
}
