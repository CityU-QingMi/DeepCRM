    private Result setLength(long lobID, long length) {

        ResultMetaData meta   = updateLobLength.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[UPDATE_LENGTH.LOB_LENGTH] = ValuePool.getLong(length);
        params[UPDATE_LENGTH.LOB_ID]     = ValuePool.getLong(lobID);

        Result result = sysLobSession.executeCompiledStatement(updateLobLength,
            params, 0);

        return result;
    }
