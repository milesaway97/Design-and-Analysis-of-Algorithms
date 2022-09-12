import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    static int sets_remaining;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] initial_values = new int[scanner.nextInt()];
        for (int i = 0; i < initial_values.length; i++){
            initial_values[i] = scanner.nextInt();
        }
        ArrayList<Integer> Listofsums = new ArrayList<>(Collections.nCopies(20000000, 0));
        sets_remaining = (int) Math.pow(2, initial_values.length);
        MostCommonSum(initial_values, Listofsums, 0, 0);
    }

    //0 = 10000000

    public static void MostCommonSum(int[] initial_values, ArrayList<Integer> Listofsums, int index, int sum) {
        if (index == initial_values.length) {
            Listofsums.set(sum + 10000000, Listofsums.get(sum + 10000000) + 1);
            sets_remaining--;
            if (sets_remaining == 0) {
                PrintSolution(Listofsums);
            }
        } else {
            MostCommonSum(initial_values, Listofsums, index + 1, sum + initial_values[index]);

            MostCommonSum(initial_values, Listofsums, index + 1, sum);
        }
    }

    static void PrintSolution(ArrayList<Integer> Listofsums) {
        int totalSubsets = Collections.max(Listofsums);
        boolean not_found = true;
        int a = 0;
        while (not_found) {
            if (Listofsums.get(a) == totalSubsets) {
                System.out.println(a - 10000000 + " " + totalSubsets);
                not_found = false;
            }
            a++;
        }
    }
}

