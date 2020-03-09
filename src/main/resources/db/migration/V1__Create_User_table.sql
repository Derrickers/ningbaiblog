create table User
(
	id bigint auto_increment,
	account varchar(30) null,
	name varchar(255) default "匿名的可爱宝贝" null,
	description varchar(255) default "这个人好懒哦，都没有个人简介的～" null,
	label varchar(255) null,
	sex int default 3 null,
	avatar_url varchar(255) null,
	gmt_create bigint null,
	gmt_modify bigint null,
	constraint User_pk
		primary key (id)
);

