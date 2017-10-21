    private int encode0(final char[] charArray, int charOffset, int charLength, final byte[] byteArray) {
        int offset = 0;
        int length = Math.min(charLength, byteArray.length);
        int charDoneIndex = charOffset + length;
        while (charOffset < charDoneIndex) {
            final int m = encodeISOArray0(charArray, charOffset, byteArray, offset, length);
            charOffset += m;
            offset += m;
            if (m != length) {
                final char c = charArray[(charOffset++)];
                if ((Character.isHighSurrogate(c)) && (charOffset < charDoneIndex)
                        && (Character.isLowSurrogate(charArray[(charOffset)]))) {
                    if (charLength > byteArray.length) {
                        charDoneIndex++;
                        charLength--;
                    }
                    charOffset++;
                }
                byteArray[(offset++)] = '?';
                length = Math.min(charDoneIndex - charOffset, byteArray.length - offset);
            }
        }
        return offset;
    }
