e.Rede - Rebatedor Fe
==========================
O e.Rede Rebatedor Fe tem como objetivo simular o serviço de análise antifraude 
de uma transação.

Configurações da IDE
--------------------
Este projeto usa a biblioteca [**Lombok**](https://projectlombok.org), que deve estar
integrada ao Eclipse para que o projeto funcione corretamente na IDE. Para tal, deve-se:

1. Baixá-la [**aqui**](https://projectlombok.org/download).
2. Executá-la (duplo-clique) e seguir as instruções (ao final, o arquivo lombok.jar deve estar presente na pasta raiz da instalação do Eclipse).
3. Reiniciar o Eclipse.

Se, após o projeto ter sido importado, ele contiver erros nos métodos get e set (sintoma típico de que o Lombok não está instalado ou de que ainda não está sendo executado pela IDE), clicar em *`Project > Clean`* deve resolver o problema.

É recomendado o uso da [**Spring Tool Suite (STS)**](https://spring.io/tools/sts). Se outra versão do Eclipse for utilizada, recomenda-se instalar também o plugin **Spring Tools** pela *Eclipse Marketplace*, para suporte ao Spring Framework.

Independente da versão, deve-se instalar o plugin **Buildship Gradle Integration** pela *Eclipse Marketplace*, para permitir integração com o Gradle.

Projeto Gradle
--------------
Após clonar o repositório é recomendado executar os passos abaixo para importar o projeto: 

* No menu `File > Import...`
  * Selecionar a opção: `Gradle > Existing Gradle Project`, depois `Next >`
  * Navegar até a pasta raiz do projeto e selecione `Finish`
