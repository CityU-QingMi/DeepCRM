    private Result getBytesCompressed(long lobID, long offset, int length,
                                      boolean isClob) {

        byte[]   dataBytes = new byte[length];
        long[][] parts     = getParts(lobID, offset, offset + length);

        for (int i = 0; i < parts.length; i++) {
            long[] part       = parts[i];
            long   partOffset = part[ALLOC_PART.PART_OFFSET];
            int    partLength = (int) part[ALLOC_PART.PART_LENGTH];
            Result result = getPartBytesCompressedInBuffer(lobID, part,
                isClob);

            if (result.isError()) {
                return result;
            }

            ArrayUtil.copyBytes(partOffset, dataBuffer, 0, partLength, offset,
                                dataBytes, length);
        }

        return ResultLob.newLobGetBytesResponse(lobID, offset, dataBytes);
    }
