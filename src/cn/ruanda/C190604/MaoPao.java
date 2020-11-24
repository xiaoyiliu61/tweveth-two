package cn.ruanda.C190604;

import java.util.Arrays;

public class MaoPao {
    public static void main(String[] args) {
        int[] array =new int[] {9, 8, 10, 23, 89, 76, 45, 7, 23, 41};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
            System.out.println("从小到大排序的结果：");

           System.out.println(Arrays.toString(array));

    }
}
