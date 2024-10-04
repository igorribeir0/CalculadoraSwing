# Calculadora em Java

Este projeto é uma implementação de uma calculadora simples em Java usando a biblioteca Swing para a interface gráfica. Ele foi estruturado com base no padrão Observer, permitindo que o display da calculadora seja atualizado automaticamente sempre que houver uma alteração nos valores inseridos pelo usuário.

## Funcionalidades

- **Teclado numérico e de operações**: Interface de botões para números e operadores (adição, subtração, multiplicação, divisão, etc.).
- **Display**: Exibe os números inseridos e o resultado da operação.
- **Cálculos básicos**: Suporte a operações aritméticas básicas.

## Estrutura do Projeto

O projeto é dividido em dois pacotes principais:

1. **`br.Igorribeiro.visao`**: Contém as classes relacionadas à interface gráfica do usuário (GUI).
   - `Teclado`: Classe que representa o teclado da calculadora, com botões para números e operadores.
   - `Display`: Classe que representa o display da calculadora, que exibe os valores e resultados das operações.
   
2. **`modelo`**: Contém a lógica principal e os observadores.
   - `Memoria`: Classe responsável por gerenciar o estado atual da calculadora e processar os comandos de entrada.
   - `MemoriaObserver`: Interface para observar mudanças no estado da memória.

### Classes Principais

#### `Teclado.java`
- Organiza os botões da calculadora em um layout de grade (GridBagLayout).
- Cada botão tem uma cor específica (números, operadores, etc.).
- Ao clicar em um botão, ele aciona o método `actionPerformed`, que passa o comando para a classe `Memoria`.

#### `Display.java`
- Observa a `Memoria` e exibe o valor atual no display.
- O display é atualizado automaticamente sempre que há uma mudança no valor processado.

#### `Memoria.java`
- Gerencia o estado da calculadora e os comandos de entrada.
- Armazena os valores digitados e as operações em andamento.
- Notifica os observadores (como o `Display`) sempre que o valor atual muda.

## Dependências

- **Java 8+**
- **Biblioteca Swing**: Para a interface gráfica do usuário.

## Como Executar

1. Clone o repositório em sua máquina local:
   ```bash
   git clone https://github.com/seu-usuario/calculadora-java.git
