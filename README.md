# Password Validator API

A API de validação de senhas `password-validator` é um projeto Java que realiza diversas verificações de segurança em senhas. Este projeto utiliza Java 22 e Spring Boot 3.3.2, e segue a Clean Architecture, implementando as camadas `domain`, `application`, e `presentation`.

## Funcionalidades

A API realiza as seguintes validações de senha:

- **Comprimento mínimo**: A senha deve ter pelo menos 9 caracteres.
- **Dígito numérico**: A senha deve conter pelo menos 1 dígito numérico.
- **Letra maiúscula**: A senha deve conter pelo menos 1 letra maiúscula.
- **Letra minúscula**: A senha deve conter pelo menos 1 letra minúscula.
- **Caractere especial**: A senha deve conter pelo menos 1 caractere especial.
- **Caracteres repetidos**: A senha não deve conter caracteres repetidos.

## Tecnologias Utilizadas

- **Java 22**: Versão do Java utilizada.
- **Spring Boot 3.3.2**: Framework para desenvolvimento da API.
- **Lombok**: Biblioteca para reduzir a verbosidade do código Java.
- **OpenAPI**: Para integração com Swagger, facilitando a documentação e o teste da API.
- **Mockito**: Framework de mocking para testes unitários.
- **JUnit**: Framework de testes unitários.

## Endpoints

### Validação de Senha

- **Endpoint**: `/passwords/validates`
- **Método HTTP**: GET
- **Parâmetros**: `password` (String) - A senha a ser validada.

### Exemplo de Requisição

```http
GET /passwords/validates?password=ExemploSenha123!
```

### Exemplo de Resposta
**Quando a senha é válida:**

```json
{
"valid": true,
"validationMessage": "Senha válida",
"errors": []
}
```

**Quando a senha não é válida (OBS: a lista "errors" será preenchida conforme os erros encontrados):**
```json
{
  "valid": false,
  "validationMessage": "Foram encontradas inconsistências na validação da senha",
  "errors": [
    "A senha informada precisa conter pelo menos 1 dígito",
    "A senha informada deve conter pelo menos 1 letra maiúscula",
    "A senha informada deve conter pelo menos 1 letra minúscula",
    "A senha informada precisa conter pelo menos 1 caractere especial",
    "A senha informada deve conter pelo menos 9 caracteres"
  ]
}
```

## Estrutura do Projeto

O projeto segue a Clean Architecture com as seguintes camadas:

- **Domain**: Contém as entidades e regras de negócio.
- **Application**: Contém os casos de uso e a lógica de aplicação.
- **Presentation**: Contém os controladores e a interface com o usuário (API).

## Configuração e Execução

1. **Clone o repositório**:

    ```bash
    git clone https://github.com/usuario/password-validator.git
    cd password-validator
    ```

2. **Configure o ambiente**:
   Certifique-se de ter o Java 22 instalado.

3. **Compile e execute a aplicação**:

   Certifique-se de ter o Maven 3.9.8 instalado.

    ```bash
    mvn spring-boot
    ```

4. **Acesse a documentação da API**:

   Após iniciar a aplicação, a documentação gerada pelo Swagger estará disponível em:

    ```bash
    http://localhost:8080/swagger-ui.html
    ```

## Testes

Para executar os testes unitários, use o comando:

```bash
mvn test
```