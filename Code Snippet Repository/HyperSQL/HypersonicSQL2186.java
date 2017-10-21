    public static String byteArrayToBitString(byte[] bytes, int bitCount) {

        char[] s = new char[bitCount];

        for (int j = 0; j < bitCount; j++) {
            byte b = bytes[j / 8];

            s[j] = BitMap.isSet(b, j % 8) ? '1'
                                          : '0';
        }

        return new String(s);
    }
