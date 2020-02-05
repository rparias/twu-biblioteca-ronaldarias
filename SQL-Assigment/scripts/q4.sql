insert into Book(id,title) values(11,'The Pragmatic Programmer');
insert into Member(id,name) values(43,'Ronald Arias');
insert into checkout_item(member_id,book_id) values(43,11);
select m.name from member m
where m.id in(
select ch.member_id
from book b, checkout_item ch
where ch.book_id = b.id and
b.title = 'The Pragmatic Programmer');