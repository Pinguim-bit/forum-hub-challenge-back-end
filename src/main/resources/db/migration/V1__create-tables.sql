-- ========================
-- USUARIOS
-- ========================
CREATE TABLE usuarios (
                          id    BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nome  VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL
);

-- ========================
-- PERFIS
-- ========================
CREATE TABLE perfis (
                        id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome        VARCHAR(255) NOT NULL,
                        usuario_id  BIGINT NOT NULL,

                        CONSTRAINT fk_perfis_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- ========================
-- CURSOS
-- ========================
CREATE TABLE cursos (
                        id        BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome      VARCHAR(255) NOT NULL,
                        categoria VARCHAR(255) NOT NULL
);

-- ========================
-- TOPICOS
-- ========================
CREATE TABLE topicos (
                         id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                         titulo       VARCHAR(255) NOT NULL,
                         mensagem     TEXT NOT NULL,
                         data_criacao DATETIME NOT NULL,
                         status       VARCHAR(50) NOT NULL,
                         autor_id     BIGINT NOT NULL,
                         curso_id     BIGINT NOT NULL,

                         CONSTRAINT fk_topicos_autor FOREIGN KEY (autor_id) REFERENCES perfis(id),
                         CONSTRAINT fk_topicos_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

-- ========================
-- RESPOSTAS
-- ========================
CREATE TABLE respostas (
                           id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                           mensagem     TEXT NOT NULL,
                           data_criacao DATETIME NOT NULL,
                           solucao      VARCHAR(255),
                           topico_id    BIGINT NOT NULL,
                           perfil_id    BIGINT NOT NULL,

                           CONSTRAINT fk_respostas_topico FOREIGN KEY (topico_id) REFERENCES topicos(id),
                           CONSTRAINT fk_respostas_autor  FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);