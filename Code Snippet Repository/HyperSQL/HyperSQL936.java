    public static boolean areAllIntIndexesAsBooleanArray(int[] arra,
            boolean[] arrb) {

        for (int i = 0; i < arra.length; i++) {
            if (arrb[arra[i]]) {
                continue;
            }

            return false;
        }

        return arra.length == countTrueElements(arrb);
    }
