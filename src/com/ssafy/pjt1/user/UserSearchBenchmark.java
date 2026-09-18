package com.ssafy.pjt1.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/** 독립 실행용 비교 실험. 실제 회원 데이터와 비밀번호 검증을 사용하지 않는다. */
public final class UserSearchBenchmark {
    private static volatile long sink;
    private static final int QUERIES = 2_000;
    private static final int ROUNDS = 7;

    public static void main(String[] args) {
        System.out.println("Java: " + System.getProperty("java.version"));
        System.out.println("2000 lookups/batch; 3 warmups; median of 7 rounds; fixed random seed");
        System.out.println("members,case,list_ms,hash_ms,speedup");
        for (int size : new int[] {1_000, 10_000, 100_000}) {
            List<UserRepository.Account> list = new ArrayList<>();
            UserRepository repository = new UserRepository();
            for (int i = 0; i < size; i++) {
                UserRepository.Account account = new UserRepository.Account(
                    new UserDomain("user" + i, "Test", 20, 170, 60, ""), "BENCHMARK_ONLY");
                list.add(account);
                repository.add(account);
            }
            for (boolean hit : new boolean[] {true, false}) {
                Random random = new Random(20260918L);
                String[] queries = new String[QUERIES];
                for (int i = 0; i < queries.length; i++)
                    queries[i] = "user" + (hit ? random.nextInt(size) : size + random.nextInt(size));
                // 측정 전에 두 구현의 검색 결과가 같은 객체인지 확인한다.
                for (String id : queries) {
                    if (linear(list, id) != repository.findById(id))
                        throw new AssertionError("Different result: " + id);
                }
                for (int i = 0; i < 3; i++) {
                    measureList(list, queries);
                    measureHash(repository, queries);
                }
                long[] listTimes = new long[ROUNDS], hashTimes = new long[ROUNDS];
                for (int i = 0; i < ROUNDS; i++) {
                    // 실행 순서의 영향을 줄이기 위해 번갈아 측정한다.
                    if (i % 2 == 0) {
                        listTimes[i] = measureList(list, queries);
                        hashTimes[i] = measureHash(repository, queries);
                    } else {
                        hashTimes[i] = measureHash(repository, queries);
                        listTimes[i] = measureList(list, queries);
                    }
                }
                Arrays.sort(listTimes);
                Arrays.sort(hashTimes);
                double listMs = listTimes[ROUNDS / 2] / 1_000_000.0;
                double hashMs = hashTimes[ROUNDS / 2] / 1_000_000.0;
                System.out.printf(Locale.ROOT, "%d,%s,%.4f,%.4f,%.2f%n",
                    size, hit ? "hit" : "miss", listMs, hashMs, listMs / hashMs);
            }
        }
    }
    private static UserRepository.Account linear(List<UserRepository.Account> list, String id) {
        for (UserRepository.Account account : list)
            if (account.profile.getId().equals(id)) return account;
        return null;
    }
    private static long measureList(List<UserRepository.Account> list, String[] queries) {
        long sum = 0, start = System.nanoTime();
        for (String id : queries) {
            UserRepository.Account account = linear(list, id);
            if (account != null) sum += account.profile.getId().length();
        }
        long elapsed = System.nanoTime() - start;
        sink = sum;
        return elapsed;
    }
    private static long measureHash(UserRepository repository, String[] queries) {
        long sum = 0, start = System.nanoTime();
        for (String id : queries) {
            UserRepository.Account account = repository.findById(id);
            if (account != null) sum += account.profile.getId().length();
        }
        long elapsed = System.nanoTime() - start;
        sink = sum;
        return elapsed;
    }
}
