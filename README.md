
# Desafio Duxus Estágio

## Como testar a API

### 1. Cadastrar Integrante
**POST /integrantes**
```json
{
  "nome": "Bangalore",
  "franquia": "Apex Legends",
  "funcao": "Suporte"
}
```

### 2. Listar Integrantes
**GET /integrantes**

---

### 3. Cadastrar Time
**POST /times**
```json
{
  "data": "2024-07-28",
  "integrantes": [
    { "id": 1, "nome": "Bangalore", "franquia": "Apex Legends", "funcao": "Suporte" },
    { "id": 2, "nome": "Bloodhound", "franquia": "Apex Legends", "funcao": "Scout" }
  ]
}
```

### 4. Listar Times
**GET /times**

---

### 5. Consultar Time da Data
**GET /processamento/time-da-data?data=2024-07-28**

### 6. Integrante Mais Usado
**GET /processamento/integrante-mais-usado?dataInicial=2024-07-01&dataFinal=2024-07-28**

### 7. Integrantes do Time Mais Comum
**GET /processamento/time-mais-comum?dataInicial=2024-07-01&dataFinal=2024-07-28**

### 8. Função Mais Comum
**GET /processamento/funcao-mais-comum?dataInicial=2024-07-01&dataFinal=2024-07-28**

### 9. Franquia Mais Famosa
**GET /processamento/franquia-mais-famosa?dataInicial=2024-07-01&dataFinal=2024-07-28**

### 10. Contagem por Franquia
**GET /processamento/contagem-por-franquia?dataInicial=2024-07-01&dataFinal=2024-07-28**

### 11. Contagem por Função
**GET /processamento/contagem-por-funcao?dataInicial=2024-07-01&dataFinal=2024-07-28**

---

## Exemplos de Resposta Esperada

#### Exemplo: Time da Data
```json
{
  "id": 1,
  "data": "2024-07-28",
  "composicaoTime": [
    { "id": 1, "integrante": { "id": 1, "nome": "Bangalore", "franquia": "Apex Legends", "funcao": "Suporte" } },
    { "id": 2, "integrante": { "id": 2, "nome": "Bloodhound", "franquia": "Apex Legends", "funcao": "Scout" } }
  ]
}
```

#### Exemplo: Função Mais Comum
```json
{
  "função": "Suporte"
}
```

#### Exemplo: Contagem por Franquia
```json
{
  "Apex Legends": 5,
  "Overwatch": 2
}
```

---

## Observações
- Todos os endpoints aceitam e retornam JSON.
- Datas devem ser enviadas no formato `YYYY-MM-DD`.
- Para testar, utilize ferramentas como Postman, Insomnia ou Swagger UI.
- O sistema armazena os dados em memória (ao reiniciar, os dados são perdidos).

---
