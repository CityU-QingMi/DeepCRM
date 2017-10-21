    public JDBCResultSet(JDBCConnection conn, Result r,
                         ResultMetaData metaData) {

        this.session    = conn == null ? null
                                       : conn.sessionProxy;
        this.result     = r;
        this.connection = conn;
        rsProperties    = r.rsProperties;
        navigator       = r.getNavigator();
        resultMetaData  = metaData;
        columnCount     = resultMetaData.getColumnCount();

        if (conn != null) {
            translateTTIType = conn.isTranslateTTIType;
        }
    }
