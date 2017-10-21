    public static byte[] or(byte[] a, byte[] b) {

        int    length      = a.length > b.length ? a.length
                                                 : b.length;
        int    shortLength = a.length > b.length ? b.length
                                                 : a.length;
        byte[] map         = new byte[length];

        if (length != shortLength) {
            byte[] source = a.length > b.length ? a
                                                : b;

            System.arraycopy(source, shortLength, map, shortLength,
                             length - shortLength);
        }

        for (int i = 0; i < shortLength; i++) {
            map[i] = (byte) (a[i] | b[i]);
        }

        return map;
    }
