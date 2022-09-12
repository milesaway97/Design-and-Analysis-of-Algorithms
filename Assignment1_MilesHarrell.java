import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int columns, rows, no_facts, no_lies;
        String val;
        StringBuilder temp = null;
        ArrayList<Integer> values = new ArrayList<>();

        rows = scanner.nextInt();
        columns = scanner.nextInt();
        val = scanner.nextLine();
        for (int i = 0; i < val.length(); i++) {
            if (val.charAt(i) != ' ')
                temp.append(val.charAt(i));
            else
                values.add(val.charAt(i) - '0');
            if (i == val.length() - 1)
                values.add(val.charAt(i) - '0');
        }
        no_facts = scanner.nextInt();
        no_lies = scanner.nextInt();

        ArrayList<String> facts = new ArrayList<>();
        for (int i = 0; i < no_facts; i++) {
            facts.add(scanner.nextLine());
        }
        
        int truths = no_facts - no_lies;
        ArrayList<ArrayList<String>> combinations = generate(facts, no_facts, truths);
        int[][] LowerBound = new int[rows][columns];
        int[][] UpperBound = new int[rows][columns];

        for (ArrayList<String> combination : combinations) {

            for (String c :combination) {

                if(c.charAt(0) == 'c'){

                }
            }
        }
    }

    public static void helper(ArrayList<ArrayList<String>> combinations, ArrayList<String> facts, ArrayList<String> data, int start, int end, int index) {
        if (index == data.size()) {
            combinations.add(data);
        } else if (start <= end) {
            data.set(index, facts.get(start));
            helper(combinations, facts, data, start + 1, end, index + 1);
            helper(combinations, facts, data, start + 1, end, index);
        }
    }

    // Generation of combinations help from: https://www.baeldung.com/java-combinations-algorithm
    public static ArrayList<ArrayList<String>> generate(ArrayList<String> facts, int n, int r) {
        ArrayList<ArrayList<String>> combinations = new ArrayList<>();
        helper(combinations, facts, new ArrayList<>(r), 0, n-1, 0);
        return combinations;
    }
}
