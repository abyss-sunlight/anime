--ɾ�����ݿ�
DROP DATABASE ecywz 
GO
CREATE DATABASE	ecywz --�������ݿ�
--������ͨ�û��Ļ�����Ϣ
use ecywz;
create table jcxx(
id int not null primary key identity , 
���� varchar(50) not null,
�ֻ� varchar(50) not null,
���� datetime null,
�Ա� nvarchar(50)  null,
�˺����� nvarchar(50) not null,
����ǩ�� nvarchar(100)not null,
ͷ�� varchar(50) not null
)
--��������Ա�Ļ�����Ϣ
use ecywz;
create table gljcxx(
�ֻ� varchar(50) not null,
���� varchar(50) not null,
id int not null primary key identity 
)
--������֤�����ʱ�����
use ecywz;
create table yzm(
��֤�� varchar(50) not null,
�ֻ� varchar(50) not null,
����ʱ�� bigint not null,
)
--������Դ��Ŀ¼
use ecywz;
create table zykml(
ͼƬ varchar(50) not null,
id int not null primary key identity (1,1),
������� varchar(500) not null,
���� int not null,
���� varchar(50) not null,
����1 varchar(50) not null,
����2 varchar(50)  null,
����3 varchar(50)  null,
��� datetime not null,
���� varchar(50) not null,
)
--������Դ��
use ecywz;
create table zyk(
��Ƶ varchar(50) not null,
ͼƬ varchar(50) not null,
id int not null,
������� varchar(500) not null,
���� int not null,
���� varchar(50) not null,
����1 varchar(50) not null,
����2 varchar(50)  null,
����3 varchar(50)  null,
��� datetime not null,
���� varchar(50) not null,
)
