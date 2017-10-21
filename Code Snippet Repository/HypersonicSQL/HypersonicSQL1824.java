    public static boolean haveEqualArrays(int[] arra, int[] arrb, int count) {

        if (count > arra.length || count > arrb.length) {
            return false;
        }

        for (int j = 0; j < count; j++) {
            if (arra[j] != arrb[j]) {
                return false;
            }
        }

        return true;
    }
