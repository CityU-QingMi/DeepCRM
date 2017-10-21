    public static byte[] charArrayToBytes(char[] chars, int length) {

        byte[] bytes = new byte[length * 2];

        for (int i = 0, j = 0; j < length; i += 2, j++) {
            int c = chars[j];

            bytes[i]     = (byte) (c >> 8);
            bytes[i + 1] = (byte) c;
        }

        return bytes;
    }
