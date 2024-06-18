/* ***************************************************************
* Autor............: Iury Ramos Sodre
* Matricula........: 202310440
* Inicio...........: 21.05.2024
* Ultima alteracao.: 26.05.2024
* Nome.............: ControleTelaInicial
* Funcao...........: Criacao de uma animacao de trens percorrendo os trilhos
*************************************************************** */
package controles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ControleTelaInicial implements Initializable{
  @FXML
  private Button botaoIniciar;

  @FXML
  private ChoiceBox<String> caixaSolucao; //Caixa de selecao da solucao desejada

  private String[] opsolucao = {"Variavel de Travamento", "Estrita Alternancia", "Solucao de Peterson"}; //as opcoes que estarao na caixa
  public static String solucaoSelecionada;
   
  private Stage stage;
  private Scene tela;
  private Parent root;


/* ***************************************************************
* Metodo: mudePraTelaPrincipal
* Funcao: muda a tela para onde ocorre a animacao
* Parametros: recebe um evento que eh o clique do mouse no botao
* Retorno: void
*************************************************************** */
  @FXML
  public void mudePraTelaPrincipal(ActionEvent event) throws IOException{
    FXMLLoader layout = new FXMLLoader(getClass().getResource("../cenas/telaPrincipal.fxml")); //Armazena em layout o arquivo fxml desejado
    root = layout.load();  //Define na variavel root o carregamento do layout feito anteriormente

    stage = (Stage)((Node)event.getSource()).getScene().getWindow();// Obtém o Node que disparou o evento e o converte para o tipo Node e obtém a Scene desse Node e, a partir dela, obtém a janela (Stage) associada

    tela = new Scene(root);
    stage.setResizable(false); // mantem o tamanho fixo

    //Adiciona e define o titulo e o icone da barra superior do programa:
    stage.setTitle("D20 Trilhos");
    Image icone = new Image("./img/iconeBarra.jpg");
    stage.getIcons().add(icone);
    
    stage.setScene(tela);//Define qual tela sera mostrada
    
    stage.show();//Mostra a cena do programa

  }//fim do mudePraTelaPrincipal

/* ***************************************************************
* Metodo: mudePraTelaInicial
* Funcao: muda a tela de apresentacao
* Parametros: recebe um evento que eh o clique do mause no botao
* Retorno: void
*************************************************************** */
  @FXML
  public void mudePraTelaInicial(ActionEvent event) throws IOException{
    FXMLLoader layout = new FXMLLoader(getClass().getResource("../cenas/telaInicial.fxml")); //Armazena em layout o arquivo fxml desejado
    root = layout.load();  //Define na variavel root o carregamento do layout feito anteriormente

    stage = (Stage)((Node)event.getSource()).getScene().getWindow();// Obtém o Node que disparou o evento e o converte para o tipo Node e obtém a Scene desse Node e, a partir dela, obtém a janela (Stage) associada

    tela = new Scene(root);
    stage.setResizable(false); // mantem o tamanho fixo

    //Adiciona e define o titulo e o icone da barra superior do programa:
    stage.setTitle("D20 Trilhos");
    Image icone = new Image("./img/iconeBarra.jpg");
    stage.getIcons().add(icone);
    
    stage.setScene(tela);//Define qual tela sera mostrada
    
    stage.show();//Mostra a cena do programa

  }//fim do mudePraTElaInicial

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    caixaSolucao.getItems().addAll(opsolucao); //insere na choiceBox as opcoes possiveis 
    caixaSolucao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> { //observa o item selecionado e atualiza de solucaoSelecionada
      solucaoSelecionada = newValue; //variavel que sera usada pra definir qual solucao aplicar
    });
      
  }//fim do initialize
 
}//fim da classe ControleTelaInicial