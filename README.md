# dac-jsf-atividade

Para essa atividade é preciso implementar os seguintes requisitos:

* CRUD de Autor
* CRUD de Livro
* Empréstimo
* Devolução


Essa aplicação deve ser desenvolvida utilizando JSF para a apresentação, a persistência é de livre escolha do aluno. 

A implantação deve ocorrer via *Docker*.

Implantação:

Com o terminal aberto no diretório do projeto, execute os comandos abaixo:

docker build -t emprestimo/banco ./postgres

docker build -t emprestimo/app .

docker run -p 5433:5432 -d --name banco emprestimo/banco

docker run -p 8081:8080 -d --name app --link banco:banco emprestimo/app
