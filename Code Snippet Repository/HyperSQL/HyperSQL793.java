    void init(ResultMetaData meta, JDBCConnection conn) throws SQLException {

        resultMetaData = meta;
        columnCount    = resultMetaData.getColumnCount();

        // fredt -  props is null for internal connections, so always use the
        //          default behaviour in this case
        // JDBCDriver.getPropertyInfo says
        // default is true
        useColumnName = true;

        if (conn == null) {
            return;
        }

        useColumnName    = conn.isUseColumnName;
        translateTTIType = conn.isTranslateTTIType;
    }
