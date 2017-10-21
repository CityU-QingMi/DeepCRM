    public static int getTwoPowerScale(int n) {

        int shift = 0;

        if (n == 0) {
            return 0;
        }

        for (int i = 0; i < 32; i++) {
            if ((n & 1) != 0) {
                shift = i;
            }

            n >>= 1;
        }

        return shift;
    }
