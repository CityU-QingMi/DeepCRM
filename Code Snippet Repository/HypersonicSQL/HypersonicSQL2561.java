    private Result createPart(long lobID, long partOffset, int dataLength,
                              int byteLength, int blockOffset,
                              int blockCount) {

        ResultMetaData meta   = createPart.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[ALLOC_PART.BLOCK_COUNT]  = ValuePool.getInt(blockCount);
        params[ALLOC_PART.BLOCK_OFFSET] = ValuePool.getInt(blockOffset);
        params[ALLOC_PART.PART_OFFSET]  = ValuePool.getLong(partOffset);
        params[ALLOC_PART.PART_LENGTH]  = ValuePool.getLong(dataLength);
        params[ALLOC_PART.PART_BYTES]   = ValuePool.getLong(byteLength);
        params[ALLOC_PART.LOB_ID]       = ValuePool.getLong(lobID);

        Result result = sysLobSession.executeCompiledStatement(createPart,
            params, 0);

        return result;
    }
