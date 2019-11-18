create table books_aud(
	event_id int(11) not null auto_increment,
    event_date datetime not null,
    event_type varchar(10) default null,
    old_book_id int(11),
    new_book_id int(11),
    old_title varchar(255),
    new_title varchar(255),
    old_pubyear int(4),
    new_pubyear int(4),
    old_bestseller boolean,
    new_bestseller boolean,
    primary key (event_id)
);

create table readers_aud(
	event_id int(11) not null auto_increment,
    event_date datetime not null,
    event_type varchar(10) default null,
    old_reader_id int(11),
    new_reader_id int(11),
    old_firstname varchar(255),
    new_firstname varchar(255),
    old_lastname varchar(255),
    new_lastname varchar(255),
    old_peselid varchar(255),
    new_peselid varchar(255),
    old_vip_level varchar(20),
    new_vip_level varchar(20),
    primary key (event_id)
);

delimiter $

create trigger readers_insert after insert on readers
for each row
begin
	insert into readers_aud (event_date, event_type, new_reader_id, new_firstname, new_lastname, new_peselid, new_vip_level)
		values(curtime(), "insert", new.reader_id, new.firstname, new.lastname, new.peselid, new.vip_level);
end $

create trigger readers_delete after delete on readers
for each row
begin
	insert into readers_aud (event_date, event_type, old_reader_id)
		values(curtime(), "delete", old.reader_id);
end $

create trigger readers_update after update on readers
for each row
begin
	insert into readers_aud (event_date, event_type,
		new_reader_id, new_firstname, new_lastname, new_peselid, new_vip_level,
		old_reader_id, old_firstname, old_lastname, old_peselid, old_vip_level)
		values(curtime(), "update",  
			new.reader_id, new.firstname, new.lastname, new.peselid, new.vip_level,
            old.reader_id, old.firstname, old.lastname, old.peselid, old.vip_level);
end $

create trigger books_insert after insert on books
for each row
begin
	insert into books_aud (event_date, event_type, new_book_id, new_title, new_pubyear, new_bestseller)
		values(curtime(), "insert", new.book_id, new.title, new.pubyear, new.bestseller);
end $

create trigger books_delete after delete on books
for each row
begin
	insert into books_aud (event_date, event_type, old_book_id)
		values(curtime(), "delete", old.book_id);
end $

create trigger books_update after update on books
for each row
begin
	insert into books_aud (event_date, event_type,
		new_book_id, new_title, new_pubyear, new_bestseller,
		old_book_id, old_title, old_pubyear, old_bestseller)
		values(curtime(), "update",  
			new.book_id, new.title, new.pubyear, new.bestseller,
            old.book_id, old.title, old.pubyear, old.bestseller);
end $


delimiter ;

