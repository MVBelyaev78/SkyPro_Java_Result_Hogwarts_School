package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(Integer age);

    Collection<Student> findByAgeBetween(Integer startAge, Integer endAge);

    @Query(value = "select count(1) from student s", nativeQuery = true)
    Long findStudentsCount();

    @Query(value = "select avg(s.age) from student s", nativeQuery = true)
    Double findStudentsAverageAge();

    @Query(value = "select s.* from tbl_student s order by s.id_student desc limit ?1", nativeQuery = true)
    Collection<Student> findLastStudents(Integer num);
}
