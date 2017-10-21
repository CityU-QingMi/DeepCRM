    public static int getTwoPowerFloor(int n) {

        int shift = getTwoPowerScale(n);

        if (shift == 0) {
            return 0;
        }

        return 1 << shift;
    }
