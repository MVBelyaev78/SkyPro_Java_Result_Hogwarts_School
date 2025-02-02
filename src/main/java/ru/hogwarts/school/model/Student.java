package ru.hogwarts.school.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(Long id, String name, Integer age) {
        super();
        this.id = id;
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

    public Faculty getFaculty() {
        return faculty;
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
