select m.name from member m
where m.id in(
select ch.member_id
from book b, checkout_item ch
where ch.book_id = b.id and
b.title = 'The Hobbit');