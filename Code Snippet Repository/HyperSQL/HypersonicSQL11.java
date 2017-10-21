    public ClientConnection(String host, int port, String path,
                            String database, boolean isTLS,
                            boolean isTLSWrapper, String user,
                            String password, int timeZoneSeconds) {

        this.host         = host;
        this.port         = port;
        this.path         = path;
        this.database     = database;
        this.isTLS        = isTLS;
        this.isTLSWrapper = isTLSWrapper;
        this.zoneSeconds  = timeZoneSeconds;
        this.zoneString   = TimeZone.getDefault().getID();

        initStructures();
        initConnection(host, port, isTLS);

        Result login = Result.newConnectionAttemptRequest(user, password,
            database, zoneString, timeZoneSeconds);
        Result resultIn = execute(login);

        if (resultIn.isError()) {
            throw Error.error(resultIn);
        }

        sessionID              = resultIn.getSessionId();
        databaseID             = resultIn.getDatabaseId();
        databaseUniqueName     = resultIn.getDatabaseName();
        clientPropertiesString = resultIn.getMainString();
        randomID               = resultIn.getSessionRandomID();
    }
