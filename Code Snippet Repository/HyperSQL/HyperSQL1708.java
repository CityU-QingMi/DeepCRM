    public long createClob(Session session, long length) {

        writeLock.lock();

        try {
            Long           lobID  = getNewLobID();
            ResultMetaData meta   = createLob.getParametersMetaData();
            Object[]       params = new Object[meta.getColumnCount()];

            params[LOB_IDS.LOB_ID]          = lobID;
            params[LOB_IDS.LOB_LENGTH]      = ValuePool.getLong(length);
            params[LOB_IDS.LOB_USAGE_COUNT] = ValuePool.INTEGER_0;
            params[LOB_IDS.LOB_TYPE]        = ValuePool.getInt(Types.SQL_CLOB);

            Result result = sysLobSession.executeCompiledStatement(createLob,
                params, 0);

            usageChanged = true;

            return lobID.longValue();
        } finally {
            writeLock.unlock();
        }
    }
