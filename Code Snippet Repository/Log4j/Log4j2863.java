    private static int encodeISOArray0(final char[] charArray, int charIndex, final byte[] byteArray, int byteIndex, final int length) {
        int i = 0;
        for (; i < length; i++) {
            final char c = charArray[(charIndex++)];
            if (c > 255) {
                break;
            }
            byteArray[(byteIndex++)] = ((byte) c);
        }
        return i;
    }
