    public static int find(byte[] arra, int start, int limit, int b, int c) {

        int k = 0;

        for (; k < limit; k++) {
            if (arra[k] == b || arra[k] == c) {
                return k;
            }
        }

        return -1;
    }
