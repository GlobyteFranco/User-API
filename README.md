# User-API
Proyecto propuesto para entregar por Dani en el que se utilizaran los contenidos vistos sobre Springboot entre otros

Comandos CURL:

Registro:
curl -X POST http://localhost:8080/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "nuevo@mail.com",
    "password": "123456"
  }'

Login:
curl -X POST http://localhost:8080/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "nuevo@mail.com",
    "password": "123456"
  }'

Login con password incorrecta:
  curl -X POST http://localhost:8080/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "nuevo@mail.com",
    "password": "wrongpass"
  }'

Login con usuario inexistente:
  curl -X POST http://localhost:8080/users/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "noexiste@mail.com",
    "password": "123456"
  }'





