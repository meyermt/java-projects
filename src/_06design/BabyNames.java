package _06design;

public class BabyNames {

    public static final int LIMIT = 50;

    public static void main(String[] args) throws java.io.FileNotFoundException {
        java.util.Scanner in = new java.util.Scanner(new java.io.File("babynames.txt"));
        double boyTotal = 0;
        double girlTotal = 0;
        while (boyTotal < LIMIT || girlTotal < LIMIT) {
            int rank = in.nextInt();
            System.out.print(rank + " ");
            boyTotal = processName(in, boyTotal);
            girlTotal = processName(in, girlTotal);
            System.out.println();
        }
        in.close();
    }

    public static double processName(java.util.Scanner in, double total) {
        String name = in.next();
        int count = in.nextInt();
        double percent = in.nextDouble();
        if (total < LIMIT) {
            System.out.print(name + " ");
        }
        total = total + percent;
        return total;
    }
}