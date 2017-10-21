    private int compareBytesCompressed(long aID, long bID) {

        long[][] aParts = getParts(aID, 0, Long.MAX_VALUE);
        long[][] bParts = getParts(bID, 0, Long.MAX_VALUE);

        for (int i = 0; i < aParts.length && i < bParts.length; i++) {
            int aPartLength = (int) aParts[i][ALLOC_PART.PART_LENGTH];

            getPartBytesCompressedInBuffer(aID, aParts[i], false);

            byte[] aPartBytes = new byte[aPartLength];

            System.arraycopy(dataBuffer, 0, aPartBytes, 0, aPartLength);

            int bPartLength = (int) bParts[i][ALLOC_PART.PART_LENGTH];

            getPartBytesCompressedInBuffer(aID, bParts[i], false);

            int result = ArrayUtil.compare(aPartBytes, 0, aPartLength,
                                           byteBuffer, bPartLength);

            if (result != 0) {
                return result;
            }
        }

        if (aParts.length == bParts.length) {
            return 0;
        }

        return aParts.length > bParts.length ? 1
                                             : -1;
    }
