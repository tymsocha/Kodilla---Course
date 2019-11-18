create table stats (
STAT_ID INT(11) AUTO_INCREMENT PRIMARY KEY,
STAT_DATE DATETIME NOT NULL,
STAT VARCHAR(20) NOT NULL,
VALUE INT(11) NOT NULL
);

create view bestsellers_count as
select count(nullif(bestseller = false, true)) 
from books;

use kodilla_course;

delimiter $
create event update_bestseller
on schedule every 1 minute
do begin
	declare bestcount int(11);
	call UpdateBestsellers(); 
    set bestcount = (select * from bestsellers_count);
    insert into stats(stat_date, stat, value)
    values(curdate(), "bestsellers",  bestcount);
end $

delimiter ;