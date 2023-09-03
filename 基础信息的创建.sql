--删除数据库
DROP DATABASE ecywz 
GO
CREATE DATABASE	ecywz --创建数据库
--创建普通用户的基础信息
use ecywz;
create table jcxx(
id int not null primary key identity , 
密码 varchar(50) not null,
手机 varchar(50) not null,
生日 datetime null,
性别 nvarchar(50)  null,
账号名称 nvarchar(50) not null,
个性签名 nvarchar(100)not null,
头像 varchar(50) not null
)
--创建管理员的基础信息
use ecywz;
create table gljcxx(
手机 varchar(50) not null,
密码 varchar(50) not null,
id int not null primary key identity 
)
--创建验证码的临时储存库
use ecywz;
create table yzm(
验证码 varchar(50) not null,
手机 varchar(50) not null,
创建时间 bigint not null,
)
--创建资源库目录
use ecywz;
create table zykml(
图片 varchar(50) not null,
id int not null primary key identity (1,1),
具体介绍 varchar(500) not null,
集数 int not null,
番名 varchar(50) not null,
类型1 varchar(50) not null,
类型2 varchar(50)  null,
类型3 varchar(50)  null,
年代 datetime not null,
地区 varchar(50) not null,
)
--创建资源库
use ecywz;
create table zyk(
视频 varchar(50) not null,
图片 varchar(50) not null,
id int not null,
具体介绍 varchar(500) not null,
集数 int not null,
番名 varchar(50) not null,
类型1 varchar(50) not null,
类型2 varchar(50)  null,
类型3 varchar(50)  null,
年代 datetime not null,
地区 varchar(50) not null,
)
