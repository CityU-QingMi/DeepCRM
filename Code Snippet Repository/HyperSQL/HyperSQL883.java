    public XAConnection getXAConnection() throws SQLException {

        // Comment out before public release:
/**/
/**/
/**/
/**/

        // Use JDBCDriver directly so there is no need to register with DriverManager
        JDBCConnection connection =
            (JDBCConnection) JDBCDriver.getConnection(url, connectionProps);
        JDBCXAConnection xaConnection = new JDBCXAConnection(this, connection);

        return xaConnection;
    }
