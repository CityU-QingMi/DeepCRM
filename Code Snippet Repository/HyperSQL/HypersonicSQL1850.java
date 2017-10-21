    public static int copyBytes(long sourceOffset, byte[] source,
                                int sourceOff, int sourceLength,
                                long destOffset, byte[] dest, int destLength) {

        if (sourceOff >= source.length) {
            return 0;
        }

        if (sourceOff + sourceLength > source.length) {
            sourceLength = source.length - sourceOff;
        }

        if (destLength > dest.length) {
            destLength = dest.length;
        }

        if (sourceOffset + sourceOff >= destOffset + destLength
                || sourceOffset + sourceOff + sourceLength <= destOffset) {
            return 0;
        }

        long sourceIndex = destOffset - sourceOffset;
        long destIndex   = 0;
        int  sourceLimit = sourceOff + sourceLength;

        if (sourceIndex >= 0) {
            if (sourceIndex < sourceOff) {
                sourceIndex = sourceOff;
            }
        } else {
            destIndex   = -sourceIndex + sourceOff;
            sourceIndex = sourceOff;
        }

        sourceLength = sourceLimit - (int) sourceIndex;

        if (sourceLength > destLength - destIndex) {
            sourceLength = destLength - (int) destIndex;
        }

        System.arraycopy(source, (int) sourceIndex, dest, (int) destIndex,
                         sourceLength);

        return sourceLength;
    }
