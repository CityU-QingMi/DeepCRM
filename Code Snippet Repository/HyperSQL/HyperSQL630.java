    public JDBCConnection(JDBCConnection c,
                          JDBCConnectionEventListener eventListener) {

        sessionProxy      = c.sessionProxy;
        connProperties    = c.connProperties;
        clientProperties  = c.clientProperties;
        isPooled          = true;
        poolEventListener = eventListener;

        setLocalVariables();
    }
