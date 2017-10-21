    public static int find(byte[] arra, int start, int limit, byte[] arrb) {

        int k = start;

        limit = limit - arrb.length + 1;

        int value = arrb[0];

        for (; k < limit; k++) {
            if (arra[k] == value) {
                if (arrb.length == 1) {
                    return k;
                }

                if (containsAt(arra, k, arrb)) {
                    return k;
                }
            }
        }

        return -1;
    }
