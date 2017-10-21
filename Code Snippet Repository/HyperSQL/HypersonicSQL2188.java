    public static int writeHexBytes(byte[] o, final int from, byte[] b) {

        int len = b.length;
        int pos = from;

        for (int i = 0; i < len; i++) {
            int c = ((int) b[i]) & 0xff;

            o[pos++] = HEXBYTES[c >> 4 & 0xf];
            o[pos++] = HEXBYTES[c & 0xf];
        }

        return pos - from;
    }
