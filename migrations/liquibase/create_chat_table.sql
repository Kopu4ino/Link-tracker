create table if not exists chat
(
    id        bigint generated always as identity,
    user_name text,

    primary key (id)
);
