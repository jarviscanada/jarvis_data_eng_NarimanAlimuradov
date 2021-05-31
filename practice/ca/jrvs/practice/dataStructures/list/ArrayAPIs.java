package ca.jrvs.practice.dataStructures.list;

import java.util.Arrays;
import java.util.List;

public class ArrayAPIs {

    public static void main(String[] args) {
        int[] intArray = new int[10];
        intArray[0] = 31;
        intArray[1] = 32;
        intArray[2] = 12;

        int[] fastArray = {1, 2, 3};

        String[] stringArray = {"apple", "banana", "orange", "avocado"};
        int[][] matrix = {{1,2,3}, {4,5,6},{7,8,9}};

        String[] newFruitArray = Arrays.copyOfRange(stringArray, 0, 2);
        System.out.println(Arrays.toString(newFruitArray));

        System.arraycopy(intArray, 0, fastArray, 2, 1);
        System.out.println(Arrays.toString(fastArray));

        List<String> intList = Arrays.asList(stringArray);

        Arrays.sort(stringArray);
        System.out.println(Arrays.binarySearch(stringArray, "banana"));

    }
}
