CREATE TABLE produtos(

    id                  UUID PRIMARY KEY,
    nome                VARCHAR(100) NOT NULL,
    preco               DECIMAL(10,2) NOT NULL,
    quantidade          INT NOT NULL,
    datahoracadastro    TIMESTAMP NOT NULL,
    ativo               BOOLEAN NOT NULL

);