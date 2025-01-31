package ru.hogwarts.school.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="faculty")
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="color")
    private String color;

    public Faculty() {
    }

    public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Faculty(Long id, String name, String color) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object object) {
        return (this == object ||
                object != null &&
                        getClass() == object.getClass() &&
                        Objects.equals(id, ((Faculty) object).id) &&
                        Objects.equals(name, ((Faculty) object).name) &&
                        Objects.equals(color, ((Faculty) object).color));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }

    @Override
    public String toString() {
        return String.format("faculty: {id=%s, name='%s', color='%s'}", id, name, color);
    }
}
