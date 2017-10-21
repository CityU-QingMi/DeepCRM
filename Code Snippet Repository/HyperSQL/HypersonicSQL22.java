    protected ClientConnection(ClientConnection other) {

        this.host         = other.host;
        this.port         = other.port;
        this.path         = other.path;
        this.database     = other.database;
        this.isTLS        = other.isTLS;
        this.isTLSWrapper = other.isTLSWrapper;
        this.zoneSeconds  = other.zoneSeconds;
        this.zoneString   = other.zoneString;

        //
        this.sessionID              = other.sessionID;
        this.databaseID             = other.databaseID;
        this.databaseUniqueName     = other.databaseUniqueName;
        this.clientPropertiesString = other.clientPropertiesString;
        this.randomID               = other.randomID;

        initStructures();
        initConnection(host, port, isTLS);
    }
