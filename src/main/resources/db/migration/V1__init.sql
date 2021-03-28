create table students (
  id                    bigserial,
  studentname          varchar(30) unique,
  password              varchar(80) not null,
  score                 int(8),
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE students_roles (
  student_id               bigint not null,
  role_id               int not null,
  primary key (student_id, role_id),
  foreign key (student_id) references students (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into students (studentname, password, score)
values
('student1', '$2y$12$7yinCsWra7rO8d61diEypuFGE7JSHjtHr8vnMrP89h8/aZgfQra0a', 10),
('student2', '$2y$12$w2z6A4.1H.Hq.WcRafmqieJqYbVFljNooHrTYi/Gppagl4WG7m8um', 20),
('student3', '$2y$12$IgVZrZDCbLLrFHBvYz88ROQifxA0YJvjbzE.fXxcY6.g6LPoSIFIq', 30);

insert into students_roles (student_id, role_id)
values
(1, 1),
(2, 1),
(3, 1);