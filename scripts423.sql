-- студенты с названиями их факультетов
select s."name", s.age, f."name" faculty_name
  from student s
       join faculty f on f.id = s.faculty_id;

-- студенты, у которых есть аватарки
select s."name", s.age
  from student s
 where s.id in (select x.student_id from avatar x);
