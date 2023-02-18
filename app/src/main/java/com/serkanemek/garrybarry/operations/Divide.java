package com.serkanemek.garrybarry.operations;

public class Divide extends Numbers {

    public Divide(Integer level){
        {
            try{
                if(level == 1) {
                   number2 = random.nextInt(10);
                    if(number2 <= 1){
                        number2 += 2 ;
                    }
                    number1 = number2 * (random.nextInt(7) + 2);

                }else if(level == 2){
                    number2 = random.nextInt(10);
                    if(number2 <= 1){
                        number2 += 3 ;
                    }
                    number1 = number2 * (random.nextInt(12) + 2);

                }else if(level == 3){
                    number2 = random.nextInt(12);
                    if(number2 <= 2){
                        number2 += 3 ;
                    }
                    number1 = number2 * (random.nextInt(15) + 2);

                }else if(level == 4){
                    number2 = random.nextInt(15);
                    if(number2 <= 2){
                        number2 += 3 ;
                    }
                    number1 = number2 * (random.nextInt(20) + 2);
                }else if(level == 5){
                    number2 = random.nextInt(20);
                    if(number2 <= 2){
                        number2 += 4 ;
                    }
                    number1 = number2 * (random.nextInt(20) + 2);

                }else if(level == 6){
                    number2 = random.nextInt(20);
                    if(number2 <= 3){
                        number2 += 4 ;
                    }
                    number1 = number2 * (random.nextInt(30) + 2);
                }}


            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public Integer calculater(Integer level){
        try{
           result = number1 / number2;
           return result;

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
