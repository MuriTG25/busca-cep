# Busca Cep
### Sobre o projeto 
Esse projeto foi baseado num conteúdo extra de um curso de android da Alura. Esse conteúdo simulava uma tela de cadastro, onde inseria o CEP num campo de texto e os dados restantes seriam preenchidos de forma automática. </br>

Essa busca é feita através de uma requisição API com o [Via Cep](https://viacep.com.br/), utilizando o Ktor para isso.</br>

É um projeto Android em Kotlin, que  roda em aparelhos android a partir da versão 7.0 (SDK 24) até a versão mais atual (até o momento), a 14 (SDK 34).</br>

Ele usa principios de arquitetura android e utiliza o modelo MVVM.</br>

Diferente do projeto original, teremos 2 telas. A primeira tela, a tela de busca, onde colocaremos o CEP, e ao clicar no botão de buscar, será feito as validações dos dados, para depois fazer a busca. </br>
E na segunda tela, a tela de resultado, dependendo do resultado obtido, mostrará resultados diferentes. Podendo mostrar tela de erro, por problemas de conexão ou até mesmo valor inválido. Obtendo sucesso, mostrará o resultado, onde podemos copiar esses dados.</br>

Contaremos com o Navigation para transição de telas, Hilt para injeção de dependências, Jetpack Compose e Material 3 para a construção das UI.

# Tela de Busca

Nesta tela, temos o logo do aplicativo no topo, além de outros 2 componentes.</br>

O primeiro componente é o campo de texto, no qual vamos preencher o CEP desejado. Para termos uma validação mais garantida do resultado, inserimos limitações nele, como:
 - Teclado na opção que aparece apenas números, sem símbolos.
 - O campo de texto aceita apenas 8 dígitos na inserção. Caso digite mais, esse número não será contado.
 - Na tela, o valor terá o aspecto visual com o traço após o 5º número (xxxxx-xxx), mas internamente ele está guardando apenas os números (xxxxxxxx).

O segundo componente é o botão de busca. Antes de realizar a busca ele vai fazer uma série de validações no valor, como:
 - Se o valor estiver vazio, aparecerá uma mensagem acima do campo de texto avisando que o preenchimento do CEP é obrigatório. Aviso que sumirá após inserir pelo menos 1 caractere.
 - Só é aceito caracteres que sejam números, caso haja simbolos, letras, não será aceito. Mesmo que seja virgula ",", formando um número não inteiro, ou menos "-", que forma um número negativo, também não será aceito
 - Aceita apenas 8 digitos. Se tiver menos, ele não aceitará. E devido ao limitador do campo de texto, não é possível inserir mais que 8 digitos.
 - Apresentando todos esses erros, aparecerá um dialog avisando sobre o erro de inserção de caracteres inválidos ou da quantidade de digitos inválido.
 
 Passando das validações, faremos uma requisição GET com o valor do cep na API do Via CEP, e iremos para a tela de resultados. </br>

 # Tela de Resultado

Dentro da tela de resultado, temos 4 sub-telas diferentes. A tela de carregamento, tela de erro de conexão, tela de CEP inválido, e tela de CEP válido </br>

### Tela de carregamento

Enquanto a requisição não estiver completa, aparece esta tela. Nela contém um icone redondo de carregamento, indicando que ainda está sendo feito a busca. Após concluir a requisição, podemos mostrar uma das outras telas.

### Tela de erro de conexão

Caso haja algum erro de conexão nesta requisição (status HTTP não 2xx), mostraremos uma tela onde avisa que houve um erro de conexão. </br>

Nele contém uma mensagem falando sobre o erro e 2 botões:
 - O primeiro deles, é o botão de recarregar, onde faremos uma nova requisição, utilizando o mesmo dado do CEP.
 - E o segundo, que o botão de voltar, onde retornaremos para a tela de busca.

 ### Tela de CEP inválido

 Ao fazer a requisição no Via Cep, se o valor digitado for inválido, haverá um retorno apenas apresentando o valor de error como true. </br>

 Sendo assim, haverá uma mensagem que indica que o CEP buscado é inválido, com o botão de voltar para a tela de busca.

### Tela de CEP válido

Caso haja um retorno positivo, será mostrado na tela os seguintes valores:
 - CEP
 - Logradouro
 - Complemento (se tiver)
 - bairro
 - cidade
 - estado

 Todos estes valores são copiáveis através do SelectionContainer, que está envolto apenas nos valores, e não no título do campo. Fazendo com que o usuário possa copiar apenas um dos valores de resultado.</br>

E caso queira copiar tudo, temos um botão, que apenas com o clique, pegamos todos esses valores, colocamos em apenas 1 string, e com a ajuda do Clipboard Manager, envia esse valor completo para o Clipboard.</br>

E temos o botão de voltar para a tela de busca.</br>

# Sobre a requisição API

Como dito anteriormente, o nosso aplicativo faz a requisição API com o Via Cep. Para fazer isso, utilizamos o Ktor, com a engine CIO.</br>

Essa biblioteca fará essa conexão. Primeiramente enviaremos o dado do CEP através da url, com este formato, fazendo a requisição Get: </br>

    viacep.com.br/ws/--cep--/json/ 

Formato que pede os dados deste CEP e com a response em formato de Json. </br>

Para receber a response em Json, temos o plugin Content Negotiation e o Serialization Json, que recebe este Json e converte automaticamente na classe EnderecoResponse. </br>

E este Json recebe todos esses dados:
 - CEP
 - Logradouro
 - Complemento 
 - Bairro
 - Localidade
 - UF
 - IBGE
 - Gia
 - DDD
 - Siafi
 - Error (se for um cep inválido)

Mas ao fazer a requisição, pedimos para ignorar o IBGE, Gia, DDD, Siafi e colocar o resto no objeto do EnderecoResponse.</br>

Após feita a requisição, o Repositório colocará este resultado dentro de um flow. Além disso, tem um catch que captura as exceções(vindas de status de 3xx para cima):
 - RedirectResponseException (status 3xx)
 - ClientRequestException (status 4xx)
 - ServerResponseException (status 5xx)

Capturado essas exceções, retorna um empty flow, que será tratado no View Model da tela de resultados.

# Testes Automatizados

Temos os 3 níveis de testes automatizados, teste de unidade, teste de integração e teste de UI.

### Teste de unidade

Testes feitos através do Kluent, que verifica funções de validação de dados.

### Teste de integração

Testes feitos no Mockk, que verifica a integração entre o repositório e o service.

### Teste de Integração

Testes feitos com aplicativo rodando no emulador de android ou dispositivo físico com ajuda do Junit de Android. Nele temos 2 categorias, um teste que utiliza do plugin Client Mock do Ktor para simular um servidor, no qual faremos simulações de respostas diferentes:

 - Status não 2xx
 - Cep Inválido
 - Cep Válido

Ele serve para fazermos os testes de comportamento, analisar o componente da tela sem ficar fazendo requisições reais, correndo o risco de estourar o limite diário de requisições no servidor.</br>

Contamos também com testes rodando a requisição real, para testar na prática a requisição API.

# Plugin utilizados

 - Ktor Client
 - Ktor CIO
 - Ktor Content Negotiation 
 - Ktor Serialization Json
 - Ktor Client Mock
 - Navigation Compose
 - Jetpack Compose
 - Hilt
 - Junit
 - Junit Android
 - Mockk
 - Kluent
 - Ui Automator
 - Espresso
 - Material 3


