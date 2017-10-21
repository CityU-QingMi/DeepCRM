    private Object[] getLobHeader(long lobID) {

        ResultMetaData meta   = getLob.getParametersMetaData();
        Object[]       params = new Object[meta.getColumnCount()];

        params[0] = ValuePool.getLong(lobID);

        sysLobSession.sessionContext.pushDynamicArguments(params);

        Result result = getLob.execute(sysLobSession);

        sysLobSession.sessionContext.pop();

        if (result.isError()) {
            throw result.getException();
        }

        RowSetNavigator navigator = result.getNavigator();
        boolean         next      = navigator.next();
        Object[]        data      = null;

        if (next) {
            data = navigator.getCurrent();
        }

        navigator.release();

        return data;
    }
