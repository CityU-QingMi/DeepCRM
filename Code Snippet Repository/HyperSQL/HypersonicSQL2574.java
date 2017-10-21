    private Result getPartBytesCompressedInBuffer(long lobID, long[] part,
            boolean isClob) {

        long blockOffset     = part[ALLOC_PART.BLOCK_OFFSET];
        long partOffset      = part[ALLOC_PART.PART_OFFSET];
        long partLength      = part[ALLOC_PART.PART_LENGTH];
        int  partBytesLength = (int) part[ALLOC_PART.PART_BYTES];
        long blockByteOffset = blockOffset * lobBlockSize;
        Result result = getBytesNormal(lobID, blockByteOffset,
                                       partBytesLength);

        if (result.isError()) {
            return result;
        }

        byte[] byteBlock = ((ResultLob) result).getByteArray();

        inflate(byteBlock, partBytesLength, isClob);

        return ResultLob.newLobSetResponse(lobID, partLength);
    }
