/* ***************************************************************
* Autor............: Iury Ramos Sodre
* Matricula........: 202310440
* Inicio...........: 21.05.2024
* Ultima alteracao.: 26.05.2024
* Nome.............: Principal
* Funcao...........: Utiliza de todas as classes do projeto pra fazer efetivamente a animacao
*************************************************************** */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import controles.ControleAnimacao; 
import controles.ControleTelaInicial;
import controles.CaminhosTrem;
import controles.thread.Movimentacao;

@SuppressWarnings("unused")

public class Principal extends Application {

  public static void main(String[] args) {
    launch(args);
  }

/* ***************************************************************
* Metodo: start
* Funcao: define e inicia a cena que sera mostrada em tela
* Parametros: recebe um objeto do tipo Stage queeh usado para inicializar o programa
* Retorno: void
*************************************************************** */

  @Override
  public void start(Stage stage) throws Exception {
    
    FXMLLoader layout = new FXMLLoader(getClass().getResource("./cenas/telaInicial.fxml")); //Armazena em layout o arquivo fxml desejado
    Parent root = layout.load();  //Define na variavel root o carregamento do layout feito anteriormente
 
    Scene tela = new Scene(root,999,563); //Armazena em Tela o layout e define o tamanho fixo da tela.
   
    stage.setResizable(false); // mantem o tamanho fixo
    //Adiciona e define o titulo e o icone da barra superior do programa
    stage.setTitle("D20 Trilhos");
    Image icone = new Image("./img/iconeBarra.jpg");
    stage.getIcons().add(icone);
    
    stage.setScene(tela);//Define qual tela sera mostrada
    
    stage.show();//Mostra a cena do programa

    stage.setOnCloseRequest(e -> System.exit(0));
    
  }// fim do start
  
}//fim da classe Principal