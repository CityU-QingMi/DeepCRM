    public static boolean haveCommonElement(int[] arra, int[] arrb) {

        if (arra == null || arrb == null) {
            return false;
        }

        for (int i = 0; i < arra.length; i++) {
            int c = arra[i];

            for (int j = 0; j < arrb.length; j++) {
                if (c == arrb[j]) {
                    return true;
                }
            }
        }

        return false;
    }
