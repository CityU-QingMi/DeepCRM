    private void deleteBlockAddresses(long lobID, int offset, int limit) {

        ResultMetaData meta   = deleteLobPartCall.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[DELETE_BLOCKS.LOB_ID]       = ValuePool.getLong(lobID);
        params[DELETE_BLOCKS.BLOCK_OFFSET] = ValuePool.getInt(offset);
        params[DELETE_BLOCKS.BLOCK_LIMIT]  = ValuePool.getInt(limit);
        params[DELETE_BLOCKS.TX_ID] =
            ValuePool.getLong(sysLobSession.getTransactionTimestamp());

        Result result =
            sysLobSession.executeCompiledStatement(deleteLobPartCall, params,
                0);
    }
