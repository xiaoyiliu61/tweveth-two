package cn.ruanda.C190604;

import java.lang.reflect.Array;
import java.util.Arrays;

public class XuanZe {
    public static void main(String[] args) {
        int[] array ={9, 8, 10, 23, 89, 76, 45, 7, 23, 41};
        for (int i = 0;i<array.length-1;i++){
            for (int j =i+1;j<array.length;j++){
                if (array[i]>array[j]){
                    int temp = array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
