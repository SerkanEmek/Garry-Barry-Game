package com.serkanemek.garrybarry.operations;

public class Subs extends Numbers {

    public Subs(Integer level) {

        try{
            if(level == 1) {
                number1 = random.nextInt(10);
                number2 = random.nextInt(10);
                if(number1 < number2){
                    int temp = number1;
                    number1 = number2;
                    number2 = temp;
                }
                if(number1 <= 1){
                    number1 += 2;
                }
                if(number2 == 0){
                    number2 += 1;
                }
                if(number1 == number2){
                    number1 +=1;
                }

                //number3 = random.nextInt(10);
            }else if(level == 2){
                number1 = random.nextInt(40);
                number2 = random.nextInt(10);
                if(number1 < number2){
                    int temp = number1;
                    number1 = number2;
                    number2 = temp;
                }
                if(number1 < 10){
                    number1 +=10;
                }
                if(number2 <= 3){
                    number2 += 3;
                }

                //number3 = random.nextInt(10);
            }else if(level == 3){
                number1 = random.nextInt(100);
                number2 = random.nextInt(50);
                if(number1 < number2){
                    int temp = number1;
                    number1 = number2;
                    number2 = temp;
                }
                if(number1 < 50){
                    number1 += 30;
                }
                if(number2 <20 ){
                    number2 += 20;
                }
                if(number1 == number2){
                    number1 +=1;
                }

                //number3 = random.nextInt(10);
            }else if(level == 4){
                number1 = random.nextInt(200);
                number2 = random.nextInt(100);

                if(number1 < number2){
                    int temp = number1;
                    number1 = number2;
                    number2 = temp;
                }
                if(number1 < 100 ){
                    number1 += 100;
                }
                if(number2 < 40){
                    number2 += 30;
                }

                // number3 = random.nextInt(10);
            }else if(level == 5){
                number1 = random.nextInt(500);
                number2 = random.nextInt(300);

                if(number1 < 200){
                    number1 += 200;
                }
                if(number2 < 100){
                    number2 += 150;
                }
                if(number1 == number2){
                    number1 +=1;
                }
                if(number1 < number2){
                    int temp = number1;
                    number1 = number2;
                    number2 = temp;
                }


                // number3 = random.nextInt(10);
            }else if(level == 6){
                number1 = random.nextInt(999);
                number2 = random.nextInt(600);

                if (number1 < 500){
                    number1 += 500;
                }
                if (number2 <300){
                    number2 += 250;
                }
                if(number1 == number2){
                    number1 +=1;
                }
                if(number1 < number2){
                    int temp = number1;
                    number1 = number2;
                    number2 = temp;
                }

                // number3 = random.nextInt(100);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setLevel(Integer level){
        this.level = level;
    }

    public Integer calculater(Integer level){
        try{
            if(number1 > number2){
                result = number1 - number2;
                return result;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
