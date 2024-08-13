# Password Validator API

A API de validação de senhas `app-password-validator` é um projeto Java que realiza diversas verificações de segurança em senhas.
Este projeto utiliza Java 22 e Spring Boot 3.3.2, e segue a Clean Architecture, implementando as camadas *domain*, *application* e *presentation*.

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

### Resposta da API

O JSON de resposta da API possui 3 campos:

| Nome                  | Tipo    | Descrição                                                                                                                                     |
|-----------------------|---------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| **valid**             | boolean | Informa se a senha é válida de acordo com as verificações realizadas.                                                                         |
| **validationMessage** | string  | Mensagem adicional à validação, descrevendo de forma textual se a senha é válida ou não.                                                      |
| **errors**            | list    | Lista de strings que será preenchida somente se algum erro na validação for encontrado. Nesse caso, a lista conterá a descrição de cada erro. |


### Exemplos de resposta

**Quando a senha é válida:**

```json
{
"valid": true,
"validationMessage": "Senha válida",
"errors": []
}
```

**Quando a senha não é válida:**
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

O projeto segue a Clean Architecture de acordo com a estrutura abaixo, implementando as seguintes camadas:

- **Domain**: Contém as entidades e regras de negócio.
- **Application**: Contém os casos de uso e a lógica de aplicação.
- **Presentation**: Contém os controladores e a interface com o usuário (API).

   ```plaintext
   src
   └── main
       └── java
            └── com.lucaezellner.pwdvalidator
                  ├── application
                  │   ├── dto
                  │   │   └── PasswordValidationResponseDto.java
                  │   └── usecase
                  │       └── ValidatePasswordUseCase.java
                  ├── domain
                  │   ├── entity
                  │   │   └── Password.java
                  │   ├── enums
                  │   │   └── ValidationStatus.java
                  │   └── util
                  │       └── RegexUtil.java
                  ├── presentation
                  │   └── controller
                  │       └── PasswordValidatorController.java
                  └── PwdValidatorApplication.java
   ```

## Configuração e Execução

1. **Clone o repositório**:

    ```bash
    git clone https://github.com/lucaezellner/app-password-validator.git
    cd password-validator
    ```

2. **Configure o ambiente**:
   Certifique-se de ter o Java 22 instalado. Para verificar a instalação, execute o comando abaixo:

   ```bash
   java -version
   ```

3. **Compile e execute a aplicação**:

   Certifique-se de ter o Maven 3 instalado e configurado na variável de ambiente PATH do Windows.

    ```bash
    mvn spring-boot::run
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

Os testes foram desenvolvidos para validar as entidades, usecases e controllers nos diversos cenários de
sucesso ou erro na validação e possíveis exceções lançadas durante o processamento.