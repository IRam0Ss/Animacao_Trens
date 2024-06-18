/* ***************************************************************
* Autor............: Iury Ramos Sodre
* Matricula........: 202310440
* Inicio...........: 03.05.2024
* Ultima alteracao.: 04.05.2024
* Nome.............: D20 Trilhos
* Funcao...........: Definir os caminhos dos trens
*************************************************************** */
package controles;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class CaminhosTrem {

/* ***************************************************************
* Metodo: caminhoTrem01Esquerda 
* Funcao: define o caminho do trem01 saindo da esquerda 
* Parametros: void
* Retorno: Path
*************************************************************** */
  
  public static Path caminhoTrem01Esquerda(){
    Path caminhoTrem01 = new Path(); //Define onde sera armazenado o caminho percorrido pelo trem01 

    caminhoTrem01.getElements().add(new MoveTo(0,25)); //define posicao  trem01
    caminhoTrem01.getElements().add(new LineTo(165,25)); //define posicao trem01 na primeira reta
          
    caminhoTrem01.getElements().add(new MoveTo(165, 25)); //Posicao  trem01 na curva
    caminhoTrem01.getElements().add(new LineTo(225, 85)); //Posicao trem01 apos curva

    caminhoTrem01.getElements().add(new MoveTo(225,85)); //Posicao  trem01 na reta
    caminhoTrem01.getElements().add(new LineTo(345,85)); //Posicao trem01 na reta

    caminhoTrem01.getElements().add(new MoveTo(345, 85)); //Posicao  trem01 na curva
    caminhoTrem01.getElements().add(new LineTo(415, 25)); //Posicao trem01 apos curva

    caminhoTrem01.getElements().add(new MoveTo(415,25)); //Posicao  trem01 na reta
    caminhoTrem01.getElements().add(new LineTo(580,25)); //Posicao trem01 na reta

    caminhoTrem01.getElements().add(new MoveTo(580, 25)); //Posicao  trem01 na curva
    caminhoTrem01.getElements().add(new LineTo(635, 85)); //Posicao trem01 apos curva

    caminhoTrem01.getElements().add(new MoveTo(635,85)); //Posicao  trem01 na reta
    caminhoTrem01.getElements().add(new LineTo(770,85)); //Posicao trem01 na reta

    caminhoTrem01.getElements().add(new MoveTo(770, 85)); //Posicao  trem01 na curva
    caminhoTrem01.getElements().add(new LineTo(830, 25)); //Posicao trem01 apos curva

    caminhoTrem01.getElements().add(new MoveTo(830,25)); //Posicao  trem01 na reta
    caminhoTrem01.getElements().add(new LineTo(999,25)); //Posicao trem01 na reta

    return caminhoTrem01;
  }

/* ***************************************************************
* Metodo: caminhoTrem02Esquerda 
* Funcao: define o caminho do trem02 saindo da esquerda 
* Parametros: void
* Retorno: Path
*************************************************************** */
  public static Path caminhoTrem02Esquerda(){
    Path caminhoTrem02 = new Path(); //Define onde sera armazenado o caminho do trem 02

    caminhoTrem02.getElements().add(new MoveTo(0,25)); //Define a posicao de saida do trem 02
    caminhoTrem02.getElements().add(new LineTo(165,25)); //Define a posicao trem 02 apos a reta

    caminhoTrem02.getElements().add(new MoveTo(165,25)); //Posicao  trem02 no inicio da curva 
    caminhoTrem02.getElements().add(new LineTo(225,-35)); //Posicao trem02 no fim da curva

    caminhoTrem02.getElements().add(new MoveTo(225,-35)); //Define a posicao de saida do trem 02
    caminhoTrem02.getElements().add(new LineTo(345,-35)); //Define a posicao trem 02 apos a reta

    caminhoTrem02.getElements().add(new MoveTo(345,-35)); //Posicao trem02 no inicio da curva 
    caminhoTrem02.getElements().add(new LineTo(415,25)); //Posicao trem02 no fim da curva

    caminhoTrem02.getElements().add(new MoveTo(415,25)); //Define a posicao de saida do trem 02
    caminhoTrem02.getElements().add(new LineTo(580,25)); //Define a posicao trem 02 apos a reta

    caminhoTrem02.getElements().add(new MoveTo(580,25)); //Posicao  trem02 no inicio da curva 
    caminhoTrem02.getElements().add(new LineTo(635,-35)); //Posicao trem02 no fim da curva

    caminhoTrem02.getElements().add(new MoveTo(635,-35)); //Define a posicao de saida do trem 02
    caminhoTrem02.getElements().add(new LineTo(770,-35)); //Define a posicao trem 02 apos a reta

    caminhoTrem02.getElements().add(new MoveTo(770,-35)); //Posicao  trem02 no inicio da curva 
    caminhoTrem02.getElements().add(new LineTo(830,25)); //Posicao trem02 no fim da curva

    caminhoTrem02.getElements().add(new MoveTo(830,25)); //Define a posicao de saida do trem 02
    caminhoTrem02.getElements().add(new LineTo(999,25)); //Define a posicao trem 02 apos a reta

    return caminhoTrem02;
  }

/* ***************************************************************
* Metodo: caminhoTrem01Direita
* Funcao: define o caminho do trem01 saindo da direita 
* Parametros: void
* Retorno: Path
*************************************************************** */
  public static Path caminhoTrem01Direita(){
    Path caminhoTrem01 = new Path(); // Define onde sera armazenado o caminho de trem 01

    caminhoTrem01.getElements().add(new MoveTo(999,35)); //Define a posicao de saida do trem 01
    caminhoTrem01.getElements().add(new LineTo(830,35)); //Define a posicao trem 01 apos a reta
    
    caminhoTrem01.getElements().add(new MoveTo(830,35)); //Posicao do trem01 no inicio da curva
    caminhoTrem01.getElements().add(new LineTo(770,90)); //Posicao do trem01 no fim da curva

    caminhoTrem01.getElements().add(new MoveTo(770,90)); //Posicao do trem01 no inicio da reta
    caminhoTrem01.getElements().add(new LineTo(635, 90)); //Posicao do trem01 no final da reta

    caminhoTrem01.getElements().add(new MoveTo(635,90)); //Posicao do trem01 no inicio da curva
    caminhoTrem01.getElements().add(new LineTo(580,35)); //Posicao do trem01 no fim da curva

    caminhoTrem01.getElements().add(new MoveTo(580,35)); //Posicao do trem01 no inicio da reta
    caminhoTrem01.getElements().add(new LineTo(415, 35)); //Posicao do trem01 no final da reta

    caminhoTrem01.getElements().add(new MoveTo(415,35)); //Posicao do trem01 no inicio da curva
    caminhoTrem01.getElements().add(new LineTo(345,90)); //Posicao do trem01 no fim da curva

    caminhoTrem01.getElements().add(new MoveTo(345,90)); //Posicao do trem01 no inicio da reta
    caminhoTrem01.getElements().add(new LineTo(225, 90)); //Posicao do trem01 no final da reta

    caminhoTrem01.getElements().add(new MoveTo(225,90)); //Posicao do trem01 no inicio da curva
    caminhoTrem01.getElements().add(new LineTo(165,35)); //Posicao do trem01 no fim da curva

    caminhoTrem01.getElements().add(new MoveTo(165,35)); //Posicao do trem01 no inicio da reta
    caminhoTrem01.getElements().add(new LineTo(0, 35)); //Posicao do trem01 no final da reta

    return caminhoTrem01;
  }

  /* ***************************************************************
* Metodo: caminhoTrem02Direita
* Funcao: define o caminho do trem02 saindo da direita 
* Parametros: void
* Retorno: Path
*************************************************************** */
  public static Path caminhoTrem02Direita(){
    Path caminhoTrem02 = new Path();

    caminhoTrem02.getElements().add(new MoveTo(999,30)); //Define a posicao  trem02
    caminhoTrem02.getElements().add(new LineTo(830,30)); //Define a posicao trem02 no fim da reta

    caminhoTrem02.getElements().add(new MoveTo(830,30)); //Posicao  trem02 na curva
    caminhoTrem02.getElements().add(new LineTo(770,-35)); //Posicao trem02 na curva

    caminhoTrem02.getElements().add(new MoveTo(770,-35)); //Posicao  trem02 na reta
    caminhoTrem02.getElements().add(new LineTo(635,-35)); //Posicao trem02 na reta

    caminhoTrem02.getElements().add(new MoveTo(635,-35)); //Posicao  trem02 na curva
    caminhoTrem02.getElements().add(new LineTo(580,30)); //Posicao trem02 na curva

    caminhoTrem02.getElements().add(new MoveTo(580,30)); //Posicao  trem02 na reta
    caminhoTrem02.getElements().add(new LineTo(415,30)); //Posicao trem02 na reta

    caminhoTrem02.getElements().add(new MoveTo(415,30)); //Posicao  trem02 na curva
    caminhoTrem02.getElements().add(new LineTo(345,-35)); //Posicao trem02 na curva

    caminhoTrem02.getElements().add(new MoveTo(345,-35)); //Posicao  trem02 na reta
    caminhoTrem02.getElements().add(new LineTo(225,-35)); //Posicao trem02 na reta

    caminhoTrem02.getElements().add(new MoveTo(225,-35)); //Posicao  trem02 na curva
    caminhoTrem02.getElements().add(new LineTo(165,30)); //Posicao trem02 na curva

    caminhoTrem02.getElements().add(new MoveTo(165,30)); //Posicao  trem02 na reta
    caminhoTrem02.getElements().add(new LineTo(0,30)); //Posicao trem02 na reta

    return caminhoTrem02;
  }

}
