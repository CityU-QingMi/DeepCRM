    public static long byteSequenceToLong(byte[] bytes, int pos) {

        long val = 0;

        for (int i = 0; i < 8; i++) {
            long b = bytes[pos + i] & 0xff;

            val += (b << ((7 - i) * 8));
        }

        return val;
    }
