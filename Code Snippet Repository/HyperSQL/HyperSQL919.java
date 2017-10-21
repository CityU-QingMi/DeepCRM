    public static boolean haveEqualArrays(Object[] arra, Object[] arrb,
                                          int count) {

        if (count > arra.length || count > arrb.length) {
            return false;
        }

        for (int j = 0; j < count; j++) {
            if (arra[j] != arrb[j]) {
                if (arra[j] == null || !arra[j].equals(arrb[j])) {
                    return false;
                }
            }
        }

        return true;
    }
