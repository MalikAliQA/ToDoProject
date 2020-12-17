drop table if exists task CASCADE; 
drop table if exists user CASCADE;

create table IF NOT EXISTS user (id bigint PRIMARY KEY AUTO_INCREMENT, `name` varchar(255) not null);
create table IF NOT EXISTS task (id bigint PRIMARY KEY AUTO_INCREMENT, body varchar(255) not null, `name` varchar(255) not null, user_id bigint, 
	CONSTRAINT fk_user_task_id
    FOREIGN KEY (user_id)
    REFERENCES user (id)
    ON DELETE CASCADE);
