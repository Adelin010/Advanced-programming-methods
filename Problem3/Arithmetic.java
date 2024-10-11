package Problem3;

import java.util.Arrays;

public class Arithmetic {
    

    public Number sumOf2(Number num1, Number num2){
        //OC overflow flag for the carry 
        int OC = 0;
        //For the result we need a vector which is 1 and a half bigger to 
        //store the degets in case of overflowing 
        //all the fields that are not in use will be denoted with -1
        int capacity = (int)(num1.length * 1.5);
        System.out.println(capacity);
        int[] values = new int[capacity];
        Arrays.fill(values, -1);
        for(int val: values)
            System.out.println(val);
        //idx is used to start from the end part of the vector
        int idx = capacity-1;
        for(int i = num1.length-1 ; i >= 0; --i){
            int sum = num1.degets[i] + num2.degets[i];
            values[idx] = (sum + OC) % 10;
            idx--;
            if(sum >= 10)
                OC = 1;
            else 
                OC = 0;
        }
        if(OC == 1)
            values[idx] = 1;
        for(int val: values)
            System.out.println(val);

        return new Number(values);
    }
}
