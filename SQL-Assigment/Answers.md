# Assigment 3

## 1. Who checked out the book 'The Hobbit’?
**Answer:** Anand Beck
```sql
select m.name from member m
where m.id in(
select ch.member_id
from book b, checkout_item ch
where ch.book_id = b.id and
b.title = 'The Hobbit');
```
## 2. How many people have not checked out anything?
**Answer:** 37
```sql
select count(id)
from member
where id not in(
select member_id
from checkout_item);
```
## 3. What books and movies aren't checked out?
**Answer:**
1984
Catcher in the Rye
Crouching Tiger, Hidden Dragon
Domain Driven Design
Fellowship of the Ring
Lawrence of Arabia
Office Space
Thin Red Line
To Kill a Mockingbird
Tom Sawyer
```sql
select title
from book
where id not in(
select book_id
from checkout_item
where book_id is not null)
union
select title
from movie
where id not in(
select movie_id
from checkout_item
where movie_id is not null);
```
## 4. Add the book 'The Pragmatic Programmer', and add yourself as a member. Check out 'The Pragmatic Programmer'. Use your query from question 1 to verify that you have checked it out. Also, provide the SQL used to update the database.
**Answer:** Ronald Arias
```sql
insert into Book(id,title) values(11,'The Pragmatic Programmer');
insert into Member(id,name) values(43,'Ronald Arias');
insert into checkout_item(member_id,book_id) values(43,11);
select m.name from member m
where m.id in(
select ch.member_id
from book b, checkout_item ch
where ch.book_id = b.id and
b.title = 'The Pragmatic Programmer');
```
## 5. Who has checked out more than 1 item? 
**Answer:**
Anand Beck
Frank Smith
```sql
select m.name from member m
where m.id in(
select ch.member_id
from checkout_item ch
group by ch.member_id
having count(ch.member_id) > 1
);
```