    public static int countStartElementsAt(byte[] arra, int start,
                                           byte[] arrb) {

        int k = 0;

        mainloop:
        for (int i = start; i < arra.length; i++) {
            for (int j = 0; j < arrb.length; j++) {
                if (arra[i] == arrb[j]) {
                    k++;

                    continue mainloop;
                }
            }

            break;
        }

        return k;
    }
