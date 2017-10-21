    public static boolean isTwoPower(int n, int max) {

        for (int i = 0; i <= max; i++) {
            if ((n & 1) != 0) {
                return n == 1;
            }

            n >>= 1;
        }

        return false;
    }
