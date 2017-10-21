    private long[][] getParts(long lobID, long offset, long limit) {

        ResultMetaData meta   = getSpanningParts.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[GET_LOB_PART.LOB_ID]       = ValuePool.getLong(lobID);
        params[GET_LOB_PART.BLOCK_OFFSET] = ValuePool.getLong(offset);
        params[GET_LOB_PART.BLOCK_LIMIT]  = ValuePool.getLong(limit);

        sysLobSession.sessionContext.pushDynamicArguments(params);

        Result result = getSpanningParts.execute(sysLobSession);

        sysLobSession.sessionContext.pop();

        RowSetNavigator navigator = result.getNavigator();
        int             size      = navigator.getSize();
        long[][]        blocks    = new long[size][6];

        for (int i = 0; i < size; i++) {
            navigator.absolute(i);

            Object[] data = navigator.getCurrent();

            for (int j = 0; j < blocks[i].length; j++) {
                blocks[i][j] = ((Number) data[j]).longValue();
            }
        }

        navigator.release();

        return blocks;
    }
