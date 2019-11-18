create index reader_firstname on readers (firstname);
create index reader_lastname on readers (lastname);
create index book_title on books (title);

drop procedure if exists FindJohns;
drop procedure if exists InsertRecordsToReaders;

delimiter $

create procedure FindJohns()
begin
	select * from readers
    where firstname = "John";
end $

create procedure InsertRecordsToReaders()
begin
	declare k int(11) default 0;
    while (k < 100000) do
		if (mod(k, 200) = 0) then
			insert into readers(firstname, lastname, peselId, vip_level)
			values("John", "Doe", "645465456", "Standard Customer");
		else
			insert into readers(firstname, lastname, peselId, vip_Level)
			values("Mary", "Jane", "89798741", "Gold Customer");
        end if;
        if (mod(k, 5000) = 0) then commit;
        end if;
        set k = k + 1;
	end while;
end $

delimiter ;

call InnsertrRecordsToReaders();
call FindJohns();
	