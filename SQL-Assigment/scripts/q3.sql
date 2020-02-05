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