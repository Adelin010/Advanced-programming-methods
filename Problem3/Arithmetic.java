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

    public Number multiplication(Number num, int scalar){
        int[] values = new int[(int)(num.length*1.5)];
        int idx = values.length - 1;
        Arrays.fill(values, -1);
        //flag for carry
        int OC = 0;
        for(int i = num.length-1; i >= 0; --i){
            int mult = ((scalar * num.degets[i]) + OC);
            values[idx--] = mult % 10;
            OC = mult / 10;
        }
        if(OC != 0)
            values[idx] = OC;
        return new Number(values);
    }

    public Number division(Number n1, int scalar){
        int[] values = new int[n1.length];
        Arrays.fill(values, -1);
        int idxValues = 0;

        if(n1.length < 1)
            throw new Error("Incapable to make a division, Divided to short !");
        //index to the current deget to be divided
        int idx = 0;
        int rest = 0;
        //Generate the number of length = scalars number of degets 
        //if the number generated is lower than we will add the next deget
        //the function is made so in the future will be able to get a n2.length or n2.length+1
        //argument of a object of type Number to make division between two Number objects
        while(idx < n1.length){
            int divided = Number.generateNumberOfNPositionsOffseted(n1, 1, idx, rest);
            if(divided < scalar && idx  == 0){
                divided = Number.generateNumberOfNPositionsOffseted(n1, 2, idx, rest);
                idx += 2;
                rest = divided % scalar;
                values[idxValues++] = divided / scalar;
                continue;
            }
            rest = divided % scalar;
            values[idxValues++] = divided / scalar;
            idx += 1;
        }

        return new Number(values);
    }
}
