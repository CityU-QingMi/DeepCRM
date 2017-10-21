    public static int findNotIn(byte[] arra, int start, int limit,
                                byte[] byteSet) {

        mainloop:
        for (int k = start; k < limit; k++) {
            for (int i = 0; i < byteSet.length; i++) {
                if (arra[k] == byteSet[i]) {
                    continue mainloop;
                }
            }

            return k;
        }

        return -1;
    }
