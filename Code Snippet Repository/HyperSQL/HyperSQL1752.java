    private long[] getLastPart(long lobID) {

        ResultMetaData meta   = getLastPart.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[GET_LOB_PART.LOB_ID] = ValuePool.getLong(lobID);

        sysLobSession.sessionContext.pushDynamicArguments(params);

        Result result = getLastPart.execute(sysLobSession);

        sysLobSession.sessionContext.pop();

        RowSetNavigator navigator = result.getNavigator();
        int             size      = navigator.getSize();
        long[]          blocks    = new long[6];

        if (size == 0) {
            blocks[ALLOC_PART.LOB_ID] = lobID;
        } else {
            navigator.absolute(0);

            Object[] data = navigator.getCurrent();

            for (int j = 0; j < blocks.length; j++) {
                blocks[j] = ((Number) data[j]).longValue();
            }
        }

        navigator.release();

        return blocks;
    }
