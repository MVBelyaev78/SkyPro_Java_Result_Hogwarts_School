package ru.hogwarts.school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
                        Objects.equals(id, ((Student) object).id) &&
                        Objects.equals(age, ((Student) object).age) &&
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
