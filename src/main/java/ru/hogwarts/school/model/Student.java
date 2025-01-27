package ru.hogwarts.school.model;

import java.util.Objects;

public class Student {
    private long id;
    private String name;
    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.id = -1;
        this.name = name;
        this.age = age;
    }

    public Student(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object object) {
        return (this == object ||
                object != null &&
                        getClass() == object.getClass() &&
                        id == ((Student) object).id &&
                        age == ((Student) object).age &&
                        Objects.equals(name, ((Student) object).name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return String.format("Student: {id=%s, name='%s', age=%s}", id, name, age);
    }
}
