package Problem4;

import java.util.Random;

public class Electronic {

    protected float price;

    public String toString(){
        return "The price is " + this.price;
    }  
    
    Electronic(){
        this.price = new Random().nextFloat(100);
    }
    Electronic(float price){
        this.price = price;
    }

}
