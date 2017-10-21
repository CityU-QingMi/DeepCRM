    public Result adjustUsageCount(Session session, long lobID, int delta) {

        ResultMetaData meta   = updateLobUsage.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[UPDATE_USAGE.BLOCK_COUNT] = ValuePool.getInt(delta);
        params[UPDATE_USAGE.LOB_ID]      = ValuePool.getLong(lobID);

        session.sessionContext.pushDynamicArguments(params);

        Result result = updateLobUsage.execute(session);

        usageChanged = true;

        session.sessionContext.pop();

        return result;
    }
