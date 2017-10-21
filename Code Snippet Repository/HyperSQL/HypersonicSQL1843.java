    public static boolean containsAllTrueElements(boolean[] arra,
            boolean[] arrb) {

        for (int i = 0; i < arra.length; i++) {
            if (arrb[i] && !arra[i]) {
                return false;
            }
        }

        return true;
    }
