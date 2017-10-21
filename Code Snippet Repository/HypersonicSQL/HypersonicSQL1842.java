    public static boolean isAnyIntIndexInBooleanArray(int[] arra,
            boolean[] arrb) {

        for (int i = 0; i < arra.length; i++) {
            if (arrb[arra[i]]) {
                return true;
            }
        }

        return false;
    }
