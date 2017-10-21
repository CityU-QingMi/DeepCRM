    public static String byteArrayToSQLHexString(byte[] b) {

        int    len = b.length;
        char[] s   = new char[len * 2 + 3];

        s[0] = 'X';
        s[1] = '\'';

        int j = 2;

        for (int i = 0; i < len; i++) {
            int c = ((int) b[i]) & 0xff;

            s[j++] = (char) HEXBYTES[c >> 4 & 0xf];
            s[j++] = (char) HEXBYTES[c & 0xf];
        }

        s[j] = '\'';

        return new String(s);
    }
