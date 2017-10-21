    public static int cdiv(int a, int b) {

        int c = a / b;

        if (a % b != 0) {
            c++;
        }

        return c;
    }
