package com.serkanemek.garrybarry.operations;

import java.util.Random;

public class IqGameXfigure extends NumbersForIq {


    public IqGameXfigure(Integer combination, Integer level){

        randomNumber1 = new Random();
        chooseLevel(combination, level);
    }

    public void combination1(Integer level){
        if(level == 1){
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
            result = number7;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(5) + 1;
            number2 = number1 + 5;
            number3 = number2 + 5;
            number4 = number3 + 5;
            number5 = randomNumber1.nextInt(10) + 5;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 5;
            number7 = number6 + 5;
            number8 = number7 + 5;
            result = number7;
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
            result = number7;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(5) + 7;
            number2 = number1 - 1;
            number3 = number2 - 1;
            number4 = number3 - 1;
            number5 = randomNumber1.nextInt(7) + 7;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 - 1;
            number7 = number6 - 1;
            number8 = number7 - 1;
            result = number7;
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
            result = number7;
        }
    }

    public void combination2(Integer level){

        if(level == 1){
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
            result = number8;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(10) + 2;
            number2 = number1 + 5;
            number3 = number2 + 10;
            number4 = number3 + 15;
            number5 = randomNumber1.nextInt(10) + 2;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 5;
            number7 = number6 + 10;
            number8 = number7 + 15;
            result = number8;
        }
        if(level == 3){
            number1 = randomNumber1.nextInt(80) + 35;
            number2 = number1 - 5;
            number3 = number2 - 5;
            number4 = number3 - 5;
            number5 = randomNumber1.nextInt(80) + 35;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 - 5;
            number7 = number6 - 5;
            number8 = number7 - 5;
            result = number8;
        }
        if(level == 4){
            number1 = randomNumber1.nextInt(20) + 2;
            number2 = number1 + 1;
            number3 = number2 + 2;
            number4 = number3 + 3;
            number5 = randomNumber1.nextInt(20) + 2;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 1;
            number7 = number6 + 2;
            number8 = number7 + 3;
            result = number8;
        }
        if(level == 5){
            number1 = randomNumber1.nextInt(50) + 30;
            number2 = number1 + 10;
            number3 = number2 + 20;
            number4 = number3 + 30;
            number5 = randomNumber1.nextInt(50) + 30;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 10;
            number7 = number6 + 20;
            number8 = number7 + 30;
            result = number8;
        }


    }

    public void combination3(Integer level){

        if(level == 1){
            number1 = randomNumber1.nextInt(10) + 1;
            number2 = number1 + 20;
            number3 = number2 + 20;
            number4 = number3 + 20;
            number5 = randomNumber1.nextInt(10) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 20;
            number7 = number6 + 20;
            number8 = number7 + 20;
            result = number5;
        }
        if(level == 2){
            number1 = randomNumber1.nextInt(10) + 20;
            number2 = number1 - 2;
            number3 = number2 - 2;
            number4 = number3 - 2;
            number5 = randomNumber1.nextInt(10) + 20;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 - 2;
            number7 = number6 - 2;
            number8 = number7 - 2;
            result = number5;
        }
        if(level == 3){
            number1 = randomNumber1.nextInt(5) + 2;
            number2 = number1 * number1;
            number3 = number2 + 1;
            number4 = number3 * number3;
            number5 = randomNumber1.nextInt(10) + 1;
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 * number5;
            number7 = number6 + 1;
            number8 = number7 * number7;
            result = number5;
        }
        if(level == 4){
            number1 = (randomNumber1.nextInt(10) + 20);
            number2 = number1 + 1;
            number3 = number2 + 2;
            number4 = number3 + 1;
            number5 = (randomNumber1.nextInt(10) + 20);
            if(number5 == number1){
                number5 += 2;
            }
            number6 = number5 + 1;
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
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
