create table if not exists link
(
    id          bigint generated always as identity,
    url         text                     not null,
    lastUpdate timestamp with time zone not null,
    lastCheck timestamp with time zone not null,

    unique (url),
    primary key (id)
);
