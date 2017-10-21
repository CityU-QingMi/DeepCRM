    public static int compare(byte[] a, int aOffset, int aLength, byte[] b,
                              int bLength) {

        int length = aLength;

        if (length > bLength) {
            length = bLength;
        }

        for (int i = 0; i < length; i++) {
            if (a[aOffset + i] == b[i]) {
                continue;
            }

            return (((int) a[aOffset + i]) & 0xff) > (((int) b[i]) & 0xff) ? 1
                                                                           : -1;
        }

        if (aLength == bLength) {
            return 0;
        }

        return aLength < bLength ? -1
                                 : 1;
    }
