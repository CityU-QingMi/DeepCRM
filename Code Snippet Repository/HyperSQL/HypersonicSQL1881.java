    public static long cdiv(long a, long b) {

        long c = a / b;

        if (a % b != 0) {
            c++;
        }

        return c;
    }
