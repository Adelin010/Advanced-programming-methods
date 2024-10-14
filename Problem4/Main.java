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

        System.out.println("Most expansive: " + getMostExpansiveOfAll(usbs, keyboards));
        System.out.println("Cheapest: " + getCheapest(usbs));


        /*
         * Definning a comparator for the highest affordable price
         */

        int buget = new Random().nextInt(80) + 10;
        System.out.println("The buget is: " + buget);

        Comparator<Integer> highestAffordable = new Comparator<>() {

        
            @Override 
            public int compare(Integer f1, Integer f2){
                return f1.compareTo(f2);
            }
        };

        int affordable = getMostExpansiveAffordable(usbs, buget, highestAffordable);
        if(affordable < 0){
            System.out.println("There is no object we can afford");
        }else{
            System.out.println("We can afford: " + affordable);
        }

        System.out.println("We can buy if there are enough money: ");
        int canBuy = affordAllOfThem(keyboards, usbs, buget);
        if(canBuy != -1)
            System.out.println(canBuy);
        else
            System.out.println("We can't buy");
    }


    private static int getMostExpansive(Electronic[] elecs){
        if(elecs.length == 0)
            throw new Error("The argument array is empty. Please provide values");
        Optional<Integer> res = Arrays.stream(elecs).map(elec -> elec.price).max(Integer::compareTo);
        return res.get();
    }

    private static int getMostExpansiveOfAll(Electronic[] usbs, Electronic[] keyboards){
        return Math.max(getMostExpansive(keyboards), getMostExpansive(usbs));
    }

    private static int getCheapest(Electronic[] elecs){
        if(elecs.length == 0)
            throw new Error("The argument array is empty. Please provide values");
        Optional<Integer> res = Arrays.stream(elecs).map(elec -> elec.price).min(Integer::compareTo);
        return res.get();
    }

    private static int getMostExpansiveAffordable(Electronic[] elecs, int buget,Comparator<Integer> highestAffordable){
        if(elecs.length == 0)
            throw new Error("The argument array is empty. Please provide values");
        Optional<Integer> res = Arrays.stream(elecs).map(elec -> elec.price).filter(price -> price < buget).max(highestAffordable);
        if(!res.isPresent())
            return (int)-1.00;
        
        return res.get();
    }


    private static int affordAllOfThem(Electronic[] keyboards, Electronic[] usbs, int buget){
        /*
         * Use the TreeSet built on red-black balanced tree to add all the prices of 
         * an object in a sorted order
         * Complexity of adding O(log n)
         * Complexity of for loop O(n)
         * Complexity of the entire process O(n*log n) 
         */
        TreeSet<Integer> keyb = new TreeSet<>();
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
        int keyboardPrice = -1;
        int usbPrice = -1;
        for(Electronic usb: usbs){
            if(buget < usb.price)
                continue;
            
            int diff = buget - usb.price;
            try{
                int highPrice = keyb.lower(diff);
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
