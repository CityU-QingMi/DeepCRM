    public Result truncate(long lobID, long offset) {

        if (byteBuffer == null) {
            throw Error.error(ErrorCode.DATA_IS_READONLY);
        }

        writeLock.lock();

        try {
            Object[] data = getLobHeader(lobID);

            if (data == null) {
                return Result.newErrorResult(Error.error(ErrorCode.X_0F502));
            }

            long length     = ((Long) data[LOB_IDS.LOB_LENGTH]).longValue();
            long byteLength = offset;

            if (((Integer) data[LOB_IDS.LOB_TYPE]).intValue()
                    == Types.SQL_CLOB) {
                byteLength *= 2;
            }

            int blockOffset = (int) ((byteLength + lobBlockSize - 1)
                                     / lobBlockSize);
            ResultMetaData meta   = deleteLobPartCall.getParametersMetaData();
            Object[]       params = new Object[meta.getColumnCount()];

            params[DELETE_BLOCKS.LOB_ID]       = ValuePool.getLong(lobID);
            params[DELETE_BLOCKS.BLOCK_OFFSET] = Integer.valueOf(blockOffset);
            params[DELETE_BLOCKS.BLOCK_LIMIT]  = ValuePool.INTEGER_MAX;
            params[DELETE_BLOCKS.TX_ID] =
                ValuePool.getLong(sysLobSession.getTransactionTimestamp());

            Result result =
                sysLobSession.executeCompiledStatement(deleteLobPartCall,
                    params, 0);

            setLength(lobID, offset);

            return ResultLob.newLobTruncateResponse(lobID, offset);
        } finally {
            writeLock.unlock();
        }
    }
