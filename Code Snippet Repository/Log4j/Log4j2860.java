    private static int encodeISOArray(final String charArray, int charIndex, final byte[] byteArray, int byteIndex, final int length) {
        int i = 0;
        for (; i < length; i++) {
            final char c = charArray.charAt(charIndex++);
            if (c > 255) {
                break;
            }
            byteArray[(byteIndex++)] = ((byte) c);
        }
        return i;
    }
