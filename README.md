# API de entregas

API de entregas feita com Spring Boot, banco Mysql e Flywaydb para versionamento do banco.

## Rotas da API

**Cliente:**

- `POST /clientes`: Cria um novo cliente. Exemplo:

**Body:**

```
{
	"nome":"eduardo",
	"email":"maiara@gmail.com",
	"telefone":"2345422221"
}
```

Retorna ele mesmo com o id.

- `GET /clientes`: Retorna uma lista de clientes.

```
[
	{
		"id": 1,
		"nome": "jose",
		"email": "jose@gmail.com",
		"telefone": "8999277333"
	},
	{
		"id": 2,
		"nome": "vicente",
		"email": "vicent@gmail.com",
		"telefone": "439927322"
	}
]
```

- `GET /clientes/{id}`: Retorna um cliente. Exemplo:

```
	{
		"id": 2,
		"nome": "vicente",
		"email": "vicent@gmail.com",
		"telefone": "439927322"
	},
```

- `PUT /clientes/{id}`: Altera um cliente. Exemplo:
  **Body:**

```
	{
		"nome": "vicente santos",
		"email": "vicent@gmail.com",
		"telefone": "439927322"
	},
```

Retorna ele próprio modificado e com id
**Entrega:**

- `POST /entregas`: Cria uma entrega. Você deve informar o cliente que vai enviar, o destinatário com os dados de endereço e uma taxa. Exemplo:

**Body:**

```
{
	"cliente":{
		"id":6
	},
	"destinatario":{
		"nome":"William bonner",
		"logradouro":"Travessa vinicius santoro",
		"complemento":"sem",
		"numero":"653",
		"bairro":"centro"
	},
	"taxa":27
}
```

**Retorno:**

```
{
	"id": 5,
	"cliente": {
		"id": 6,
		"nome": "maiara neves dos santos"
	},
	"destinatario": {
		"nome": "William bonner",
		"logradouro": "Travessa vinicius santoro",
		"numero": "653",
		"complemento": "sem",
		"bairro": "centro"
	},
	"taxa": 27,
	"dataPedido": "2022-01-14T16:31:23.5780375-03:00",
	"dataFinalizacao": null
}
```

- `GET /entregas`: Retorna todas as entregas. Exemplo:

```
[
	{
		"id": 1,
		"cliente": {
			"id": 3,
			"nome": "alice"
		},
		"destinatario": {
			"nome": "carminha",
			"logradouro": "rua 45 b",
			"numero": "234",
			"complemento": "casa rosa",
			"bairro": "centro"
		},
		"status": "FINALIZADA",
		"taxa": 34.00,
		"dataPedido": "2022-01-14T10:02:24-03:00",
		"dataFinalizacao": "2022-01-14T20:40:08-03:00"
	},
	{
		"id": 2,
		"cliente": {
			"id": 2,
			"nome": "vicente"
		},
		"destinatario": {
			"nome": "raul gil",
			"logradouro": "rua augusto cesar",
			"numero": "89",
			"complemento": "proximo a padaria",
			"bairro": "centro"
		},
		"status": "PENDENTE",
		"taxa": 98.00,
		"dataPedido": "2022-01-14T12:45:14-03:00",
		"dataFinalizacao": null
	}
]
```

- `GET /entregas/{id}`: Retorna uma entrega. Exemplo:

```
	{
		"id": 1,
		"cliente": {
			"id": 3,
			"nome": "alice"
		},
		"destinatario": {
			"nome": "carminha",
			"logradouro": "rua 45 b",
			"numero": "234",
			"complemento": "casa rosa",
			"bairro": "centro"
		},
		"status": "FINALIZADA",
		"taxa": 34.00,
		"dataPedido": "2022-01-14T10:02:24-03:00",
		"dataFinalizacao": "2022-01-14T20:40:08-03:00"
	}
```

- `PUT /entregas/{id}/finalizacao`: Finaliza uma entrega.

**Body:** vazio.

**Retorno:** vazio, no content.

- `POST /entregas/{id}/ocorrencias`: Cadastra uma ocorrência na entrega. Exemplo:

**Body:**

```
{
  "descricao":"recusou  a encomenda porque passou do prazo"
}
```

**Retorno:**

```
{
	"id": 3,
	"descricao": "recusou  a encomenda porque passou do prazo",
	"dataRegistro": "2022-01-14T20:04:53.712734-03:00"
}
```

- `GET /entregas/{id}/ocorrencias`: Retorna todas as ocorrências de uma entrega. Exemplo:

```
[
	{
		"id": 2,
		"descricao": "Não havia ninguém para receber o pacote",
		"dataRegistro": "2022-01-14T20:04:16-03:00"
	},
	{
		"id": 3,
		"descricao": "recusou  a encomenda porque passou do prazo",
		"dataRegistro": "2022-01-14T20:04:54-03:00"
	}
]
```

## Tecnologias

As seguintes tecnologias foram usadas:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Mysql](https://www.mysql.com)
- [Flyway](https://flywaydb.org/)
- [ModelMapper](http://modelmapper.org/)

## Execução

Acesse o arquivo _application.properties_ em _src/main/resources_ e modifique conforme suas configurações.

```
spring.datasource.url=jdbc:mysql://{HOST_DATABASE}/{DATABASE}?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username={USUARIO}
spring.datasource.password={SENHA}
```

Execute o comando para rodar a API

```
mvn spring-boot:run
```

Assim que a aplicação for iniciada o Flyway vai executar todas as migrations para criar as tabelas no banco.

Por default, a API está disponível no endereço [http://localhost:8080/](http://localhost:8080/)
