    public static boolean containsAll(int[] arra, int[] arrb) {

        mainLoop:
        for (int i = 0; i < arrb.length; i++) {
            for (int j = 0; j < arra.length; j++) {
                if (arrb[i] == arra[j]) {
                    continue mainLoop;
                }
            }

            return false;
        }

        return true;
    }
