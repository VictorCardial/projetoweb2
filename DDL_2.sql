
drop database produtora;

create schema produtora;

use produtora;

create table usr_usuario(
    usr_id bigint unsigned primary key auto_increment,
    usr_login varchar(50),
    usr_senha varchar(50),
    constraint use_login_uk unique (usr_login)
);

INSERT INTO usr_usuario (usr_login, usr_senha)
VALUES ("admin","123");

INSERT INTO usr_usuario (usr_login, usr_senha)
VALUES ("jonathan","123");

INSERT INTO usr_usuario (usr_login, usr_senha)
VALUES ("lucas","123");

INSERT INTO usr_usuario (usr_login, usr_senha)
VALUES ("hercules","123");

INSERT INTO usr_usuario (usr_login, usr_senha)
VALUES ("victor","123");

create table pes_pessoa(
	pes_id bigint unsigned primary key auto_increment,
    pes_nome varchar(50),
    pes_cpf bigint,
    tipo varchar(1),
    atr_fama varchar(50),
    dub_especialidade varchar(50),
    constraint pes_cpf_uk unique (pes_cpf)
);

create table fmg_filmagem (
	fmg_id bigint unsigned primary key auto_increment,
    fmg_nome varchar(50) not null,
    fmg_ano date not null,
    fmg_duracao float not null,
    diretor bigint unsigned,
    constraint fmg_pes_fk foreign key (diretor)
		references pes_pessoa(pes_id)
);

create table nov_novela (
	fmg_id bigint unsigned,
    nov_capitulo bigint not null,
    nov_desc_cap varchar(100) not null,
    constraint nov_fmg_fk foreign key (fmg_id)
		references fmg_filmagem(fmg_id)
);

create table flm_filme (
	fmg_id bigint unsigned,
    flm_descricao varchar(100) not null,
    constraint flm_fmg_fk foreign key (fmg_id)
		references fmg_filmagem(fmg_id)
);

create table atu_atuacao (
	pes_id bigint unsigned,
    fmg_id bigint unsigned,
    constraint atu_pk primary key(pes_id, fmg_id),
    constraint atu_fmg_fk foreign key (fmg_id)
		references fmg_filmagem(fmg_id),
	constraint atu_pes_fk foreign key (pes_id)
		references pes_pessoa(pes_id)
);


