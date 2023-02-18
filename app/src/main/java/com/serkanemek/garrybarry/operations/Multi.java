package com.serkanemek.garrybarry.operations;

public class Multi extends Numbers {

    public Multi(Integer level){

        try {

                if(level == 1) {
                    number1 = random.nextInt(10);
                    number2 = random.nextInt(10);
                    if(number1 <= 1){
                        number1 += 1;
                    }
                    if(number2 <= 1){
                        number2 += 1;
                    }

                    //number3 = random.nextInt(10);
                }else if(level == 2){
                    number1 = random.nextInt(20);
                    number2 = random.nextInt(10);
                    if(number1 < 10){
                        number1 += 5;
                    }
                    if(number2 <= 1){
                        number2 += 1;
                    }
                    //number3 = random.nextInt(10);
                }else if(level == 3){
                    number1 = random.nextInt(10);
                    number2 = random.nextInt(10);
                    number3 = random.nextInt(10);
                    if(number1 <= 1){
                        number1 += 1;
                    }
                    if(number2 <= 1){
                        number2 += 1;
                    }
                    if(number3 <= 1){
                        number3 +=1;
                    }


                }else if(level == 4){
                    number1 = random.nextInt(50);
                    number2 = random.nextInt(10);
                    if(number1 < 10){
                        number1 += 15;
                    }
                    if(number2 <= 1){
                        number2 += 1;
                    }
                   // number3 = random.nextInt(10);
                }else if(level == 5){
                    number1 = random.nextInt(50);
                    number2 = random.nextInt(10);
                    number3 = random.nextInt(10);
                    if(number1 <20){
                        number1 += 15;
                    }
                    if(number2 <= 1){
                        number2 += 1;
                    }
                    if(number3 <= 1){
                        number3 += 1;
                    }
                }else if(level == 6){
                    number1 = random.nextInt(100);
                    number2 = random.nextInt(20);
                    number3 = random.nextInt(10);
                    if(number1 < 20){
                        number1 += 20;
                    }
                    if(number2 <10){
                        number2 += 10;
                    }
                    if(number3 <= 1){
                        number3 += 1;
                    }
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Integer calculater(Integer level){
        try{
            if(level == 1 || level == 2 || level == 4){
                result = number1 * number2;
                return result;
            } else if (level == 3 || level == 5 || level == 6){
                result = number1 * number2 * number3;
                return result;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
