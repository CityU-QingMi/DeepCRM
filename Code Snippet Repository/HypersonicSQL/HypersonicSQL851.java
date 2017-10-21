    public synchronized Object getAttribute(int id) {

        switch (id) {

            case SessionInterface.INFO_ISOLATION :
                return ValuePool.getInt(isolationLevel);

            case SessionInterface.INFO_AUTOCOMMIT :
                return sessionContext.isAutoCommit;

            case SessionInterface.INFO_CONNECTION_READONLY :
                return isReadOnlyDefault ? Boolean.TRUE
                                         : Boolean.FALSE;

            case SessionInterface.INFO_CATALOG :
                return database.getCatalogName().name;
        }

        return null;
    }
