    public static char[] byteArrayToChars(byte[] bytes, int bytesLength) {

        char[] chars = new char[bytesLength / 2];

        for (int i = 0, j = 0; j < chars.length; i += 2, j++) {
            chars[j] = (char) ((bytes[i] << 8) + (bytes[i + 1] & 0xff));
        }

        return chars;
    }
