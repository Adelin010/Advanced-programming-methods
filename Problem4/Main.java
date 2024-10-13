package Problem4;

import java.util.Arrays;
import java.util.Optional;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

public class Main {
    
    public static void main(String[] args) {
        //Init the keyboards and USB arrays
        Electronic[] keyboards = new Electronic[10];
        Electronic[] usbs = new Electronic[10];

        for(int i = 0; i < 10; ++i){
            keyboards[i] = new Keyboard();
            usbs[i] = new USB();
        }

        for(Electronic usb: usbs)
            System.out.println(usb.toString());

        System.out.println("*********************");
        for(Electronic k: keyboards)
            System.out.println(k.toString());

        System.out.println("Most expansive: " + getMostExpansive(usbs));
        System.out.println("Cheapest: " + getCheapest(usbs));


        /*
         * Definning a comparator for the highest affordable price
         */

        float buget = new Random().nextFloat(80) + 10;
        System.out.println("The buget is: " + buget);

        Comparator<Float> highestAffordable = new Comparator<Float>() {

        
            @Override 
            public int compare(Float f1, Float f2){
                return f1.compareTo(f2);
            }
        };

        float affordable = getMostExpansiveAffordable(usbs, buget, highestAffordable);
        if(affordable < 0.00){
            System.out.println("There is no object we can afford");
        }else{
            System.out.println("We can afford: " + affordable);
        }

        System.out.println("We can buy if there are enough money: ");
        float canBuy = affordAllOfThem(keyboards, usbs, buget);
        if(canBuy != -1)
            System.out.println(canBuy);
        else
            System.out.println("We can't buy");
    }


    private static float getMostExpansive(Electronic[] elecs){
        if(elecs.length == 0)
            throw new Error("The argument array is empty. Please provide values");
        Optional<Float> res = Arrays.stream(elecs).map(elec -> elec.price).max(Float::compareTo);
        return res.get();
    }

    private static float getCheapest(Electronic[] elecs){
        if(elecs.length == 0)
            throw new Error("The argument array is empty. Please provide values");
        Optional<Float> res = Arrays.stream(elecs).map(elec -> elec.price).min(Float::compareTo);
        return res.get();
    }

    private static float getMostExpansiveAffordable(Electronic[] elecs, float buget,Comparator<Float> highestAffordable){
        if(elecs.length == 0)
            throw new Error("The argument array is empty. Please provide values");
        Optional<Float> res = Arrays.stream(elecs).map(elec -> elec.price).filter(price -> price < buget).max(highestAffordable);
        if(!res.isPresent())
            return (float)-1.00;
        
        return res.get();
    }


    private static float affordAllOfThem(Electronic[] keyboards, Electronic[] usbs, float buget){
        /*
         * Use the TreeSet built on red-black balanced tree to add all the prices of 
         * an object in a sorted order
         * Complexity of adding O(log n)
         * Complexity of for loop O(n)
         * Complexity of the entire process O(n*log n) 
         */
        TreeSet<Float> keyb = new TreeSet<>();
        for(Electronic keyboard: keyboards){
            keyb.add(keyboard.price);
        }

        /*
         * Travers the second vector and for each price 
         * search for the highest possible value 
         * lower than the differance between buget and second vector price(left over money)
         * save the result and compare it with all the other iterations
         * Complexity of traversing O(n)
         * Complexity of searching O(log n)
         * Whole Complexity O(n*log n)
         */
        float keyboardPrice = -1;
        float usbPrice = -1;
        for(Electronic usb: usbs){
            if(buget < usb.price)
                continue;
            
            float diff = buget - usb.price;
            try{
                float highPrice = keyb.lower(diff);
                if(highPrice > keyboardPrice){
                    usbPrice = usb.price;
                    keyboardPrice = highPrice;
                }

            }catch(NullPointerException exp){
                continue;
            }
            
        }
        return keyboardPrice + usbPrice;

    }


}
