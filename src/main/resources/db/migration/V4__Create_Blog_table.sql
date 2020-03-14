create table blog
(
	id bigint auto_increment,
	title varchar(30) null,
	content varchar(5000) null,
	tags varchar(255) null,
	gmt_create bigint null,
	gmt_modify bigint null,
	view_count bigint null,
	like_count bigint null,
	author varchar(50) null,
	constraint blog_pk
		primary key (id)
);

