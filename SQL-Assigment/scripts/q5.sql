select m.name from member m
where m.id in(
select ch.member_id
from checkout_item ch
group by ch.member_id
having count(ch.member_id) > 1
);