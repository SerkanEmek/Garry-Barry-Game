package com.serkanemek.garrybarry.operations;

import java.util.Random;

public class IqGame extends NumbersForIq {

    public IqGame(Integer combination, Integer level){

        randomNumber1 = new Random();
        chooseLevel(combination, level);
    }

    public void combination1(Integer level){
        if(level == 1){
            number1 = randomNumber1.nextInt(5) + 1;
            number2 = number1 + 1;
            number3 = number2 + 2;
            number5 = randomNumber1.nextInt(10) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 1;
            number7 = number6 + 2;
            result = number3;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(5) + 1;
            number2 = number1 + 10;
            number3 = number2 + 10;
            number5 = randomNumber1.nextInt(10) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 10;
            number7 = number6 + 10;
            result = number3;
        }
        if(level == 3){
            number1 = randomNumber1.nextInt(10) + 11;
            number2 = number1 - 5;
            number3 = number2 - 5;
            number5 = randomNumber1.nextInt(10) + 11;
            if(number5 == number1){
                number5++;
            }
            number6 = number5 - 5;
            number7 = number6 - 5;
            result = number3;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(10) + 1;
            number2 = number1 + 5;
            number3 = number2 + 5;
            number5 = randomNumber1.nextInt(5) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 5;
            number7 = number6 + 5;
            result = number3;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(5) + 5;
            number2 = number1 + 10;
            number3 = number2 + 5;
            number5 = randomNumber1.nextInt(5) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 10;
            number7 = number6 + 5;
            result = number3;
        }
    }

    public void combination2(Integer level){ //Kombinasyon 1 = a b c & d e f?

      if(level == 1){
          number1 = randomNumber1.nextInt(5) + 2;
          number2 = number1 * 2;
          number3 = number2 * 2;
          number5 = randomNumber1.nextInt(10) + 2;
          if(number5 == number1 ){
              number5++;
          }
          number6 = number5 * 2;
          number7 = number6 * 2;
          result = number7;
      }
      if(level == 2){
          number1 = randomNumber1.nextInt(5) + 2;
          number2 = number1 * 3;
          number3 = number2 * 2;
          number5 = randomNumber1.nextInt(10) + 2;
          if(number5 == number1 ){
              number5++;
          }
          number6 = number5 * 3;
          number7 = number6 * 2;
          result = number7;
      }
      if(level == 3){
          number1 = randomNumber1.nextInt(5) + 2;
          number2 = number1 + 2;
          number3 = number2 + 4;
          number5 = randomNumber1.nextInt(10) + 2;
          if (number5 == number1) {
              number5++;
          }
          number6 = number5 + 2;
          number7 = number6 + 4;
          result = number7;
      }
      if(level == 4){
          number1 = randomNumber1.nextInt(5) + 2;
          number2 = number1 * 2;
          number3 = number2 + 2;
          number5 = randomNumber1.nextInt(5) + 2;
          if (number5 == number1) {
              number5 += 2;
          }
          number6 = number5 * 2;
          number7 = number6 + 2;
          result = number7;
      }
      if(level == 5){
          number1 = randomNumber1.nextInt(5) + 2;
          number2 = number1 * 3;
          number3 = number2 * 3;
          number5 = randomNumber1.nextInt(5) + 2;
          if (number5 == number1) {
              number5++;
          }
          number6 = number5 * 3;
          number7 = number6 * 3;
          result = number7;
      }

      }

    public void combination3(Integer level){// a? b c & d e f

        if(level == 1){
            number1 = randomNumber1.nextInt(10) + 5;
            number2 = number1 * 4;
            number3 = number2 / 2;
            number5 = randomNumber1.nextInt(10) + 5;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 * 4;
            number7 = number6 / 2;
            result = number1;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(10) + 5;
            number2 = number1 + 5;
            number3 = number2 + 10;
            number5 = randomNumber1.nextInt(10) + 5;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 5;
            number7 = number6 + 10;
            result = number1;
        }
        if(level == 3){
            number1 = (randomNumber1.nextInt(20) + 10) * 2;
            number2 = number1 / 2;
            number3 = number2 - 2;
            number5 = (randomNumber1.nextInt(10) + 5) * 2;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 / 2;
            number7 = number6 - 2;
            result = number1;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(20) + 10;
            number2 = number1 - 3;
            number3 = number2 - 6;
            number5 = randomNumber1.nextInt(20) + 10;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 - 3;
            number7 = number6 - 6;
            result = number1;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(5) + 1;
            number2 = number1 * 5;
            number3 = number2 * 5;
            number5 = randomNumber1.nextInt(5) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 * 5;
            number7 = number6 * 5;
            result = number1;
        }

    }

    public void combination4(Integer level){
        if(level == 1){
            number1 = randomNumber1.nextInt(5) + 1;
            number2 = number1 + 1;
            number3 = number2 + 2;
            number4 = number3 + 3;
            number5 = randomNumber1.nextInt(10) + 5;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 1;
            number7 = number6 + 2;
            number8 = number7 + 3;
            result = number4;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(5) + 1;
            number2 = number1 + 2;
            number3 = number2 + 2;
            number4 = number3 + 2;
            number5 = randomNumber1.nextInt(10) + 5;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 2;
            number7 = number6 + 2;
            number8 = number7 + 2;
            result = number4;
        }
        if(level == 3){
            number1 = randomNumber1.nextInt(5) + 1;
            number2 = number1 + 3;
            number3 = number2 + 3;
            number4 = number3 + 3;
            number5 = randomNumber1.nextInt(10) + 5;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 3;
            number7 = number6 + 3;
            number8 = number7 + 3;
            result = number4;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(5) + 7;
            number2 = number1 - 3;
            number3 = number2 - 2;
            number4 = number3 - 1;
            number5 = randomNumber1.nextInt(7) + 7;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 - 3;
            number7 = number6 - 2;
            number8 = number7 - 1;
            result = number4;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(5) + 1;
            number2 = number1 + 10;
            number3 = number2 + 10;
            number4 = number3 + 10;
            number5 = randomNumber1.nextInt(10) + 5;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 10;
            number7 = number6 + 10;
            number8 = number7 + 10;
            result = number4;
        }
    }

    public void combination5(Integer level){

        if(level == 1){
            number1 = randomNumber1.nextInt(10) + 1;
            number2 = number1 + 2;
            number3 = number2 + 3;
            number4 = number3 + 4;
            number5 = randomNumber1.nextInt(10) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 2;
            number7 = number6 + 3;
            number8 = number7 + 4;
            result = number8;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(10) + 2;
            number2 = number1 + 5;
            number3 = number2 + 5;
            number4 = number3 + 5;
            number5 = randomNumber1.nextInt(10) + 2;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 5;
            number7 = number6 + 5;
            number8 = number7 + 5;
            result = number8;
        }
        if(level == 3){
            number1 = randomNumber1.nextInt(80) + 35;
            number2 = number1 - 5;
            number3 = number2 - 10;
            number4 = number3 - 15;
            number5 = randomNumber1.nextInt(80) + 35;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 - 5;
            number7 = number6 - 10;
            number8 = number7 - 15;
            result = number8;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(20) + 2;
            number2 = number1 * 2;
            number3 = number2 * 2;
            number4 = number3 / 4;
            number5 = randomNumber1.nextInt(20) + 2;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 * 2;
            number7 = number6 * 2;
            number8 = number7 / 4;
            result = number8;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(50) + 30;
            number2 = number1 + 10;
            number3 = number2 + 10;
            number4 = number3 - 5;
            number5 = randomNumber1.nextInt(50) + 30;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 10;
            number7 = number6 + 10;
            number8 = number7 - 5;
            result = number8;
        }


    }

    public void combination6(Integer level){

        if(level == 1){
            number1 = randomNumber1.nextInt(10) + 1;
            number2 = number1 + 1;
            number3 = number1 * 2;
            number4 = number2 * 2;
            number5 = randomNumber1.nextInt(10) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 1;
            number7 = number5 * 2;
            number8 = number6 * 2;
            result = number5;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(10) + 1;
            number2 = number1 * 2;
            number3 = number2 * 2;
            number4 = number3 * 2;
            number5 = randomNumber1.nextInt(10) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 * 2;
            number7 = number6 * 2;
            number8 = number7 * 2;
            result = number5;
        }
        if(level == 3){
            number1 = randomNumber1.nextInt(5) + 2;
            number2 = number1 * number1;
            number3 = number2 + 5;
            number4 = number3 + 5;
            number5 = randomNumber1.nextInt(10) + 1;
            if(number5 == number1){
                number5++;
            }
            number6 = number5 * number5;
            number7 = number6 + 5;
            number8 = number7 + 5;
            result = number5;
        }
        if(level == 4){
            number1 = (randomNumber1.nextInt(10) + 2) * 3;
            number2 = number1 / 3;
            number3 = number2 + 2;
            number4 = number3 + 1;
            number5 = (randomNumber1.nextInt(10) + 2) * 3;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 / 3;
            number7 = number6 + 2;
            number8 = number7 + 1;
            result = number5;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(70) + 40;
            number2 = number1 + 10;
            number3 = number2 + 11;
            number4 = number3 + 12;
            number5 = randomNumber1.nextInt(40) + 30;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 10;
            number7 = number6 + 11;
            number8 = number7 + 12;
            result = number5;
        }





    }

    public void combination7(Integer level){
        if(level == 1){
            number1 = randomNumber1.nextInt(5) + 2;
            number5 = number1 + 2;
            number9 = number5 + 2;
            number2 = randomNumber1.nextInt(5) + 2;
            if(number2 == number1){
                number2 += 2;
            }
            number6 = number2 + 2;
            number10 = number6 + 2;
            number3 = randomNumber1.nextInt(5) + 7;
            number7 = number3 + 2;
            number11 = number7 + 2;
            result = number9;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(5) + 2;
            number5 = number1 + 1;
            number9 = number5 + 1;
            number2 = randomNumber1.nextInt(5) + 2;
            if(number2 == number1){
                number2 += 2;
            }
            number6 = number2 + 1;
            number10 = number6 + 1;
            number3 = randomNumber1.nextInt(5) + 7;
            number7 = number3 + 1;
            number11 = number7 + 1;
            result = number9;
        }
        if(level == 3){
            number1 = randomNumber1.nextInt(5) + 2;
            number5 = number1 + 10;
            number9 = number5 + 10;
            number2 = randomNumber1.nextInt(5) + 2;
            if(number2 == number1){
                number2 += 2;
            }
            number6 = number2 + 10;
            number10 = number6 + 10;
            number3 = randomNumber1.nextInt(5) + 7;
            number7 = number3 + 10;
            number11 = number7 + 10;
            result = number9;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(5) + 2;
            number5 = number1 + 1;
            number9 = number5 + 2;
            number2 = randomNumber1.nextInt(5) + 2;
            if(number2 == number1){
                number2 += 2;
            }
            number6 = number2 + 1;
            number10 = number6 + 2;
            number3 = randomNumber1.nextInt(5) + 7;
            number7 = number3 + 1;
            number11 = number7 + 2;
            result = number9;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(5) + 5;
            number5 = number1 - 1;
            number9 = number5 - 1;
            number2 = randomNumber1.nextInt(5) + 8;
            if(number2 == number1){
                number2 += 2;
            }
            number6 = number2 - 1;
            number10 = number6 - 1;
            number3 = randomNumber1.nextInt(5) + 15;
            number7 = number3 - 1;
            number11 = number7 - 1;
            result = number9;
        }
    }

    public void combination8(Integer level){
        if(level == 1){
            number1 = randomNumber1.nextInt(10) +2;
            number2 = number1 + 10;
            number3 = number2 - 5;
            number5 = randomNumber1.nextInt(10) +2;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 10;
            number7 = number6 - 5;
            number9 = randomNumber1.nextInt(10) + 14;
            number10 = number9 + 10;
            number11 = number10 - 5;
            result = number6;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(10) +2;
            number2 = number1 * 3;
            number3 = number2 / 3;
            number5 = randomNumber1.nextInt(10) +2;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 * 3;
            number7 = number6 / 3;
            number9 = randomNumber1.nextInt(10) + 14;
            number10 = number9 * 3;
            number11 = number10 / 3;
            result = number6;
        }
        if(level == 3){
            number1 = (randomNumber1.nextInt(20) + 13) * 10;
            number2 = number1 / 10;
            number3 = number2 + 10;
            number5 = (randomNumber1.nextInt(10) +2) * 10;
            if(number5 == number1){
                number5 += 10;
            }
            number6 = number5 / 10;
            number7 = number6 + 10;
            number9 = (randomNumber1.nextInt(10) + 2) * 10;
            if(number9 == number5){
                number9 += 10;
            }
            number10 = number9 / 10;
            number11 = number10 + 10;
            result = number6;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(20) + 13;
            number2 = number1 + 11;
            number3 = number2 + 12;
            number5 = randomNumber1.nextInt(10) +2;
            number6 = number5 + 11;
            number7 = number6 + 12;
            number9 = randomNumber1.nextInt(10) + 2;
            if(number9 == number5){
                number9 += 2;
            }
            number10 = number9 + 11;
            number11 = number10 + 12;
            result = number6;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(20) + 13;
            number2 = number1 * 2;
            number3 = number2 + number1;
            number5 = randomNumber1.nextInt(10) +2;
            number6 = number5 * 2;
            number7 = number6 + number5;
            number9 = randomNumber1.nextInt(10) + 2;
            if(number9 == number5){
                number9 += 2;
            }
            number10 = number9 * 2;
            number11 = number10 + number9;
            result = number6;
        }

    }

    public void combination9(Integer level){
        if(level == 1){
            number1 = randomNumber1.nextInt(10) + 2;
            number5 = number1 * 2;
            number9 = number5 * 2;
            number2 = randomNumber1.nextInt(10) + 2;
            if(number2 == number1){
                number2 += 2;
            }
            number6 = number2 * 2;
            number10 = number6 * 2;
            number3 = randomNumber1.nextInt(20) + 14;
            number7 = number3 * 2;
            number11 = number7 * 2;
            result = number5;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(10) + 2;
            number5 = number1 + 5;
            number9 = number5 + 10;
            number2 = randomNumber1.nextInt(10) + 2;
            if(number2 == number1){
                number2 += 2;
            }
            number6 = number2 + 5;
            number10 = number6 + 10;
            number3 = randomNumber1.nextInt(20) + 14;
            number7 = number3 + 5;
            number11 = number7 + 10;
            result = number5;
        }
        if(level == 3){
            number1 = randomNumber1.nextInt(90) + 80;
            number5 = number1 - 10;
            number9 = number5 - 10;
            number2 = randomNumber1.nextInt(50) + 30;
            number6 = number2 - 10;
            number10 = number6 - 10;
            number3 = randomNumber1.nextInt(50) + 30;
            if(number3 == number2){
                number3 += 5;
            }
            number7 = number3 - 10;
            number11 = number7 - 10;
            result = number5;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(50) + 35;
            number5 = number1 + 20;
            number9 = number5 + 20;
            number2 = randomNumber1.nextInt(20) + 14;
            number6 = number2 + 20;
            number10 = number6 + 20;
            number3 = randomNumber1.nextInt(10) + 2;
            number7 = number3 + 20;
            number11 = number7 + 20;
            result = number5;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(10) + 2;
            number5 = number1 + 3;
            number9 = number5 + 6;
            number2 = randomNumber1.nextInt(50) + 35;
            number6 = number2 + 3;
            number10 = number6 + 6;
            number3 = randomNumber1.nextInt(20) + 14;
            number7 = number3 + 3;
            number11 = number7 + 6;
            result = number5;
        }

    }

    public void combination10(Integer level){
        if(level == 1){
            number1 = randomNumber1.nextInt(10) + 2;
            number3 = number1 + 2;
            number2 = randomNumber1.nextInt(10) + 12;
            number4 = number2 + 2;
            number5 = randomNumber1.nextInt(10) + 2;
            if(number5 == number1){
                number5 += 2;
            }
            number7 = number5 + 2;
            number6 = randomNumber1.nextInt(10) + 12;
            if(number6 == number2){
                number6 += 2;
            }
            number8 = number6 + 2;
            number9 = randomNumber1.nextInt(50) + 25;
            number11 = number9 + 2;
            number10 = randomNumber1.nextInt(50) + 25;
            if(number10 == number9){
                number10 += 2;
            }
            number12 = number10 + 2;
            result = number8;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(10) + 2;
            number2 = number1 + 3;
            number3 = number2 + 5;
            number4 = number3 + 7;
            number5 = randomNumber1.nextInt(10) + 13;
            number6 = number5 + 3;
            number7 = number6 + 5;
            number8 = number7 + 7;
            number9 = randomNumber1.nextInt(10) +25;
            number10 = number9 + 3;
            number11 = number10 + 5;
            number12 = number11 + 7;
            result = number8;
        }
        if(level == 3){
            number1 = randomNumber1.nextInt(10) + 25;
            number2 = number1 + 5;
            number3 = number2 + 10;
            number4 = number3 + 15;
            number5 = randomNumber1.nextInt(10) + 13;
            number6 = number5 + 5;
            number7 = number6 + 10;
            number8 = number7 + 15;
            number9 = randomNumber1.nextInt(10) +2;
            number10 = number9 + 5;
            number11 = number10 + 10;
            number12 = number11 + 15;
            result = number8;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(10) + 70;
            number2 = number1 - 3;
            number3 = number2 - 5;
            number4 = number3 - 7;
            number5 = randomNumber1.nextInt(10) + 30;
            number6 = number5 - 3;
            number7 = number6 - 5;
            number8 = number7 - 7;
            number9 = randomNumber1.nextInt(10) +50;
            number10 = number9 - 3;
            number11 = number10 - 5;
            number12 = number11 - 7;
            result = number8;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(10) + 40;
            number2 = number1 - 10;
            number3 = number2 + 5;
            number4 = number3 - 10;
            number5 = randomNumber1.nextInt(10) + 80;
            number6 = number5 - 10;
            number7 = number6 + 5;
            number8 = number7 - 10;
            number9 = randomNumber1.nextInt(10) + 20;
            number10 = number9 - 10;
            number11 = number10 + 5;
            number12 = number11 - 10;
            result = number8;
        }




    }




    public void chooseLevel(Integer combination, Integer level){

        try {
            if(combination == 1){
                combination1(level);
            }
            if(combination == 2){
                combination2(level);
            }
            if(combination == 3){
                combination3(level);
            }
            if(combination == 4){
                combination4(level);
            }
            if(combination == 5){
                combination5(level);
            }
            if(combination == 6){
                combination6(level);
            }
            if(combination == 7){
                combination7(level);
            }
            if(combination == 8){
                combination8(level);
            }
            if(combination == 9){
                combination9(level);
            }
            if(combination == 10){
                combination10(level);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
