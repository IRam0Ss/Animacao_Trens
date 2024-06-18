/* ***************************************************************
* Autor............: Iury Ramos Sodre
* Matricula........: 202310440
* Inicio...........: 21.05.2024
* Ultima alteracao.: 26.05.2024
* Nome.............: ControleAnimacao
* Funcao...........: Controla todos os elementos relacionados a animacao dos trens 
*************************************************************** */
package controles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controles.thread.Movimentacao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Trens;


public class ControleAnimacao implements Initializable{

  @FXML
  protected Slider sliderVelocidadeTrem01; //Slider referente a velocidade do trem 01

  @FXML
  protected Slider sliderVelocidadeTrem02; //Slider referente a velocidade do trem 02

  Movimentacao movimentoTrem01 = new Movimentacao(), movimentoTrem02 = new Movimentacao(); // cria as threads responsaveis pela movimentacao dos trens
  Trens trem1Info = new Trens(), trem2Info = new Trens(); //cria os objetos da classe Trens que possui as informacoes dos tr

  //Variaveis inteiras para a solucao de variavel de travamento
  public static int varTravamento1 = 0, varTravamento2 = 0;  

  //Variaveis inteiras para a solucao de estrita alternancia
  public static int estritaAlt1 = 0, estritaAlt2 = 0;    

  //Variaveis inteiras para a solucao de Peterson
  public static int vez1 = 0, vez2 = 0;    

  public static boolean[] interesse1 = {false, false};   //Vetor 1 de booleans setados como false para a solucao de Peterson
  public static boolean[] interesse2 = {false, false};   //Vetor 2 de booleans setados como false para a solucao de Peterson
  
  @FXML
  private Label indicadorVelocidadeTrem01; // caixa de texto que indica a velocidade do trem 01

  @FXML
  private Label indicadorVelocidadeTrem02; //caixa de texto que indica a velocidade do trem 02

  @FXML
  private Button botaoReset;

  @FXML
  private Button botaoTelaInicial;

  @FXML
  protected ImageView trem01; //Imagem do trem 01 = trem do trilho superior

  @FXML
  private ImageView trem02; //Imagem do trem 02 = trem do trilho inferior

  @FXML
  private ChoiceBox<String> caixaOpcoes; //Caixa de selecao da animacao desejada

  private String[] opcoes = {"Ambos Pela Esquerda","Ambos Pela Direita", "Trem 01 pela Esquerda e Trem 02 pela Direita","Trem 01 pela Direita e Trem 02 pela Esqueda"}; //As opcoes que estarao na caixa



  // Trabalhando com as animacoes

/* ***************************************************************
* Metodo: initialize (metodo padrao definido pela implements)
* Funcao: inicializa uma animacao
* Parametros: url location = referente ao arquivo fxml onde estao os elementos, resourceBundle resources = referente a recursos do arquivo
* Retorno: void
*************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources) {

    botaoReset(); //chama o metodo do botao reset

    //Trabalhando com a choiceBox
    caixaOpcoes.getItems().addAll(opcoes); //Adiciona as opcoes a caixa
    caixaOpcoes.setOnAction(this::defineAnimacao); //Define a acao/animacao que vai ser tocada, chamando o metodo defineAnimacao

    //Trabalhando com os Sliders

    indicadorVelocidadeTrem01.setText(String.format("%.2f m/s", sliderVelocidadeTrem01.getValue())); //Muda o texto da caixa de texto para mostrar a velocidade do trem 01;
    indicadorVelocidadeTrem02.setText(String.format("%.2f m/s", sliderVelocidadeTrem02.getValue())); //Muda o texto da caixa de texto para mostrar a velocidade do trem 02;
         
    //controla a mudanca da caixa de texto
    controleSlider01(); 
    controleSlider02();

  }//fim do initialize

/* ***************************************************************
* Metodo: botaoReset 
* Funcao: resetar a animacao para as posicoes e velocidades predefinidas
* Parametros: void
* Retorno: void
*************************************************************** */
  @FXML
  public void botaoReset(){
    // Botao Reset
    botaoReset.setOnAction(new EventHandler<ActionEvent>() {

      @Override
       public void handle(ActionEvent event) {
        
        //valores padrao dos sliders
        sliderVelocidadeTrem01.setValue(5); 
        sliderVelocidadeTrem02.setValue(5);

        //reseta os valores das variaveis de solucao

        ControleAnimacao.varTravamento1 = 0;
        ControleAnimacao.varTravamento2 = 0;

        ControleAnimacao.estritaAlt1 = 0;
        ControleAnimacao.estritaAlt2 = 0;

        ControleAnimacao.vez1 = 0;
        ControleAnimacao.vez2 = 0;
        
        for(int i=0; i<2; i++){
          ControleAnimacao.interesse1[i] = false;
          ControleAnimacao.interesse2[i] = false;
        }
        
        finalizarAnimacao(); //finaliza as thread de Movimentacao atuais

        defineAnimacao(event); //chama o metodo 
      
      }
    }); 
  }


/* ***************************************************************
* Metodo: defineAnimacao 
* Funcao: identifica a escolha do usuario e seleciona qual animacao ira animar
* Parametros: objeto do tipo evento que vai ser usado pra definir a animacao no initialize
* Retorno: void
*************************************************************** */
  public void defineAnimacao(ActionEvent evento){

    //reseta os valores das variaveis de solucao

    ControleAnimacao.varTravamento1 = 0;
    ControleAnimacao.varTravamento2 = 0;

    ControleAnimacao.estritaAlt1 = 0;
    ControleAnimacao.estritaAlt2 = 0;

    ControleAnimacao.vez1 = 0;
    ControleAnimacao.vez2 = 0;

    for(int i=0; i<2; i++){
      ControleAnimacao.interesse1[i] = false;
      ControleAnimacao.interesse2[i] = false;
    }

    //valores padrao dos sliders
    sliderVelocidadeTrem01.setValue(5); 
    sliderVelocidadeTrem02.setValue(5);
        
      String escolha = caixaOpcoes.getValue();

       switch (escolha) {
           case "Ambos Pela Esquerda":
              osDoisPelaEsquerda();
              break;
           case "Ambos Pela Direita":
              osDoisPelaDireita();
              break;
           case "Trem 01 pela Esquerda e Trem 02 pela Direita":
              trem01CimaEsquedaTem02BaixoDireita();
              break;
           case "Trem 01 pela Direita e Trem 02 pela Esqueda":
              trem01CimaDireitaTrem02BaixoEsquerda();
              break;
           default:
              break;
        }//fim switch

  }// fim do defineAnimacao

/* ***************************************************************
* Metodo: osDoisPelaEsquerda 
* Funcao: define e mostra a animacao dos dois trens saindo pela esqueda 
* Parametros: void
* Retorno: void
*************************************************************** */

    
  private void osDoisPelaEsquerda() {
    
    finalizarAnimacao(); //chama o metodo que finaliza as threads de Movimentacao criadas

    //inicializa as threads
    Movimentacao movimentoTrem01 = new Movimentacao(new Trens (trem01, sliderVelocidadeTrem01,"cimaEsquerda"));
    Movimentacao movimentoTrem02 = new Movimentacao(new Trens (trem02, sliderVelocidadeTrem02,"baixoEsquerda"));
    
    iniciarAnimacao(); //chama o metodo que inicia a animacao

    //da start nas threads 
    movimentoTrem01.start(); 
    movimentoTrem02.start();

  }//fim de osDoisPelaEsquerda
    
/* ***************************************************************
* Metodo: trem01CimaEsquedaTem02BaixoDireita
* Funcao: define e mostra a animacao do trem01 saindo de cima esquerda e do trem02 saindo de baixo direita 
* Parametros: void
* Retorno: void
*************************************************************** */

  private void trem01CimaEsquedaTem02BaixoDireita(){

    finalizarAnimacao(); //chama o metodo que finaliza as threads de Movimentacao criadas

    //inicializa as threads
    Movimentacao movimentoTrem01 = new Movimentacao(new Trens (trem01, sliderVelocidadeTrem01,"cimaEsquerda"));
    Movimentacao movimentoTrem02 = new Movimentacao(new Trens (trem02, sliderVelocidadeTrem02,"baixoDireita"));

    iniciarAnimacao(); //chama o metodo que inicia a animacao
    
    //da start nas threads 
    movimentoTrem01.start(); 
    movimentoTrem02.start();

  }// fim trem01CimaEsquedaTem02BaixoDireita

/* ***************************************************************
* Metodo: trem01CimaDireitaTrem02BaixoEsquerda
* Funcao: define e mostra a animacao do trem01 saindo de cima direita e do trem02 saindo de baixo esquerda 
* Parametros: void
* Retorno: void
*************************************************************** */

  private void trem01CimaDireitaTrem02BaixoEsquerda(){

    finalizarAnimacao(); //chama o metodo que finaliza as threads de Movimentacao criadas

    //inicializa as threads
    Movimentacao movimentoTrem01 = new Movimentacao(new Trens (trem01, sliderVelocidadeTrem01,"cimaDireita"));
    Movimentacao movimentoTrem02 = new Movimentacao(new Trens (trem02, sliderVelocidadeTrem02,"baixoEsquerda"));

    iniciarAnimacao(); //chama o metodo que inicia a animacao
    
    //da start nas threads 
    movimentoTrem01.start(); 
    movimentoTrem02.start();


    }//fim do trem01CimaDireitaTrem02BaixoEsquerda

/* ***************************************************************
* Metodo: osDoisPelaDireita
* Funcao: define e mostra a animacao dos dois trens saindo pela direita
* Parametros: void
* Retorno: void
*************************************************************** */

  private void osDoisPelaDireita(){

    finalizarAnimacao(); //chama o metodo que finaliza as threads de Movimentacao criadas

    //inicializa as threads
    Movimentacao movimentoTrem01 = new Movimentacao(new Trens (trem01, sliderVelocidadeTrem01,"cimaDireita"));
    Movimentacao movimentoTrem02 = new Movimentacao(new Trens (trem02, sliderVelocidadeTrem02,"baixoDireita"));

    iniciarAnimacao(); //chama o metodo que inicia a animacao
    
    //da start nas threads 
    movimentoTrem01.start(); 
    movimentoTrem02.start();

    
  }//fim do osDoisPelaDireita

    //Definir o botao de retorno a tela inicial:
    private Stage stage; 
    private Scene tela; 
    private Parent root;


/* ***************************************************************
* Metodo: mudePraTelaInicial
* Funcao: muda a tela para a primeira
* Parametros: recebe um evento que eh o clique do mouse no botao
* Retorno: void
*************************************************************** */
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
    }//fim do mudePraTelaInicial

/* ***************************************************************
* Metodo: controleSlider01
* Funcao: analisa os dados referentes ao slider e define a mostra no texto
* Parametros:void
* Retorno: void
*************************************************************** */ 

  public void controleSlider01(){

    //Trabalha e define os dados referentes ao slider de velocidade do trem01:
    sliderVelocidadeTrem01.valueProperty().addListener(new ChangeListener<Number>() {

    /* ***************************************************************
    * Metodo: changed (padrao do Change Listener)
    * Funcao: analizar o valor da posicao do slider e setar as configuracoes de velocidade e caixa de texto que apareceram
    * Parametros: o valor observado, o valor antigo e o novo valor do slider
    * Retorno: void
    *************************************************************** */

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
          double velocidadeTrem01 = sliderVelocidadeTrem01.getValue();
          
          indicadorVelocidadeTrem01.setText(String.format("%.2f m/s", velocidadeTrem01)); //Muda o texto da caixa de texto para mostrar a velocidade;
            
             
        }//fim do changed  
      });

  }//fim do controleSlider01

/* ***************************************************************
* Metodo: controleSlider02
* Funcao: analisa os dados referentes ao slider e define a mostra no texto
* Parametros:void
* Retorno: void
*************************************************************** */ 

  public void controleSlider02(){
    //Trabalha e define os dados referentes ao slider de velocidade do trem01:
    sliderVelocidadeTrem02.valueProperty().addListener(new ChangeListener<Number>() {

    /* ***************************************************************
    * Metodo: changed (padrao do Change Listener)
    * Funcao: analizar o valor da posicao do slider e setar as configuracoes de velocidade e caixa de texto que apareceram
    * Parametros: o valor observado, o valor antigo e o novo valor do slider
    * Retorno: void
    *************************************************************** */

    @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

      double velocidadeTrem02 = sliderVelocidadeTrem02.getValue();  
      indicadorVelocidadeTrem02.setText(String.format("%.2f m/s", velocidadeTrem02)); //Muda o texto da caixa de texto para mostrar a velocidade;
          
          
    }//fim do changed  
    });

  }//fim do controleSlider02

/* ***************************************************************
* Metodo: finalizarAnimacao
* Funcao: encerra as threads criadas pela classe movimento
* Parametros:void
* Retorno: void
*************************************************************** */ 
  public void finalizarAnimacao(){

    Thread[] ativas = new Thread[Thread.activeCount()]; //cria um array de todas as threads ativas
    Thread.enumerate(ativas);

    //percorre todas as threads ativas e finaliza as que sao do tipo Movimentacao
    for(Thread e:ativas){ 
      if (e instanceof Movimentacao) {
        Movimentacao atual = (Movimentacao)e;

        while(atual.isAlive()){
          Movimentacao.rodando = false;
        }
      }
    }
  }
/* ***************************************************************
* Metodo: iniciarAnimacao
* Funcao: inicia as threads criadas pela classe movimento
* Parametros:void
* Retorno: void
*************************************************************** */ 
  public void iniciarAnimacao(){
    Movimentacao.rodando = true;
  }
}//fim da classe ControleAnimaca