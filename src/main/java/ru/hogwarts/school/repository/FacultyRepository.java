package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByColor(String color);

    @Query(value =
                    "select f.*\n" +
                    "  from faculty f\n" +
                    " where (lower(f.color) like '%' || ?1 || '%'\n" +
                    "    or  lower(f.\"name\") like '%' || ?1 || '%'\n" +
                    "       )",
            nativeQuery = true)
    Collection<Faculty> findByNameOrColorContainsIgnoreCase(String stringFilter);
}
