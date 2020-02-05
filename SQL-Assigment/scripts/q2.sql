select count(id)
from member
where id not in(
select member_id
from checkout_item);