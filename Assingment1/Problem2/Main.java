package Problem2;

import java.util.Arrays;
import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[20];
        for(int i = 0; i < 20; ++i){
            array[i] = rand.nextInt(100);
        }

        for(int i: array)
            System.out.println(i);

        System.out.println("\nMaximum: " + maximal(array));
        System.out.println("\nMinimum: " + minimal(array));
        System.out.println("___________________________");
        System.out.println("Max Summ: " + maxSumm(array));
        System.out.println("Min Summ: " + minSumm(array));
    }

    private static int maximal(int[] numbers){
        return Arrays.stream(numbers).max().getAsInt();
    }

    private static int minimal(int[] numbers){
        return Arrays.stream(numbers).min().getAsInt();
    }

    private static int maxSumm(int[] nums){
        int min = minimal(nums);
        int sum  = Arrays.stream(nums).reduce(0, (a, b) -> a+b);
        return sum - min;
    }

    private static int minSumm(int[] nums){
        int max = maximal(nums);
        int sum = Arrays.stream(nums).reduce(0, (a, b) -> a+b);
        return sum - max;
    }
}
