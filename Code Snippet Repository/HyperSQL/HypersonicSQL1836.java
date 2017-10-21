    public static int findIn(byte[] arra, int start, int limit,
                             byte[] byteSet) {

        for (int k = start; k < limit; k++) {
            for (int i = 0; i < byteSet.length; i++) {
                if (arra[k] == byteSet[i]) {
                    return k;
                }
            }
        }

        return -1;
    }
