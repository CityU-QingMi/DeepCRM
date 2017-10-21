    public static int countCommonElements(Object[] arra, int alen,
                                          Object[] arrb) {

        int k = 0;

        for (int i = 0; i < alen; i++) {
            for (int j = 0; j < arrb.length; j++) {
                if (arra[i] == arrb[j]) {
                    k++;

                    break;
                }
            }
        }

        return k;
    }
