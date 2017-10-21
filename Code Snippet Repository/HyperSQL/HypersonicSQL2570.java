    private int compareTextCompressed(Collation collation, long aID,
                                      long bID) {

        long[][] aParts = getParts(aID, 0, Long.MAX_VALUE);
        long[][] bParts = getParts(bID, 0, Long.MAX_VALUE);

        for (int i = 0; i < aParts.length && i < bParts.length; i++) {
            int aPartLength = (int) aParts[i][ALLOC_PART.PART_LENGTH];

            getPartBytesCompressedInBuffer(aID, aParts[i], true);

            String aString = new String(ArrayUtil.byteArrayToChars(dataBuffer,
                aPartLength));
            int bPartLength = (int) bParts[i][ALLOC_PART.PART_LENGTH];

            getPartBytesCompressedInBuffer(bID, bParts[i], true);

            String bString = new String(ArrayUtil.byteArrayToChars(dataBuffer,
                bPartLength));
            int diff = collation.compare(aString, bString);

            if (diff != 0) {
                return diff;
            }
        }

        if (aParts.length == bParts.length) {
            return 0;
        }

        return aParts.length > bParts.length ? 1
                                             : -1;
    }
