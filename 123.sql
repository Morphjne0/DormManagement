drop database dormdb;
create database DormDB;

use DormDB;
create table studentTBl
(
s_name varchar(45) not null,  /*학생이름*/
s_iD char(10) not null,/*학생아이디*/
dept char(15) not null,/*학과*/
sex char(1),   /*성별 남 또는 여*/
milserv char(10) ,  /*군필여부*/
P_number char(15) not null,  /*핸드폰번호*/
address char(80) not null,  /*주소*/
r_Number char(5) not null,   /*방번호*/
E_Number char(20) not null,  /*비상 번호*/
C_history char(15),   /*병력*/
Religion char(10),  /*종교*/
pic char(80), /*사진링크*/
pnt int(100),
PRIMARY KEY (s_id, pic)
);

select * from studentTbl;

insert into studentTbl
values ('유재빈', '180210027', '컴퓨터정보','남', '군필', '010-4474-6682','인천',101, '010-0085-2772','','','','0');

insert into studentTbl
values ('송현식', '1402107242', '컴퓨터정보','남', '', '010-1228-7264','인천',102, '010-5585-7516','','불교','','0');

insert into studentTbl
values ('배영일', '1602106524', '컴퓨터정보','남', '미필', '010-8870-0086','경기',103, '010-6024-3425','','불교','','0');

insert into studentTbl
values ('임현진', '1702101275', '컴퓨터정보','여', '', '010-0067-1658','서울',301, '010-4108-8423','','천주교','','0');

insert into studentTbl
values ('양현철', '1702103541', '컴퓨터정보','남', '군필', '010-1405-1064','서울',104, '010-8721-7456','','기독교','','0');

insert into studentTbl
values ('전경재', '1802109823', '컴퓨터정보','남', '', '010-7455-5277','경기',105, '010-7601-2215','','','','0');

insert into studentTbl
values ('최도윤', '1802107851', '컴퓨터정보','남', '미필', '010-7412-2104','경기',101, '010-2884-4835','','','','0');

insert into studentTbl
values ('강도환', '1802101698', '컴퓨터정보','남', '군필', '010-5145-0004','서울',102, '010-6301-6821','','천주교','','0');

insert into studentTbl
values ('성영진', '1902107821', '컴퓨터정보','남', '군필', '010-6273-2137','서울',103, '010-3460-3882','','','','0');

insert into studentTbl
values ('윤하윤', '1902104892', '컴퓨터정보','여', '', '010-1315-3306','서울',302, '010-4565-3122','','','','0');

insert into studentTbl
values ('정문수', '1902109821', '컴퓨터정보','남', '군필', '010-2706-5260','경기',201, '010-0358-5678','','','','0');

insert into studentTbl
values ('박민성', '1902101587', '컴퓨터정보','남', '', '010-3480-5147','경기',202, '010-4644-3388','','','','0');

insert into studentTbl
values ('윤영아', '1902101268', '컴퓨터정보','여', '', '010-3183-4456','경기',303, '010-4876-4122','','','','0');

insert into studentTbl
values ('문성남', '1902101567', '컴퓨터정보','남', '군필', '010-1207-8402','경기',204, '010-0588-1615','','기독교','','0');

insert into studentTbl
values ('한영원', '2002108874', '컴퓨터정보','남', '미필', '010-0731-8383','서울',205, '010-0613-2245','','','','0');

insert into studentTbl
values ('정민혁','2002105547', '컴퓨터정보','남', '군필', '010-5786-1320','경기',205, '010-5646-4370','','기독교','','0');

insert into studentTbl
values ('송문수','2102106874', '컴퓨터정보','남', '군필', '010-8312-4617','경기',201, '010-6713-2478','','기독교','','0');

insert into studentTbl
values ('이세욱','2102104678', '컴퓨터정보','남', '군필', '010-2352-1708','대전',203, '010-1280-6866','','','','0');

insert into studentTbl
values ('김예은','2102106987', '컴퓨터정보','여', '', '010-4506-6278','부산',304, '010-4836-2451','','기독교','','0');

insert into studentTbl
values ('한상윤','2102106399', '컴퓨터정보','남', '군필', '010-2466-4746','청주',204, '010-3206-5475','','','','0');

insert into studentTbl
values ('가나다','1234', '컴퓨터정보','남', '군필', '010-2466-4746','청주',204, '010-3206-5475','','가나다','C:/symbol.png','0');

insert into studentTbl
values ('배길성','1702100348', '컴퓨터정보','남', '군필', '010-3392-9918','의정부',103, '010-0000-0000','','천주교','C:/pic/증명사진_2020.jpg','0');

insert into studentTbl
values ('홍길동','123456789', '컴퓨터정보','남', '군필', '010-3392-9918','의정부',103, '010-0000-0000','','천주교','C:/pic/123.jpg','0');


select * from studenttbl;

drop table DPrequestTBL;

create table DPTbL   /*애로사항 테이블*/
(    
num int auto_increment not null primary key,
s_name varchar(45) not null,  /*학생이름*/
s_iD char(10) not null ,/*학생아이디*/
dept char(15) not null,/*부서*/
r_Number char(5) not null,   /*방번호*/
P_number char(15) not null,  /*핸드폰번호*/
P_date char(20) not null,  /*등록일*/
com char(100) not null, /*애로사항 내용*/
com_sub char(100) not null,
state char(10) not null,
foreign key (s_ID) references studentTbl(s_ID) on delete cascade on update cascade
);


insert into DPTbL
values ('0','배길성','1702100348' ,'컴퓨터정보','103','010', '2021-01-01', '내용','제목','진행');

insert into DPTbL
values ('0','홍길동','123456789' ,'쌀먹','111','0110', '2021-01-02', '내용','제목','진행');

select *from DPTbL;




create table OnTBL  /*외박자 테이블*/
(    
num int auto_increment not null primary key,
s_name varchar(45) not null,  /*학생이름*/
s_iD char(10) not null ,/*학생아이디*/
dept char(15) not null,/*부서*/
r_Number char(5) not null,   /*방번호*/
P_number char(15) not null,  /*핸드폰번호*/
DDate char(20) not null,  /*출발일*/
desti char(40) not null, /*행성지*/
RDate char(20) not null, /*복귀일*/
temp char(5) not null,	/*체온*/
trans char(50) not null, /*이동수단*/
Ret char(1) not null,/* 복귀여부 */

foreign key (s_ID) references studentTbl(s_ID) on delete cascade on update cascade
);


insert into OnTbL
values ('0','배길성','1702100348' ,'컴퓨터정보','103','010-1234-12334','2021-06-01', '수원', '2021-06-15','36.5','전철','x');

insert into OnTbL
values ('0','홍길동','123456789' ,'컴퓨터정보','103','010-1234-12334','2021-06-01', '수원', '2021-06-15','36.5','전철','x');

select*from onTbl;

/*drop table RnpTBL;
create table RnpTBL   
(    
num int auto_increment not null primary key,
s_name varchar(45) not null,  
s_iD char(10) not null ,
dept char(15) not null,
r_Number char(5) not null,   
P_number char(15) not null, 
pnt int(20) not null, 

foreign key (s_ID) references studentTbl(s_ID)
);

insert into RnpTbL
values ('0','배길성','1702100348' ,'컴퓨터정보','103','010-1234-12334','5');  

select * from RnpTbl;
*/
create table RnpLogTBL   /*상벌점 테이블2 */
(
num int(255) not null auto_increment primary key,
s_name varchar(45) not null,  /*학생이름*/
s_iD char(10) not null ,/*학생아이디*/
pnt int(20) not null,  /*벌점*/
rsn char(100) not null, /*사유*/
dat char(100) not null, /*날짜*/

FOREIGN KEY (s_id) REFERENCES studenttbl (s_id) on delete cascade on update cascade
);



insert into RnplogTbL
values ('0','배길성','1702100348' ,10,'음주귀사','2021/06/01'); 
insert into RnplogTbL
values ('0','배길성','1702100348' ,20,'무단귀사','2021/06/01'); 
insert into RnplogTbL
values ('0','홍길동','123456789' ,10,'음주귀사','2021/06/01');  
select * from RnpLogtbl;

select pnt from rnplogtbl where s_name = '배길성' and s_id = '1702100348';

delete from studenttbl where s_name='홍길동' and s_id='123456789';
