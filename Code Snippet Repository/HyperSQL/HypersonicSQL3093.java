    public static HsqlSocketRequestHandler createHsqlServer(String dbFilePath,
            boolean debugMessages, boolean silentMode) throws SQLException {

        ServerProperties props =
            new ServerProperties(ServerConstants.SC_PROTOCOL_HSQL);

        props.setProperty("server.dbname.0", "");
        props.setProperty("server.database.0", dbFilePath);
        props.setProperty("server.trace", debugMessages);
        props.setProperty("server.silent", silentMode);

        Server server = new Server();

        try {
            server.setProperties(props);
        } catch (Exception e) {
            throw new SQLException("Failed to set server properties: " + e);
        }

        if (!server.openDatabases()) {
            Throwable t = server.getServerError();

            if (t instanceof HsqlException) {
                throw JDBCUtil.sqlException((HsqlException) t);
            } else {
                throw JDBCUtil.sqlException(
                    Error.error(ErrorCode.GENERAL_ERROR));
            }
        }

        server.setState(ServerConstants.SERVER_STATE_ONLINE);

        // Server now implements HsqlSocketRequestHandler,
        // so there's really no need for HsqlSocketRequestHandlerImpl
        return server;
    }
