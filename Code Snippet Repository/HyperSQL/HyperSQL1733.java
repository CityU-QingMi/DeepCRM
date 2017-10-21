    private void divideBlockAddresses(long lobID, int offset) {

        ResultMetaData meta   = divideLobPartCall.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[DIVIDE_BLOCK.BLOCK_OFFSET] = ValuePool.getInt(offset);
        params[DIVIDE_BLOCK.LOB_ID]       = ValuePool.getLong(lobID);

        Result result =
            sysLobSession.executeCompiledStatement(divideLobPartCall, params,
                0);
    }
