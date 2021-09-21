create table PRODUTO
(
    id        int auto_increment
        primary key,
    nome      varchar(50)  not null,
    descricao varchar(255) null
);


create table CATEGORIA
(
    id int auto_increment,
    nome varchar(50) not null,
    primary key(id)
) ENGINE = InnoDB;

insert into CATEGORIA(nome) values ('ELETRONICOS');
insert into CATEGORIA(nome) values ('ELETRODOMESTICOS');
insert into CATEGORIA(nome) values ('MOVEIS');

alter table PRODUTO add column categoria_id int;
alter table PRODUTO add foreign key (categoria_id) references CATEGORIA(id);

update PRODUTO set categoria_id = 1 where id = 1;
update PRODUTO set categoria_id = 2 where id = 2;
update PRODUTO set categoria_id = 3 where id = 18;