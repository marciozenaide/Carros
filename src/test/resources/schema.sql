CREATE TABLE IF NOT EXISTS carro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    url_foto VARCHAR(255),
    url_video VARCHAR(255),
    latitude DOUBLE,
    longitude DOUBLE,
    tipo VARCHAR(255)
);

-- Opcional: Inserir os dados base que seu teste 'testListaCarros' procura
DELETE FROM carro;
INSERT INTO carro (nome, descricao, tipo) VALUES ('Ferrari F40', 'Esportivo Vermelho', 'esportivo');
INSERT INTO carro (nome, descricao, tipo) VALUES ('Bugatti Veyron', 'Superesportivo', 'esportivo');