insert into faculties(id, faculty_name) values (1, 'faculty');
insert into faculties(id, faculty_name) values (2, 'faculty 2');

insert into chairs(id, chair_name, faculty_id) values (1, 'chair', 1);
insert into chairs(id, chair_name, faculty_id) values (2, 'chair 2', 1);

insert into stud_groups(id, group_name, chair_id) values (1, 'student group', 1);
insert into stud_groups(id, group_name, chair_id) values (2, 'student group 2', 1);

insert into students(id, first_name, last_name, email, phone, birth_date, report_card, course, entry_date, group_id)
    values (1, 'fname', 'lname', 'email@gmail.com', '911', '2002-12-29', 23622, 1, '2020-01-01', 1);
insert into students(id, first_name, last_name, email, phone, birth_date, report_card, course, entry_date, group_id)
    values (2, 'fname 2', 'lname 2', 'email2@gmail.com', '102', '2002-12-28', 23623, 1, '2020-01-01', 1);

insert into subjects(id, subject_name) values (1, 'subject');
insert into subjects(id, subject_name) values (2, 'subject 2');

insert into sessions(id, exam_date, semester, student_id, subject_id, grade)
    values (1, '2020-01-01', 1, 1, 1, 5);
insert into sessions(id, exam_date, semester, student_id, subject_id, grade)
    values (2, '2020-01-01', 1, 1, 1, 4);