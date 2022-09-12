import java.util.Scanner;
import java.util.Arrays;

public class Solution {
    public static DisjointSet disjointSet;
    public static int mouse, cat, food;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, no_removals, row, column, size;
        String DIR;
        n = scanner.nextInt();
        no_removals = scanner.nextInt();
        size = n * n;

        mouse = 1;
        cat = n;
        food = size;

        disjointSet = new DisjointSet(n * n);
        for (int i = 0; i <= size; i++) {
            disjointSet.makeSet(i);
        }

        for(int i = 0; i < no_removals; i++) {
            row = scanner.nextInt();
            column = scanner.nextInt();
            DIR = scanner.next();

            int x = (row * n) + column + 1;
            int y = 0;

            switch (DIR) {
                case "N":
                    y = x - n;
                    break;
                case "E":
                    y = x + 1;
                    break;
                case "W":
                    y = x - 1;
                    break;
                case "S":
                    y = x + n;
                    break;
            }
            MakeDecision(x, y);
        }
    }

    public static void MakeDecision(int x, int y) {
        int[] setsCopy = disjointSet.getSets();
        int x_root = disjointSet.find(x);
        int y_root = disjointSet.find(y);

        if ((disjointSet.find(mouse) == x_root && disjointSet.find(cat) == y_root) ||
                (disjointSet.find(mouse) == y_root && disjointSet.find(cat) == x_root)) {
            //Cat can reach mouse
            System.out.println("C");
        } else {
            if (setsCopy[x] == y || setsCopy[y] == x || (setsCopy[x] == setsCopy[y] && setsCopy[x] != -1 && setsCopy[y] != -1)
                    && (disjointSet.find(mouse) == x_root || disjointSet.find(mouse) == y_root)) {
                //Creates a loop
                System.out.println("X");
            } else {
                disjointSet.union(x_root, y_root);
                int[] setsCopy2 = disjointSet.getSets();
                if (disjointSet.find(mouse) == disjointSet.find(food))
                    System.out.println("D"); //Maze can be finished
                else //Union allowed, maze not finished
                    System.out.println("K");
            }
        }
    }
}
class DisjointSet {
    int[] sets;

    public DisjointSet(int n) {//n is size of universal set
        sets = new int[n + 1];
        Arrays.fill(sets, 0);
    }

    public void checkIndex(int x) {
        if (x < 0 || x > sets.length) throw new IndexOutOfBoundsException();
    }

    public int[] getSets() {
        return sets;
    }

    public void setSets(int[] setsGiven) {
        sets = setsGiven;
    }

    public void makeSet(int x) {
        checkIndex(x);
        if (sets[x] != 0)
            throw new IllegalArgumentException("Item " + x + " is already in a set");
        sets[x] = -1;
    }

    //returns root of x
    public int find(int x) {
        checkIndex(x);
        if (sets[x] == 0)
            throw new IllegalArgumentException("Item " + x + " is not in a set");

        // Implement function recursively
        if (sets[x] < 0) return x;
        int root = find(sets[x]);
        sets[x] = root; //Path compression heuristic
        return root;
    }

    public void union(int x, int y) {
        checkIndex(x);
        checkIndex(y);

        if (sets[x] >= 0 || sets[y] >= 0) // ||x==y
            throw new IllegalArgumentException("union must take the roots of two" +
                    "distinct trees as arguments.");
        if (sets[x] > sets[y]) {
            //y is bigger set
            sets[y] += sets[x];
            sets[x] = y;//root of set x and making it a child of root of y
        } else {
            sets[x] += sets[y];
            sets[y] = x;
        }
    }
} 