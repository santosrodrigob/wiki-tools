use data_teste;
/*
drop table tb_referencia;
drop table tb_sub_referencia;
drop table tb_licao_aprendida;
drop table tb_role;
*/

/*
	select * from tb_referencia;
	select * from tb_sub_referencia;
	select * from tb_licao_aprendida;
	select * from tb_role;
	select * from tb_usuario;
*/
create table if not exists `tb_equipamento`
(
	codigo_equipamento int auto_increment NOT NULL,
	equipamento varchar(30) NOT NULL,
	marca varchar(20) NULL,
	modelo varchar(20) NULL,
    inativo bit NULL,
	codigo_usuario_criacao int NULL,
	codigo_usuario_alteracao int NULL,	
	data_criacao date NOT NULL,
	data_alteracao date NULL,
    primary key (codigo_equipamento)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

select * from tb_equipamento;

insert into tb_equipamento(equipamento, marca, modelo, data_criacao)
select 'TERMINAL', 'WILBOR', '1.44', convert(now(), date)

insert into tb_equipamento(equipamento, marca, modelo, data_criacao)
select 'CELULAR', 'SAMSUNG', 'S8', convert(now(), date)

insert into tb_equipamento(equipamento, marca, modelo, data_criacao)
select 'CPU', 'BEMATECH', 'ALL IN ONE', convert(now(), date)
create table if not exists `tb_role`
(
	codigo_acesso int auto_increment NOT NULL,
	acesso varchar(15) NOT NULL,
		primary key (codigo_acesso)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;
#insert into tb_role(codigo_acesso, acesso) select 1, 'ROLE_ADMIN';

create table if not exists `tb_referencia`
(
	codigo_referencia int auto_increment NOT NULL,
    referencia varchar(10000) NOT NULL,
    inativo bit NULL,
	codigo_usuario_criacao int NULL,
	codigo_usuario_alteracao int NULL,	
	data_criacao date NOT NULL,
	data_alteracao date NULL,
		primary key (codigo_referencia)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create table if not exists `tb_sub_referencia`
(
	codigo_sub_referencia int auto_increment NOT NULL,
	codigo_referencia int NOT NULL,
    sub_referencia varchar(10000) NOT NULL,
    inativo bit NULL,
	codigo_usuario_criacao int NULL,
	codigo_usuario_alteracao int NULL,	
	data_criacao date NOT NULL,
	data_alteracao date NULL,
		primary key (codigo_sub_referencia)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;
alter table tb_sub_referencia add foreign key (codigo_referencia) references tb_referencia (codigo_referencia);

create table if not exists `tb_licao_aprendida`
(
	id int auto_increment NOT NULL,
    cliente varchar(30) NOT NULL,
    erro varchar(10000) NOT NULL,
    solucao varchar(10000) NOT NULL,
    codigo_referencia int NOT NULL,
    codigo_sub_referencia int NOT NULL,
    codigo_tecnico int NOT NULL,
    inativo bit NULL,
	codigo_usuario_criacao int NULL,
	codigo_usuario_alteracao int NULL,	
	data_criacao date NOT NULL,
	data_alteracao date NULL,
		primary key (id)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;
alter table tb_licao_aprendida add foreign key (codigo_referencia) references tb_referencia (codigo_referencia);
alter table tb_licao_aprendida add foreign key (codigo_sub_referencia) references tb_sub_referencia (codigo_sub_referencia);

/*Tabelas do homologador - usuario*/
create table if not exists `tb_filtro`
(
	codigo_modulo int NULL,
    codigo_feature int NULL,
    codigo_tipo int NULL,
	codigo_modulo_leia_me int NULL,
    versao_leia_me varchar(15) NULL,
    data_alteracao date NULL
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create table if not exists `tb_usuario`
(
	id_usuario int auto_increment NOT NULL,
	codigo_usuario int NOT NULL,
    usuario varchar(50) NOT NULL,
    senha varchar(15) NOT NULL,
	codigo_acesso_wiki int NULL,
	codigo_acesso_homologador int NULL,
    ativo bit NOT NULL,
    data_criacao date NOT NULL,
    data_alteracao date NULL,
    inativo bit NULL, #usado no homologador
		primary key (id_usuario)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;
alter table tb_usuario add foreign key (codigo_acesso_wiki) references tb_role (codigo_acesso);
alter table tb_usuario add foreign key (codigo_acesso_homologador) references tb_role (codigo_acesso);
#insert into tb_usuario(codigo_usuario, usuario, senha, codigo_acesso_wiki, codigo_acesso_homologador, ativo, data_criacao) select 1, 'RODRIGO', 1, 1, 1, 1, convert(now(), date)

#select * from tb_usuario;
#drop table tb_usuario;

create table if not exists `tb_modulo`
(
	codigo_modulo int auto_increment, 
    modulo varchar(50) NOT NULL, 
    inativo bit NULL,
		primary key (codigo_modulo)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create table if not exists `tb_feature`
(
	codigo_feature int auto_increment NOT NULL, 
    codigo_modulo int NOT NULL,
    feature varchar(50) NOT NULL,
    inativo bit NULL,
		primary key (codigo_feature)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create table if not exists `tb_tipo`
(
	codigo_tipo int auto_increment NOT NULL, 
    codigo_modulo int NOT NULL,
    codigo_feature int NOT NULL,
    tipo varchar(50) NOT NULL,
    inativo bit NULL,
		primary key (codigo_tipo)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;
alter table tb_tipo add foreign key (codigo_modulo) references tb_modulo (codigo_modulo);
alter table tb_tipo add foreign key (codigo_feature) references tb_feature (codigo_feature);

create table if not exists `tb_regra_negocio`
(
	codigo_regra int auto_increment NOT NULL,
    regra_negocio varchar(2000) NOT NULL,
    codigo_modulo int NOT NULL,
    codigo_feature int NOT NULL,
	codigo_tipo int NOT NULL,
	teste_feature bit NOT NULL,
	codigo_usuario_criacao int NULL,
	codigo_usuario_alteracao int NULL,
	data_criacao datetime NULL,
	data_alteracao datetime NULL,
    inativo bit NULL,
		primary key (codigo_regra)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

alter table tb_regra_negocio add foreign key (codigo_modulo) references tb_modulo (codigo_modulo);
alter table tb_regra_negocio add foreign key (codigo_feature) references tb_feature (codigo_feature);
alter table tb_regra_negocio add foreign key (codigo_tipo) references tb_tipo (codigo_tipo);

create table if not exists `tb_comportamento`
(
	codigo_comportamento int auto_increment,
    comportamento varchar(2000) NOT NULL,
    codigo_modulo int NOT NULL,
    codigo_feature int NOT NULL,
	codigo_tipo int NOT NULL,
	teste_feature bit NOT NULL,
	codigo_usuario_criacao int NULL,
	codigo_usuario_alteracao int NULL,
	data_criacao datetime NULL,
	data_alteracao datetime NULL,
    inativo bit NULL,
		primary key (codigo_comportamento)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

alter table tb_comportamento add foreign key (codigo_modulo) references tb_modulo (codigo_modulo);
alter table tb_comportamento add foreign key (codigo_feature) references tb_feature (codigo_feature);
alter table tb_comportamento add foreign key (codigo_tipo) references tb_tipo (codigo_tipo);

create table if not exists `tb_caso_teste`
(
	codigo_caso_teste int auto_increment NOT NULL,
    caso_teste varchar(2000) NOT NULL,
    codigo_modulo int NOT NULL,
    codigo_feature int NOT NULL,
	codigo_tipo int NOT NULL,
	teste_feature bit NOT NULL,
	codigo_usuario_criacao int NULL,
	codigo_usuario_alteracao int NULL,
	data_criacao datetime NULL,
	data_alteracao datetime NULL,
    inativo bit NULL,
		primary key (codigo_caso_teste)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

alter table tb_caso_teste add foreign key (codigo_modulo) references tb_modulo (codigo_modulo);
alter table tb_caso_teste add foreign key (codigo_feature) references tb_feature (codigo_feature);
alter table tb_caso_teste add foreign key (codigo_tipo) references tb_tipo (codigo_tipo);

/* TESTES */

create table if not exists `tb_teste`
(
	codigo_teste int auto_increment NOT NULL,
    codigo_modulo int NOT NULL,
    codigo_feature int NOT NULL,
	codigo_tipo int NOT NULL,
    situacao_caso_teste bit NULL,
    situacao_regra bit NULL,
    situacao_comportamento bit NULL,
    conclusao varchar(2000) NULL,
    observacao varchar(2000) NULL,
    inativo bit NULL,
	codigo_usuario_criacao int NULL,
	codigo_usuario_alteracao int NULL,	
	data_criacao date NOT NULL,
	data_alteracao date NULL,
		primary key(codigo_teste)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

alter table tb_teste add foreign key (codigo_modulo) references tb_modulo (codigo_modulo);
alter table tb_teste add foreign key (codigo_feature) references tb_feature (codigo_feature);
alter table tb_teste add foreign key (codigo_tipo) references tb_tipo (codigo_tipo);

create table if not exists `tb_teste_atributo`
(
	id_teste int auto_increment NOT NULL ,
	codigo_teste int NOT NULL,
    codigo_modulo int NOT NULL,
    codigo_feature int NOT NULL,
	codigo_tipo int NOT NULL,
	codigo_caso_teste int NULL,
	codigo_comportamento int NULL,
	codigo_regra int NULL,
    situacao bit NULL,
    conclusao varchar(2000) NULL,
    observacao varchar(2000) NULL,
    data_hora_ini datetime NULL,
    data_hora_fim datetime NULL,
    inativo bit NULL,
		primary key(id_teste)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

alter table tb_teste_atributo add foreign key (codigo_modulo) references tb_modulo (codigo_modulo);
alter table tb_teste_atributo add foreign key (codigo_feature) references tb_feature (codigo_feature);
alter table tb_teste_atributo add foreign key (codigo_tipo) references tb_tipo (codigo_tipo);
alter table tb_teste_atributo add foreign key (codigo_teste) references tb_teste (codigo_teste);
alter table tb_teste_atributo add foreign key (codigo_caso_teste) references tb_caso_teste (codigo_caso_teste);
alter table tb_teste_atributo add foreign key (codigo_regra) references tb_regra_negocio (codigo_regra);
alter table tb_teste_atributo add foreign key (codigo_comportamento) references tb_comportamento (codigo_comportamento);

create table if not exists `tb_bugs`
(
	codigo_bug int auto_increment NOT NULL,
    bug	varchar(1000) NOT NULL,
	codigo_teste int NOT NULL,
	codigo_caso_teste int NULL,
	codigo_comportamento int NULL,
	codigo_regra int NULL,
    situacao bit NULL,
    tipo int NULL,
		primary key(codigo_bug)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

alter table tb_bugs add foreign key (codigo_teste) references tb_teste (codigo_teste);
alter table tb_bugs add foreign key (codigo_caso_teste) references tb_caso_teste (codigo_caso_teste);
alter table tb_bugs add foreign key (codigo_regra) references tb_regra_negocio (codigo_regra);
alter table tb_bugs add foreign key (codigo_comportamento) references tb_comportamento (codigo_comportamento);

create table if not exists `tb_leia_me`
(
	codigo_leia_me int auto_increment NOT NULL, 
    codigo_modulo int NOT NULL,
    leia_me varchar(2000) NOT NULL,
    versao varchar(2000) NOT NULL,
    codigo_usuario int NULL,
    data_criacao date NULL,
    data_alteracao date NULL,
    inativo bit NULL,
		primary key (codigo_leia_me)
) Engine InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;
alter table tb_feature add foreign key (codigo_modulo) references tb_modulo (codigo_modulo);
