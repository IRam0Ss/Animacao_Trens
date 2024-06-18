/* ***************************************************************
* Autor............: Iury Ramos Sodre
* Matricula........: 202310440
* Inicio...........: 21.05.2024
* Ultima alteracao.: 26.05.2024
* Nome.............: Trens
* Funcao...........: Cria um objeto do tipo trem que possui os elementos nescesarios
*************************************************************** */

package modelo;


import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class Trens {

  private ImageView trem;
  private Slider velocidade;
  private String posicao;

/* ***************************************************************
* Metodo: Construtor da classe trens
* Funcao: define os dados proprios da classe trens
* Parametros: ImageView trem = recebe qual o elemento que é o trem, Slider velocidade = recebe o slider que controla a velocidade da animacao, String posicao = recebe qual a pocisao de saida do trem
* Retorno: void
*************************************************************** */

  public Trens(ImageView trem, Slider velocidade, String posicao) {
    
    this.trem = trem;
    this.velocidade = velocidade;
    this.posicao = posicao;
    

    switch (posicao) { //dependendo da posicao escolhida define as animacoes
      case "cimaEsquerda":
      
        trem.setLayoutX(0);
        trem.setLayoutY(220);
        trem.setRotate(0);
        
        break;
      
      case "baixoEsquerda":

        trem.setLayoutX(0);
        trem.setLayoutY(347);
        trem.setRotate(0);

        break;

      case "cimaDireita":

        trem.setLayoutX(950);
        trem.setLayoutY(230);
        trem.setRotate(180);

        break;

      case "baixoDireita":

        trem.setLayoutX(950);
        trem.setLayoutY(347);
        trem.setRotate(180);

        break;
    
      default:
        break;
    }
  }//fim do switch
  public Trens(){} //construtor vazio

  //metodos getters e setters

  public ImageView getTrem() {
    return trem;
  }

  public void setTrem(ImageView trem) {
    this.trem = trem;
  }

  public double getVelocidade() {
    return velocidade.getValue();
  }

  public String getPosicao() {
    return posicao;
  }

  public void setPosicao(String posicao) {
    this.posicao = posicao;
  }

  public Slider getSlider(){
    return velocidade;
  }
 
}//fim da classe Trens
