package com.serkanemek.garrybarry.operations;

import java.util.Random;

public class MemoryNumbers {

    Random randomNumber;
    Integer numb1,numb2,numb3,numb4,numb5;
   // Integer level;

    public MemoryNumbers(Integer level) {
        randomNumber = new Random();

            if(level == 1){
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(90) + 10;
            }
            if(level == 2){
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(9) + 1;
                numb4 = randomNumber.nextInt(9) + 1;
            }
            if(level == 3){
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(9) + 1;
                numb4 = randomNumber.nextInt(90) + 10;
            }
            if(level == 4){
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(90) + 10;
            }
            if(level == 5){
                numb2 = randomNumber.nextInt(900) + 100;
                numb3 = randomNumber.nextInt(900) + 100;
            }
            if(level == 6){
                numb2 = randomNumber.nextInt(9) + 1;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(90) + 10;
            }
            if(level == 7){
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(90) + 10;
            }
            if(level == 8){
                numb1 = randomNumber.nextInt(9) + 1;
                numb2 = randomNumber.nextInt(9) + 1;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(9) + 1;
            }
            if(level == 9){
                numb1 = randomNumber.nextInt(9) + 1;
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(9) + 1;
            }
            if(level == 10){
                numb1 = randomNumber.nextInt(9) + 1;
                numb2 = randomNumber.nextInt(9) + 1;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(9) + 1;
            }
            if(level == 11){
                numb1 = randomNumber.nextInt(90) + 10;
                numb2 = randomNumber.nextInt(9) + 1;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(90) + 10;
            }
            if(level == 12){
                numb1 = randomNumber.nextInt(90) + 10;
                numb2 = randomNumber.nextInt(9) + 1;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(9) + 1;
            }
            if(level == 13){
                numb1 = randomNumber.nextInt(90) + 10;
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(90) + 10;
            }
            if(level == 14){
                numb1 = randomNumber.nextInt(900) + 100;
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(9) + 1;
                numb4 = randomNumber.nextInt(90) + 10;
            }
            if(level == 15){
                numb2 = randomNumber.nextInt(900) + 100;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(900) + 100;
            }
            if(level == 16){
                numb1 = randomNumber.nextInt(900) + 100;
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(9) + 1;
            }
            if(level == 17){
                numb1 = randomNumber.nextInt(9) + 1;
                numb2 = randomNumber.nextInt(900) + 100;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(900) + 100;
            }
            if(level == 18){
                numb1 = randomNumber.nextInt(9) + 1;
                numb2 = randomNumber.nextInt(9) + 1;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(9) + 1;
                numb5 = randomNumber.nextInt(9) + 1;
            }
            if(level == 19){
                numb1 = randomNumber.nextInt(9) + 1;
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(90) + 10;
                numb5 = randomNumber.nextInt(9) + 1;
            }
            if(level == 20){
                numb1 = randomNumber.nextInt(9) + 1;
                numb2 = randomNumber.nextInt(900) + 100;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(9) + 1;
                numb5 = randomNumber.nextInt(9) + 1;
            }
            if(level == 21){
                numb1 = randomNumber.nextInt(90) + 10;
                numb2 = randomNumber.nextInt(9) + 1;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(9) + 1;
                numb5 = randomNumber.nextInt(90) + 10;
            }
            if(level == 22){
                numb1 = randomNumber.nextInt(90) + 10;
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(9) + 1;
                numb5 = randomNumber.nextInt(90) + 10;
            }
            if(level == 23){
                numb1 = randomNumber.nextInt(900) + 100;
                numb2 = randomNumber.nextInt(9) + 1;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(90) + 10;
                numb5 = randomNumber.nextInt(9) + 1;
            }
            if(level == 24){
                numb1 = randomNumber.nextInt(900) + 100;
                numb2 = randomNumber.nextInt(900) + 100;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(900) + 100;
            }
            if(level == 25){
                numb1 = randomNumber.nextInt(90) + 10;
                numb2 = randomNumber.nextInt(90) + 10;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(90) + 10;
                numb5 = randomNumber.nextInt(90) + 10;
            }
            if(level == 26){
                numb1 = randomNumber.nextInt(90) + 10;
                numb2 = randomNumber.nextInt(900) + 100;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(900) + 100;
                numb5 = randomNumber.nextInt(9) + 1;
            }
            if(level == 27){
                numb1 = randomNumber.nextInt(90) + 10;
                numb2 = randomNumber.nextInt(900) + 100;
                numb3 = randomNumber.nextInt(90) + 10;
                numb4 = randomNumber.nextInt(900) + 100;
                numb5 = randomNumber.nextInt(90) + 10;
            }
            if(level == 28){
                numb1 = randomNumber.nextInt(900) + 100;
                numb2 = randomNumber.nextInt(9) + 1;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(900) + 100;
                numb5 = randomNumber.nextInt(90) + 10;
            }
            if(level == 29){
                numb1 = randomNumber.nextInt(900) + 100;
                numb2 = randomNumber.nextInt(900) + 100;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(900) + 100;
                numb5 = randomNumber.nextInt(90) + 10;
            }
            if(level == 30){
                numb1 = randomNumber.nextInt(900) + 100;
                numb2 = randomNumber.nextInt(900) + 100;
                numb3 = randomNumber.nextInt(900) + 100;
                numb4 = randomNumber.nextInt(900) + 100;
                numb5 = randomNumber.nextInt(900) + 100;
            }



    }
    public void calculate(Integer level){
        if(level == 1){
            numb2 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(90) + 10;
        }
        if(level == 2){
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(9) + 1;
            numb4 = randomNumber.nextInt(9) + 1;
        }
        if(level == 3){
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(9) + 1;
            numb4 = randomNumber.nextInt(90) + 10;
        }
        if(level == 4){
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(90) + 10;
        }
        if(level == 5){
            numb2 = randomNumber.nextInt(900) + 100;
            numb3 = randomNumber.nextInt(900) + 100;
        }
        if(level == 6){
            numb2 = randomNumber.nextInt(9) + 1;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(90) + 10;
        }
        if(level == 7){
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(90) + 10;
        }
        if(level == 8){
            numb1 = randomNumber.nextInt(9) + 1;
            numb2 = randomNumber.nextInt(9) + 1;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(9) + 1;
        }
        if(level == 9){
            numb1 = randomNumber.nextInt(9) + 1;
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(9) + 1;
        }
        if(level == 10){
            numb1 = randomNumber.nextInt(9) + 1;
            numb2 = randomNumber.nextInt(9) + 1;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(9) + 1;
        }
        if(level == 11){
            numb1 = randomNumber.nextInt(90) + 10;
            numb2 = randomNumber.nextInt(9) + 1;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(90) + 10;
        }
        if(level == 12){
            numb1 = randomNumber.nextInt(90) + 10;
            numb2 = randomNumber.nextInt(9) + 1;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(9) + 1;
        }
        if(level == 13){
            numb1 = randomNumber.nextInt(90) + 10;
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(90) + 10;
        }
        if(level == 14){
            numb1 = randomNumber.nextInt(900) + 100;
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(9) + 1;
            numb4 = randomNumber.nextInt(90) + 10;
        }
        if(level == 15){
            numb2 = randomNumber.nextInt(900) + 100;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(900) + 100;
        }
        if(level == 16){
            numb1 = randomNumber.nextInt(900) + 100;
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(9) + 1;
        }
        if(level == 17){
            numb1 = randomNumber.nextInt(9) + 1;
            numb2 = randomNumber.nextInt(900) + 100;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(900) + 100;
        }
        if(level == 18){
            numb1 = randomNumber.nextInt(9) + 1;
            numb2 = randomNumber.nextInt(9) + 1;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(9) + 1;
            numb5 = randomNumber.nextInt(9) + 1;
        }
        if(level == 19){
            numb1 = randomNumber.nextInt(9) + 1;
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(90) + 10;
            numb5 = randomNumber.nextInt(9) + 1;
        }
        if(level == 20){
            numb1 = randomNumber.nextInt(9) + 1;
            numb2 = randomNumber.nextInt(900) + 100;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(9) + 1;
            numb5 = randomNumber.nextInt(9) + 1;
        }
        if(level == 21){
            numb1 = randomNumber.nextInt(90) + 10;
            numb2 = randomNumber.nextInt(9) + 1;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(9) + 1;
            numb5 = randomNumber.nextInt(90) + 10;
        }
        if(level == 22){
            numb1 = randomNumber.nextInt(90) + 10;
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(9) + 1;
            numb5 = randomNumber.nextInt(90) + 10;
        }
        if(level == 23){
            numb1 = randomNumber.nextInt(900) + 100;
            numb2 = randomNumber.nextInt(9) + 1;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(90) + 10;
            numb5 = randomNumber.nextInt(9) + 1;
        }
        if(level == 24){
            numb1 = randomNumber.nextInt(900) + 100;
            numb2 = randomNumber.nextInt(900) + 100;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(900) + 100;
        }
        if(level == 25){
            numb1 = randomNumber.nextInt(90) + 10;
            numb2 = randomNumber.nextInt(90) + 10;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(90) + 10;
            numb5 = randomNumber.nextInt(90) + 10;
        }
        if(level == 26){
            numb1 = randomNumber.nextInt(90) + 10;
            numb2 = randomNumber.nextInt(900) + 100;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(900) + 100;
            numb5 = randomNumber.nextInt(9) + 1;
        }
        if(level == 27){
            numb1 = randomNumber.nextInt(90) + 10;
            numb2 = randomNumber.nextInt(900) + 100;
            numb3 = randomNumber.nextInt(90) + 10;
            numb4 = randomNumber.nextInt(900) + 100;
            numb5 = randomNumber.nextInt(90) + 10;
        }
        if(level == 28){
            numb1 = randomNumber.nextInt(900) + 100;
            numb2 = randomNumber.nextInt(9) + 1;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(900) + 100;
            numb5 = randomNumber.nextInt(90) + 10;
        }
        if(level == 29){
            numb1 = randomNumber.nextInt(900) + 100;
            numb2 = randomNumber.nextInt(900) + 100;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(900) + 100;
            numb5 = randomNumber.nextInt(90) + 10;
        }
        if(level == 30){
            numb1 = randomNumber.nextInt(900) + 100;
            numb2 = randomNumber.nextInt(900) + 100;
            numb3 = randomNumber.nextInt(900) + 100;
            numb4 = randomNumber.nextInt(900) + 100;
            numb5 = randomNumber.nextInt(900) + 100;
        }
    }


    public Integer getNumb1() {
        return numb1;
    }

    public Integer getNumb2() {
        return numb2;
    }

    public Integer getNumb3() {
        return numb3;
    }

    public Integer getNumb4() {
        return numb4;
    }

    public Integer getNumb5() {
        return numb5;
    }


}
