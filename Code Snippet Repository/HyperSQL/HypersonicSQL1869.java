    public static boolean containsAllAtStart(int[] arra, int[] arrb) {

        if (arrb.length > arra.length) {
            return false;
        }

        mainLoop:
        for (int i = 0; i < arra.length; i++) {
            if (i == arrb.length) {
                return true;
            }

            for (int j = 0; j < arrb.length; j++) {
                if (arra[i] == arrb[j]) {
                    continue mainLoop;
                }
            }

            return false;
        }

        return true;
    }
