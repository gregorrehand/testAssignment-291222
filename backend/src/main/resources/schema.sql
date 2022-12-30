create table if not exists sector
(
    id  uuid default gen_random_uuid() not null primary key,
    sector_name varchar(255) not null,
    parent_sector_id uuid constraint fk_parent_sector_id references sector
);

create table if not exists reply
(
    id uuid default gen_random_uuid() not null primary key,
    reply_name varchar(255) not null,
    agree_to_terms boolean not null
);

create table if not exists reply_sector
(
    id uuid default gen_random_uuid() not null primary key,
    reply_id uuid not null constraint fk_reply_id references reply,
    sector_id uuid not null constraint fk_sector_id references sector
);


