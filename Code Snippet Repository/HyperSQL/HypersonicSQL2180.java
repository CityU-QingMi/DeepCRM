    public static int writeUUIDHexBytes(byte[] o, final int from, byte[] b) {

        if (b.length != 16) {
            throw new NumberFormatException();
        }

        int pos = from;
        int hexIndex;

        for (int i = 0; i < b.length; ) {
            hexIndex = (b[i] & 0xf0) >> 4;
            o[pos++] = HEXBYTES[hexIndex];
            hexIndex = b[i] & 0xf;
            o[pos++] = HEXBYTES[hexIndex];

            i++;

            if (i >= 4 && i <= 10 && (i % 2) == 0) {
                o[pos++] = '-';
            }
        }

        return pos - from;
    }
