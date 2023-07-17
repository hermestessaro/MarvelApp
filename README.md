
# Marvel App

Uma aplicação que consome a Marvel API para exibir informações sobre personagens e HQs. Este app foi criado utilizando o Jetpack Compose para a UI e a biblioteca Retrofit para conexão e troca de informações com a API. Para a exibição das informações paginadas, foi utilizada a biblioteca Paging3, além de Room para persistência dos dados mesmo sem acesso à internet.


## Funcionalidades

O app busca informações da API e as exibe de forma paginada, antes as salvando em um banco de dados local. É possível navegar por duas telas, uma que lista personagens e outra que lista quadrinhos. Ao clicar em qualquer item, uma tela de detalhes aparecerá contendo informações (caso disponíveis) sobre aquele item.

Os possíveis erros em busca de informações são reportados utilizando Toast, e, em caso de falta de conexão com internet, uma mensagem é exibida nas telas de listagem.

Sem internet, o app apresenta as listas desde que elas tenham sido carregadas no banco de dados em algum outro momento.


