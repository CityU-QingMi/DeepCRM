    public static String toStringUUID(byte[] b) {

        if (b == null) {
            return null;
        }

        if (b.length != 16) {
            throw new NumberFormatException();
        }

        char[] chars = new char[36];
        int    hexIndex;

        for (int i = 0, j = 0; i < b.length; ) {
            hexIndex   = (b[i] & 0xf0) >> 4;
            chars[j++] = (char) HEXBYTES[hexIndex];
            hexIndex   = b[i] & 0xf;
            chars[j++] = (char) HEXBYTES[hexIndex];

            i++;

            if (i >= 4 && i <= 10 && (i % 2) == 0) {
                chars[j++] = '-';
            }
        }

        return new String(chars);
    }
