-- desc
desc user;
desc blog;
desc category;
desc post;

-- select
select * from user;
select * from blog;
select * from category;
select * from post;

-- insert 

-- insert into blog values ('1' , "블로그타이틀", "블로그로고");
-- insert into category values (null , "카테고리이름", "카테고리설명", '1');
-- insert into post values (null , "포스트이름", "포스트 내용 설명", '1');

-- delete 

-- delete from user;
-- a.no, a.name, a.description, count(b.title)

  select a.no, a.name, a.description, count(b.title) as blogId
	from (select *
			from category a 
		   where a.blog_id = '1') a
    left outer join post b
      on a.no = b.category_no
group by a.no
order by a.no desc;
