create table record
(
	id bigint auto_increment,
	type int null,
	creator bigint null,
	receiver bigint null,
	receiver_type int null,
	state int null,
	gmt_create bigint null,
	constraint record_pk
		primary key (id)
);

