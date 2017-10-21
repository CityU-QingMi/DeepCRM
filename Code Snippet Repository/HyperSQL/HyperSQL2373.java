    protected void init(int protocol) {

        // PRE:  This method is only called from the constructor
        serverState      = ServerConstants.SERVER_STATE_SHUTDOWN;
        serverConnSet    = new HashSet();
        serverId         = toString();
        serverId         = serverId.substring(serverId.lastIndexOf('.') + 1);
        serverProtocol   = protocol;
        serverProperties = ServerConfiguration.newDefaultProperties(protocol);
        logWriter        = new PrintWriter(System.out);
        errWriter        = new PrintWriter(System.err);

        JavaSystem.setLogToSystem(isTrace());
    }
