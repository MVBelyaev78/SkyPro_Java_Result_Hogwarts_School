package ru.hogwarts.school.constant;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

public class TestConstants {
    public static final Faculty FACULTY_1 = new Faculty("Gryffindor", "brown");
    public static final Faculty FACULTY_11 = new Faculty("Gryffindor2", "black");
    public static final Faculty FACULTY_2 = new Faculty("Hufflepuff", "black");

    public static final String COLOR_1 = "black";

    public static final Student STUDENT_1 = new Student("Elvis Presley", 17);
    public static final Student STUDENT_2 = new Student("Perry Como", 12);
    public static final Student STUDENT_3 = new Student("Bob Dylan", 17);

    public static final Integer AGE_1 = 17;
}
