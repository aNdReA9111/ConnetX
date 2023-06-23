package connectx.AFLP;



public class test {
    private static long exponentiationBySquaring(long base, int exponent) {
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result *= base;
            }
            base *= base;
            exponent /= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();

        System.out.println(exponentiationBySquaring(3,500));


        long endtime = System.currentTimeMillis();
        System.out.println(endtime - starttime);

        starttime = System.currentTimeMillis();
        System.out.println((int) Math.pow(3, 500));
        endtime = System.currentTimeMillis();
        System.out.println(endtime - starttime);
    }
}
