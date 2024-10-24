package Problem3;

import java.util.Random;
import java.util.Arrays;

public class Number {
    
    protected int[] degets;
    protected int length;

    Number(){
        Random r = new Random();
        this.length  = 10;
        this.degets = new int[10];
        for(int i = 0; i < 10; ++i){
            if(i == 0 ){
                this.degets[i] = r.nextInt(9)+1;
                continue;
            }
            this.degets[i] = r.nextInt(10);
        }
    }

    Number(int degetsNum){
        Random r = new Random();
        this.degets = new int[degetsNum];
        this.length = degetsNum;
        for(int i = 0; i < degetsNum; ++i){
            if(i == 0 ){
                this.degets[i] = r.nextInt(9)+1;
                continue;
            }
            this.degets[i] = r.nextInt();
        }
    }

    Number(int degetsNum, int val){
        this.degets = new int[degetsNum];
        this.length = degetsNum;
        for(int i = 0; i < degetsNum; ++i){
            this.degets[i] = val;
        }
    }

    Number(int[] values){

        int[] degets = Arrays.stream(values).filter(value -> value != -1 ).toArray();

        this.degets = new int[degets.length];
        for(int i = 0; i < degets.length; ++i){
            this.degets[i] = degets[i];
        }
    }

    public String toString(){
        String res = "Number representation: ";
        for(int num: this.degets){
            res =  res + num;
        }
        return res;
    }

    public static int generateNumberOfNPositionsOffseted(Number num, int n, int pos, int... initialVal){
        int res = 0;
        for(int number: initialVal)
            res = number;
        if(num.length < n)
            throw new Error("length of a Number object has to be bigger in order to be able to generate from it an int of n positions");
        for(int i = pos; i < num.length && n != 0; ++i){
            res = res * 10 + num.degets[i];
            n--;
        }
        return res;
    }
}
