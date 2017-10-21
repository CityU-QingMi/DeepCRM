    public static int countSameElements(byte[] arra, int start, byte[] arrb) {

        int k     = 0;
        int limit = arra.length - start;

        if (limit > arrb.length) {
            limit = arrb.length;
        }

        for (int i = 0; i < limit; i++) {
            if (arra[i + start] == arrb[i]) {
                k++;
            } else {
                break;
            }
        }

        return k;
    }
