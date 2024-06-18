/* ***************************************************************
* Autor............: Iury Ramos Sodre
* Matricula........: 202310440
* Inicio...........: 21.05.2024
* Ultima alteracao.: 26.05.2024
* Nome.............: Movimentacao
* Funcao...........: Realiza a movimentacao dos trens
*************************************************************** */
package controles.thread;

import controles.ControleAnimacao;
import controles.ControleTelaInicial;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import modelo.Trens;

public class Movimentacao extends Thread {

  private Trens trem; 
  private Slider velocidade;

  public static volatile boolean rodando = true;

  private String solucao = ControleTelaInicial.solucaoSelecionada; //armazena a solucao selecionado
  
  private boolean zonaCritica01 = false;
  private boolean zonaCritica02 = false;

  //variaveis que numeram os processos, usado na solucao de peterson
  private int processo01 = 0;
  private int processo02 = 1;
  
/* ***************************************************************
* Metodo: Construtor da classe Movimentacao
* Funcao: define os dados proprios da classe Movimentacao
* Parametros: Trens trem = recebe os dados do trem que sera movido
* Retorno: void
*************************************************************** */ 

  public Movimentacao(Trens trem){
    this.trem = trem;
    velocidade = trem.getSlider();
  }
  public Movimentacao(){} //construtor vazio
  
  //metodos getters e setters

  public Trens getTrem() {
    return trem;
  }

  public double getVelocidade() {
    return velocidade.getValue();
  }

  public void setTrem(Trens trem) {
    this.trem = trem;
  }


  public String getSolucao() {
    return solucao;
  }

  public void setSolucao(String solucao) {
    this.solucao = solucao;
  }

/* ***************************************************************
* Metodo: run
* Funcao: classe principal de uma thread com a funcao de realizar o movimento da animacao
* Parametros: void
* Retorno: void
*************************************************************** */
  @Override
  public void run() {

    while(rodando){
      Platform.runLater(()-> {
        animacao();
      
      });
      try {
        Thread.sleep(1000/60);
      } catch (InterruptedException e) {
      }
    }
  } //fim do run

/* ***************************************************************
* Metodo: animacao
* Funcao: define e possui a logica da animacao selecionada para o trem
* Parametros: void
* Retorno: void
*************************************************************** */
  public void animacao(){
    ImageView imagemTrem = trem.getTrem();
    
    switch (trem.getPosicao()) {
      case "cimaEsquerda":
      
        if((imagemTrem.getLayoutX() >= 0) && (imagemTrem.getLayoutX() <= 95)){
          imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
        }
      //inicio da zona Critica 01:
        else if((imagemTrem.getLayoutX() >=95) && (imagemTrem.getLayoutX() <=165 )){

          if(!entrouNaZonaCritica01()&& !zonaCritica01){break;} //se nao puder entrar na zona critica e ela estiver ocupada, mantem o trem parado, usando a negacao do valor boolean por causa do funcionamento do if

          imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
          imagemTrem.setLayoutY(imagemTrem.getLayoutY() + velocidade.getValue());
          imagemTrem.setRotate(45);

        }else if((imagemTrem.getLayoutX() >=165)&& (imagemTrem.getLayoutX() <=285)){
    
          imagemTrem.setRotate(0);
          imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());   

        }else if((imagemTrem.getLayoutX() >=285) && (imagemTrem.getLayoutX() <= 350)){

          imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
          imagemTrem.setLayoutY(imagemTrem.getLayoutY() - velocidade.getValue());
          imagemTrem.setRotate(-45);

          
        }//fim da zona Critica 01
        else if((imagemTrem.getLayoutX() >= 350) && (imagemTrem.getLayoutX() <= 520)){

          saiuDaZonaCritica01(); //metodo que muda as variaveis para permitir a passagem pela zona

          imagemTrem.setRotate(0);
          imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());

        }
        //inicio da zona Critica 02:
        else if((imagemTrem.getLayoutX()>= 520) && (imagemTrem.getLayoutX() <=590)){

          if(!entrouNaZonaCritica02() && !zonaCritica02){break;}//se nao puder entrar na zona critica e ela estiver ocupada, mantem o trem parado, usando a negacao do valor boolean por causa do funcionamento do if

          imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
          imagemTrem.setLayoutY(imagemTrem.getLayoutY() + velocidade.getValue());
          imagemTrem.setRotate(45);

        }else if ((imagemTrem.getLayoutX() >=590) && (imagemTrem.getLayoutX() <= 705)){

          imagemTrem.setRotate(0);
          imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());

        }else if((imagemTrem.getLayoutX() >=705) && (imagemTrem.getLayoutX() <= 770)){

          imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
          imagemTrem.setLayoutY(imagemTrem.getLayoutY() - velocidade.getValue());
          imagemTrem.setRotate(-45);

          
        }//fim da zona Critica 02
        else if ((imagemTrem.getLayoutX() >=770) && (imagemTrem.getLayoutX() <= 999)){

          saiuDaZonaCritica02(); //metodo que muda as variaveis para permitir a passagem pela zona

          imagemTrem.setRotate(0);
          imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());

        }else{

          imagemTrem.setLayoutX(0);
          imagemTrem.setLayoutY(220);

        }
        
        break;

      case "baixoEsquerda":
      if((imagemTrem.getLayoutX() >= 0) && (imagemTrem.getLayoutX() <= 95)){
        imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
      }
    //inicio da zona Critica 01:
      else if((imagemTrem.getLayoutX() >=95) && (imagemTrem.getLayoutX() <=165 )){

        if(!entrouNaZonaCritica01() && !zonaCritica01){break;} //se nao puder entrar na zona critica e ela estiver ocupada, mantem o trem parado, usando a negacao do valor boolean por causa do funcionamento do if

        imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
        imagemTrem.setLayoutY(imagemTrem.getLayoutY() - velocidade.getValue());
        imagemTrem.setRotate(-45);

      }else if((imagemTrem.getLayoutX() >=165)&& (imagemTrem.getLayoutX() <=285)){
  
        imagemTrem.setRotate(0);
        imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());   

      }else if((imagemTrem.getLayoutX() >=285) && (imagemTrem.getLayoutX() <= 350)){

        imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
        imagemTrem.setLayoutY(imagemTrem.getLayoutY() + velocidade.getValue());
        imagemTrem.setRotate(45);

        
      }//fim da zona Critica 01
      else if((imagemTrem.getLayoutX() >= 350) && (imagemTrem.getLayoutX() <= 520)){
        
        saiuDaZonaCritica01(); //metodo que muda as variaveis para permitir a passagem pela zona

        imagemTrem.setRotate(0);
        imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());

      }
      //inicio da zona Critica 02:
      else if((imagemTrem.getLayoutX()>= 520) && (imagemTrem.getLayoutX() <=580)){

        if(!entrouNaZonaCritica02() && !zonaCritica02){break;}//se nao puder entrar na zona critica e ela estiver ocupada, mantem o trem parado, usando a negacao do valor boolean por causa do funcionamento do if

        imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
        imagemTrem.setLayoutY(imagemTrem.getLayoutY() - velocidade.getValue());
        imagemTrem.setRotate(-45);

      }else if ((imagemTrem.getLayoutX() >=580) && (imagemTrem.getLayoutX() <= 705)){

        imagemTrem.setRotate(0);
        imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());

      }else if((imagemTrem.getLayoutX() >=705) && (imagemTrem.getLayoutX() <= 790)){

        imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());
        imagemTrem.setLayoutY(imagemTrem.getLayoutY() + velocidade.getValue());
        imagemTrem.setRotate(45);

        
      }//fim da zona Critica 02
      else if ((imagemTrem.getLayoutX() >=790) && (imagemTrem.getLayoutX() <= 999)){
        
        saiuDaZonaCritica02(); //metodo que muda as variaveis para permitir a passagem pela zona

        imagemTrem.setRotate(0);
        imagemTrem.setLayoutX(imagemTrem.getLayoutX() + velocidade.getValue());

      }else{

        imagemTrem.setLayoutX(0);
        imagemTrem.setLayoutY(347);

      }
      
      break;

      case "cimaDireita":
          if((imagemTrem.getLayoutX()<= 950) && (imagemTrem.getLayoutX() >= 765)){

            imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

          }
          //inicio da zona Critica 02:
          else if((imagemTrem.getLayoutX()<= 765) && (imagemTrem.getLayoutX() >= 700)){

            if(!entrouNaZonaCritica02() && !zonaCritica02){break;}//se nao puder entrar na zona critica e ela estiver ocupada, mantem o trem parado, usando a negacao do valor boolean por causa do funcionamento do if

            imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());
            imagemTrem.setLayoutY(imagemTrem.getLayoutY() + velocidade.getValue());
            imagemTrem.setRotate(135);

          }else if((imagemTrem.getLayoutX() <=700) && (imagemTrem.getLayoutX()>= 565)){

            imagemTrem.setRotate(180);
            imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

          }else if((imagemTrem.getLayoutX()<= 565) && (imagemTrem.getLayoutX() >= 490)){

            imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());
            imagemTrem.setLayoutY(imagemTrem.getLayoutY() - velocidade.getValue());
            imagemTrem.setRotate(-135);

          }
          //fim da zona Critica 02
          else if((imagemTrem.getLayoutX() <=490) && (imagemTrem.getLayoutX()>= 350)){

            saiuDaZonaCritica02();//metodo que muda as variaveis para permitir a passagem pela zona

            imagemTrem.setRotate(180);
            imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

          }
          //inicio da zona Critica01:
          else if((imagemTrem.getLayoutX()<= 350) && (imagemTrem.getLayoutX() >= 280)){

            if(!entrouNaZonaCritica01() && !zonaCritica01){break;}//se nao puder entrar na zona critica e ela estiver ocupada, mantem o trem parado, usando a negacao do valor boolean por causa do funcionamento do if

            imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());
            imagemTrem.setLayoutY(imagemTrem.getLayoutY() + velocidade.getValue());
            imagemTrem.setRotate(135);

          }else if((imagemTrem.getLayoutX() <=280) && (imagemTrem.getLayoutX() >= 150)){

            imagemTrem.setRotate(180);
            imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

          }else if((imagemTrem.getLayoutX()<= 150) && (imagemTrem.getLayoutX() >= 90)){

            imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());
            imagemTrem.setLayoutY(imagemTrem.getLayoutY() - velocidade.getValue());
            imagemTrem.setRotate(-135);

          }
          //fim da zona Critica01
          else if((imagemTrem.getLayoutX() <=90) && (imagemTrem.getLayoutX() >= -50)){

            saiuDaZonaCritica01();//metodo que muda as variaveis para permitir a passagem pela zona

            imagemTrem.setRotate(180);
            imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

          }else{

            imagemTrem.setLayoutX(950);
            imagemTrem.setLayoutY(220);
    
          }

          break;

      case "baixoDireita":
        if((imagemTrem.getLayoutX()<= 950) && (imagemTrem.getLayoutX() >= 765)){

          imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

        }
        //inicio da zona Critica 02:
        else if((imagemTrem.getLayoutX()<= 765) && (imagemTrem.getLayoutX() >= 700)){

          if(!entrouNaZonaCritica02() && !zonaCritica02){break;}//se nao puder entrar na zona critica e ela estiver ocupada, mantem o trem parado, usando a negacao do valor boolean por causa do funcionamento do if

          imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());
          imagemTrem.setLayoutY(imagemTrem.getLayoutY() - velocidade.getValue());
          imagemTrem.setRotate(-135);

        }else if((imagemTrem.getLayoutX() <=700) && (imagemTrem.getLayoutX()>= 565)){

          imagemTrem.setRotate(180);
          imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

        }else if((imagemTrem.getLayoutX()<= 565) && (imagemTrem.getLayoutX() >= 490)){

          imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());
          imagemTrem.setLayoutY(imagemTrem.getLayoutY() + velocidade.getValue());
          imagemTrem.setRotate(135);

        }
        //fim da zona Critica 02
        else if((imagemTrem.getLayoutX() <=490) && (imagemTrem.getLayoutX()>= 350)){

          saiuDaZonaCritica02();//metodo que muda as variaveis para permitir a passagem pela zona

          imagemTrem.setRotate(180);
          imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

        }
        //inicio da zona Critica01:
        else if((imagemTrem.getLayoutX()<= 350) && (imagemTrem.getLayoutX() >= 280)){

          if(!entrouNaZonaCritica01() && !zonaCritica01){break;}//se nao puder entrar na zona critica e ela estiver ocupada, mantem o trem parado, usando a negacao do valor boolean por causa do funcionamento do if

          imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());
          imagemTrem.setLayoutY(imagemTrem.getLayoutY() - velocidade.getValue());
          imagemTrem.setRotate(-135);

        }else if((imagemTrem.getLayoutX() <=280) && (imagemTrem.getLayoutX() >= 150)){

          imagemTrem.setRotate(180);
          imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

        }else if((imagemTrem.getLayoutX()<= 150) && (imagemTrem.getLayoutX() >= 90)){

          imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());
          imagemTrem.setLayoutY(imagemTrem.getLayoutY() + velocidade.getValue());
          imagemTrem.setRotate(135);

        }
        //fim da zona Critica01
        else if((imagemTrem.getLayoutX() <=90) && (imagemTrem.getLayoutX() >= -50)){

          saiuDaZonaCritica01();//metodo que muda as variaveis para permitir a passagem pela zona

          imagemTrem.setRotate(180);
          imagemTrem.setLayoutX(imagemTrem.getLayoutX() - velocidade.getValue());

        }else{

          imagemTrem.setLayoutX(950);
          imagemTrem.setLayoutY(347);
  
        }

        break;
          
      default:
        break;
    }

  }//fim da animacao

/* ***************************************************************
* Metodo: entrouNaZonaCritica01
* Funcao: controla as solucoes da entrada da zona critica 01
* Parametros: void
* Retorno:void
*************************************************************** */
  public boolean entrouNaZonaCritica01(){

    switch (solucao) {
      case "Variavel de Travamento":
        if(ControleAnimacao.varTravamento1 == 1){
          return false; // nao pode entrar na zona 
        }else{
          ControleAnimacao.varTravamento1 =1; //muda a variavel pra indicar que alguem vai entrar
          zonaCritica01 = true; //muda pra indicar que a zona esta ocupada
          return true; //libera entrada 
        }  
        
      case "Estrita Alternancia":
        if(trem.getPosicao().equals("cimaEsquerda") || trem.getPosicao().equals("cimaDireita")){
          if(ControleAnimacao.estritaAlt1 == 0){ //libera a passagem dos trens de cima caso a vez seja 0
            zonaCritica01 = true;
            return true;
          }else{ //impede a passagem dos trens de cima caso a vez seja 1
            return false;
          }
        }
        else if(trem.getPosicao().equals("baixoEsquerda")||trem.getPosicao().equals("baixoDireita")){
          if(ControleAnimacao.estritaAlt1 == 1){ //libera a passagem dos trens de baixo caso a vez seja 1
            zonaCritica01 = true;
            return true;
          }else{ //impede a passagem dos trens de baixo caso a vez seja 0
            return false;
          }
        }

      case "Solucao de Peterson":
        if(trem.getPosicao().equals("cimaEsquerda") || trem.getPosicao().equals("cimaDireita")){

          int outroProcesso = 1- processo01;

          ControleAnimacao.interesse1[processo01] = true; //anucia o interesse do processo 1 em acessar a zona critica

          ControleAnimacao.vez1 = processo01; //define a vez como a do processo 1

          if(ControleAnimacao.interesse1[outroProcesso] == true && ControleAnimacao.vez1 == processo01){ //se o interesse for do outro processo e a vez for do processo 1 impede a entrada na zona
            return false;
          }else{ //se não permite a entrada na zona
            zonaCritica01 = true;
            return true;
          }
        }
        else if(trem.getPosicao().equals("baixoEsquerda") || trem.getPosicao().equals("baixoDireita")){

          int outroProcesso = 1 - processo02;

          ControleAnimacao.interesse1[processo02] = true; //anucia interesse do processo em entrar na zona

          ControleAnimacao.vez1 = processo02; //define a vez do processo 2

          if(ControleAnimacao.interesse1[outroProcesso] == true && ControleAnimacao.vez1 == processo02){ //se o interesse for do outro processo e a vez for do processo 2 impede a entrada na zona
            return false;
          }else{ //se não permite a entrada na zona
            zonaCritica01 = true;
            return true;
          }
        }

           
      default:
        System.out.println("Nao ta lendo a solucao escolhida");
        return false;
      
    }
    
  } //fim do entrouNaZonaCritica01

/* ***************************************************************
* Metodo: saiuDaZonaCritica01
* Funcao: controla a saida da zona critica01
* Parametros: void
* Retorno:void
*************************************************************** */
  public void saiuDaZonaCritica01(){

    switch (solucao) {
      case "Variavel de Travamento":
        ControleAnimacao.varTravamento1 = 0;
        zonaCritica01 = false;
      break;
    
      case "Estrita Alternancia":
        if(trem.getPosicao().equals("cimaEsquerda")||trem.getPosicao().equals("cimaDireita")){ //libera a zona critica e passa a vez
          zonaCritica01 = false;
          ControleAnimacao.estritaAlt1 = 1;
        }
        if(trem.getPosicao().equals("baixoEsquerda")||trem.getPosicao().equals("baixoDireita")){ //libera a zona critica e passa a vez
          zonaCritica01 = false;
          ControleAnimacao.estritaAlt1 = 0;
        }
      break;

      case "Solucao de Peterson":
        if(trem.getPosicao().equals("cimaEsquerda") || trem.getPosicao().equals("cimaDireita")){ //libera a zona, passa a vez e remove o interesse
          zonaCritica01 = false;
          ControleAnimacao.interesse1[processo01] = false;
          ControleAnimacao.vez1 = processo02; 
        }
        if(trem.getPosicao().equals("baixoEsquerda")||trem.getPosicao().equals("baixoDireita")){ //libera a zona, passa a vez e remove o interesse
          zonaCritica01 = false;
          ControleAnimacao.interesse1[processo02] = false;
          ControleAnimacao.vez1 = processo01; 
        }
      break;
      
    
      default:
      break;
      
    }
  }//fim do saiDaZonaCritica01

  
/* ***************************************************************
* Metodo: entrouNaZonaCritica02
* Funcao: controla as solucoes da entrada da zona critica 02
* Parametros: void
* Retorno:void
*************************************************************** */
  public boolean entrouNaZonaCritica02(){

    switch (solucao) {
      case "Variavel de Travamento":
        if(ControleAnimacao.varTravamento2 == 1){
          return false; // nao pode entrar na zona 
        }else{
          ControleAnimacao.varTravamento2 =1; //muda a variavel pra indicar que alguem vai entrar
          zonaCritica02 = true; //muda pra indicar que a zona esta ocupada
          return true; //libera entrada 
        } 
        
      case "Estrita Alternancia":
        if(trem.getPosicao().equals("cimaEsquerda")||trem.getPosicao().equals("cimaDireita")){
          if(ControleAnimacao.estritaAlt2 == 0){ //libera a passagem dos trens de cima caso a vez seja 0
            zonaCritica02 = true;
            return true;
          }else{ //impede a passagem dos trens de cima caso a vez seja 1
            return false;
          }
        }
        else if(trem.getPosicao().equals("baixoEsquerda")||trem.getPosicao().equals("baixoDireita")){
          if(ControleAnimacao.estritaAlt2 == 1){ //libera a passagem dos trens de baixo caso a vez seja 1
            zonaCritica02 = true;
            return true;
          }else{ //impede a passagem dos trens de baixo caso a vez seja 0
            return false;
          }
        }
        case "Solucao de Peterson":
        if(trem.getPosicao().equals("cimaEsquerda") || trem.getPosicao().equals("cimaDireita")){

          int outroProcesso = 1- processo01;

          ControleAnimacao.interesse2[processo01] = true; //anucia o interesse do processo 1 em acessar a zona critica

          ControleAnimacao.vez2 = processo01; //define a vez como a do processo 1

          if(ControleAnimacao.interesse2[outroProcesso] == true && ControleAnimacao.vez2 == processo01){ //se o interesse for do outro processo e a vez for do processo 1 impede a entrada na zona
            return false;
          }else{ //se não permite a entrada na zona
            zonaCritica02 = true;
            return true;
          }
        }
        else if(trem.getPosicao().equals("baixoEsquerda") || trem.getPosicao().equals("baixoDireita")){

          int outroProcesso = 1 - processo02;

          ControleAnimacao.interesse2[processo02] = true; //anucia interesse do processo em entrar na zona

          ControleAnimacao.vez2 = processo02; //define a vez do processo 2

          if(ControleAnimacao.interesse2[outroProcesso] == true && ControleAnimacao.vez2 == processo02){ //se o interesse for do outro processo e a vez for do processo 2 impede a entrada na zona
            return false;
          }else{ //se não permite a entrada na zona
            zonaCritica02 = true;
            return true;
          }
        }
      
    
      default:
        System.out.println("Nao ta lendo a solucao escolhida");
        return false;
      
    }
  }//fim do entrouNaZonaCritica02

/* ***************************************************************
* Metodo: saiuDaZonaCritica01
* Funcao: controla a saida da zona critica01
* Parametros: void
* Retorno:void
*************************************************************** */
public void saiuDaZonaCritica02(){

  switch (solucao) {
    case "Variavel de Travamento":
      ControleAnimacao.varTravamento2 = 0;
      zonaCritica02 = false;
    break;

    case "Estrita Alternancia":
      if(trem.getPosicao().equals("cimaEsquerda")||trem.getPosicao().equals("cimaDireita")){ //libera a zona critica e passa a vez
        zonaCritica02 = false;
        ControleAnimacao.estritaAlt2 = 1;
      }
      if(trem.getPosicao().equals("baixoEsquerda")||trem.getPosicao().equals("baixoDireita")){ //libera a zona critica e passa a vez
        zonaCritica02 = false;
        ControleAnimacao.estritaAlt2 = 0;
      }
    break;

    case "Solucao de Peterson":
      if(trem.getPosicao().equals("cimaEsquerda") || trem.getPosicao().equals("cimaDireita")){ //libera a zona, passa a vez e remove o interesse
        zonaCritica02 = false;
        ControleAnimacao.interesse2[processo01] = false;
        ControleAnimacao.vez2 = processo02; 
      }
      if(trem.getPosicao().equals("baixoEsquerda")||trem.getPosicao().equals("baixoDireita")){ //libera a zona, passa a vez e remove o interesse
        zonaCritica02 = false;
        ControleAnimacao.interesse2[processo02] = false;
        ControleAnimacao.vez2 = processo01; 
      }
    break;
    
  
    default:
    break;
    
  }
}//fim do saiuDaZonaCritica02

}//fim da classe Movimentacao
