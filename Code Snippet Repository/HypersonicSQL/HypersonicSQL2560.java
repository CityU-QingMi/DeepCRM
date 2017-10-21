    private Result createFullBlockAddresses(long lobID, int offset,
            int count) {

        ResultMetaData meta = createSingleLobPartCall.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[ALLOC_BLOCKS.BLOCK_COUNT]  = ValuePool.getInt(count);
        params[ALLOC_BLOCKS.BLOCK_OFFSET] = ValuePool.getInt(offset);
        params[ALLOC_BLOCKS.LOB_ID]       = ValuePool.getLong(lobID);

        Result result =
            sysLobSession.executeCompiledStatement(createSingleLobPartCall,
                params, 0);

        return result;
    }
