    public static int[] booleanArrayToIntIndexes(boolean[] arrb) {

        int count = 0;

        for (int i = 0; i < arrb.length; i++) {
            if (arrb[i]) {
                count++;
            }
        }

        int[] intarr = new int[count];

        count = 0;

        for (int i = 0; i < arrb.length; i++) {
            if (arrb[i]) {
                intarr[count++] = i;
            }
        }

        return intarr;
    }
