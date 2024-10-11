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
        int[] values = new int[capacity];
        Arrays.fill(values, -1);
        
        //idx is used to start from the end part of the vector
        int idx = capacity-1;
        for(int i = num1.length-1 ; i >= 0; --i){
            int sum = num1.degets[i] + num2.degets[i];
            values[idx] = (sum + OC) % 10;
            idx--;
            if(sum+OC >= 10)
                OC = 1;
            else 
                OC = 0;
        }
        if(OC == 1)
            values[idx] = 1;
       
        return new Number(values);
    }


    public Number diffOf2(Number num1, Number num2){

        //make a copy of the array of the numbers
        int[] arr1 = new int[num1.length];
        for(int i = 0; i < arr1.length; ++i){
            arr1[i] = num1.degets[i];
        }
        int[] arr2 = new int[num2.length];
        for(int i = 0; i < arr2.length; ++i){
            arr2[i] = num2.degets[i];
        }

        //Check which one is bigger 
        for(int i = 0; i < num1.length; i++){
            if(arr1[i] > arr2[i])
                break;
            else if(arr1[i] == arr2[i])
                continue;
            //if the number from which I want to subtract is smaller
            //return null
            return null;
        }

        int[] values = new int[num1.length];
        Arrays.fill(values, -1);
        //execute all degets
        for(int i = arr1.length - 1; i >= 0; --i){
            if(arr1[i] >= arr2[i]){
                values[i] = arr1[i] - arr2[i];
            }else{
                //start the for to find the first deget to the left that is not 0
                //to substract 1
                int j;
                for(j = i -1; j >= 0; --j){
                    if(arr1[j] != 0){
                        arr1[j]--;
                        break;
                    }
                        
                }
                //Give +9 to all the degets between i and j except i if there is
                if(j != i-1){
                    for(int k = j+1; k < i; ++k){
                        arr1[k] += 9;
                    }
                }
                //Now make the diff from 10+deget
                values[i] = (arr1[i]+10) - arr2[i];
            }
        }
        //reset to -1 all the degets of 0 in the front of the number
        for(int i = 0; i < values.length; ++i){
            if(values[i] > 0)
                break;
            else if(values[i] == 0)
                values[i] = -1;
        }

        return new Number(values);
    }
}
