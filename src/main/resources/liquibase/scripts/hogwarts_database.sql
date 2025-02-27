-- liquibase formatted sql

-- changeset mikhail.belyaev:1

create table public.tbl_faculty(
	id_faculty serial,
	nm_color   varchar(20) not null,
	nm_name    varchar(50) not null,
	constraint faculty_pk primary key (id_faculty),
	constraint faculty_uk$1 unique (nm_color, nm_name)
);

create table public.tbl_student(
	id_student serial,
	nn_age     integer default 20,
	nm_name    varchar(50) not null,
	id_faculty integer not null,
	constraint student_pk primary key (id_student),
	constraint student_uk$1 unique (nm_name),
	constraint student_ch$1 check (nn_age >= 16),
	constraint student_fk$1 foreign key (id_faculty) references public.tbl_faculty(id_faculty)
);

create index student_i$1 on public.tbl_student (id_faculty);
create index student_i$2 on public.tbl_student (nm_name);

create table public.tbl_avatar(
	id_avatar     serial,
	nm_file_path  varchar(100) not null,
	nn_file_size  integer not null,
	nm_media_type varchar(20) not null,
	id_student    integer,
	vl_data       oid,
	constraint avatar_pk primary key (id_avatar),
	constraint avatar_uk$1 unique (id_student),
	constraint avatar_fk$1 foreign key (id_student) references public.tbl_student(id_student)
);
