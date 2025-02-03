-- Студенты с возрастом между 10 и 20
select s.*
  from student s
 where s.age between 10 and 20;

-- Студенты по именам
select s."name"
  from student s;

-- Студенты с буковой "f" в имени без учета регистра
select s.*
  from student s
 where lower(s."name") like '%f%'

-- Студенты с возрастом менее 15
select s.*
  from student s
 where s.age < 15;

-- Студенты в порядке увеличния возраста
select s.*
  from student s
 order by s.age;
