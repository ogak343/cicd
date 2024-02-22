create table account (
    id bigserial primary key,
    username varchar not null unique,
    password varchar not null,
    enabled boolean not null default false,
    role varchar(15) not null
);

create table confirmation_code (
    id bigserial primary key,
    code int not null,
    created_at timestamp not null,
    expired_at timestamp not null,
    confirmed_at timestamp,
    account_id bigint not null references account(id)
);

create sequence account_id_sequence minvalue 1 increment 1 cache 1;
create sequence confirmation_code_id_sequence minvalue 1 increment 1 cache 1;