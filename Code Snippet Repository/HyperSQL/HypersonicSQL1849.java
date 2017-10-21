    public static int countNonStartElementsAt(byte[] arra, int start,
            byte[] arrb) {

        int k = 0;

        mainloop:
        for (int i = start; i < arra.length; i++) {
            for (int j = 0; j < arrb.length; j++) {
                if (arra[i] == arrb[j]) {
                    break mainloop;
                }
            }

            k++;
        }

        return k;
    }
