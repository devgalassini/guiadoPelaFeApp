# **Guiado pela Fé - App de Localização de Igrejas Evangélicas**



  <div align="center">
    <img src="app/src/main/res/drawable/imag1.png" width="200"/>
</div>




## Descrição

O **Guiado pela Fé** é um aplicativo Android desenvolvido em **Kotlin** que permite aos usuários localizar **igrejas evangélicas** em tempo real, dentro de um raio específico, para que eles possam encontrar um local de adoração próximo de onde estão. O aplicativo utiliza a **geolocalização** para identificar a posição do usuário e buscar igrejas evangélicas nas proximidades, mostrando informações sobre cada igreja em tempo real.

## Funcionalidades

- **Login**: O usuário pode se autenticar para acessar o aplicativo.
- **Geolocalização**: O app acessa a localização em tempo real do usuário para encontrar igrejas próximas.
- **Busca de Igrejas**: Baseado na localização do usuário, o app encontra igrejas evangélicas próximas dentro de um raio de X metros.
- **Exibição de Resultados**: Mostra na tela a lista de igrejas encontradas com detalhes como nome, endereço, dia e hoario de funcionamento e distância.
- **Navegação para a Igreja**: O usuário pode clicar em uma igreja e ser redirecionado para um mapa com direções para a igreja.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação utilizada para desenvolver o app.
- **Android SDK**: Plataforma para o desenvolvimento de aplicativos Android.
- **Google Maps API**: Para mostrar a localização das igrejas e o caminho até elas.
- **Firebase Authentication**: Para gerenciar o login e autenticação do usuário.
- **Geofencing**: Para criar uma área de busca e encontrar igrejas em um raio específico.
- **Retrofit**: Para realizar chamadas à API e obter os dados sobre igrejas.
- **Room Database**: Para armazenar informações sobre igrejas localizadas pelo usuário.

