    public static String byteArrayToSQLBitString(byte[] bytes, int bitCount) {

        char[] s = new char[bitCount + 3];

        s[0] = 'B';
        s[1] = '\'';

        int pos = 2;

        for (int j = 0; j < bitCount; j++) {
            byte b = bytes[j / 8];

            s[pos++] = BitMap.isSet(b, j % 8) ? '1'
                                              : '0';
        }

        s[pos] = '\'';

        return new String(s);
    }
