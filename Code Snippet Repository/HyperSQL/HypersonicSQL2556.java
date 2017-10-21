    private void initialiseLobSpace() {

        Statement statement = sysLobSession.compileStatement(existsBlocksSQL);
        Result    result    = statement.execute(sysLobSession);

        if (result.isError()) {
            throw result.getException();
        }

        RowSetNavigator navigator = result.getNavigator();
        int             size      = navigator.getSize();

        if (size > 0) {
            return;
        }

        statement = sysLobSession.compileStatement(initialiseBlocksSQL);

        Object[] params = new Object[3];

        params[0] = ValuePool.INTEGER_0;
        params[1] = ValuePool.getInt(totalBlockLimitCount);
        params[2] = ValuePool.getLong(0);

        sysLobSession.executeCompiledStatement(statement, params, 0);
    }
