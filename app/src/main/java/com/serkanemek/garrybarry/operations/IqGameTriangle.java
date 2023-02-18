package com.serkanemek.garrybarry.operations;

import java.util.Random;

public class IqGameTriangle extends NumbersForIq {

    public IqGameTriangle(Integer combination, Integer level){

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
            result = number6;
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
            result = number6;
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
            result = number6;
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
            result = number6;
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
            result = number6;
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
            result = number5;
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
            result = number5;
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
            result = number5;
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
            result = number5;
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
            result = number5;
        }

    }


    public void combination4(Integer level){
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
                number5 += 2;
            }
            number6 = number5 / 10;
            number7 = number6 + 10;
            number9 = (randomNumber1.nextInt(10) + 2) * 10;
            if(number9 == number5){
                number9 += 2;
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

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
