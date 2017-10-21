    public static int[] union(int[] arra, int[] arrb) {

        int newSize = arra.length + arrb.length
                      - ArrayUtil.countCommonElements(arra, arrb);

        if (newSize > arra.length && newSize > arrb.length) {
            int[] arrn = (int[]) ArrayUtil.resizeArray(arrb, newSize);
            int   pos  = arrb.length;

            mainloop:
            for (int i = 0; i < arra.length; i++) {
                for (int j = 0; j < arrb.length; j++) {
                    if (arra[i] == arrb[j]) {
                        continue mainloop;
                    }
                }

                arrn[pos++] = arra[i];
            }

            return arrn;
        }

        return arra.length > arrb.length ? arra
                                         : arrb;
    }
