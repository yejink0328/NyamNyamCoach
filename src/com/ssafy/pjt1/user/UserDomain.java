package com.ssafy.pjt1.user;

/** 외부에서 직접 수정할 수 없는 회원 정보. 변경은 Manager를 거친다. */
public final class UserDomain {
    private final String id, name, disease;
    private final int age;
    private final double height, weight;

    public UserDomain(String id, String name, int age, double height, double weight, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.disease = disease;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public String getDisease() { return disease; }
    @Override public String toString() {
        return "아이디: " + id + ", 이름: " + name + ", 나이: " + age
            + ", 키(cm): " + height + ", 몸무게(kg): " + weight
            + ", 질환: " + (disease.isEmpty() ? "없음" : disease);
    }
}
