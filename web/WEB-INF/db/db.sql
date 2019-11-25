-- 创建管理员表
show tables;

drop table if exists admin;

create table admin
(
    id       int primary key auto_increment,
    username varchar(24) not null,
    password varchar(72) not null
) engine = innodb
  charset = utf8;

-- 向管理员表插入几条数据
insert into admin(username, password)
values ("lxgzhw", "lxgzhw");

-- 查询插入结果
select *
from admin;

-- 创建用户信息表
-- 	姓名	性别	年龄	籍贯	QQ	邮箱
drop table if exists userinfo;
create table userinfo
(
    id      bigint primary key auto_increment,
    name    varchar(24) not null,
    gender  char(1)     not null,
    age     smallint    not null,
    address varchar(24),
    qq      varchar(11),
    email   varchar(72)
) engine = innodb
  charset = utf8;

-- 查看是否创建成功
show tables;
-- 多插入一些数据
insert into userinfo(name, gender, age, address, qq, email)
values ("大鹏", "男", 22, "贵州", "111", "111@qq.com"),
       ("萃萃", "女", 18, "重庆", "222", "222@qq.com"),
       ("女神", "女", 18, "重庆", "222", "222@qq.com"),
       ("空明", "男", 33, "异世大陆", "666", "666@qq.com"),
       ("大鹏", "男", 22, "贵州", "111", "111@qq.com"),
       ("萃萃", "女", 18, "重庆", "222", "222@qq.com"),
       ("女神", "女", 18, "重庆", "222", "222@qq.com"),
       ("空明", "男", 33, "异世大陆", "666", "666@qq.com"),
       ("大鹏", "男", 22, "贵州", "111", "111@qq.com"),
       ("萃萃", "女", 18, "重庆", "222", "222@qq.com"),
       ("女神", "女", 18, "重庆", "222", "222@qq.com"),
       ("空明", "男", 33, "异世大陆", "666", "666@qq.com"),
       ("大鹏", "男", 22, "贵州", "111", "111@qq.com"),
       ("萃萃", "女", 18, "重庆", "222", "222@qq.com"),
       ("女神", "女", 18, "重庆", "222", "222@qq.com"),
       ("空明", "男", 33, "异世大陆", "666", "666@qq.com"),
       ("大鹏", "男", 22, "贵州", "111", "111@qq.com"),
       ("萃萃", "女", 18, "重庆", "222", "222@qq.com"),
       ("女神", "女", 18, "重庆", "222", "222@qq.com"),
       ("空明", "男", 33, "异世大陆", "666", "666@qq.com"),
       ("大鹏", "男", 22, "贵州", "111", "111@qq.com"),
       ("萃萃", "女", 18, "重庆", "222", "222@qq.com"),
       ("女神", "女", 18, "重庆", "222", "222@qq.com"),
       ("空明", "男", 33, "异世大陆", "666", "666@qq.com");


-- 查看插入结果
select count(*) from userinfo;