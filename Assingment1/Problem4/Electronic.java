package Problem4;

import java.util.Random;

public class Electronic {

    protected int price;

    public String toString(){
        return "The price is " + this.price;
    }  
    
    Electronic(){
        this.price = new Random().nextInt(100);
    }
    Electronic(int price){
        this.price = price;
    }

}
