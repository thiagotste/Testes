if db_id('DB_Bliblioteca') is not null
	drop database DB_Bliblioteca;

create database DB_Bliblioteca;

use DB_Bliblioteca;

create table Biblioteca
(
	Cod_Biblioteca int not null primary key identity,
	Nome varchar(50) not null,
	email varchar(50) not null,
	Endereco varchar(50) not null,
	CEP char(9) not null,
	Cidade varchar(50) not null,
	UF char(2) not null,
	Telefone1 char(15) not null,
	Telefone2 char(15)
);
create table Funcionario
(
	Cod_Funcionario int not null primary key identity,
    Cod_Biblioteca int not null,
    Pri_Nome varchar(50) not null,
	Ult_Nome varchar(50) not null,
	Email varchar(50) not null unique,
    Senha varchar(50) not null,
	CPF char(14) not null unique,
    Sexo char(1) not null,
    Dt_nascimento date not null,
	Endereco varchar(50) not null,
    complemento varchar(20),
	CEP char(9) not null,
	Cidade varchar(50) not null,
	UF char(2) not null,
	Telefone1 char(14) not null,
	Telefone2 char(15),
    Tipo varchar(20) not null,
    
    constraint Biblioteca_fk_Funcionario
    foreign key (Cod_Biblioteca)
    references biblioteca(Cod_Biblioteca)
);

create index IX_Funcionario
on Funcionario(Cod_Biblioteca);

create table Cliente
(
	Cod_Cliente int not null primary key identity,
	Pri_Nome varchar(50) not null,
	Ult_Nome varchar(50) not null,
	Email varchar(50) not null unique,
    Senha varchar(50) not null,
	CPF char(14) not null unique,
    Sexo char(1) not null,
    Dt_nascimento date not null,
	Endereco varchar(50) not null,
    complemento varchar(20),
	CEP char(9) not null,
	Cidade varchar(50) not null,
	UF char(2) not null,
	Telefone1 char(14) not null,
	Telefone2 char(15)
);
create table Escritor
(
	Cod_Escritor int not null primary key identity,
	Pri_Nome varchar(50) not null,
	Ult_Nome varchar(50) not null
);
create table Tipo_livro
(
	Cod_Tipo int not null primary key identity,
	Nome varchar(50) not null
);
create table Livro
(
	Cod_Livro int not null,
	Cod_Escritor int not null,
	Cod_Tipo int not null,
	Cod_Biblioteca int not null,
	Nome varchar(50) not null,
	Editora varchar(50) not null,
	Vol varchar(50) not null,
	Edicao varchar(50) not null,
    Idioma varchar(30) not null,
    Num_Pag varchar(10) not null,
	Ano_Lancamento char(4),
	Dt_Aquisicao date not null,
    Descricao text not null,
    Formato varchar(20) not null,
    Situacao char(1) not null default '0',

	constraint pk_livro
	primary key(Cod_Livro, Cod_Escritor)
	
);

alter table Livro
add constraint livro_fk_escritor
	foreign key (Cod_Escritor)
	references Escritor(Cod_Escritor);

alter table Livro 
add constraint livro_fk_biblioteca
	foreign key (Cod_Biblioteca)
	references Biblioteca (Cod_Biblioteca);

alter table Livro 
add	constraint livro_fk_tipo_livro
	foreign key (Cod_Tipo)
	references Tipo_Livro (Cod_Tipo);

create index IX_Livro
on Livro(Cod_Tipo, Cod_Biblioteca);


create table Emprestimo_Funcionario(
    Cod_Funcionario int not null,
    Cod_Livro int not null,
    Cod_Escritor int not null,
    Dt_emprestimo date not null,
    Dt_entrega date not null,
    Situacao char(1) default 1	
);

alter table emprestimo_Funcionario
add constraint emprestimo_funcionario_fk_funcionario
    foreign key (Cod_Funcionario)
    references funcionario (Cod_Funcionario);

alter table emprestimo_funcionario
add constraint emprestimo_funcionario_fk_livro
    foreign key (Cod_Livro, Cod_escritor)
    references Livro (Cod_Livro, Cod_escritor);

create index IX_emprestimo_funcionario_cod_funcionario_cod_livro_cod_escritor
on emprestimo_funcionario(cod_funcionario, cod_livro, cod_escritor);

create table Emprestimo(
    Cod_Cliente int not null,
    Cod_Livro int not null,
    Cod_Escritor int not null,
    Dt_emprestimo date not null,
    Dt_entrega date not null,
    Situacao char(1) default 1

);

alter table emprestimo
add constraint emprestimo_fk_cliente
    foreign key (Cod_Cliente)
    references cliente (Cod_Cliente);
alter table emprestimo
add constraint emprestimo_fk_livro
    foreign key (Cod_Livro, Cod_Escritor)
    references Livro (Cod_Livro, Cod_Escritor);

create index IX_emprestimo_cod_cliente_cod_livro_cod_escritor
on emprestimo(cod_cliente, cod_livro, cod_escritor);