CREATE TABLE autor(
	cpf VARCHAR(15) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	PRIMARY KEY (cpf)
);

CREATE TABLE livro(
	isbn VARCHAR(20) NOT NULL,
	descricao VARCHAR(100) NOT NULL,
	edicao VARCHAR(50) NOT NULL,
	PRIMARY KEY (isbn)
);

CREATE TABLE autor_livro(
	cpf_autor VARCHAR(15) NOT NULL,
	isbn_livro VARCHAR(20) NOT NULL,
	PRIMARY KEY (cpf_autor, isbn_livro),
	FOREIGN KEY (cpf_autor) REFERENCES autor (cpf) ON DELETE CASCADE,
	FOREIGN KEY (isbn_livro) REFERENCES livro (isbn) ON DELETE CASCADE
);

CREATE TABLE emprestimo(
	id SERIAL,
	data_emprestimo DATE NOT NULL,
	nome_cliente VARCHAR(50) NOT NULL,
	situacao VARCHAR(15) NOT NULL,
	isbn_livro VARCHAR(20) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (isbn_livro) REFERENCES livro (isbn) ON DELETE CASCADE
);