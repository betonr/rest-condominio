CREATE SCHEMA condominio;

use condominio;

CREATE TABLE usr_usuario (
  USR_ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  USR_NOME VARCHAR(20) NOT NULL,
  USR_SENHA VARCHAR(50) NOT NULL,
  USR_CPF VARCHAR(50) NOT NULL,
  USR_CELULAR VARCHAR(50) NOT NULL,
  USR_APARTAMENTO VARCHAR(50) NOT NULL,
  USR_CARRO VARCHAR(50),
  USR_BLOCO VARCHAR(50) NOT NULL,
  USR_ANIMAL_ESTIMACAO BOOLEAN,
  PRIMARY KEY (USR_ID),
  UNIQUE KEY UNI_USUARIO_NOME (USR_NOME)
);

CREATE TABLE aut_autorizacao(
  AUT_ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  AUT_NOME VARCHAR(20) NOT NULL,
  PRIMARY KEY (AUT_ID),
  UNIQUE KEY UNI_AUT_NOME (AUT_NOME)
);

CREATE TABLE uau_usuario_autorizacao(
  USR_ID BIGINT UNSIGNED NOT NULL,
  AUT_ID BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (USR_ID, AUT_ID),
  FOREIGN KEY AUT_USUARIO_FK (USR_ID) REFERENCES usr_usuario (USR_ID) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY AUT_AUTORIZACAO_FK (AUT_ID) REFERENCES aut_autorizacao (AUT_ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE ag_agenda (
  AG_ID BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  AG_OBSERVACAO VARCHAR(100) NOT NULL,
  AG_DATA_HORA DATETIME NOT NULL,
  USR_CRIACAO_ID BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (AG_ID),
  FOREIGN KEY AG_USR_FK (USR_CRIACAO_ID) REFERENCES usr_usuario(USR_ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO 
	usr_usuario(USR_NOME, USR_SENHA, USR_CPF, USR_CELULAR, USR_APARTAMENTO, USR_CARRO, USR_BLOCO, USR_ANIMAL_ESTIMACAO) 
	VALUES('admin', CONCAT('{MD5}', MD5('admin')), '44493057874', '12982004877', '407', 'dwd-3420', 'bloco 7', 1);
	
INSERT INTO 
	aut_autorizacao(AUT_NOME) VALUES('ROLE_USUARIO');
INSERT INTO 
	aut_autorizacao(AUT_NOME) VALUES('ROLE_ADMIN');
	
INSERT INTO uau_usuario_autorizacao(USR_ID, AUT_ID)
	SELECT USR_ID, AUT_ID
	FROM usr_usuario, aut_autorizacao
	WHERE USR_NOME = 'admin'
	AND AUT_NOME = 'ROLE_ADMIN';