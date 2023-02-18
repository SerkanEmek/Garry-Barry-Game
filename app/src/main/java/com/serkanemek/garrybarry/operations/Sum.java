package com.serkanemek.garrybarry.operations;

public class Sum extends Numbers {


    public Sum(Integer level){
        try{
        if(level == 1) {
            number1 = random.nextInt(10);
            number2 = random.nextInt(10);
            if(number1 == 0){
                number1 += 1;
            }
            if(number2 == 0){
                number2 +=1;
            }
            //number3 = random.nextInt(10);
        }else if(level == 2){
            number1 = random.nextInt(100);
            number2 = random.nextInt(10);
            if(number1 < 10){
                number1 += 10;
            }
            if(number2 == 0){
                number2 +=1;
            }

            //number3 = random.nextInt(10);
        }else if(level == 3){
            number1 = random.nextInt(10);
            number2 = random.nextInt(10);
            number3 = random.nextInt(10);
            if(number1 == 0){
                number1 += 1;
            }
            if(number2 == 0){
                number2 +=1;
            }
            if(number3 == 0){
                number3 +=1;
            }
        }else if(level == 4){
            number1 = random.nextInt(100);
            number2 = random.nextInt(10);
            number3 = random.nextInt(10);
            if(number1 < 10){
                number1 += 10;
            }
            if(number2 == 0){
                number2 +=1;
            }
            if(number3 == 0){
                number3 +=1;
            }
        }else if(level == 5){
            number1 = random.nextInt(100);
            number2 = random.nextInt(100);
            number3 = random.nextInt(10);
            if(number1 < 10){
                number1 += 10;
            }
            if(number2 < 10){
                number2 +=10;
            }
            if(number3 == 0){
                number3 +=1;
            }
        }else if(level == 6){
            number1 = random.nextInt(100);
            number2 = random.nextInt(100);
            number3 = random.nextInt(100);
            if(number1 < 40){
                number1 += 40;
            }
            if(number2 < 30){
                number2 +=30;
            }
            if(number3 < 10){
                number3 +=10;
            }
        }}


        catch (Exception e){
            e.printStackTrace();
        }

    }

    public Integer calculater(Integer level){
        try{
            if(level == 1){
                result = number1 + number2;
                return result;
            } else if (level == 2){
                result = number1 + number2;
                return result;
            } else {
                result = number1+number2+number3;
                return result;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
