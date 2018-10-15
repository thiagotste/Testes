insert into biblioteca (nome, email, endereco, cep, cidade, uf, telefone1, telefone2)
values('Bilioteca Fatec', 'fatec@fatec.sp.gov.br', 'Av. Bartolomeu de Gusmão, 110', 
'11045908', 'Santos',  'SP', '(13) 3227-6003','');
select * from biblioteca;

insert into cliente(pri_nome, ult_nome, email, senha, cpf, sexo, dt_nascimento, endereco,
complemento, cep, cidade, uf, telefone1, telefone2)
values('thiago', 'ferreira', 'thiago@thiago.com', 'thiago@thiago.com123456', '381908028-79', 'm', '1986-11-16', 
'R. Constâncio Vaz Guimarães, 10', 'apto 06', '11045-220', 'Santos', 'SP', '(!3) 3326-0872', '(13) 99173-7728');
select * from cliente;
 
insert into escritor (pri_nome, ult_nome)
values('Suzanne', 'Collins'),
('Jules', 'Verne'),
('Stephen', 'King'),
('Arthur Conan', 'Doyle');
select * from escritor;
 
insert into tipo_livro (nome)
value('Aventura, Fantasia, Ficção Científica e Guerra'),
('science Fiction, Suspense');
select * from tipo_livro;

insert into livro
values(1, 1, 1, 1, 'The Hunger Games: Mockingjay', 'Scholastic Press', '1', 'Primeira Edição','2010', '2014-07-16'),
(2, 1, 1, 1, 'The Hunger Games', 'Scholastic Press', '1', 'Primeira Edição','2010', '2014-07-16'),
(3, 1, 1, 1, 'The Hunger Games:  Catching Fire', 'Scholastic Press', '1', 'Primeira Edição','2010', '2014-07-16'),
(1, 2, 1, 1, 'Twenty Thousand Leagues under the Sea', 'Project Gutenberg', '1', '','2008', '2014-11-01'),
(1, 3, 1, 1, 'Under the Dome: A Novel', 'Gallery Books', '1', '','2013', '2014-08-18'),
(1, 4, 1, 1, 'His Last Bow', 'Feedbooks', '1', '','1917', '2014-06-02');
select * from livro;

insert into emprestimo
values(1, 1, '2014-12-03', '2014-12-10');
select * from emprestimo;

insert into funcionario (Cod_Biblioteca , pri_nome, ult_nome, email, cpf, senha, sexo, dt_nascimento, endereco,
complemento, cep, cidade, uf, telefone1, telefone2, Tipo)
values(1, 'thiago', 'ferreira', 'thiago@thiago.com', '381908028-79', 'thiago@thiago.com123456', 'm', '1986-11-16', 
'R. Constâncio Vaz Guimarães, 10', 'apto 06', '11045-220', 'Santos', 'SP', '(!3) 3326-0872', '(13) 99173-7728', 'administrador');

select * from funcionario;