    public static int countStartIntIndexesInBooleanArray(int[] arra,
            boolean[] arrb) {

        int k = 0;

        for (int i = 0; i < arra.length; i++) {
            if (arrb[arra[i]]) {
                k++;
            } else {
                break;
            }
        }

        return k;
    }
