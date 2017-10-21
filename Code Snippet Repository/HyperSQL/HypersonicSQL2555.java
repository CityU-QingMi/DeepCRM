    private int[][] getBlockAddresses(long lobID, int offset, int limit) {

        ResultMetaData meta   = getSpanningBlocks.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[GET_LOB_PART.LOB_ID]       = ValuePool.getLong(lobID);
        params[GET_LOB_PART.BLOCK_OFFSET] = ValuePool.getInt(offset);
        params[GET_LOB_PART.BLOCK_LIMIT]  = ValuePool.getInt(limit);

        sysLobSession.sessionContext.pushDynamicArguments(params);

        Result result = getSpanningBlocks.execute(sysLobSession);

        sysLobSession.sessionContext.pop();

        RowSetNavigator navigator = result.getNavigator();
        int             size      = navigator.getSize();
        int[][]         blocks    = new int[size][3];

        for (int i = 0; i < size; i++) {
            navigator.absolute(i);

            Object[] data = navigator.getCurrent();

            blocks[i][LOBS.BLOCK_ADDR] =
                ((Integer) data[LOBS.BLOCK_ADDR]).intValue();
            blocks[i][LOBS.BLOCK_COUNT] =
                ((Integer) data[LOBS.BLOCK_COUNT]).intValue();
            blocks[i][LOBS.BLOCK_OFFSET] =
                ((Integer) data[LOBS.BLOCK_OFFSET]).intValue();
        }

        navigator.release();

        return blocks;
    }
