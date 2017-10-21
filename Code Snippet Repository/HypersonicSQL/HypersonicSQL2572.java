    private Result setBytesBACompressedPart(long lobID, long offset,
            byte[] dataBytes, int dataLength, boolean isClob) {

        // get block offset after existing blocks and compressed block
        long[] lastPart = getLastPart(lobID);
        int blockOffset = (int) lastPart[ALLOC_PART.BLOCK_OFFSET]
                          + (int) lastPart[ALLOC_PART.BLOCK_COUNT];

        // check position
        long limit = lastPart[ALLOC_PART.PART_OFFSET]
                     + lastPart[ALLOC_PART.PART_LENGTH];

        if (limit != offset || limit % largeLobBlockSize != 0) {
            return Result.newErrorResult(Error.error(ErrorCode.X_0A501,
                    "compressed lobs"));
        }

        int byteLength = deflate(dataBytes, 0, dataLength, isClob);
        int blockCount = (byteLength + lobBlockSize - 1) / lobBlockSize;
        Result result = createFullBlockAddresses(lobID, blockOffset,
            blockCount);

        if (result.isError()) {
            return result;
        }

        result = createPart(lobID, offset, dataLength, byteLength,
                            blockOffset, blockCount);

        if (result.isError()) {
            return result;
        }

        long blockByteOffset = blockOffset * (long) lobBlockSize;
        int blockByteLength =
            (int) ArrayUtil.getBinaryMultipleCeiling(byteLength, lobBlockSize);

        setBytesBANormal(lobID, blockByteOffset, dataBuffer, blockByteLength);

        storeModified = true;

        return ResultLob.newLobSetResponse(lobID, dataLength);
    }
