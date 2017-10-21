    public Result deleteLob(long lobID) {

        writeLock.lock();

        try {
            ResultMetaData meta   = deleteLobCall.getParametersMetaData();
            Object[]       params = new Object[meta.getColumnCount()];

            params[0] = ValuePool.getLong(lobID);
            params[1] = ValuePool.getLong(0);

            Result result =
                sysLobSession.executeCompiledStatement(deleteLobCall, params,
                    0);

            usageChanged = true;

            return result;
        } finally {
            writeLock.unlock();
        }
    }
